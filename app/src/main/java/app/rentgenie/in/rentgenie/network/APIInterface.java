package app.rentgenie.in.rentgenie.network;

import app.rentgenie.in.rentgenie.models.LodgingResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
	@GET(ApiUrls.LODGING)
	Observable<LodgingResponse> getLodgingResponse(@Query("location") String location,
												   @Query("radius") int radius,
												   @Query("type") String type,
												   @Query("key") String key);

}
