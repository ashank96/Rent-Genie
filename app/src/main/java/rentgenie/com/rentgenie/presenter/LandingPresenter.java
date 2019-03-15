package rentgenie.com.rentgenie.presenter;
import android.location.Location;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;
import io.reactivex.Observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import rentgenie.com.rentgenie.Utils;
import rentgenie.com.rentgenie.contract.LandingContract;
import rentgenie.com.rentgenie.model.Lodging;
import rentgenie.com.rentgenie.model.Result;
import rentgenie.com.rentgenie.repository.PlacesRepository;


public class LandingPresenter extends MvpBasePresenter<LandingContract.View> implements LandingContract.Presenter {

    PlacesRepository placesRepository;
    Location location;
    String address;


    public LandingPresenter(){ }

    public LandingPresenter(PlacesRepository placesRepository) {
        this.placesRepository=placesRepository;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onYourLocationButtonClicked() {
        if (Utils.isLocationSet) {
            getView().showSuccessMessage("" + location);
            placesRepository.setLocation(location);
            placesRepository.getPlaces().flatMap(new Function<Lodging, ObservableSource<List<Result>>>() {
                @Override
                public ObservableSource<List<Result>> apply(Lodging lodging) throws Exception {
                    return Observable.just(lodging.getResults());
                }
            }).flatMap(new Function<List<Result>, ObservableSource<Result>>() {
                @Override
                public ObservableSource<Result> apply(List<Result> results) throws Exception {
                    return Observable.fromIterable(results);
                }
            }).
                    subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<Result>() {
                @Override
                public void accept(Result result) throws Exception {
                    Log.i(getClass().getSimpleName(),result.getName());
                }
            });

        }
    }

    @Override
    public void onCustomLocationButtonClicked() {
       address = getView().getTextViewAddress();
    }

    @Override
    public void setLocation(Location location) {
        this.location=location;
        Utils.isLocationSet=true;
    }
}
