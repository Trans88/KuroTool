package cn.trans88.kurotool.net.rx;

import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RxRestService {
    @GET
    Observable<String> get(@HeaderMap Map<String,String> headers, @Url String url, @QueryMap WeakHashMap<String, Object> params);

    @FormUrlEncoded
    @POST
    Observable<String> post(@HeaderMap Map<String,String> headers,@Url String url, @FieldMap Map<String, Object> params);

    @POST
    Observable<String> postRaw(@HeaderMap Map<String,String> headers,@Url String url, @Body RequestBody requestBody);

    @FormUrlEncoded
    @PUT
    Observable<String> put(@HeaderMap Map<String,String> headers, @Url String url, @FieldMap Map<String, Object> params);

    @PUT
    Observable<String> putRaw(@HeaderMap Map<String,String> headers,@Url String url, @Body RequestBody requestBody);


}
