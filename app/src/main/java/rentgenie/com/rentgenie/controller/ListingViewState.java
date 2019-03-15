package rentgenie.com.rentgenie.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState;

import java.util.List;

import rentgenie.com.rentgenie.contract.ListingContract;
import rentgenie.com.rentgenie.model.Result;

class ListingViewState implements RestorableViewState<ListingContract.View>, MvpLceView<List<Result>> {
    @Override
    public void saveInstanceState(@NonNull Bundle out) {

    }

    @Override
    public RestorableViewState<ListingContract.View> restoreInstanceState(Bundle in) {
        return null;
    }

    @Override
    public void apply(ListingContract.View view, boolean retained) {

    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {

    }

    @Override
    public void setData(List<Result> data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
