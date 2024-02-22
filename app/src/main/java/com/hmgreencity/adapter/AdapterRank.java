package com.hmgreencity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hmgreencity.R;
import com.hmgreencity.model.ModelLstAchiever;

import java.util.ArrayList;

public class AdapterRank extends RecyclerView.Adapter<AdapterRank.HolderRank> {

    private Context context;
    private ArrayList<ModelLstAchiever> achieverArrayList;

    public AdapterRank(Context context, ArrayList<ModelLstAchiever> achieverArrayList) {
        this.context = context;
        this.achieverArrayList = achieverArrayList;
    }

    @NonNull
    @Override
    public AdapterRank.HolderRank onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.layout_rank_single_row,parent,false);
        return new AdapterRank.HolderRank(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRank.HolderRank holder, int position) {
        ModelLstAchiever modelLstAchiever=achieverArrayList.get(position);

        String substring=modelLstAchiever.getImageURLpopup().substring(6);
        String link="http://Admin.hmgreencity.com/"+substring;

        Glide.with(context).load(link)
                .into(holder.imageView);

        holder.tv_rank_name.setText(modelLstAchiever.getAchiverRankpopup());
        holder.tv_rank_num.setText(modelLstAchiever.getAchiver());



    }

    @Override
    public int getItemCount() {
        return achieverArrayList.size();
    }

    public class HolderRank extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_rank_name,tv_rank_num;
        public HolderRank(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            tv_rank_name=itemView.findViewById(R.id.tv_rank_name);
            tv_rank_num=itemView.findViewById(R.id.tv_rank_num);

        }
    }
}
