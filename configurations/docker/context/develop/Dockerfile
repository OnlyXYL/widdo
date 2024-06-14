### 基础镜像，使用alpine操作系统，openjkd使用
FROM 101.43.194.240:5000/jdk:openjdk14

# 作者
LABEL authro="widdo" email="only_xyl@163.com"

ARG VERSION
ARG SERVICE_NAMEd
ARG SERVICE_PORT
ENV VERSION ${VERSION}
ENV SERVICE_NAME ${SERVICE_NAME}
ENV SERVICE_PORT ${SERVICE_PORT}

# 系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8
# 运行参数
ENV JAVA_OPTS=""

# 声明一个挂载点，容器内此路径会对应宿主机的某个文件夹
VOLUME /tmp

# 应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD ./target/${SERVICE_NAME}-${VERSION}.jar /app.jar

# 声明运行时端口
EXPOSE ${SERVICE_PORT}

# 启动容器时的进程
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar