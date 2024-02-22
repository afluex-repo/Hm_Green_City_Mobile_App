package com.hmgreencity.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.hmgreencity.Activity.ContainerActivity;
import com.hmgreencity.Activity.SignUp;
import com.hmgreencity.R;
import com.hmgreencity.app.AppConfig;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.constants.BaseFragment;
import com.hmgreencity.model.response.treeView.GetGenelogyItem;
import com.hmgreencity.model.response.treeView.ResponseTreeView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TreeView extends BaseFragment implements View.OnClickListener {
    Unbinder unbinder;
    Dialog dialog, BusinessDialog;

    Button btn_downline_rank;

    //    Items
    private List<GetGenelogyItem> tree_view_arr = new ArrayList<GetGenelogyItem>();
    ImageView tree_l_one_photo, tree_l_two_a_photo, tree_l_two_b_photo, tree_l_three_a_photo, tree_l_three_b_photo, tree_l_three_c_photo, tree_l_three_d_photo,
            tree_l_four_one_photo, tree_l_four_two_photo, tree_l_four_three_photo, tree_l_four_four_photo, tree_l_four_five_photo, tree_l_four_six_photo,
            tree_l_four_seven_photo, tree_l_four_eight_photo;
    TextView moveup, go_btn, movetoroot, title, tree_l_one_customerid, tree_l_one_customer_name, tree_l_one_customer_spillby,
            tree_l_two_a_customerid, tree_l_two_a_customer_name, tree_l_two_a_customer_spillby,
            tree_l_two_b_customerid, tree_l_two_b_customer_name, tree_l_two_b_customer_spillby,
            tree_l_three_a_customerid, tree_l_three_a_customer_name, tree_l_three_a_customer_spillby,
            tree_l_three_b_customerid, tree_l_three_b_customer_name, tree_l_three_b_customer_spillby,
            tree_l_three_c_customerid, tree_l_three_c_customer_name, tree_l_three_c_customer_spillby,
            tree_l_three_d_customerid, tree_l_three_d_customer_name, tree_l_three_d_customer_spillby,
            tree_l_four_one_customerid, tree_l_four_two_customerid, tree_l_four_three_customerid, tree_l_four_four_customerid,
            tree_l_four_five_customerid, tree_l_four_six_customerid, tree_l_four_seven_customerid, tree_l_four_eight_customerid,
            tree_l_four_one_customer_name, tree_l_four_two_customer_name, tree_l_four_three_customer_name, tree_l_four_four_customer_name,
            tree_l_four_five_customer_name, tree_l_four_six_customer_name, tree_l_four_seven_customer_name, tree_l_four_eight_customer_name,
            tree_l_four_one_customer_spillby, tree_l_four_two_customer_spillby, tree_l_four_three_customer_spillby, tree_l_four_four_customer_spillby,
            tree_l_four_five_customer_spillby, tree_l_four_six_customer_spillby, tree_l_four_seven_customer_spillby, tree_l_four_eight_customer_spillby,
            tree_l_one_reg, tree_l_two_a_reg, tree_l_two_b_reg, tree_l_three_a_reg, tree_l_three_b_reg, tree_l_three_c_reg, tree_l_three_d_reg, tree_l_four_one_reg, tree_l_four_two_reg, tree_l_four_three_reg, tree_l_four_four_reg, tree_l_four_five_reg,
            tree_l_four_six_reg, tree_l_four_seven_reg, tree_l_four_eight_reg;
    private LinearLayout main_lo, node_one, tree_l_one_lo, tree_l_two_a_lo, tree_l_two_b_lo, tree_l_three_a_lo, tree_l_three_b_lo,
            tree_l_three_c_lo, tree_l_three_d_lo, tree_l_four_one_lo,
            tree_l_four_two_lo, tree_l_four_three_lo, tree_l_four_four_lo, tree_l_four_five_lo, tree_l_four_six_lo, tree_l_four_seven_lo,
            tree_l_four_eight_lo,
            node_two, node_three, node_four, node_five, node_six, node_seven, node_eight, node_nine, node_ten, node_eleven, node_twelve, node_thirteen, node_fourteen, node_fifteen;

    private String loginId = "", top_node_parentID;
    EditText search_et;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_geonology, container, false);
        unbinder = ButterKnife.bind(this, view);

        findid(view);

        loginId = PreferencesManager.getInstance(context).getLoginId();
        getTreeViewDetail(loginId);
        btn_downline_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ContainerActivity) getActivity()).ReplaceFragment(new FragmentReportDownlineRank(), "Downline Rank Achiever Reports");
            }
        });

        return view;
    }

    public void findid(View view) {

        movetoroot = view.findViewById(R.id.movetoroot);
        go_btn = view.findViewById(R.id.go_btn);
        moveup = view.findViewById(R.id.moveup);

        btn_downline_rank=view.findViewById(R.id.btn_downline_rank);

        tree_l_one_photo = view.findViewById(R.id.tree_l_one_photo);
        tree_l_two_a_photo = view.findViewById(R.id.tree_l_two_a_photo);
        tree_l_two_b_photo = view.findViewById(R.id.tree_l_two_b_photo);
        tree_l_three_a_photo = view.findViewById(R.id.tree_l_three_a_photo);
        tree_l_three_b_photo = view.findViewById(R.id.tree_l_three_b_photo);
        tree_l_three_c_photo = view.findViewById(R.id.tree_l_three_c_photo);
        tree_l_three_d_photo = view.findViewById(R.id.tree_l_three_d_photo);

        tree_l_four_one_photo = view.findViewById(R.id.tree_l_four_one_photo);
        tree_l_four_two_photo = view.findViewById(R.id.tree_l_four_two_photo);
        tree_l_four_three_photo = view.findViewById(R.id.tree_l_four_three_photo);
        tree_l_four_four_photo = view.findViewById(R.id.tree_l_four_four_photo);
        tree_l_four_five_photo = view.findViewById(R.id.tree_l_four_five_photo);
        tree_l_four_six_photo = view.findViewById(R.id.tree_l_four_six_photo);
        tree_l_four_seven_photo = view.findViewById(R.id.tree_l_four_seven_photo);
        tree_l_four_eight_photo = view.findViewById(R.id.tree_l_four_eight_photo);

        tree_l_four_one_customerid = view.findViewById(R.id.tree_l_four_one_customerid);
        tree_l_four_two_customerid = view.findViewById(R.id.tree_l_four_two_customerid);
        tree_l_four_three_customerid = view.findViewById(R.id.tree_l_four_three_customerid);
        tree_l_four_four_customerid = view.findViewById(R.id.tree_l_four_four_customerid);
        tree_l_four_five_customerid = view.findViewById(R.id.tree_l_four_five_customerid);
        tree_l_four_six_customerid = view.findViewById(R.id.tree_l_four_six_customerid);
        tree_l_four_seven_customerid = view.findViewById(R.id.tree_l_four_seven_customerid);
        tree_l_four_eight_customerid = view.findViewById(R.id.tree_l_four_eight_customerid);

        tree_l_four_one_customer_name = view.findViewById(R.id.tree_l_four_one_customer_name);
        tree_l_four_two_customer_name = view.findViewById(R.id.tree_l_four_two_customer_name);
        tree_l_four_three_customer_name = view.findViewById(R.id.tree_l_four_three_customer_name);
        tree_l_four_four_customer_name = view.findViewById(R.id.tree_l_four_four_customer_name);
        tree_l_four_five_customer_name = view.findViewById(R.id.tree_l_four_five_customer_name);
        tree_l_four_six_customer_name = view.findViewById(R.id.tree_l_four_six_customer_name);
        tree_l_four_seven_customer_name = view.findViewById(R.id.tree_l_four_seven_customer_name);
        tree_l_four_eight_customer_name = view.findViewById(R.id.tree_l_four_eight_customer_name);

        tree_l_four_one_customer_spillby = view.findViewById(R.id.tree_l_four_one_customer_spillby);
        tree_l_four_two_customer_spillby = view.findViewById(R.id.tree_l_four_two_customer_spillby);
        tree_l_four_three_customer_spillby = view.findViewById(R.id.tree_l_four_three_customer_spillby);
        tree_l_four_four_customer_spillby = view.findViewById(R.id.tree_l_four_four_customer_spillby);
        tree_l_four_five_customer_spillby = view.findViewById(R.id.tree_l_four_five_customer_spillby);
        tree_l_four_six_customer_spillby = view.findViewById(R.id.tree_l_four_six_customer_spillby);
        tree_l_four_seven_customer_spillby = view.findViewById(R.id.tree_l_four_seven_customer_spillby);
        tree_l_four_eight_customer_spillby = view.findViewById(R.id.tree_l_four_eight_customer_spillby);

        search_et = view.findViewById(R.id.search_et);
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    checkDownlineForTree(search_et.getText().toString().trim(), "null", context);
                    return true;
                }
                return false;
            }
        });

        tree_l_one_customerid = view.findViewById(R.id.tree_l_one_customerid);
        tree_l_one_customer_name = view.findViewById(R.id.tree_l_one_customer_name);
        tree_l_one_customer_spillby = view.findViewById(R.id.tree_l_one_customer_spillby);
        tree_l_two_a_customerid = view.findViewById(R.id.tree_l_two_a_customerid);
        tree_l_two_a_customer_name = view.findViewById(R.id.tree_l_two_a_customer_name);
        tree_l_two_a_customer_spillby = view.findViewById(R.id.tree_l_two_a_customer_spillby);
        tree_l_two_b_customerid = view.findViewById(R.id.tree_l_two_b_customerid);
        tree_l_two_b_customer_name = view.findViewById(R.id.tree_l_two_b_customer_name);
        tree_l_two_b_customer_spillby = view.findViewById(R.id.tree_l_two_b_customer_spillby);
        tree_l_three_a_customerid = view.findViewById(R.id.tree_l_three_a_customerid);
        tree_l_three_a_customer_name = view.findViewById(R.id.tree_l_three_a_customer_name);
        tree_l_three_a_customer_spillby = view.findViewById(R.id.tree_l_three_a_customer_spillby);
        tree_l_three_b_customerid = view.findViewById(R.id.tree_l_three_b_customerid);
        tree_l_three_b_customer_name = view.findViewById(R.id.tree_l_three_b_customer_name);
        tree_l_three_b_customer_spillby = view.findViewById(R.id.tree_l_three_b_customer_spillby);
        tree_l_three_c_customerid = view.findViewById(R.id.tree_l_three_c_customerid);
        tree_l_three_c_customer_name = view.findViewById(R.id.tree_l_three_c_customer_name);
        tree_l_three_c_customer_spillby = view.findViewById(R.id.tree_l_three_c_customer_spillby);
        tree_l_three_d_customerid = view.findViewById(R.id.tree_l_three_d_customerid);
        tree_l_three_d_customer_name = view.findViewById(R.id.tree_l_three_d_customer_name);
        tree_l_three_d_customer_spillby = view.findViewById(R.id.tree_l_three_d_customer_spillby);


        tree_l_one_reg = view.findViewById(R.id.tree_l_one_reg);
        tree_l_two_a_reg = view.findViewById(R.id.tree_l_two_a_reg);
        tree_l_two_b_reg = view.findViewById(R.id.tree_l_two_b_reg);
        tree_l_three_a_reg = view.findViewById(R.id.tree_l_three_a_reg);
        tree_l_three_b_reg = view.findViewById(R.id.tree_l_three_b_reg);
        tree_l_three_c_reg = view.findViewById(R.id.tree_l_three_c_reg);
        tree_l_three_d_reg = view.findViewById(R.id.tree_l_three_d_reg);
        tree_l_four_one_reg = view.findViewById(R.id.tree_l_four_one_reg);
        tree_l_four_two_reg = view.findViewById(R.id.tree_l_four_two_reg);
        tree_l_four_three_reg = view.findViewById(R.id.tree_l_four_three_reg);
        tree_l_four_four_reg = view.findViewById(R.id.tree_l_four_four_reg);
        tree_l_four_five_reg = view.findViewById(R.id.tree_l_four_five_reg);
        tree_l_four_six_reg = view.findViewById(R.id.tree_l_four_six_reg);
        tree_l_four_seven_reg = view.findViewById(R.id.tree_l_four_seven_reg);
        tree_l_four_eight_reg = view.findViewById(R.id.tree_l_four_eight_reg);

        main_lo = view.findViewById(R.id.main_lo);
        node_one = view.findViewById(R.id.node_one);
        node_two = view.findViewById(R.id.node_two);
        node_three = view.findViewById(R.id.node_three);
        node_four = view.findViewById(R.id.node_four);
        node_five = view.findViewById(R.id.node_five);
        node_six = view.findViewById(R.id.node_six);
        node_seven = view.findViewById(R.id.node_seven);

        node_eight = view.findViewById(R.id.node_eight);
        node_nine = view.findViewById(R.id.node_nine);
        node_ten = view.findViewById(R.id.node_ten);
        node_eleven = view.findViewById(R.id.node_eleven);
        node_twelve = view.findViewById(R.id.node_twelve);
        node_thirteen = view.findViewById(R.id.node_thirteen);
        node_fourteen = view.findViewById(R.id.node_fourteen);
        node_fifteen = view.findViewById(R.id.node_fifteen);

        tree_l_one_lo = view.findViewById(R.id.tree_l_one_lo);
        tree_l_two_a_lo = view.findViewById(R.id.tree_l_two_a_lo);
        tree_l_two_b_lo = view.findViewById(R.id.tree_l_two_b_lo);
        tree_l_three_a_lo = view.findViewById(R.id.tree_l_three_a_lo);
        tree_l_three_b_lo = view.findViewById(R.id.tree_l_three_b_lo);
        tree_l_three_c_lo = view.findViewById(R.id.tree_l_three_c_lo);
        tree_l_three_d_lo = view.findViewById(R.id.tree_l_three_d_lo);
        tree_l_four_one_lo = view.findViewById(R.id.tree_l_four_one_lo);
        tree_l_four_two_lo = view.findViewById(R.id.tree_l_four_two_lo);
        tree_l_four_three_lo = view.findViewById(R.id.tree_l_four_three_lo);
        tree_l_four_four_lo = view.findViewById(R.id.tree_l_four_four_lo);
        tree_l_four_five_lo = view.findViewById(R.id.tree_l_four_five_lo);
        tree_l_four_six_lo = view.findViewById(R.id.tree_l_four_six_lo);
        tree_l_four_seven_lo = view.findViewById(R.id.tree_l_four_seven_lo);
        tree_l_four_eight_lo = view.findViewById(R.id.tree_l_four_eight_lo);

        moveup.setOnClickListener(this);
        go_btn.setOnClickListener(this);
        movetoroot.setOnClickListener(this);
        node_one.setOnClickListener(this);
        node_two.setOnClickListener(this);
        node_three.setOnClickListener(this);
        node_four.setOnClickListener(this);
        node_five.setOnClickListener(this);
        node_six.setOnClickListener(this);
        node_seven.setOnClickListener(this);
        node_eight.setOnClickListener(this);
        node_nine.setOnClickListener(this);
        node_ten.setOnClickListener(this);
        node_eleven.setOnClickListener(this);
        node_twelve.setOnClickListener(this);
        node_thirteen.setOnClickListener(this);
        node_fourteen.setOnClickListener(this);
        node_fifteen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_btn:
                getTreeViewDetail(search_et.getText().toString().trim());
                break;
            case R.id.moveup:
                getTreeViewDetail(top_node_parentID);
                break;
            case R.id.movetoroot:
                loginId = PreferencesManager.getInstance(context).getLoginId() + "";
                getTreeViewDetail(loginId);
                break;
            case R.id.node_one:
                loginId = tree_l_one_customerid.getText().toString();
                execute(tree_l_one_customerid, loginId);
                break;
            case R.id.node_two:
                loginId = tree_l_two_a_customerid.getText().toString();
                execute(tree_l_two_a_customerid, loginId);
                break;
            case R.id.node_three:
                loginId = tree_l_two_b_customerid.getText().toString();
                execute(tree_l_two_b_customerid, loginId);
                break;
            case R.id.node_four:
                loginId = tree_l_three_a_customerid.getText().toString();
                execute(tree_l_three_a_customerid, loginId);
                break;
            case R.id.node_five:
                loginId = tree_l_three_b_customerid.getText().toString();
                execute(tree_l_three_b_customerid, loginId);
                break;
            case R.id.node_six:
                loginId = tree_l_three_c_customerid.getText().toString();
                execute(tree_l_three_c_customerid, loginId);
                break;
            case R.id.node_seven:
                loginId = tree_l_three_d_customerid.getText().toString();
                execute(tree_l_three_d_customerid, loginId);
                break;

            case R.id.node_eight:
                loginId = tree_l_four_one_customerid.getText().toString();
                execute(tree_l_four_one_customerid, loginId);
                break;
            case R.id.node_nine:
                loginId = tree_l_four_two_customerid.getText().toString();
                execute(tree_l_four_two_customerid, loginId);
                break;
            case R.id.node_ten:
                loginId = tree_l_four_three_customerid.getText().toString();
                execute(tree_l_four_three_customerid, loginId);
                break;
            case R.id.node_eleven:
                loginId = tree_l_four_four_customerid.getText().toString();
                execute(tree_l_four_four_customerid, loginId);
                break;
            case R.id.node_twelve:
                loginId = tree_l_four_five_customerid.getText().toString();
                execute(tree_l_four_five_customerid, loginId);
                break;
            case R.id.node_thirteen:
                loginId = tree_l_four_six_customerid.getText().toString();
                execute(tree_l_four_six_customerid, loginId);
                break;
            case R.id.node_fourteen:
                loginId = tree_l_four_seven_customerid.getText().toString();
                execute(tree_l_four_seven_customerid, loginId);
                break;
            case R.id.node_fifteen:
                loginId = tree_l_four_eight_customerid.getText().toString();
                execute(tree_l_four_eight_customerid, loginId);
                break;

        }
    }

    private void execute(TextView view, String loginId) {
        Bundle param = new Bundle();

        Log.e("LoginId1234567574",loginId);
        Log.e("LoginId1234567574",""+loginId.equalsIgnoreCase(""));
        if (loginId.equalsIgnoreCase("")) {
            if (view == tree_l_one_customerid) {
            } else if (view == tree_l_two_a_customerid) {
                loginId = tree_l_one_customerid.getText().toString();
            }
            else if (view == tree_l_two_b_customerid) {
                loginId = tree_l_one_customerid.getText().toString();
            } else if (view == tree_l_three_a_customerid) {
                loginId = tree_l_two_a_customerid.getText().toString();
            } else if (view == tree_l_three_b_customerid) {
                loginId = tree_l_two_a_customerid.getText().toString();
            } else if (view == tree_l_three_c_customerid) {
                loginId = tree_l_two_b_customerid.getText().toString();
            } else if (view == tree_l_three_d_customerid) {
                loginId = tree_l_two_b_customerid.getText().toString();
            } else if (view == tree_l_four_one_customerid) {
                loginId = tree_l_three_a_customerid.getText().toString();
            } else if (view == tree_l_four_two_customerid) {
                loginId = tree_l_three_a_customerid.getText().toString();
            } else if (view == tree_l_four_three_customerid) {
                loginId = tree_l_three_b_customerid.getText().toString();
            } else if (view == tree_l_four_four_customerid) {
                loginId = tree_l_three_b_customerid.getText().toString();
            } else if (view == tree_l_four_five_customerid) {
                loginId = tree_l_three_c_customerid.getText().toString();
            } else if (view == tree_l_four_six_customerid) {
                loginId = tree_l_three_c_customerid.getText().toString();
            } else if (view == tree_l_four_seven_customerid) {
                loginId = tree_l_three_d_customerid.getText().toString();
            } else if (view == tree_l_four_eight_customerid) {
                loginId = tree_l_three_d_customerid.getText().toString();
            }

            if (loginId.equalsIgnoreCase("")) {
                showMessage("Invalid Position.");
            } else {
                param.putString("place_under_id", loginId);
                hideKeyboard();
//                goToActivity(SignUp.class, param);

            }

            if(PreferencesManager.getInstance(getActivity()).getRank().equals("Star")){
                ((ContainerActivity) context).ReplaceFragment(new SignUpFragment(), "Register");
            }else{
                Toast.makeText(getActivity(), "Your Rank is not Eligible for Downline Registration", Toast.LENGTH_LONG).show();
            }

        }



        else {
            ChooseFreomTreeDialog(loginId, context);
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    void ChooseFreomTreeDialog(String loginId, final Context context) {
        dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        dialog.setContentView(R.layout.dialog_choose_from_tree);

        Button btn_tree_details = dialog.findViewById(R.id.btn_tree_details);
        Button btn_show_business = dialog.findViewById(R.id.btn_show_business);

        btn_show_business.setOnClickListener(v -> {
            BusinessDialog(loginId, context);
            dialog.dismiss();
        });

        btn_tree_details.setOnClickListener(v -> {
            getTreeViewDetail(loginId);
            dialog.dismiss();
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    void BusinessDialog(String loginId, final Context context) {
        BusinessDialog = new Dialog(context);
        BusinessDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        BusinessDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        BusinessDialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        BusinessDialog.setContentView(R.layout.dialog_tree_business);

        Button btn_ok = BusinessDialog.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(v -> BusinessDialog.dismiss());

        TextView tv_user_name = BusinessDialog.findViewById(R.id.tv_user_name);
        TextView tv_hold_left = BusinessDialog.findViewById(R.id.tv_hold_left);
        TextView hold_right = BusinessDialog.findViewById(R.id.hold_right);
        TextView hold_total = BusinessDialog.findViewById(R.id.hold_total);

        TextView tv_login_id = BusinessDialog.findViewById(R.id.tv_login_id);
        TextView tv_activate_with = BusinessDialog.findViewById(R.id.tv_activate_with);
        TextView tv_sponsor_id = BusinessDialog.findViewById(R.id.tv_sponsor_id);
        TextView tv_join_on = BusinessDialog.findViewById(R.id.tv_join_on);

        TextView active_left = BusinessDialog.findViewById(R.id.active_left);
        TextView inactive_left = BusinessDialog.findViewById(R.id.inactive_left);
        TextView totalmembers_left = BusinessDialog.findViewById(R.id.totalmembers_left);
        TextView totalbuis_left = BusinessDialog.findViewById(R.id.totalbuis_left);

        TextView active_right = BusinessDialog.findViewById(R.id.active_right);
        TextView inactive_right = BusinessDialog.findViewById(R.id.inactive_right);
        TextView totalmembers_right = BusinessDialog.findViewById(R.id.totalmembers_right);
        TextView totalbuis_right = BusinessDialog.findViewById(R.id.totalbuis_right);

        TextView active_total = BusinessDialog.findViewById(R.id.active_total);
        TextView inactive_total = BusinessDialog.findViewById(R.id.inactive_total);
        TextView totalmembers_total = BusinessDialog.findViewById(R.id.totalmembers_total);
        TextView totalbuis_total = BusinessDialog.findViewById(R.id.totalbuis_total);

        for (int i = 0; i < tree_view_arr.size(); i++) {
            if (tree_view_arr.get(i).getLoginId().equalsIgnoreCase(loginId)) {
                tv_user_name.setText(tree_view_arr.get(i).getMemberName());
                tv_login_id.setText(tree_view_arr.get(i).getLoginId());
//        tv_activate_with.setText(tree_view_arr.get(i).getString("PackageAmount"));
                tv_sponsor_id.setText(tree_view_arr.get(i).getSponsorId());
                tv_join_on.setText(tree_view_arr.get(i).getActivationDate());

                active_left.setText(tree_view_arr.get(i).getActiveLeft());
                inactive_left.setText(tree_view_arr.get(i).getInactiveLeft());
                totalmembers_left.setText(String.valueOf(Integer.parseInt(tree_view_arr.get(i).getActiveLeft()) + Integer.parseInt(tree_view_arr.get(i).getInactiveLeft())+ Integer.parseInt(tree_view_arr.get(i).getHoldLeft())));
                totalbuis_left.setText(tree_view_arr.get(i).getBusinessLeft());

                active_right.setText(tree_view_arr.get(i).getActiveRight());
                inactive_right.setText(tree_view_arr.get(i).getInactiveRight());
                totalmembers_right.setText(String.valueOf(Integer.parseInt(tree_view_arr.get(i).getActiveRight()) + Integer.parseInt(tree_view_arr.get(i).getInactiveRight())
                        + Integer.parseInt(tree_view_arr.get(i).getHoldRight())));
                totalbuis_right.setText(tree_view_arr.get(i).getBusinessRight());

//                Log.e("FThgh",tree_view_arr.get(i).getHoldLeft());
                tv_hold_left.setText(tree_view_arr.get(i).getHoldLeft());
                hold_right.setText(tree_view_arr.get(i).getHoldRight());

                active_total.setText(String.valueOf(Integer.parseInt(tree_view_arr.get(i).getActiveRight()) + Integer.parseInt(tree_view_arr.get(i).getActiveLeft())));
                if(tree_view_arr.get(i).getHoldLeft()!=null && tree_view_arr.get(i).getHoldRight()!=null){
                    hold_total.setText(String.valueOf(Integer.parseInt(tree_view_arr.get(i).getHoldLeft()) + Integer.parseInt(tree_view_arr.get(i).getHoldRight())));
                }

                inactive_total.setText(String.valueOf(Integer.parseInt(tree_view_arr.get(i).getInactiveRight()) + Integer.parseInt(tree_view_arr.get(i).getInactiveLeft())));
                totalmembers_total.setText(String.valueOf(Integer.parseInt(totalmembers_left.getText().toString())
                        + Integer.parseInt(totalmembers_right.getText().toString())));
                Double left=Double.parseDouble(tree_view_arr.get(i).getBusinessLeft());
                Double right=Double.valueOf(tree_view_arr.get(i).getBusinessRight());
                Double add=left+right;
                DecimalFormat decimalFormatter = new DecimalFormat("############.##");
                decimalFormatter.setMinimumFractionDigits(2);
                decimalFormatter.setMaximumFractionDigits(3);
                totalbuis_total.setText(decimalFormatter.format(Double.parseDouble(String.valueOf(add))));
                Log.e("GHFghcg",""+add);
                Log.e("GHFghcg",""+left);
                Log.e("GHFghcg",""+right);
//                totalbuis_total.setText(String.format("%.2f",String.valueOf
//                        ((Double.parseDouble(tree_view_arr.get(i).getBusinessLeft()) + Double.parseDouble(tree_view_arr.get(i).getBusinessRight())))));
                break;
            }
        }

        BusinessDialog.setCanceledOnTouchOutside(true);
        BusinessDialog.setCancelable(true);
        BusinessDialog.show();
    }

    private void getTreeViewDetail(String loginId) {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("loginid", loginId);
        object.addProperty("Fk_headId", PreferencesManager.getInstance(context).getUserId());

        Log.e("jhjiji","LLL::"+loginId+"HEAD"+PreferencesManager.getInstance(context).getUserId());
//        LoggerUtil.logItem(object);

        Call<ResponseTreeView> call = apiServices.getTreeView(object);
        call.enqueue(new Callback<ResponseTreeView>() {
            @Override
            public void onResponse(Call<ResponseTreeView> call, Response<ResponseTreeView> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                String twoL = "", twoR = "", three1 = "", three2 = "", three3 = "", three4 = "";
                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    {
                        main_lo.setVisibility(View.VISIBLE);
                        removepreviousData();
                        tree_view_arr = response.body().getGetGenelogy();
                        Log.e("jhghjfuv",response.body().toString());

                        try {
                            if ((tree_view_arr.size() != 0)) {
                                for (int j = 0; j < tree_view_arr.size(); j++) {
                                    try {
                                        //CASE 1
                                        if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("1")) {
                                            top_node_parentID = tree_view_arr.get(j).getSponsorId();
                                            Log.e("++++", top_node_parentID + "");
                                            AppConfig.treeview_top_loginID = tree_view_arr.get(j).getLoginId();
                                            tree_l_one_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_one_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_one_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_one_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_one_customer_spillby.setText(String.format("Sponsor by :%s", tree_view_arr.get(j).getFkSponsorId()));
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_one_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                            //CASE 2
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("2") &&
                                                tree_view_arr.get(j).getLeg().equalsIgnoreCase("L")) {
                                            twoL = tree_view_arr.get(j).getFkUserId();
                                            tree_l_two_a_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_two_a_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_two_a_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_two_a_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_two_a_customer_spillby.setText(String.format("Sponsor by :%s", tree_view_arr.get(j).getFkSponsorId()));
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_two_a_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("2") &&
                                                tree_view_arr.get(j).getLeg().equalsIgnoreCase("R")) {
                                            twoR = tree_view_arr.get(j).getFkUserId();
                                            tree_l_two_b_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_two_b_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_two_b_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_two_b_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_two_b_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_two_b_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                            //CASE 3
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("3") &&
                                                tree_view_arr.get(j).getLeg().equalsIgnoreCase("L")
                                                && twoL.equals(tree_view_arr.get(j).getFkParentId())) {
                                            three1 = tree_view_arr.get(j).getFkUserId();
                                            tree_l_three_a_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_three_a_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_three_a_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_three_a_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_three_a_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_three_a_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("3") && tree_view_arr.get(j).getLeg().equalsIgnoreCase("R")
                                                && twoL.equals(tree_view_arr.get(j).getFkParentId())) {
                                            three2 = tree_view_arr.get(j).getFkUserId();
                                            tree_l_three_b_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_three_b_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_three_b_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_three_b_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_three_b_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_three_b_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("3") && tree_view_arr.get(j).getLeg().equalsIgnoreCase("L")
                                                && twoR.equals(tree_view_arr.get(j).getFkParentId())) {
                                            three3 = tree_view_arr.get(j).getFkUserId();
                                            tree_l_three_c_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_three_c_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_three_c_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_three_c_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_three_c_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_three_c_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("3") && tree_view_arr.get(j).getLeg().equalsIgnoreCase("R")
                                                && twoR.equals(tree_view_arr.get(j).getFkParentId())) {
                                            three4 = tree_view_arr.get(j).getFkUserId();
                                            tree_l_three_d_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_three_d_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_three_d_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_three_d_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_three_d_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_three_d_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        }
                                        // ######### Case 4
                                        else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("4") && tree_view_arr.get(j).getLeg().equalsIgnoreCase("L")
                                                && three1.equals(tree_view_arr.get(j).getFkParentId())) {
                                            tree_l_four_one_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_four_one_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_four_one_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_four_one_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_four_one_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }

                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_four_one_photo);

                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("4") && tree_view_arr.get(j).getLeg().equalsIgnoreCase("R")
                                                && three1.equals(tree_view_arr.get(j).getFkParentId())) {
                                            tree_l_four_two_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_four_two_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_four_two_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_four_two_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_four_two_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_four_two_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("4") && tree_view_arr.get(j).getLeg().equalsIgnoreCase("L")
                                                && three2.equals(tree_view_arr.get(j).getFkParentId())) {
                                            tree_l_four_three_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_four_three_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_four_three_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_four_three_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_four_three_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_four_three_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("4") && tree_view_arr.get(j).getLeg().equalsIgnoreCase("R")
                                                && three2.equals(tree_view_arr.get(j).getFkParentId())) {
                                            tree_l_four_four_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_four_four_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_four_four_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_four_four_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_four_four_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_four_four_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("4") && tree_view_arr.get(j).getLeg().equalsIgnoreCase("L")
                                                && three3.equals(tree_view_arr.get(j).getFkParentId())) {
                                            tree_l_four_five_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_four_five_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_four_five_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_four_five_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_four_five_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_four_five_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("4") && tree_view_arr.get(j).getLeg().equalsIgnoreCase("R")
                                                && three3.equals(tree_view_arr.get(j).getFkParentId())) {
                                            tree_l_four_six_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_four_six_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_four_six_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_four_six_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_four_six_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_four_six_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("4") && tree_view_arr.get(j).getLeg().equalsIgnoreCase("L")
                                                && three4.equals(tree_view_arr.get(j).getFkParentId())) {
                                            tree_l_four_seven_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_four_seven_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_four_seven_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_four_seven_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_four_seven_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_four_seven_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        } else if (tree_view_arr.get(j).getMemberLevel().equalsIgnoreCase("4") && tree_view_arr.get(j).getLeg().equalsIgnoreCase("R")
                                                && three4.equals(tree_view_arr.get(j).getFkParentId())) {
                                            tree_l_four_eight_customerid.setText(tree_view_arr.get(j).getLoginId());
                                            tree_l_four_eight_customer_name.setText(tree_view_arr.get(j).getMemberName());
                                            if (tree_view_arr.get(j).getFkSponsorId().equalsIgnoreCase("")) {
                                                tree_l_four_eight_customer_spillby.setVisibility(View.GONE);
                                            } else {
//                                                tree_l_four_eight_customer_spillby.setVisibility(View.VISIBLE);
                                                tree_l_four_eight_customer_spillby.setText("Sponsor by :" + tree_view_arr.get(j).getFkSponsorId());
                                            }
                                            Glide.with(context).load("http://" + tree_view_arr.get(j).getImageURL())
                                                    .into(tree_l_four_eight_photo);
                                            Log.e("LOFGFGHF","LOGIN"+tree_view_arr.get(j).getLoginId()+"::::"+tree_view_arr.get(j).getImageURL());
                                        }
                                    } catch (Error r) {
                                        r.printStackTrace();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                ///set REGISTER////////////
                                if (tree_l_one_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_one_customerid.getText().toString().trim().equalsIgnoreCase("")) {
//                                set ReGISTER
                                    tree_l_one_reg.setVisibility(View.VISIBLE);
                                    tree_l_one_lo.setVisibility(View.GONE);
                                    tree_l_one_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_one_reg.setVisibility(View.GONE);
                                    tree_l_one_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_two_a_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_two_a_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_two_a_reg.setVisibility(View.VISIBLE);
                                    tree_l_two_a_lo.setVisibility(View.GONE);
                                    tree_l_two_a_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_two_a_reg.setVisibility(View.GONE);
                                    tree_l_two_a_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_two_b_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_two_b_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_two_b_reg.setVisibility(View.VISIBLE);
                                    tree_l_two_b_lo.setVisibility(View.GONE);
                                    tree_l_two_b_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_two_b_reg.setVisibility(View.GONE);
                                    tree_l_two_b_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_three_a_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_three_a_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_three_a_reg.setVisibility(View.VISIBLE);
                                    tree_l_three_a_lo.setVisibility(View.GONE);
                                    tree_l_three_a_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_three_a_reg.setVisibility(View.GONE);
                                    tree_l_three_a_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_three_b_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_three_b_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_three_b_reg.setVisibility(View.VISIBLE);
                                    tree_l_three_b_lo.setVisibility(View.GONE);
                                    tree_l_three_b_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_three_b_reg.setVisibility(View.GONE);
                                    tree_l_three_b_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_three_c_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_three_c_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_three_c_reg.setVisibility(View.VISIBLE);
                                    tree_l_three_c_lo.setVisibility(View.GONE);
                                    tree_l_three_c_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_three_c_reg.setVisibility(View.GONE);
                                    tree_l_three_c_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_three_d_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_three_d_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_three_d_reg.setVisibility(View.VISIBLE);
                                    tree_l_three_d_lo.setVisibility(View.GONE);
                                    tree_l_three_d_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_three_d_reg.setVisibility(View.GONE);
                                    tree_l_three_d_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_four_one_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_four_one_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_four_one_reg.setVisibility(View.VISIBLE);
                                    tree_l_four_one_lo.setVisibility(View.GONE);
                                    tree_l_four_one_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_four_one_reg.setVisibility(View.GONE);
                                    tree_l_four_one_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_four_two_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_four_two_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_four_two_reg.setVisibility(View.VISIBLE);
                                    tree_l_four_two_lo.setVisibility(View.GONE);
                                    tree_l_four_two_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_four_two_reg.setVisibility(View.GONE);
                                    tree_l_four_two_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_four_three_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_four_three_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_four_three_reg.setVisibility(View.VISIBLE);
                                    tree_l_four_three_lo.setVisibility(View.GONE);
                                    tree_l_four_three_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_four_three_reg.setVisibility(View.GONE);
                                    tree_l_four_three_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_four_four_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_four_four_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_four_four_reg.setVisibility(View.VISIBLE);
                                    tree_l_four_four_lo.setVisibility(View.GONE);
                                    tree_l_four_four_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_four_four_reg.setVisibility(View.GONE);
                                    tree_l_four_four_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_four_five_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_four_five_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_four_five_reg.setVisibility(View.VISIBLE);
                                    tree_l_four_five_lo.setVisibility(View.GONE);
                                    tree_l_four_five_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_four_five_reg.setVisibility(View.GONE);
                                    tree_l_four_five_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_four_six_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_four_six_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_four_six_reg.setVisibility(View.VISIBLE);
                                    tree_l_four_six_lo.setVisibility(View.GONE);
                                    tree_l_four_six_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_four_six_reg.setVisibility(View.GONE);
                                    tree_l_four_six_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_four_seven_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_four_seven_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_four_seven_reg.setVisibility(View.VISIBLE);
                                    tree_l_four_seven_lo.setVisibility(View.GONE);
                                    tree_l_four_seven_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_four_seven_reg.setVisibility(View.GONE);
                                    tree_l_four_seven_lo.setVisibility(View.VISIBLE);
                                }
                                if (tree_l_four_eight_customer_name.getText().toString().trim().equalsIgnoreCase("")
                                        && tree_l_four_eight_customerid.getText().toString().trim().equalsIgnoreCase("")) {
                                    tree_l_four_eight_reg.setVisibility(View.VISIBLE);
                                    tree_l_four_eight_lo.setVisibility(View.GONE);
                                    tree_l_four_eight_photo.setImageDrawable(getResources().getDrawable(R.drawable.user_user));
                                } else {
                                    tree_l_four_eight_reg.setVisibility(View.GONE);
                                    tree_l_four_eight_lo.setVisibility(View.VISIBLE);
                                }
                            } else {
                                showMessage(response.body().getMessage());
                            }
                        } catch (Error e) {
                            hideLoading();
                            e.printStackTrace();
                        } catch (Exception e) {
                            hideLoading();
                            e.printStackTrace();
                        }
                    }
                }else {
                    Log.e("KMJKNJN",response.body().getStatus());
                    Log.e("KMJKNJN",response.body().getMessage());
                    Log.e("KMJKNJN",response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseTreeView> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void removepreviousData() {
        search_et.setText("");
        tree_view_arr.clear();
        tree_l_one_customerid.setText("");
        tree_l_one_customer_name.setText("");
        tree_l_one_customer_spillby.setText("");
        tree_l_two_a_customerid.setText("");
        tree_l_two_a_customer_name.setText("");
        tree_l_two_a_customer_spillby.setText("");
        tree_l_two_b_customerid.setText("");
        tree_l_two_b_customer_name.setText("");
        tree_l_two_b_customer_spillby.setText("");
        tree_l_three_a_customerid.setText("");
        tree_l_three_a_customer_name.setText("");
        tree_l_three_a_customer_spillby.setText("");
        tree_l_three_b_customerid.setText("");
        tree_l_three_b_customer_name.setText("");
        tree_l_three_b_customer_spillby.setText("");
        tree_l_three_c_customerid.setText("");
        tree_l_three_c_customer_name.setText("");
        tree_l_three_c_customer_spillby.setText("");
        tree_l_three_d_customerid.setText("");
        tree_l_three_d_customer_name.setText("");
        tree_l_three_d_customer_spillby.setText("");

        tree_l_four_one_customerid.setText("");
        tree_l_four_two_customerid.setText("");
        tree_l_four_three_customerid.setText("");
        tree_l_four_four_customerid.setText("");
        tree_l_four_five_customerid.setText("");
        tree_l_four_six_customerid.setText("");
        tree_l_four_seven_customerid.setText("");
        tree_l_four_eight_customerid.setText("");

        tree_l_four_one_customer_name.setText("");
        tree_l_four_two_customer_name.setText("");
        tree_l_four_three_customer_name.setText("");
        tree_l_four_four_customer_name.setText("");
        tree_l_four_five_customer_name.setText("");
        tree_l_four_six_customer_name.setText("");
        tree_l_four_seven_customer_name.setText("");
        tree_l_four_eight_customer_name.setText("");

        tree_l_four_one_customer_spillby.setText("");
        tree_l_four_two_customer_spillby.setText("");
        tree_l_four_three_customer_spillby.setText("");
        tree_l_four_four_customer_spillby.setText("");
        tree_l_four_five_customer_spillby.setText("");
        tree_l_four_six_customer_spillby.setText("");
        tree_l_four_seven_customer_spillby.setText("");
        tree_l_four_eight_customer_spillby.setText("");
    }
}