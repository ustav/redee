package is24.mapi.Model;

public class AccessToken {
  public final String token;
  public final long expirationTime;

  public AccessToken(String token, long expirationTime) {
    this.token = token;
    this.expirationTime = expirationTime;
  }
}
