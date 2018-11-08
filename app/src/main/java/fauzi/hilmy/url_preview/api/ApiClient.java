package fauzi.hilmy.url_preview.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://api.linkpreview.net/";

    private static Retrofit setInit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiInterface getInstance() {
        return setInit().create(ApiInterface.class);
    }
}

