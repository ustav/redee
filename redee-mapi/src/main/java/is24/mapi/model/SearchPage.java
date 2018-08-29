package is24.mapi.model;

import java.util.List;

public class SearchPage {
    public int totalResults;
    public int pageSize;
    public int pageNumber;
    public int numberOfPages;
    public int numberOfListings;
    public String searchId;
    public List<SearchResult> results;

    public SearchPage(int totalResults, int pageSize, int pageNumber, int numberOfPages, int numberOfListings, String searchId, List<SearchResult> results) {
        this.totalResults = totalResults;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.numberOfPages = numberOfPages;
        this.numberOfListings = numberOfListings;
        this.searchId = searchId;
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfListings() {
        return numberOfListings;
    }

    public void setNumberOfListings(int numberOfListings) {
        this.numberOfListings = numberOfListings;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }
}
