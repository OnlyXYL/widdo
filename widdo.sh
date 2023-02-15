#!/bin/bash

# Java ENV（此处需要修改，需要预先安装JDK）,如果已经配置过环境变量，则此处可以省略
#export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.151-5.b12.el7_4.x86_64
#export JRE_HOME=${JAVA_HOME}/jre

# Apps Info
# 应用存放地址（此处需要修改）
APP_HOME=/usr/local/software/git_work/widdo/configurations/docker/context/target
# 应用名称
APP_NAME=$1
# widdo日志文件名
#WIDDO_LOG_FILE_NAME=/tmp/widdo-*

#widdo 日志
WIDDO_LOG_FILE_NAME=/usr/local/software/git_work/widdo/log

#获取当前时间
TIME=`date +"%Y%m%d%H%M%S"`


# Shell Info

# 使用说明，用来提示输入参数
usage() {
    echo "Usage: sh boot [APP_NAME] [build|start|stop|restart|status]"
    exit 1
}

# 检查程序是否在运行
is_exist(){
        # 获取PID
        PID=$(ps -ef |grep ${APP_NAME} | grep -v $0 |grep -v grep |awk '{print $2}')
        # -z "${pid}"判断pid是否存在，如果不存在返回1，存在返回0
        if [ -z "${PID}" ]; then
                # 如果进程不存在返回1
                return 1
        else
                # 进程存在返回0
                return 0
        fi
}

#先删除原来的临时文件
delete(){
       #先删除原来的临时文件
       echo "【WIDDO】开始删除widdo的日志文件..."
       rm -rf ${WIDDO_LOG_FILE_NAME}
       echo " "
       echo "widdo delete log success"
       echo " "
}

# 定义打包程序函数
# 打包函数会删除指定目录中已存在的jar包，然后把新的jar包复制进去
build(){
        #先删除原来的临时文件
        delete  
#        mvn clean install -DSkipTests | tee ${MAVEN_BUILD_LOG_FILE_NAME}${TIME}
        mvn clean install -DSkipTests

        if [ $? -eq "0" ]; then
                echo "widdo build success."
        else
                echo "widdo build failed."
        fi
}

# 定义启动程序函数
start(){
        is_exist
	if [ $? -eq "0" ]; then
                echo "${APP_NAME} is already running, PID=${PID}"
        else
                #nohup ${JRE_HOME}/bin/java -jar ${APP_HOME}/${APP_NAME} >/dev/null 2>&1 &
                #nohup java -jar ${APP_HOME}/${APP_NAME} >/tmp/${APP_NAME}.log 2>&1 &
                nohup java -jar ${APP_HOME}/${APP_NAME} >/dev/null 2>&1 &
	        PID=$(echo $!)
                echo "${APP_NAME} start success, PID=$!"
        fi
}

# 停止进程函数
stop(){
        is_exist
        if [ $? -eq "0" ]; then
                kill -9 ${PID}
		delete
                echo "${APP_NAME} process stop, PID=${PID}"
        else
                echo "There is not the process of ${APP_NAME}"
        fi
}
# 重启进程函数
restart(){
        stop
        start
}

# 查看进程状态
status(){
        is_exist
        if [ $? -eq "0" ]; then
                echo "${APP_NAME} is running, PID=${PID}"
        else
                echo "There is not the process of ${APP_NAME}"
        fi
}

case $2 in

"build")
        build
        ;;
"start")
        start
        ;;
"stop")
        stop
        ;;
"restart")
        restart
        ;;
"status")
       status
        ;;
        *)
        usage
        ;;
esac
exit 0
