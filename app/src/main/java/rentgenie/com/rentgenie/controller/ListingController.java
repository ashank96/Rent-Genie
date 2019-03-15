package rentgenie.com.rentgenie.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.conductor.viewstate.lce.MvpLceViewStateController;
import com.hannesdorfmann.mosby3.mvp.conductor.lce.MvpLceController;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;

import java.util.List;

import rentgenie.com.rentgenie.ListingView;
import rentgenie.com.rentgenie.R;
import rentgenie.com.rentgenie.contract.ListingContract;
import rentgenie.com.rentgenie.model.Result;
import rentgenie.com.rentgenie.presenter.ListingPresenter;

class ListingController extends MvpLceViewStateController<NestedScrollView,List<Result>,ListingView,ListingPresenter>
        implements ListingContract.View {

    public static ListingController newInstance() {
        return new ListingController();
    }
    public ListingController(Bundle bundle){
        super(bundle);
    }

    @Override
    public List<Result> getData() {
        return null;
    }


    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    public ListingController(){}
    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.home_controller,container,false);
       // getActivity().findViewById(R.id.masterLayout).setBackground();
        return view;
    }


    @Override
    public void setData(List<Result> data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @NonNull
    @Override
    public ListingPresenter createPresenter() {
        return null;
    }

    @NonNull
    @Override
    public LceViewState<List<Result>, ListingView> createViewState() {
        return null;
    }
}
