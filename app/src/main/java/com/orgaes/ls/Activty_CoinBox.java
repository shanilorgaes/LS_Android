package com.orgaes.ls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;import androidx.appcompat.app.AppCompatActivity;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.RETROFIT_NEW.CoinData.CoinDATA_Model;
import com.orgaes.ls.RETROFIT_NEW.CoinData.CoinData_Response;


import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;


/*
 * Purpose – Activty_CoinBox is an activity which the user can check their coin box and expire date
 * @author SHANIL
 * Created on 21-5-2020
 */
public class Activty_CoinBox extends AppCompatActivity {

    String UserID,auth_token;

    TextView CoinValueTXT,CoinLastEarnedTXT,CoinTOTALTXT,CoinExpTXT,Shared_Coins,Req_Coins,Exchange_Coins,Claimed_Coins,Ex_For_Money,Used_Coins,Expired_Coins,PendingDays;

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
        setContentView(R.layout.layout_coinbox);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activty_CoinBox.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activty_CoinBox.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activty_CoinBox.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activty_CoinBox.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        CoinValueTXT=findViewById(R.id.coinvalue);
        CoinLastEarnedTXT=findViewById(R.id.coinlastearned);
        CoinTOTALTXT=findViewById(R.id.totalcoin);
        CoinExpTXT=findViewById(R.id.coinexpiry);
        Shared_Coins=findViewById(R.id.sharedcoins);
        Req_Coins=findViewById(R.id.requested_coins);
        Exchange_Coins=findViewById(R.id.exchange_coins);
        Claimed_Coins=findViewById(R.id.claimedcoins);
        Ex_For_Money=findViewById(R.id.ex_for_money);
        Used_Coins=findViewById(R.id.usedcoins);
        Expired_Coins=findViewById(R.id.ztextView34);
        PendingDays=findViewById(R.id.date_pending);

        TextView coinnewtotal_coins=findViewById(R.id.coinnewtotal_coins);
        TextView coinnew_value=findViewById(R.id.coinnew_value);
        TextView coinnew_expdate=findViewById(R.id.coinnew_expdate);
        TextView coinnewshared_coins=findViewById(R.id.coinnewshared_coins);
        TextView  coinnew_textView33=findViewById(R.id.coinnew_textView33);
        TextView coinnew_atextView33=findViewById(R.id.coinnew_atextView33);
        TextView coinnew_btextView33=findViewById(R.id.coinnew_btextView33);
        TextView coinnew_ctextView33=findViewById(R.id.coinnew_ctextView33);
        TextView  coinnew_dtextView33=findViewById(R.id.coinnew_dtextView33);
        TextView coinnew_coins_used=findViewById(R.id.coinnew_coins_used);
        TextView coins_expired=findViewById(R.id.coins_expired);



        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        CoinValueTXT.setTypeface(dosis);
        CoinLastEarnedTXT.setTypeface(dosis);
        CoinTOTALTXT.setTypeface(dosis);
        CoinExpTXT.setTypeface(dosis);
        Shared_Coins.setTypeface(dosis);
        Req_Coins.setTypeface(dosis);
        Exchange_Coins.setTypeface(dosis);
        Claimed_Coins.setTypeface(dosis);
        Ex_For_Money.setTypeface(dosis);
        Used_Coins.setTypeface(dosis);
        Expired_Coins.setTypeface(dosis);
        PendingDays.setTypeface(dosis);

        coinnewtotal_coins.setTypeface(avner);
        coinnew_value.setTypeface(avner);
        coinnew_expdate.setTypeface(avner);
        coinnewshared_coins.setTypeface(avner);
        coinnew_textView33.setTypeface(avner);
        coinnew_atextView33.setTypeface(avner);
        coinnew_btextView33.setTypeface(avner);
        coinnew_ctextView33.setTypeface(avner);
        coinnew_dtextView33.setTypeface(avner);
        coinnew_coins_used.setTypeface(avner);
        coins_expired.setTypeface(avner);
        ImageView Scan_Luck=findViewById(R.id.scan_luck);
        Scan_Luck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inhome=new Intent(getApplicationContext(), Activity_HomePage.class);
                startActivity(inhome);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        UserID= prefsnew.getString("UserID", "");
        String Userpic= prefsnew.getString("Userpic", "");

