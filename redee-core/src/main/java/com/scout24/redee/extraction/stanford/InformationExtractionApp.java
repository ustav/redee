package com.scout24.redee.extraction.stanford;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scout24.redee.exception.ResourceException;
import com.scout24.redee.extraction.DateExtraction;
import com.scout24.redee.extraction.model.Expose;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * Created by dprawdzik on 10.01.18.
 */
public class InformationExtractionApp {

    public static void main(String[] args) throws IOException, ResourceException, ParseException {
        extract();
    }

    private static void extract() throws IOException, ResourceException, ParseException {

        int amountExpose = 0;
        int amountCorrectExtractedVisitAppointments = 0;
        Gson gson = new Gson();
        StanfordInformationExtractor extractor = new StanfordInformationExtractor();
        String fileName = "expose_apartment-rent_20-pages.json";
        fileName = "exposes_big.json";
        //fileName = "exposes.json";
        String json = FileUtils.readFileToString(new File("/Users/pstanic/Projects/redee/redee-core/src/main/resources/scout24/" + fileName), "UTF-8");
        Type listType = new TypeToken<List<Expose>>() {
        }.getType();
        List<Expose> exposes = gson.fromJson(json, listType);
        for (Expose expose : exposes) {
            amountExpose++;
            String[] contents = {expose.title, expose.descriptionNote, expose.locationNote};

            // over all exposés
            for (String content : contents) {
                if (content == null)
                    continue;
                //System.out.println((content.length() > 100) ? content.substring(0, 100) + "..." : content);
                Collection<DateExtraction> extractions = extractor.extract(content);
                if ((content.contains("Besicht") || content.contains("BES")) && extractions.size() == 0) {
                    System.out.println("Something went wrong!");
                    System.out.println(content);
                } else {
                    if (extractions.size() != 0)
                        amountCorrectExtractedVisitAppointments++;

                    for (DateExtraction extraction : extractions) {
                        System.out.println("ID: " + expose.id);
                        System.out.println("Extraction text: " + extraction.getText());
                        System.out.println("Determined start date: " + extraction.getStart());
                    }
                }
            }
        }
        System.out.println("# exposés\t" + amountExpose);
        System.out.println("# correct extracted visit appointments\t" + amountCorrectExtractedVisitAppointments);
    }
}
