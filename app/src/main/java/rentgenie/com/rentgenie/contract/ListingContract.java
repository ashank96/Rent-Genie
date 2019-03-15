package rentgenie.com.rentgenie.contract;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import java.util.List;

import rentgenie.com.rentgenie.model.Result;

public interface ListingContract {

    interface View extends MvpLceView<List<Result>> {

    }

    interface Presenter {

    }


}
