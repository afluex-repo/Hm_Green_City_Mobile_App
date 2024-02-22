package com.hmgreencity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.hmgreencity.R;
import com.hmgreencity.adapter.UnpaidIncomeAdopter;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.response.responseUnpaidIncome.ResponseUnpaidIncome;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnpaidIncome extends BaseFragment {

    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    private Unbinder unbinder;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unpaid_income, container, false);

        unbinder = ButterKnife.bind(this, view);

        recyclerview1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview1.setLayoutManager(layoutManager);

        if (NetworkUtils.getConnectivityStatus(context) != 0)
            getData();
        else showMessage(R.string.alert_internet);

        return view;
    }

    public void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
        LoggerUtil.logItem(object);
        Call<ResponseUnpaidIncome> call = apiServices.UnpaidIncomes(object);
        call.enqueue(new Callback<ResponseUnpaidIncome>() {
            @Override
            public void onResponse(Call<ResponseUnpaidIncome> call, Response<ResponseUnpaidIncome> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    if (response.body().getLstunpaidincomes() != null && response.body().getLstunpaidincomes().size() > 0) {
                        UnpaidIncomeAdopter adapter = new UnpaidIncomeAdopter(response.body().getLstunpaidincomes(), getContext());
                        recyclerview1.setAdapter(adapter);
                    } else showMessage(response.body().getMessage());
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseUnpaidIncome> call, Throwable t) {
                hideLoading();
            }
        });
    }
}
