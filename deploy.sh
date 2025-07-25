# 构建镜像（仅需一次）
#windows 环境，启用 BuildKit   #$env:DOCKER_BUILDKIT=1
DOCKER_BUILDKIT=1 docker build -t widdo-system:latest .

# 启动所有服务（包括Nacos）
docker-compose up -d

# 查看服务状态
docker-compose ps

# 动态更新配置（无需重启服务）
# 1. 登录Nacos控制台: http://localhost:8848/nacos (默认账号nacos/nacos)
# 2. 修改配置后，调用服务刷新接口：
curl -X POST http://localhost:9901/actuator/refresh  # 刷新生活服务

# 停止所有服务
docker-compose down