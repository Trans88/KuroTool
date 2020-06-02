package cn.trans88.kurotool.net.rx;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

class RestCreator {

    private static final ArrayList<Interceptor> INTERCEPTORS =new ArrayList<>();
    private static String BASE_URL= "";
    private static boolean mUse =true;
//    private static String BASE_URL= "";

    private static final class ParamsHolder{
        static final WeakHashMap<String, Object> PARAMS=new WeakHashMap<>();
    }

    static WeakHashMap<String, Object> getParams(){
        return ParamsHolder.PARAMS;
    }



    private static final class RetrofitHolder{


        private static final Retrofit RETROFIT_CLIENT =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static final class OKHttpHolder{
        private static final int TIME_OUT=60;

        private static final OkHttpClient.Builder BUILDER=new OkHttpClient.Builder();

        private static final ArrayList<Interceptor> INTERCEPTORS =getInterceptor();

        private static OkHttpClient.Builder addInterceptor(){
            if (mUse){
                if (INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()){
                    for (Interceptor interceptors: INTERCEPTORS) {
                        BUILDER.addInterceptor(interceptors);
                    }
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RxRestServiceHolder{
        private static final RxRestService REST_SERVICE= RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }
    public static RxRestService getRxRestService(){
//        setBaseUrl(baseUrl);
        return RxRestServiceHolder.REST_SERVICE;
    }

    static void setUse(boolean use){
            mUse=use;
    }

    static RxRestService getRxRestService(String baseUrl){
        setBaseUrl(baseUrl);
        return RxRestServiceHolder.REST_SERVICE;
    }

    private static void setBaseUrl(String baseUrl){
        BASE_URL =baseUrl;
    }

    public static void addInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
    }

    private  static ArrayList<Interceptor> getInterceptor(){
        HttpLoggingInterceptor loggingInterceptor =new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                try {
                    String message = URLDecoder.decode(s, "utf-8");
                    Log.e("TRS","OKHTTP ---------"+message);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        INTERCEPTORS.add(loggingInterceptor);
        return INTERCEPTORS;
    }

}
