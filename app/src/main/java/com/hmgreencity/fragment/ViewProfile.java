package com.hmgreencity.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonObject;
import com.hmgreencity.Activity.EditProfile;
import com.hmgreencity.BuildConfig;
import com.hmgreencity.R;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.response.ResponseViewProfile;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProfile extends BaseFragment {

    @BindView(R.id.img_member)
    ImageView imgMember;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_sponsor_id)
    TextView tvSponsorId;
    @BindView(R.id.tv_sponsor_name)
    TextView tvSponsorName;
    @BindView(R.id.tv_join_date)
    TextView tvJoinDate;
    @BindView(R.id.tv_leg)
    TextView tvLeg;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    Unbinder unbinder;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_account_number)
    TextView tvAccountNumber;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_branch_name)
    TextView tvBranchName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewprofile, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkUtils.getConnectivityStatus(context) != 0)
            getViewProfile();
        else showMessage(R.string.alert_internet);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private void getViewProfile() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
        LoggerUtil.logItem(object);

        Call<ResponseViewProfile> call = apiServices.ViewProfiles(object);

        Log.e("JKBUfytf",BuildConfig.BASE_URL);
        call.enqueue(new Callback<ResponseViewProfile>() {
            @Override
            public void onResponse(Call<ResponseViewProfile> call, Response<ResponseViewProfile> response) {
                hideLoading();

                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    tvName.setText(response.body().getFirstName() + " " + response.body().getLastName());
                    tvContact.setText(response.body().getMobile());
                    tvSponsorId.setText(response.body().getSponsorId());
                    tvEmail.setText(response.body().getEmailId());
                    tvAccountNumber.setText(response.body().getAccountNumber());
                    tvBankName.setText(response.body().getBankName());
                    tvSponsorName.setText(response.body().getSponsorName());
                    tvJoinDate.setText(response.body().getJoiningDate());
                    tvLeg.setText(response.body().getLeg());
                    tvStatus.setText(response.body().getStatus());

                    tvUserId.setText(PreferencesManager.getInstance(context).getLoginId());
                    //.setText(response.body().getIFSC());
                    // Picasso.with(context).load("http://admin.hmgreencity.com/".getProfilePicture()).into(img);

                    Picasso.with(context).load(BuildConfig.BASE_URL + response.body().getProfilePicture()).into(imgMember);

                    tvBranchName.setText(response.body().getBankBranch());

                } else {
                    showMessage("Server Error");}
            }

            @Override
            public void onFailure(Call<ResponseViewProfile> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @OnClick(R.id.btn_edit)
    public void onViewClicked() {
        goToActivity(EditProfile.class, null);
    }
}

