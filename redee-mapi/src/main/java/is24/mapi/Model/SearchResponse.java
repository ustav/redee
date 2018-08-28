package is24.mapi.Model;

import java.util.List;

public class SearchResponse {

    public final List<SearchItem> results;
    public final int totalResults;
    public final int pageSize;
    public final int pageNumber;
    public final int numberOfPages;
    public final int totalNewResults;

    public SearchResponse(List<SearchItem> results, int totalResults, int pageSize, int pageNumber, int numberOfPages, int totalNewResults) {
        this.results = results;
        this.totalResults = totalResults;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.numberOfPages = numberOfPages;
        this.totalNewResults = totalNewResults;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "totalResults=" + totalResults +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", numberOfPages=" + numberOfPages +
                ", totalNewResults=" + totalNewResults +
                ", results=" + results +
                '}';
    }
}
