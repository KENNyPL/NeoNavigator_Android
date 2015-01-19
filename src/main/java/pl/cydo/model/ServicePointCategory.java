package pl.cydo.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ServicePointCategory {
    private Long id;
    private String text;
    private List<ServicePointCategory> children= new LinkedList<ServicePointCategory>();

    public ServicePointCategory() {
    }

    public ServicePointCategory(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public boolean add(ServicePointCategory categoriesTreeModel) {
        return children.add(categoriesTreeModel);
    }

    public List<ServicePointCategory> getChildren() {
        return children;
    }

    public void setChildren(List<ServicePointCategory> children) {
        this.children = children;
    }
}
