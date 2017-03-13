package setpushchik.denis.testandersen.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import setpushchik.denis.testandersen.network.model.PlacesResponse;

/**
 * Created by Denis Stepushchik on 13.03.17.
 */

public class RedditApi {

    private static RedditService sRedditService;

    public static synchronized RedditService getRedditService() {
        if (sRedditService == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .build();

            sRedditService = new Retrofit.Builder()
                    .baseUrl(RedditService.BASE_API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(RedditService.class);
        }
        return sRedditService;
    }

    public interface RedditService {
        String BASE_API_URL = "https://www.reddit.com/r/EarthPorn/";
        String LIMIT_100_ADDITION = "limit=100";

        @GET("top/.json?" + LIMIT_100_ADDITION)
        Observable<PlacesResponse> getTop();

        @GET("new/.json?" + LIMIT_100_ADDITION)
        Observable<PlacesResponse> getNew();
    }
}
