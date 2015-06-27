#!/bin/bash

bin1=`dirname "$0"`
bin1=`cd "$bin1"; pwd`
BASEDIR1=`cd "${bin1}/.."; pwd`

function killpid()
{
  if [ ! -z "$*" ] ; then
	echo "kill $*"
    kill $*
  fi
}

function forceKillPid(){
   if [ ! -z "$*" ] ; then
      echo "force kill $*"
      kill -9 $*
    fi
}

PID=`cat wanba-crawler-server.pid | awk '{print $1}'`
if [ -n ${PID} ]
then
    killpid ${PID}
    sleep 10
    PID1=`ps -ef |grep java| grep 'wanba-crawler'| grep -v grep | awk '{print $2}'`
    if [ -n ${PID1} ] ; then
       forceKillPid ${PID1}
    fi
    rm wanba-crawler-server.pid
fi

exit 0
