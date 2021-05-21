package com.assessment.rakutenassessment.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.assessment.rakutenassessment.R;
import com.assessment.rakutenassessment.model.Value;

import java.util.ArrayList;

public class FetchRepoDataAdapter extends RecyclerView.Adapter<FetchRepoDataAdapter.ViewHolder> {
    private onItemClickListener onItemClickListener;
    private ArrayList<Value> androidItemList;

    public FetchRepoDataAdapter(ArrayList<Value> androidItemList) {
        this.androidItemList = androidItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_layout_android_topics,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Value androidItem = androidItemList.get(i);
        viewHolder.FullName.setText(androidItem.getName());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.respond(androidItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidItemList.size();
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView FullName , RepoLink , Language , NumberOfStars , NumberOfWatch , NumberOfForks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_card_view);
            FullName = itemView.findViewById(R.id.FullName);
            RepoLink = itemView.findViewById(R.id.RepoLink);
            NumberOfStars = itemView.findViewById(R.id.NumberOfStars);
            NumberOfForks = itemView.findViewById(R.id.NumberOfForks);
            NumberOfWatch = itemView.findViewById(R.id.NumberOfWatch);
        }
    }

    public interface onItemClickListener{
        void respond(Value androidItem);
    }
}
