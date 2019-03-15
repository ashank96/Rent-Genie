package app.rentgenie.in.rentgenie.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class,NetworkModule.class})
public interface Graph extends AppComponent, ModuleComponent{

	final class Initializer {
		public static Graph initialize(Application application) {
			return DaggerGraph.builder()
					.mainModule(new MainModule(application))
					.build();
		}
	}
}
