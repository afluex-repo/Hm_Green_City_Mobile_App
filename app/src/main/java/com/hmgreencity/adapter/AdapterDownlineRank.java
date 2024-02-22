package com.hmgreencity.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.hmgreencity.BuildConfig;
import com.hmgreencity.R;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.model.ModelDownlineRankList;
import com.hmgreencity.model.ResponseDownlineAssociate;
import com.hmgreencity.retrofit.ApiServices;
import com.hmgreencity.retrofit.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDownlineRank extends RecyclerView.Adapter<AdapterDownlineRank.HolderDownlineRank> {
    private Context context;
    private ArrayList<ModelDownlineRankList> downlineRankListArrayList;


    public AdapterDownlineRank(Context context, ArrayList<ModelDownlineRankList> downlineRankListArrayList) {
        this.context = context;
        this.downlineRankListArrayList = downlineRankListArrayList;
    }

    @NonNull
    @Override
    public AdapterDownlineRank.HolderDownlineRank onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_downline_rank_list,parent,false);
        return new AdapterDownlineRank.HolderDownlineRank(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDownlineRank.HolderDownlineRank holder, int position) {

        if(position==0){
            holder.img_img.setVisibility(View.GONE);
            holder.txt_img.setVisibility(View.VISIBLE);

            holder.txt_sr.setTypeface(Typeface.DEFAULT_BOLD);
            holder.txt_left.setTypeface(Typeface.DEFAULT_BOLD);
            holder.txt_right.setTypeface(Typeface.DEFAULT_BOLD);
            holder.txt_name.setTypeface(Typeface.DEFAULT_BOLD);
        }else{
            ModelDownlineRankList downlineRankList=downlineRankListArrayList.get(position-1);

            holder.txt_sr.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.txt_left.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.txt_right.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.txt_name.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.img_img.setVisibility(View.VISIBLE);
            holder.txt_img.setVisibility(View.GONE);
            holder.txt_sr.setText(""+position);
            holder.txt_left.setText(downlineRankList.getTotalAchieverLeft());
            holder.txt_right.setText(downlineRankList.getTotalAchieverRight());
            holder.txt_name.setText(downlineRankList.getRankName());

            String substring=downlineRankList.getRewardImage().substring(6);
            String link=BuildConfig.BASE_URL+substring;

            Glide.with(context).load(link)
                    .into(holder.img_img);
            holder.txt_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Integer.parseInt(downlineRankList.getTotalAchieverLeft())>0){
                        showAssociateDialog("L",downlineRankList.getFKRankId());
                    }
                }
            });

            holder.txt_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Integer.parseInt(downlineRankList.getTotalAchieverRight())>0){
                        showAssociateDialog("R",downlineRankList.getFKRankId());
                    }
                }
            });
        }

    }

    private void showAssociateDialog(String leg, String fkRankId) {
        final android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(context);
        View mView = LayoutInflater.from(context).inflate(R.layout.dialog_associate_show, null);
        alert.setView(mView);
        RecyclerView rv_associate_downline=mView.findViewById(R.id.rv_associate_downline);
        ImageView img_close=mView.findViewById(R.id.img_close);

        ApiServices apiServices = ServiceGenerator.createService(ApiServices.class);


        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getUserId());
        object.addProperty("FK_RankId", fkRankId);
        object.addProperty("Leg", leg);

        Call<ResponseDownlineAssociate> call = apiServices.DownlineRankAchieverAssociateReports(object);
        call.enqueue(new Callback<ResponseDownlineAssociate>() {
            @Override
            public void onResponse(Call<ResponseDownlineAssociate> call, Response<ResponseDownlineAssociate> response) {
                if(response.isSuccessful()){
                    if(response.body().getDownlineAssociateArrayList()!=null &&
                    response.body().getDownlineAssociateArrayList().size()>0){
                        AdapterDownlineAssociate adapterDownlineAssociate=new AdapterDownlineAssociate(context,response.body().getDownlineAssociateArrayList());
                        rv_associate_downline.setAdapter(adapterDownlineAssociate);
                        showDIalog(alert,img_close);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDownlineAssociate> call, Throwable t) {

            }
        });























//        img_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.dismiss();
//            }
//        });

    }

    private void showDIalog(AlertDialog.Builder alert, ImageView img_close) {
        final android.app.AlertDialog alertDialog = alert.create();
        alertDialog.show();

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return downlineRankListArrayList.size()+1;
    }

    public class HolderDownlineRank extends RecyclerView.ViewHolder {

        TextView txt_sr,txt_img,txt_name,  txt_left ,txt_right;
        ImageView img_img;


        public HolderDownlineRank(@NonNull View itemView) {
            super(itemView);
            txt_sr=itemView.findViewById(R.id.txt_sr_no);
            txt_img=itemView.findViewById(R.id.txt_img);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_left=itemView.findViewById(R.id.txt_left);
            txt_right=itemView.findViewById(R.id.txt_right);
            img_img=itemView.findViewById(R.id.img_img);

        }
    }
}
