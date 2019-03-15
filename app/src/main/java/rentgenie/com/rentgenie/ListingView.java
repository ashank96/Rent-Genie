package rentgenie.com.rentgenie;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import java.util.List;

import rentgenie.com.rentgenie.model.Result;

public class ListingView implements MvpLceView<List<Result>> {
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
