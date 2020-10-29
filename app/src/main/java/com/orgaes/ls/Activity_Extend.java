package com.orgaes.ls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;
import com.orgaes.ls.Adapter.ExtendTime_Adapter;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.Functions.SpeedyLinearLayoutManager;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCountData;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCount_Response;
import com.orgaes.ls.RETROFIT_NEW.ExtendTime.Extend_TimeResponse;
import com.orgaes.ls.RETROFIT_NEW.ExtendTime.Extend_Time_Module;
import com.orgaes.ls.RETROFIT_NEW.ExtendTime.Extend_Times_Extend;
import com.orgaes.ls.RETROFIT_NEW.POSTEXTEND.POstExtend_Response;
import com.orgaes.ls.RETROFIT_NEW.POSTEXTEND.PostExt_Module;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Extend extends AppCompatActivity {

    TextView HeaderTxt,ActualTxt,DateTimetxt,Coinshandtxt,Coinstotaltxt,ValidCount_txt,EditionName;
    String auth_token;
    String ID;
    String image,Edition,Date;
    List<Extend_Times_Extend>extend_times_extends;
    Extend_Times_Extend extend_times_extend;
    RecyclerView ex_time;
    ExtendTime_Adapter mAdapter;
    ImageView ItemPic;
    ProgressBar progressbar;
    TextView txtDay,txtHour,txtMinute,txtSecond,ex_buttontxt;
    ConstraintLayout Extend_btn;
    Handler handlerluck;
    Runnable runnableluck;
    ImageView Menu_Icon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extend_draw);
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        auth_token= prefsnew.getString("auth_token", "");
        Bundle bn=getIntent().getExtras();
        assert bn != null;
        ID=bn.getString("id");
        image=bn.getString("image");
        Edition=bn.getString("EditionName");
        Date=bn.getString("Date");
        HeaderTxt=findViewById(R.id.header_txt);
        ActualTxt=findViewById(R.id.textView25);
        DateTimetxt=findViewById(R.id.textView30);
        EditionName=findViewById(R.id.textView29);
        Coinshandtxt=findViewById(R.id.coinsonhand_txt);
        Coinstotaltxt=findViewById(R.id.coins_total);
        ValidCount_txt=findViewById(R.id.valid_count_txt);
        progressbar=findViewById(R.id.progressbar);
        ItemPic=findViewById(R.id.itempic);
        Menu_Icon=findViewById(R.id.menu_icon);
        ex_buttontxt=findViewById(R.id.ex_buttontxt);
        ex_time=findViewById(R.id.ex_time);
        txtDay=findViewById(R.id.txtDay);
        txtHour=findViewById(R.id.txtHour);
        txtMinute=findViewById(R.id.txtMinute);
        txtSecond=findViewById(R.id.txtSecond);
        Extend_btn=findViewById(R.id.generate_barcode);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Extend.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Extend.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_Extend.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Extend.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");
        Menu_Icon.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.btn_click);
            mp.start();
            Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });
        Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS+image).into(ItemPic);
        EditionName.setText(Edition);
        DateTimetxt.setText(""+Date);
        HeaderTxt.setTypeface(dosis);
        ex_buttontxt.setTypeface(dosis);
        ActualTxt.setTypeface(dosis);
        DateTimetxt.setTypeface(dosis);
        EditionName.setTypeface(dosis);
        Coinshandtxt.setTypeface(dosis);
        Coinstotaltxt.setTypeface(dosis);
        ValidCount_txt.setTypeface(dosis);
        CoinCount(auth_token);
        get_ExtendTimes(auth_token,ID);
        Extend_btn.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.btn_click);
            mp.start();
            System.out.println("LS EXT VALUES   "+Constants.CONST_EXTVALUE);
            if (Constants.CONST_EXTVALUE.equals("")){
                Toast.makeText(Activity_Extend.this, "Please select any timezone", Toast.LENGTH_SHORT).show();
            }else {
                extend_Redeem();
            }
        });
    }

    private void CoinCount(String token){
        progressbar.setVisibility(View.VISIBLE);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CoinCountData> call3 = apiInterface.Fetch_Coincount(token);
        call3.enqueue(new Callback<CoinCountData>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<CoinCountData> call, @NotNull Response<CoinCountData> response) {
                progressbar.setVisibility(View.GONE);
                try {
                    int Code=response.body().getCode();
                    System.out.println("LS COIN RATE 1 "+Code);
                    if (Code==200){

                        CoinCount_Response coinRate_response=response.body().getResponse();
                        Constants.Const_UserCoins= Integer.parseInt(coinRate_response.getCoinCount());
                        Coinstotaltxt.setText(""+Integer.parseInt(coinRate_response.getCoinCount()));
                    }


                }catch (Exception ignored){
                    ignored.printStackTrace();
                    System.out.println("LS COIN RATE 2 "+ignored.getMessage());
                }

            }

            @Override
            public void onFailure(@NotNull Call<CoinCountData> call, @NotNull Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                progressbar.setVisibility(View.GONE);
                System.out.println("LS COIN RATE 3 "+t.getMessage());
            }
        });

    }

    private void get_ExtendTimes(String token, String id){
        progressbar.setVisibility(View.VISIBLE);
        APIInterface apiInterface=APIClient.getClient().create(APIInterface.class);
        Call<Extend_Time_Module>call=apiInterface.Fetch_ExtendTimes(token, id);
        call.enqueue(new Callback<Extend_Time_Module>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<Extend_Time_Module> call, @NotNull Response<Extend_Time_Module> response) {
                try {
                    progressbar.setVisibility(View.GONE);
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){

                        Extend_TimeResponse extend_response=response.body().getResponse();
                        extend_times_extends=extend_response.getExtends();
                        ValidCount_txt.setText("VALID ONLY "+extend_times_extends.size()+" TIMES");
                        if (extend_times_extends.size()>0){
                            for (int i=0;i<extend_times_extends.size();i++){

                                extend_times_extend=extend_times_extends.get(i);

                            }
                            mAdapter = new ExtendTime_Adapter(ex_buttontxt,auth_token,extend_times_extends, Activity_Extend.this,getApplicationContext(), position -> mAdapter.put_colors(position));
                            ex_time.setLayoutManager(new SpeedyLinearLayoutManager(Activity_Extend.this, SpeedyLinearLayoutManager.VERTICAL, false));
                            ex_time.setItemAnimator(new DefaultItemAnimator());
                            ex_time.setAdapter(mAdapter);
                        }else {
                            Toast.makeText(Activity_Extend.this, "No Extend Times Available", Toast.LENGTH_SHORT).show();
                        }
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Extend_Time_Module> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                progressbar.setVisibility(View.GONE);
            }
        });

    }

    private void extend_Redeem() {
        System.out.println("LS ID ::::  "+ID+"         "+Constants.CONST_EXTVALUE+"   "+auth_token);
        progressbar.setVisibility(View.VISIBLE);
//        Extend_btn.setEnabled(false);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PostExt_Module> call3 = apiInterface.PostExtend(auth_token, ID, Constants.CONST_EXTVALUE);
        call3.enqueue(new Callback<PostExt_Module>() {
            @Override
            public void onResponse(@NotNull Call<PostExt_Module> call, @NotNull Response<PostExt_Module> response) {
                progressbar.setVisibility(View.GONE);
//                Extend_btn.setEnabled(false);
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        CoinCount(auth_token);
                        POstExtend_Response claim_response=response.body().getResponse();
                        Constants.CONST_EXTVALUE="";
                        new SweetAlertDialog(Activity_Extend.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("SUCCESS")
                                .setContentText("Extended Successfully")
                                .setConfirmText("CLOSE")

                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                    }
                                })
                                .show();

                        handlerluck = new Handler();
                        runnableluck = new Runnable() {
                            @SuppressLint({"SetTextI18n", "DefaultLocale"})
                            @Override
                            public void run() {
                                handlerluck.postDelayed(this, 1000);
                                try {
                                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(
                                            "dd-MM-yyyy hh:mm:ss");
                                    // Please here set your event date//YYYY-MM-DD
                                    Date futureDate = dateFormat.parse(claim_response.getEndDate());
                                    DateTimetxt.setText(claim_response.getEndDate());
                                    Date currentDate = new Date();
                                    if (!currentDate.after(futureDate)) {
                                        assert futureDate != null;
                                        long diff = futureDate.getTime()
                                                - currentDate.getTime();
                                        long days = diff / (24 * 60 * 60 * 1000);
                                        diff -= days * (24 * 60 * 60 * 1000);
                                        long hours = diff / (60 * 60 * 1000);
                                        diff -= hours * (60 * 60 * 1000);
                                        long minutes = diff / (60 * 1000);
                                        diff -= minutes * (60 * 1000);
                                        long seconds = diff / 1000;
                                        txtDay.setText("" + String.format("%02d", days));
                                        txtHour.setText("" + String.format("%02d", hours));
                                        txtMinute.setText(""+ String.format("%02d", minutes));
                                        txtSecond.setText(""+ String.format("%02d", seconds));
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        handlerluck.postDelayed(runnableluck, 1000);
                    }else if (Code==201){

                        Toast.makeText(getApplicationContext(), "Claiming Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==203){

                        Toast.makeText(getApplicationContext(), "Sorry Stock Is Not Available", Toast.LENGTH_SHORT).show();

                    }else if (Code==205){
                        new SweetAlertDialog(Activity_Extend.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("OH HO !")
                                .setContentText("STOCK IS NOT AVAILABLE")
                                .setConfirmText("CLOSE")

                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                    }
                                })
                                .show();
//                        Toast.makeText(getApplicationContext(), "Insufficient Coin", Toast.LENGTH_SHORT).show();

                    }else if (Code==207){

                        Toast.makeText(getApplicationContext(), "Validation Error", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(getApplicationContext(), Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intlogout);
                    }else if (Code==301){

                        Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(getApplicationContext(), Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intlogout);
                    }else if (Code==303){

                        Toast.makeText(getApplicationContext(), "Session Expired", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(getApplicationContext(), Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intlogout);
                    }else if (Code==305){

                        Toast.makeText(getApplicationContext(), "Token Missing", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(getApplicationContext(), Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intlogout);
                    }
                }catch (Exception e){
                    System.out.println("LS EXCEPTIONS   "+e.getMessage());
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
//                    Extend_btn.setEnabled(true);
                }
            }
            @Override
            public void onFailure(@NotNull Call<PostExt_Module> call, @NotNull Throwable t) {
                System.out.println("LS EXCEPTIONS 2  "+t.getMessage());
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                progressbar.setVisibility(View.GONE);
//                Extend_btn.setEnabled(true);
            }
        });
    }

}
