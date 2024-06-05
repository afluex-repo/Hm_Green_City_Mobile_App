package com.hmgreencity.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.hmgreencity.R;
import com.hmgreencity.app.PreferencesManager;
import com.hmgreencity.constants.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Splash extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ButterKnife.bind(this);

        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.delay_zoom_in);
        imageView.startAnimation(aniSlide);

        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = pInfo.versionName;
            // tvVersion.setText(getString(R.string.version) + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(() -> {
            if (!PreferencesManager.getInstance(context).getUserId().equalsIgnoreCase("")) {
                Log.e("LOGINGGD",PreferencesManager.getInstance(context).getUserId());
                goToActivityWithFinish(context, ContainerActivity.class, null);
            } else {
            goToActivityWithFinish(context, Login.class, null);
        }
        }, 2500);
    }
}