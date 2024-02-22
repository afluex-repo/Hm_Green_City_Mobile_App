package com.hmgreencity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hmgreencity.Activity.ContainerActivity;
import com.hmgreencity.R;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.request.RequestChangePassword;
import com.hmgreencity.model.response.ResponseChangePassword;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.et_old_pswd)
    EditText etOldPswd;
    @BindView(R.id.et_new_pswd)
    EditText etNewPswd;
    @BindView(R.id.et_confrm_pswd)
    EditText etConfrmPswd;
    @BindView(R.id.btn_login)
    Button btn_login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_password, container, false);
        unbinder = ButterKnife.bind(this, view);

        btn_login.setOnClickListener(v -> {
            if (NetworkUtils.getConnectivityStatus(context) != 0) {
                if (Validation())
                    getPasswordChange();
            } else showMessage(R.string.alert_internet);
        });
        return view;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private void getPasswordChange() {
        showLoading();
        hideKeyboard();
        RequestChangePassword requestChangePswd = new RequestChangePassword();
        requestChangePswd.setPkParentId(PreferencesManager.getInstance(context).getUserId());
        requestChangePswd.setOldPassword(etOldPswd.getText().toString().trim());
        requestChangePswd.setNewPassword(etNewPswd.getText().toString().trim());
        requestChangePswd.setPasswordType("P");

        LoggerUtil.logItem(requestChangePswd);
        Call<ResponseChangePassword> call = apiServices.ChangePassword(requestChangePswd);
        call.enqueue(new Callback<ResponseChangePassword>() {
            @Override
            public void onResponse(@NonNull Call<ResponseChangePassword> call, @NonNull Response<ResponseChangePassword> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("0")) {
                        showMessage("Password Changed Successfully");
                        ((ContainerActivity) context).ReplaceFragment(new Dashboard(), "Dashboard");
                    } else {
                        showMessage(response.body().getErrorMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseChangePassword> call, @NonNull Throwable t) {
                hideLoading();
                showMessage("Something went wrong");
            }
        });
    }

    private boolean Validation() {
        if (etOldPswd.getText().toString().length() == 0) {
            etOldPswd.setError("Please enter old password");
            return false;
        } else if (etNewPswd.getText().toString().length() == 0) {
            etNewPswd.setError("Please enter new password");
            return false;
        } else if (etNewPswd.getText().toString().trim().equals(etOldPswd.getText().toString().trim())) {
            etNewPswd.setError("New Password could not be same as old password");
            return false;
        } else if (!etNewPswd.getText().toString().equals(etConfrmPswd.getText().toString())) {
            etConfrmPswd.setError("Password not matched");
            etNewPswd.setError("Password not matched");
            return false;
        }
        return true;
    }
}
