package com.orgaes.ls;

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
import android.widget.ProgressBar;
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
import com.orgaes.ls.Adapter.LeaderBoardAdapter;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoard_Model.LeaderBoard_Response;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoard_Model.LeaderBoard_data;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoard_Model.Leaderboard_Details;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

/*
 * Purpose – Activity_LeaderBoard is an activity which have the full deatils of LeaderBoard
 * @author SHANIL
 * Created on 21-5-2020
 */
public class Activity_LeaderBoard extends AppCompatActivity {


    String SignedIn,auth_token;
    RecyclerView Rcyclerview_Lucks;
    APIInterface apiInterface;
    String UserID;
    List<String>user_name=new ArrayList<>();
    List<String>user_email=new ArrayList<>();
    List<String>user_phone=new ArrayList<>();
    List<String>user_gender=new ArrayList<>();
    List<String>user_photo=new ArrayList<>();
    List<String>lb_no_of_coins=new ArrayList<>();
    List<String>user_id=new ArrayList<>();
    List<String>lb_coin_points=new ArrayList<>();
    List<String>totalluck=new ArrayList<>();

    ProgressBar progressbar;
    LeaderBoardAdapter mAdapter;
    TextView Txt1,Txt2,Txt3;
    ImageView Toparrow;
    ImageView Bottomarrow;
    int position2=0;
    ImageView Firstimg,PrevImg,Lastimg,Nextimg;
    TextView Currentpage;
    int CurrntCOunt;
    int Page;
    ImageView LuckRadarImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_leaderboard);
        try {

            ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
            Zoomy.Builder builder = new Zoomy.Builder(Activity_LeaderBoard.this)
                    .target(constraintLayoutmain)
                    .interpolator(new OvershootInterpolator())
                    .tapListener(new TapListener() {
                        @Override
                        public void onTap(View v) {
//                            Toast.makeText(Activity_LeaderBoard.this, "Tap on "
//                                    + v.getTag(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .longPressListener(new LongPressListener() {
                        @Override
                        public void onLongPress(View v) {
//                            Toast.makeText(Activity_LeaderBoard.this, "Long press on "
//                                    + v.getTag(), Toast.LENGTH_SHORT).show();
                        }
                    }).doubleTapListener(new DoubleTapListener() {
                        @Override
                        public void onDoubleTap(View v) {
//                            Toast.makeText(Activity_LeaderBoard.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                        }
                    });

            builder.register();
        Rcyclerview_Lucks=findViewById(R.id.expandable_recyclerview);
        progressbar=findViewById(R.id.progressbar);
        Toparrow=findViewById(R.id.toparrow);
        Bottomarrow=findViewById(R.id.bottomarrow);
        Txt1=findViewById(R.id.textView8);
        Txt2=findViewById(R.id.coinstxt);
        Txt3=findViewById(R.id.coinspent);
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
        Firstimg.setOnClickListener(v -> Fetch_LeaderBoard(1));
        Lastimg.setOnClickListener(v -> Fetch_LeaderBoard(Page));

        Nextimg.setOnClickListener(v -> {
            Fetch_LeaderBoard(CurrntCOunt+1);
        });

        PrevImg.setOnClickListener(v -> {
            if (CurrntCOunt==1){

            }else {
                Fetch_LeaderBoard(CurrntCOunt - 1);
            }
        });
        Toparrow.setOnClickListener(v -> {

            position2=position2+1;
            Rcyclerview_Lucks.smoothScrollToPosition(position2);

        });

        Bottomarrow.setOnClickListener(v -> {
            if (position2==0){
            }else {
                position2=position2-1;
                Rcyclerview_Lucks.smoothScrollToPosition(position2);
            }
        });
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        Txt1.setTypeface(avner);
        Txt2.setTypeface(avner);
        Txt3.setTypeface(avner);
        ImageView Scan_Luck=findViewById(R.id.scan_luck);
        Scan_Luck.setOnClickListener(v -> {
            Intent inhome=new Intent(getApplicationContext(), Activity_HomePage.class);
            startActivity(inhome);
            finish();
        });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void NavDrawer() {
        try {
        ConstraintLayout MenuIcon=findViewById(R.id.menu_icon);

        MenuIcon.setOnClickListener(v -> {

            Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            //overridePendingTransition(R.anim.left_right, R.anim.right_left);
        });

        ImageView scan_luck;
        scan_luck = (ImageView) findViewById(R.id.scan_luck);
        scan_luck.setOnClickListener(v -> {
            Intent in=new Intent(getApplicationContext(), Activity_HomePage.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);
            ////overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();



        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        UserID= prefsnew.getString("UserID", "");
        auth_token= prefsnew.getString("auth_token", "");
        Fetch_LeaderBoard(1);
        SignedIn= prefsnew.getString("SignedIn", "");
        NavDrawer();
    }

    private void Fetch_LeaderBoard(int i) {

        progressbar.setVisibility(View.VISIBLE);
        user_name.clear();
        user_email.clear();
        user_phone.clear();
        user_gender.clear();
        user_photo.clear();
        lb_no_of_coins.clear();
        user_id.clear();
        lb_coin_points.clear();
        totalluck.clear();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<LeaderBoard_data> call3 = apiInterface.FETCH_LeaderBoard("http://ec2-13-232-195-100.ap-south-1.compute.amazonaws.com/ls_dev/userapi/leaderboard/"+i,auth_token);
        call3.enqueue(new Callback<LeaderBoard_data>() {
            @Override
            public void onResponse(@NotNull Call<LeaderBoard_data> call, @NotNull retrofit2.Response<LeaderBoard_data> response) {
                progressbar.setVisibility(View.GONE);

                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        LeaderBoard_Response response1=response.body().getResponse();
                        List<Leaderboard_Details> Leaderebrd=response1.getLeaderboard();
                        Page=response1.getPages();
                        System.out.println("LS SIZE   "+Leaderebrd.size());
                        if (Leaderebrd.size()>0){
                            Currentpage.setText(""+i);
                            CurrntCOunt= (i);
                            ArrayList<Leaderboard_Details> LeaderBoardDetails = new ArrayList<>(Leaderebrd);
                            Leaderboard_Details leaderboardUser=response1.getLeaderboardUser().get(0);
                            if (RankList(leaderboardUser.getRank(),i)){
                                LeaderBoardDetails.add(leaderboardUser);
                            }

                            System.out.println("LS SIZE 11111111  "+LeaderBoardDetails.size());
                            System.out.println("LS SIZE  2 "+LeaderBoardDetails.size());
                            for (int i=0;i<Leaderebrd.size();i++){
                                Leaderboard_Details leaderboard=Leaderebrd.get(i);
                                user_name.add(leaderboard.getName());
                                user_photo.add(leaderboard.getPhotoUrl());
                                lb_no_of_coins.add(leaderboard.getCoins());
                                user_id.add(leaderboard.getUserId());
                                totalluck.add(leaderboard.getTotalLucks());
                            }
                            mAdapter = new LeaderBoardAdapter(LeaderBoardDetails,UserID,auth_token,Leaderebrd,
                                    getApplicationContext(),Activity_LeaderBoard.this,SignedIn);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            Rcyclerview_Lucks.setLayoutManager(mLayoutManager);
                            Rcyclerview_Lucks.setItemAnimator(new DefaultItemAnimator());
                            Rcyclerview_Lucks.setAdapter(mAdapter);
                        }else {
                            Toast toast= Toast.makeText(getApplicationContext(),
                                    "No Data Found", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
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
            public void onFailure(@NotNull Call<LeaderBoard_data> call, @NotNull Throwable t) {
                progressbar.setVisibility(View.GONE);
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });
    }

    private boolean RankList(String rank, int i) {
        int RANK= Integer.parseInt(rank);
        int startrange,endrange;
        startrange=((i-1)*10)+1;
        endrange=i*10;
        if (RANK>=startrange&&RANK<=endrange) {

            return false;

        }    else {
            return true;
        }

    }
}
