package com.hmgreencity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hmgreencity.R;
import com.hmgreencity.model.ModelSponserList;

import java.util.ArrayList;

public class AdapterSponsor extends RecyclerView.Adapter<AdapterSponsor.HolderSponsor> {
    private Context context;
    private ArrayList<ModelSponserList> sponserListArrayList;

    public AdapterSponsor(Context context, ArrayList<ModelSponserList> sponserListArrayList) {
        this.context = context;
        this.sponserListArrayList = sponserListArrayList;
    }

    @NonNull
    @Override
    public AdapterSponsor.HolderSponsor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_sponsor_single_row,parent,false);
        return new AdapterSponsor.HolderSponsor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSponsor.HolderSponsor holder, int position) {

        ModelSponserList modelSponserList=sponserListArrayList.get(position);
        holder.txt_loginId.setText(modelSponserList.getLoginIDD());
        holder.txt_name.setText(modelSponserList.getUserName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("site_position");

                intent.putExtra("login_id",modelSponserList.getLoginIDD());
                intent.putExtra("name",modelSponserList.getUserName());



//                    intent.putExtra("position1", holder.getAdapterPosition());
//                    intent.putExtra("workerDeletedType", workerType);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sponserListArrayList.size();
    }

    public class HolderSponsor extends RecyclerView.ViewHolder {


        TextView txt_loginId,txt_name;
        public HolderSponsor(@NonNull View itemView) {
            super(itemView);
            txt_loginId=itemView.findViewById(R.id.txt_loginId);
            txt_name=itemView.findViewById(R.id.txt_name);
        }
    }
}
