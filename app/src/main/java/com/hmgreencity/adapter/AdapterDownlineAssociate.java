package com.hmgreencity.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hmgreencity.R;
import com.hmgreencity.model.ModelDownlineAssociate;

import java.util.ArrayList;

public class AdapterDownlineAssociate extends RecyclerView.Adapter<AdapterDownlineAssociate.HolderDownlineAssociate> {


    private Context context;
    private ArrayList<ModelDownlineAssociate> downlineAssociateArrayList;

    public AdapterDownlineAssociate(Context context, ArrayList<ModelDownlineAssociate> downlineAssociateArrayList) {
        this.context = context;
        this.downlineAssociateArrayList = downlineAssociateArrayList;
    }

    @NonNull
    @Override
    public AdapterDownlineAssociate.HolderDownlineAssociate onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_downline_associate_single_row,parent,false);
        return new AdapterDownlineAssociate.HolderDownlineAssociate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDownlineAssociate.HolderDownlineAssociate holder, int position) {

    if(position==0){

        holder.txt_srno.setTypeface(Typeface.DEFAULT_BOLD);
        holder.txt_loginId.setTypeface(Typeface.DEFAULT_BOLD);
        holder.txt_name.setTypeface(Typeface.DEFAULT_BOLD);

    }else{
        ModelDownlineAssociate modelDownlineAssociate= downlineAssociateArrayList.get(position-1);

        holder.txt_srno.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        holder.txt_loginId.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        holder.txt_name.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        holder.txt_srno.setText(""+position);
        holder.txt_loginId.setText(modelDownlineAssociate.getLoginId());
        holder.txt_name.setText(modelDownlineAssociate.getName());
    }

    }

    @Override
    public int getItemCount() {
        return downlineAssociateArrayList.size()+1;
    }

    public class HolderDownlineAssociate extends RecyclerView.ViewHolder {
        TextView txt_srno,txt_loginId,txt_name;
        public HolderDownlineAssociate(@NonNull View itemView) {
            super(itemView);

            txt_srno=itemView.findViewById(R.id.txt_srno);
            txt_loginId=itemView.findViewById(R.id.txt_loginId);
            txt_name=itemView.findViewById(R.id.txt_name);
        }
    }
}
