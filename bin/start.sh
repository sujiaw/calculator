#!/bin/bash -xe

ENTRANCE="com.airwallex.calc.App"

cd ..
# 执行启动前先执行stop.sh
bash ./bin/stop.sh 2>/dev/null || true

java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath="${CWD}/tmp" -cp ./conf:./lib/* $ENTRANCE --spring.profiles.active=prod  2>&1
