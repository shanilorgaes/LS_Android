package com.orgaes.ls;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.orgaes.ls.Adapter.Dipluck_Adapter;
import com.orgaes.ls.Adapter.FootPrint_Adapter;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCountData;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCount_Response;
import com.orgaes.ls.RETROFIT_NEW.DipLucks.DipLuck;
import com.orgaes.ls.RETROFIT_NEW.DipLucks.DipLuck_Response;
import com.orgaes.ls.RETROFIT_NEW.DipLucks.DipLucks_Model;
import com.orgaes.ls.RETROFIT_NEW.Editions_Data.EditionData;
import com.orgaes.ls.RETROFIT_NEW.Editions_Data.EditionModule;
import com.orgaes.ls.RETROFIT_NEW.Editions_Data.Edition_Response;
import com.orgaes.ls.RETROFIT_NEW.UserWinner.DrawWin;
import com.orgaes.ls.RETROFIT_NEW.UserWinner.DrawWinner_Model;
import com.orgaes.ls.RETROFIT_NEW.UserWinner.DrawWinner_Response;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_DipLuck extends AppCompatActivity {

    TextView txt_Header,txt_CoinsOnHand,txt_TotalCoins,txt_yearly,txt_Comingsoon;
    ImageView Luck_winners,CoinsRequest;
    Spinner filter_spinner;
    String Editionname;
    ProgressBar progressbar;
    RecyclerView recyclerView;
    Dipluck_Adapter mAdapter;
    DipLuck dipLuck;
    List<DipLuck>dipLucks=new ArrayList<>();
    List<String>EditionID=new ArrayList<>();
    List<String>EditionName=new ArrayList<>();
    List<DrawWin>drawWins;
    DrawWin drawWin;
    ConstraintLayout menu_icon;
    String latitude,longitude;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dip_luck);

        try {
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
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        String auth_token= prefsnew.getString("auth_token", "");
        txt_Header=findViewById(R.id.txt1);
        txt_CoinsOnHand=findViewById(R.id.coinsonhand_txt);
        txt_TotalCoins=findViewById(R.id.coins_total);
        txt_yearly=findViewById(R.id.yearly_txt);
        txt_Comingsoon=findViewById(R.id.comingsoon_txt);
        Luck_winners=findViewById(R.id.luck_winners);
        CoinsRequest=findViewById(R.id.coin_request);
        filter_spinner=findViewById(R.id.filter_spinner);
        progressbar=findViewById(R.id.progressbar);
        recyclerView=findViewById(R.id.luck_list);
        menu_icon=findViewById(R.id.menu_icon);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_DipLuck.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_DipLuck.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_DipLuck.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_DipLuck.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        txt_Header.setTypeface(dosis);
        txt_CoinsOnHand.setTypeface(dosis);
        txt_TotalCoins.setTypeface(dosis);
        txt_yearly.setTypeface(avner);
        txt_Comingsoon.setTypeface(avner);

        Luck_winners.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.btn_click);
            mp.start();
            fetch_Winner(auth_token);

        });
        CoinsRequest.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.btn_click);
            mp.start();
            Intent in = new Intent(getApplicationContext(), Activity_ProfileView.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
            editor.putString("from_wallet", "1");
            editor.apply();
            startActivity(in);

        });
        Fetch_Editions(auth_token);
        CoinCount(auth_token);
        filter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) filter_spinner.getSelectedView()).setTextColor(getResources().getColor(R.color.White));
                ((TextView) filter_spinner.getSelectedView()).setTextSize(10);
                if (((TextView) filter_spinner.getSelectedView()).getText().toString().equals("EDITION /LOCATION - FILTER")){
//                    Toast.makeText(Activity_DipLuck.this, "Yes", Toast.LENGTH_SHORT).show();
                    fetch_Lucks(auth_token,"0");
                }else {
//                    Toast.makeText(Activity_DipLuck.this, "NO", Toast.LENGTH_SHORT).show();
                    Editionname= String.valueOf(filter_spinner.getSelectedItem());
                    String answer = Editionname.substring(Editionname.indexOf("[")+1,Editionname.indexOf("]"));
                    fetch_Lucks(auth_token,answer);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        menu_icon.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.btn_click);
            mp.start();
            Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });
    }

    private void fetch_Lucks(String auth_token,String ID) {

        progressbar.setVisibility(View.VISIBLE);
        System.out.println("LS LATI AND LONGI 4  "+latitude+" ------------  "+longitude);
        APIInterface apiInterface=APIClient.getClient().create(APIInterface.class);
        Call<DipLucks_Model>call=apiInterface.FETCH_DipLuck("http://ec2-13-232-195-100.ap-south-1.compute.amazonaws.com/ls_dev/userapi/diplucks/"+latitude+"/"+longitude+"/"+ID,auth_token);
        call.enqueue(new Callback<DipLucks_Model>() {
            @Override
            public void onResponse(Call<DipLucks_Model> call, Response<DipLucks_Model> response) {
                progressbar.setVisibility(View.GONE);
                try {

                    int Code =response.body().getCode();
                    System.out.println("LS DIPLUCK  "+Code);
                    if (Code==200){

                        DipLuck_Response dipLuck_response=response.body().getResponse();
                        dipLucks=dipLuck_response.getDipLucks();
                        System.out.println("LS DIPLUCK2  "+dipLucks.size());
                        if (dipLucks.size()>0){
                            for (int i=0;i<dipLucks.size();i++){
                                dipLuck=dipLucks.get(i);

                            }
                            mAdapter = new Dipluck_Adapter(progressbar,auth_token,dipLucks,getApplicationContext(),  Activity_DipLuck.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(mAdapter);
                        }

                    }else if (Code==201){
                        Toast.makeText(Activity_DipLuck.this, "No Dip Luck in this edition", Toast.LENGTH_SHORT).show();
                    }


                }catch (Exception e){

                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<DipLucks_Model> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                progressbar.setVisibility(View.GONE);
            }
        });

    }

    private void Fetch_Editions(String auth_token) {
        progressbar.setVisibility(View.VISIBLE);
        EditionID.clear();
        EditionName.clear();

        APIInterface interfaceApi = APIClient.getClient().create(APIInterface.class);
        Call<EditionModule> call3 = interfaceApi.Fetch_Editions(auth_token);
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
                            concatenated.add("EDITION /LOCATION - FILTER");
                            for (int i=0;i<editions.size();i++){

                                EditionID.add(editions.get(i).getId());
                                EditionName.add(editions.get(i).getDisplayName());
                                concatenated.add("["+EditionID.get(i)+"]"+EditionName.get(i));

                            }

                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, concatenated);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            filter_spinner.setAdapter(arrayAdapter);


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

    private void fetch_Winner(String auth_token) {

        progressbar.setVisibility(View.VISIBLE);

        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<DrawWinner_Model> call=apiInterface.getWInnerDetails(auth_token);
        call.enqueue(new Callback<DrawWinner_Model>() {
            @Override
            public void onResponse(Call<DrawWinner_Model> call, Response<DrawWinner_Model> response) {
                progressbar.setVisibility(View.GONE);
                try {

                    int Code =response.body().getCode();
                    System.out.println("LS DIPLUCK  "+Code);
                    if (Code==200){
                        DrawWinner_Response dipLuck_response=response.body().getResponse();
                        drawWins=dipLuck_response.getDrawWin();
                        System.out.println("LS DIPLUCK2  "+drawWins.size());
                        if (drawWins.size()>0){
                            for (int i=0;i<drawWins.size();i++){
                                drawWin=drawWins.get(i);

                            }
                            System.out.println("LS DIPLUCKDATA  "+drawWins.get(0).getId());
                            Intent in=new Intent(getApplicationContext(),Activity_LuckWinners.class);
                            Bundle bn=new Bundle();
                            bn.putString("id",drawWins.get(0).getId());
                            bn.putString("item_id",drawWins.get(0).getItem_id());
                            bn.putString("name",drawWins.get(0).getName());
                            bn.putString("desc",drawWins.get(0).getDescription());
                            bn.putString("image",drawWins.get(0).getImage());
                            bn.putString("rank",drawWins.get(0).getRank());
                            in.putExtras(bn);
                            startActivity(in);
                        }


                    }else if (Code==201){

                        Intent in=new Intent(getApplicationContext(),Activity_LuckLoser.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(in);

                    }


                }catch (Exception e){

                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<DrawWinner_Model> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                progressbar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent in=new Intent(getApplicationContext(),Activity_HomePage.class);
        startActivity(in);
        finish();
    }
    private void CoinCount(String token){

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CoinCountData> call3 = apiInterface.Fetch_Coincount(token);
        call3.enqueue(new Callback<CoinCountData>() {
            @Override
            public void onResponse(@NotNull Call<CoinCountData> call, @NotNull Response<CoinCountData> response) {

                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    System.out.println("LS COIN RATE 1 "+Code);
                    if (Code==200){

                        CoinCount_Response coinRate_response=response.body().getResponse();
                        Constants.Const_UserCoins= Integer.parseInt(coinRate_response.getCoinCount());
                        txt_TotalCoins.setText(""+Integer.parseInt(coinRate_response.getCoinCount()));
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
        if (ActivityCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
//                Constants.Const_Latitude=latitude;
//                Constants.Const_Longitude=longitude;
//                System.out.println("LS LATI AND LONGI 1  "+Constants.Const_Latitude+" ------------  "+Constants.Const_Longitude);

            } else {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "Unable to find location", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();
            }
        }
    }
}
