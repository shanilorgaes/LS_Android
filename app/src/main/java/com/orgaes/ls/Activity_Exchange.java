package com.orgaes.ls;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;import androidx.appcompat.app.AppCompatActivity;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.orgaes.ls.Adapter.ExchangeAdapter;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCountData;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCount_Response;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRate;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRateData;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRate_Response;
import com.orgaes.ls.RETROFIT_NEW.Editions_Data.EditionData;
import com.orgaes.ls.RETROFIT_NEW.Editions_Data.EditionModule;
import com.orgaes.ls.RETROFIT_NEW.Editions_Data.Edition_Response;
import com.orgaes.ls.RETROFIT_NEW.Exchange_Items.EXDATAMODEL;
import com.orgaes.ls.RETROFIT_NEW.Exchange_Items.Ex_Response;
import com.orgaes.ls.RETROFIT_NEW.Exchange_Items.EX_Item;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
 * Purpose – Activity_Exchange is an activty for user can see the exchange details and user can exchange items in edition vice
 * @author SHANIL
 * Created on 21-5-2020
 */
public class Activity_Exchange extends AppCompatActivity {



    APIInterface interfaceApi;
    String str_SignedIn, str_UserId, str_UserCoins;
    RecyclerView recycler_Exchange;
    ExchangeAdapter adapterExchange;
    TextView txtCoins, txtTotCoins, txt_TodayValue, txtCoinValue;;
    Spinner spinnereditions;
    List<String>EditionID=new ArrayList<>();
    List<String>EditionName=new ArrayList<>();
    ProgressBar progressbar;
    String ItemName;
    ImageView Toparrow;
    ImageView Bottomarrow;
    int position2=0;
    ImageView LuckRadarImage;
    String auth_token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_exchange);

        Toparrow=findViewById(R.id.toparrow);
        Bottomarrow=findViewById(R.id.bottomarrow);
        recycler_Exchange =findViewById(R.id.exchangelist);
        spinnereditions=findViewById(R.id.editionspinner);
        txtCoins =findViewById(R.id.coincount);
        txtTotCoins =findViewById(R.id.textView9);
        txtCoinValue =findViewById(R.id.coinsvalue);
        txt_TodayValue =findViewById(R.id.textView10);
        progressbar=findViewById(R.id.progressbar);
        LuckRadarImage=findViewById(R.id.luckradar);
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        String UserID= prefsnew.getString("UserID", "");
        str_SignedIn = prefsnew.getString("SignedIn", "");
        str_UserId = prefsnew.getString("UserID", "");
        str_UserCoins = prefsnew.getString("UserCoins", "");
        auth_token= prefsnew.getString("auth_token", "");
        CoinCount(auth_token);
        CoinRate(auth_token);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Exchange.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Exchange.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(v -> Toast.makeText(Activity_Exchange.this, "Long press on "
                        + v.getTag(), Toast.LENGTH_SHORT).show()).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Exchange.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        LuckRadarImage.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.btn_click);
            mp.start();
            Intent in=new Intent(getApplicationContext(),Activity_LuckRadar.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);
        });
        ImageView Scan_Luck=findViewById(R.id.scan_luck);
        Scan_Luck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_Exchange.this, R.raw.btn_click);
                mp.start();
                Intent inhome=new Intent(getApplicationContext(), Activity_HomePage.class);
                startActivity(inhome);
                finish();
            }
        });


        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        txtTotCoins.setTypeface(avner);
        txt_TodayValue.setTypeface(avner);
        txtCoinValue.setTypeface(avner);
        txtCoins.setTypeface(avner);
        FetchEditions(auth_token);

        /*
        * Choosing the Editions  for displaying exchange items
        * */
        spinnereditions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 ItemName= String.valueOf(spinnereditions.getSelectedItem());
                String answer = ItemName.substring(ItemName.indexOf("[")+1,ItemName.indexOf("]"));
                FetchExchangeItems(answer,auth_token);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*
        * Scrolling up the exchange items
        * */
        Toparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position2=position2+1;
                recycler_Exchange.smoothScrollToPosition(position2);

            }
        });

        /*
        * Scrolling down the exchange items
        * */
        Bottomarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position2==0){
                }else {
                    position2=position2-1;
                    recycler_Exchange.smoothScrollToPosition(position2);
                }
            }
        });
    }
    private void NavDrawer() {
        ConstraintLayout toolbar = (ConstraintLayout) findViewById(R.id.toolbar2);
        ConstraintLayout MenuIcon=findViewById(R.id.menu_icon);

        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_Exchange.this, R.raw.btn_click);
                mp.start();
                Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                //overridePendingTransition(R.anim.left_right, R.anim.right_left);
            }
        });

        ImageView scan_luck;
        scan_luck = (ImageView) findViewById(R.id.scan_luck);
        scan_luck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_Exchange.this, R.raw.btn_click);
                mp.start();
                Intent in=new Intent(getApplicationContext(), Activity_HomePage.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                ////overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        NavDrawer();
    }

