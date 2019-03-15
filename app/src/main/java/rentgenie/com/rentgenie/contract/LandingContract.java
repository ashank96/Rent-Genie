package rentgenie.com.rentgenie.contract;

import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import java.util.List;

import rentgenie.com.rentgenie.model.Location;
import rentgenie.com.rentgenie.model.Result;

public interface LandingContract {
    interface View extends MvpView {
        void showCheckBoxSelected();
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
        String getTextViewAddress();
    }
    interface Presenter extends MvpPresenter<View>{
        void onYourLocationButtonClicked();
        void onCustomLocationButtonClicked();
        void setLocation(android.location.Location location);
    }
}
