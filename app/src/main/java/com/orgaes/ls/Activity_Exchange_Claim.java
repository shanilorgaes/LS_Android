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

public class Activity_Exchange_Claim extends AppCompatActivity {

    String Todaysvalue,ExchangeImage,UserCoins,ItemCoinValue,SponsorName,Descrption,item_itemID,Redeemdate,CollectionCenter,EditionName,userid,TotalCoin;

    TextView Editionname;
    TextView TotalCoinsTxt;
    TextView TodayCointxt;
    TextView ItemCoin;
    TextView Sponsor;
    TextView Description;
    TextView RedeemDate;
    TextView Collection;
    TextView TxtDay;
    TextView TxtHour;
    TextView Txtmin;
    TextView TxtSec;

    Button approve;
    ImageView itempic;
    Runnable runnableluck;
    Handler handlerluck;
    ImageView luckboxlay;
    String auth_token;
    ImageView LuckRadarImage;

    //Default Texts
    TextView total_coins,todaysvalue,sponsor,details,redeem,collection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.exchange_item_claim);
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);

        Bundle bn=getIntent().getExtras();
        ExchangeImage=bn.getString("exchange_image");
        ItemCoinValue =bn.getString("totalcoin");
        EditionName =bn.getString("item_edition_name");
        SponsorName=bn.getString("exchange_sponsor");
        Descrption=bn.getString("item_details");
        Redeemdate=bn.getString("item_date");
        userid=bn.getString("userid");
        item_itemID=bn.getString("item_itemID");
        CollectionCenter=bn.getString("item_collection_center");

        Editionname=findViewById(R.id.editionspinner);
        TotalCoinsTxt=findViewById(R.id.coincount);
        TodayCointxt=findViewById(R.id.coinsvalue);
        ItemCoin=findViewById(R.id.itemamount);
        Sponsor=findViewById(R.id.sponsorname);
        Description=findViewById(R.id.details);
        RedeemDate=findViewById(R.id.redeemdate);
        Collection=findViewById(R.id.collection);
        TxtDay=findViewById(R.id.txtDay);
        TxtHour=findViewById(R.id.txtHour);
        Txtmin=findViewById(R.id.txtMinute);
        TxtSec=findViewById(R.id.txtSecond);
        approve=findViewById(R.id.approve);
        itempic=findViewById(R.id.itempic);
        total_coins=findViewById(R.id.textView9);
        todaysvalue=findViewById(R.id.textView10);
        sponsor=findViewById(R.id.ntextView9);
        details=findViewById(R.id.mtextView10);
        redeem=findViewById(R.id.textView11);
        collection=findViewById(R.id.textView12);
        luckboxlay=findViewById(R.id.luckboxlay);
        LuckRadarImage=findViewById(R.id.luckradar);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Exchange_Claim.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Exchange_Claim.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_Exchange_Claim.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Exchange_Claim.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        LuckRadarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_Exchange_Claim.this, R.raw.btn_click);
                mp.start();
                Intent in=new Intent(getApplicationContext(),Activity_LuckRadar.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
            }
        });
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        total_coins.setTypeface(dosis);
        todaysvalue.setTypeface(dosis);
        sponsor.setTypeface(dosis);
        details.setTypeface(dosis);
        redeem.setTypeface(dosis);
        collection.setTypeface(dosis);
        TotalCoinsTxt.setTypeface(dosis);
        TodayCointxt.setTypeface(dosis);
        ItemCoin.setTypeface(dosis);
        Sponsor.setTypeface(dosis);
        Description.setTypeface(dosis);
        RedeemDate.setTypeface(dosis);
        Collection.setTypeface(dosis);
        Editionname.setTypeface(avner);

        Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS + ExchangeImage).into(itempic);

        Editionname.setText(EditionName);
        ItemCoin.setText(ItemCoinValue);
        Sponsor.setText(SponsorName);
        Description.setText(Descrption);
        RedeemDate.setText(Redeemdate);
        Collection.setText(CollectionCenter);

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_Exchange_Claim.this, R.raw.btn_click);
                mp.start();
                LuckClaim(item_itemID);

            }
        });

        luckboxlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_Exchange_Claim.this, R.raw.btn_click);
                mp.start();
                Intent in=new Intent(getApplicationContext(), Activity_LuckBox.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent in =new Intent(getApplicationContext(),Activity_Exchange.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
        finish();
    }

    private void LuckClaim(String id) {
        System.out.println("LS EXCLAIM DATA   "+auth_token+"    "+id);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ClaimDatAModel> call3 = apiInterface.Api_Claim_Exchnage(auth_token, id,"exchangeitem");
        call3.enqueue(new Callback<ClaimDatAModel>() {
            @Override
            public void onResponse(@NotNull Call<ClaimDatAModel> call, @NotNull Response<ClaimDatAModel> response) {

                try {

                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){

                        Toast toast = Toast.makeText(getApplicationContext(),"Successfully claimed your luck", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
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
                                        TxtDay.setText("" + String.format("%02d", days));
                                        TxtHour.setText("" + String.format("%02d", hours));
                                        Txtmin.setText(""
                                                + String.format("%02d", minutes));
                                        TxtSec.setText(""
                                                + String.format("%02d", seconds));
                                    } else {
                                        Toast toast = Toast.makeText(getApplicationContext(),"Its outdated", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
//                                textViewGone();
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
                }catch (Exception e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<ClaimDatAModel> call, Throwable t) {
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
                                TodayCointxt.setText(""+Constants.Const_UserCoinValue);
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

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        UserCoins= prefsnew.getString("UserCoins", "");
        auth_token= prefsnew.getString("auth_token", "");
        int userCoin= (Constants.Const_UserCoins);
        int itemCoin= Integer.parseInt(ItemCoinValue);
        int balance_coin=userCoin-itemCoin;
        TotalCoinsTxt.setText(""+balance_coin);
        CoinRate(auth_token);
//        CoinCount(auth_token);

        ConstraintLayout toolbar = (ConstraintLayout) findViewById(R.id.toolbar2);
        ConstraintLayout MenuIcon=findViewById(R.id.menu_icon);

        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_Exchange_Claim.this, R.raw.btn_click);
                mp.start();
                Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                //overridePendingTransition(R.anim.left_right, R.anim.right_left);
            }
        });
    }




}
