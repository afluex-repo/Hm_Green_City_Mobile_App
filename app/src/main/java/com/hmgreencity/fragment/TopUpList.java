package com.hmgreencity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hmgreencity.R;
import com.hmgreencity.adapter.TopUpListAdopter;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.request.RequestTopUpList;
import com.hmgreencity.model.response.responseTopUpList.LsttopupreportItem;
import com.hmgreencity.model.response.responseTopUpList.ResponseTopUpList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopUpList extends BaseFragment {

    private Unbinder unbinder;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_up_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutManager);

        if (NetworkUtils.getConnectivityStatus(context) != 0)
            getData();
        else showMessage(R.string.alert_internet);

        return view;
    }

    public void getData() {
        showLoading();
        RequestTopUpList requestTopUpList = new RequestTopUpList();

        requestTopUpList.setLoginId(PreferencesManager.getInstance(context).getLoginId());
        LoggerUtil.logItem(requestTopUpList);

        Call<ResponseTopUpList> call = apiServices.TopUpLists(requestTopUpList);
        call.enqueue(new Callback<ResponseTopUpList>() {
            @Override
            public void onResponse(Call<ResponseTopUpList> call, Response<ResponseTopUpList> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    TopUpListAdopter adapter = new TopUpListAdopter(response.body().getLsttopupreport(), getContext());
                    recyclerview.setAdapter(adapter);
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseTopUpList> call, Throwable t) {
                hideLoading();
            }
        });
    }
}

