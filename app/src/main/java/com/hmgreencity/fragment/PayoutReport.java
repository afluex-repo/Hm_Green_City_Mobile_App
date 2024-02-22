package com.hmgreencity.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
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
import com.hmgreencity.adapter.DirectSearchAdapter;
import com.hmgreencity.adapter.PayoutReportAdopter;
import com.hmgreencity.adapter.PayoutReportSearchAdapter;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.common.Utils;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.request.RequestDirectSearch;
import com.hmgreencity.model.request.RequestPayoutReportSearch;
import com.hmgreencity.model.response.responseDirectSearch.ResponseDirectSearch;
import com.hmgreencity.model.response.responsePayoutReport.ResponsePayoutReport;
import com.hmgreencity.model.response.responsePayoutReportSearch.ResponsePayoutReportSearch;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayoutReport extends BaseFragment {
    @BindView(R.id.tv_payout_no)
    EditText tvPayoutNo;
    @BindView(R.id.tv_from_date)
    EditText tvFromDate;
    @BindView(R.id.tv_to_date)
    EditText tvToDate;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    private Unbinder unbinder;
    BottomSheetDialog searchDialog;

    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    private DatePickerDialog mDatePickerDialog, getmDatePickerDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payout_report, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerview1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview1.setLayoutManager(layoutManager);

        if (NetworkUtils.getConnectivityStatus(context) != 0)
            getData();
            //getData1();

        else showMessage(R.string.alert_internet);


        return view;
    }

    public void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
        Log.e("PLOBJHGGHF",PreferencesManager.getInstance(context).getLoginId());
        LoggerUtil.logItem(object);
        Call<ResponsePayoutReport> call = apiServices.PayoutReports(object);
        call.enqueue(new Callback<ResponsePayoutReport>() {
            @Override
            public void onResponse(Call<ResponsePayoutReport> call, Response<ResponsePayoutReport> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if(response.body().getStatus()!=null){
                    if (response.body().getStatus().equalsIgnoreCase("0")) {
                        PayoutReportAdopter adapter = new PayoutReportAdopter(response.body().getLstPayoutDetail(), getContext());
                        recyclerview1.setAdapter(adapter);
                    } else showMessage(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponsePayoutReport> call, Throwable t) {
                hideLoading();
            }

        });
    }

    public void getData1() {
        showLoading();
        RequestPayoutReportSearch requestPayoutReportSearch = new RequestPayoutReportSearch();
        requestPayoutReportSearch.setLoginId(PreferencesManager.getInstance(context).getLoginId());
        LoggerUtil.logItem(requestPayoutReportSearch);
        Call<ResponsePayoutReportSearch> call = apiServices.getPayoutReportSearch(requestPayoutReportSearch);
        call.enqueue(new Callback<ResponsePayoutReportSearch>() {
            @Override
            public void onResponse(Call<ResponsePayoutReportSearch> call, Response<ResponsePayoutReportSearch> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    PayoutReportSearchAdapter adapter = new PayoutReportSearchAdapter(response.body().getLstPayoutDetails(), getContext());
                    recyclerview1.setAdapter(adapter);
                    recyclerview1.setVisibility(View.VISIBLE);
                } else if (response.body().getStatus().equalsIgnoreCase("1")) {

                    recyclerview1.setVisibility(View.GONE);
                    showMessage(response.body().getMessage());
                } else

                    showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponsePayoutReportSearch> call, Throwable t) {
                hideLoading();
            }

        });
    }
    public void getDataSearch(String startDate, String endDate, String payout_number) {
        showLoading();
        RequestPayoutReportSearch requestPayoutReportSearch = new RequestPayoutReportSearch();
        requestPayoutReportSearch.setLoginId(PreferencesManager.getInstance(context).getLoginId());
      //  if (payout_number.length() == 0)
            requestPayoutReportSearch.setPayoutNo(payout_number);
      //  else requestPayoutReportSearch.setPayoutNo(payout_number);
      //  if (startDate.length() == 0)
            requestPayoutReportSearch.setFromDate(startDate);
       // else if (endDate.length() == 0)
            requestPayoutReportSearch.setToDate(endDate);
       // else requestPayoutReportSearch.setToDate(endDate);

        LoggerUtil.logItem(requestPayoutReportSearch);
        Call<ResponsePayoutReportSearch> call = apiServices.getPayoutReportSearch(requestPayoutReportSearch);
        call.enqueue(new Callback<ResponsePayoutReportSearch>() {
            @Override
            public void onResponse(Call<ResponsePayoutReportSearch> call, Response<ResponsePayoutReportSearch> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    PayoutReportSearchAdapter adapter = new PayoutReportSearchAdapter(response.body().getLstPayoutDetails(), getContext());
                    recyclerview1.setAdapter(adapter);
                    recyclerview1.setVisibility(View.VISIBLE);
                } else {
                    recyclerview1.setVisibility(View.GONE);
                    showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponsePayoutReportSearch> call, Throwable t) {
                hideLoading();
            }
        });
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchhDialog();
            }
        });
    }


    private void searchhDialog() {
        searchDialog = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dilalog_filter_search, null);

        searchDialog.setContentView(sheetView);
        TextView tv_start_date = sheetView.findViewById(R.id.tv_start_date);
        TextView tv_end_date = sheetView.findViewById(R.id.tv_end_date);
        EditText et_payout_no = sheetView.findViewById(R.id.et_payout_number);
        Button btn_cancel = sheetView.findViewById(R.id.btn_cancel);
        Button btn_search = sheetView.findViewById(R.id.btn_search);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchDialog.dismiss();
            }
        });

        btn_search.setOnClickListener(v -> {
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
        getDataSearch(tv_start_date.getText().toString().trim(),
                tv_end_date.getText().toString().trim(),
                et_payout_no.getText().toString().trim());

//        btn_search.setOnClickListener(v -> {
//            searchDialog.dismiss();
//
//        });

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


    @OnClick(R.id.iv_search)
    public void onViewClicked() {
        searchhDialog();
    }
}
