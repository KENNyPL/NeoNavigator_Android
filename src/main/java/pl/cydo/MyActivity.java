package pl.cydo;

import android.app.Activity;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import pl.cydo.social.GooglePlusManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import pl.jcygan.android.R;     //need generate new certificate...


public class MyActivity extends Activity {
    private GooglePlusManager googlePlusManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

initializeStrictPolicy();

        setContentView(R.layout.main);
        initSubsystems();
        initMap();

        findViewById(R.id.sign_in_button).setOnClickListener(googlePlusManager);
        findViewById(R.id.sign_out_button).setOnClickListener(googlePlusManager);
    }

    private void initializeStrictPolicy() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void initMap() {
        GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

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

    private void initSubsystems() {
        try {
            googlePlusManager = new GooglePlusManager(this);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    protected void onStart() {
        super.onStart();
        googlePlusManager.onConnectionSuspended(0);
//        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();

//        if (mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.disconnect();
//        }
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
