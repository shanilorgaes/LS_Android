package com.orgaes.ls;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_GuestUserMenu extends AppCompatActivity{

    TextView Header;
    ImageView Register_page;
    ImageView scan_luck;
    Button Continue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_menu_layout);
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");
        Header=findViewById(R.id.textView3);
        Register_page=findViewById(R.id.regicon);
        scan_luck=findViewById(R.id.scan_luck);
        Continue=findViewById(R.id.buttons_continue);
        Header.setTypeface(dosis);
        Continue.setTypeface(avner);


        Register_page.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), Activity_Register.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });

        Continue.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), Activity_HomePage.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();
        });
        scan_luck.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), Activity_HomePage.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Intent in=new Intent(getApplicationContext(),Activity_HomePage.class);
        startActivity(in);
        finish();
    }
}
