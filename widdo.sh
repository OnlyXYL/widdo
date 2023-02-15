#! /bin/sh
# 端口号
# Apps Info
# 应用存放地址（此处需要修改）
WIDDO_JAR_PATH=/usr/local/software/git_work/widdo/configurations/docker/context/target

# 版本信息
WIDDO_VERSION=$1

#widdo 日志
WIDDO_LOG_PATH=/usr/local/software/git_work/widdo/log

#jar包数组
JARS=(widdo-cloud-gateway-${WIDDO_VERSION}.jar widdo-study-${WIDDO_VERSION}.jar widdo-life-${WIDDO_VERSION}.jar)

# 模块 用于启动命令时单个启动，可以不用版本及后缀。
MODULES=(widdo-cloud-gateway widdo-study widdo-life)
# 模块名称 可以写中文也可以
MODULE_NAMES=(widdo-cloud-gateway widdo-study widdo-life)

#先删除原来的临时文件
delete(){
       #先删除原来的临时文件
       echo "【WIDDO】开始删除widdo的日志文件..."
       rm -rf ${WIDDO_LOG_PATH}
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

start() {

  echo  "-------- widdo start running --------"

  local MODULE=
  local MODULE_NAME=
  local JAR_NAME=
  local command="$1"
  local commandOk=0
  local count=0
  local okCount=0
  for((i=0;i<${#MODULES[@]};i++))
  do
    MODULE=${MODULES[$i]}
    MODULE_NAME=${MODULE_NAMES[$i]}
    JAR_NAME=${JARS[$i]}
    if [ "$command" == "all" ] || [ "$command" == "$MODULE" ];then
      commandOk=1
      count=0
      PID=`ps -ef |grep $(echo $JAR_NAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
      if [ -n "$PID" ];then
        echo "$MODULE---$MODULE_NAME:已经运行,PID=$PID"
      else
        nohup java -Xms256m -Xmx512m -jar $WIDDO_JAR_PATH/$JAR_NAME >> /dev/null 2>&1 &
        PID=`ps -ef |grep $(echo $JAR_NAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
        while [ -z "$PID" ]
        do
          if (($count == 30));then
            echo "$MODULE---$MODULE_NAME:$(expr $count \* 10)秒内未启动,请检查!"
            break
          fi
          count=$(($count+1))
          echo "$MODULE_NAME启动中.................."
          sleep 5s
          PID=`ps -ef |grep $(echo $JAR_NAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
        done
        okCount=$(($okCount+1))
        echo "$MODULE---$MODULE_NAME:已经启动成功,PID=$PID"
      fi
    fi
  done
  if(($commandOk == 0));then
    echo "第二个参数输入错误"
  else
    echo "............本次共启动:$okCount个服务..........."
  fi
}

stop() {
  local MODULE=
  local MODULE_NAME=
  local JAR_NAME=
  local command="$1"
  local commandOk=0
  local okCount=0
  for((i=0;i<${#MODULES[@]};i++))
  do
    MODULE=${MODULES[$i]}
    MODULE_NAME=${MODULE_NAMES[$i]}
    JAR_NAME=${JARS[$i]}
    if [ "$command" = "all" ] || [ "$command" = "$MODULE" ];then
      commandOk=1
      PID=`ps -ef |grep $JAR_NAME | grep -v grep | awk '{print $2}'`
      if [ -n "$PID" ];then
        echo "$MODULE---$MODULE_NAME:准备结束,PID=$PID"
        kill -9 $PID
	rm -rf "$WIDDO_LOG_PATH/$MODULE_NAME"
	PID=`ps -ef |grep $JAR_NAME | grep -v grep | awk '{print $2}'`
        while [ -n "$PID" ]
        do
          sleep 3s
          PID=`ps -ef |grep $JAR_NAME | grep -v grep | awk '{print $2}'`
        done
        echo "$MODULE---$MODULE_NAME:成功结束"
        okCount=$(($okCount+1))
      else
        echo "$MODULE---$MODULE_NAME:未运行"
      fi
    fi
  done
  if (($commandOk == 0));then
    echo "第二个参数输入错误"
  else
    echo "............本次共停止:$okCount个服务............"
  fi
}


case "$2" in
  start)
    start "$3"
  ;;
  stop)
    stop "$3"
  ;;
  restart)
    stop "$3"
    sleep 3s
    start "$3"
  ;;
  *)
    echo "第一个参数请输入:start|stop|restart"
    exit 1
  ;;
esac
