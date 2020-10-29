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
import com.orgaes.ls.Activity_LeaderBoard;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.FrndReq_Sent.Req_Sent;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile.LB_Prof_Data;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile.LB_Prof_Response;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile.LB_Prof_UserProfile;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoard_Model.Leaderboard_Details;
import com.orgaes.ls.RETROFIT_NEW.SendRequest.SendRequest;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.MyViewHolder> {


    Context context;
    String userID1;
    Activity_LeaderBoard activity_editions1;
    Dialog dialog;
    Dialog dialogReq;
    int clicked1=0;
    int clicked2=0;
    int clicked3=0;
    String signedIn;
    String token;
    Leaderboard_Details leaderboardUser;
    ArrayList<Leaderboard_Details> leaderBoardDetails;

    public LeaderBoardAdapter(ArrayList<Leaderboard_Details> leaderBoardDetails, String userID, String auth_token, List<Leaderboard_Details> leaderebrd, Context applicationContext, Activity_LeaderBoard activity_leaderBoard, String signedIn) {

        this.token=auth_token;
        this.activity_editions1=activity_leaderBoard;
        this.context=applicationContext;
        this.userID1=userID;
        this.leaderBoardDetails=leaderBoardDetails;
        this.signedIn=signedIn;

    }


    @NotNull
    @Override
    public LeaderBoardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_leaderboard_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(LeaderBoardAdapter.MyViewHolder holder, final int position) {
        Typeface avner = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                "Dosis-Medium.ttf");
        System.out.println("LS LEADERBOARD   "+leaderboardUser);

        holder.COINS.setTypeface(dosis);
        holder.USERNAME.setTypeface(dosis);
        holder.COINS.setTypeface(dosis);
        holder.RANK.setTypeface(dosis);
        holder.TotalLuck.setTypeface(dosis);

        if (userID1.equals(leaderBoardDetails.get(position).getUserId())){
            Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + leaderBoardDetails.get(position).getPhotoUrl()).into(holder.userpic);
        }else {
            if (leaderBoardDetails.get(position).getPhotoApprovalStatus().equals("1")){
                if (leaderBoardDetails.get(position).getPhotoLockStatus().equals("1")) {
                    Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + leaderBoardDetails.get(position).getPhotoUrl()).into(holder.userpic);
                }else {
                    holder.userpic.setImageResource(R.drawable.no_photo);
                }
            }else {
                holder.userpic.setImageResource(R.drawable.no_photo);
            }
        }


        holder.USERNAME.setText(leaderBoardDetails.get(position).getName());
        holder.COINS.setText(leaderBoardDetails.get(position).getCoins());
        if (userID1.equals(leaderBoardDetails.get(position).getUserId())){
            holder.constraintLayout3.setBackgroundResource(R.drawable.darkbg_leaderboard);
            holder.banner.setVisibility(View.VISIBLE);
        }else {
            holder.constraintLayout3.setBackgroundResource(R.drawable.frame);
            holder.banner.setVisibility(View.GONE);
        }
        holder.RANK.setText(""+leaderBoardDetails.get(position).getRank());
        holder.TotalLuck.setText(""+leaderBoardDetails.get(position).getTotalLucks());


        holder.constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                if (clicked1==0){
                    if (userID1.equals(leaderBoardDetails.get(position).getUserId())){
                        if (leaderBoardDetails.get(position).getRegister_status().equals("1")){
                            ProfilePopup(leaderBoardDetails.get(position).getUserId(),"same_User", leaderBoardDetails.get(position).getPhotoLockStatus());
                        }else {
                            Toast.makeText(context, "Sorry, This is a GUEST USER", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        if (leaderBoardDetails.get(position).getRegister_status().equals("1")){
                            ProfilePopup(leaderBoardDetails.get(position).getUserId(),"other_User",leaderBoardDetails.get(position).getPhotoLockStatus());
                        }else {
                            Toast.makeText(context, "Sorry, This is a GUEST USER", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return leaderBoardDetails.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView USERNAME,COINS, RANK ,TotalLuck;
        ImageView userpic;
        ImageView banner;
        public ConstraintLayout constraintLayout3;

        public MyViewHolder(View view) {
            super(view);
            USERNAME = (TextView) view.findViewById(R.id.username);
            constraintLayout3 = (ConstraintLayout) view.findViewById(R.id.constraintLayout3);
            COINS = (TextView) view.findViewById(R.id.coins);
            RANK = (TextView) view.findViewById(R.id.rank);
            userpic = (ImageView) view.findViewById(R.id.userpic);
            banner = (ImageView) view.findViewById(R.id.banner);
            TotalLuck = (TextView) view.findViewById(R.id.coinspent);

        }
    }

    private void ProfilePopup(String USERID, String other_user, String photoLockStatus) {
        if (clicked1==0){
            clicked1=1;
            dialog = new Dialog(activity_editions1, android.R.style.Theme_Dialog);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.profile_popup);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
            window.setAttributes(wlp);

            ImageView CloseButton=dialog.findViewById(R.id.imageclose);
            ImageView ONOFF=dialog.findViewById(R.id.onoff);
            TextView CoinsOnHand=dialog.findViewById(R.id.coinsonhand);
            TextView LucksOnHand=dialog.findViewById(R.id.luckonhand);
            TextView VouchersOnHand=dialog.findViewById(R.id.voucheronhand);
            TextView WalletMoney=dialog.findViewById(R.id.walletmoney);
            TextView Total_Luck_Win=dialog.findViewById(R.id.total_luckwin);
            TextView Shared_Coins=dialog.findViewById(R.id.shared_coins);
            TextView Shared_Luck=dialog.findViewById(R.id.shared_luck);
            TextView Total_Chunqz=dialog.findViewById(R.id.total_chunqz);
            TextView Total_Exchange=dialog.findViewById(R.id.total_exchange);
            TextView UserName=dialog.findViewById(R.id.usernametxtid);
            TextView UserID=dialog.findViewById(R.id.textView35);
            TextView UserEmail=dialog.findViewById(R.id.useremail);
            TextView UserPhone=dialog.findViewById(R.id.user_phone);
            TextView AboutMe=dialog.findViewById(R.id.aboutme);
            ImageView Prof_Pic=dialog.findViewById(R.id.profilepic);
            ProgressBar progressbar=dialog.findViewById(R.id.progressbar);
            ConstraintLayout ChunqReq=dialog.findViewById(R.id.constraintLayout46);
            ConstraintLayout CoinReq=dialog.findViewById(R.id.constraintLayout47);
            ConstraintLayout constraintLayoutmain=dialog.findViewById(R.id.main_lay);
            Zoomy.Builder builder = new Zoomy.Builder(activity_editions1)
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
            //Default Texts
            TextView txt1=dialog.findViewById(R.id.textView9);
            TextView txt2=dialog.findViewById(R.id.textView10);
            TextView txt3=dialog.findViewById(R.id.textView11);
            TextView txt4=dialog.findViewById(R.id.textView12);
            TextView txt5=dialog.findViewById(R.id.textView13);
            TextView txt6=dialog.findViewById(R.id.textView14);
            TextView txt7=dialog.findViewById(R.id.textView15);
            TextView txt8=dialog.findViewById(R.id.utextView16);
            TextView txt9=dialog.findViewById(R.id.textView16);
            TextView txt10=dialog.findViewById(R.id.textView18);

            //ONOFF Texts
            ImageView coinshand=dialog.findViewById(R.id.coinshand_onoff);
            ImageView activelucks=dialog.findViewById(R.id.activeluckhand_onoff);
            ImageView vouchersonhand=dialog.findViewById(R.id.vouchers_onoff);
            ImageView walletmoney=dialog.findViewById(R.id.walletmoney_onoff);
            ImageView total_luck_win=dialog.findViewById(R.id.total_luck_onoff);
            ImageView total_share_coin=dialog.findViewById(R.id.sharescoin_onoff);
            ImageView total_share_luck=dialog.findViewById(R.id.sharesluck_onoff);
            ImageView total_chunqs=dialog.findViewById(R.id.chunqz_onoff);
            ImageView total_exchanges=dialog.findViewById(R.id.exchanges_onoff);


            Typeface avner = Typeface.createFromAsset(context.getAssets(),
                    "AvenirLTStd-Light.otf");
            Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                    "Dosis-Medium.ttf");

            txt1.setTypeface(avner);
            txt2.setTypeface(avner);
            txt3.setTypeface(avner);
            txt4.setTypeface(avner);
            txt5.setTypeface(avner);
            txt6.setTypeface(avner);
            txt7.setTypeface(avner);
            txt8.setTypeface(avner);
            txt9.setTypeface(avner);
            txt10.setTypeface(avner);
            CoinsOnHand.setTypeface(avner);
            LucksOnHand.setTypeface(avner);
            VouchersOnHand.setTypeface(avner);
            WalletMoney.setTypeface(avner);
            Total_Luck_Win.setTypeface(avner);
            Shared_Coins.setTypeface(avner);
            Shared_Luck.setTypeface(avner);
            Total_Chunqz.setTypeface(avner);
            Total_Exchange.setTypeface(avner);
            UserName.setTypeface(avner);
            UserID.setTypeface(avner);
            UserEmail.setTypeface(avner);
            UserPhone.setTypeface(avner);
            AboutMe.setTypeface(dosis);

            progressbar.setVisibility(View.GONE);

            CloseButton.setOnClickListener(v -> {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                clicked1=0;
                dialog.dismiss();

            });
            ChunqReq.setOnClickListener(v -> {
                System.out.println("LS CHUNQ REQ SEND   ");
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                    clicked1=0;
                    Send_Request(USERID,progressbar);



            });

            Profile_Popup(other_user,ONOFF,CoinReq,coinshand,activelucks,vouchersonhand,walletmoney,total_luck_win,total_share_coin,total_share_luck,total_chunqs,total_exchanges,USERID,
                    CoinsOnHand,LucksOnHand,VouchersOnHand,WalletMoney,Total_Luck_Win,Shared_Coins,Shared_Luck,Total_Chunqz,Total_Exchange,UserName,UserID,UserEmail,UserPhone,AboutMe,Prof_Pic,dialog,photoLockStatus);

            try {
                dialog.getWindow().getAttributes().windowAnimations = R.style.MyAlertDialogStyle;
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void Profile_Popup(String other_user, ImageView ONOFF, ConstraintLayout coinReq, ImageView coinshand, ImageView activelucks, ImageView vouchersonhand, ImageView walletmoney, ImageView total_luck_win, ImageView total_share_coin, ImageView total_share_luck,
                               ImageView total_chunqs, ImageView total_exchanges, String USERID, TextView coinsOnHand, TextView lucksOnHand, TextView vouchersOnHand, TextView walletMoney, TextView total_Luck_Win, TextView shared_Coins,
                               TextView shared_Luck, TextView total_Chunqz, TextView total_Exchange, TextView userName, TextView userID, TextView userEmail, TextView userPhone, TextView aboutMe,
                               ImageView prof_Pic, Dialog dialog, String photoLockStatus) {
        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<LB_Prof_Data> call3 = apiInterface.Fetch_LB_Profile_user(token,USERID);
        call3.enqueue(new Callback<LB_Prof_Data>() {
            @Override
            public void onResponse(@NotNull Call<LB_Prof_Data> call, @NotNull retrofit2.Response<LB_Prof_Data> response) {
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        LB_Prof_Response lb_prof_response=response.body().getResponse();
                        List<LB_Prof_UserProfile>view_profile=lb_prof_response.getUserProfile();
                        if (view_profile.size()>0){
                            for (int i=0;i<view_profile.size();i++){

                                LB_Prof_UserProfile userProfile=view_profile.get(i);

                                if (userProfile.getPhone().equals("1")){
                                    userPhone.setText(userProfile.getMobile());
                                }

                                userName.setText(userProfile.getName());
                                userID.setText(userProfile.getId());
                                userEmail.setText(userProfile.getEmail());

                                if (userProfile.getSelfInfo() == null){
                                    aboutMe.setText("");
                                }else {
                                    aboutMe.setText(""+userProfile.getSelfInfo());
                                }
                                coinshand.setImageResource(R.drawable.off);
                                activelucks.setImageResource(R.drawable.off);
                                vouchersonhand.setImageResource(R.drawable.off);
                                walletmoney.setImageResource(R.drawable.off);
                                total_luck_win.setImageResource(R.drawable.off);
                                total_share_coin.setImageResource(R.drawable.off);
                                total_share_luck.setImageResource(R.drawable.off);
                                total_chunqs.setImageResource(R.drawable.off);
                                total_exchanges.setImageResource(R.drawable.off);
                                ONOFF.setImageResource(R.drawable.off);

                                if (userProfile.getCoin().equals("1")){
                                    coinshand.setImageResource(R.drawable.on);
                                    coinsOnHand.setText(userProfile.getCoinInHand());
                                }if (userProfile.getActiveLuck().equals("1")){
                                    lucksOnHand.setText(userProfile.getActiveLucks());
                                    activelucks.setImageResource(R.drawable.on);
                                }if (userProfile.getVoucher().equals("1")){
                                    vouchersOnHand.setText(userProfile.getVouchers());
                                    vouchersonhand.setImageResource(R.drawable.on);
                                }if (userProfile.getWallet().equals("1")){
                                    walletMoney.setText(userProfile.getWalletMoney());
                                    walletmoney.setImageResource(R.drawable.on);
                                }if (userProfile.getTotalLuck().equals("1")){
                                    total_Luck_Win.setText(userProfile.getTotalLucks());
                                    total_luck_win.setImageResource(R.drawable.on);
                                }if (userProfile.getShareCoin().equals("1")){
                                    shared_Coins.setText(userProfile.getSharedCoins());
                                    total_share_coin.setImageResource(R.drawable.on);
                                }if (userProfile.getShareLuck().equals("1")){
                                    shared_Luck.setText(userProfile.getSharedLucks());
                                    total_share_luck.setImageResource(R.drawable.on);
                                }if (userProfile.getFriends().equals("1")){
                                    total_Chunqz.setText(userProfile.getTotalFriends());
                                    total_chunqs.setImageResource(R.drawable.on);
                                }if (userProfile.getExchanges().equals("1")){
                                    total_Exchange.setText(userProfile.getExchangedCoins());
                                    total_exchanges.setImageResource(R.drawable.on);
                                }
                                System.out.println("LS USER DATA PIC 1   "+userProfile.getIsActive());
                                System.out.println("LS USER DATA PIC 2   "+userProfile.getPhotoApprovalStatus());
                                if (userProfile.getPhotoApprovalStatus().equals("0")) {
                                    prof_Pic.setImageResource(R.drawable.no_photo);
                                } else {
                                    if (photoLockStatus.equals("1"))
                                    {
                                        Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + userProfile.getPhotoUrl()).into(prof_Pic);
                                    }else {
                                        prof_Pic.setImageResource(R.drawable.no_photo);
                                    }

                                }
                                coinReq.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                                        mp.start();
                                        if (signedIn.equals("1")) {
                                            dialog.dismiss();
                                            Coin_Request(userID1, userProfile.getId(), userProfile.getCoinInHand());
                                        }else {
                                            Toast toast = Toast.makeText(context,"Please sign in to send request", Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.CENTER, 0, 0);
                                            toast.show();
                                        }
                                    }
                                });
                            }
                        }
                    }else if (Code==201){

                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<LB_Prof_Data> call, Throwable t) {
                call.cancel();
                Toast toast= Toast.makeText(context,
                        "Some Error Occured, Contact Our Customer Care", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();

            }
        });
    }
    private void Send_Request(String sessionID,  ProgressBar progressbar) {

        APIInterface apiInterface;
        progressbar.setVisibility(View.VISIBLE);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Req_Sent> call3 = apiInterface.SendRequest(token,sessionID);
        call3.enqueue(new Callback<Req_Sent>() {
            @Override
            public void onResponse(Call<Req_Sent> call, retrofit2.Response<Req_Sent> response) {
                progressbar.setVisibility(View.GONE);
                try {
                    int Code=response.body().getCode();
                    if (Code==200){

                        Req_Sent();
                    }else if (Code==203){

                        Toast.makeText(context, "Already Sent", Toast.LENGTH_SHORT).show();

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

                progressbar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void Coin_Request(String sessionID, String s, String coinSUM) {

        dialogReq = new Dialog(activity_editions1, android.R.style.Theme_Dialog);
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
        ImageView Close=dialogReq.findViewById(R.id.ic_close);
        Button SendReeq=dialogReq.findViewById(R.id.button_send);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                dialogReq.dismiss();
            }
        });
        progressbar.setVisibility(View.GONE);
        SendReeq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                progressbar.setVisibility(View.VISIBLE);
                int coinTot= Integer.parseInt(coinSUM);
                String ReqCoin= Req_Field.getText().toString();
                int ReqCoin2 = 0;
                try {
                    ReqCoin2= Integer.parseInt(ReqCoin);
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (coinTot>ReqCoin2){

                    if (sessionID.equals(s)){
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(context, "Wrong Request", Toast.LENGTH_SHORT).show();
                    }else {
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
                                        if (clicked3==0){
                                            CoinReq_Sent(dialogReq);
                                        }

                                    }else if (Code==203){

                                        Toast.makeText(context, "Already Sent", Toast.LENGTH_SHORT).show();

                                    }else if (Code==201){

                                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                                    }else if (Code==205){

                                        Toast.makeText(context, "Sender and reciever is same", Toast.LENGTH_SHORT).show();

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
                            public void onFailure(Call<SendRequest> call, Throwable t) {
                                call.cancel();
                                Toast toast= Toast.makeText(context,
                                        "Some Error Occured, Contact Our Customer Care", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                                toast.show();

                                progressbar.setVisibility(View.GONE);
                            }
                        });
                    }


                }else {
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(context, "Sorry, You requested more coins", Toast.LENGTH_SHORT).show();
                }

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

        if (clicked2==0){
            clicked2=1;
            clicked1=0;
            Dialog dialog=new Dialog(activity_editions1, android.R.style.Theme_Dialog);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.friend_request_send);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            ConstraintLayout constraintLayoutmain=dialog.findViewById(R.id.main_lay);
            Zoomy.Builder builder = new Zoomy.Builder(activity_editions1)
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
            TextView Goodluck=dialog.findViewById(R.id.goodluck);
            Typeface avner = Typeface.createFromAsset(context.getAssets(),
                    "AvenirLTStd-Light.otf");
            Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                    "Dosis-Medium.ttf");
            Goodluck.setTypeface(dosis);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                    mp.start();
                    clicked2=0;
                    clicked1=0;
                    dialog.dismiss();
                }
            });

            try {
                dialog.getWindow().getAttributes().windowAnimations = R.style.MyAlertDialogStyle;
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
                clicked2=1;
            }
        }


    }
    public void CoinReq_Sent(Dialog dialogReq){
        if (clicked3==0){
            clicked3=1;
            dialogReq.dismiss();
            Dialog dialog=new Dialog(activity_editions1, android.R.style.Theme_Dialog);
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
            Zoomy.Builder builder = new Zoomy.Builder(activity_editions1)
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
                    clicked3=0;
                    clicked1=0;
                    clicked2=0;
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