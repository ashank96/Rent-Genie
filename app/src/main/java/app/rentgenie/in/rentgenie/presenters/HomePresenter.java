package app.rentgenie.in.rentgenie.presenters;

import android.util.Log;

import javax.inject.Inject;

import app.rentgenie.in.rentgenie.Constants;
import app.rentgenie.in.rentgenie.contracts.HomeContract;
import app.rentgenie.in.rentgenie.models.LodgingResponse;
import app.rentgenie.in.rentgenie.network.APIInterface;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

	APIInterface apiInterface;
	private HomeContract.View view;

	@Inject
	public HomePresenter(APIInterface apiInterface){
		this.apiInterface = apiInterface;
	}

	@Override
	public void loadLodgingData(String locationString,int radius) {
		view.hideContentView();
		view.showLoadingView();

		apiInterface.getLodgingResponse(locationString,radius,Constants.LODGING,Constants.API_KEY)
				.concatWith(apiInterface.getLodgingResponse(locationString,1500,Constants.REAL_ESTATE,Constants.API_KEY))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(getLodgingObserver());
	}




	private Observer<? super LodgingResponse> getLodgingObserver() {
		return new Observer<LodgingResponse>() {
			@Override
			public void onSubscribe(Disposable d) {

			}

			@Override
			public void onNext(LodgingResponse lodgingResponse) {
				view.updateLodgingResponse(lodgingResponse);
			}

			@Override
			public void onError(Throwable e) {
				Log.i(getClass().getSimpleName()+" Error","Retrofit Lodging");
			}

			@Override
			public void onComplete() {

			}
		};
	}

	@Override
	public void attachView(HomeContract.View view) {
		this.view = view;
	}

	@Override
	public void detachView(boolean retainInstance) {

	}

	@Override
	public void detachView() {

	}

	@Override
	public void destroy() {

	}
}
