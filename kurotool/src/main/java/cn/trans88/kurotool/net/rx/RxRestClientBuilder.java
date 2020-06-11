package cn.trans88.kurotool.net.rx;

import android.content.Context;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RxRestClientBuilder {
    private String mUrl=null;
    private  static final Map<String, Object> PARAMS=RestCreator.getParams();
    private  RequestBody mRequestBody=null;
    private Context mContext=null;
    private File mFile=null;
    private String mBaseUrl=null;
    private boolean mUse =true;
    private Map HEADERS =new HashMap<String,String>();

    RxRestClientBuilder(){

    }

    public final RxRestClientBuilder useInterceptor(Boolean bool){
        this.mUse=bool;
        return this;
    }

//    public final RxRestClientBuilder headers(HashMap<String,String> headers){
//        this.HEADERS=headers;
//        return this;
//    }

    public final RxRestClientBuilder baseUrl(String baseUrl){
        this.mBaseUrl=baseUrl;
        return this;
    }

//    public final RxRestClientBuilder url(String url){
//        this.mUrl=url;
//        return this;
//    }
//    public final RxRestClientBuilder parmas(WeakHashMap<String, Object> params){
//        PARAMS.putAll(params);
//        return this;
//    }
//    public final RxRestClientBuilder parmas(String key, Object value){
//        PARAMS.put(key,value);
//        return this;
//    }
//    public final RxRestClientBuilder file(File file){
//        this.mFile=file;
//        return this;
//    }
//    public final RxRestClientBuilder file(String filePath){
//        this.mFile=new File(filePath);
//        return this;
//    }
//
//    public final RxRestClientBuilder raw(String raw){
//        this.mRequestBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
//        return this;
//    }
    public final RxRestClient build(){
        return new RxRestClient(mRequestBody,mFile,mContext,mBaseUrl,mUse);
    }
}
