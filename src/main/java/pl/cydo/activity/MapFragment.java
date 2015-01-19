package pl.cydo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import pl.cydo.model.ServicePoint;
import pl.jcygan.android.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MapFragment extends Fragment {
    private View v;
    private GoogleMap map;
    private PointsCollectorContainer pointsCollectorContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (v == null) {
            try {
                v = inflater.inflate(R.layout.main, container, false);
                initMap();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
//            pointsCollectorContainer.getPointsCollector().execute(new Object());
            List<ServicePoint> points =  pointsCollectorContainer.getPointsCollector().execute(new Object()).get();

            System.out.println(points);
            for(ServicePoint point: points){
                LatLng latLng = new LatLng(point.getLatitude().doubleValue()/1000000, point.getLongitude().doubleValue()/1000000);
                map.addMarker(new MarkerOptions()
                        .title(point.getName())
                        .position(latLng));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.pointsCollectorContainer = (PointsCollectorContainer) activity;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (v != null) {
            ViewGroup parentViewGroup = (ViewGroup) v.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
        }
    }


    private void initMap() {
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map));

        map = mapFragment.getMap();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-18.142, 178.431), 2));
        map.setMyLocationEnabled(true);

        // Polylines are useful for marking paths and routes on the map.
        map.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(-33.866, 151.195))  // Sydney
                .add(new LatLng(-18.142, 178.431))  // Fiji
                .add(new LatLng(21.291, -157.821))  // Hawaii
                .add(new LatLng(37.423, -122.091))  // Mountain View
        );
    }
}
