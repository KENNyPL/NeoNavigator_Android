package pl.cydo.util;

import android.os.AsyncTask;
import pl.cydo.model.ServicePoint;
import pl.cydo.repository.PointsRepository;

import java.util.List;

public class PointsCollector extends AsyncTask<Object, Boolean, List<ServicePoint>> {

    private Long latitude;
    private Long longitude;
    private Long distance;
    private String categoryName;
    private String ip;

    public PointsCollector(String ip, Long latitude, Long longitude, Long distance, String categoryName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.categoryName = categoryName;
        this.ip = ip;
    }

    @Override
    protected List<ServicePoint> doInBackground(Object... objects) {
        return PointsRepository.getINSTANCE().getPoints(ip, latitude, longitude, distance, categoryName);
    }

    public Long getLatitude() {
        return latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public Long getDistance() {
        return distance;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getIp() {
        return ip;
    }
}
