package rentgenie.com.rentgenie.root;

import javax.inject.Singleton;

import dagger.Component;
import rentgenie.com.rentgenie.MainActivity;
import rentgenie.com.rentgenie.controller.LandingController;
import rentgenie.com.rentgenie.network.PlacesModule;

@Singleton
@Component(modules = {ApplicationModule.class,PlacesModule.class})
public interface ApplicationComponent {
   void inject (LandingController target);
}
