package is24.mapi;

import is24.mapi.model.AuthTokenResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthRestApi {

    String ENDPOINT = "https://publicauth.immobilienscout24.de/";

    @POST("oauth/token")
    @FormUrlEncoded
    Call<AuthTokenResponse> getAuthToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret
    );

}
