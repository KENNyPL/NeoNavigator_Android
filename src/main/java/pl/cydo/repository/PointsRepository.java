package pl.cydo.repository;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import pl.cydo.model.CategoriesTreeModel;
import pl.cydo.model.ServicePoint;
import pl.cydo.util.RestRepository;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class PointsRepository {
    private static PointsRepository INSTANCE = new PointsRepository();

    private PointsRepository() {

    }

    public static PointsRepository getINSTANCE() {
        return INSTANCE;
    }

    public List<ServicePoint> getPoints(Long latitude, Long longitude, Long distance, String categoryname){
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

            StringBuilder url = new StringBuilder("http://192.168.0.4:8080/NeoNavigator/rest/points/all")
                    .append("/"+longitude)
                    .append("/"+latitude)
                    .append("/"+distance)
                    .append("/"+categoryname);

        List<ServicePoint> points = Arrays.asList(gson.fromJson(RestRepository.callWebService(url.toString()), ServicePoint[].class));

        System.out.println(points);
        return points;
    }
}
