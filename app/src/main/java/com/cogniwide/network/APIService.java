package com.cogniwide.network;

import com.cogniwide.model.MovieModel;
import com.cogniwide.utils.Common;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIService {

    @GET(Common.Movie)
    Observable<MovieModel> getMovieData();

}
