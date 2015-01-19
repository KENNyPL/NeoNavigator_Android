package pl.cydo.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import pl.cydo.model.CategoriesTreeModel;
import pl.cydo.model.ServicePointCategory;
import pl.cydo.util.RestRepository;

import java.io.IOException;

public class CategoryRepository {
    private static CategoryRepository INSTANCE;
    ServicePointCategory categories;

    private CategoryRepository() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
        categories = gson.fromJson(RestRepository.callWebService("http://192.168.0.4:8080/NeoNavigator/rest/categories/all"), ServicePointCategory.class);

        System.out.println(categories);
    }



    public static CategoryRepository getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (CategoryRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CategoryRepository();
                }
            }
        }
        return INSTANCE;
    }

    public ServicePointCategory getCategories() {
        return categories;
    }
}
