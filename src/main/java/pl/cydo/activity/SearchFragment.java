package pl.cydo.activity;

import android.app.Activity;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.common.SignInButton;
import pl.cydo.dialog.CategoryChooseDialog;
import pl.cydo.social.GooglePlusManager;
import pl.cydo.util.PointsCollector;
import pl.jcygan.android.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class SearchFragment extends Fragment {

    private GooglePlusManager googlePlusManager;
    private Button logOutButton;
    private Button categoryChooseButton;
    private SignInButton logIntButton;
    private View rootView;
    private PointsCollectorContainer pointsCollectorContainer;
    private EditText editText;
    private EditText longitudeText;
    private EditText latitudeEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.serch_fragment, container, false);

        categoryChooseButton = (Button) rootView.findViewById(R.id.category_choose);
        editText = (EditText) rootView.findViewById(R.id.editText);
        latitudeEdit = (EditText) rootView.findViewById(R.id.latitude);
        longitudeText = (EditText) rootView.findViewById(R.id.longitude);

        categoryChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new CategoryChooseDialog();
                newFragment.show(getChildFragmentManager(), "missiles");
            }
        });

        rootView.findViewById(R.id.show_points_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTabHost mTabHost = (FragmentTabHost) getActivity().findViewById(android.R.id.tabhost);
                mTabHost.setCurrentTab(1);
                pointsCollectorContainer.setPointsCollector(new PointsCollector(Long.parseLong(latitudeEdit.getText().toString()), Long.parseLong(longitudeText.getText().toString()),
                        Long.parseLong(editText.getText().toString()), categoryChooseButton.getText().toString()));
            }
        });

        initSubsystems();

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.pointsCollectorContainer = (PointsCollectorContainer) activity;
    }

    private void initSubsystems() {
        try {
            initGooglePlusSubsystem();
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    private void initGooglePlusSubsystem() throws IntentSender.SendIntentException {
        googlePlusManager = new GooglePlusManager(this.getActivity());
        logIntButton = (SignInButton) rootView.findViewById(R.id.sign_in_button);
        logIntButton.setOnClickListener(googlePlusManager);
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