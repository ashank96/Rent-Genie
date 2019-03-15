package app.rentgenie.in.rentgenie.controllers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hannesdorfmann.mosby3.mvp.conductor.MvpController;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import app.rentgenie.in.rentgenie.BaseActivity;
import app.rentgenie.in.rentgenie.GeocodingLocation;
import app.rentgenie.in.rentgenie.R;
import app.rentgenie.in.rentgenie.RentGenieApplication;
import app.rentgenie.in.rentgenie.ViewUtils;
import app.rentgenie.in.rentgenie.adapters.HomeAdapter;
import app.rentgenie.in.rentgenie.contracts.HomeContract;
import app.rentgenie.in.rentgenie.databinding.ConductorHomeBinding;
import app.rentgenie.in.rentgenie.models.LodgingResponse;
import app.rentgenie.in.rentgenie.presenters.HomePresenter;


public class HomeController extends MvpController<HomeContract.View,HomePresenter>
		implements HomeContract.View,LocationListener {

	ConductorHomeBinding conductorHomeBinding;
	HomeAdapter homeAdapter;
	Location currentLocation;
	boolean isCurrentLocation;

	@NonNull
	@Override
	protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
		conductorHomeBinding = DataBindingUtil.inflate(inflater, R.layout.conductor_home, container, false);
		init();
		return conductorHomeBinding.getRoot();
	}

	@SuppressLint("MissingPermission")
	@Override
	protected void onAttach(@NonNull View view) {
		super.onAttach(view);
		if(permissionsGranted()) {
			currentLocation = ((BaseActivity) getActivity()).getLocationManager()
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if(currentLocation!=null) {
				getPresenter().loadLodgingData(getLocationString(currentLocation), getRadius());
			}
		}
		requestLocationService();
		setClickListeners();
	}

	private void setClickListeners() {
		conductorHomeBinding.searchAddressButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!TextUtils.isEmpty(conductorHomeBinding.addressEditText.getText())){
					GeocodingLocation locationAddress = new GeocodingLocation();
					ViewUtils.makeToastLong(getActivity(),"Searching based on your address");
					locationAddress.getAddressFromLocation(conductorHomeBinding.addressEditText.getText().toString(),
							getApplicationContext(), new GeocoderHandler());
				}
			}
		});

		conductorHomeBinding.radiusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(TextUtils.isEmpty(conductorHomeBinding.addressEditText.getText())) {
					if(currentLocation!=null) {
						getPresenter().loadLodgingData(getLocationString(currentLocation), getRadius());
					}
				}else{
					GeocodingLocation locationAddress = new GeocodingLocation();
					locationAddress.getAddressFromLocation(conductorHomeBinding.addressEditText.getText().toString(),
							getApplicationContext(), new GeocoderHandler());
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		conductorHomeBinding.searchUserLocationButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(currentLocation!=null) {
					conductorHomeBinding.addressEditText.getText().clear();
					ViewUtils.makeToastLong(getActivity(), "Searching based on your current location");
					getPresenter().loadLodgingData(getLocationString(currentLocation), getRadius());
				}
			}
		});
	}

	private int getRadius() {
		return Integer.parseInt(conductorHomeBinding.radiusSpinner.getSelectedItem().toString().split(" ")[0])*1000;
	}

	private String getLocationString(Location currentLocation) {
		return String.valueOf(currentLocation.getLatitude())+","+String.valueOf(currentLocation.getLongitude());
	}

	private void init() {
		homeAdapter = new HomeAdapter();
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
		conductorHomeBinding.recyclerView.setLayoutManager(linearLayoutManager);
		conductorHomeBinding.recyclerView.setAdapter(homeAdapter);
		conductorHomeBinding.recyclerView.setNestedScrollingEnabled(false);
	}


	private class GeocoderHandler extends Handler {
		@Override
		public void handleMessage(Message message) {
			String latLng;
			switch (message.what) {
				case 1:
					Bundle bundle = message.getData();
					latLng = bundle.getString("address");
					break;
				default:
					latLng = null;
			}
			if(latLng!=null){
				getPresenter().loadLodgingData(latLng,getRadius());
			}else{
				ViewUtils.makeToastShort(getActivity(),"Invalid Address");
			}

		}
	}

	private void requestLocationService() {
		if (!permissionsGranted()) {
			requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
					Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
		} else {
			requestForLocationUpdates();
		}
	}

	@SuppressLint("MissingPermission")
	private void requestForLocationUpdates() {
		((BaseActivity) getActivity()).getLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, this);
		((BaseActivity) getActivity()).getLocationManager().requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, this);
	}

	@SuppressLint("MissingPermission")
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		switch (requestCode) {
			case 1:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					ViewUtils.makeToastShort(getActivity(), "Permission Granted");
					requestForLocationUpdates();
				} else {
					ViewUtils.makeToastShort(getActivity(), "Permission denied\nPlease Grant Permissions to run the App");
					getActivity().finish();

				}
		}
	}


	@Override
	public void onLocationChanged(Location location) {
		if(!isCurrentLocation) {
			currentLocation = location;
			isCurrentLocation=true;
		}

		Log.i("Location", location.getLatitude() + " " + location.getLongitude());
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.i("Location", provider + " status changed");
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.i("Location", provider + " enabled");
	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.i("Location", provider + " disabled");
	}

	private boolean permissionsGranted() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
					getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				return false;
			}
		}
		return true;
	}



	@NonNull
	@Override
	public HomePresenter createPresenter() {
		return RentGenieApplication.getInstance().getComponent().getPresenter();
	}


	@Override
	public void updateLodgingResponse(LodgingResponse lodgingResponse) {
		hideLoadingView();
		showContentView();
		homeAdapter.updateList(lodgingResponse.getResults());
	}

	@Override
	public void showLoadingView() {
		conductorHomeBinding.loadingView.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoadingView() {

		conductorHomeBinding.loadingView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void showContentView() {
		conductorHomeBinding.recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideContentView() {
		conductorHomeBinding.recyclerView.setVisibility(View.INVISIBLE);
	}

	@Override
	protected void onDetach(@NonNull View view) {
		super.onDetach(view);
		isCurrentLocation=false;
	}
}
