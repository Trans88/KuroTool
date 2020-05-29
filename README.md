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
implementation 'com.trs.kuroTool:kuroTool:1.0.0'
```
或者

**Maven**
```
<dependency>
	<groupId>com.trs.kuroTool</groupId>
	<artifactId>kuroTool</artifactId>
	<version>1.0.0</version>
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
                .setParams("","")//输入参数，可输可不输，支持Hashmap,file,单一key-value
                .setURL("users/")//输入路由地址
                .get()//请求的方式
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TestEntity>() {//需要解析成的对象TestEntity可换成任意实体类型
                    @Override
                    protected void onGot(TestEntity entities) {
                        Log.e(TAG," getCommandId : "+entities.getCommand().getId());
                    }
                });
```

**打赏**

如果对您有所帮助，可以给我打赏，当做请我一杯咖啡或者一瓶酒作为奖励，您的帮助对我非常重要！

<img src="1590744983.jpg" style="zoom:20%;" />

<img src="weixin.png" style="zoom:18%;" />