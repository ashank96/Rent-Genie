package app.rentgenie.in.rentgenie.contracts;

import android.location.Location;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import app.rentgenie.in.rentgenie.models.LodgingResponse;

public interface HomeContract {
	interface View extends MvpView{
		void updateLodgingResponse(LodgingResponse weatherResponse);
		void showLoadingView();
		void hideLoadingView();
		void showContentView();
		void hideContentView();
	}
	interface Presenter extends MvpPresenter<View>{
		void loadLodgingData(String locString,int radius);
	}
}
