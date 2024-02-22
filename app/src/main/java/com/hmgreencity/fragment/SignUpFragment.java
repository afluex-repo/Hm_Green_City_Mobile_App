package com.hmgreencity.fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.hmgreencity.Activity.ContainerActivity;
import com.hmgreencity.R;
import com.hmgreencity.adapter.AdapterSponsor;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.databinding.FragmentSignUpBinding;
import com.hmgreencity.model.ModelSponserList;
import com.hmgreencity.model.response.ResponsePinCode;
import com.hmgreencity.model.response.ResponseRegistration;
import com.hmgreencity.model.response.ResponseSponsorList;
import com.hmgreencity.retrofit.ApiServices;
import com.hmgreencity.retrofit.ServiceGenerator;

import java.util.ArrayList;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpFragment extends Fragment {

    FragmentSignUpBinding binding;

    private ArrayList<ModelSponserList> sponserListArrayList;
    private ArrayList<ModelSponserList> filtersponserListArrayList;


    public ApiServices apiServices, createServiceUtilityV2;

    android.app.AlertDialog.Builder alert = null;
    android.app.AlertDialog alertDialog = null;

    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding = FragmentSignUpBinding.inflate(inflater, container, false);

        apiServices = ServiceGenerator.createService(ApiServices.class);

        binding.etSponsorId.setText(PreferencesManager.getInstance(getActivity()).getLoginId());
        binding.etSponsorName.setText(PreferencesManager.getInstance(getActivity()).getFull_Name());


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        sponserListArrayList = new ArrayList<>();

        filtersponserListArrayList = new ArrayList<>();

        binding.btnConfirm.setVisibility(View.VISIBLE);
        binding.btnEdit.setVisibility(View.GONE);
        binding.btnRegister.setVisibility(View.GONE);
        binding.rbLeft.setChecked(true);
        binding.rbRight.setChecked(false);

        getSponsorList();

        binding.rbLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.rbRight.isChecked()) {
                    binding.rbRight.setChecked(false);
                    binding.rbLeft.setChecked(true);
                } else {
                    binding.rbRight.setChecked(false);
                    binding.rbLeft.setChecked(true);
                }
            }
        });

        binding.spinner.setVisibility(View.GONE);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                binding.etSponsorId.setText(sponserListArrayList.get(i).getLoginIDD());
                binding.etSponsorName.setText(sponserListArrayList.get(i).getUserName());
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.etSponsorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        binding.etPincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 5) {
                    getPinCode();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.rbRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.rbLeft.isChecked()) {
                    binding.rbRight.setChecked(true);
                    binding.rbLeft.setChecked(false);
                } else {
                    binding.rbRight.setChecked(true);
                    binding.rbLeft.setChecked(false);
                }
            }
        });

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter("site_position"));

        binding.rbMale.setChecked(true);
        binding.rbFemale.setChecked(false);


        binding.rbMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.rbFemale.isChecked()) {
                    binding.rbFemale.setChecked(false);
                    binding.rbMale.setChecked(true);
                } else {
                    binding.rbFemale.setChecked(false);
                    binding.rbMale.setChecked(true);
                }
            }
        });

        binding.rbFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.rbMale.isChecked()) {
                    binding.rbFemale.setChecked(true);
                    binding.rbMale.setChecked(false);
                } else {
                    binding.rbFemale.setChecked(true);
                    binding.rbMale.setChecked(false);
                }
            }
        });


        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etEmail.setEnabled(true);
                binding.etFirstName.setEnabled(true);
                binding.etLastName.setEnabled(true);
                binding.etAadhar.setEnabled(true);
                binding.etMobile.setEnabled(true);
                binding.etSponsorName.setEnabled(true);
                binding.etSponsorId.setEnabled(true);
                binding.etPancard.setEnabled(true);
                binding.etAddress.setEnabled(true);
                binding.etPincode.setEnabled(true);
                binding.etState.setEnabled(true);
                binding.etCity.setEnabled(true);

                binding.rbMale.setEnabled(true);
                binding.rbFemale.setEnabled(true);
                binding.rbLeft.setEnabled(true);
                binding.rbRight.setEnabled(true);

                binding.btnConfirm.setVisibility(View.VISIBLE);
                binding.btnEdit.setVisibility(View.GONE);
                binding.btnRegister.setVisibility(View.GONE);
            }
        });


        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.etSponsorId.getText().toString())) {
                    Toast.makeText(getActivity(), "Sponsor Id cannot be blank", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.etFirstName.getText().toString())) {
                    Toast.makeText(getActivity(), "Enter the first name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.etMobile.getText().toString())) {
                    Toast.makeText(getActivity(), "Enter Mobile", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.etAadhar.getText().toString())) {
                    Toast.makeText(getActivity(), "Enter Aadhar Number", Toast.LENGTH_SHORT).show();
                } else {
                    binding.etEmail.setEnabled(false);
                    binding.etFirstName.setEnabled(false);
                    binding.etLastName.setEnabled(false);
                    binding.etAadhar.setEnabled(false);
                    binding.etMobile.setEnabled(false);
                    binding.etSponsorName.setEnabled(false);
                    binding.etSponsorId.setEnabled(false);
                    binding.etPancard.setEnabled(false);
                    binding.etAddress.setEnabled(false);
                    binding.etPincode.setEnabled(false);
                    binding.etState.setEnabled(false);
                    binding.etCity.setEnabled(false);

                    binding.rbMale.setEnabled(false);
                    binding.rbFemale.setEnabled(false);
                    binding.rbLeft.setEnabled(false);
                    binding.rbRight.setEnabled(false);

                    binding.btnConfirm.setVisibility(View.GONE);
                    binding.btnEdit.setVisibility(View.VISIBLE);
                    binding.btnRegister.setVisibility(View.VISIBLE);
                }
            }
        });


        return binding.getRoot();
    }

    private void showDialog() {
        alert = new android.app.AlertDialog.Builder(getActivity());
        View mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_select_sponsor, null);
        alert.setView(mView);
        RecyclerView rv_sponsor = mView.findViewById(R.id.rv_sponsor);
        EditText et_search = mView.findViewById(R.id.et_search);
        filtersponserListArrayList = new ArrayList<>();
        AdapterSponsor adapterSponsor = new AdapterSponsor(getActivity(), sponserListArrayList);
        rv_sponsor.setAdapter(adapterSponsor);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() > 0) {
                    filtersponserListArrayList.clear();
                    for (int pos = 0; pos < sponserListArrayList.size(); pos++) {

                        Log.e("Position", "Pos:::" + pos);
                        Log.e("Position", "Log:::" + sponserListArrayList.get(pos).getLoginIDD());
                        Log.e("Position", "Char:::" + charSequence);
                        Log.e("Position", "Pos:::" + pos);
                        if (sponserListArrayList.get(pos).getLoginIDD().toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                                sponserListArrayList.get(pos).getUserName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            filtersponserListArrayList.add(sponserListArrayList.get(pos));

                        }


                    }

                    Log.e("JNjbjbjkb", "" + filtersponserListArrayList.size());
                    if (filtersponserListArrayList.size() > 0) {
                        AdapterSponsor adapterSponsor = new AdapterSponsor(getActivity(), filtersponserListArrayList);
                        rv_sponsor.setAdapter(adapterSponsor);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        alertDialog = alert.create();
        alertDialog.show();
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String salaryId = intent.getStringExtra("login_id");
            String name = intent.getStringExtra("name");
            binding.etSponsorId.setText(salaryId);
            binding.etSponsorName.setText(name);
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }

        }
    };

    private void registerUser() {
        JsonObject object = new JsonObject();
        object.addProperty("SponsorId", binding.etSponsorId.getText().toString());
        object.addProperty("FirstName", binding.etFirstName.getText().toString());
        object.addProperty("MobileNo", binding.etMobile.getText().toString());
        object.addProperty("PanCard", binding.etPancard.getText().toString());
        object.addProperty("AdharNo", binding.etAadhar.getText().toString());
        object.addProperty("AddedBy", PreferencesManager.getInstance(getActivity()).getUserId());
        if (binding.rbLeft.isChecked()) {
            object.addProperty("Leg", "Left");
        } else {
            object.addProperty("Leg", "Right");
        }
        LoggerUtil.logItem(object);
        Call<ResponseRegistration> call = apiServices.DownlineRegistrationAction(object);
        call.enqueue(new Callback<ResponseRegistration>() {
            @Override
            public void onResponse(Call<ResponseRegistration> call, Response<ResponseRegistration> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("0")) {
                        showCommonDialog(response.body());


                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseRegistration> call, Throwable t) {

            }
        });

    }

    private void showCommonDialog(ResponseRegistration responseRegistration) {
        final android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(getActivity());
        View mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_registration_successful, null);
        alert.setView(mView);
        TextView txt_login_id = mView.findViewById(R.id.txt_login_id);
        TextView txt_password = mView.findViewById(R.id.txt_password);
        TextView txt_name = mView.findViewById(R.id.txt_name);
        TextView txt_mobile = mView.findViewById(R.id.txt_mobile);
        ImageView img_close = mView.findViewById(R.id.img_close);
        Button btn_close = mView.findViewById(R.id.btn_close);
        Button btn_share = mView.findViewById(R.id.btn_share);


        final android.app.AlertDialog alertDialog1 = alert.create();
        alertDialog1.show();
        txt_login_id.setText(responseRegistration.getLoginId());
        txt_password.setText(responseRegistration.getPassWord());
        txt_mobile.setText(responseRegistration.getMobileNo());
        txt_name.setText(responseRegistration.getDisplayName());
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String body = "Hi " + "\b" + responseRegistration.getDisplayName() + " " + "! Your registration is successful " +
                        "\nYour User id for Login is: " + responseRegistration.getLoginId().toString() + " and Your password for Login is: "
                        + responseRegistration.getPassWord() + ". Have a nice day.";
                String sub = "Invite";
                System.out.println("++++++++++++++++++++body" + body);
                myIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
                myIntent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
            }
        });



        alertDialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

                ((ContainerActivity) getActivity()).ReplaceFragment(new Dashboard(), "Dashboard");
            }
        });

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void getPinCode() {
        JsonObject object = new JsonObject();
        object.addProperty("PinCode", binding.etPincode.getText().toString());
        LoggerUtil.logItem(object);
        Call<ResponsePinCode> call = apiServices.GetStateCity(object);

        call.enqueue(new Callback<ResponsePinCode>() {
            @Override
            public void onResponse(Call<ResponsePinCode> call, Response<ResponsePinCode> response) {
                if (response.isSuccessful() && response.body().getStatus().equals("0")) {
                    binding.etState.setText(response.body().getState());
                    binding.etCity.setText(response.body().getCity());

                } else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePinCode> call, Throwable t) {

            }
        });

    }

    private void getSponsorList() {

        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(getActivity()).getLoginId());
        LoggerUtil.logItem(object);
        Log.e("SIZERDYD", "" + PreferencesManager.getInstance(getActivity()).getLoginId());

        Call<ResponseSponsorList> call = apiServices.GetSponserList(object);
        call.enqueue(new Callback<ResponseSponsorList>() {
            @Override
            public void onResponse(Call<ResponseSponsorList> call, Response<ResponseSponsorList> response) {

                progressDialog.dismiss();


                Log.e("SIZERDYD", "" + response.isSuccessful());
                Log.e("SIZERDYD", "" + response);
                if (response.isSuccessful()) {
                    sponserListArrayList = new ArrayList<>(response.body().getSponserListArrayList());


                    Log.e("SIZERDYD", "" + sponserListArrayList.size());
                    binding.spinner.setAdapter(new SiteSpinnerAdapter());
                }
            }

            @Override
            public void onFailure(Call<ResponseSponsorList> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    class SiteSpinnerAdapter
            extends BaseAdapter {
        SiteSpinnerAdapter() {
        }

        public int getCount() {
            return sponserListArrayList.size();
        }

        public Object getItem(int n) {
            return null;
        }

        public long getItemId(int n) {
            return 0L;
        }

        public View getView(int n, View view, ViewGroup viewGroup) {
            View view2 = getLayoutInflater().inflate(R.layout.layout_sponsor_single_row, null);
            TextView textView = (TextView) view2.findViewById(R.id.txt_loginId);
            TextView textView1 = (TextView) view2.findViewById(R.id.txt_name);
            textView.setText(sponserListArrayList.get(n).getLoginIDD());
            textView1.setText(sponserListArrayList.get(n).getUserName());
            return view2;
        }
    }


    class FilterAdapter
            extends BaseAdapter {
        FilterAdapter() {
        }

        public int getCount() {
            return filtersponserListArrayList.size();
        }

        public Object getItem(int n) {
            return null;
        }

        public long getItemId(int n) {
            return 0L;
        }

        public View getView(int n, View view, ViewGroup viewGroup) {
            View view2 = getLayoutInflater().inflate(R.layout.layout_sponsor_single_row, null);
            TextView textView = (TextView) view2.findViewById(R.id.txt_loginId);
            TextView textView1 = (TextView) view2.findViewById(R.id.txt_name);
            textView.setText(filtersponserListArrayList.get(n).getLoginIDD());
            textView1.setText(filtersponserListArrayList.get(n).getUserName());
            return view2;
        }
    }
}