package com.orgaes.ls;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;

/*
 * Purpose – Activity_AboutUS is an activity about LUCKY SCAN
 * @author SHANIL
 * Created on 21-5-2020
 */
public class Activity_AboutUS extends AppCompatActivity {

    TextView txtAboutus;
    String strAboutusContent, str_SignIn;
    ConstraintLayout constr_ScanLuck;
    ConstraintLayout constraintLayoutmain;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        /*
        * Animation, when activity launches
        * Activity full screen
        * */
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_aboutus);

        bindControlls();
    }

    private void bindControlls() {
        txtAboutus =findViewById(R.id.aboutus);
        constr_ScanLuck = findViewById(R.id.scan_luck);
        constraintLayoutmain = findViewById(R.id.constraintLayoutmain);
        constr_ScanLuck.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.btn_click);
            mp.start();
            Intent inhome=new Intent(getApplicationContext(), Activity_HomePage.class);
            startActivity(inhome);
            finish();
        });
        /*
         * Adding fonts
         * */
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");
        txtAboutus.setTypeface(dosis);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_AboutUS.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(v -> {
//                        Toast.makeText(Activity_AboutUS.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_AboutUS.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_AboutUS.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        strAboutusContent = prefsnew.getString("AboutusContent", "");
        assert strAboutusContent != null;
        String data= strAboutusContent.replace("]","");
        txtAboutus.setText(data.replace("[",""));
        str_SignIn = prefsnew.getString("SignedIn", "");
    }

    @Override
    public void onBackPressed() {
        Intent in=new Intent(getApplicationContext(),Activity_HomePage.class);
        startActivity(in);
        finish();
    }
}