        ConstraintLayout toolbar = (ConstraintLayout) findViewById(R.id.toolbar2);
        ConstraintLayout MenuIcon=findViewById(R.id.menu_icon);
        auth_token= prefsnew.getString("auth_token", "");
        System.out.println("LS AUTH KEY 1 "+auth_token);
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                //overridePendingTransition(R.anim.left_right, R.anim.right_left);
            }
        });
        Fetch_Coins(auth_token);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    private void Fetch_Coins(String token) {
        System.out.println("LS AUTH KEY  "+ token);
        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CoinDATA_Model> call3 = apiInterface.Fetch_Coins(token);
        call3.enqueue(new Callback<CoinDATA_Model>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<CoinDATA_Model> call, @NotNull retrofit2.Response<CoinDATA_Model> response) {
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        CoinData_Response response1=response.body().getResponse();
                        System.out.println("LS COIN BOX VALUES  1 "+response1.getCoinValue());
                        System.out.println("LS COIN BOX VALUES  2 "+response1.getCoinTotal());
                        System.out.println("LS COIN BOX VALUES  3 "+response1.getCoinExpiryDate());
                        System.out.println("LS COIN BOX VALUES  4 "+response1.getCoinShared());
                        System.out.println("LS COIN BOX VALUES  5 "+response1.getCoinRequested());
                        System.out.println("LS COIN BOX VALUES  6 "+response1.getCoinExchanged());
                        System.out.println("LS COIN BOX VALUES  7 "+response1.getCoinLuckRadarClaimed());
                        System.out.println("LS COIN BOX VALUES  8 "+response1.getCoinWalletMoney());
                        System.out.println("LS COIN BOX VALUES  9 "+response1.getCoinUsed());
                        System.out.println("LS COIN BOX VALUES  10 "+response1.getCoinTotalExpired());
                        System.out.println("LS COIN BOX VALUES  11 "+response1.getCoinLastEarned());
                        int last= Integer.parseInt((response1.getCoinLastEarned()));
                        int total= Integer.parseInt((response1.getCoinTotal()));
                        int prevcoin=total-last;
                        CoinValueTXT.setText(response1.getCoinValue());
                        CoinLastEarnedTXT.setText(prevcoin+"+"+last+"="+total);
                        CoinTOTALTXT.setText(response1.getCoinTotal());
                        CoinExpTXT.setText(response1.getCoinExpiryDate());
                        Shared_Coins.setText(response1.getCoinShared());
                        Req_Coins.setText(response1.getCoinRequested());
                        Exchange_Coins.setText(response1.getCoinExchanged());
                        Claimed_Coins.setText(response1.getCoinLuckRadarClaimed());
                        Ex_For_Money.setText(response1.getCoinWalletMoney());
                        Used_Coins.setText(""+response1.getCoinUsed());
                        Expired_Coins.setText(response1.getCoinTotalExpired());
                        @SuppressLint("SimpleDateFormat") Date userDob = new SimpleDateFormat("dd-MM-yyyy").parse(response1.getCoinExpiryDate());
                        Date today = new Date();
                        assert userDob != null;
                        long diff =  userDob.getTime()-today.getTime();
                        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
                        int hours = (int) (diff / (1000 * 60 * 60));
                        int minutes = (int) (diff / (1000 * 60));
                        int seconds = (int) (diff / (1000));
                        System.out.println("LS COIN DATE PENDING   "+numOfDays);
                        PendingDays.setText(""+numOfDays);
                    }else if (Code==201){

                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

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
                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            @Override
            public void onFailure(@NotNull Call<CoinDATA_Model> call, @NotNull Throwable t) {
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                t.printStackTrace();
            }
        });
    }
}
