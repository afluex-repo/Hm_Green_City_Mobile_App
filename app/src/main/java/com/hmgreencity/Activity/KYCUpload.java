package com.hmgreencity.Activity;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.hmgreencity.BuildConfig;
import com.hmgreencity.R;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.constants.BaseActivity;
import com.hmgreencity.databinding.ActivityKycUploadBinding;
import com.hmgreencity.model.request.RequestKYC;
import com.hmgreencity.model.response.ResponseKYc;
import com.hmgreencity.model.response.lstKycDocuments;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.File;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class KYCUpload extends BaseActivity {

    @BindView(R.id.tv_adhar_number)
    EditText tvAdharNumber;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_pan_number)
    EditText tvPanNumber;
    @BindView(R.id.tv_pan_status)
    TextView tvPanStatus;
    @BindView(R.id.tv_document_number)
    EditText tvDocumentNumber;
    @BindView(R.id.tv_document_status)
    TextView tvDocumentStatus;
    @BindView(R.id.btn_adhar)
    Button btnAdhar;
    @BindView(R.id.imag_adhar)
    ImageView imagAdhar;
    @BindView(R.id.btn_pan)
    Button btnPan;
    @BindView(R.id.imag_pan)
    ImageView imagPan;
    @BindView(R.id.btn_document)
    Button btnDocument;
    @BindView(R.id.imag_document)
    ImageView imagDocument;
    @BindView(R.id.btn_uplode_kyc)
    Button btnUplodeAdhar;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.txt_userId)
    TextView txt_userId;

    private static final int aadhar_front_request_code = 100;
    private static final int aadhar_back_request_code = 101;
    private static final int pan_request_code = 102;
    private static final int document_request_code = 103;

    private String aadharFrontPath, aadharBackPath, panPath, documentPath, selectedImagePath;

    private Uri aadhar_front, aadhar_back, pan, document;

    private int camera = 1, gallery = 2, flag = 0;
    private int currentRequestCode;  // To track which button was pressed
    String profile;
    // for zoom
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;

    ActivityKycUploadBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKycUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ButterKnife.bind(this);

        tvTitle.setText("Upload KYC");
        binding.txtUserId.setText(PreferencesManager.getInstance(context).getLoginId());

        getKycList();
        onclicklistener();


        binding.btnAdhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRequestCode = aadhar_front_request_code;
                permissionCheck();
            }
        });

        binding.btnAdharBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRequestCode = aadhar_back_request_code;
                permissionCheck();
            }
        });

        binding.btnPan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRequestCode = pan_request_code;
                permissionCheck();
            }
        });

        binding.btnDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRequestCode = document_request_code;
                permissionCheck();
            }
        });

        binding.btnUplodeKyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.tvAdharNumber.getText().toString())) {
                    Toast.makeText(context, "Enter Aadhar Number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.tvPanNumber.getText().toString())) {
                    Toast.makeText(context, "Enter Pan Number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.tvDocumentNumber.getText().toString())) {
                    Toast.makeText(context, "Enter document Number", Toast.LENGTH_SHORT).show();
                } else {
                    uploadKyC();
                }
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivityWithFinish(context, ContainerActivity.class, null);
            }
        });
    }

    private void onclicklistener() {
        binding.imagAdhar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });

        binding.imagAdharBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });
        binding.imagPan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });

        binding.imagDocument.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    private void uploadKyC() {
        Log.e("Tanishq", "AF" + aadharFrontPath);
        Log.e("Tanishq", "AB" + aadharBackPath);
        Log.e("Tanishq", "PP" + panPath);
        Log.e("Tanishq", "DP" + documentPath);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("Pk_UserID", PreferencesManager.getInstance(context).getUserId())
                .addFormDataPart("DocumentNumber", binding.tvDocumentNumber.getText().toString())
                .addFormDataPart("PanNumber", binding.tvPanNumber.getText().toString())
                .addFormDataPart("AdharNumber", binding.tvAdharNumber.getText().toString())
                .addFormDataPart("AccountHolderName", binding.tvAccountHolder.getText().toString())
                .addFormDataPart("BankName", binding.tvBankName.getText().toString())
                .addFormDataPart("IFSCCode", binding.tvIfscCode.getText().toString())
                .addFormDataPart("BankBranch", binding.tvBankBranch.getText().toString())

                .addFormDataPart("AdharImage", aadharFrontPath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(aadharFrontPath)))
                .addFormDataPart("PanImage", panPath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(panPath)))
                .addFormDataPart("DocumentImage", documentPath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(documentPath)))
                .addFormDataPart("AdharBacksideImage", aadharBackPath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(aadharBackPath)))
                .build();

        Call<ResponseKYc> call = apiServices.UploadKyc(body);

        call.enqueue(new Callback<ResponseKYc>() {
            @Override
            public void onResponse(Call<ResponseKYc> call, Response<ResponseKYc> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(KYCUpload.this, "Document Uploaded Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText((Context) KYCUpload.this, (CharSequence) response.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("Hygdygg", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseKYc> call, Throwable t) {
                Log.e("Rejbjkab", t.toString());
            }
        });
    }

    private void getKycList() {
        RequestKYC object = new RequestKYC();
        object.setPk_UserID( PreferencesManager.getInstance(context).getUserId());
        LoggerUtil.logItem(object);
        Call<ResponseKYc> call = apiServices.GetKYCList(object);

        call.enqueue(new Callback<ResponseKYc>() {
            @Override
            public void onResponse(Call<ResponseKYc> call, Response<ResponseKYc> response) {

                Log.e("dfssg",""+response.isSuccessful());
                if(response.isSuccessful()){
                    lstKycDocuments  lstKycDocuments=response.body().getLstKycdocuments().get(0);
                    binding.tvAdharNumber.setText(lstKycDocuments.getAdharNumber());
                    binding.tvDocumentNumber.setText(lstKycDocuments.getDocumentNumber());
                    binding.tvPanNumber.setText(lstKycDocuments.getPanNumber());
                    binding.tvAccountHolder.setText(lstKycDocuments.getBankBranch());
                    binding.tvBankBranch.setText(lstKycDocuments.getBankBranch());
                    binding.tvBankName.setText(lstKycDocuments.getBankName());
                    binding.tvIfscCode.setText(lstKycDocuments.getIFSCCode());

                    binding.tvStatus.setText("Status :"+lstKycDocuments.getAdharStatus());
                    binding.tvPanStatus.setText("Status :"+lstKycDocuments.getPanStatus());
                    binding.tvDocumentStatus.setText("Status :"+lstKycDocuments.getDocumentStatus());
                    if(lstKycDocuments.getDocumentStatus().equals("Approved")){
                        binding.btnAdharBack.setVisibility(View.GONE);
                        binding.btnAdhar.setVisibility(View.GONE);
                        binding.btnPan.setVisibility(View.GONE);
                        binding.btnDocument.setVisibility(View.GONE);
                        binding.btnUplodeKyc.setVisibility(View.GONE);
                        binding.tvDocumentNumber.setFocusable(false);
                        binding.tvAdharNumber.setFocusable(false);
                        binding.tvPanNumber.setFocusable(false);
                        binding.tvAccountHolder.setFocusable(false);
                        binding.tvBankBranch.setFocusable(false);
                        binding.tvBankName.setFocusable(false);
                        binding.tvIfscCode.setFocusable(false);

                    }else{
                        binding.btnAdharBack.setVisibility(View.VISIBLE);
                        binding.btnAdhar.setVisibility(View.VISIBLE);
                        binding.btnPan.setVisibility(View.VISIBLE);
                        binding.btnDocument.setVisibility(View.VISIBLE);
                        binding.btnUplodeKyc.setVisibility(View.VISIBLE);

                        binding.tvDocumentNumber.setFocusable(true);
                        binding.tvAdharNumber.setFocusable(true);
                        binding.tvPanNumber.setFocusable(true);
                        binding.tvAccountHolder.setFocusable(true);
                        binding.tvBankName.setFocusable(true);
                        binding.tvBankBranch.setFocusable(true);
                        binding.tvIfscCode.setFocusable(true);
                    }

                    if(lstKycDocuments.getAdharStatus().equals("Not Uploaded")){

                    }
                    Log.e("d;smdkld",lstKycDocuments.getAdharImage());

                    Picasso.with(KYCUpload.this).load(BuildConfig.BASE_URL + lstKycDocuments.getAdharImage().substring(1)) .placeholder(R.drawable.box_bg).into(binding.imagAdhar);
                    Picasso.with(KYCUpload.this).load(BuildConfig.BASE_URL + lstKycDocuments.getPanImage().substring(1)).placeholder(R.drawable.box_bg).into(binding.imagPan);
                    Picasso.with(KYCUpload.this).load(BuildConfig.BASE_URL + lstKycDocuments.getDocumentImage().substring(1)).placeholder(R.drawable.box_bg).into(binding.imagDocument);
                    Picasso.with(KYCUpload.this).load(BuildConfig.BASE_URL + lstKycDocuments.getAdharBacksideImage().substring(1)).placeholder(R.drawable.box_bg).into(binding.imagAdharBack);

                }
            }

            @Override
            public void onFailure(Call<ResponseKYc> call, Throwable t) {

            }
        });
    }


    public void permissionCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(KYCUpload.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(KYCUpload.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(KYCUpload.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(KYCUpload.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
            } else {
                selectImage();
            }
        } else {
            selectImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectImage();
        }
    }

    private void selectImage() {
        Intent intent = CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .getIntent(KYCUpload.this);
        startActivityForResult(intent, currentRequestCode);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (result != null) {
                Uri uri = result.getUri();
                if (uri != null) {
                    selectedImagePath = uri.getPath();
                    Log.d("ImageUpload", "Selected image path: " + selectedImagePath); // Add this log

                    if (requestCode == aadhar_front_request_code) {
                        aadharFrontPath = selectedImagePath;
                        Log.d("ImageUpload", "Aadhar Front Path: " + aadharFrontPath); // Add this log
                        loadImageIntoImageView(aadharFrontPath, binding.imagAdhar);
                    } else if (requestCode == aadhar_back_request_code) {
                        aadharBackPath = selectedImagePath;
                        loadImageIntoImageView(aadharBackPath, binding.imagAdharBack);
                    } else if (requestCode == pan_request_code) {
                        panPath = selectedImagePath;
                        loadImageIntoImageView(panPath, binding.imagPan);
                    } else if (requestCode == document_request_code) {
                        documentPath = selectedImagePath;
                        loadImageIntoImageView(documentPath, binding.imagDocument);
                    }
                } else {
                    Toast.makeText(this, "Failed to get cropped image URI", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Failed to crop image", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void loadImageIntoImageView(String imagePath, ImageView imageView) {
        if (!TextUtils.isEmpty(imagePath)) {
            Picasso.with(context)
                    .load(new File(imagePath))
                    .placeholder(R.drawable.bg) // Placeholder image while loading
                    .error(R.drawable.camera) // Image to display in case of loading error
                    .into(imageView);
        } else {
            Toast.makeText(this, "Image path is empty", Toast.LENGTH_SHORT).show();
        }
    }

    //for zoom image

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            imagAdhar.setScaleX(mScaleFactor);
            imagAdhar.setScaleY(mScaleFactor);
            return true;
        }
    }

}