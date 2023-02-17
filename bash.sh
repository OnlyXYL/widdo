#!/bin/bash
source ~/.bash_profile
#JAVA_OPTIONS_INITIAL=-Xms128M
#JAVA_OPTIONS_MAX=-Xmx512M
echo "Application [$1]-[profile-$2] $3"
JAVA_OPTS="-server  -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+UseParallelGC"
_JAR_NAME=$1
APP_NAME=$1
SPRING_BOOT_PROFILE=$2
PID=$(ps aux|grep ${_JAR_NAME} |grep -v grep | grep -v $0 | awk '{print $2}')
#SERVICE_NAME=`echo $_JAR_NAME | awk -F '.jar' '{print $1}'`
#SKYWALKING_OPTS="-javaagent:/home/skywalking-agent/skywalking-agent.jar -Dskywalking.agent.service_name=$SERVICE_NAME"
function check_if_process_is_running {
if [ "$PID" = "" ];
then
return 1
fi
ps -p $PID |grep "java"
return $?
}

case "$3" in
status)
if check_if_process_is_running
then
echo "$APP_NAME is running...."
else
echo "$APP_NAME is not running...."
fi
;;
stop)
if ! check_if_process_is_running
then
echo "$APP_NAME is alread stopped."
exit 0
fi
kill -9 $PID
echo "Waiting for process to stop......"
NOT_KILLED=1
for i in {1..20}; do
if check_if_process_is_running
then
echo "."
sleep 1
else
NOT_KILLED=0
fi
done

if [ $NOT_KILLED = 1 ]
then
echo "Cannot kill process"
exit 1
fi
echo "the $APP_NAME already stoped"
;;
start)
if [ "$PID" != "" ] && check_if_process_is_running
then
echo "$APP_NAME already running "
exit 1
fi

nohup java $JAVA_OPTS -jar $_JAR_NAME  --spring.profiles.active=$SPRING_BOOT_PROFILE > ./$_JAR_NAME.log 2>&1 &

echo "Starting ..."
for i in {1..20};do
echo "."
sleep 1
done

if check_if_process_is_running
then
echo "$APP_NAME fail"
else
echo "$APP_NAME started"
fi
;;
restart)
sh $0 $1 $2 stop
if [ $? = 1 ]
then
exit 1
fi
sh $0 $1 $2 start
;;
*)
echo "Usage: $3 {start|stop|restart|status}"
exit 1
esac

exit 0