package com.hmgreencity.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;
import com.hmgreencity.BuildConfig;
import com.hmgreencity.R;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.common.LoggerUtil;
import com.hmgreencity.constants.BaseActivity;
import com.hmgreencity.fragment.BusinessReport;
import com.hmgreencity.fragment.ChangePassword;
import com.hmgreencity.fragment.Dashboard;
import com.hmgreencity.fragment.Direct;
import com.hmgreencity.fragment.Downline;
import com.hmgreencity.fragment.PayoutLedger;
import com.hmgreencity.fragment.PayoutReport;
import com.hmgreencity.fragment.TopUpList;
import com.hmgreencity.fragment.TopUpListNew;
import com.hmgreencity.fragment.TreeView;
import com.hmgreencity.fragment.UnpaidIncome;
import com.hmgreencity.fragment.ViewProfile;
import com.hmgreencity.model.response.ResponseDashboard;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ContainerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerMenuItems drawerMenuItems;
    @BindView(R.id.img_side_menu)
    ImageButton imgSideMenu;
    @BindView(R.id.txt_userId)
    TextView txt_userId;
    @BindView(R.id.txt_heading)
    TextView txt_heading;
    @BindView(R.id.rl_clickmenu)
    RelativeLayout rlClickmenu;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.img_rank)
    ImageView img_rank;
    @BindView(R.id.img_settings)
    ImageView img_settings;
    @BindView(R.id.txt_rank)
    TextView txt_rank;
    @BindView(R.id.img_profile)
    ImageView img_profile;
    @BindView(R.id.ll_dashboard)
    LinearLayout ll_dashboard;
 @BindView(R.id.ll_not_dashboard)
    LinearLayout ll_not_dashboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ButterKnife.bind(this);
        Toolbar containertoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(containertoolbar);
        navView.setNavigationItemSelectedListener(this);
        View hView = navView.getHeaderView(0);
        drawerMenuItems = new DrawerMenuItems(hView);
        ReplaceFragment(new Dashboard(), "Dashboard");

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @OnClick({R.id.img_side_menu, R.id.rl_clickmenu,R.id.img_settings})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_side_menu:
            case R.id.rl_clickmenu:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                break;
            case R.id.img_settings:
                ReplaceFragment(new ChangePassword(),"Change Password");
        }
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
                AssociateDashBoardTotals();
                ll_dashboard.setVisibility(View.VISIBLE);
                ll_not_dashboard.setVisibility(View.GONE);
            }else{
                ll_dashboard.setVisibility(View.GONE);
                ll_not_dashboard.setVisibility(View.VISIBLE);
            }

            transaction.commitAllowingStateLoss();
        }, 200);
    }

    class DrawerMenuItems {

        DrawerMenuItems(View itemView) {
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.img_profile, R.id.tv_dashboard, R.id.tv_top_up_list,
                R.id.tv_profile, R.id.tv_change_password, R.id.tv_payout_report, R.id.tv_business_report,R.id.tv_upload_kyc,R.id.tv_downline, R.id.tv_payout_ledger,
                R.id.tv_direct,R.id.tv_unpaid_income, R.id.tv_tree_view, R.id.tv_logout,R.id.tv_top_up_list_new})

        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.img_profile:
                    Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.tv_dashboard:
                    if (!(currentFragment instanceof Dashboard))
                        ReplaceFragment(new Dashboard(), "Dashboard");
                    break;
                case R.id.tv_top_up_list:
                    if (!(currentFragment instanceof TopUpList))
                        ReplaceFragment(new TopUpList(), "TopUp List");
                    break;
                case R.id.tv_profile:
                    if (!(currentFragment instanceof ViewProfile))
                        ReplaceFragment(new ViewProfile(), "Profile");
                    break;
                case R.id.tv_upload_kyc:
                        goToActivity(context, KYCUpload.class, null);
                    break;

                case R.id.tv_change_password:
                    if (!(currentFragment instanceof ChangePassword))
                        ReplaceFragment(new ChangePassword(), "Change Password");
                    break;
                case R.id.tv_payout_report:
                    if (!(currentFragment instanceof PayoutReport))
                        ReplaceFragment(new PayoutReport(), "Payout Report");
                    break;
                case R.id.tv_payout_ledger:
                    if (!(currentFragment instanceof PayoutLedger))
                        ReplaceFragment(new PayoutLedger(), "Payout Ledger");
                    break;
                case R.id.tv_unpaid_income:
                    if (!(currentFragment instanceof UnpaidIncome))
                        ReplaceFragment(new UnpaidIncome(), "Unpaid Income");
                    break;
                case R.id.tv_business_report:
                    if (!(currentFragment instanceof BusinessReport))
                        ReplaceFragment(new BusinessReport(), "Business Report");

                    break;
                case R.id.tv_direct:
                    if (!(currentFragment instanceof BusinessReport))
                        ReplaceFragment(new Direct(), "Direct");

                    break;
                case R.id.tv_downline:
                    if (!(currentFragment instanceof BusinessReport))
                        ReplaceFragment(new Downline(), "Downline");

                    break;

                case R.id.tv_tree_view:
                    if (!(currentFragment instanceof TreeView))
                        ReplaceFragment(new TreeView(), "Tree View");
                    break;
                case R.id.tv_logout:
                    logoutDialog(context, Login.class);
                    break;
                    case R.id.tv_top_up_list_new:
                        ReplaceFragment(new TopUpListNew(), "Top Up List New");
                    break;
            }
            drawerLayout.closeDrawers();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (currentFragment instanceof Dashboard) {
            super.onBackPressed();
            finish();
        } else {
            ReplaceFragment(new Dashboard(), "Dashboard");
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        }
    }

    private void AssociateDashBoardTotals() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getUserId());
        Log.e("SEFFDKM",PreferencesManager.getInstance(context).getUserId());
        LoggerUtil.logItem(object);

        Call<ResponseDashboard> call = apiServices.AssociateDashBoardTotals(object);
        call.enqueue(new Callback<ResponseDashboard>() {
            @Override
            public void onResponse(Call<ResponseDashboard> call, Response<ResponseDashboard> response) {
                hideLoading();
                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatus().equalsIgnoreCase("0")) {
                        String abc=response.body().getImageURL();
                        String abc1=response.body().getProfilePic();
                        if(abc.length()>0){
                            String link= BuildConfig.BASE_URL+abc.substring(6);
                            Picasso.with(ContainerActivity.this).load(link).into(img_rank);
                            txt_rank.setText(response.body().getAchiverRank());
                        }else{
                            img_rank.setImageResource(R.drawable.hm_logo);
                            txt_rank.setText("Welcome to HM GROUP OF COMPANY");
                        }
                        if(abc1.length()>0) {
                            String link1= BuildConfig.BASE_URL+abc1.substring(6);
                            Picasso.with(ContainerActivity.this).load(link1).into(img_profile);
                        }else{

                        }
                        txt_userId.setText(PreferencesManager.getInstance(context).getLoginId());
                        txt_heading.setText("Hi!!! "+response.body().getName());
                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {
                    Log.e("SEFFDKM",e.getMessage());
                    showMessage(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseDashboard> call, Throwable t) {
                hideLoading();
            }
        });
    }
}