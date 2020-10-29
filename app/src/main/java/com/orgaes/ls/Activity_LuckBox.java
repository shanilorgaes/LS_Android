package com.orgaes.ls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.orgaes.ls.Adapter.LuckBox_Adapter;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.RETROFIT_NEW.BOX.BOXDATA;
import com.orgaes.ls.RETROFIT_NEW.BOX.BOX_BoxItem;
import com.orgaes.ls.RETROFIT_NEW.BOX.BOX_Response;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class Activity_LuckBox extends AppCompatActivity {


    RecyclerView LuckRecycler;
    ImageView Share,LuckRadar;

    LuckBox_Adapter mAdapter;
    String UserID,auth_token;
    Spinner SpinnerFilter;
    TextView Total_Luck1;
    TextView luck_box_header;
    TextView Total_Luck;
    TextView Last_WinDate1;
    TextView Last_WinDate;
    ImageView Toparrow,Bottomarrow;
    int position2=0;

    List<BOX_BoxItem> Box_Items;
    List<String>DATA_Spinner=new ArrayList<>();

    List<String>ITEMNAME=new ArrayList<>();
    List<String>EDITIONNAME=new ArrayList<>();
    List<String>DATE=new ArrayList<>();
    List<String>IMAGE=new ArrayList<>();
    List<String>ID=new ArrayList<>();
    List<String>ITEMID=new ArrayList<>();
//    List<String>EDITIONAME=new ArrayList<>();
    List<String>TYPE=new ArrayList<>();
    ImageView LuckRadarImage;
    String value="0";
    ProgressBar progressbar;
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
        setContentView(R.layout.layout_luckbox);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_LuckBox.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_LuckBox.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_LuckBox.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_LuckBox.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        LuckRecycler=findViewById(R.id.luckbox_recycler);
        Share=findViewById(R.id.share);
        Total_Luck1=findViewById(R.id.total_lucks);
        Total_Luck=findViewById(R.id.total_lucks_main);
        Last_WinDate1=findViewById(R.id.lastwindate_);
        Last_WinDate=findViewById(R.id.last_windate_main);
        SpinnerFilter=findViewById(R.id.spinnerfilter);
        Toparrow=findViewById(R.id.toparrow);
        Bottomarrow=findViewById(R.id.bottomarrow);
        luck_box_header=findViewById(R.id.luck_box_header);
        progressbar=findViewById(R.id.progressbar);

        LuckRadarImage=findViewById(R.id.luckradar);
        LuckRadarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),Activity_LuckRadar.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
            }
        });
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        Total_Luck.setTypeface(avner);
        Total_Luck1.setTypeface(avner);
        Last_WinDate1.setTypeface(avner);
        Last_WinDate.setTypeface(avner);
        luck_box_header.setTypeface(avner);


        ImageView Scan_Luck=findViewById(R.id.scan_luck);
        Scan_Luck.setOnClickListener(v -> {
            Intent inhome=new Intent(getApplicationContext(), Activity_HomePage.class);
            startActivity(inhome);
            finish();
        });
        Toparrow.setOnClickListener(v -> {

            position2=position2+1;
            LuckRecycler.smoothScrollToPosition(position2);

        });

        Bottomarrow.setOnClickListener(v -> {
            if (position2==0){
            }else {
                position2=position2-1;
                LuckRecycler.smoothScrollToPosition(position2);
            }
        });

        DATA_Spinner.add("ALL");
        DATA_Spinner.add("luck");
        DATA_Spinner.add("luck_radar");
        DATA_Spinner.add("exchange_item");
        DATA_Spinner.add("draw");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, DATA_Spinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerFilter.setAdapter(arrayAdapter);

        SpinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.DKGRAY);
                    ((TextView) parent.getChildAt(0)).setTextSize(10);
                    ((TextView) parent.getChildAt(0)).setAllCaps(true);
                    String ITEM = String.valueOf(SpinnerFilter.getSelectedItem());
                    if (value.equals("1")) {
                        if (ITEM.equals("ALL")) {
                            Fetch_DATA(auth_token);
                        } else {
                            Change(ITEM);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public void Change(String ITEM){
        List<String>ITEMNAME1=new ArrayList<>();
        List<String>DATE1=new ArrayList<>();
        List<String>IMAGE1=new ArrayList<>();
        List<String>ID1=new ArrayList<>();
        List<String>ITEMID1=new ArrayList<>();
        List<String>EDITIONAME1=new ArrayList<>();
        List<String>TYPE1=new ArrayList<>();
        if (Box_Items.size()>0){
            for (int i = 0; i< Box_Items.size(); i++){
                if (Box_Items.get(i).getType().equals(ITEM)) {
                    if (ITEM.equals("ALL")) {
                        Fetch_DATA(auth_token);
                    } else {
                        DATE1.add(DATE.get(i));
                        IMAGE1.add(IMAGE.get(i));
                        ITEMNAME1.add(ITEMNAME.get(i));
                        EDITIONAME1.add(EDITIONNAME.get(i));
                        ID1.add(ID.get(i));
                        ITEMID1.add(ITEMID.get(i));
                        TYPE1.add(TYPE.get(i));
                    }
                }else if (ITEM.equals("ALL")) {
                    AllFunction(ITEM,DATE1, IMAGE1, ITEMNAME1,EDITIONAME1, TYPE1,ITEMID1,ID1);
                }
            }
            mAdapter.Update(DATE1,  IMAGE1, ITEMNAME1,EDITIONAME1, TYPE1,mAdapter,ITEMID1,ID1);
        }
    }

    private void AllFunction(String ITEM, List<String> date1, List<String> image1, List<String> itemname1, List<String> edition1,List<String> type1, List<String> ITEMID1, List<String> ID1) {

        for (int i = 0; i < Box_Items.size(); i++) {

            BOX_BoxItem luckBox = Box_Items.get(i);
            type1.add(luckBox.getType());
            if (ITEM.equals("luck")) {

                date1.add(String.valueOf(luckBox.getEndDate()));
                itemname1.add((luckBox.getName()));
                image1.add((luckBox.getImage()));
                edition1.add((luckBox.getEdition()));
                ITEMID1.add(luckBox.getItemId());
                ID1.add(luckBox.getId());

            } else if (ITEM.equals("luck_radar")) {

                date1.add(String.valueOf(luckBox.getEndDate()));
                itemname1.add((luckBox.getName()));
                image1.add((luckBox.getImage()));
                ITEMID1.add(luckBox.getItemId());
                ID1.add(luckBox.getId());
                edition1.add((luckBox.getEdition()));
            } else if (ITEM.equals("exchange_item")) {

                date1.add(String.valueOf(luckBox.getEndDate()));
                itemname1.add((luckBox.getName()));
                image1.add((luckBox.getImage()));
                ITEMID1.add(luckBox.getItemId());
                ID1.add(luckBox.getId());
                edition1.add((luckBox.getEdition()));
            }else if (ITEM.equals("draw")) {

                date1.add(String.valueOf(luckBox.getEndDate()));
                itemname1.add((luckBox.getName()));
                image1.add((luckBox.getImage()));
                ITEMID1.add(luckBox.getItemId());
                ID1.add(luckBox.getId());
                edition1.add((luckBox.getEdition()));
            }

        }

        mAdapter = new LuckBox_Adapter(Box_Items,date1,image1,type1,itemname1,edition1,ITEMID1,ID1
                ,  Activity_LuckBox.this,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        LuckRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        LuckRecycler.setItemAnimator(new DefaultItemAnimator());
        LuckRecycler.setAdapter(mAdapter);


    }

    private void Fetch_DATA(String auth_token) {
        progressbar.setVisibility(View.VISIBLE);
        ITEMNAME.clear();
        DATE.clear();
        IMAGE.clear();
        EDITIONNAME.clear();
        TYPE.clear();

        APIInterface apiInterface;

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<BOXDATA> call3 = apiInterface.Fetch_LuckBox(auth_token);
        call3.enqueue(new Callback<BOXDATA>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<BOXDATA> call, @NotNull retrofit2.Response<BOXDATA> response) {
                progressbar.setVisibility(View.GONE);
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        BOX_Response response1=response.body().getResponse();
                        Box_Items =response1.getBoxItems();
                        Total_Luck.setText(""+ Box_Items.size());
                        Last_WinDate.setText(response1.getLastLuckWinDate());
                        if (Box_Items.size()>0) {
                            value="1";
                            for (int i = 0; i < Box_Items.size(); i++) {

                                BOX_BoxItem luckBox = Box_Items.get(i);
                                TYPE.add(luckBox.getType());
                                switch (TYPE.get(i)) {
                                    case "luck":
                                        DATE.add(String.valueOf(luckBox.getEndDate()));
                                        ITEMNAME.add((luckBox.getName()));
                                        IMAGE.add((luckBox.getImage()));
                                        ITEMID.add(luckBox.getItemId());
                                        EDITIONNAME.add(luckBox.getEdition());
                                        ID.add(luckBox.getId());
                                        break;
                                    case "luck_radar":
                                        DATE.add(String.valueOf(luckBox.getEndDate()));
                                        ITEMNAME.add((luckBox.getName()));
                                        IMAGE.add((luckBox.getImage()));
                                        ITEMID.add(luckBox.getItemId());
                                        ID.add(luckBox.getId());
                                        EDITIONNAME.add(luckBox.getEdition());
                                        break;
                                    case "exchange_item":
                                        DATE.add(String.valueOf(luckBox.getEndDate()));
                                        ITEMNAME.add((luckBox.getName()));
                                        IMAGE.add((luckBox.getImage()));
                                        ITEMID.add(luckBox.getItemId());
                                        ID.add(luckBox.getId());
                                        EDITIONNAME.add(luckBox.getEdition());
                                        break;
                                    case "draw":
                                        DATE.add(String.valueOf(luckBox.getEndDate()));
                                        ITEMNAME.add((luckBox.getName()));
                                        IMAGE.add((luckBox.getImage()));
                                        ITEMID.add(luckBox.getItemId());
                                        ID.add(luckBox.getId());
                                        EDITIONNAME.add(luckBox.getEdition());
                                        break;
                                }

                            }

                            mAdapter = new LuckBox_Adapter(Box_Items,DATE,IMAGE,TYPE,ITEMNAME,EDITIONNAME,ITEMID,ID
                                    ,  Activity_LuckBox.this,getApplicationContext());
                            LuckRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                            LuckRecycler.setItemAnimator(new DefaultItemAnimator());
                            LuckRecycler.setAdapter(mAdapter);

                        }
                    }else if (Code==201){

                        Toast.makeText(getApplicationContext(), "No Items in LUCKBOX", Toast.LENGTH_SHORT).show();

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
            public void onFailure(@NotNull Call<BOXDATA> call, @NotNull Throwable t) {
                call.cancel();
                progressbar.setVisibility(View.GONE);
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();


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
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        UserID= prefsnew.getString("UserID", "");
        auth_token= prefsnew.getString("auth_token", "");
        ConstraintLayout toolbar = (ConstraintLayout) findViewById(R.id.toolbar2);
        ConstraintLayout MenuIcon=findViewById(R.id.menu_icon);

        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                //overridePendingTransition(R.anim.left_right, R.anim.right_left);
            }
        });
        Fetch_DATA(auth_token);
    }




}
