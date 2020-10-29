package com.orgaes.ls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.QRCODE.QRSCANNER;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCountData;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCount_Response;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRate;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRateData;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRate_Response;
import com.orgaes.ls.RETROFIT_NEW.GETWallet.WalletData;
import com.orgaes.ls.RETROFIT_NEW.GETWallet.WalletResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Wallet extends AppCompatActivity {

    TextView Txt1;
    TextView Txt2;
    TextView Txt3;
    TextView Txt4;
    TextView Txt5;
    TextView Txt6;
    TextView Txt7;
    TextView Txt8;
    TextView Txt9;

    TextView Txt1value;
    TextView Txt2coins;
    TextView Txt3Reqtowd;
    TextView Txt4Cashonhand;
    TextView Txt5shortagetoWd;
    TextView Txt6Cureligibility;
    TextView Txt7Lastwdcash;
    TextView Txt8Totuptonow;

    String auth_token;
    ProgressBar progressbar;
    ConstraintLayout RedeemButton;
    ImageView ScanIcon;
    ImageView CoinRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity__wallet2);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Wallet.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Wallet.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_Wallet.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Wallet.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        Txt1=findViewById(R.id.txt1);
        Txt2=findViewById(R.id.txt2);
        Txt3=findViewById(R.id.txt3);
        Txt4=findViewById(R.id.txt4);
        Txt5=findViewById(R.id.txt5);
        Txt6=findViewById(R.id.txt6);
        Txt7=findViewById(R.id.txt7);
        Txt8=findViewById(R.id.txt8);
        Txt9=findViewById(R.id.txt9);

        Txt1value=findViewById(R.id.txt10);
        Txt2coins=findViewById(R.id.txt11);
        Txt3Reqtowd=findViewById(R.id.txt12);
        Txt4Cashonhand=findViewById(R.id.txt13);
        Txt5shortagetoWd=findViewById(R.id.txt14);
        Txt6Cureligibility=findViewById(R.id.txt15);
        Txt7Lastwdcash=findViewById(R.id.txt16);
        Txt8Totuptonow=findViewById(R.id.txt17);
        RedeemButton=findViewById(R.id.constraintLayout85);
        progressbar=findViewById(R.id.progressbar);
        ScanIcon=findViewById(R.id.scanforcoin);
        CoinRequest=findViewById(R.id.coinreq);

        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        Txt1.setTypeface(dosis);
        Txt2.setTypeface(avner);
        Txt3.setTypeface(avner);
        Txt4.setTypeface(avner);
        Txt5.setTypeface(avner);
        Txt6.setTypeface(avner);
        Txt7.setTypeface(avner);
        Txt8.setTypeface(avner);
        Txt9.setTypeface(avner);

        Txt1value.setTypeface(dosis);
        Txt2coins.setTypeface(dosis);
        Txt3Reqtowd.setTypeface(dosis);
        Txt4Cashonhand.setTypeface(dosis);
        Txt5shortagetoWd.setTypeface(dosis);
        Txt6Cureligibility.setTypeface(dosis);
        Txt7Lastwdcash.setTypeface(dosis);
        Txt8Totuptonow.setTypeface(dosis);
        ConstraintLayout MenuIcon=findViewById(R.id.menu_icon);

        MenuIcon.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(Activity_Wallet.this, R.raw.btn_click);
            mp.start();
            Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            //overridePendingTransition(R.anim.left_right, R.anim.right_left);
        });
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        auth_token= prefsnew.getString("auth_token", "");

        CoinCount(auth_token);

        RedeemButton.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(Activity_Wallet.this, R.raw.btn_click);
            mp.start();
            Intent in=new Intent(getApplicationContext(),Activity_Redeem.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);

        });

        ScanIcon.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(Activity_Wallet.this, R.raw.btn_click);
            mp.start();
            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
            editor.putString("QR_SCANNED", "NOT_FINISHED");
            editor.putString("ADS_TYPE", "MAIN_ADS");
            editor.apply();
            Intent in=new Intent(getApplicationContext(), QRSCANNER.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);
            finish();
        });

        CoinRequest.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(Activity_Wallet.this, R.raw.btn_click);
            mp.start();
            Intent in = new Intent(getApplicationContext(), Activity_ProfileView.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
            editor.putString("from_wallet", "1");
            editor.apply();
            startActivity(in);
        });
    }

    private void fetch_Wallet(String token) {
        progressbar.setVisibility(View.VISIBLE);
            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<WalletData> call3 = apiInterface.Fetch_Wallet(token);
            call3.enqueue(new Callback<WalletData>() {
                @Override
                public void onResponse(@NotNull Call<WalletData> call, @NotNull Response<WalletData> response) {
                    progressbar.setVisibility(View.GONE);
                    try {
                        int Code=response.body().getCode();
                        System.out.println("LS COIN RATE 1 "+Code);
                        if (Code==200){

                            WalletResponse coinRate_response=response.body().getResponse();
                            Txt3Reqtowd.setText(coinRate_response.getMinCoinsRequires());
                            Txt5shortagetoWd.setText("");
                            Txt6Cureligibility.setText("");
                            Txt7Lastwdcash.setText(coinRate_response.getLastRedeemMoney());
                            Txt8Totuptonow.setText(coinRate_response.getEarnedMoney());
                            Txt4Cashonhand.setText(""+coinRate_response.getEarnedMoney());
                        }


                    }catch (Exception ignored){
                        ignored.printStackTrace();
                        System.out.println("LS COIN RATE 2 "+ignored.getMessage());
                    }

                }

                @Override
                public void onFailure(@NotNull Call<WalletData> call, @NotNull Throwable t) {
                    t.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    System.out.println("LS COIN RATE 3 "+t.getMessage());
                    progressbar.setVisibility(View.GONE);
                }
            });



    }

    private void CoinRate(String token){
        progressbar.setVisibility(View.VISIBLE);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CoinRateData> call3 = apiInterface.Fetch_Coinrate(token);
        call3.enqueue(new Callback<CoinRateData>() {
            @Override
            public void onResponse(@NotNull Call<CoinRateData> call, @NotNull Response<CoinRateData> response) {
                progressbar.setVisibility(View.GONE);
                try {
                    int Code=response.body().getCode();
                    System.out.println("LS COIN RATE 1 "+Code);
                    if (Code==200){

                        CoinRate_Response coinRate_response=response.body().getResponse();
                        List<CoinRate>coinRates=coinRate_response.getCoinRate();
                        if (coinRates.size()>0){
                            for (int i=0;i<coinRates.size();i++){
                                CoinRate coinRate=coinRates.get(i);
                                Constants.Const_UserCoinValue= (coinRate.getCoinValue());
                                Txt1value.setText(""+Constants.Const_UserCoinValue);
                                fetch_Wallet(token);
                            }
                            System.out.println("LS COIN RATE 2 "+Constants.Const_UserCoins);
                        }

                    }else {
                    }


                }catch (Exception ignored){
                    ignored.printStackTrace();
                    System.out.println("LS COIN RATE 2 "+ignored.getMessage());
                    progressbar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(@NotNull Call<CoinRateData> call, @NotNull Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                System.out.println("LS COIN RATE 3 "+t.getMessage());
                progressbar.setVisibility(View.GONE);
            }
        });

    }
    private void CoinCount(String token){
        progressbar.setVisibility(View.VISIBLE);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CoinCountData> call3 = apiInterface.Fetch_Coincount(token);
        call3.enqueue(new Callback<CoinCountData>() {
            @Override
            public void onResponse(@NotNull Call<CoinCountData> call, @NotNull Response<CoinCountData> response) {
                progressbar.setVisibility(View.GONE);
                try {
                    int Code=response.body().getCode();
                    System.out.println("LS COIN RATE 1 "+Code);
                    if (Code==200){

                        CoinCount_Response coinRate_response=response.body().getResponse();
                        Constants.Const_UserCoins= Integer.parseInt(coinRate_response.getCoinCount());
                        Txt2coins.setText(""+Constants.Const_UserCoins);
                        CoinRate(token);
                    }else {
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

                System.out.println("LS COIN RATE 3 "+t.getMessage());
                progressbar.setVisibility(View.GONE);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}