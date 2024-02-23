package com.hmgreencity.fragment;

import static com.hmgreencity.common.Utils.showMessage;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.hmgreencity.R;
import com.hmgreencity.adapter.TopUpListAdopter;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.request.RequestTopUpList;
import com.hmgreencity.model.response.responseTopUpList.ResponseTopUpList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopUpListNew extends BaseFragment {


    private Unbinder unbinder;

    com.hmgreencity.databinding.FragmentTopUpListNewBinding binding;


    String toDate,fromDate;

    private final Calendar myCalendar = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= com.hmgreencity.databinding.FragmentTopUpListNewBinding.inflate(inflater,container,false);
        binding.recyclerview1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerview1.setLayoutManager(layoutManager);


        DatePickerDialog.OnDateSetListener date = (view1, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "dd/MM/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            fromDate = sdf.format(myCalendar.getTime());
            Log.e("Date", "From:" + fromDate);
            updateLabel(binding.textFromDate);


        };

        DatePickerDialog.OnDateSetListener dateFrom = (view1, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "dd/MM/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            fromDate = sdf.format(myCalendar.getTime());
            Log.e("Date", "From:" + fromDate);
            updateLabel(binding.textToDate);


        };

        binding.textFromDate.setOnClickListener((View v) -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
//            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 86400000);
            datePickerDialog.show();
            String myFormat = "dd/MM/yyyy"; //In which you need put here

        });
        binding.textToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), dateFrom, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                String myFormat = "dd/MM/yyyy"; //In which you need put here
            }
        });


        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(binding.textToDate.getText().toString(),binding.textFromDate.getText().toString());
            }
        });



        if (NetworkUtils.getConnectivityStatus(context) != 0)
            getData("","");
        else showMessage(R.string.alert_internet);

        return binding.getRoot();
    }
    public void getData(String to, String from) {
        showLoading();
        RequestTopUpList requestTopUpList = new RequestTopUpList();

        requestTopUpList.setLoginId(PreferencesManager.getInstance(context).getLoginId());

        Log.e("asfasfaf",PreferencesManager.getInstance(context).getLoginId());
        requestTopUpList.setToDate(to);
        requestTopUpList.setFromDate(from);
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
                    binding.recyclerview1.setAdapter(adapter);
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseTopUpList> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void updateLabel(EditText fromdateEt) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        fromdateEt.setText(sdf.format(myCalendar.getTime()));
    }
}