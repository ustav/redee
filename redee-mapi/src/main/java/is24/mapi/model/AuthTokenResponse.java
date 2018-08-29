package is24.mapi.model;

import com.google.gson.annotations.SerializedName;

public class AuthTokenResponse {

  @SerializedName("access_token")
  public final String accessToken;
  @SerializedName("expires_in")
  public final long expiresIn;

  public AuthTokenResponse(String accessToken, long expiresIn) {
    this.accessToken = accessToken;
    this.expiresIn = expiresIn;
  }
}
