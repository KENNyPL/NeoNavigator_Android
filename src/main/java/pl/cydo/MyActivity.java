package pl.cydo;

import android.app.Activity;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import pl.cydo.dialog.CategoryChooseDialog;
import pl.cydo.social.GooglePlusManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import pl.jcygan.android.R;     //need generate new certificate...


public class MyActivity extends FragmentActivity {
//    private GooglePlusManager googlePlusManager;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//initializeStrictPolicy();
//
//        setContentView(R.layout.main);
//        findViewById(R.id.category_choose).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    DialogFragment newFragment = new CategoryChooseDialog();
//                    newFragment.show(getSupportFragmentManager(), "missiles");
//            }
//        });
//
//    }
//
//    private void initializeStrictPolicy() {
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//    }
//
//    private void initMap() {
//        GoogleMap map = ((MapFragment) getFragmentManager()
//                .findFragmentById(R.id.map)).getMap();
//
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                new LatLng(-18.142, 178.431), 2));
//        map.setMyLocationEnabled(true);
//
//        // Polylines are useful for marking paths and routes on the map.
//        map.addPolyline(new PolylineOptions().geodesic(true)
//                .add(new LatLng(-33.866, 151.195))  // Sydney
//                .add(new LatLng(-18.142, 178.431))  // Fiji
//                .add(new LatLng(21.291, -157.821))  // Hawaii
//                .add(new LatLng(37.423, -122.091))  // Mountain View
//        );
//    }
//
//    private void initSubsystems() {
//        try {
//            googlePlusManager = new GooglePlusManager(this);
//            CategoryRepository categoryRepository = CategoryRepository.getINSTANCE();
//        } catch (IntentSender.SendIntentException e) {
//            e.printStackTrace();
//        }
//    }
//
//    protected void onStart() {
//        super.onStart();
//
//        initSubsystems();
//        initMap();
//
////        googlePlusManager.onConnectionSuspended(0);
//    }
//
//    protected void onStop() {
//        super.onStop();
//
////        if (mGoogleApiClient.isConnected()) {
////            mGoogleApiClient.disconnect();
////        }
//    }
//
//
//    public static Bitmap getBitmapFromURL(String src) {
//        try {
//            URL url = new URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            return myBitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
