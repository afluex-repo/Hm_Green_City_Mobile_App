package com.hmgreencity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonObject;
import com.hmgreencity.R;
import com.hmgreencity.adapter.AdapterDownlineRank;
import com.hmgreencity.adapter.AdapterSponsor;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.databinding.FragmentReportDownlineRankBinding;
import com.hmgreencity.model.ModelDownlineRankList;
import com.hmgreencity.model.response.ResponseDashboard;
import com.hmgreencity.model.response.ResponseDownlineRank;
import com.hmgreencity.retrofit.ApiServices;
import com.hmgreencity.retrofit.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentReportDownlineRank extends Fragment {
    FragmentReportDownlineRankBinding binding;
    public ApiServices apiServices;


    ArrayList<ModelDownlineRankList> downlineRankListArrayList;
    ArrayList<ModelDownlineRankList> filterdownlineRankListArrayList;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentReportDownlineRankBinding.inflate(inflater,container,false);
        apiServices = ServiceGenerator.createService(ApiServices.class);

        downlineRankListArrayList=new ArrayList<>();
        filterdownlineRankListArrayList=new ArrayList<>();

        getDownloadRanklist();

       binding.etSearch.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if (charSequence.length() > 0) {
                   filterdownlineRankListArrayList.clear();
                   for (int pos = 0; pos < downlineRankListArrayList.size(); pos++) {

                       Log.e("Position", "Pos:::" + pos);
                       Log.e("Position", "Log:::" + downlineRankListArrayList.get(pos).getFKRankId());
                       Log.e("Position", "Char:::" + charSequence);
                       Log.e("Position", "Pos:::" + pos);
                       if (downlineRankListArrayList.get(pos).getRankName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                           filterdownlineRankListArrayList.add(downlineRankListArrayList.get(pos));

                       }


                   }


                   if (filterdownlineRankListArrayList.size() > 0) {
                       AdapterDownlineRank adapterDownlineRank=new AdapterDownlineRank(getActivity(),filterdownlineRankListArrayList);
                       binding.rvDownlineRankList.setAdapter(adapterDownlineRank);
                   }
               }else{
                   getDownloadRanklist();
               }
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });
        return binding.getRoot();
    }

    private void getDownloadRanklist() {

        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(getActivity()).getUserId());

        Log.e("jhvjhv",PreferencesManager.getInstance(getActivity()).getUserId());

        Call<ResponseDownlineRank> call = apiServices.DownlineRankAchieverReports(object);
        call.enqueue(new Callback<ResponseDownlineRank>() {
            @Override
            public void onResponse(Call<ResponseDownlineRank> call, Response<ResponseDownlineRank> response) {

                Log.e("Resgcjhc",""+response.body().getDownlineRankListArrayList().size());
                if(response.isSuccessful()){
                    if(response.body().getDownlineRankListArrayList()!=null && response.body().getDownlineRankListArrayList().size()>0){

                        downlineRankListArrayList = new ArrayList<>(response.body().getDownlineRankListArrayList());
                        AdapterDownlineRank adapterDownlineRank=new AdapterDownlineRank(getActivity(),response.body().getDownlineRankListArrayList());
                        binding.rvDownlineRankList.setAdapter(adapterDownlineRank);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDownlineRank> call, Throwable t) {

            }
        });
        LoggerUtil.logItem(object);

    }
}