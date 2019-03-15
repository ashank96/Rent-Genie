package rentgenie.com.rentgenie.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class ApplicationModule {
    private Application application;

    ApplicationModule(Application application){
        this.application=application;
    }

    @Singleton
    @Provides
    public Context providesContext(){
        return application;
    }

    
}
