package se.dropmedia.milan.assignment_4;

import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, com.google.android.gms.location.LocationListener, TreasureDialog.OnOptionSelected {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private TreasureMap mTreasureMap;
    private Marker mMarker;
    public Vibrator vibrator;
    public LocationRequest mLocationRequest;
    public Bundle args;
    public boolean rightAnswer = true;

    LatLng eslov = new LatLng(55.834939, 13.302004);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        args = new Bundle();
        mTreasureMap = new TreasureMap();
        mTreasureMap.SetCurrentTreasureLocation();
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        SetUpMapIfNeeded();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();
        mGoogleApiClient.connect();
    }

    private void SetUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                SetUpMap();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SetUpMapIfNeeded();
    }

    private void SetUpMap() {
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings mUISetting = mMap.getUiSettings();
        mUISetting.setZoomControlsEnabled(true);
        mUISetting.setMyLocationButtonEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(eslov));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        mMarker = mMap.addMarker(new MarkerOptions().position(mTreasureMap.GetCurrentTreasureLocation()).icon(BitmapDescriptorFactory.fromResource(R.drawable.red)));
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("TAG", "Connected");
        mLocationRequest = new LocationRequest();
        mLocationRequest.create();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mTreasureMap.SetCurrentPlayerLocation(location);
        if (mTreasureMap.isTreasureFound() && rightAnswer) {
            NextQuestion();
            rightAnswer = false;
        }
    }

    public void NextQuestion()
    {
        args.putInt("treasure", mTreasureMap.GetTreasureCounter());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        TreasureDialog tDialog = new TreasureDialog();
        tDialog.setArguments(args);
        tDialog.show(ft, "dialog");

        MediaPlayer start = MediaPlayer.create(this, R.raw.bikehorn);
        start.start();
        vibrator.vibrate(1000);
    }

    @Override
    public void onComplete(int option) {
        rightAnswer = true;
        Toast toast;
        //If answer is wrong --> GAME OVER
        if (option != mTreasureMap.Correct_Answers[mTreasureMap.GetTreasureCounter()]) {
            toast = Toast.makeText(getApplicationContext(), "Game Over Loser!", Toast.LENGTH_LONG);
            MediaPlayer nooo = MediaPlayer.create(this, R.raw.nooo);
            nooo.start();
            mMap.clear();
            rightAnswer = false;
        }
        //If answer is right --> CONTINUE
        else {
            toast = Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_LONG);
            //If it is last treasure_point ---> PLAYER WIN
            if (mTreasureMap.GetTreasureCounter() == mTreasureMap.numberOfTreasures) {
                toast = Toast.makeText(getApplicationContext(), "Winner, Winner Chicken Dinner!", Toast.LENGTH_LONG);
                MediaPlayer right = MediaPlayer.create(this, R.raw.winner);
                right.start();
                vibrator.vibrate(1000);
                mMap.clear();
                rightAnswer = false;
            }
            //If it is right answer but not last treasure point ---> spawn new treasure point
            else {
                mMarker.remove();
                mTreasureMap.TreasureCounter();
                mTreasureMap.SetCurrentTreasureLocation();
                mMarker = mMap.addMarker(new MarkerOptions().position(mTreasureMap.GetCurrentTreasureLocation()).icon(BitmapDescriptorFactory.fromResource(R.drawable.red)));
            }
        }
        toast.show();
    }
}
