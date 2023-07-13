#! /bin/bash

# 构建docker镜像

docker build -t peng49/fly-admin-vue:v0.1 .

docker build -t peng49/fly-admin-vue:v0.1 .
docker tag peng49/fly-admin-vue:v0.1 peng49/fly-admin-vue:latest

echo "${DOCKER_PASSWORD}" | docker login -u "${DOCKER_USERNAME}" --password-stdin
docker push peng49/fly-admin-vue:v0.1
docker push peng49/fly-admin-vue:latest


