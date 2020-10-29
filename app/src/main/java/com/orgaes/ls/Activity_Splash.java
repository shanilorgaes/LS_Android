package com.orgaes.ls;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


/*
 * Purpose – Activity_Splash is an activity which is displaying 1 second when app is open
 * @author SHANIL
 * Created on 21-5-2020
 */
public class Activity_Splash extends AppCompatActivity {
    String lang_Select;
    String SignedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        /*
         * changing the footbar color to black
         * */
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        /*
         * Full screen view (hiding status bar)
         * */
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_splash);
        new Handler().postDelayed(() -> {
            /*
             * redirecting  if user selected the language , it will redirect to @Activity_HomePage
             * */
            if (lang_Select.equals("1")) {
                if (SignedIn.equals("1") || SignedIn.equals("2")) {
                    Intent in = new Intent(getApplicationContext(), Activity_HomePage.class);
                    startActivity(in);
                    finish();
                } else {
                    Intent in = new Intent(getApplicationContext(), Activity_Choose_Language.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                    finish();
                }
            } else {

                Intent in = new Intent(getApplicationContext(), Activity_Choose_Language.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                finish();
            }
        }, 1000);


    }

//    @SuppressLint("HardwareIds")
    @Override
    protected void onResume() {
        super.onResume();

        /*
         * fetching the value from sharedpreferences to lang_Select
         * */
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        lang_Select = prefsnew.getString("Lang_Selected", "");
        SignedIn = prefsnew.getString("SignedIn", "");
        /*
         * TO CHECK THE PIXEL DENSITY OF THE DEVICE
         * */
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
    }
    }

