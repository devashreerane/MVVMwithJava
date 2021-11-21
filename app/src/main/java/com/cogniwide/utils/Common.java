package com.cogniwide.utils;


import com.cogniwide.network.APIService;
import com.cogniwide.network.RetroInstance;


public class Common {

    public static final String BASE_URL = "https://api.themoviedb.org/";

    public static final String API_KEY = "434d1d4ec64f574aed3d6f31bc984c2f";
    public static final String Movie = "3/movie/popular?api_key="+API_KEY;

    public static APIService getApiInterface() {
        return RetroInstance.getRetrofitClient(BASE_URL).create(APIService.class);
    }

}
