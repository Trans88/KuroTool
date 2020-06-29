package cn.trans88.kurotool.net.rx;

import android.content.Context;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.http.Url;

/**
 * @author TRS
 * Created on 2019/8/13
 * function : Network request
 */
public class RxRestClient {
    private static String URL;
    private static WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private RequestBody BODY;
    private final File FILE;
    private final Context CONTEXT;
    private final String BASE_URL;
    private final boolean USE_INTERCEPTOR;
    private RxRestService service;
    private final Map headers =new HashMap<String,String>();

    public RxRestClient(RequestBody body, File file,
                        Context context, String baseUrl,boolean useInterceptor) {
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        BASE_URL = baseUrl;
        USE_INTERCEPTOR =useInterceptor;
        RestCreator.setUse(USE_INTERCEPTOR);
        service = RestCreator.getRxRestService(BASE_URL);
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        if (URL == null) {
            try {
                throw new RoutingAddressNotFoundException("routing address is null or error please set routing address by setUrl() In front of get() !");
            } catch (RoutingAddressNotFoundException e) {
                e.printStackTrace();
            }
        }

        Observable<String> observable = null;
        switch (method) {
            case GET:
                observable = service.get(headers,URL, PARAMS);
                break;
            case POST:
                observable = service.post(headers,URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(headers,URL, BODY);
                break;
            case PUT:
                observable = service.put(headers,URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(headers,URL, BODY);
                break;
//            case DELETE:
//                observable = service.delete(URL, PARAMS);
//                break;
//            case UPLOAD:
//                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
//                final MultipartBody.Part body=MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
//                observable=RestCreator.getRxRestService().upload(URL,body);
//                break;
            default:
                break;
        }
        if (observable !=null){
            observable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }else {
            throw new NullPointerException("Observable is null!");
        }

        return observable;
    }


    public final RxRestClient setURL(String url) {
        URL = url;
        return this;
    }

    public final RxRestClient setParams(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClient setParam(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClient setHeaders(HashMap<String,String> headers){
        this.headers.putAll(headers);
        return this;
    }

    public final RxRestClient setHeader(String key, String value){
        this.headers.put(key, value);
        return this;
    }

    public final RxRestClient setBody(RequestBody body){
        this.BODY =body;
        return this;
    }





    //
//    /**
//     * 具体使用方法
//     */
    public final void get(BaseObserver observer) {
         subscribe(request(HttpMethod.GET),observer);
    }

    public final void  put(BaseObserver<?> observer) {
        if (BODY == null) {
            subscribe(request(HttpMethod.PUT),observer);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("putRaw params must be null");
            }
            subscribe(request(HttpMethod.PUT_RAW),observer);
        }

    }

    public final void post(BaseObserver<?> observer) {
        if (BODY == null) {
            subscribe(request(HttpMethod.POST),observer);
        } else {
            if (!PARAMS.isEmpty()) {
                //For the original data, the parameter must be empty
                throw new RuntimeException("postRaw params must be null");
            }
            subscribe(request(HttpMethod.POST_RAW),observer);
        }

    }
//
//    public final Observable<String> delete() {
//       return request(HttpMethod.DELETE);
//    }
//
//    public final Observable<String> upload(){
//       return request(HttpMethod.UPLOAD);
//    }
//
////    public final void download(){
////        new DownLoadHandler(URL,REQUEST,SUCCESS,FAILURE,DOWNLOAD_DIR,EXTENSION,NAME,ERROR).handleDownload();
////    }
    @SuppressWarnings("unchecked")
    private void subscribe (Observable<String> observable,Observer observer){
        observable.subscribe(observer);
    }
}
