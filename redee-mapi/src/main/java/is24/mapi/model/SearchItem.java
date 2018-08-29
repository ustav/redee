package is24.mapi.model;

import java.util.List;

public class SearchItem {
  public final String id;
  public final String infoLine;
  public final List<String> attributes;
  public final String pictureUrl;
  public final boolean radius;
  public final boolean isNew;

  public SearchItem(String id, String infoLine, List<String> attributes, String pictureUrl, boolean radius, boolean isNew) {
    this.id = id;
    this.infoLine = infoLine;
    this.attributes = attributes;
    this.pictureUrl = pictureUrl;
    this.radius = radius;
    this.isNew = isNew;
  }

  @Override
  public String toString() {
    return "SearchItem{" +
            "id='" + id + '\'' +
            ", infoLine='" + infoLine + '\'' +
            ", attributes=" + attributes +
            ", pictureUrl='" + pictureUrl + '\'' +
            '}';
  }
}
