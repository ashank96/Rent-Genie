package rentgenie.com.rentgenie.network;


import dagger.Module;
import dagger.Provides;

import rentgenie.com.rentgenie.repository.PlacesRepository;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class PlacesModule {
    public final String URL = "https://maps.googleapis.com";


    @Provides
    public Retrofit provideRetrofit(String baseURL){
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public PlacesApiService providesApiService(){
        return provideRetrofit(URL).create(PlacesApiService.class);
    }


    @Provides
    public PlacesRepository placesRepository(PlacesApiService placesApiService){
        return new PlacesRepository(placesApiService);
    }

}
