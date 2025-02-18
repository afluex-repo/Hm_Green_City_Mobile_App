package com.hmgreencity.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.hmgreencity.R;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.constants.BaseActivity;
import com.hmgreencity.model.request.RequestLogin;
import com.hmgreencity.model.response.ResponseLogin;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends BaseActivity {

    @BindView(R.id.et_Username)
    EditText etUsername;
    @BindView(R.id.et_Password)
    EditText etPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.tv_forgot_password, R.id.btn_login, R.id.tv_gotoRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forgot_password:
                goToActivity(context, ForgotPassword.class, null);
                break;
            case R.id.btn_login:
                getLogin();
               /* if (NetworkUtils.getConnectivityStatus(context) != 0) {
                    if (validate())

                } else showMessage(R.string.alert_internet);*/
                break;
            case R.id.tv_gotoRegister:
                goToActivity(context, SignUp.class, null);
                break;
        }
    }


    private boolean validate() {
        if (etUsername.getText().toString().trim().length() == 0) {
            etUsername.setError("Enter Username");
            return false;
        } else if (etPassword.getText().toString().trim().length() == 0) {
            etPassword.setError("Enter Password");
            return false;
        }
        return true;
    }

    private void getLogin() {
        showLoading();
        hideKeyboard();
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setLoginId(etUsername.getText().toString().trim());
        requestLogin.setPassword(etPassword.getText().toString().trim());
        LoggerUtil.logItem(requestLogin);
        Call<ResponseLogin> call = apiServices.getLogin(requestLogin);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                hideLoading();
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    PreferencesManager.getInstance(context).setUserId(response.body().getUserId());
                    PreferencesManager.getInstance(context).setFull_Name(response.body().getFullName());
                    PreferencesManager.getInstance(context).setUserType(response.body().getUserType());
                    PreferencesManager.getInstance(context).setProfilePic(response.body().getProfile());
                    PreferencesManager.getInstance(context).setLoginId(response.body().getLoginId());
                    goToActivityWithFinish(context, ContainerActivity.class, null);
                    showMessage(response.body().getMessage());
                } else showMessage(response.body().getMessage());
            }
            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                hideLoading();
                Toast.makeText(Login.this, t+"", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}