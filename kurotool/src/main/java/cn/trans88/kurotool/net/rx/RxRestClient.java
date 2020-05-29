package cn.trans88.kurotool.net.rx;

import android.content.Context;

import java.io.File;
import java.net.URI;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Url;

/**
 * @author TRS透明
 * Created on 2019/8/13
 * function : 网络请求
 */
public class RxRestClient {
    private static String URL;
    private static WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final RequestBody BODY;
    private final File FILE;
    private final Context CONTEXT;
    private final String BASE_URL;
    private final boolean USE_INTERCEPTOR;
    private RxRestService service;

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
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
//            case POST_RAW:
//                observable = service.postRaw(URL, BODY);
//                break;
//            case PUT:
//                observable = service.put(URL, PARAMS);
//                break;
//            case PUT_RAW:
//                observable = service.putRaw(URL, BODY);
//                break;
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

    public final RxRestClient setParams(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    //
//    /**
//     * 具体使用方法
//     */
    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    //
//    public final Observable<String> put() {
//        if (BODY == null) {
//           return request(HttpMethod.PUT);
//        } else {
//            if (!PARAMS.isEmpty()) {
//                throw new RuntimeException("putRaw params must be null");
//            }
//          return   request(HttpMethod.PUT);
//        }
//
//    }
//
    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                //原始的数据的话，参数一定需要为空
                throw new RuntimeException("postRaw params must be null");
            }
            return request(HttpMethod.POST_RAW);
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
}
