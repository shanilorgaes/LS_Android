package com.orgaes.ls;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.orgaes.ls.Adapter.Winners_Adapter_LOOSERS;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCountData;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCount_Response;
import com.orgaes.ls.RETROFIT_NEW.DrawWinners.DrawWinners_Model;
import com.orgaes.ls.RETROFIT_NEW.DrawWinners.Draw_Winners_Response;
import com.orgaes.ls.RETROFIT_NEW.DrawWinners.Draw_Winners_Winner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_LuckLoser extends AppCompatActivity {

    TextView name_txt;
    TextView txt_CoinsOnHand,txt_TotalCoins;
    List<Draw_Winners_Winner>draw_winners_winners;
    Draw_Winners_Winner drawWinnersWinner;
    Winners_Adapter_LOOSERS mAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Spinner filter_spinner;
    List<String>Winner_Filter=new ArrayList<>();
    ConstraintLayout menu_icon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loser);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_LuckLoser.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_LuckLoser.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_LuckLoser.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_LuckLoser.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        name_txt=findViewById(R.id.name_txt);
        txt_CoinsOnHand=findViewById(R.id.coinsonhand_txt);
        txt_TotalCoins=findViewById(R.id.coins_total);
        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressbar);
        filter_spinner=findViewById(R.id.filter_spinner);
        menu_icon=findViewById(R.id.menu_icon);
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");
        txt_CoinsOnHand.setTypeface(dosis);
        txt_TotalCoins.setTypeface(dosis);
        name_txt.setTypeface(dosis);
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        String auth_token= prefsnew.getString("auth_token", "");
//        getWinners(auth_token);

//        Winner_Filter.add("SELECT WINNER TYPE");
        Winner_Filter.add("HOURLY");
        Winner_Filter.add("DAILY");
        Winner_Filter.add("WEEKLY");
        Winner_Filter.add("MONTHLY");
//        Winner_Filter.add("YEARLY");
//        Winner_Filter.add("MEGA");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Winner_Filter);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter_spinner.setAdapter(arrayAdapter);

        filter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parent.getChildAt(0)).setTextSize(10);
                ((TextView) parent.getChildAt(0)).setAllCaps(true);
                String ITEM= String.valueOf(filter_spinner.getSelectedItem());
                getWinners(auth_token,ITEM);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CoinCount(auth_token);

        menu_icon.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.btn_click);
            mp.start();
            Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });

    }

    public void getWinners(String auth_token, String ITEM){
        String Type="hourly";
        if (ITEM.equals("HOURLY")){
            Type="hourly";
        }else if (ITEM.equals("DAILY")){
            Type="daily";
        }else if (ITEM.equals("WEEKLY")){
            Type="weekly";
        }else if (ITEM.equals("MONTHLY")){
            Type="monthly";
        }else if (ITEM.equals("YEARLY")){
            Type="yearly";
        }else if (ITEM.equals("MEGA")){
            Type="mega";
        }
        progressBar.setVisibility(View.VISIBLE);
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<DrawWinners_Model> call=apiInterface.get_Winners(auth_token,Type);
        call.enqueue(new Callback<DrawWinners_Model>() {
            @Override
            public void onResponse(Call<DrawWinners_Model> call, Response<DrawWinners_Model> response) {
                progressBar.setVisibility(View.GONE);
                try {

                    int Code=response.body().getCode();
                    System.out.println("LS WINNERS DATA  "+Code);
                    if (Code==200){

                        Draw_Winners_Response draw_winners_response=response.body().getResponse();
                        draw_winners_winners=draw_winners_response.getWinners();
                        if (draw_winners_winners.size()>0){
                            recyclerView.setVisibility(View.VISIBLE);
                            for (int i=0;i<draw_winners_winners.size();i++){
                                drawWinnersWinner=draw_winners_winners.get(i);
                            }
                            mAdapter = new Winners_Adapter_LOOSERS(progressBar,auth_token,draw_winners_winners,getApplicationContext(),  Activity_LuckLoser.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(mAdapter);
                        }


                    }else if (Code==201){
                        recyclerView.setVisibility(View.GONE);
                        Toast.makeText(Activity_LuckLoser.this, "No Winners found", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<DrawWinners_Model> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                progressBar.setVisibility(View.GONE);
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
                        txt_TotalCoins.setText(""+Integer.parseInt(coinRate_response.getCoinCount()));
                    }else {
//                        Toast.makeText(Activity_HomePage.this, "Coin Error", Toast.LENGTH_SHORT).show();
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
    public void onBackPressed() {
        super.onBackPressed();
    }
}
