package is24.mapi;

import io.reactivex.Single;
import is24.mapi.Model.*;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Map;

public class Api {

    RestApi restApi;
    AuthApi authApi;
    LegacyRestApi legacyRestApi;

    public Api() {
        this(true);
    }

    public Api(boolean useAuthInterceptor) {

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (useAuthInterceptor) {
            AuthInterceptor authInterceptor = new AuthInterceptor();
            builder.addInterceptor(authInterceptor);
        }

        OkHttpClient client = builder.addInterceptor(logInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApi.ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restApi = retrofit.create(RestApi.class);

        retrofit = new Retrofit.Builder()
                .baseUrl(LegacyRestApi.ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        legacyRestApi = retrofit.create(LegacyRestApi.class);

        authApi = new AuthApi();
    }

    public Single<SearchResponse> search(Map<String, String> parameters) {
        return Single.create(e -> {
            Response<SearchResponse> response = restApi.search(parameters).execute();
            if (response.isSuccessful()) {
                SearchResponse searchResponse = response.body();
                if (searchResponse == null) {
                    e.onError(new NullPointerException("SearchResponse is null"));
                } else {
                    e.onSuccess(searchResponse);
                }
            } else {
                ResponseBody errorBody = response.errorBody();
                e.onError(new IOException(errorBody == null ? null : errorBody.string()));
            }
        });
    }

    public Single<SearchResponse> search(Map<String, String> parameters, String authBearer) {
        return Single.create(e -> {
            Response<SearchResponse> response = restApi.search(parameters, authBearer).execute();
            if (response.isSuccessful()) {
                SearchResponse searchResponse = response.body();
                if (searchResponse == null) {
                    e.onError(new NullPointerException("SearchResponse is null"));
                } else {
                    e.onSuccess(searchResponse);
                }
            } else {
                ResponseBody errorBody = response.errorBody();
                e.onError(new IOException(errorBody == null ? null : errorBody.string()));
            }
        });
    }

    public Single<Expose> getExpose(String exposeId) {
        return Single.create(e -> {
            Response<ExposeResponse> response = restApi.getExpose(exposeId).execute();
            if (response.isSuccessful()) {
                ExposeResponse exposeResponse = response.body();
                if (exposeResponse == null) {
                    e.onError(new NullPointerException("ExposeResponse is null"));
                } else {
                    e.onSuccess(new Expose(exposeResponse));
                }
            } else {
                ResponseBody errorBody = response.errorBody();
                e.onError(new IOException(errorBody == null ? null : errorBody.string()));
            }
        });
    }


    public Single<LegacyExpose> getLegacyExpose(String exposeId) {
        return Single.create(e -> {
            Response<LegacyExposeResponse> response = legacyRestApi.getExpose(exposeId).execute();
            if (response.isSuccessful()) {
                LegacyExposeResponse exposeResponse = response.body();
                if (exposeResponse == null) {
                    e.onError(new NullPointerException("LegacyExposeResponse is null"));
                } else {
                    e.onSuccess(exposeResponse.expose);
                }
            } else {
                ResponseBody errorBody = response.errorBody();
                e.onError(new IOException(errorBody == null ? null : errorBody.string()));
            }
        });
    }

    public Single<LegacyExpose> getLegacyExpose(String exposeId, String authBearer) {
        return Single.create(e -> {
            Response<LegacyExposeResponse> response = legacyRestApi.getExpose(exposeId, authBearer).execute();
            if (response.isSuccessful()) {
                LegacyExposeResponse exposeResponse = response.body();
                if (exposeResponse == null) {
                    e.onError(new NullPointerException("LegacyExposeResponse is null"));
                } else {
                    e.onSuccess(exposeResponse.expose);
                }
            } else {
                ResponseBody errorBody = response.errorBody();
                e.onError(new IOException(errorBody == null ? null : errorBody.string()));
            }
        });
    }

    class AuthInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request
                    .newBuilder()
                    .addHeader("Authorization", "Bearer " + authApi.getAccessToken().blockingGet())
                    .build();

            return chain.proceed(request);
        }
    }
}
