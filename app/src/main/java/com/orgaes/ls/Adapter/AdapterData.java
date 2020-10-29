package com.orgaes.ls.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
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
import com.orgaes.ls.Activity_HomePage;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckData_Model;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckData_Response;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckDetails_Model;
import com.orgaes.ls.RETROFIT_NEW.LuckWinners.TodaysLuck_Data;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.MyViewHolder> {



    Context context;
    Activity_HomePage activity_homePage1;
    Dialog dialog;
    Runnable runnableluck;
    Handler handlerluck;
    int clicked=0;
    String LuckClicked="1";
    ProgressBar progressBar;

    List<TodaysLuck_Data> todaysLuck_homes=new ArrayList<>();

    public AdapterData(ProgressBar porgressbar, List<TodaysLuck_Data> todaysLuck_data, Activity_HomePage applicationContext, Context applicationContext1) {

        this.context = applicationContext1;
        this.activity_homePage1 = applicationContext;
        this.todaysLuck_homes = todaysLuck_data;
        this.progressBar=porgressbar;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView UserImage;
        ConstraintLayout mainitemlayout;

        public MyViewHolder(View view) {
            super(view);
            UserImage = (ImageView) view.findViewById(R.id.userimage);
            mainitemlayout = (ConstraintLayout) view.findViewById(R.id.mainitemlayout);

        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

            Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(position).getImage()).into(holder.UserImage);
            holder.mainitemlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LuckClicked="1";
                    activity_homePage1.onClickCalled(LuckClicked);

                        if (clicked==0) {

                            viewLuckdata(todaysLuck_homes.get(position).getId(),todaysLuck_homes.get(position).getType());
                        }
                }
            });
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
                        popupLuckData(luckDetails_models);
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

    @Override
    public int getItemCount() {
        return todaysLuck_homes.size();
    }

    private void popupLuckData(List<LuckDetails_Model> todaysLuck_homes) {

        if (clicked==0){
            clicked=1;
            dialog = new Dialog(activity_homePage1, android.R.style.Theme_Dialog);
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
            Share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicked=0;
                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "LS (LUCKY SCAN)");
                        String shareMessage= "\nLet me recommend you this application  \nLS (LUCKY SCAN)\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.orgaes.ls"+"   \n"+Details.getText().toString()+" \n"+SponsorName.getText().toString()+"  \n"+"Luck Ends On "+EndingDate.getText().toString();
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        activity_homePage1.startActivity(Intent.createChooser(shareIntent, "choose one"));
                    } catch(Exception e) {
                        //e.toString();
                    }

                }
            });
            System.out.println("LS LUCK DATE  "+todaysLuck_homes.get(0).getRedeemDate());
            //Timer functions
            handlerluck = new Handler();
            runnableluck = new Runnable() {
                @Override
                public void run() {
                    handlerluck.postDelayed(this, 1000);
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "dd-MM-yyyy hh:mm:ss");
                        // Please here set your event date//YYYY-MM-DD
                        Date futureDate = dateFormat.parse(todaysLuck_homes.get(0).getEndDate());
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
                    clicked=0;

                    LuckClicked="0";
                    activity_homePage1.onClickCalled(LuckClicked);

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


    }

}