package rentgenie.com.rentgenie.root;

import android.app.Application;

import dagger.Component;
import rentgenie.com.rentgenie.network.PlacesModule;

public class App extends Application {
    private ApplicationComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        component=DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .placesModule(new PlacesModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }

}
