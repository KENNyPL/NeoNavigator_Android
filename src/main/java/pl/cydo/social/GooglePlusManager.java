package pl.cydo.social;

import android.app.Activity;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import pl.jcygan.android.R;

import java.io.InputStream;
import java.net.URL;

public class GooglePlusManager implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<People.LoadPeopleResult> {
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap map;
    private LocationManager locationManager = null;
    private Activity mainActivity;

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    /* A flag indicating that a PendingIntent is in progress and prevents
     * us from starting further intents.
     */
    private boolean mIntentInProgress;

    /* Track whether the sign-in button has been clicked so that we know to resolve
 * all issues preventing sign-in without waiting.
 */
    private boolean mSignInClicked;

    /* Store the connection result from onConnectionFailed callbacks so that we can
 * resolve them when the user clicks sign-in.
 */
    private ConnectionResult mConnectionResult;

    public GooglePlusManager(Activity activity) throws IntentSender.SendIntentException {
        mainActivity = activity;
        mGoogleApiClient = new GoogleApiClient.Builder(mainActivity.getBaseContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    /* A helper method to resolve the current ConnectionResult error. */
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mainActivity.startIntentSenderForResult(mConnectionResult.getResolution().getIntentSender(),
                        RC_SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                // The intent was canceled before it was sent.  Return to the default
                // state and attempt to connect to get an updated ConnectionResult.
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        System.out.println("onConnectionSuspended: " + i);
        mGoogleApiClient.connect();
        System.out.println("onConnectionSuspended: " + mGoogleApiClient.isConnected());
    }


    //    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button
                && !mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            resolveSignInError();
        }

        if (view.getId() == R.id.sign_out_button) {
            if (mGoogleApiClient.isConnected()) {
                Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);

                Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
                        .setResultCallback(new ResultCallback<Status>() {

                            @Override
                            public void onResult(Status status) {
                                System.out.println(status);
                            }
                        });

                mGoogleApiClient.disconnect();
                mGoogleApiClient.connect();
                System.out.println("DISCONECTED");
                Toast.makeText(view.getContext(), "Disconnected", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onResult(People.LoadPeopleResult peopleData) {
        System.err.println("--> " + peopleData);
//        if (peopleData.getStatus().getStatusCode() == CommonStatusCodes.SUCCESS) {
//            PersonBuffer personBuffer = peopleData.getPersonBuffer();
//            try {
//                int count = personBuffer.getCount();
//                for (int i = 0; i < count; i++) {
//                    Toast.makeText(mainActivity, "Display name: " + personBuffer.get(i).getDisplayName(), Toast.LENGTH_LONG).show();
//                }
//            } finally {
//                personBuffer.close();
//            }
//        } else {
//            Toast.makeText(mainActivity, "Error requesting visible circles: " + peopleData.getStatus(), Toast.LENGTH_LONG).show();
//        }
    }

    public void onConnectionFailed(ConnectionResult result) {
        if (!mIntentInProgress && result.hasResolution()) {
            try {
                mIntentInProgress = true;
                mainActivity.startIntentSenderForResult(result.getResolution().getIntentSender(),
                        RC_SIGN_IN, null, 0, 0, 0);

                mainActivity.findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            } catch (Exception e) {
                // The intent was canceled before it was sent.  Return to the default
                // state and attempt to connect to get an updated ConnectionResult.
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        System.out.println("onConnected");
        mainActivity.findViewById(R.id.sign_in_button).setVisibility(View.INVISIBLE);
        mSignInClicked = false;
//        Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
        Toast.makeText(mainActivity, "User is connected!", Toast.LENGTH_LONG).show();

        String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
        System.err.println("email: " + email);


        Plus.PeopleApi.loadVisible(mGoogleApiClient, null).setResultCallback(this);


//        getAndDisplaySimpleInfo();
    }

    public Bitmap loadImageFromURL(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Bitmap myBitmap = BitmapFactory.decodeStream(is);
            return myBitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public void getAndDisplaySimpleInfo() {
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            String personName = currentPerson.getDisplayName();
            Person.Image personPhoto = currentPerson.getImage();
            String personGooglePlusProfile = currentPerson.getUrl();

            System.err.println("Person: " + personName + " " + personGooglePlusProfile);

            Toast toast = new Toast(mainActivity);
            ImageView view = new ImageView(mainActivity);

            view.setImageBitmap(loadImageFromURL(personPhoto.getUrl()));
            toast.setView(view);
            toast.show();


        } else {
            System.err.println("Person: FAIL");
        }


        System.err.println("==> ");
        Plus.PeopleApi.loadVisible(mGoogleApiClient, null)
                .setResultCallback(this);
        System.err.println("++> ");
    }
}
