package is24.mapi;

import com.google.gson.Gson;
import io.reactivex.Flowable;
import io.reactivex.Single;
import is24.mapi.Model.LegacyExpose;
import is24.mapi.Model.SearchItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fetcher {
    public static void main(String[] args) {
        Map<String, String> searchParameters = new HashMap<>();
        searchParameters.put("searchType", "region");
        searchParameters.put("realestatetype", "apartmentrent");
        searchParameters.put("haspromotion", "false");
        searchParameters.put("numberofrooms", "3.00");
        searchParameters.put("price", "0.00-1000.00");
        searchParameters.put("livingspace", "80.00");
        searchParameters.put("sorting", "-firstactivation");
        searchParameters.put("pagesize", "100");
        searchParameters.put("equipment", "balcony,builtInKitchen");
        searchParameters.put("geocodes", "1276003001");
        searchParameters.put("publishedAfter", "2018-08-01T00:00:00");

        System.out.println("Starting");

        Api api = new Api();
        List<LegacyExpose> items = api.search(searchParameters)
                .flatMap(searchResponse -> {
                    System.out.println("Search response: " + searchResponse);
                    return loadExposes(searchResponse.results, api);
                })
                .blockingGet();

        System.out.println("Loaded: " + items);

        Gson gson = new Gson();
        for (LegacyExpose item : items) {
            String json = gson.toJson(item);
            System.out.println(json);
        }
    }

    private static Single<List<LegacyExpose>> loadExposes(List<SearchItem> items, Api api) {
        return Flowable
                .fromIterable(items)
                .flatMapSingle(searchItem -> api.getLegacyExpose(searchItem.id))
                .toList();
    }
}
