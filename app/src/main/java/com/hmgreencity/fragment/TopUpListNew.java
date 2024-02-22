package com.hmgreencity.fragment;

import static com.hmgreencity.common.Utils.showMessage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmgreencity.R;
import com.hmgreencity.adapter.TopUpListAdopter;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.request.RequestTopUpList;
import com.hmgreencity.model.response.responseTopUpList.ResponseTopUpList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopUpListNew extends BaseFragment {

    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview;

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_up_list_new, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutManager);

        if (NetworkUtils.getConnectivityStatus(context) != 0)
            getData();
        else showMessage(R.string.alert_internet);

        // Inflate the layout for this fragment
        return view;
    }
    public void getData() {
        showLoading();
        RequestTopUpList requestTopUpList = new RequestTopUpList();

        requestTopUpList.setLoginId(PreferencesManager.getInstance(context).getLoginId());
        LoggerUtil.logItem(requestTopUpList);

        Call<ResponseTopUpList> call = apiServices.TopUpListsNew(requestTopUpList);
        call.enqueue(new Callback<ResponseTopUpList>() {
            @Override
            public void onResponse(Call<ResponseTopUpList> call, Response<ResponseTopUpList> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    TopUpListAdopter adapter = new TopUpListAdopter(response.body().getLstTopupReportNew(), getContext());

                    Log.e("fhcghcghcghc",""+response.body().getLstTopupReportNew().size());
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