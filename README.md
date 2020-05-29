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
                .subscribeOn(Schedulers.io())//切换到IO线程
                .observeOn(AndroidSchedulers.mainThread())//切换回主线程
                .subscribe(new BaseObserver<TestEntity>() {//需要解析成的对象TestEntity可换成任意对象
                    @Override
                    protected void onGot(TestEntity entities) {
                        Log.e(TAG," getCommandId : "+entities.getCommand().getId());
                    }
                });
```
