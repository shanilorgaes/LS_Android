package com.orgaes.ls;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;import androidx.appcompat.app.AppCompatActivity;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.orgaes.ls.Adapter.Luckradar_Adapter;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCountData;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCount_Response;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRate;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRateData;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRate_Response;
import com.orgaes.ls.RETROFIT_NEW.Edition_Check.EditionCheck;
import com.orgaes.ls.RETROFIT_NEW.Edition_Check.Edition_CheckResponse;
import com.orgaes.ls.RETROFIT_NEW.Editions_Data.EditionData;
import com.orgaes.ls.RETROFIT_NEW.Editions_Data.EditionModule;
import com.orgaes.ls.RETROFIT_NEW.Editions_Data.Edition_Response;
import com.orgaes.ls.RETROFIT_NEW.Luck_Radar_Data.LuckRadarData;
import com.orgaes.ls.RETROFIT_NEW.Luck_Radar_Data.LuckRadar_Details;
import com.orgaes.ls.RETROFIT_NEW.Luck_Radar_Data.LuckRadar_Response;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
 * Purpose – Activity_LuckRadar is an activity which the user can check the ads in corresponding editions
 * @author SHANIL
 * Created on 21-5-2020
 */
public class Activity_LuckRadar extends AppCompatActivity {
    RecyclerView Rcyclerview_Lucks;
    APIInterface apiInterface;
    Luckradar_Adapter mAdapter;
    String SignedIn,auth_token;
    int UserCoins;
    String  UserID;
     Spinner editionspinner;
     ProgressBar progressbar;
     TextView Total;
     TextView coinsTotal;
    ImageView mImgRadarBack;
    MediaPlayer soundPlayer;
    List<String>EditionID=new ArrayList<>();
    List<String>EditionName=new ArrayList<>();
    ConstraintLayout TopHeader;
    ConstraintLayout Radar_bg;
    ConstraintLayout TotalCoins;
    ConstraintLayout Todaysvalue;
    ConstraintLayout header;
//    TextView Radar_RedHead;
//    TextView Numbers;
    ImageView CoverCoin;
    ImageView colinstatus;
    ImageView TopArrow;
    ImageView BottomArrow;
    ImageView Lines;

    TextView Current_Offers;
//    TextView Current_OffersCount;
    TextView TotalCoinsTXT;
    TextView TotalCoinsValue;
    TextView TotalCoinValue;
    int position2=0;
//    String latitude,longitude;
    String Lati,Longi;
    GPSTracker gps;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION = 1;
    boolean isValue=true;
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
        setContentView(R.layout.layout_luckradar);
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        UserID = prefsnew.getString("UserID", "");
        SignedIn= prefsnew.getString("SignedIn", "");
        auth_token= prefsnew.getString("auth_token", "");
        Rcyclerview_Lucks = findViewById(R.id.expandable_recyclerview);
        editionspinner=findViewById(R.id.editionspinner);
        progressbar=findViewById(R.id.progressbar);
        Total=findViewById(R.id.textView27);
        mImgRadarBack = findViewById(R.id.mImgRadarBack);
        TopHeader=findViewById(R.id.constraintLayout7);
        TotalCoins=findViewById(R.id.constraintLayout5);
        Todaysvalue=findViewById(R.id.constraintLayout6);
        header=findViewById(R.id.header);
        CoverCoin=findViewById(R.id.imageView12);
        colinstatus=findViewById(R.id.colinstatus);
        TopArrow=findViewById(R.id.toparrow);
        BottomArrow=findViewById(R.id.bottomarrow);
        Radar_bg=findViewById(R.id.radar_bg);
        Lines=findViewById(R.id.lines);
        Current_Offers=findViewById(R.id.textView26);
        TotalCoinsTXT=findViewById(R.id.textView9);
        TotalCoinsValue=findViewById(R.id.textView10);
        TotalCoinValue=findViewById(R.id.coinsvalue);
        coinsTotal=findViewById(R.id.coinstotal);
        try{
        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            turnGPSOn();
        } else {
            getLocation();
        }
    }catch (Exception e) {
        e.printStackTrace();
    }
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_LuckRadar.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_LuckRadar.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_LuckRadar.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_LuckRadar.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();



        TopHeader.setVisibility(View.GONE);
        TotalCoins.setVisibility(View.GONE);
        Todaysvalue.setVisibility(View.GONE);
        header.setVisibility(View.GONE);
        CoverCoin.setVisibility(View.GONE);
        colinstatus.setVisibility(View.GONE);
        Rcyclerview_Lucks.setVisibility(View.GONE);
        progressbar.setVisibility(View.GONE);
        TopArrow.setVisibility(View.GONE);
        progressbar.setVisibility(View.GONE);
        BottomArrow.setVisibility(View.GONE);
        Lines.setVisibility(View.GONE);

        TopHeader.setVisibility(View.VISIBLE);
        TotalCoins.setVisibility(View.VISIBLE);
        Todaysvalue.setVisibility(View.VISIBLE);
        header.setVisibility(View.VISIBLE);
        CoverCoin.setVisibility(View.VISIBLE);
        colinstatus.setVisibility(View.VISIBLE);
        Rcyclerview_Lucks.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.VISIBLE);
        TopArrow.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.VISIBLE);
        BottomArrow.setVisibility(View.VISIBLE);
        Lines.setVisibility(View.VISIBLE);
        Radar_bg.setVisibility(View.GONE);

        CoinRate(auth_token);
        CoinCount(auth_token);
