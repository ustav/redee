package is24.mapi;

import java.util.Map;

import is24.mapi.Model.ExposeResponse;
import is24.mapi.Model.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RestApi {

  String ENDPOINT = "https://api.mobile.immobilienscout24.de/";

  @GET("home/search")
  Call<SearchResponse> search(@QueryMap Map<String, String> parameters);

  @GET("expose/{exposeId}")
  Call<ExposeResponse> getExpose(@Path("exposeId") String exposeId);

}