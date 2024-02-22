package com.hmgreencity.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.hmgreencity.R;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.FileUtils;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.common.NetworkUtils;
import com.hmgreencity.common.Utils;
import com.hmgreencity.constants.BaseActivity;
import com.hmgreencity.fragment.ChangePassword;
import com.hmgreencity.model.request.RequestUpdateProfile;
import com.hmgreencity.model.response.ResponseUpdateProfile;
import com.hmgreencity.model.response.ResponseViewProfile;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends BaseActivity implements IPickCancel, IPickResult {
    @BindView(R.id.img_member)
    ImageView imgMember;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_mem_id)
    TextView tvMemId;
    @BindView(R.id.tv_contact)
    EditText tvContact;
    @BindView(R.id.tv_email)
    EditText tvEmail;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.tv_account_number)
    EditText tvAccountNumber;
    @BindView(R.id.tv_bank_name)
    EditText tvBankName;
    @BindView(R.id.tv_branch_name)
    EditText tvBranchName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_ifsc)
    EditText tvIfsc;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.tv_lastname)
    TextView tvLastname;


    @BindView(R.id.txt_userId)
    TextView txt_userId;

    @BindView(R.id.img_settings)
    ImageView img_settings;
    @BindView(R.id.ll_dashboard)
    LinearLayout ll_dashboard;
    @BindView(R.id.ll_not_dashboard)
    LinearLayout ll_not_dashboard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ButterKnife.bind(this);
        tvTitle.setText("Edit Profle");

        if (NetworkUtils.getConnectivityStatus(context) != 0)
            getViewProfile();
        else showMessage(R.string.alert_internet);


    }


    private Fragment currentFragment;

    public void ReplaceFragment(Fragment setFragment, String title) {
        new Handler().postDelayed(() -> {
            currentFragment = setFragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame, setFragment, title);
            tvTitle.setText(title);
            if(title.equals("Dashboard")){
                ll_dashboard.setVisibility(View.VISIBLE);
                ll_not_dashboard.setVisibility(View.GONE);
            }else{
                ll_dashboard.setVisibility(View.GONE);
                ll_not_dashboard.setVisibility(View.VISIBLE);
            }

            transaction.commitAllowingStateLoss();
        }, 200);
    }

    private void getViewProfile() {

        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getLoginId());
        LoggerUtil.logItem(object);

        Call<ResponseViewProfile> call = apiServices.ViewProfiles(object);
        call.enqueue(new Callback<ResponseViewProfile>() {
            @Override
            public void onResponse(Call<ResponseViewProfile> call, Response<ResponseViewProfile> response) {
                hideLoading();

                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    tvName.setText(response.body().getFirstName());
                    tvLastname.setText(response.body().getLastName());

                    tvContact.setText(response.body().getMobile());
                    tvMemId.setText(response.body().getLoginId());
                    // tvSponsorId.setText(response.body().getSponsorId());
                    tvEmail.setText(response.body().getEmailId());
                    tvAccountNumber.setText(response.body().getAccountNumber());
                    tvBankName.setText(response.body().getBankName());
                    // tvSponsorName.setText(response.body().getSponsorName());
                    // tvJoinDate.setText(response.body().getJoiningDate());
                    tvIfsc.setText(response.body().getIFSC());
                    tvBranchName.setText(response.body().getBankBranch());

                } else showMessage(response.body().getMessage());
            }


            @Override
            public void onFailure(Call<ResponseViewProfile> call, Throwable t) {
                hideLoading();
            }
        });
    }

    public void getUpdateProfile() {
        showLoading();
        RequestUpdateProfile object = new RequestUpdateProfile();
        object.setAccountNumber(tvAccountNumber.getText().toString().trim());
        object.setBankBranch(tvBranchName.getText().toString().trim());
        object.setBankName(tvBankName.getText().toString().trim());
        object.setEmailId(tvEmail.getText().toString().trim());
        object.setFirstName(tvName.getText().toString().trim());
        object.setLastName(tvLastname.getText().toString().trim());
        object.setIFSC(tvIfsc.getText().toString().trim());
        object.setMobile(tvContact.getText().toString());
        object.setPKUserId(PreferencesManager.getInstance(context).getUserId());

        LoggerUtil.logItem(object);

        Call<ResponseUpdateProfile> call = apiServices.getUpdateProfiles(object);
        call.enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                hideLoading();
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    onBackPressed();
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {
                hideLoading();
            }
        });


    }

    @OnClick({R.id.img_member, R.id.btn_update, R.id.img_back,R.id.img_settings})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_member:
                showDialog();
                break;
            case R.id.btn_update:
                getUpdateProfile();
                break;
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.img_settings:
                ReplaceFragment(new ChangePassword(),"Change Password");
        }
    }

    PickImageDialog dialog;

    void showDialog() {
        PickSetup pickSetup = new PickSetup();
        pickSetup.setTitle("Select Image");
        pickSetup.setPickTypes(EPickType.GALLERY, EPickType.CAMERA);
        pickSetup.setGalleryIcon(com.vansuita.pickimage.R.mipmap.gallery_colored);
        pickSetup.setCameraIcon(com.vansuita.pickimage.R.mipmap.camera_colored);
        pickSetup.setCancelTextColor(R.color.colorAccent);
        // If show system dialog..
//        pickSetup.setSystemDialog(true);

        dialog = PickImageDialog.build(pickSetup);
        dialog.setOnPickCancel(this);
        dialog.show(getSupportFragmentManager());
    }

    @Override
    public void onPickResult(PickResult pickResult) {
        Log.e("RESULT", "= " + pickResult.getPath());
        if (pickResult.getError() == null) {
            CropImage.activity(pickResult.getUri()).setCropShape(CropImageView.CropShape.RECTANGLE)
                    .start(context);
        } else {
            Log.e("RESULT", "ERROR = " + pickResult.getError());
        }
    }

    File homeWorkFile, compressedImageFile;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imgMember.setImageBitmap(result.getBitmap());
                homeWorkFile = FileUtils.getFile(context, result.getUri());
                compressedImageFile = new Compressor.Builder(this)
                        .setMaxWidth(800)
                        .setMaxHeight(640)
                        .setQuality(100)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .build()
                        .compressToFile(homeWorkFile);
                uploadProfilePic();
                Log.e("ADDRESS File ", homeWorkFile.toString());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                        Exception error = result.getError();
            }
        }
    }

    @Override
    public void onCancelClick() {
    }

    private void uploadProfilePic() {
        LoggerUtil.logItem(compressedImageFile.length());
        showLoading();
        RequestBody requestBody;
        MultipartBody.Part body = null;
        requestBody = RequestBody.create(MediaType.parse("image/*"), compressedImageFile);
        body = MultipartBody.Part.createFormData("ImageURL", compressedImageFile.getName(), requestBody);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getUserId());

        Call<JsonObject> call = apiServices.uploadProfilePic(userId, body);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                LoggerUtil.logItem(t.getMessage());
                hideLoading();
            }
        });
    }
}