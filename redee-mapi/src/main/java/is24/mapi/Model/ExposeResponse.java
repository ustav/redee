package is24.mapi.Model;

import java.util.List;

public class ExposeResponse {
  public final Header header;
  public final List<Section> sections;

  public ExposeResponse(Header header, List<Section> sections) {
    this.header = header;
    this.sections = sections;
  }
}
