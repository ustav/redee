package is24.mapi;

import io.reactivex.Single;
import java.io.IOException;

import is24.mapi.model.AccessToken;
import is24.mapi.model.AuthTokenResponse;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthApi {

  private static final String GRANT_TYPE = "client_credentials";
  private static final String CLIENT_ID = System.getenv("REDEE_CLIENT_ID");
  private static final String CLIENT_SECRET = System.getenv("REDEE_CLIENT_SECRET");

  private AuthRestApi restApi;

  private AccessToken accessToken;

  public AuthApi() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(AuthRestApi.ENDPOINT)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    restApi = retrofit.create(AuthRestApi.class);
  }

  public Single<String> getAccessToken() {
    if (accessToken == null || accessToken.expirationTime > System.currentTimeMillis()) {
      return refreshAccessToken();
    } else {
      return Single.just(accessToken.token);
    }
  }

  public Single<String> refreshAccessToken() {
    return requestAccessToken()
        .doOnSuccess(newAccessToken -> accessToken = newAccessToken)
        .map(newAccessToken -> newAccessToken.token);
  }

  Single<AccessToken> requestAccessToken() {
    return Single.create(e -> {
          Response<AuthTokenResponse> response = restApi.getAuthToken(GRANT_TYPE, CLIENT_ID, CLIENT_SECRET).execute();
          if (response.isSuccessful()) {
            AuthTokenResponse authTokenResponse = response.body();
            if (authTokenResponse == null) {
              e.onError(new NullPointerException("AuthTokenResponse is null"));
            } else {
              e.onSuccess(
                  new AccessToken(
                      authTokenResponse.accessToken,
                      System.currentTimeMillis() + authTokenResponse.expiresIn * 1000
                  )
              );
            }
          } else {
            ResponseBody errorBody = response.errorBody();
            e.onError(new IOException(errorBody == null ? null : errorBody.string()));
          }
        });
  }

}
