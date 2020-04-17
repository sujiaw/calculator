#!/bin/bash -xe

ENTRANCE="com.airwallex.calc.App"
# kill
SPID=`ps aux | grep ${ENTRANCE} | grep -v grep | grep -v kill | awk '{print $2}'`

if [ -n "$SPID" ]; then
  kill ${SPID}
  while kill -0 ${SPID} 2>/dev/null; do echo "calculator is shutting down..."; sleep 1; done
fi
