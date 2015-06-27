#!/bin/bash

#./start_server.sh --env=dev --node=0 --ip=192.168.1.11 --port=7650

ENVIRONMENT="dev"

function usage()
{
    echo "crawler server 1.0"
    echo ""
    echo "./start_server.sh"
    echo "\t-h --help"
    echo "\t-e --env dev or production"
    echo ""
}

if [ $# = 0 ]; then
   echo "no arguments provided."
   usage
   exit 1
fi

SNODE="0"
SIP=""
SPORT=5555
while [ "$1" != "" ]; do
    PARAM=`echo $1 | awk -F= '{print $1}'`
    VALUE=`echo $1 | awk -F= '{print $2}'`
    case ${PARAM} in
        -h | --help)
            usage
            exit
            ;;
        -e | --env)
            ENVIRONMENT=${VALUE}
            ;;
        *)
            echo "ERROR: unknown parameter \"$PARAM\""
            usage
            exit 1
            ;;
    esac
    shift
done

bin=`dirname "$0"`
BASEDIR=`cd "$bin/.."; pwd`

export BASEDIR

cd ${BASEDIR}/shell

source ./common.sh

#bash ${BASEDIR}/bin/stop_all.sh

cd ${BASEDIR}

CLASSPATH=${BASEDIR}/${ENVIRONMENT}/:${CLASSPATH}

mkdir -p ${BASEDIR}/logs

RPC_HEAP_OPTS="-Xmx1024m -Xms1024m"

if [ ${ENVIRONMENT} = "dev" ]; then
    RPC_HEAP_OPTS="-Xmx1024m -Xms1024m"
elif [ ${ENVIRONMENT} = "production"  ]; then
    RPC_HEAP_OPTS="-Xmx4096m -Xms4096m"
fi

SERVER_PERM_OPTS="-XX:+CMSClassUnloadingEnabled -XX:PermSize=256M -XX:MaxPermSize=512M"

echo "starting wanba-crawler server..."

#if [ -f "wanba-crawler-server.pid" ];then
#    echo "wanba-crawler-server.pid has existed, exit"
#else
    nohup ${JAVA} ${JAVA_OPTS}  ${RPC_HEAP_OPTS} ${SERVER_PERM_OPTS} ${GC_OPTS} -classpath ${CLASSPATH} -Dfile.encoding=UTF-8 -Dlog.home=${LOG_DIR} ${MAIN_CLASS} > /dev/null 2>&1 &
    echo $! >> wanba-crawler-server.pid
#fi
#
#exit