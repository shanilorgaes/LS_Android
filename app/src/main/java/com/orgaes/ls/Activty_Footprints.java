package com.orgaes.ls;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;import androidx.appcompat.app.AppCompatActivity;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.orgaes.ls.Adapter.FootPrint_Adapter;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.RETROFIT_NEW.FootPrintData.FootprintData;
import com.orgaes.ls.RETROFIT_NEW.FootPrintData.FootprintMain;
import com.orgaes.ls.RETROFIT_NEW.FootPrintData.Footprint_Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


/*
 * Purpose – Activty_Footprints is an activity which is the user can see his history(Footprints)
 * Total Ads
 * Total ads Skipped
 * Total luck earned
 * Total luck claim
 * @author SHANIL
 * Created on 21-5-2020
 */

public class Activty_Footprints extends AppCompatActivity {

    String UserID,auth_token;
    RecyclerView recyclerView;
    FootPrint_Adapter mAdapter;
    Spinner Type_Filter;
    List<FootprintData>footprintDetails,filterList;
    List<String>DATA_Spinner=new ArrayList<>();
    List<String>DATA_NEW=new ArrayList<>();

    List<String>Name=new ArrayList<>();
    List<String>Date=new ArrayList<>();
    List<String>Time=new ArrayList<>();
    List<String>Type=new ArrayList<>();
//  List<String>Images=new ArrayList<>();
    String value="0";
    ImageView Toparrow;
    ImageView Bottomarrow;
    int position2=0;
    ProgressBar progressbar;
    int Page=1;

    ImageView Firstimg,PrevImg,Lastimg,Nextimg;
    TextView Currentpage;
    int CurrntCOunt;
    ImageView LuckRadarImage;
//    CircleImageView userimage;
//    TextView TotalAds,TotalSkipped,TotalLuck,TotalClaim;
//    ImageView close_button;
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
        setContentView(R.layout.layout_footprints);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activty_Footprints.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activty_Footprints.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activty_Footprints.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activty_Footprints.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        recyclerView=findViewById(R.id.recyclerView);
        Toparrow=findViewById(R.id.toparrow);
        Bottomarrow=findViewById(R.id.bottomarrow);
        Type_Filter=findViewById(R.id.type_filter);
        progressbar=findViewById(R.id.progressbar);
        Currentpage=findViewById(R.id.pagecount);

