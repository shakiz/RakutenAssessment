package com.assessment.rakutenassessment.api;

import com.assessment.rakutenassessment.model.Repos;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AllApiService {
    //Call for topics android
    @GET("repositories/")
    Call<Repos> getAllTopics();
}
