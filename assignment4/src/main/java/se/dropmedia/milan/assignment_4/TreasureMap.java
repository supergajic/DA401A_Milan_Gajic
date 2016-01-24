package se.dropmedia.milan.assignment_4;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by MAH on 2016-01-19.
 */
public class TreasureMap {

    public final int numberOfTreasures = 3;
    public static int treasureCounter = 0;
    public Location currentPlayerLocation;
    public Location currentTreasureLocation;
    public final int[] Correct_Answers = {2, 1, 1, 0};

    private LatLng latLng;
    private final double radius = 15;
    private final double[][] treasureLocations = {{55.834914, 13.301400}, {55.836430, 13.301528}, {55.834247, 13.299126}, {55.834033, 13.301475}};

    public TreasureMap()
    {

    }

    public void SetCurrentPlayerLocation(Location location)
    {
        currentPlayerLocation = new Location("");
        currentPlayerLocation.setLongitude(location.getLongitude());
        currentPlayerLocation.setLatitude(location.getLatitude());
    }

    public void SetCurrentTreasureLocation()
    {
        currentTreasureLocation = new Location("");
        currentTreasureLocation.setLongitude(treasureLocations[treasureCounter][1]);
        currentTreasureLocation.setLatitude(treasureLocations[treasureCounter][0]);
    }


    public LatLng GetCurrentTreasureLocation() {
        latLng = new LatLng(currentTreasureLocation.getLatitude(),currentTreasureLocation.getLongitude());
        return latLng;

    }

    public void TreasureCounter()
    {
        treasureCounter++;
    }

    public int GetTreasureCounter()
    {
        return treasureCounter;
    }

    public boolean isTreasureFound()
    {
        if(currentPlayerLocation.distanceTo(currentTreasureLocation) <= radius)
        {
            return true;
        }
        return false;
    }
}
