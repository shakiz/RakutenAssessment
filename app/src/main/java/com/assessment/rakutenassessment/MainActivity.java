package com.assessment.rakutenassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.assessment.rakutenassessment.adapter.FetchRepoDataAdapter;
import com.assessment.rakutenassessment.databinding.ActivityMainBinding;
import com.assessment.rakutenassessment.model.Repos;
import com.assessment.rakutenassessment.model.Value;
import com.assessment.rakutenassessment.mvvm.FetchRepoViewModel;
import com.assessment.rakutenassessment.utils.UX;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private FetchRepoViewModel fetchRepoViewModel;
    private UX ux;
    private ArrayList<Value> androidTopicList;
    private FetchRepoDataAdapter fetchRepoDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //region perform all object creations
        initUI();
        //endregion

        //region perform UI interactions
        performUIInteractions();
        //endregion
    }

    private void initUI() {
        androidTopicList = new ArrayList<>();
        ux = new UX(MainActivity.this);
        fetchRepoViewModel = ViewModelProviders.of(this).get(FetchRepoViewModel.class);
    }

    private void performUIInteractions() {
        performServerOperation();
    }

    //region perform server fetch
    private void performServerOperation(){
        fetchRepoViewModel.getData(this);
        fetchRepoViewModel.getAndroidRepos().observe(this, new Observer<Repos>() {
            @Override
            public void onChanged(Repos items) {
                if (items != null) {
                    androidTopicList = new ArrayList<>(items.getValues());
                    loadListView();
                    fetchRepoDataAdapter.notifyDataSetChanged();
                    noDataVisibility(false);
                }
                else {
                    Toast.makeText(MainActivity.this, R.string.nodata, Toast.LENGTH_SHORT).show();
                    noDataVisibility(true);
                }
            }
        });
    }
    //endregion

    //region load list data
    private void loadListView(){
        fetchRepoDataAdapter = new FetchRepoDataAdapter(androidTopicList);
        activityMainBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        activityMainBinding.mRecyclerView.setAdapter(fetchRepoDataAdapter);
        fetchRepoDataAdapter.notifyDataSetChanged();
        fetchRepoDataAdapter.setOnItemClickListener(new FetchRepoDataAdapter.onItemClickListener() {
            @Override
            public void respond(Value androidItem) {

            }
        });
    }
    //endregion

    //region set no data related components visible
    private void noDataVisibility(boolean shouldVisible){
        if (shouldVisible) {
            activityMainBinding.NoDataMessage.setVisibility(View.VISIBLE);
        } else {
            activityMainBinding.NoDataMessage.setVisibility(View.GONE);
        }
    }
    //endregion

}