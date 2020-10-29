package com.orgaes.ls.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;
import com.orgaes.ls.Activity_Choose_Language;
import com.orgaes.ls.Activity_HomePage;
import com.orgaes.ls.Activity_Wallet;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.FrndReq_Sent.Req_Sent;
import com.orgaes.ls.RETROFIT_NEW.LuckWinners.TodaysWinner_Data;
import com.orgaes.ls.RETROFIT_NEW.SendRequest.SendRequest;
import com.orgaes.ls.RETROFIT_NEW.WinnerData.WinnerData_Main;
import com.orgaes.ls.RETROFIT_NEW.WinnerData.WinnerData_Response;
import com.orgaes.ls.RETROFIT_NEW.WinnerData.WinnerDetails_;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataWinners extends RecyclerView.Adapter<AdapterDataWinners.MyViewHolder> {



    Context context;
    Activity_HomePage activity_homePage1;
    Dialog dialog;
    Dialog dialogReq;
    String SessionID;
    List<TodaysWinner_Data> todaysWinners =new ArrayList<>();

    int Clicked=0;
    int ClickedCoinreq=0;
    String LuckClicked="1";
    String signedIn;
    String token;
    ProgressBar progressBar;

    public AdapterDataWinners(String auth_token, String signedIn, ProgressBar porgressbar, List<TodaysWinner_Data> todaysWinner_data, Activity_HomePage activity_homePage, Context applicationContext) {
        this.todaysWinners=todaysWinner_data;
        this.activity_homePage1=activity_homePage;
        this.context=applicationContext;
        this.progressBar=porgressbar;
        this.signedIn=signedIn;
        this.token=auth_token;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView UserImage;
        ConstraintLayout mainitemlayout;

        public MyViewHolder(View view) {
            super(view);
            UserImage = (ImageView) view.findViewById(R.id.userimage);
            mainitemlayout = (ConstraintLayout) view.findViewById(R.id.mainitemlayout);

        }
    }

    @NotNull
    @Override
    public AdapterDataWinners.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull AdapterDataWinners.MyViewHolder holder, final int position) {



        if (todaysWinners.get(position).getIsActive().equals("1")){
            if (todaysWinners.get(position).getPhotoApprovalStatus().equals("1")){
                Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + todaysWinners.get(position).getPhotoUrl()).into(holder.UserImage);
            }else {
                holder.UserImage.setImageResource(R.drawable.no_photo);
            }
        }else {
            holder.UserImage.setImageResource(R.drawable.no_photo);
        }

            System.out.println("LS WINNER IMAGE  "+AppConfig.URL_BASE_VIDEOS + todaysWinners.get(position).getPhotoUrl());
        holder.mainitemlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                if (todaysWinners.get(position).getIsActive().equals("1")) {
                    if (Clicked==0){

                        LuckClicked="1";
                        activity_homePage1.onClickCalled(LuckClicked);
                        System.out.println("LS TODAYs WINNER   "+todaysWinners.get(position).getType());

                        if (todaysWinners.get(position).getIsActive().equals("1")){

                            viewWinnerDatas(todaysWinners.get(position).getId(),todaysWinners.get(position).getType(),position);
                        }


                    }

                }else {
                    Toast toast = Toast.makeText(context,"Oh Ho ! Its a private account", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return todaysWinners.size();
    }

    private void viewWinnerDatas(String id, String type, int position) {
        progressBar.setVisibility(View.VISIBLE);
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<WinnerData_Main> call3 = apiInterface.Api_GetWinnersData("http://ec2-13-232-195-100.ap-south-1.compute.amazonaws.com/ls_dev/userapi/winnerdetail/"+id+"/"+type);
        call3.enqueue(new Callback<WinnerData_Main>() {
            @Override
            public void onResponse(@NotNull Call<WinnerData_Main> call, @NotNull Response<WinnerData_Main> response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    assert response.body() != null;
                    int Code =response.body().getCode();
                    System.out.println("LS LUCK DATA ::  "+response.body().getResponse()+"   "+id+"   "+type);
                    if (Code==200){

                        WinnerData_Response response1=response.body().getResponse();
                        List<WinnerDetails_>luckDetails_models=response1.getWinnerDetails();
                        System.out.println("LS LUCK DATA 111111::  "+luckDetails_models);
                        popupWinnerdata(luckDetails_models,0,type);
                    }


                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("LS LUCK DATA2 ::  "+e.getMessage());
                    Toast toast = Toast.makeText(context,"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<WinnerData_Main> call, Throwable t) {
                t.printStackTrace();
                Toast toast= Toast.makeText(context,
                        "Some Error Occured, Contact Our Customer Care", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();

                System.out.println("LS LUCK DATA3 ::  "+t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });



    }



    @SuppressLint("SetTextI18n")
    private void popupWinnerdata(List<WinnerDetails_> todaysLuck_homes, int position, String type) {

        try
        {
            if (Clicked==0){
                Clicked=1;
                dialog = new Dialog(activity_homePage1, android.R.style.Theme_Dialog);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.todayswinner_layout);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setCancelable(false);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                ConstraintLayout constraintLayoutmain=dialog.findViewById(R.id.main_lay);
                Zoomy.Builder builder = new Zoomy.Builder(activity_homePage1)
                        .target(constraintLayoutmain)
                        .interpolator(new OvershootInterpolator())
                        .tapListener(new TapListener() {
                            @Override
                            public void onTap(View v) {
//                            Toast.makeText(Activity_HomePage.this, "Tap on "
//                                    + v.getTag(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .longPressListener(new LongPressListener() {
                            @Override
                            public void onLongPress(View v) {
//                            Toast.makeText(Activity_HomePage.this, "Long press on "
//                                    + v.getTag(), Toast.LENGTH_SHORT).show();
                            }
                        }).doubleTapListener(new DoubleTapListener() {
                            @Override
                            public void onDoubleTap(View v) {
//                            Toast.makeText(Activity_HomePage.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.register();

                Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                        "Dosis-Medium.ttf");
                Typeface avner = Typeface.createFromAsset(context.getAssets(),
                        "AvenirLTStd-Light.otf");

                ImageView Close_Button=dialog.findViewById(R.id.ic_close);
                ImageView Sponsorimage=dialog.findViewById(R.id.sponsorimage);
                ImageView UserPhoto=dialog.findViewById(R.id.profilepic);
                ImageView LuckImage=dialog.findViewById(R.id.luck_image);
                ImageView Share=dialog.findViewById(R.id.share);
                TextView userID=dialog.findViewById(R.id.user_id);
                TextView Time=dialog.findViewById(R.id.time);
                TextView Date=dialog.findViewById(R.id.date_id);
                TextView EditionName=dialog.findViewById(R.id.textView38);
                TextView Username=dialog.findViewById(R.id.usernametxtid);
                TextView UserEmail=dialog.findViewById(R.id.useremail);
                TextView UserPhone=dialog.findViewById(R.id.user_phone);
                TextView UserCoin=dialog.findViewById(R.id.coincount);
                TextView Active_Luck=dialog.findViewById(R.id.activeluck);
                TextView VoucheronHand=dialog.findViewById(R.id.voucher_on_hand);
                TextView WalletMoney=dialog.findViewById(R.id.walletmoney);
                TextView Total_Luck=dialog.findViewById(R.id.total_luck);
                TextView Total_Shared_coins=dialog.findViewById(R.id.total_shared_coins);
                TextView Total_Shared_Lucks=dialog.findViewById(R.id.total_shared_lucks);
                TextView Total_Chunqz=dialog.findViewById(R.id.total_chunqz);
                TextView Total_Exchanges=dialog.findViewById(R.id.exchanges);
                ConstraintLayout Chunq_Req=dialog.findViewById(R.id.chunq_requests);
                ConstraintLayout Chat_Request=dialog.findViewById(R.id.chatrequest);
                ConstraintLayout Coin_Request=dialog.findViewById(R.id.coin_request);

                TextView lock_txt=dialog.findViewById(R.id.lock_txt);
                TextView textView37=dialog.findViewById(R.id.textView37);
                TextView textView38=dialog.findViewById(R.id.textView38);
                TextView textView9=dialog.findViewById(R.id.textView9);
                TextView textView10=dialog.findViewById(R.id.textView10);
                TextView textView11=dialog.findViewById(R.id.textView11);
                TextView textView12=dialog.findViewById(R.id.textView12);
                TextView textView13=dialog.findViewById(R.id.textView13);
                TextView textView14=dialog.findViewById(R.id.textView14);
                TextView textView15=dialog.findViewById(R.id.textView15);
                TextView utextView16=dialog.findViewById(R.id.utextView16);
                TextView textView16=dialog.findViewById(R.id.textView16);



                Username.setText(todaysLuck_homes.get(0).getName());
                userID.setText(todaysLuck_homes.get(0).getId());
                UserEmail.setText(todaysLuck_homes.get(0).getEmail());
                UserPhone.setText(todaysLuck_homes.get(0).getMobile());
                UserCoin.setText(todaysLuck_homes.get(0).getCoinInHand());
                Active_Luck.setText(todaysLuck_homes.get(0).getActiveLucks());
                VoucheronHand.setText(todaysLuck_homes.get(0).getVouchers());
                WalletMoney.setText(todaysLuck_homes.get(0).getWallet_money());
                Total_Luck.setText(todaysLuck_homes.get(0).getTotalLucks());
                Total_Shared_coins.setText(todaysLuck_homes.get(0).getSharedCoins());
                Total_Shared_Lucks.setText(todaysLuck_homes.get(0).getShareLuck());
                Total_Chunqz.setText(todaysLuck_homes.get(0).getTotalFriends());
                Total_Exchanges.setText(todaysLuck_homes.get(0).getExchanges());
                EditionName.setText("EDITION : "+todaysLuck_homes.get(0).getEdition());
                System.out.println("LS WINNER SPONSOR   "+todaysLuck_homes.get(0).getEdition());
//                Date.setText(": DATE : "+todaysLuck_homes.get(position).get());
//                Time.setText(": TIME : "+todaysLuck_homes.get(position).tim());

                //onoff
                ImageView OnOffUserCoin=dialog.findViewById(R.id.onoff_coinhand);
                ImageView OnOffActive_Luck=dialog.findViewById(R.id.onoffactiveluck);
                ImageView OnOffVoucheronHand=dialog.findViewById(R.id.onoffvoucher_on_hand);
                ImageView OnOffWalletMoney=dialog.findViewById(R.id.onoffwalletmoney);
                ImageView OnOffTotal_Luck=dialog.findViewById(R.id.onofftotal_luckwin);
                ImageView OnOffTotal_Shared_coins=dialog.findViewById(R.id.onofftotal_shared_coins);
                ImageView OnOffTotal_Shared_Lucks=dialog.findViewById(R.id.onofftotal_shared_lucks);
                ImageView OnOffTotal_Chunqz=dialog.findViewById(R.id.onoff_chunqz);
                ImageView OnOffTotal_Exchanges=dialog.findViewById(R.id.onofftotal_exchanges);
                lock_txt.setTypeface(avner);
                textView37.setTypeface(avner);
                textView38.setTypeface(avner);
                textView9.setTypeface(avner);
                textView10.setTypeface(avner);
                textView11.setTypeface(avner);
                textView12.setTypeface(avner);
                textView13.setTypeface(avner);
                textView14.setTypeface(avner);
                textView15.setTypeface(avner);
                utextView16.setTypeface(avner);
                textView16.setTypeface(avner);
                Username.setTypeface(avner);
                userID.setTypeface(avner);
                UserEmail.setTypeface(avner);
                UserPhone.setTypeface(avner);
                UserCoin.setTypeface(avner);
                Active_Luck.setTypeface(avner);
                VoucheronHand.setTypeface(avner);
                WalletMoney.setTypeface(avner);
                Total_Luck.setTypeface(avner);
                Total_Shared_coins.setTypeface(avner);
                Total_Shared_Lucks.setTypeface(avner);
                Total_Chunqz.setTypeface(avner);
                Total_Exchanges.setTypeface(avner);
                EditionName.setTypeface(avner);
                Date.setTypeface(avner);
                Time.setTypeface(avner);

                OnOffUserCoin.setImageResource(R.drawable.off);
                OnOffActive_Luck.setImageResource(R.drawable.off);
                OnOffVoucheronHand.setImageResource(R.drawable.off);
                OnOffWalletMoney.setImageResource(R.drawable.off);
                OnOffTotal_Shared_coins.setImageResource(R.drawable.off);
                OnOffTotal_Chunqz.setImageResource(R.drawable.off);
                OnOffTotal_Exchanges.setImageResource(R.drawable.off);
                OnOffTotal_Luck.setImageResource(R.drawable.off);
                OnOffTotal_Shared_Lucks.setImageResource(R.drawable.off);

                System.out.println("LS IMAGES TEST  "+AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(position).getPhotoUrl());
                System.out.println("LS IMAGES TEST2  "+AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(position).getImage());
                System.out.println("LS IMAGES TEST3  "+AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(position).getClientUserImage());

                if (todaysLuck_homes.get(position).getPhotoApprovalStatus().equals("1")){
                    Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(position).getPhotoUrl())
                            .into(UserPhoto);
                }else{

                    UserPhoto.setBackgroundResource(R.drawable.no_photo);

                } if (todaysLuck_homes.get(position).getCoin().equals("1")){
                    OnOffUserCoin.setImageResource(R.drawable.on);
                    UserCoin.setVisibility(View.VISIBLE);
                }
                if (todaysLuck_homes.get(position).getActiveLuck().equals("1")){
                    OnOffActive_Luck.setImageResource(R.drawable.on);
                    Active_Luck.setVisibility(View.VISIBLE);
                } if (todaysLuck_homes.get(position).getVoucher().equals("1")){
                    OnOffVoucheronHand.setImageResource(R.drawable.on);
                    VoucheronHand.setVisibility(View.VISIBLE);
                } if (todaysLuck_homes.get(position).getWallet().equals("1")){
                    OnOffWalletMoney.setImageResource(R.drawable.on);
                    WalletMoney.setVisibility(View.VISIBLE);
                } if (todaysLuck_homes.get(position).getShareCoin().equals("1")){
                    OnOffTotal_Shared_coins.setImageResource(R.drawable.on);
                    Total_Shared_coins.setVisibility(View.VISIBLE);
                } if (todaysLuck_homes.get(position).getFriends().equals("1")){
                    OnOffTotal_Chunqz.setImageResource(R.drawable.on);
                    Total_Chunqz.setVisibility(View.VISIBLE);
                } if (todaysLuck_homes.get(position).getExchanges().equals("1")){
                    OnOffTotal_Exchanges.setImageResource(R.drawable.on);
                    Total_Exchanges.setVisibility(View.VISIBLE);
                } if (todaysLuck_homes.get(position).getTotalLuck().equals("1")){
                    OnOffTotal_Luck.setImageResource(R.drawable.on);
                    Total_Luck.setVisibility(View.VISIBLE);
                } if (todaysLuck_homes.get(position).getShareLuck().equals("1")){
                    OnOffTotal_Shared_Lucks.setImageResource(R.drawable.on);
                    Total_Shared_Lucks.setVisibility(View.VISIBLE);
                } if (todaysLuck_homes.get(position).getPhone().equals("1")){
                    UserPhone.setVisibility(View.VISIBLE);
                }


                Close_Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Clicked=0;
                        final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                        mp.start();
                        LuckClicked="0";
                        activity_homePage1.onClickCalled(LuckClicked);
                        dialog.dismiss();
                    }
                });
                Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(position).getImage()).into(LuckImage);
                Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(position).getClientUserImage()).into(Sponsorimage);

                wlp.gravity = Gravity.BOTTOM;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);

                Chunq_Req.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                        mp.start();
                        System.out.println("LS REQ CLICK  ");
                        if (signedIn.equals("1")){
                            LuckClicked="1";
                            activity_homePage1.onClickCalled(LuckClicked);
                            Clicked=0;
                            Send_Request(userID.getText().toString());
                        }else {
                            Toast toast = Toast.makeText(context,"Please sign in to send request", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }


                    }
                });


                Coin_Request.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                        mp.start();
                        if (signedIn.equals("1")){
                            LuckClicked="1";
                            activity_homePage1.onClickCalled(LuckClicked);
                            Clicked=0;
                            Coin_Request(SessionID,userID.getText().toString(),todaysLuck_homes.get(position).getCoinInHand());
                        }else {
                            Toast toast = Toast.makeText(context,"Please sign in to send request", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }

                    }
                });
                try {
                    dialog.getWindow().getAttributes().windowAnimations = R.style.MyAlertDialogStyle;
                    dialog.show();
                } catch (Exception ignored) {
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private void Send_Request(String sessionID) {
        progressBar.setVisibility(View.VISIBLE);
        System.out.println("LS WINNER ID   "+sessionID);
        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Req_Sent> call3 = apiInterface.SendRequest(token,sessionID);
        call3.enqueue(new Callback<Req_Sent>() {
            @Override
            public void onResponse(@NotNull Call<Req_Sent> call, @NotNull retrofit2.Response<Req_Sent> response) {
                progressBar.setVisibility(View.GONE);
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        Req_Sent();
                    }else if (Code==203){

                        Toast toast = Toast.makeText(context,"Already sent", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }else if (Code==205){

                        Toast.makeText(context, "Same user", Toast.LENGTH_SHORT).show();

                    }else if (Code==201){

                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==301){

                        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==303){

                        Toast.makeText(context, "Session Expired", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }

                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(context,"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Req_Sent> call, Throwable t) {
                call.cancel();
                Toast toast= Toast.makeText(context,
                        "Some Error Occured, Contact Our Customer Care", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();

                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void Coin_Request(String sessionID, String s, String coinSUM) {
        dialogReq = new Dialog(activity_homePage1, android.R.style.Theme_Dialog);
        dialogReq.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogReq.setContentView(R.layout.coin_req_page);
        dialogReq.setCanceledOnTouchOutside(false);
        dialogReq.setCancelable(false);
        dialogReq.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialogReq.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogReq.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialogReq.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        EditText Req_Field=dialogReq.findViewById(R.id.req_edt);
        ProgressBar progressbar=dialogReq.findViewById(R.id.progressbar);
        Button SendReeq=dialogReq.findViewById(R.id.button_send);
        ImageView ic_close=dialogReq.findViewById(R.id.ic_close);
        progressbar.setVisibility(View.GONE);
        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                dialogReq.dismiss();
            }
        });

        SendReeq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                progressbar.setVisibility(View.VISIBLE);
                if (Req_Field.getText().toString().equals("")){
                    Toast.makeText(context, "Enter the coin number", Toast.LENGTH_SHORT).show();
                }else {
                    int coinTot= Integer.parseInt(coinSUM);
                    String ReqCoin= (Req_Field.getText().toString());
                int ReqCoin2 = 0;
                try {
                     ReqCoin2= Integer.parseInt(ReqCoin);
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                System.out.println("LS COINS DETAILS   "+coinTot+"    "+ReqCoin2);
                if (coinTot>ReqCoin2){
                        APIInterface apiInterface;
                        apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<SendRequest> call3 = apiInterface.SendCoinRequest(token,sessionID,s, String.valueOf(ReqCoin2));
                        call3.enqueue(new Callback<SendRequest>() {
                            @Override
                            public void onResponse(Call<SendRequest> call, retrofit2.Response<SendRequest> response) {
                                progressbar.setVisibility(View.GONE);
                                try {
                                    int Code=response.body().getCode();
                                    if (Code==200){

                                        if (ClickedCoinreq==0) {
                                            CoinReq_Sent(dialogReq);
                                        }
                                    }else if (Code==201){

                                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                                    }else if (Code==203){

                                        Toast.makeText(context, "Does not contain much number of coins", Toast.LENGTH_SHORT).show();

                                    }else if (Code==205){

                                        Toast.makeText(context, "Sender and Reciever is same ", Toast.LENGTH_SHORT).show();

                                    }else if (Code==207){

                                        Toast.makeText(context, "Validation Error", Toast.LENGTH_SHORT).show();
                                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                                        settings.edit().remove("Lang_Selected").apply();
                                        settings.edit().clear().apply();
                                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        context.startActivity(intlogout);
                                    }else if (Code==301){

                                        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                                        settings.edit().remove("Lang_Selected").apply();
                                        settings.edit().clear().apply();
                                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        context.startActivity(intlogout);
                                    }else if (Code==303){

                                        Toast.makeText(context, "Session Expired", Toast.LENGTH_SHORT).show();
                                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                                        settings.edit().remove("Lang_Selected").apply();
                                        settings.edit().clear().apply();
                                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        context.startActivity(intlogout);
                                    }else if (Code==305){

                                        Toast.makeText(context, "Token Missing", Toast.LENGTH_SHORT).show();
                                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                                        settings.edit().remove("Lang_Selected").apply();
                                        settings.edit().clear().apply();
                                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        context.startActivity(intlogout);
                                    }

                                }catch (Exception  e){
                                    e.printStackTrace();
                                    Toast toast = Toast.makeText(context,"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();
                                }
                            }

                            @Override
                            public void onFailure(Call<SendRequest> call, Throwable t) {
                                call.cancel();
                                Toast toast= Toast.makeText(context,
                                        "Some Error Occured, Contact Our Customer Care", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                                toast.show();

                                progressbar.setVisibility(View.GONE);
                            }
                        });
                    }else {
                    progressbar.setVisibility(View.GONE);
                    Toast toast = Toast.makeText(context,"Sorry, you requested more coins", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    progressbar.setVisibility(View.GONE);
                    }

                }
//
            }
        });

        try {
            dialogReq.getWindow().getAttributes().windowAnimations = R.style.MyAlertDialogStyle;
            dialogReq.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Req_Sent(){

        Dialog dialog=new Dialog(activity_homePage1, android.R.style.Theme_Dialog);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.friend_request_send);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        ImageView close=dialog.findViewById(R.id.ic_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                dialog.dismiss();
            }
        });
        ConstraintLayout constraintLayoutmain=dialog.findViewById(R.id.main_lay);
        Zoomy.Builder builder = new Zoomy.Builder(activity_homePage1)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                            Toast.makeText(Activity_HomePage.this, "Tap on "
//                                    + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                            Toast.makeText(Activity_HomePage.this, "Long press on "
//                                    + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                            Toast.makeText(Activity_HomePage.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        try {
            dialog.getWindow().getAttributes().windowAnimations = R.style.MyAlertDialogStyle;
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void CoinReq_Sent(Dialog dialogReq){
        dialogReq.dismiss();
        if (ClickedCoinreq==0){
            ClickedCoinreq=1;
            Dialog dialog=new Dialog(activity_homePage1, android.R.style.Theme_Dialog);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.coin_req_sent);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            ConstraintLayout constraintLayoutmain=dialog.findViewById(R.id.main_lay);
            Zoomy.Builder builder = new Zoomy.Builder(activity_homePage1)
                    .target(constraintLayoutmain)
                    .interpolator(new OvershootInterpolator())
                    .tapListener(new TapListener() {
                        @Override
                        public void onTap(View v) {
//                            Toast.makeText(Activity_HomePage.this, "Tap on "
//                                    + v.getTag(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .longPressListener(new LongPressListener() {
                        @Override
                        public void onLongPress(View v) {
//                            Toast.makeText(Activity_HomePage.this, "Long press on "
//                                    + v.getTag(), Toast.LENGTH_SHORT).show();
                        }
                    }).doubleTapListener(new DoubleTapListener() {
                        @Override
                        public void onDoubleTap(View v) {
//                            Toast.makeText(Activity_HomePage.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                        }
                    });

            builder.register();
            ImageView close=dialog.findViewById(R.id.ic_close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                    mp.start();
                    ClickedCoinreq=0;
                    dialog.dismiss();
                }
            });

            try {
                dialog.getWindow().getAttributes().windowAnimations = R.style.MyAlertDialogStyle;
                dialog.show();
            } catch (Exception e) {

                e.printStackTrace();

            }
        }


    }

}