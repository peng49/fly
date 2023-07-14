#!/bin/bash

FLY_PATH=/var/www/

params=$*

project="${1}"
if [ "$project" = "" ]; then
    project="web"
    params="web"
fi

params=${params/${project}/""}

case "$project" in
  "web")
      java -jar ${FLY_PATH}fly-web/application.jar -f ${FLY_PATH}fly-web/application.yml ${params}
    ;;
  "admin")
      java -jar ${FLY_PATH}fly-admin/application.jar -f ${FLY_PATH}fly-admin/application.yml ${params}
    ;;
esac