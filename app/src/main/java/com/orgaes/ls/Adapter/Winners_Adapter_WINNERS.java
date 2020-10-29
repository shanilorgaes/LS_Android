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
import android.os.Handler;
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
import com.orgaes.ls.Activity_LuckWinners;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.DrawWinners.Draw_Winners_Winner;
import com.orgaes.ls.RETROFIT_NEW.FrndReq_Sent.Req_Sent;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile.LB_Prof_Data;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile.LB_Prof_Response;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile.LB_Prof_UserProfile;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckData_Model;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckData_Response;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckDetails_Model;
import com.orgaes.ls.RETROFIT_NEW.SendRequest.SendRequest;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Winners_Adapter_WINNERS extends RecyclerView.Adapter<Winners_Adapter_WINNERS.MyViewHolder> {
    Dialog dialog;
    Runnable runnableluck;
    Handler handlerluck;
    Context context;
    Activity_LuckWinners activity_luckLoser;
    String auth_token;
    ProgressBar progressBar;
    Dialog dialogReq;
    int clicked1=0;
    int clicked2=0;
    int clicked3=0;
    List<Draw_Winners_Winner> draw_winners_winners;
    public Winners_Adapter_WINNERS(ProgressBar progressBar, String auth_token, List<Draw_Winners_Winner> draw_winners_winners, Context applicationContext, Activity_LuckWinners activity_luckLoser) {
        this.progressBar=progressBar;
        this.auth_token=auth_token;
        this.draw_winners_winners=draw_winners_winners;
        this.context=applicationContext;
        this.activity_luckLoser=activity_luckLoser;
    }

    @NonNull
    @Override
    public Winners_Adapter_WINNERS.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.winner_sub_layout, parent, false);

        return new Winners_Adapter_WINNERS.MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Winners_Adapter_WINNERS.MyViewHolder holder, int position) {
        Typeface avner = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                "Dosis-Medium.ttf");

        holder.date_time.setText(draw_winners_winners.get(position).getDrawDate()+" "+draw_winners_winners.get(position).getDrawTime());
        holder.coinsspent.setText(draw_winners_winners.get(position).getEnrollFee());
        holder.username.setText(draw_winners_winners.get(position).getName());
        Glide.with(context).load(AppConfig.URL_BASE_VIDEOS+draw_winners_winners.get(position).getPhotoUrl()).into(holder.userpic);
        Glide.with(context).load(AppConfig.URL_BASE_VIDEOS+draw_winners_winners.get(position).getImage()).into(holder.luckPic);

        if (draw_winners_winners.get(position).getType().equals("HOURLY")){
            holder.luckType.setImageResource(R.drawable.hourley_luck_logo);
        }else if (draw_winners_winners.get(position).getType().equals("DAILY")){
            holder.luckType.setImageResource(R.drawable.daily_luck_logo);
        }else if (draw_winners_winners.get(position).getType().equals("WEEKLY")){
            holder.luckType.setImageResource(R.drawable.weekly_luck_logo);
        }else if (draw_winners_winners.get(position).getType().equals("MONTHLY")){
            holder.luckType.setImageResource(R.drawable.monthly_luck_logo);
        }/*else if (draw_winners_winners.get(position).getType().equals("YEARLY")){
            holder.luckType.setImageResource(R.drawable.hourley_luck_logo);
        }else if (draw_winners_winners.get(position).getType().equals("MEGA")){

        }*/

        holder.luckPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();

                viewLuckdata(draw_winners_winners.get(position).getId(),"draw");
            }
        });
        holder.userpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();

                ProfilePopup(draw_winners_winners.get(position).getUser_id());
            }
        });

    }
    @Override
    public int getItemCount() {
        return draw_winners_winners.size();
    }

    private void viewLuckdata(String id, String type) {
        progressBar.setVisibility(View.VISIBLE);
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<LuckData_Model> call3 = apiInterface.Api_GetLuck("http://ec2-13-232-195-100.ap-south-1.compute.amazonaws.com/ls_dev/userapi/luckdetail/"+id+"/"+type);
        call3.enqueue(new Callback<LuckData_Model>() {
            @Override
            public void onResponse(@NotNull Call<LuckData_Model> call, @NotNull Response<LuckData_Model> response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    assert response.body() != null;
                    int Code =response.body().getCode();
                    System.out.println("LS LUCK DATA ::  "+Code+"   "+id+"   "+type);
                    if (Code==200){

                        LuckData_Response response1=response.body().getResponse();
                        List<LuckDetails_Model>luckDetails_models=response1.getLuckDetails();
                        popupLuckData(luckDetails_models,type);
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
            public void onFailure(Call<LuckData_Model> call, Throwable t) {
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
    private void popupLuckData(List<LuckDetails_Model> todaysLuck_homes, String type) {

            dialog = new Dialog(activity_luckLoser, android.R.style.Theme_Dialog);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.luck_popup);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                    "Dosis-Medium.ttf");
            Typeface avner = Typeface.createFromAsset(context.getAssets(),
                    "AvenirLTStd-Light.otf");
            ImageView LuckImage=dialog.findViewById(R.id.profilepic);
            ImageView CloseImage=dialog.findViewById(R.id.imageclose);
            ImageView Share=dialog.findViewById(R.id.share);
            ImageView SponsorImage=dialog.findViewById(R.id.sponsorimage);
            TextView SponsorName=dialog.findViewById(R.id.coincount);
            TextView TotalLuck=dialog.findViewById(R.id.activeluck);
            TextView CoinSupply=dialog.findViewById(R.id.coins_supply);
            TextView Details=dialog.findViewById(R.id.details);
            TextView Type=dialog.findViewById(R.id.type_of_ad);
            TextView Duration=dialog.findViewById(R.id.duration);
            TextView ReleasingEditions=dialog.findViewById(R.id.editions);
            TextView EndingDate=dialog.findViewById(R.id.ending_date);
            TextView TXTday=dialog.findViewById(R.id.txtDay);
            TextView TXThour=dialog.findViewById(R.id.txtHour);
            TextView TXTmin=dialog.findViewById(R.id.txtMinute);
            TextView TXTSec=dialog.findViewById(R.id.txtSecond);
            TextView TxtRedeemdate=dialog.findViewById(R.id.redeemdate);
            TextView TxtCollectingDate=dialog.findViewById(R.id.collection);
        ConstraintLayout constraintLayoutmain=dialog.findViewById(R.id.main_lay);
        Zoomy.Builder builder = new Zoomy.Builder(activity_luckLoser)
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
            TextView spnsorname=dialog.findViewById(R.id.textView9);
            TextView thisluck=dialog.findViewById(R.id.textView20);
            TextView textView=dialog.findViewById(R.id.textView);
            TextView textView10=dialog.findViewById(R.id.textView10);
            TextView textView11=dialog.findViewById(R.id.textView11);
            TextView textView12=dialog.findViewById(R.id.textView12);
            TextView textView13=dialog.findViewById(R.id.textView13);
            TextView textView14=dialog.findViewById(R.id.textView14);
            TextView textView15=dialog.findViewById(R.id.textView15);
            TextView textView16=dialog.findViewById(R.id.textView16);
            TextView textView17=dialog.findViewById(R.id.textView17);
            TextView textView18=dialog.findViewById(R.id.textView18);

            spnsorname.setTypeface(avner);
            thisluck.setTypeface(avner);
            textView.setTypeface(avner);
            textView10.setTypeface(avner);
            textView11.setTypeface(avner);
            textView12.setTypeface(avner);
            textView13.setTypeface(avner);
            textView14.setTypeface(avner);
            textView15.setTypeface(avner);
            textView16.setTypeface(avner);
            textView17.setTypeface(avner);
            textView18.setTypeface(avner);
            TotalLuck.setTypeface(avner);
            CoinSupply.setTypeface(avner);
            Details.setTypeface(avner);
            Type.setTypeface(avner);
            Duration.setTypeface(avner);
            ReleasingEditions.setTypeface(avner);
            EndingDate.setTypeface(avner);
            TxtRedeemdate.setTypeface(avner);
            TxtCollectingDate.setTypeface(avner);
            System.out.println("LS TODAYS LUCK DATAS  1  "+todaysLuck_homes.get(0).getClientUserFname()+" "+todaysLuck_homes.get(0).getClientUserLname());
            System.out.println("LS TODAYS LUCK DATAS  2  "+todaysLuck_homes.get(0).getTotalCount());
            System.out.println("LS TODAYS LUCK DATAS  3  "+todaysLuck_homes.get(0).getCoinSupply());
            System.out.println("LS TODAYS LUCK DATAS  4  "+todaysLuck_homes.get(0).getDescription());
            System.out.println("LS TODAYS LUCK DATAS  5  "+todaysLuck_homes.get(0).getAdMedia());
            System.out.println("LS TODAYS LUCK DATAS  6  "+todaysLuck_homes.get(0).getDuration());
            System.out.println("LS TODAYS LUCK DATAS  6  "+todaysLuck_homes.get(0).getEdition());
            System.out.println("LS TODAYS LUCK DATAS  6  "+todaysLuck_homes.get(0).getEndDate());
            System.out.println("LS TODAYS LUCK DATAS  6  "+todaysLuck_homes.get(0).getCollectionCenter());


            SponsorName.setText(""+todaysLuck_homes.get(0).getClientUserFname()+" "+todaysLuck_homes.get(0).getClientUserLname());
            TotalLuck.setText(""+todaysLuck_homes.get(0).getTotalCount());
            CoinSupply.setText(todaysLuck_homes.get(0).getCoinSupply());
            Details.setText(""+todaysLuck_homes.get(0).getDescription());
            Type.setText(todaysLuck_homes.get(0).getAdMedia());
            Duration.setText(""+todaysLuck_homes.get(0).getDuration()+" Sec");
            ReleasingEditions.setText(""+todaysLuck_homes.get(0).getEdition());
            EndingDate.setText(""+todaysLuck_homes.get(0).getEndDate());
            TxtRedeemdate.setText(""+todaysLuck_homes.get(0).getRedeemDate());
            TxtCollectingDate.setText(""+todaysLuck_homes.get(0).getCollectionCenter());

            Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(0).getImage()).into(LuckImage);
            Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(0).getClientUserImage()).into(SponsorImage);

            Share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                    mp.start();

                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "LS (LUCKY SCAN)");
                        String shareMessage= "\nLet me recommend you this application  \nLS (LUCKY SCAN)\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.orgaes.ls"+"   \n"+Details.getText().toString()+" \n"+SponsorName.getText().toString()+"  \n"+"Luck Ends On "+EndingDate.getText().toString();
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        activity_luckLoser.startActivity(Intent.createChooser(shareIntent, "choose one"));
                    } catch(Exception e) {
                        //e.toString();
                    }

                }
            });
            System.out.println("LS LUCK DATE  "+todaysLuck_homes.get(0).getRedeemDate());
            //Timer functions
            handlerluck = new Handler();
            runnableluck = new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    handlerluck.postDelayed(this, 1000);
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "dd-MM-yyyy hh:mm:ss");
                        // Please here set your event date//YYYY-MM-DD
                        Date futureDate = dateFormat.parse(todaysLuck_homes.get(0).getRedeemDate());
                        Date currentDate = new Date();
                        if (!currentDate.after(futureDate)) {
                            long diff = futureDate.getTime()
                                    - currentDate.getTime();
                            long days = diff / (24 * 60 * 60 * 1000);
                            diff -= days * (24 * 60 * 60 * 1000);
                            long hours = diff / (60 * 60 * 1000);
                            diff -= hours * (60 * 60 * 1000);
                            long minutes = diff / (60 * 1000);
                            diff -= minutes * (60 * 1000);
                            long seconds = diff / 1000;
                            TXTday.setText("" + String.format("%02d", days));
                            TXThour.setText("" + String.format("%02d", hours));
                            TXTmin.setText(""
                                    + String.format("%02d", minutes));
                            TXTSec.setText(""
                                    + String.format("%02d", seconds));
                        } else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            handlerluck.postDelayed(runnableluck, 1000);

            CloseImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                    mp.start();
                    dialog.dismiss();

                }
            });

            wlp.gravity = Gravity.BOTTOM;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
            window.setAttributes(wlp);

            try {
                dialog.getWindow().getAttributes().windowAnimations = R.style.MyAlertDialogStyle;
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void ProfilePopup(String USERID) {
        if (clicked1==0){
            clicked1=1;
            dialog = new Dialog(activity_luckLoser, android.R.style.Theme_Dialog);
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
            Zoomy.Builder builder = new Zoomy.Builder(activity_luckLoser)
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
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();

                System.out.println("LS CHUNQ REQ SEND   ");

                clicked1=0;
                Send_Request(USERID,progressbar);



            });

            Profile_Popup("other_User",ONOFF,CoinReq,coinshand,activelucks,vouchersonhand,walletmoney,total_luck_win,total_share_coin,total_share_luck,total_chunqs,total_exchanges,USERID,
                    CoinsOnHand,LucksOnHand,VouchersOnHand,WalletMoney,Total_Luck_Win,Shared_Coins,Shared_Luck,Total_Chunqz,Total_Exchange,UserName,UserID,UserEmail,UserPhone,AboutMe,Prof_Pic,dialog);

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
                               ImageView prof_Pic, Dialog dialog) {
        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<LB_Prof_Data> call3 = apiInterface.Fetch_LB_Profile_user(auth_token,USERID);
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
                                    Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + userProfile.getPhotoUrl()).into(prof_Pic);
                                }
                                coinReq.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                                        mp.start();
                                        Coin_Request(USERID, userProfile.getId(), userProfile.getCoinInHand());

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
        Call<Req_Sent> call3 = apiInterface.SendRequest(auth_token,sessionID);
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

        dialogReq = new Dialog(activity_luckLoser, android.R.style.Theme_Dialog);
        dialogReq.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogReq.setContentView(R.layout.coin_req_page);
        dialogReq.setCanceledOnTouchOutside(false);
        dialogReq.setCancelable(false);
        dialogReq.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialogReq.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogReq.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialogReq.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        ConstraintLayout constraintLayoutmain=dialog.findViewById(R.id.main_lay);
        Zoomy.Builder builder = new Zoomy.Builder(activity_luckLoser)
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
                        Call<SendRequest> call3 = apiInterface.SendCoinRequest(auth_token,sessionID,s, String.valueOf(ReqCoin2));
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
            Dialog dialog=new Dialog(activity_luckLoser, android.R.style.Theme_Dialog);
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
            Zoomy.Builder builder = new Zoomy.Builder(activity_luckLoser)
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
            Dialog dialog=new Dialog(activity_luckLoser, android.R.style.Theme_Dialog);
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
            Zoomy.Builder builder = new Zoomy.Builder(activity_luckLoser)
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



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView coinsspent;
        TextView date_time;
        ImageView luckType;
        ImageView userpic;
        ImageView luckPic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            username=itemView.findViewById(R.id.username);
            date_time=itemView.findViewById(R.id.datetime);
            coinsspent=itemView.findViewById(R.id.coinspent);
            userpic=itemView.findViewById(R.id.userpic);
            luckPic=itemView.findViewById(R.id.luck_pic);
            luckType=itemView.findViewById(R.id.type_logo);

        }
    }
}
