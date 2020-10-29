package com.orgaes.ls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;import androidx.appcompat.app.AppCompatActivity;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.RETROFIT_NEW.ClaimData.ClaimDatAModel;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCountData;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCount_Response;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRate;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRateData;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRate_Response;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_LuckRadar_Claim extends AppCompatActivity {

    TextView txtDay, txtHour, txtMinute, txtSecond,editionspinner;

    Handler handlerluck;
    Runnable runnableluck;
    Intent intent;
    String SessionID;
    Button Claim_now;
    String auth_token;
    ImageView LuckRadarImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.radar_item_claim);

        intent=getIntent();
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        auth_token= prefsnew.getString("auth_token", "");
        ImageView luckbox=findViewById(R.id.luckboxbox);
        ImageView luckimage=findViewById(R.id.itempic);
        Claim_now=findViewById(R.id.approve);

        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_LuckRadar_Claim.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_LuckRadar_Claim.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_LuckRadar_Claim.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_LuckRadar_Claim.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        TextView total_coinstxt=findViewById(R.id.total_coinstxt);
        TextView coinsvaluetxt=findViewById(R.id.coinsvaluetxt);
        TextView sponsortxt=findViewById(R.id.sponsortxt);
        TextView detailstxt=findViewById(R.id.detailstxt);
        TextView redeemtxt=findViewById(R.id.redeemtxt);
        TextView collectiontxt=findViewById(R.id.collectiontxt);
        TextView total_coins=findViewById(R.id.total_coins);
        TextView coinsvalue=findViewById(R.id.coinsvalue);
        TextView sponsorname=findViewById(R.id.sponsorname);
        TextView details=findViewById(R.id.details);
        TextView collection=findViewById(R.id.collection);
        TextView editionspinner=findViewById(R.id.editionspinner);
        TextView itemamount=findViewById(R.id.itemamount);

        total_coinstxt.setTypeface(avner);
        coinsvaluetxt.setTypeface(avner);
        sponsortxt.setTypeface(avner);
        detailstxt.setTypeface(avner);
        redeemtxt.setTypeface(avner);
        collectiontxt.setTypeface(avner);
        total_coins.setTypeface(avner);
        coinsvalue.setTypeface(avner);
        sponsorname.setTypeface(avner);
        details.setTypeface(avner);
        collection.setTypeface(avner);
        editionspinner.setTypeface(avner);
        LuckRadarImage=findViewById(R.id.luckradar);
        LuckRadarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_LuckRadar_Claim.this, R.raw.btn_click);
                mp.start();
                Intent in=new Intent(getApplicationContext(),Activity_LuckRadar.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
            }
        });
        editionspinner.setText(intent.getStringExtra("itemName"));

        luckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_LuckRadar_Claim.this, R.raw.btn_click);
                mp.start();
                Intent in=new Intent(getApplicationContext(), Activity_LuckBox.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);

            }
        });
        sponsorname.setText(intent.getStringExtra("sponsorname"));
        details.setText(intent.getStringExtra("details"));
        redeemtxt.setText(intent.getStringExtra("redeemdate"));
        total_coins.setText(intent.getStringExtra("balance_coin"));
        coinsvalue.setText(""+Constants.Const_UserCoinValue);
        collection.setText(intent.getStringExtra("collectioncenter"));
        itemamount.setText(intent.getStringExtra("itemamount"));

        Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS+intent.getStringExtra("itempic")).into(luckimage);



        txtDay = (TextView) findViewById(R.id.txtDay);
        txtHour = (TextView) findViewById(R.id.txtHour);
        txtMinute = (TextView) findViewById(R.id.txtMinute);
        txtSecond = (TextView) findViewById(R.id.txtSecond);
        Claim_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_LuckRadar_Claim.this, R.raw.btn_click);
                mp.start();
                luckbox.setVisibility(View.VISIBLE);
                LuckClaim(intent.getStringExtra("lrdi"),txtDay,txtHour,txtMinute,txtSecond);

            }
        });
        ConstraintLayout MenuIcon=findViewById(R.id.menu_icon);

        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_LuckRadar_Claim.this, R.raw.btn_click);
                mp.start();
                Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                //overridePendingTransition(R.anim.left_right, R.anim.right_left);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        SessionID= prefsnew.getString("UserID", "");
    }

    private void LuckClaim(String lrId, TextView txtDay, TextView txtHour, TextView txtMinute, TextView txtSecond) {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ClaimDatAModel> call3 = apiInterface.Api_radarClaim(auth_token,lrId,"luckradar" );
        call3.enqueue(new Callback<ClaimDatAModel>() {
            @Override
            public void onResponse(@NotNull Call<ClaimDatAModel> call, @NotNull Response<ClaimDatAModel> response) {

                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        CoinRate(auth_token);
                        CoinCount(auth_token);
                        Claim_now.setVisibility(View.GONE);
                        Claim_now.setEnabled(false);
                        Toast toast = Toast.makeText(getApplicationContext(),"Successfully claimed your luck", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        System.out.println("LS CLAIMDATE   "+response.body().getResponse().getEndDate());
                        handlerluck = new Handler();
                        runnableluck = new Runnable() {
                            @SuppressLint({"DefaultLocale", "SetTextI18n"})
                            @Override
                            public void run() {
                                handlerluck.postDelayed(this, 1000);
                                try {
                                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(
                                            "dd-MM-yyyy hh:mm:ss");
                                    // Please here set your event date//YYYY-MM-DD
                                    Date futureDate = dateFormat.parse(response.body().getResponse().getEndDate());
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
                                        txtMinute.setText(""
                                                + String.format("%02d", minutes));
                                        txtSecond.setText(""
                                                + String.format("%02d", seconds));
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

                        Toast.makeText(getApplicationContext(), "Insufficient Coin", Toast.LENGTH_SHORT).show();

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
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            @Override
            public void onFailure(@NotNull Call<ClaimDatAModel> call, @NotNull Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });
    }


    private void CoinRate(String token){

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CoinRateData> call3 = apiInterface.Fetch_Coinrate(token);
        call3.enqueue(new Callback<CoinRateData>() {
            @Override
            public void onResponse(@NotNull Call<CoinRateData> call, @NotNull Response<CoinRateData> response) {

                try {
                    int Code=response.body().getCode();
                    System.out.println("LS COIN RATE 1 "+Code);
                    if (Code==200){

                        CoinRate_Response coinRate_response=response.body().getResponse();
                        List<CoinRate> coinRates=coinRate_response.getCoinRate();
                        if (coinRates.size()>0){
                            for (int i=0;i<coinRates.size();i++){
                                CoinRate coinRate=coinRates.get(i);
                                Constants.Const_UserCoinValue= (coinRate.getCoinValue());
//                                Toast.makeText(Activity_HomePage.this, "Coin Collected", Toast.LENGTH_SHORT).show();
                            }
                            System.out.println("LS COIN RATE 2 "+Constants.Const_UserCoins);
                        }

                    }else {
                        Toast.makeText(getApplicationContext(), "Coin Error", Toast.LENGTH_SHORT).show();
                    }


                }catch (Exception ignored){
                    ignored.printStackTrace();
                    System.out.println("LS COIN RATE 2 "+ignored.getMessage());
                }

            }

            @Override
            public void onFailure(@NotNull Call<CoinRateData> call, @NotNull Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                System.out.println("LS COIN RATE 3 "+t.getMessage());
            }
        });

    }
    private void CoinCount(String token){

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CoinCountData> call3 = apiInterface.Fetch_Coincount(token);
        call3.enqueue(new Callback<CoinCountData>() {
            @Override
            public void onResponse(@NotNull Call<CoinCountData> call, @NotNull Response<CoinCountData> response) {

                try {
                    int Code=response.body().getCode();
                    System.out.println("LS COIN RATE 1 "+Code);
                    if (Code==200){

                        CoinCount_Response coinRate_response=response.body().getResponse();
                        Constants.Const_UserCoins= Integer.parseInt(coinRate_response.getCoinCount());
                    }else {
                        Toast.makeText(getApplicationContext(), "Coin Error", Toast.LENGTH_SHORT).show();
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
            }
        });

    }


}
