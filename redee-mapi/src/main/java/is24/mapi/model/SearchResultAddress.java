package is24.mapi.model;

public class SearchResultAddress {
    public final String line;
    public final Double lat;
    public final Double lon;

    public SearchResultAddress(String line, Double lat, Double lon) {
        this.line = line;
        this.lat = lat;
        this.lon = lon;
    }
}
