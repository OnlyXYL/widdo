#!/bin/bash
set -euo pipefail

# ====== 确保实时输出 ======
exec 3>&1 4>&2
trap 'exec 2>&4 1>&3' 0 1 2 3
exec 1> >(tee -a build.log) 2>&1

# 禁用输出缓冲
if [ -t 1 ]; then
  stdbuf -o0 -e0 -i0
fi

# ====================== 配置区域 ======================
# 项目配置
PROJECT_NAME="widdo"
VERSION=$(date +%Y%m%d-%H%M%S)-$(git rev-parse --short HEAD)
SERVICES=("widdo-study" "widdo-life" "widdo-hadoop")

# 私有仓库配置
PRIVATE_REGISTRY="192.168.22.105:5000"
REGISTRY_USER="widdo"
REGISTRY_PASSWORD="widdo_docker"  # 实际使用应从安全存储获取

# 构建配置
JAVA_VERSION="21"
BUILDER_IMAGE="paketobuildpacks/builder-jammy-base:latest"
BUILD_CACHE_DIR="./build-cache"

# ====================== 打印构建信息 ======================
echo "🚀 构建开始于: $(date)"
echo "========================================"
echo "项目: $PROJECT_NAME"
echo "版本: $VERSION"
echo "仓库: $PRIVATE_REGISTRY"
echo "构建器: $BUILDER_IMAGE"
echo "========================================"

# 加载 .env 文件
if [ -f ".env" ]; then
    export $(grep -v '^#' .env | xargs)
fi

# 验证变量
if [ -z "${DOCKER_USER}" ] || [ -z "${DOCKER_PASSWORD}" ]; then
    echo "❌ 错误: 环境变量未设置"
    echo "请创建 .env 文件或设置环境变量"
    exit 1
fi

# ====================== 初始化 ======================
echo "🔧 [1/6] 初始化构建环境..."
mkdir -p "${BUILD_CACHE_DIR}"
export DOCKER_BUILDKIT=1

# ====================== 安全扫描 ======================
#echo "🔍 [2/6] 运行代码安全扫描..."
#mvn dependency-check:aggregate -Dformat=HTML
#echo "安全扫描报告生成: target/dependency-check-report.html"


# ====================== 构建本地依赖 ======================
echo "🔨 [2/6] 构建本地依赖..."
MAVEN_REPO_LOCAL="D:/Program Files/work/maven-repository-bmzt"
mvn -B -nsu clean install -am -DskipTests -Dmaven.deploy.skip=true -Dmaven.repo.local="$MAVEN_REPO_LOCAL"
# ====================== 构建镜像 ======================
echo "🏗️ [3/6] 构建生产镜像..."

  echo "  → 构建 widdo-gateway 服务..."

  # 记录开始时间
  START_TIME=$(date +%s)

  mvn -pl widdo-gateway spring-boot:build-image \
    -Dmaven.repo.local="$MAVEN_REPO_LOCAL" \
    -Dspring-boot.build-image.builder=${BUILDER_IMAGE} \
    -Dspring-boot.build-image.imageName=${PRIVATE_REGISTRY}/${PROJECT_NAME}/widdo-gateway:${VERSION} \
    -Dspring-boot.build-image.cache.dir=${BUILD_CACHE_DIR} \
    -Dspring-boot.build-image.env.BP_JVM_VERSION=${JAVA_VERSION} \
    -Dspring-boot.build-image.env.BP_OCI_AUTHOR=only_xyl@163.com \
    -Dspring-boot.build-image.env.BP_VULN_SCAN_ENABLED=true


for SERVICE in "${SERVICES[@]}"; do

  echo "  → 构建 ${SERVICE} 服务..."

  mvn -pl widdo-services/${SERVICE} spring-boot:build-image \
    -Dmaven.repo.local="$MAVEN_REPO_LOCAL" \
    -Dspring-boot.build-image.builder=${BUILDER_IMAGE} \
    -Dspring-boot.build-image.imageName=${PRIVATE_REGISTRY}/${PROJECT_NAME}/${SERVICE}:${VERSION} \
    -Dspring-boot.build-image.cache.dir=${BUILD_CACHE_DIR} \
    -Dspring-boot.build-image.env.BP_JVM_VERSION=${JAVA_VERSION} \
    -Dspring-boot.build-image.env.BP_OCI_AUTHOR=only_xyl@163.com \
    -Dspring-boot.build-image.env.BP_VULN_SCAN_ENABLED=true

    # 计算构建时间
      END_TIME=$(date +%s)
      DURATION=$((END_TIME - START_TIME))

      echo "✅ 构建完成! 耗时: ${DURATION}秒"

done

# ====================== 登录私有仓库 ======================
echo "🔑 [4/6] 登录私有仓库..."
echo "${REGISTRY_PASSWORD}" | docker login -u "${REGISTRY_USER}" --password-stdin "${PRIVATE_REGISTRY}" || {
    echo "❌ 仓库登录失败!"
    exit 1
}

# ====================== 推送镜像 ======================
echo "🚀 [5/6] 推送镜像到私有仓库..."

for SERVICE in $SERVICES; do
  echo "  → 推送 ${SERVICE} 镜像..."
  docker push "${PRIVATE_REGISTRY}/${PROJECT_NAME}/${SERVICE}:${VERSION}"

  echo "   镜像: $REGISTRY/$PROJECT_NAME/$SERVICE:$VERSION"

  # 添加最新标签
  docker tag "${PRIVATE_REGISTRY}/${PROJECT_NAME}/${SERVICE}:${VERSION}" \
    "${PRIVATE_REGISTRY}/${PROJECT_NAME}/${SERVICE}:latest"
  docker push "${PRIVATE_REGISTRY}/${PROJECT_NAME}/${SERVICE}:latest"
done

# ====================== 生成部署清单 ======================
echo "📝 [6/6] 生成部署清单..."
cat > deployment-manifest-${VERSION}.yaml <<EOF
# ${PROJECT_NAME} 部署清单
version: ${VERSION}
timestamp: $(date -u +"%Y-%m-%dT%H:%M:%SZ")
services:
  - name: widdo-study
    image: ${PRIVATE_REGISTRY}/${PROJECT_NAME}/widdo-study:${VERSION}
    ports: 8080
  - name: widdo-life
    image: ${PRIVATE_REGISTRY}/${PROJECT_NAME}/widdo-life:${VERSION}
    ports: 8080
  - name: widdo-hadoop
    image: ${PRIVATE_REGISTRY}/${PROJECT_NAME}/widdo-hadoop:${VERSION}
    ports: 8080
EOF

# ====================== 完成输出 ======================
echo "✅ 构建和推送完成! 版本: ${VERSION}"
echo "=========================================="
echo "镜像仓库: ${PRIVATE_REGISTRY}/${PROJECT_NAME}"
echo "部署清单: deployment-manifest-${VERSION}.yaml"
echo "日志文件: build.log"
echo "总耗时: $(($(date +%s) - START_TIME))秒"
echo "=========================================="