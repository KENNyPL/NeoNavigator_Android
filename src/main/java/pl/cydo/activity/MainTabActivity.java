package pl.cydo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import pl.cydo.util.ApplicationResourceCollector;
import pl.cydo.util.PointsCollector;
import pl.jcygan.android.R;

public class MainTabActivity extends FragmentActivity implements PointsCollectorContainer {
    private FragmentTabHost mTabHost;

    private PointsCollector pointsCollector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Step 1: Inflate layout
        setContentView(R.layout.main_table_view);
        // Step 2: Setup TabHost
        initialiseTabHost(savedInstanceState);
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); //set the tab as per the saved state
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new ApplicationResourceCollector().execute(new Object());
    }

    private void initialiseTabHost(Bundle savedInstanceState) {


        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("parameters").setIndicator("Parameters"),
                SearchFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("map").setIndicator("Map"),
                MapFragment.class, null);
    }

    @Override
    public void setPointsCollector(PointsCollector pointsCollector) {
        this.pointsCollector=pointsCollector;
    }

    @Override
    public PointsCollector getPointsCollector() {
        return pointsCollector;
    }
}
