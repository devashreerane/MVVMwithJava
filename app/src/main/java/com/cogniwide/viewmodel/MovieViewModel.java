package com.cogniwide.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cogniwide.model.MovieModel;
import com.cogniwide.repository.MovieRepo;


public class MovieViewModel extends ViewModel {

    private final MovieRepo movieRepo;

    public MovieViewModel() {
        movieRepo = new MovieRepo();
    }

    public LiveData<MovieModel> getMovieData() {
        return movieRepo.getMovieList();
    }
}
