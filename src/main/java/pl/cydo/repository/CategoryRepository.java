package pl.cydo.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.cydo.model.ServicePointCategory;
import pl.cydo.util.RestRepository;

public class CategoryRepository {
    private static CategoryRepository INSTANCE;
    ServicePointCategory categories;

    private CategoryRepository() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
        categories = gson.fromJson(RestRepository.callWebService("http://192.168.0.4:8080/NeoNavigator/rest/categories/all"), ServicePointCategory.class);
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
