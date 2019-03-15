package rentgenie.com.rentgenie.repository;

import android.location.Location;
import android.util.Log;

import rentgenie.com.rentgenie.model.Lodging;
import rentgenie.com.rentgenie.network.PlacesApiService;

public class PlacesRepository {
    public final String KEY = "AIzaSyB5PWz3wHic1iXAYNbWq2iaE_gGySjeybo";
    PlacesApiService placesApiService;
    Location location;

    public PlacesRepository() {
    }

    public PlacesRepository(PlacesApiService placesApiService) {
        this.placesApiService = placesApiService;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public io.reactivex.Observable<Lodging> getPlaces() {
        String loc=location.getLatitude()+","+location.getLongitude();
        Log.i("location",loc);

        return placesApiService.fetchProperties(loc, "1500", "lodging",KEY);
    }
}
