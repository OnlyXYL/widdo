# ========== 第一阶段：构建阶段 (JDK 21) ==========
FROM xiayaling/maven:3.9.11-eclipse-temurin-21-alpine AS builder
WORKDIR /build

# 设置本地仓库位置
ENV MAVEN_REPO_LOCAL=/build/.m2/repository
ENV MAVEN_OPTS="-Dmaven.repo.local=${MAVEN_REPO_LOCAL} -Xmx1024m"

RUN echo "MAVEN_REPO_LOCAL: ${MAVEN_REPO_LOCAL}" && \
    mkdir -p ${MAVEN_REPO_LOCAL}

# 1. 复制POM文件（利用Docker缓存层）
COPY pom.xml .
COPY widdo-bom/pom.xml widdo-bom/
COPY widdo-autoconfigure/pom.xml widdo-autoconfigure/
COPY widdo-gateway/pom.xml widdo-gateway/
COPY widdo-docs/pom.xml widdo-docs/
COPY widdo-packages/pom.xml widdo-packages/
COPY widdo-packages/widdo-assistant/pom.xml widdo-packages/widdo-assistant/
COPY widdo-packages/widdo-cache/pom.xml widdo-packages/widdo-cache/
COPY widdo-packages/widdo-data/pom.xml widdo-packages/widdo-data/
COPY widdo-register/pom.xml widdo-register/
COPY widdo-services/pom.xml widdo-services/
COPY widdo-services/widdo-life/pom.xml widdo-services/widdo-life/
COPY widdo-services/widdo-study/pom.xml widdo-services/widdo-study/
COPY widdo-services/widdo-hadoop/pom.xml widdo-services/widdo-hadoop/
COPY widdo-starters/pom.xml widdo-starters/
COPY widdo-starters/widdo-starter-elasticsearch/pom.xml widdo-starters/widdo-starter-elasticsearch/
COPY widdo-starters/widdo-starter-hadoop/pom.xml widdo-starters/widdo-starter-hadoop/
COPY widdo-starters/widdo-starter-jena/pom.xml widdo-starters/widdo-starter-jena/
COPY widdo-starters/widdo-starter-neo4j/pom.xml widdo-starters/widdo-starter-neo4j/
COPY widdo-starters/widdo-starter-orientdb/pom.xml widdo-starters/widdo-starter-orientdb/
COPY widdo-starters/widdo-starter-sql/pom.xml widdo-starters/widdo-starter-sql/

# 2. 下载所有依赖（节省80%构建时间）
RUN mvn -B dependency:resolve -T 1C -Dmaven.repo.local=${MAVEN_REPO_LOCAL}

# 3. 复制源代码并构建
COPY . .
RUN mvn -B clean install -DskipTests\
    -Dmaven.compiler.release=21 \
    -Dmaven.repo.local=${MAVEN_REPO_LOCAL} \
    -pl '!widdo-docs,!widdo-register' \
    -am


# ========== 第二阶段：运行时阶段 (JRE 21) ==========
FROM xiayaling/eclipse-temurin:21-jre-alpine-3.21
WORKDIR /app

# 设置时区
RUN apk add --no-cache tzdata && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone

# 添加CDS (Class Data Sharing) 支持 - JDK 21 优化
RUN java -Xshare:dump -XX:+UnlockDiagnosticVMOptions -XX:SharedArchiveFile=app-cds.jsa \
    --illegal-access=deny -Djdk.debug=cds

# 从构建阶段复制jar包
COPY --from=builder /build/widdo-gateway/target/*.jar ./gateway.jar
COPY --from=builder /build/widdo-services/widdo-life/target/*.jar ./life.jar
COPY --from=builder /build/widdo-services/widdo-study/target/*.jar ./study.jar
COPY --from=builder /build/widdo-services/widdo-hadoop/target/*.jar ./hadoop.jar

# 动态启动脚本
RUN echo $'#!/bin/sh\n\
case $SERVICE_TYPE in\n\
  GATEWAY) APP_NAME="widdo-gateway"; exec java $JAVA_OPTS -Dspring.application.name=$APP_NAME -jar gateway.jar ;;\n\
  LIFE)    APP_NAME="widdo-life";    exec java $JAVA_OPTS -Dspring.application.name=$APP_NAME -jar life.jar    ;;\n\
  STUDY)   APP_NAME="widdo-study";   exec java $JAVA_OPTS -Dspring.application.name=$APP_NAME -jar study.jar   ;;\n\
  HADOOP)  APP_NAME="widdo-hadoop";  exec java $JAVA_OPTS -Dspring.application.name=$APP_NAME -jar hadoop.jar  ;;\n\
  *)       echo "Unknown service: $SERVICE_TYPE"; exit 1 ;;\n\
esac\n\
\n\
# 使用CDS加速启动\n\
if [ -f "app-cds.jsa" ]; then\n\
  exec java $JAVA_OPTS \\\n\
    -XX:SharedArchiveFile=app-cds.jsa \\\n\
    -Dspring.application.name=$APP_NAME \\\n\
    -jar $JAR "$@"\n\
else\n\
  exec java $JAVA_OPTS \\\n\
    -Dspring.application.name=$APP_NAME \\\n\
    -jar $JAR "$@"\n\
fi' > entrypoint.sh && chmod +x entrypoint.sh

# 健康检查
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -sSf http://localhost:${SERVER_PORT:-8080}/actuator/health >/dev/null || exit 1

EXPOSE 9900 9901 9902 9903
ENTRYPOINT ["./entrypoint.sh"]