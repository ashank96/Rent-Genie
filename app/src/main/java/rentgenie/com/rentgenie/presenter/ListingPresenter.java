package rentgenie.com.rentgenie.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import rentgenie.com.rentgenie.ListingView;
import rentgenie.com.rentgenie.contract.ListingContract;

public class ListingPresenter extends MvpBasePresenter<ListingView> implements ListingContract.Presenter, MvpPresenter<ListingView> {
    @Override
    public void destroy() {
        super.destroy();
    }
}

