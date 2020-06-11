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
                .baseUrl("xxxx") //输入基本的ip地址构建RxRestClient对象
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
**在kotlin环境下**
先在Application中初始化

```kotlin
//初始化ApiClient,第一个参数传入baseUrl,第二个参数传是否使用拦截器
ApiClient.init("xxxx",true)
```
再请求具体的接口
```kotlin
val test = ApiClient.get<TestEntity>("users/")
Log.e("test","date : "+test.command.id)
```
对！只需一行就能请求到对应的接口并转化成实体类，转化的类型通过泛型传入，得益于kotlin对回调的优化

**如何使用webSocket长连接：**

```java
WsManager wsManager = WsManager.builder()
                .wsUrl("ws://ledok.cn:8083/")//连接地址
                .heartBeatTime(10*1000)//设置心跳时间,默认时间30s
                .reconnect(true)//设置是否重连，默认为true
                .reconnectTime(5*1000)//设置重连时间,默认时间10s
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
        wsManager.stopConnect(1000,"normal close");//停止连接，接收两个参数code和提示内容
```

**打赏**

如果对您有所帮助，可以给我打赏，当做请我一杯咖啡或者一瓶酒作为奖励，您的帮助对我非常重要！

<img src="README.assets/1590744983.jpg" height="auto" width ="400" style="zoom:22%;" /><img src="README.assets/weixin.png" height="auto" width ="400" style="zoom:20%;" />