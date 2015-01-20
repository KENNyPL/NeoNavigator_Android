package pl.cydo.repository;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.cydo.model.ServicePoint;
import pl.cydo.util.RestRepository;

import java.util.Arrays;
import java.util.List;

public class PointsRepository {
    private static PointsRepository INSTANCE = new PointsRepository();

    private PointsRepository() {
    }

    public static PointsRepository getINSTANCE() {
        return INSTANCE;
    }

    public List<ServicePoint> getPoints(String ip, Long latitude, Long longitude, Long distance, String categoryname) {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        StringBuilder url = new StringBuilder("http://"+ip+":8080/NeoNavigator/rest/points/all")
                .append("/" + longitude)
                .append("/" + latitude)
                .append("/" + distance)
                .append("/" + categoryname);

        List<ServicePoint> points = Arrays.asList(gson.fromJson(RestRepository.callWebService(url.toString()), ServicePoint[].class));

        return points;
    }
}
