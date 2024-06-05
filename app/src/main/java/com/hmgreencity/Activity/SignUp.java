package com.hmgreencity.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.JsonObject;
import com.hmgreencity.R;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.constants.BaseActivity;
import com.hmgreencity.model.request.RequestSignup;
import com.hmgreencity.model.response.ResponseSignup;
import com.hmgreencity.model.response.ResponseSponsorName;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends BaseActivity {

    @BindView(R.id.et_sponcer_id)
    EditText et_sponcer_id;
    @BindView(R.id.et_first_name)
    EditText et_first_name;
    @BindView(R.id.et_last_name)
    EditText et_last_name;
    @BindView(R.id.et_mobile)
    EditText et_mobile;
    @BindView(R.id.sponsor_name)
    TextView sponsor_name;
    @BindView(R.id.rg_leg)
    RadioGroup rg_leg;
    @BindView(R.id.rg_gender)
    RadioGroup rg_gender;

    String leg = "L", gender = "Male";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        rg_gender.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_female)
                gender = "Female";
            else gender = "Male";
        });

        rg_leg.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_left)
                leg = "L";
            else leg = "R";
        });

        et_sponcer_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_sponcer_id.getText().toString().length() >= 6) {
                    GetSponsorName(et_sponcer_id.getText().toString().trim());
                } else {
                    sponsor_name.setText("");
                }
            }
        });
    }

    @OnClick({R.id.btn_register, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                if (NetworkUtils.getConnectivityStatus(context) != 0) {
                    if (validate())
                        getRegistered();
                } else showMessage(R.string.alert_internet);
                break;
            case R.id.tv_login:
                goToActivityWithFinish(context, Login.class, null);
                break;
        }
    }

    private boolean validate() {
        if (sponsor_name.getText().toString().trim().length() == 0) {
            et_sponcer_id.setError("Enter Sponsor Id");
            return false;
        } else if (et_first_name.getText().toString().trim().length() == 0) {
            et_first_name.setError("Enter First Name");
            return false;
        } else if (et_last_name.getText().toString().trim().length() == 0) {
            et_last_name.setError("Enter Last Name");
            return false;
        } else if (et_mobile.getText().toString().trim().length() != 10) {
            et_mobile.setError("Enter valid mobile number");
            return false;
        }
        return true;
    }

    private void getRegistered() {
        showLoading();
        hideKeyboard();
        RequestSignup signup = new RequestSignup();
        signup.setFirstName(et_first_name.getText().toString().trim());
        signup.setLastName(et_last_name.getText().toString());
        signup.setSponsorId(et_sponcer_id.getText().toString().trim());
        signup.setGender(gender);
        signup.setMobileNo(et_mobile.getText().toString());
        signup.setLeg(leg);
        signup.setAddress("");
        signup.setEmail("");
        signup.setPanCard("");
        signup.setPinCode("");
        signup.setRegistrationBy("mobile");

        LoggerUtil.logItem(signup);
        Call<ResponseSignup> call = apiServices.Registration(signup);
        call.enqueue(new Callback<ResponseSignup>() {
            @Override
            public void onResponse(Call<ResponseSignup> call, Response<ResponseSignup> response) {
                hideLoading();
                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatus().equalsIgnoreCase("0")) {
                        createInfoDialog(context, "Registered Successfully", "Dear, " + response.body().getFullName() +
                                ", You are successfully registered with HM Green City. Your\nLogin Id : " +
                                response.body().getLoginId() + "\nPassword : " + response.body().getPassword() +
                                "\nCredentials will be sent on your mobile number.", true);
                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {
                    showMessage("Server Issue");
                }
            }

            @Override
            public void onFailure(Call<ResponseSignup> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void GetSponsorName(String sponsorId) {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("sponsorId", sponsorId);

        LoggerUtil.logItem(object);
        Call<ResponseSponsorName> call = apiServices.GetSponsorName(object);
        call.enqueue(new Callback<ResponseSponsorName>() {
            @Override
            public void onResponse(Call<ResponseSponsorName> call, Response<ResponseSponsorName> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatus().equalsIgnoreCase("0"))
                    sponsor_name.setText(response.body().getSponsorName());
                else {
                    showMessage(response.body().getMessage());
                    sponsor_name.setText("");
                }
            }

            @Override
            public void onFailure(Call<ResponseSponsorName> call, Throwable t) {
                hideLoading();
            }
        });
    }
}