package pl.cydo.service;

import pl.cydo.model.CategoriesTreeModel;
import retrofit.http.GET;

import java.util.List;

public interface CategoriesService {

    @GET("/")
    List<CategoriesTreeModel> listRepos();
}
