package fauzi.hilmy.url_preview.api;

import com.google.gson.JsonArray;

import fauzi.hilmy.url_preview.model.ResponsePreview;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("?")
    Call<ResponsePreview> getTech(
            @Query("key") String apiKey,
            @Query("q") String query
    );
}
