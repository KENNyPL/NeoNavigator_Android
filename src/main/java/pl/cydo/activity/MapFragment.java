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
import pl.cydo.model.ServicePoint;
import pl.jcygan.android.R;

import java.util.List;

public class MapFragment extends Fragment {
    private View rootView;
    private GoogleMap map;
    private PointsCollectorContainer pointsCollectorContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null) {
            try {
                rootView = inflater.inflate(R.layout.main, container, false);
                initMap();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            List<ServicePoint> points = pointsCollectorContainer.getPointsCollector().execute(new Object()).get();

            for (ServicePoint point : points) {
                LatLng latLng = new LatLng(point.getLatitude().doubleValue() / 1000000, point.getLongitude().doubleValue() / 1000000);
                map.addMarker(new MarkerOptions()
                        .title(point.getName())
                        .position(latLng));

            }
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(pointsCollectorContainer.getPointsCollector().getLatitude().doubleValue()/1000000,
                            pointsCollectorContainer.getPointsCollector().getLongitude().doubleValue()/1000000), 6));

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
        if (rootView != null) {
            ViewGroup parentViewGroup = (ViewGroup) rootView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
        }
    }


    private void initMap() {
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map));

        map = mapFragment.getMap();
        map.setMyLocationEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setRotateGesturesEnabled(false);
    }
}
