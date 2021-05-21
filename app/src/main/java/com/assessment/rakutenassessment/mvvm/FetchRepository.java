package com.assessment.rakutenassessment.mvvm;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.assessment.rakutenassessment.api.AllApiService;
import com.assessment.rakutenassessment.model.Repos;
import com.assessment.rakutenassessment.utils.UtilsManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchRepository {
    private AllApiService apiService;
    private UtilsManager utilsManager;
    //region singleton instance
    private static FetchRepository instance = null;

    public static FetchRepository getInstance(){
        if (instance == null){
            instance = new FetchRepository();
        }
        return instance;
    }
    //endregion

    public MutableLiveData<Repos> getAndroidRepos(Context context){
        utilsManager = new UtilsManager(context);
        final MutableLiveData<Repos> androidRepos = new MutableLiveData<>();
        apiService = utilsManager.getClient("https://api.bitbucket.org/2.0/").create(AllApiService.class);
        final Call<Repos> androidTopicCall=apiService.getAllTopics();
        androidTopicCall.enqueue(new Callback<Repos>() {
            @Override
            public void onResponse(Call<Repos> call, Response<Repos> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        if (response.body().getValues().size() > 0){
                            androidRepos.setValue(response.body());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Repos> call, Throwable t) {
                androidRepos.setValue(null);
            }
        });
        return androidRepos;
    }
}
