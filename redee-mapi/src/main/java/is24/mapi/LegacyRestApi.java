package is24.mapi;

import is24.mapi.Model.LegacyExposeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface LegacyRestApi {

  String ENDPOINT = "https://rest.immobilienscout24.de/restapi/api/search/v1.0/";

  @GET("expose/{exposeId}")
  @Headers("Accept: application/json")
  Call<LegacyExposeResponse> getExpose(@Path("exposeId") String exposeId);

}