/*
*
* Fetching editions from API
* */
    private void FetchEditions(String token) {
        progressbar.setVisibility(View.VISIBLE);
        EditionID.clear();
        EditionName.clear();

        interfaceApi = APIClient.getClient().create(APIInterface.class);
        Call<EditionModule> call3 = interfaceApi.Fetch_Editions(token);
        call3.enqueue(new Callback<EditionModule>() {
            @Override
            public void onResponse(Call<EditionModule> call, retrofit2.Response<EditionModule> response) {
                progressbar.setVisibility(View.GONE);
                try {int Code=response.body().getCode();
                    if (Code==200){
                        Edition_Response response1=response.body().getResponse();
                        List<EditionData> editions=response1.getEdition();
                        if (editions.size()>0){
                            ArrayList<String> concatenated = new ArrayList<>();
                            for (int i=0;i<editions.size();i++){

                                EditionID.add(editions.get(i).getId());
                                EditionName.add(editions.get(i).getDisplayName());
                                concatenated.add("["+EditionID.get(i)+" ]"+EditionName.get(i));

                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.textview_for_spinners, concatenated);
                            spinnereditions.setAdapter(adapter);
                        }else {
                            Toast toast = Toast.makeText(getApplicationContext(),"No editions", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }

                    }else if (Code==201){
                        Toast toast = Toast.makeText(getApplicationContext(),"No Editions Available", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }else if (Code==301){
                        Toast toast = Toast.makeText(getApplicationContext(),"Authentication Failed", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }else if (Code==303){
                        Toast toast = Toast.makeText(getApplicationContext(),"Your Session Was Expired", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }else if (Code==305){
                        Toast toast = Toast.makeText(getApplicationContext(),"Token Missing", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }else if (Code==1006){
                        Toast toast = Toast.makeText(getApplicationContext(),"Unexpected Error Occurs", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<EditionModule> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                call.cancel();
            }
        });
    }


    /*
    * Fetching exchange items
    * */
    private void FetchExchangeItems(String ID, String token) {

        EditionID.clear();
        EditionName.clear();

        interfaceApi = APIClient.getClient().create(APIInterface.class);
        Call<EXDATAMODEL> call3 = interfaceApi.Fetch_Exchange(token,ID.replace(" ",""));
        call3.enqueue(new Callback<EXDATAMODEL>() {
            @Override
            public void onResponse(Call<EXDATAMODEL> call, retrofit2.Response<EXDATAMODEL> response) {


                try {int Code=response.body().getCode();
                    if (Code==200){
                        Ex_Response response1=response.body().getResponse();
                        List<EX_Item> ExchangeItems=response1.getItems();
                        if (ExchangeItems.size()>0){
                            ArrayList<String> concatenated = new ArrayList<>();

                            adapterExchange = new ExchangeAdapter(ExchangeItems,ItemName, str_UserId, str_UserCoins,getApplicationContext(),Activity_Exchange.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recycler_Exchange.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                            recycler_Exchange.setItemAnimator(new DefaultItemAnimator());
                            recycler_Exchange.setAdapter(adapterExchange);
                        }else {
                            Toast toast = Toast.makeText(getApplicationContext(),"No editions", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();

                        }

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
            public void onFailure(Call<EXDATAMODEL> call, Throwable t) {
                call.cancel();
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
                                txtCoinValue.setText(""+Constants.Const_UserCoinValue);
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
                        txtCoins.setText(""+Constants.Const_UserCoins);
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
