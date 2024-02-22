package com.hmgreencity.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.hmgreencity.R;
import com.hmgreencity.adapter.BusinessReportAdapter;
import com.hmgreencity.adapter.BusinessReportSearchAdapter;
import com.hmgreencity.adapter.DirectSearchAdapter;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.common.Utils;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.request.RequestBusinessReportSearch;
import com.hmgreencity.model.request.RequestDirectSearch;
import com.hmgreencity.model.response.responseBusinessReport.ResponseBusinessReport;
import com.hmgreencity.model.response.responseBusinessReportSearch.ResponseBusinessReportSearch;
import com.hmgreencity.model.response.responseDirectSearch.ResponseDirectSearch;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessReport extends BaseFragment {

    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.tv_from_date)
    EditText tvFromDate;
    @BindView(R.id.tv_to_date)
    EditText tvToDate;
    @BindView(R.id.sp_leg)
    Spinner spLeg;
    @BindView(R.id.btn_search)
    ImageView btnSearch;
    private Unbinder unbinder;
    BottomSheetDialog searchDialog;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business_report, container, false);
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
        Call<ResponseBusinessReport> call = apiServices.GetBusinessReport(object);
        call.enqueue(new Callback<ResponseBusinessReport>() {
            @Override
            public void onResponse(Call<ResponseBusinessReport> call, Response<ResponseBusinessReport> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    BusinessReportAdapter adapter = new BusinessReportAdapter(response.body().getLstassociate(), getContext());
                    recyclerview1.setAdapter(adapter);
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseBusinessReport> call, Throwable t) {
                hideLoading();
            }

        });
    }

    @OnClick(R.id.btn_search)
    public void onViewClicked() {

        searchhDialog();
    }
    public void getDataSearch(String startDate, String endDate, String leg,String isdownline) {
        showLoading();
        RequestBusinessReportSearch requestBusinessReportSearch = new RequestBusinessReportSearch();
        requestBusinessReportSearch.setLoginId(PreferencesManager.getInstance(context).getLoginId());
        if (startDate.length() == 0)
            requestBusinessReportSearch.setFromDate("null");
        else if (endDate.length() == 0)
            requestBusinessReportSearch.setToDate("null");
        else requestBusinessReportSearch.setToDate(endDate);
         if (leg.length()==0)
        requestBusinessReportSearch.setLeg("null");
         else requestBusinessReportSearch.setLeg(leg);
         requestBusinessReportSearch.setIsdownline(isdownline);

        LoggerUtil.logItem(requestBusinessReportSearch);
        Call<ResponseBusinessReportSearch> call = apiServices.GetBusinessReports(requestBusinessReportSearch);
        call.enqueue(new Callback<ResponseBusinessReportSearch>() {
            @Override
            public void onResponse(Call<ResponseBusinessReportSearch> call, Response<ResponseBusinessReportSearch> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    BusinessReportAdapter adapter = new BusinessReportAdapter(response.body().getLstassociate(), getContext());
                    recyclerview1.setAdapter(adapter);
                    recyclerview1.setVisibility(View.VISIBLE);
                } else {
                    recyclerview1.setVisibility(View.GONE);
                    showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBusinessReportSearch> call, Throwable t) {
                hideLoading();
            }
        });
    }
    String leg = "";
    String isdownline="";

    private void searchhDialog() {
        searchDialog = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dilog_business_search, null);
        searchDialog.setContentView(sheetView);
        leg = "null";

        TextView tv_start_date = sheetView.findViewById(R.id.tv_start_date);
        TextView tv_end_date = sheetView.findViewById(R.id.tv_end_date);
        TextView tv_leg = sheetView.findViewById(R.id.tv_leg);
        CheckBox checkBox=sheetView.findViewById(R.id.checkBox);
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
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked())
                {
                    isdownline="1";
                }
                else
                {
                    isdownline="0";
                }

            }
        });
        tv_leg.setOnClickListener(v -> {
            PopupMenu popup_sidemenu = new PopupMenu(context, tv_leg);
            popup_sidemenu.getMenuInflater().inflate(R.menu.menu_leg_filter, popup_sidemenu.getMenu());
            popup_sidemenu.setOnMenuItemClickListener(item -> {
                if (item.getTitle().equals("All"))
                    leg = "null";
                else if (item.getTitle().equals("Left"))
                    leg = "L";
                else if (item.getTitle().equals("Right"))
                    leg = "R";
                tv_leg.setText(item.getTitle());
                return true;
            });
            popup_sidemenu.show();
        });

        btn_search.setOnClickListener(v -> {
            searchDialog.dismiss();
            getDataSearch(tv_start_date.getText().toString().trim(),
                    tv_end_date.getText().toString().trim(),
                    leg,isdownline);

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
