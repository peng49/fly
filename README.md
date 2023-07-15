# FLY
[![Build Status](https://travis-ci.com/peng49/fly.svg?branch=master)](https://travis-ci.com/peng49/fly)
[![java](https://img.shields.io/badge/language-java-orange.svg)](https://github.com/peng49/fly)
[![Code Size](https://img.shields.io/github/languages/code-size/peng49/fly.svg)](https://github.com/peng49/fly)
[![MIT](https://img.shields.io/badge/license-MIT-blue.svg)]((https://github.com/peng49/fly))

# Docker启动
启动容器需要先创建数据库

```
create database fly;
```

点击 [https://raw.githubusercontent.com/peng49/fly/master/fly.sql](https://raw.githubusercontent.com/peng49/fly/master/fly.sql)下载sql文件

将下载的`fly.sql`导入数据库中初始化数据库结构

启动容器

启动fly-web
```shell
$ docker run -it -d --rm \
     --name fly \
     -p 8081:8080 \
     -e DB_HOST=192.168.56.1 \
     -e DB_PORT=3306\
     -e DB_PASSWORD=password \
     -e DB_USERNAME=root \
     -e DB_DATABASE=fly peng49/fly:latest
```
启动fly-admin
```shell
$ docker run -it -d --rm \
     --name fly \
     -p 8081:8080 \
     -e DB_HOST=localhost \
     -e DB_PORT=3306\
     -e DB_PASSWORD=password \
     -e DB_USERNAME=root \
     -e DB_DATABASE=fly \
     -e FRONTEND_URL=. peng49/fly:latest admin
```

# 模块说明
```
fly
 ├─fly-admin 后台管理接口
 ├─fly-cache 缓存管理（待开发）
 ├─fly-common 公共模块 （待开发）
 ├─fly-web 前端页面模块 
 └─fly-search 查询模块 (待开发)
```

# 部分页面示例
首页
![首页](https://peng49.gitee.io/images/fly/index.jpg)

列表
![列表](https://peng49.gitee.io/images/fly/list.jpg)

详情
![详情](https://peng49.gitee.io/images/fly/detail.jpg)

个人主页
![个人主页](https://peng49.gitee.io/images/fly/user-center.jpg)

登录
![登录](https://peng49.gitee.io/images/fly/login.jpg)

注册
![注册](https://peng49.gitee.io/images/fly/register.jpg)


### 参考链接

[使用idea搭建Spring boot+jsp的简单web项目](https://www.cnblogs.com/fzly-88/p/12307063.html)
