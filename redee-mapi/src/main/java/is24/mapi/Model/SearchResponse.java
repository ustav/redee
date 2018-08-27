package is24.mapi.Model;

import java.util.List;

public class SearchResponse {

  public final List<SearchItem> results;

  public SearchResponse(List<SearchItem> results) {
    this.results = results;
  }
}
