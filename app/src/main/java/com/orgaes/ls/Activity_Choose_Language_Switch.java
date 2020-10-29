package com.orgaes.ls;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.orgaes.ls.Adapter.Languages_AdapterSwitch;
import com.orgaes.ls.AppData.APIInterface;

import java.util.ArrayList;
import java.util.List;

/*
 * Purpose – Activity_Choose_Language_Switch is an activity for switch language
 * @author SHANIL
 * Created on 21-5-2020
 */
public class Activity_Choose_Language_Switch extends AppCompatActivity {


    Languages_AdapterSwitch mAdapter;

    RecyclerView Recy_ListLanguages;
    ImageView Top_Arrow,Bottom_Arrow;
    ProgressBar progressBar;

    List<String> Lang_ID=new ArrayList<>();
    List<String>Lang_FLAG=new ArrayList<>();
    List<String>Lang_RTL_LTR=new ArrayList<>();
    List<String>Lang_CODE=new ArrayList<>();
    List<String>Lang_NAME=new ArrayList<>();
    List<String>Lang_DEFAULT=new ArrayList<>();
    List<String>Lang_STATUS=new ArrayList<>();
    APIInterface apiInterface;
    String SwitchString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_choose_language);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        BindControls();

//        Fetch_Languages();

    }
    private void BindControls(){

        Recy_ListLanguages=findViewById(R.id.listview);
        progressBar=findViewById(R.id.progressbar);
        Top_Arrow=findViewById(R.id.uparrow);
        Bottom_Arrow=findViewById(R.id.downarrow);
        progressBar.setVisibility(View.VISIBLE);

        Top_Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Recy_ListLanguages.post(new Runnable() {
                    @Override
                    public void run() {
                        Recy_ListLanguages.scrollToPosition(mAdapter.getItemCount() - 1);
                        // Here adapter.getItemCount()== child count
                    }
                });

            }
        });


        Bottom_Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Recy_ListLanguages.post(new Runnable() {
                    @Override
                    public void run() {
                        Recy_ListLanguages.scrollToPosition(0);
                        // Here adapter.getItemCount()== child count
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        SwitchString= prefsnew.getString("Switch", "");
    }

}
