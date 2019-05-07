# tomcat-explore
仿造 tomcat 实现的 web 容器，用于深入学习tomcat

## 相关知识补充 ##

### 超文本传输协议（http)
&emsp;HTTP 是一种协议，允许 web 服务器和浏览器通过互联网进行来发送和接受数据。HTTP 使用可靠的 TCP 连接--TCP 默认
使用 80 端口。

### HTTP 请求 ###
**例子：**
```
POST /api/material/new-material HTTP/1.1
Host: testsj.isenruan.com
Content-Length: 40
Accept: application/json, text/plain, */*
Origin: http://testsj.isenruan.com
User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36
Content-Type: application/json;charset=UTF-8
Referer: http://testsj.isenruan.com/
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9
Cookie: _uab_collina=152463537834564730202066; Hm_lvt_56ce5d50e73be1e9457debf65fd116a9=1533614636,1533697094,1534238450,1535015592; Hm_lpvt_56ce5d50e73be1e9457debf65fd116a9=1535015592; JSESSIONID=8089A1C0DFF8DFBF7F67421220CA2CE9; _umdata=0823A424438F76AB5309218859839ADDFCDAE3DE31C87F7BB160095DB5348CE17CF2458A5117E379CD43AD3E795C914C2B5CE26415F25A83DC7C16100F54E381
Connection: keep-alive

{"page":{"pageNO":1,"everyPageCount":5}}
```
一个 HTTP 请求包括三个组成部分：
* 方法—统一资源标识符(URI)—协议/版本
* 请求的头部
* 主体内容

&emsp;方法—统一资源标识符(URI)—协议/版本出现在请求的第一行。

&emsp;请求的头部包含了关于客户端环境和请求的主体内容的有用信息。例如它可能包括浏览器设
置的语言，主体内容的长度等等。

&emsp;**请求的头部和主体内容通过一个换行符(CRLF）分隔**

### http响应 ###
**例子：**
```
HTTP/1.1 200 
Server: nginx/1.12.2
Date: Thu, 23 Aug 2018 09:13:17 GMT
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
X-Application-Context: agent-web-api:prod:9001
Access-Control-Allow-Origin: *
Proxy-Connection: keep-alive

{"success":true,"data":{"page":{"pageNO":1,"everyPageCount":5,"totalCount":36,"firstResult":0,"limit":5,"offset":0},"agentMaterialCommons":[{"id":58,"name":"测试物料","url":"20180118/20180118/4f36d2b7-f0f0-4283-ba96-ebbd8e9c5dec.gif","type":"1234","createTime":1516272145000,"creator":1,"description":"此物料测试专用","managerName":"官方"},{"id":57,"name":"111","url":"20180118/20180118/89f0637b-472a-45ec-b352-99133f7b4f28.png","type":"1111","createTime":1516271468000,"creator":1,"description":"111","managerName":"官方"}]}}
```
类似于 HTTP 请求，一个 HTTP 响应也包括三个组成部分：
* 方法—统一资源标识符(URI)—协议/版本
* 响应的头部
* 主体内容

&emsp;响应头部的第一行类似于请求头部的第一行。第一行告诉你该协议使用 HTTP 1.1，请求成功(200=成功)，表示一切都运行良好。响应头部和请求头部类似，也包括很多有用的信息。响应的主体内容是响应本身的 HTML 内容。头部和主体内容通过 CRLF 分隔开来

### Socket类 ###
&emsp;套接字是网络连接的一个端点。套接字使得一个应用可以从网络中读取和写入数据。放在两个不同计算机上的两个应用可以通过连接发送和接受字节流。为了从你的应用发送一条信息到另一个应用，你需要知道另一个应用的 IP 地址和套接字端口。在 Java 里边，套接字指的是java.net.Socket 类。

### ServerSocket类 ###
&emsp;java.net.ServerSocket 类。这是服务器套接字的实现,和 Socket 不同，服务器套接字的角色是等待来自客户端的连接请求。一旦服务器套接字获得一个连接请求，它创建一个 Socket 实例来与客户端进行通信。服务器套接字的另一个重要的属性是 backlog，这是服务器套接字开始拒绝传入的请求之前，传入的连接请求的最大队列长度