package pl.cydo.model;

import java.util.List;

public class ServicePoint {
    private Long longitude;
    private Long latitude;
    private String name;
    private List<CategoriesTreeModel> categories;

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoriesTreeModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesTreeModel> categories) {
        this.categories = categories;
    }
}