        Firstimg=findViewById(R.id.first);
        PrevImg=findViewById(R.id.prev);
        Lastimg=findViewById(R.id.last);
        Nextimg=findViewById(R.id.next);
        LuckRadarImage=findViewById(R.id.luckradar);
        LuckRadarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),Activity_LuckRadar.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
            }
        });

        ImageView Scan_Luck=findViewById(R.id.scan_luck);
        Scan_Luck.setOnClickListener(v -> {
            Intent inhome=new Intent(getApplicationContext(), Activity_HomePage.class);
            startActivity(inhome);
            finish();
        });


        Toparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position2=position2+1;
                recyclerView.smoothScrollToPosition(position2);

            }
        });

        Bottomarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position2==0){
                }else {
                    position2=position2-1;
                    recyclerView.smoothScrollToPosition(position2);
                }
            }
        });

        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        UserID= prefsnew.getString("UserID", "");
        auth_token= prefsnew.getString("auth_token", "");
        String Userpic= prefsnew.getString("Userpic", "");
        System.out.println("LS FOOTPRINT  "+auth_token);
        Fetch_Footprints(auth_token,1);
        DATA_Spinner.add("ALL");
        DATA_Spinner.add("friend_request_send");
        DATA_Spinner.add("coin_request_send");
        DATA_Spinner.add("coin_collected");
        DATA_Spinner.add("coin_shared");
        DATA_Spinner.add("coin_exchanged");
        DATA_Spinner.add("luck_win");
        DATA_Spinner.add("luck_shared");
        DATA_Spinner.add("luck_redeem");
        DATA_Spinner.add("lr_redeem");
        DATA_Spinner.add("exchange_item_redeem");
        DATA_Spinner.add("ads_skipped");
        DATA_Spinner.add("ads_finished");
        DATA_Spinner.add("luck_claimed");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, DATA_Spinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Type_Filter.setAdapter(arrayAdapter);

        Type_Filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("LS SPINNER TEST 1  ");
                ((TextView) parent.getChildAt(0)).setTextColor(Color.DKGRAY);
                ((TextView) parent.getChildAt(0)).setTextSize(10);
                ((TextView) parent.getChildAt(0)).setAllCaps(true);
                String ITEM= String.valueOf(Type_Filter.getSelectedItem());

                if (value.equals("1")) {
                    if (ITEM.equals("ALL")){
                        Fetch_Footprints(auth_token,1);
                    }else {
                        Change(ITEM);
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("LS SPINNER TEST 2  ");
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();



        ConstraintLayout toolbar = (ConstraintLayout) findViewById(R.id.toolbar2);
        ConstraintLayout MenuIcon=findViewById(R.id.menu_icon);

        Firstimg.setOnClickListener(v -> Fetch_Footprints(auth_token,1));
        Lastimg.setOnClickListener(v -> Fetch_Footprints(auth_token,Page));

        Nextimg.setOnClickListener(v -> {
            Fetch_Footprints(auth_token,CurrntCOunt+1);
        });

        PrevImg.setOnClickListener(v -> {
            Fetch_Footprints(auth_token,CurrntCOunt-1);
        });
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                //overridePendingTransition(R.anim.left_right, R.anim.right_left);
            }
        });

    }



    public void Change(String ITEM){
        Toast.makeText(this, ""+ITEM, Toast.LENGTH_SHORT).show();
        List<String>Date1=new ArrayList<>();
        List<String>Name1=new ArrayList<>();
        List<String>Time1=new ArrayList<>();
        List<String>Type1=new ArrayList<>();

        if (footprintDetails.size()>0){

            for (int i=0;i<footprintDetails.size();i++){

                if (footprintDetails.get(i).getType().equals(ITEM)) {
                    if (ITEM.equals("ALL")) {
                        Fetch_Footprints(auth_token,1);
                    } else {

                        Name1.add(footprintDetails.get(i).getType());
                        Date1.add(footprintDetails.get(i).getDate());
                        Time1.add(footprintDetails.get(i).getTime());
                        Type1.add(footprintDetails.get(i).getType());
                    }
                }else if (ITEM.equals("ALL")) {
                        AllFunction(ITEM, Date1, Time1, Type1,Name1);
                }
            }
            mAdapter.Update(ITEM,Date1,Time1,Type1,mAdapter);
        }

    }

    private void AllFunction(String ITEM, List<String> date1, List<String> time1, List<String> type1, List<String> name1) {


        for (int i=0;i<footprintDetails.size();i++){

            FootprintData footprintDetail=footprintDetails.get(i);
            Type.add(footprintDetail.getType());
            if (ITEM.equals("friend_request_send")){
                name1.add("FRIEND REQUEST");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("coin_collected")){
                name1.add("COINS COLLECTED");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("coin_request_send")){
                name1.add("COIN REQUEST");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("ads_skipped")){
                name1.add("ADS SKIPPED");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("ads_finished")){
                name1.add("ADS FINISHED");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("luck_win")){
                name1.add("LUCK WINS");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("luck_claimed")){
                name1.add("LUCK CLAIMED");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("luck_shared")){
                name1.add("LUCK SHARED");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("coin_shared")){
                name1.add("COIN SHARED");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("coin_exchanged")){
                name1.add("COIN EXCHANGED AS MONEY");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("luck_redeem")){
                name1.add("LUCK REDEEM");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("lr_redeem")){
                name1.add("LUCK RADAR REDEEM");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            } if (ITEM.equals("exchange_item_redeem")){
                name1.add("EXCHANGE ITEM REDEEM");
                date1.add(Date.get(i));
                time1.add(Time.get(i));
                
            }

        }
        mAdapter = new FootPrint_Adapter(footprintDetails,Type,date1,time1 ,name1
                ,getApplicationContext(),  Activty_Footprints.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }


    private void Fetch_Footprints(String token,int i) {
//        DATA_Spinner.clear();
        progressbar.setVisibility(View.VISIBLE);
        APIInterface apiInterface;
        Date.clear();
        Name.clear();
        Time.clear();
        Type.clear();
        System.out.println("LS FOOTPRINTS  "+token+"    "+auth_token);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<FootprintMain> call3 = apiInterface.FETCH_FOOTPRINTS_CALL("http://ec2-13-232-195-100.ap-south-1.compute.amazonaws.com/ls_dev/userapi/footprint"+"/"+i,auth_token);
        call3.enqueue(new Callback<FootprintMain>() {
            @Override
            public void onResponse(Call<FootprintMain> call, retrofit2.Response<FootprintMain> response) {
                progressbar.setVisibility(View.GONE);
                try {
                    int Code=response.body().getCode();
                    if (Code==200){
                        Footprint_Response footprint_response=response.body().getResponse();

                        footprintDetails=footprint_response.getFootprint();
                        Page= (footprint_response.getPages());
                        System.out.println("LS FOOTPRINTS SIZE  "+footprintDetails.size());
                        if (footprintDetails.size()>0){
                            Currentpage.setText(""+i);
                            CurrntCOunt= (i);
                            value="1";
                            for (int i=0;i<footprintDetails.size();i++){

                                FootprintData footprintDetail=footprintDetails.get(i);
                                Type.add(footprintDetail.getType());
                                if (Type.get(i).equals("friend_request_send")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("coin_collected")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("coin_request_send")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("ads_skipped")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("luck_win")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("luck_claimed")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("luck_shared")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("ads_finished")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("coin_shared")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("coin_exchanged")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("luck_redeem")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("lr_redeem")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                } if (Type.get(i).equals("exchange_item_redeem")){
                                    Date.add(footprintDetail.getDate());
                                    Time.add(footprintDetail.getTime());
                                    Name.add(footprintDetail.getType());
                                }

                            }
                            mAdapter = new FootPrint_Adapter(footprintDetails,Type,Date,Time
                                    , Name, getApplicationContext(),  Activty_Footprints.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(mAdapter);
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
            public void onFailure(Call<FootprintMain> call, Throwable t) {
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                progressbar.setVisibility(View.GONE);
            }
        });
    }



}
