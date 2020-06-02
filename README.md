# KuroTool
### 添加方法:
1、添加Jcenter仓库
```
buildscript {
    repositories {
        ...
        jcenter()
        ...
    }
}
```

**Gradle依赖：**
```kotlin
implementation 'com.trs.kuroTool:kuroTool:1.0.1'
```
或者

**Maven**
```
<dependency>
	<groupId>com.trs.kuroTool</groupId>
	<artifactId>kuroTool</artifactId>
	<version>1.0.1</version>
	<type>pom</type>
</dependency>
```
### 使用方法:

Rxjava+Retrofit+OkHttp的综合性网络请求框架

**如何使用网络请求功能:**

```java
RxRestClient rxRestClient = RxRestClient.builder()
                .baseUrl("http://192.168.8.66:8083/") //输入基本的ip地址构建RxRestClient对象
                .useInterceptor(true)//是否开启Http拦截器，默认开启
                .build();
        rxRestClient
                .setParams("", "")//输入参数，可输可不输，支持Hashmap,file,单一key-value
                .setURL("users/") //输入路由地址
                .post(new BaseObserver<TestEntity>() {//请求方式，传入一个观察者,需要解析成的对象TestEntity可换成任意实体对象
                    @Override
                    protected void onGot(TestEntity entities) {
                        Log.e(TAG," getCommandId : "+entities.getCommand().getId());
                    }
                });
```
**如何使用webSocket长连接**
```java
WsManager wsManager = WsManager.builder()
                .wsUrl("ws://ledok.cn:8083/")//连接地址
                .heartBeatTime(10*1000)//设置心跳时间
                .reconnect(true)//设置是否重连，默认为true
                .reconnectTime(5*1000)//设置重连时间
                .heartBeatContext("beat")//设置心跳发送的内容，此内容为空时停止心跳
                .addSendString("test send string 1")//设置连接开始时发送的内容，接收String
                .addSendString("test send string 2")
                .addSendStringList(testString)//设置连接开始时发送的内容，接收List<String>
                .build();
        wsManager
                .startConnect()//开始连接
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onGot(String entities) {//接收服务器推送的内容
                        Log.e(TAG, "onGot: "+entities);
                    }
                });

        wsManager.send("test");//向服务器发送的内容
```

**打赏**

如果对您有所帮助，可以给我打赏，当做请我一杯咖啡或者一瓶酒作为奖励，您的帮助对我非常重要！

<img src="README.assets/1590744983.jpg" style="zoom:22%;" /><img src="README.assets/weixin.png" style="zoom:20%;" />