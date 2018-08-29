package is24.mapi.model;

public class Expose {
  private String id;
  private String title;
  private String description;
  private String equipment;
  private String location;
  private String other;

  public Expose(String id, String title, String description, String equipment, String location, String other) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.equipment = equipment;
    this.location = location;
    this.other = other;
  }

  public Expose() {
    id = "EMPTY";
  }

  public Expose(ExposeResponse exposeResponse) {
    String title = null;
    String description = null;
    String equipment = null;
    String location = null;
    String other = null;

    for (Section section : exposeResponse.sections) {
      switch (section.type) {
        case "MEDIA":
          title = section.title;
          break;
        case "TEXT_AREA":
          switch (section.title) {
            case "Ausstattung":
              equipment = section.text;
              break;
            case "Lage":
              location = section.text;
              break;
            case "Sonstiges":
              other = section.text;
              break;
            case "Objektbeschreibung":
              description = section.text;
              break;
          }
          break;
      }
    }

    this.id = exposeResponse.header.id;
    this.title = title;
    this.description = description;
    this.equipment = equipment;
    this.location = location;
    this.other = other;
  }

  @Override
  public String toString() {
    return "Expose{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", equipment='" + equipment + '\'' +
        ", location='" + location + '\'' +
        ", other='" + other + '\'' +
        '}';
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEquipment() {
    return equipment;
  }

  public void setEquipment(String equipment) {
    this.equipment = equipment;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getOther() {
    return other;
  }

  public void setOther(String other) {
    this.other = other;
  }
}