//            TotalCoinValue.setText(""+Constants.Const_TodayValue.get(0));

        NavDrawer();
        editionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ItemName= String.valueOf(editionspinner.getSelectedItem());
                String answer = ItemName.substring(ItemName.indexOf("[")+1,ItemName.indexOf("]"));
                System.out.println("LS SPINNER TESTttt   "+answer+"       "+ItemName);
                Fetch_Radar(answer,ItemName);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("LS SPINNER TESTttt2   ");
                Fetch_Radar("0","");
            }
        });
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        Current_Offers.setTypeface(avner);
        TotalCoinsTXT.setTypeface(avner);
        TotalCoinsValue.setTypeface(avner);
        Total.setTypeface(avner);


        FetchEditions_LatLong(auth_token);
        arrowclickFunction();
        ImageView Scan_Luck=findViewById(R.id.scan_luck);
        Scan_Luck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(Activity_LuckRadar.this, R.raw.btn_click);
                mp.start();
                Intent inhome=new Intent(getApplicationContext(), Activity_HomePage.class);
                startActivity(inhome);
                finish();
            }
        });

//        animateRadar(UserID);

    }

    private void turnGPSOn(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }

    /*
     * Purpose – for getting the lati and longi of current location
     * @author SHANIL
     * Created on 21-5-2020
     */

    private void getLocation() {
        System.out.println("LS GPS 1 ");
        gps = new GPSTracker(Activity_LuckRadar.this);
        // check if GPS enabled
        if(gps.isGPSEnabled){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Lati= String.valueOf(latitude);
            Longi= String.valueOf(longitude);
            System.out.println("LS PERMISSION CHECK 1  "+Lati+" ------------  "+Longi);
            if (latitude==0||longitude==0){
                System.out.println("LS PERMISSION CHECK 2  ");
                isValue=false;
            }else {
                isValue=true;
                System.out.println("LS PERMISSION CHECK 3  ");
                FetchEditions_LatLong(auth_token);
            }

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is1 - \nLat: " + Lati + "\nLong: " + Longi, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // permission was granted, yay! Do the

                // contacts-related task you need to do.

                gps = new GPSTracker(Activity_LuckRadar.this);

                // Check if GPS enabled
                if (gps.isGPSEnabled) {
                    System.out.println("LS PERMISSION CHECK 6  ");
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Lati= String.valueOf(latitude);
                    Longi= String.valueOf(longitude);
                    System.out.println("LS PERMISSION CHECK 7  "+latitude+" ------------  "+longitude+"    "+isValue);
                    if (!isValue) {
                        FetchEditions_LatLong(auth_token);
                    }
                    // \n is for new line
//                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    // Can't get location.
                    // GPS or network is not enabled.
                    // Ask user to enable GPS/network in settings.
                    gps.showSettingsAlert();
                }

            } else {

                // permission denied, boo! Disable the
                // functionality that depends on this permission.

                Toast.makeText(getApplicationContext(), "You need to grant permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void arrowclickFunction() {
        ImageView Toparrow, Bottomarrow;

        Toparrow = findViewById(R.id.toparrow);
        Bottomarrow = findViewById(R.id.bottomarrow);
        Toparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position2 = position2 + 1;
                Rcyclerview_Lucks.smoothScrollToPosition(position2);

            }
        });

        Bottomarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position2 == 0) {
                } else {
                    position2 = position2 - 1;
                    Rcyclerview_Lucks.smoothScrollToPosition(position2);
                }
            }
        });
    }

    /*
    * Radar animation function
    * */
    private void animateRadar(String userID) {


        soundPlayer= MediaPlayer.create(getApplicationContext(), R.raw.radar);
        soundPlayer.start();

        Handler musicStopHandler = new Handler();
        Runnable musicStopRunable = new Runnable() {
            @Override
            public void run() {
                soundPlayer.stop();
//                Fetch_Radar2("0","");
            }
        };

        musicStopHandler.postDelayed(musicStopRunable, 5000);

    }

    @Override
    public void onBackPressed() {

        Intent inhome=new Intent(this, Activity_HomePage.class);
        startActivity(inhome);
        finish();
        ////overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /*
* Navigation Drawer Function
* */
    private void NavDrawer() {

        ConstraintLayout menu=findViewById(R.id.menu_icon);
        menu.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(Activity_LuckRadar.this, R.raw.btn_click);
            mp.start();
            Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            //overridePendingTransition(R.anim.left_right, R.anim.right_left);

        });

    }
    @Override
    protected void onResume() {
        super.onResume();

    }



    /*
    * Fetching Radar ads from Api location vice
    * */
    private void Fetch_Radar(String edition, String itemName) {

        System.out.println("LS CHECK PARAMS   "+auth_token+"    "+edition.length());

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<LuckRadarData> call3 = apiInterface.Fetch_LuckRadar(auth_token, edition.replace(" ",""));
        call3.enqueue(new Callback<LuckRadarData>() {
            @Override
            public void onResponse(@NotNull Call<LuckRadarData> call, @NotNull retrofit2.Response<LuckRadarData> response) {
                assert response.body() != null;
                int Code = response.body().getCode();
                System.out.println("LS SUCCESSS  "+response);
                progressbar.setVisibility(View.GONE);
                try {
                    if (Code == 200) {

                        TopHeader.setVisibility(View.VISIBLE);
                        TotalCoins.setVisibility(View.VISIBLE);
                        Todaysvalue.setVisibility(View.VISIBLE);
                        header.setVisibility(View.VISIBLE);
                        CoverCoin.setVisibility(View.VISIBLE);
                        colinstatus.setVisibility(View.VISIBLE);
                        Rcyclerview_Lucks.setVisibility(View.VISIBLE);
                        TopArrow.setVisibility(View.VISIBLE);
//                        progressbar.setVisibility(View.VISIBLE);
                        BottomArrow.setVisibility(View.VISIBLE);
                        Lines.setVisibility(View.VISIBLE);
                        Radar_bg.setVisibility(View.GONE);
                        LuckRadar_Response response1=response.body().getResponse();
                        List<LuckRadar_Details> radar = response1.getLuckRadar();
                        Total.setText(""+radar.size()+" Nos");
                        if (radar.size() > 0) {
                            mAdapter = new Luckradar_Adapter(auth_token,edition,radar,UserCoins,itemName
                                    , getApplicationContext(), Activity_LuckRadar.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            Rcyclerview_Lucks.setLayoutManager(mLayoutManager);
                            Rcyclerview_Lucks.setItemAnimator(new DefaultItemAnimator());
                            Rcyclerview_Lucks.setAdapter(mAdapter);
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
                } catch (Exception e) {
                    e.printStackTrace();
                    progressbar.setVisibility(View.GONE);
                    System.out.println("LS LUCKRADAR2  "+e.getMessage());
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<LuckRadarData> call, Throwable t) {
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                System.out.println("LS LUCKRADAR3  "+t.getMessage());
                progressbar.setVisibility(View.GONE);
            }
        });
    }


    /*
     *
     * Fetching editions from API
     * */
    private void FetchEditions_LatLong(String token) {
        progressbar.setVisibility(View.VISIBLE);
        EditionID.clear();
        EditionName.clear();
        System.out.println("LS LAT LONG  "+token+"------"+Lati+"------"+Longi);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<EditionCheck> call3 = apiInterface.Fetch_Editions_WithLatLong("http://ec2-13-232-195-100.ap-south-1.compute.amazonaws.com/ls_dev/userapi/editioncheck/"+Lati+"/"+Longi,token);
        call3.enqueue(new Callback<EditionCheck>() {
            @Override
            public void onResponse(@NotNull Call<EditionCheck> call, @NotNull retrofit2.Response<EditionCheck> response) {
                progressbar.setVisibility(View.GONE);
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){

                        Edition_CheckResponse edition_checkResponse=response.body().getResponse();
                        String Edition=edition_checkResponse.getEdition();
                        FetchEditions(token);
                    }else if (Code==201){
                        Toast.makeText(Activity_LuckRadar.this, "No Editions Found", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<EditionCheck> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });
    }
    /*
     *
     * Fetching editions from API
     * */
    private void FetchEditions(String token) {
        progressbar.setVisibility(View.VISIBLE);
        EditionID.clear();
        EditionName.clear();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<EditionModule> call3 = apiInterface.Fetch_Editions(token);
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
                            editionspinner.setAdapter(adapter);
                        }else {
                            Toast toast = Toast.makeText(getApplicationContext(),"No editions", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }

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
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });
    }
    private void CoinCount(String token){

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CoinCountData> call3 = apiInterface.Fetch_Coincount(token);
        call3.enqueue(new Callback<CoinCountData>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<CoinCountData> call, @NotNull Response<CoinCountData> response) {

                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    System.out.println("LS COIN RATE 1 "+Code);
                    if (Code==200){

                        CoinCount_Response coinRate_response=response.body().getResponse();
                        Constants.Const_UserCoins= Integer.parseInt(coinRate_response.getCoinCount());
                        coinsTotal.setText(""+Constants.Const_UserCoins);
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
    private void CoinRate(String token){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CoinRateData> call3 = apiInterface.Fetch_Coinrate(token);
        call3.enqueue(new Callback<CoinRateData>() {
            @Override
            public void onResponse(@NotNull Call<CoinRateData> call, @NotNull Response<CoinRateData> response) {

                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    System.out.println("LS COIN RATE 1 "+Code);
                    if (Code==200){

                        CoinRate_Response coinRate_response=response.body().getResponse();
                        List<CoinRate>coinRates=coinRate_response.getCoinRate();
                        if (coinRates.size()>0){
                            for (int i=0;i<coinRates.size();i++){
                                CoinRate coinRate=coinRates.get(i);
                                Constants.Const_UserCoinValue= ((coinRate.getCoinValue()));
                            }

                            TotalCoinValue.setText(""+Constants.Const_UserCoinValue);

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
}

