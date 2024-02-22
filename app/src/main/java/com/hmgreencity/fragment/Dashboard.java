package com.hmgreencity.fragment;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.JsonObject;
import com.hmgreencity.R;
import com.hmgreencity.adapter.AdapterRank;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.ModelLstAchiever;
import com.hmgreencity.model.ModelSponserList;
import com.hmgreencity.model.response.ResponseDashboard;
import com.hmgreencity.retrofit.ApiServices;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.tv_total_downline)
    TextView tv_total_downline;
    @BindView(R.id.tv_total_direct)
    TextView tv_total_direct;
    @BindView(R.id.tv_total_active)
    TextView tv_total_active;
    @BindView(R.id.tv_total_inactive)
    TextView tv_total_inactive;
    @BindView(R.id.tv_total_payout)
    TextView tv_total_payout;
    @BindView(R.id.tv_total_advance)
    TextView tv_total_advance;
    @BindView(R.id.tv_total_deduct)
    TextView tv_total_deduct;
    @BindView(R.id.tv_total_balance)
    TextView tv_total_balance;

    @BindView(R.id.total_left)
    TextView totalLeft;
    @BindView(R.id.paid_left)
    TextView paidLeft;
    @BindView(R.id.cf_left)
    TextView cfLeft;
    @BindView(R.id.total_right)
    TextView totalRight;
    @BindView(R.id.paid_right)
    TextView paidRight;
    @BindView(R.id.cf_right)
    TextView cfRight;
    @BindView(R.id.tv_unpaid_income)
    TextView tvUnpaidIncome;
    @BindView(R.id.tv_self_business)
    TextView tvSelfBusiness;
    @BindView(R.id.cv_business)
    CardView cvBusiness;
    @BindView(R.id.cv_total_direct)
    CardView cvTotalDirect;
    @BindView(R.id.imageView4)
    ImageView imageView4;


    @BindView(R.id.tv_total_hold)
    TextView tv_total_hold;







    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);





        if (NetworkUtils.getConnectivityStatus(context) != 0) {
           // AssoDashTotalBusiness();
            AssociateDashBoardTotals();
        } else showMessage(R.string.alert_internet);

        return view;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private void AssociateDashBoardTotals() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getUserId());
        LoggerUtil.logItem(object);

        Call<ResponseDashboard> call = apiServices.AssociateDashBoardTotals(object);
        call.enqueue(new Callback<ResponseDashboard>() {
            @Override
            public void onResponse(Call<ResponseDashboard> call, Response<ResponseDashboard> response) {
                hideLoading();
                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatus().equalsIgnoreCase("0")) {
                        tv_total_downline.setText(response.body().getTotalDownline());
                        tv_total_direct.setText(response.body().getTotalDirects());
                        tv_total_active.setText(response.body().getTotalActive());
                        tv_total_inactive.setText(response.body().getTotalInActive());

                        tv_total_payout.setText(response.body().getTotalPayout());
                        tv_total_advance.setText(response.body().getTotalAdvance());
                        tv_total_deduct.setText(response.body().getTotalDeduction());
                        tv_total_balance.setText(response.body().getPayoutWalletBalance());
                        tvSelfBusiness.setText(response.body().getSelfBusiness());
//                        if(response.body().getUnpaidIncome().length()>5){
//                            tvUnpaidIncome.setTextSize(12);
//                        }else{
//                            tvUnpaidIncome.setTextSize(16);
//                        }
                        tvUnpaidIncome.setText(response.body().getUnpaidIncome());

                        totalLeft.setText(response.body().getTotalBusinessLeft());
                        totalRight.setText(response.body().getTotalBusinessRight());
                        paidLeft.setText(response.body().getPaidBusinessLeft());
                        paidRight.setText(response.body().getPaidBusinessRight());
                        cfLeft.setText(response.body().getCarryLeft());
                        cfRight.setText(response.body().getCarryRight());

                        tv_total_hold.setText(response.body().getTotalHold());

                        PreferencesManager.getInstance(context).setRank(response.body().getAchiverRank());
                        
                        showCommonDialog(response.body().getAchieverArrayList(),response.body().getAchiverRank(),response.body().getImageURL());


                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {
                    Log.e("SEFFDKM",e.getMessage());
                    showMessage(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseDashboard> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void showRankDialog(String achiverRank, String imageURL) {
        final android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(getActivity());
        View mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_rank, null);


        alert.setView(mView);
        ImageView imageView=mView.findViewById(R.id.imageView);
        ImageView img_close=mView.findViewById(R.id.img_close);
        TextView txt_rank=mView.findViewById(R.id.txt_rank);

        txt_rank.setText(achiverRank);

        String substring=imageURL;
        String link="http://Admin.hmgreencity.com/"+substring;

        Glide.with(context).load(link)
                .into(imageView);

//        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.25); //<-- int width=400;
//        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.25);

        final android.app.AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().setLayout(getResources().getDisplayMetrics().widthPixels, 500);
        alertDialog.show();

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });






    }




    private void showCommonDialog(ArrayList<ModelLstAchiever> achieverArrayList, String achiverRank, String imageURL) {
        final android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(getActivity());
        View mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_common, null);
        alert.setView(mView);
        RecyclerView rv_rank=mView.findViewById(R.id.rv_rank);
        ImageView img_close=mView.findViewById(R.id.img_close);
        RelativeLayout ll_parent=mView.findViewById(R.id.ll_parent);
        AdapterRank adapterRank=new AdapterRank(getActivity(),achieverArrayList);
        rv_rank.setAdapter(adapterRank);



        Glide.with(this).load(R.drawable.animation).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ll_parent.setBackground(resource);
                }
            }
        });












        final android.app.AlertDialog alertDialog = alert.create();
        alertDialog.show();

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if(achiverRank!=null && !achiverRank.equals("")){
                    showRankDialog(achiverRank,imageURL);
                }
            }
        });

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

 /*   private void AssoDashTotalBusiness() {
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getUserId());
        LoggerUtil.logItem(object);

        Call<ResponseDashboardTotalBusiness> call = apiServices.AssoDashTotalBusiness(object);
        call.enqueue(new Callback<ResponseDashboardTotalBusiness>() {
            @Override
            public void onResponse(Call<ResponseDashboardTotalBusiness> call, Response<ResponseDashboardTotalBusiness> response) {
                hideLoading();
                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatus().equalsIgnoreCase("0")) {
                        totalLeft.setText(response.body().getTotalBusinessLeft());
                        totalRight.setText(response.body().getTotalBusinessRight());
                        paidLeft.setText(response.body().getPaidBusinessLeft());
                        paidRight.setText(response.body().getPaidBusinessRight());
                        cfLeft.setText(response.body().getCarryLeft());
                        cfRight.setText(response.body().getCarryRight());
                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {
                    showMessage("Server Issue");
                }
            }

            @Override
            public void onFailure(Call<ResponseDashboardTotalBusiness> call, Throwable t) {
                hideLoading();
            }
        });
    }*/





}
