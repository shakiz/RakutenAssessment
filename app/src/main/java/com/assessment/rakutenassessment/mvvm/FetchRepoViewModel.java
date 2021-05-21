package com.assessment.rakutenassessment.mvvm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.assessment.rakutenassessment.model.Repos;

public class FetchRepoViewModel extends AndroidViewModel {
    private MutableLiveData<Repos> androidRepos;
    private FetchRepository androidRepository;

    public FetchRepoViewModel(@NonNull Application application) {
        super(application);
    }

    public void getData(Context context){
        androidRepository = FetchRepository.getInstance();
        androidRepos = androidRepository.getAndroidRepos(context);
    }

    public MutableLiveData<Repos> getAndroidRepos() {
        return androidRepos;
    }
}
