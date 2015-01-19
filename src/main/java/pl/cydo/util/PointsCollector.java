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

    public PointsCollector(Long latitude, Long longitude, Long distance, String categoryName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.categoryName = categoryName;
    }

    @Override
    protected List<ServicePoint> doInBackground(Object... objects) {
        return PointsRepository.getINSTANCE().getPoints(latitude, longitude, distance, categoryName);
    }
}
