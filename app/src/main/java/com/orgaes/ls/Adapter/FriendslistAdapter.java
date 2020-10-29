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

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;
import com.orgaes.ls.Activity_Choose_Language;
import com.orgaes.ls.Activity_HomePage;
import com.orgaes.ls.Activity_ProfileView;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.FriendsList.FriendsList_Data;
import com.orgaes.ls.RETROFIT_NEW.FrndReq_Sent.Req_Sent;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile.LB_Prof_Data;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile.LB_Prof_Response;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile.LB_Prof_UserProfile;
import com.orgaes.ls.RETROFIT_NEW.SendRequest.SendRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class FriendslistAdapter extends RecyclerView.Adapter<FriendslistAdapter.ViewHolder>{


    Activity_ProfileView activity;
    Context context;
    Dialog dialogReq;
    List<String> Userid=new ArrayList<>();
    List<String>UserName=new ArrayList<>();
    List<String>UserPhoto=new ArrayList<>();
    List<String>privacy_status=new ArrayList<>();
    List<FriendsList_Data> users=new ArrayList<>();
    String Us_ID;
    Dialog dialog;
    int clicked1=0;
    String auth_token1;
    int clicked2=0;
    int clicked3=0;
    public FriendslistAdapter(String auth_token, String userID1, List<String> userid, List<String> userName, List<String> userPhoto,
                              List<String> PRIVACY, List<FriendsList_Data> users1,
                              Context applicationContext, Activity_ProfileView friendsList) {

        this.activity = friendsList;
        this.context = applicationContext;
        Userid = userid;
        UserName = userName;
        UserPhoto = userPhoto;
        privacy_status = PRIVACY;
        users = users1;
        Us_ID = userID1;
        auth_token1 = auth_token;

    }

    @NonNull
    @Override
    public FriendslistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.friends_item_profile, parent, false);
        FriendslistAdapter.ViewHolder viewHolder = new FriendslistAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final FriendslistAdapter.ViewHolder holder, final int position) {


        Typeface avner = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                "Dosis-Medium.ttf");

        holder.Username.setTypeface(dosis);
        holder.coinscount.setTypeface(dosis);
        holder.age_category.setTypeface(avner);
        holder.gender.setTypeface(avner);

        try {
            holder.Username.setText(UserName.get(position));
            holder.coinscount.setText(users.get(position).getTotalCoins() + "COINS");
            holder.age_category.setText(users.get(position).getGroupCode());
            if (users.get(position).getGender().equals("MALE") || users.get(position).getGender().equals("Male")) {
                holder.gender.setText("M");
            } else if (users.get(position).getGender().equals("FEMALE") || users.get(position).getGender().equals("Female")) {
                holder.gender.setText("F");
            } else if (users.get(position).getGender().equals("TRANS") || users.get(position).getGender().equals("Trans")) {
                holder.gender.setText("T");
            }


            Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + UserPhoto.get(position)).into(holder.imageView);

            holder.mainitemlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                    mp.start();
                    if (users.get(position).getIsActive().equals("1")) {


                        ProfilePopup(users.get(position).getId());

                    } else {
                        Toast toast = Toast.makeText(context, "Its a private account", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView Username;
        private final TextView coinscount;
        private final TextView age_category;
        private final TextView gender;
        TextView userid;
        private final ConstraintLayout mainitemlayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.userimage);
            Username = itemView.findViewById(R.id.username);
            coinscount = itemView.findViewById(R.id.coinscount);
            age_category = itemView.findViewById(R.id.age_category);
            gender = itemView.findViewById(R.id.gender);
            mainitemlayout = itemView.findViewById(R.id.main_layout);


        }
    }
    @Override
    public int getItemCount() {
        return Userid.size();
    }


    @Override
    public int getItemViewType(int position) {

        return position;
    }

    private void ProfilePopup(String USERID) {
        if (clicked1==0){
            clicked1=1;
            dialog = new Dialog(activity, android.R.style.Theme_Dialog);
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
            ConstraintLayout constraintLayoutmain=dialog.findViewById(R.id.main_lay);
            Zoomy.Builder builder = new Zoomy.Builder(activity)
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

            CloseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                    mp.start();
                    clicked1=0;
                    dialog.dismiss();

                }
            });
            ChunqReq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                    mp.start();
                    clicked1=0;
                    Send_Request(USERID,progressbar);
                }
            });

            Profile_Popup(CoinReq,coinshand,activelucks,vouchersonhand,walletmoney,total_luck_win,total_share_coin,
                    total_share_luck,total_chunqs,total_exchanges,USERID,
                    CoinsOnHand,LucksOnHand,VouchersOnHand,WalletMoney,Total_Luck_Win,Shared_Coins,Shared_Luck,Total_Chunqz,Total_Exchange,UserName,UserID,UserEmail,UserPhone,AboutMe,Prof_Pic,dialog);

            try {
                dialog.getWindow().getAttributes().windowAnimations = R.style.MyAlertDialogStyle;
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void Profile_Popup(ConstraintLayout coinReq, ImageView coinshand, ImageView activelucks, ImageView vouchersonhand,
                               ImageView walletmoney, ImageView total_luck_win, ImageView total_share_coin, ImageView total_share_luck,
                               ImageView total_chunqs, ImageView total_exchanges, String USERID, TextView coinsOnHand, TextView lucksOnHand, TextView vouchersOnHand, TextView walletMoney, TextView total_Luck_Win, TextView shared_Coins,
                               TextView shared_Luck, TextView total_Chunqz, TextView total_Exchange, TextView userName, TextView userID, TextView userEmail, TextView userPhone, TextView aboutMe,
                               ImageView prof_Pic, Dialog dialog)
    {

        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<LB_Prof_Data> call3 = apiInterface.Fetch_Profile_user(auth_token1,USERID);
        call3.enqueue(new Callback<LB_Prof_Data>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<LB_Prof_Data> call, retrofit2.Response<LB_Prof_Data> response) {

                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        LB_Prof_Response lb_prof_response=response.body().getResponse();
                        List<LB_Prof_UserProfile>view_profile=lb_prof_response.getUserProfile();
                        if (view_profile.size()>0){
                            for (int i=0;i<view_profile.size();i++){

                                LB_Prof_UserProfile userProfile=view_profile.get(i);
                                userName.setText(userProfile.getName());
                                userID.setText(userProfile.getId());
                                userEmail.setText(userProfile.getEmail());
                                userPhone.setText(userProfile.getMobile());
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
//                                ONOFF.setImageResource(R.drawable.off);

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
                                    Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + userProfile.getPhotoUrl()).into(prof_Pic);
                                }
                                coinReq.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                                        mp.start();
                                        dialog.dismiss();
                                        Coin_Request(Us_ID, userProfile.getId(), userProfile.getCoinInHand());

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
        Call<Req_Sent> call3 = apiInterface.SendRequest(auth_token1,sessionID);
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

        dialogReq = new Dialog(activity, android.R.style.Theme_Dialog);
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
                        Call<SendRequest> call3 = apiInterface.SendCoinRequest(auth_token1,sessionID,s, String.valueOf(ReqCoin2));
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
            Dialog dialog=new Dialog(activity, android.R.style.Theme_Dialog);
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
            Zoomy.Builder builder = new Zoomy.Builder(activity)
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
            Dialog dialog=new Dialog(activity, android.R.style.Theme_Dialog);
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
            Zoomy.Builder builder = new Zoomy.Builder(activity)
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
