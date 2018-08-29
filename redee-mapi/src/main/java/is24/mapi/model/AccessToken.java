package is24.mapi.model;

public class AccessToken {
  public final String token;
  public final long expirationTime;

  public AccessToken(String token, long expirationTime) {
    this.token = token;
    this.expirationTime = expirationTime;
  }
}
