#!/bin/bash

OS=`uname`
IP="" # store IP
case ${OS} in
   Linux)
        if [ -f /sbin/ifconfig ]
        then
            IP=`/sbin/ifconfig  | grep 'inet addr:'| grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $1}' | head -n 1`
        else
        # some linux use /bin/ifconfig (e.g. gentoo)
            IP=`/bin/ifconfig | grep 'inet ' | grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $2}' | head -n 1`
        fi
        ;;
   FreeBSD|OpenBSD|Darwin) IP=`ifconfig | grep -E '^en[0-9]:' -A 4 | grep -E 'inet.[0-9]' | grep -v '127.0.0.1' | awk '{ print $2}' | head -n 1` ;;
   SunOS) IP=`ifconfig -a | grep inet | grep -v '127.0.0.1' | awk '{ print $2} ' | head -n 1` ;;
   *) IP="Unknown";;
esac

LIB=${BASEDIR}/lib
LOG_DIR=${BASEDIR}/logs

CLASSPATH=${BASEDIR}
CLASSPATH=${CLASSPATH}:${BASEDIR}/com:$${BASEDIR}/org

for f in `find ${BASEDIR}/lib -type f -name "*.jar"` `find ${BASEDIR}/lib -type f -name "*.zip"`
do
  CLASSPATH=${CLASSPATH}:${f}
done

GC_OPTS="-verbosegc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:$LOG_DIR/gc.log -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0 -XX:CMSInitiatingOccupancyFraction=70 -XX:+UseCMSInitiatingOccupancyOnly -XX:+DisableAttachMechanism"
JAVA_OPTS="-server -d64 -Dfile.encoding=UTF8"

if [ -z "$JAVA_HOME" ]; then
  JAVA="java"
else
  JAVA="$JAVA_HOME/bin/java"
fi

MAIN_CLASS="com.qinziwanba.crawler.CrawlerApplication"
