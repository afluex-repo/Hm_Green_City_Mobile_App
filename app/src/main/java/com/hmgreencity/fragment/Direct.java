package com.hmgreencity.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.hmgreencity.adapter.DirectAdapter;
import com.hmgreencity.adapter.DirectSearchAdapter;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.common.Utils;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.request.RequestDirectSearch;
import com.hmgreencity.model.response.responseDirect.ResponseDirect;
import com.hmgreencity.model.response.responseDirectSearch.ResponseDirectSearch;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Direct extends BaseFragment {


    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    private Unbinder unbinder;

    BottomSheetDialog searchDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_direct, container, false);
        unbinder = ButterKnife.bind(this, view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview1.setLayoutManager(layoutManager);
        recyclerview1.setHasFixedSize(true);

        if (NetworkUtils.getConnectivityStatus(context) != 0) {
            getData();
        } else showMessage(R.string.alert_internet);

        return view;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());

        LoggerUtil.logItem(object);
        Call<ResponseDirect> call = apiServices.GetResponseDirect(object);
        call.enqueue(new Callback<ResponseDirect>() {
            @Override
            public void onResponse(Call<ResponseDirect> call, Response<ResponseDirect> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    DirectAdapter adapter = new DirectAdapter(response.body().getLstdirect(), getContext());
                    recyclerview1.setAdapter(adapter);
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseDirect> call, Throwable t) {
                hideLoading();
            }

        });
    }

    public void getDataSearch(String startDate, String endDate, String leg, String status, String name) {
        showLoading();
        RequestDirectSearch requestDirectSearch = new RequestDirectSearch();
        requestDirectSearch.setLoginId(PreferencesManager.getInstance(context).getLoginId());
        if (name.length() == 0)
            requestDirectSearch.setName("null");
        else requestDirectSearch.setName(name);
        if (startDate.length() == 0)
            requestDirectSearch.setFromDate("null");
        else if (endDate.length() == 0)
            requestDirectSearch.setToDate("null");
        else requestDirectSearch.setToDate(endDate);
        requestDirectSearch.setLeg(leg);
        requestDirectSearch.setStatus(status);

        LoggerUtil.logItem(requestDirectSearch);
        Call<ResponseDirectSearch> call = apiServices.GetResponseDirectSearch(requestDirectSearch);
        call.enqueue(new Callback<ResponseDirectSearch>() {
            @Override
            public void onResponse(Call<ResponseDirectSearch> call, Response<ResponseDirectSearch> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus1().equalsIgnoreCase("0")) {
                    DirectSearchAdapter adapter = new DirectSearchAdapter(response.body().getLstdirect(), getContext());
                    recyclerview1.setAdapter(adapter);
                    recyclerview1.setVisibility(View.VISIBLE);
                } else {
                    recyclerview1.setVisibility(View.GONE);
                    showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseDirectSearch> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @OnClick(R.id.btn_search)
    public void onViewClicked() {
        searchhDialog();
    }

    String leg = "", status = "";

    private void searchhDialog() {
        searchDialog = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_filter_direct, null);
        searchDialog.setContentView(sheetView);
        leg = "null";
        status = "null";
        TextView tv_start_date = sheetView.findViewById(R.id.tv_start_date);
        TextView tv_end_date = sheetView.findViewById(R.id.tv_end_date);
        TextView tv_leg = sheetView.findViewById(R.id.tv_leg);
        TextView tv_status = sheetView.findViewById(R.id.tv_status);
        EditText et_name = sheetView.findViewById(R.id.et_name);
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

        tv_status.setOnClickListener(v -> {
            PopupMenu popup_sidemenu = new PopupMenu(context, tv_status);
            popup_sidemenu.getMenuInflater().inflate(R.menu.menu_status_flter, popup_sidemenu.getMenu());
            popup_sidemenu.setOnMenuItemClickListener(item -> {
                if (item.getTitle().equals("All"))
                    status = "null";
                else if (item.getTitle().equals("Active"))
                    status = "P";
                else if (item.getTitle().equals("InActive"))
                    status = "T";
                else if (item.getTitle().equals("Blocked"))
                    status = "B";
                tv_status.setText(item.getTitle());
                return true;
            });
            popup_sidemenu.show();
        });

        btn_search.setOnClickListener(v -> {
            searchDialog.dismiss();
            getDataSearch(tv_start_date.getText().toString().trim(),
                    tv_end_date.getText().toString().trim(),
                    leg, status, et_name.getText().toString().trim());
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