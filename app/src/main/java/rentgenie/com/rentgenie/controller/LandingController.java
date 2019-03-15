package rentgenie.com.rentgenie.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController;

import javax.inject.Inject;

import rentgenie.com.rentgenie.Utils;
import rentgenie.com.rentgenie.contract.LandingContract;
import rentgenie.com.rentgenie.presenter.LandingPresenter;
import rentgenie.com.rentgenie.R;
import rentgenie.com.rentgenie.repository.PlacesRepository;
import rentgenie.com.rentgenie.root.App;

public class LandingController extends MvpController<LandingContract.View, LandingContract.Presenter>
        implements LandingContract.View,LocationListener {


    public static Controller newInstance() {
        return new LandingController();
    }

    @Inject
    public PlacesRepository placesRepository;
    CheckBox commercialCheckbox;
    CheckBox residentialCheckbox;
    EditText address;
    Button customLocationButton;
    Button userLocationButton;
    LocationManager locationManager;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view =
                inflater.inflate(R.layout.location_query_controller, container, false);
        ((App) getApplicationContext()).getComponent().inject(this);
        return view;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        commercialCheckbox = view.findViewById(R.id.commercial);
        residentialCheckbox = view.findViewById(R.id.residential);
        address = view.findViewById(R.id.address);
        customLocationButton = view.findViewById(R.id.customLocationButton);
        userLocationButton = view.findViewById(R.id.myLocationButton);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        requestPermissions();
        userLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions();
                presenter.onYourLocationButtonClicked();
                if(Utils.isLocationSet){
                   // getRouter().setRoot(RouterTransaction.with(ListingController.newInstance()));
                }

            }
        });
        customLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions();
                presenter.onCustomLocationButtonClicked();
            }
        });

    }

    private void requestPermissions(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }



    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }

        }else{
            showErrorMessage("Location Permissions Required!!");
        }
    }

    @Override
    public void showCheckBoxSelected() {

    }


    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getTextViewAddress() {
        return address.getText().toString();
    }

    @NonNull
    @Override
    public LandingContract.Presenter createPresenter() {
        return new LandingPresenter(placesRepository);
    }

    @Override
    public void onLocationChanged(Location location) {
        presenter.setLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
