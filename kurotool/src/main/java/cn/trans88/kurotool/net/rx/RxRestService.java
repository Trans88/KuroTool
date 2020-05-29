package cn.trans88.kurotool.net.rx;

import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RxRestService {
    @GET
    Observable<String> get(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @FormUrlEncoded
    @POST
    Observable<String> post(String url, @FieldMap Map<String, Object> params);
}
