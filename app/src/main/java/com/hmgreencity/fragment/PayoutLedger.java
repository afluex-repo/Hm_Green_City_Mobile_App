package com.hmgreencity.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.hmgreencity.R;
import com.hmgreencity.adapter.PayoutLedgerAdapter;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.common.Utils;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.request.RequestPayoutLedger;
import com.hmgreencity.model.response.responsePayoutLedger.ResponsePayoutLedger;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayoutLedger extends BaseFragment {

    @BindView(R.id.tv_from_date)
    EditText tvFromDate;
    @BindView(R.id.tv_to_date)
    EditText tvToDate;
    @BindView(R.id.btn_search)
    ImageView btnSearch;

    @BindView(R.id.tv_net_balance)
    TextView tv_net_balance;
    private Unbinder unbinder;

    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    BottomSheetDialog searchDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payout_ledger, container, false);
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
        RequestPayoutLedger object = new RequestPayoutLedger();
        object.setFk_UserId( PreferencesManager.getInstance(context).getUserId());
        LoggerUtil.logItem(object);
        Call<ResponsePayoutLedger> call = apiServices.getPayoutLedgers(object);
        call.enqueue(new Callback<ResponsePayoutLedger>() {
            @Override
            public void onResponse(Call<ResponsePayoutLedger> call, Response<ResponsePayoutLedger> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus().equalsIgnoreCase("0")) {

                    tv_net_balance.setText(response.body().getLstpayoutledger().get(0).getPayoutBalance());

                    PayoutLedgerAdapter adapter = new PayoutLedgerAdapter(response.body().getLstpayoutledger(), getContext());
                    recyclerview1.setAdapter(adapter);
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponsePayoutLedger> call, Throwable t) {
                hideLoading();
            }

        });
    }

    public void getDataSearch(String startDate, String endDate) {
        showLoading();
        RequestPayoutLedger object = new RequestPayoutLedger();
        object.setFk_UserId( PreferencesManager.getInstance(context).getUserId());
        object.setFromDate(startDate);
        object.setToDate(endDate);
        LoggerUtil.logItem(object);
        Call<ResponsePayoutLedger> call = apiServices.getPayoutLedgers(object);
        call.enqueue(new Callback<ResponsePayoutLedger>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<ResponsePayoutLedger> call, Response<ResponsePayoutLedger> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    PayoutLedgerAdapter adapter = new PayoutLedgerAdapter(response.body().getLstpayoutledger(), getContext());
                    recyclerview1.setAdapter(adapter);
                    recyclerview1.setVisibility(View.VISIBLE);

                } else
                    recyclerview1.setVisibility(View.GONE);
                    showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponsePayoutLedger> call, Throwable t) {
                hideLoading();
            }

        });
    }


    @OnClick(R.id.btn_search)
    public void onViewClicked() {
        searchhDialog();
    }
    private void searchhDialog() {
        searchDialog = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dilog_payout_search, null);
        searchDialog.setContentView(sheetView);
        TextView tv_start_date = sheetView.findViewById(R.id.tv_start_date);
        TextView tv_end_date = sheetView.findViewById(R.id.tv_end_date);
        Button btn_cancel = sheetView.findViewById(R.id.btn_cancel);
        Button btn_search = sheetView.findViewById(R.id.btn_search);
        btn_cancel.setOnClickListener(v -> {
            searchDialog.dismiss();
        });

        tv_start_date.setOnClickListener(v -> {
            datePicker(tv_start_date);
            tv_end_date.setText("");
        });

        tv_end_date.setOnClickListener(v -> {
            if (tv_start_date.getText().toString().equalsIgnoreCase(""))
                showMessage("Select Start Date");
            else
                datePicker(tv_end_date);
        });



        btn_search.setOnClickListener(v -> {
            searchDialog.dismiss();
            getDataSearch(tv_start_date.getText().toString().trim(),
                    tv_end_date.getText().toString().trim());
        });

        searchDialog.setCancelable(false);
        searchDialog.setCanceledOnTouchOutside(false);
        searchDialog.show();
    }

    private void datePicker(final TextView et) {
        Calendar cal = Calendar.getInstance();
        int mYear, mMonth, mDay;

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {
            et.setText(Utils.changeDateFormat(dayOfMonth, monthOfYear, year));
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }

}
