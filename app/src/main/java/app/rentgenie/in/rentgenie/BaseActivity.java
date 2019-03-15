package app.rentgenie.in.rentgenie;

import android.location.LocationManager;

import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {
	static LocationManager locationManager;

	protected void setScreenTitle(String title) {
	}

	protected void hideToolbar() {
	}


	public LocationManager getLocationManager() {
		if (locationManager == null) {
			locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		}

		return locationManager;
	}

}
