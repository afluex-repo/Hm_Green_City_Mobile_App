package com.hmgreencity.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.hmgreencity.R;
import com.hmgreencity.constants.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPassword extends BaseActivity {
    @BindView(R.id.et_forgot_password)
    EditText etForgotPassword;
    @BindView(R.id.btn_forgot_password)
    Button btnForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_forgot_password)
    public void onViewClicked() {
        goToActivityWithFinish(context,OTP.class,null);
    }
}
