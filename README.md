## 项目介绍
基于SpringBoot+Vue框架开发的B/S架构聊天室。使用WebSocket+Sock.js+Stomp.js实现消息的收发。

实现功能：用户注册登录，群组聊天，一对一聊天，添加删除好友，创建退出群组，发送图片。

前端工程源码：[Xunle1/chatRoom-front: 聊天室前端页面 (github.com)](https://github.com/Xunle1/chatRoom-front)

后端工程源码：[Xunle1/chatRoom: 简易聊天室 (github.com)](https://github.com/Xunle1/chatRoom)

---

## 后端技术栈

-   SpringBoot
-   Mybatis-plus
-   MySQL
-   WebSocket

## 前端技术栈

-   Vue
-   Vuetify
-   axios

## 项目预览

**登录页面**

![登录](https://gitee.com/xunle1/drawing-bed/raw/master/typora%5C/20210815024533.png)

**注册页面**

![注册](https://gitee.com/xunle1/drawing-bed/raw/master/typora%5C/20210815024617.png)

**主页面**

![主页面](https://gitee.com/xunle1/drawing-bed/raw/master/typora%5C/20210815024700.png)

**聊天页面**

![聊天页面](C:\Users\16013\AppData\Roaming\Typora\typora-user-images\image-20210815024843127.png)

## 部署过程

1.  将项目clone到本地

2.  创建数据库**chatRoom**，执行chatroom.sql文件

3.  修改application.yaml中的相关配置（数据库账户密码，阿里云oss keyId和keySecret）

4.  启动项目

5.  可进入[Swagger UI](http://localhost:8000/swagger-ui.html)查看接口定义

    ![Swagger](https://gitee.com/xunle1/drawing-bed/raw/master/typora%5C/20210815030254.png)

6.  启动Vue项目

## 存在的问题

-   删除好友和退出房间时不能正确显示（只显示最后一个）
-   发送图片时不能和文字记录保持间距
-   发送消息时每次都会读MySQL，应该换NoSQL实现
-   如果启动项目时报错，可以看看resource目录是否正确标识。IDEA中可以再project structure中将resource目录标为resource