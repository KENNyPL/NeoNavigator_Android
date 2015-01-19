package pl.cydo.model;


import java.util.LinkedList;
import java.util.List;

public class CategoriesTreeModel {
    private Long id;
    private String name;
    private List<CategoriesTreeModel> subCategories = new LinkedList<CategoriesTreeModel>();

    public CategoriesTreeModel() {
    }

    public CategoriesTreeModel(String text) {
        this.name = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean add(CategoriesTreeModel categoriesTreeModel) {
        return subCategories.add(categoriesTreeModel);
    }

    public List<CategoriesTreeModel> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<CategoriesTreeModel> subCategories) {
        this.subCategories = subCategories;
    }
}