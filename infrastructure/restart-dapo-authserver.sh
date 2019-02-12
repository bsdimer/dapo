#!/bin/bash
project=dapo-authserver
tag=0.0.1-SNAPSHOT
cid=`docker ps | grep $project | awk '{ print $1 }'`
docker stop $cid
docker container rm $cid
docker image rm docker-registry.aossia.com:5000/$project:$tag
docker image pull docker-registry.aossia.com:5000/dapo-api:$tag
docker-compose create $project
docker-compose start $project
