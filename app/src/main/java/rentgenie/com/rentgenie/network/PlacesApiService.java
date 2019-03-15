package rentgenie.com.rentgenie.network;

import java.util.List;

import io.reactivex.Observable;
import rentgenie.com.rentgenie.model.Lodging;
import rentgenie.com.rentgenie.model.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlacesApiService {
    @GET("/maps/api/place/nearbysearch/json")
    Observable<Lodging> fetchProperties(@Query("location")String latLng,
                                        @Query("radius")String radius,
                                        @Query("type")String type,
                                        @Query("key")String apiKey);
}
