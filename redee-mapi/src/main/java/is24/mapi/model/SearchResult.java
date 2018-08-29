package is24.mapi.model;

import java.util.List;

public class SearchResult {
    public final String id;
    public final String reportUrl;
    public final boolean isProject;
    public final boolean isPrivate;
    public final String listingType;
    public final String published;
    public final boolean isNewObject;
    public final List<SearchResultPicture> pictures;
    public final SearchResultTitlePicture titlePicture;
    public final SearchResultAddress address;
    public final List<SearchResultAttribute> attributes;
    public final String projectObjectsSectionHeading;
    public final List<SearchResult> projectObjects;
    public final String groupingObjectsSectionHeading;
    public final List<SearchResult> groupingObjects;


    public SearchResult(String id, String reportUrl, boolean isProject, boolean isPrivate, String listingType, String published, boolean isNewObject, List<SearchResultPicture> pictures, SearchResultTitlePicture titlePicture, SearchResultAddress address, List<SearchResultAttribute> attributes, String projectObjectsSectionHeading, List<SearchResult> projectObjects, String groupingObjectsSectionHeading, List<SearchResult> groupingObjects) {
        this.id = id;
        this.reportUrl = reportUrl;
        this.isProject = isProject;
        this.isPrivate = isPrivate;
        this.listingType = listingType;
        this.published = published;
        this.isNewObject = isNewObject;
        this.pictures = pictures;
        this.titlePicture = titlePicture;
        this.address = address;
        this.attributes = attributes;
        this.projectObjectsSectionHeading = projectObjectsSectionHeading;
        this.projectObjects = projectObjects;
        this.groupingObjectsSectionHeading = groupingObjectsSectionHeading;
        this.groupingObjects = groupingObjects;
    }
}
