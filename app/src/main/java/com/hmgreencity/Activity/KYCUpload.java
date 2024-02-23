package com.hmgreencity.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.hmgreencity.BuildConfig;
import com.hmgreencity.R;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.constants.BaseActivity;
import com.hmgreencity.databinding.ActivityKycUploadBinding;
import com.hmgreencity.model.request.RequestKYC;
import com.hmgreencity.model.request.RequestPayoutLedger;
import com.hmgreencity.model.response.ResponseKYc;
import com.hmgreencity.model.response.lstKycDocuments;
import com.hmgreencity.model.response.responsePayoutLedger.ResponsePayoutLedger;
import com.squareup.picasso.Picasso;

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
    int aadhar_front_request_code=100;
    int aadhar_back_request_code=101;
    int pan_request_code=102;
    int document_request_code=103;

    Uri aadhar_front,aadhar_back,pan,document;

    private int camera = 1, gallery = 2, flag = 0;
    String profile;

    ActivityKycUploadBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityKycUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ButterKnife.bind(this);

        tvTitle.setText("Upload KYC");
        binding.txtUserId.setText(PreferencesManager.getInstance(context).getLoginId());
        
        
        getKycList();
        
        binding.btnAdhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                permissionCheck(aadhar_front_request_code);
            }
        });

        binding.btnAdharBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionCheck(aadhar_back_request_code);
            }
        });

        binding.btnPan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionCheck(pan_request_code);
            }
        });
        binding.btnDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionCheck(document_request_code);
            }
        });

        binding.btnUplodeKyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(binding.tvAdharNumber.getText().toString())){
                    Toast.makeText(context, "Enter Aadhar Number", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(binding.tvPanNumber.getText().toString())){
                    Toast.makeText(context, "Enter Pan Number", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(binding.tvDocumentNumber.getText().toString())){
                    Toast.makeText(context, "Enter document Number", Toast.LENGTH_SHORT).show();
                }else{
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

    private void uploadKyC() {

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("Pk_UserID",  PreferencesManager.getInstance(context).getUserId())
                .addFormDataPart("DocumentNumber",binding.tvDocumentNumber.getText().toString())
                .addFormDataPart("PanNumber",binding.tvPanNumber.getText().toString())
                .addFormDataPart("AdharNumber", binding.tvAdharNumber.getText().toString())
                .addFormDataPart("AadharFile",aadhar_front.getPath(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(aadhar_front.getPath())))
                .addFormDataPart("PanFile",pan.getPath(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(pan.getPath())))
                .addFormDataPart("DocumentFile",document.getPath(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(document.getPath())))
                .addFormDataPart("AdharBacksideFile",aadhar_back.getPath(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(aadhar_back.getPath())))
                .build();

        Call<ResponseKYc> call = apiServices.UploadKyc(body);


        call.enqueue(new Callback<ResponseKYc>() {
            @Override
            public void onResponse(Call<ResponseKYc> call, Response<ResponseKYc> response) {

                if(response.isSuccessful()){
                    Toast.makeText(KYCUpload.this,response.body().getSuccessMessage(),Toast.LENGTH_LONG).show();
                }else{
                   

                    Toast.makeText((Context) KYCUpload.this, (CharSequence) response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseKYc> call, Throwable t) {

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

                    binding.tvStatus.setText("Status :"+lstKycDocuments.getAdharStatus());
                    binding.tvPanStatus.setText("Status :"+lstKycDocuments.getPanStatus());
                    binding.tvDocumentStatus.setText("Status :"+lstKycDocuments.getDocumentStatus());



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

    private void permissionCheck(int request_code) {
        boolean result = ContextCompat.checkSelfPermission(KYCUpload.this,
                Manifest.permission.READ_MEDIA_IMAGES) == (PackageManager.PERMISSION_GRANTED);

        if(result){
            if(request_code==aadhar_front_request_code){
                chooseImage();
            }else  if(request_code==aadhar_back_request_code){
                chooseAadharBackImage();
            }else  if(request_code==pan_request_code){
                choosePanImage();
            }else  if(request_code==document_request_code){
                chooseDocumentImage();
            }


        }else{
            ActivityCompat.requestPermissions(this,
                    permissions(),
                    1);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("MKOMkon",""+grantResults[0]);
        switch (requestCode) {


            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chooseImage();
                } else {
                    Toast.makeText(KYCUpload.this, "" + grantResults[0], Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }
    public static String[] storage_permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static String[] storage_permissions_33 = {
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_VIDEO
    };





        public static String[] permissions() {
            String[] p;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                p = storage_permissions_33;
            } else {
                p = storage_permissions;
            }
            return p;
        }




    private void chooseDocumentImage() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),document_request_code);
    }

    private void choosePanImage() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),pan_request_code);

    }

    private void chooseAadharBackImage() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),aadhar_back_request_code);
    }

    private void chooseImage() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),aadhar_front_request_code);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri imgUri = data.getData();
        Uri imageUri=data.getData();
        if (requestCode == aadhar_front_request_code && resultCode == RESULT_OK) {
            aadhar_front=imageUri;
          
            binding.imagAdhar.setImageURI(aadhar_front);
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            Bitmap image_uri = imageBitmap;
//            binding.imagAdhar.setImageBitmap(image_uri);
//            fileName = RealPathUtil.getRealPath(context, imgUri);





        }
        else if (requestCode == aadhar_back_request_code && resultCode == RESULT_OK) {
         aadhar_back=imageUri;
            binding.imagAdharBack.setImageURI(aadhar_back);
//            fileName = RealPathUtil.getRealPath(context, imgUri);





        }
        else if (requestCode == pan_request_code && resultCode == RESULT_OK) {
            pan=imageUri;
            binding.imagPan.setImageURI(pan);
//            fileName = RealPathUtil.getRealPath(context, imgUri);





        }
        else if (requestCode == document_request_code && resultCode == RESULT_OK) {
            document=imageUri;
          
            binding.imagDocument.setImageURI(document);
//            fileName = RealPathUtil.getRealPath(context, imgUri);





        }
    }
}

