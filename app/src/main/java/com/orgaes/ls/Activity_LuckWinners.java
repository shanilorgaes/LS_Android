package com.orgaes.ls;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;
import com.orgaes.ls.Adapter.Winners_Adapter_WINNERS;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.RETROFIT_NEW.ClaimDip.ClaimDip_Module;
import com.orgaes.ls.RETROFIT_NEW.ClaimDip.Claim_Dip_Response;
import com.orgaes.ls.RETROFIT_NEW.DrawWinners.DrawWinners_Model;
import com.orgaes.ls.RETROFIT_NEW.DrawWinners.Draw_Winners_Response;
import com.orgaes.ls.RETROFIT_NEW.DrawWinners.Draw_Winners_Winner;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckData_Model;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckData_Response;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckDetails_Model;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_LuckWinners extends AppCompatActivity {

    TextView DipHeader;
    TextView dip_luck_header;
    Button Claim_button;
    ImageView luck_box;
    ProgressBar progressBar;
    ImageView luck_image;
    String id,name,desc,rank,image,item_id;

    List<Draw_Winners_Winner> draw_winners_winners;
    Draw_Winners_Winner drawWinnersWinner;
    Winners_Adapter_WINNERS mAdapter;
    RecyclerView recyclerView;
    Spinner filter_spinner;
    List<String>Winner_Filter=new ArrayList<>();
    ConstraintLayout menu_icon;
    Handler handlerluck;
    Runnable runnableluck;
    TextView txtDay,txtHour,txtMinute,txtSecond;
    ConstraintLayout luck_details;
    Dialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_luckwinners);
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        String auth_token= prefsnew.getString("auth_token", "");
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_LuckWinners.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_LuckWinners.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_LuckWinners.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_LuckWinners.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        Bundle bn=getIntent().getExtras();
        id=bn.getString("id");
        item_id=bn.getString("item_id");
        rank=bn.getString("rank");
        desc=bn.getString("desc");
        image=bn.getString("image");
        name=bn.getString("name");
        DipHeader=findViewById(R.id.dip_header);
        Claim_button=findViewById(R.id.claim_button);
        dip_luck_header=findViewById(R.id.dip_luck_header);
        luck_box=findViewById(R.id.luck_box);
        progressBar=findViewById(R.id.progressbar);
        luck_image=findViewById(R.id.luck_image);
        recyclerView=findViewById(R.id.recyclerView);
        menu_icon=findViewById(R.id.menu_icon);
        filter_spinner=findViewById(R.id.filter_spinner);
        txtDay=findViewById(R.id.txtDay);
        txtHour=findViewById(R.id.txtHour);
        txtMinute=findViewById(R.id.txtMinute);
        txtSecond=findViewById(R.id.txtSecond);
        luck_details=findViewById(R.id.luck_details);
        Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS+image).into(luck_image);
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        DipHeader.setTypeface(dosis);
        dip_luck_header.setTypeface(dosis);
        Claim_button.setOnClickListener(v -> {

            LuckClaim(auth_token,item_id);

        });
        luck_box.setOnClickListener(v -> {

            Intent in=new Intent(getApplicationContext(),Activity_LuckWinners.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);

        });

        menu_icon.setOnClickListener(v -> {
            Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });
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

        luck_details.setOnClickListener(v -> {

            viewLuckdata(id,"draw");

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
                        popupLuckData(luckDetails_models,type);
                    }


                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("LS LUCK DATA2 ::  "+e.getMessage());
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<LuckData_Model> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                System.out.println("LS LUCK DATA3 ::  "+t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });


    }

    private void popupLuckData(List<LuckDetails_Model> todaysLuck_homes, String type) {

        
            dialog = new Dialog(Activity_LuckWinners.this, android.R.style.Theme_Dialog);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.luck_popup);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            Typeface dosis = Typeface.createFromAsset(getApplicationContext().getAssets(),
                    "Dosis-Medium.ttf");
            Typeface avner = Typeface.createFromAsset(getApplicationContext().getAssets(),
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
            Duration.setText(""+todaysLuck_homes.get(0).getDuration()+getString(R.string.sec));
            ReleasingEditions.setText(""+todaysLuck_homes.get(0).getEdition());
            EndingDate.setText(""+todaysLuck_homes.get(0).getEndDate());
            TxtRedeemdate.setText(""+todaysLuck_homes.get(0).getRedeemDate());
            TxtCollectingDate.setText(""+todaysLuck_homes.get(0).getCollectionCenter());

            Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(0).getImage()).into(LuckImage);
            Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(0).getClientUserImage()).into(SponsorImage);

            Share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "LS (LUCKY SCAN)");
                        String shareMessage= "\nLet me recommend you this application  \nLS (LUCKY SCAN)\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.orgaes.ls"+"   \n"+Details.getText().toString()+" \n"+SponsorName.getText().toString()+"  \n"+"Luck Ends On "+EndingDate.getText().toString();
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "choose one"));
                    } catch(Exception e) {
                        //e.toString();
                    }

                }
            });
            System.out.println("LS LUCK DATE  "+todaysLuck_homes.get(0).getEndDate());
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
                            mAdapter = new Winners_Adapter_WINNERS(progressBar,auth_token,draw_winners_winners,getApplicationContext(),  Activity_LuckWinners.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(mAdapter);
                        }


                    }else if (Code==201){
                        recyclerView.setVisibility(View.GONE);
                        Toast.makeText(Activity_LuckWinners.this, "No Winners found", Toast.LENGTH_SHORT).show();
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

    private void LuckClaim(String token, String id) {
        progressBar.setVisibility(View.VISIBLE);
        Claim_button.setEnabled(false);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ClaimDip_Module> call3 = apiInterface.Api_DipLuck_Claim(token, id);
        call3.enqueue(new Callback<ClaimDip_Module>() {
            @Override
            public void onResponse(@NotNull Call<ClaimDip_Module> call, @NotNull Response<ClaimDip_Module> response) {
                progressBar.setVisibility(View.GONE);
                Claim_button.setEnabled(false);
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        Claim_Dip_Response claim_response=response.body().getResponse();

                        Toast.makeText(Activity_LuckWinners.this, "Successfully Claimed your Luck", Toast.LENGTH_SHORT).show();
                        handlerluck = new Handler();
                        runnableluck = new Runnable() {
                            @SuppressLint({"SetTextI18n", "DefaultLocale"})
                            @Override
                            public void run() {
                                handlerluck.postDelayed(this, 1000);
                                try {
                                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(
                                            "dd-MM-yyyy hh:mm:ss");
                                    // Please here set your event date//YYYY-MM-DD
                                    Date futureDate = dateFormat.parse(claim_response.getEndDate());
                                    Date currentDate = new Date();
                                    if (!currentDate.after(futureDate)) {
                                        assert futureDate != null;
                                        long diff = futureDate.getTime()
                                                - currentDate.getTime();
                                        long days = diff / (24 * 60 * 60 * 1000);
                                        diff -= days * (24 * 60 * 60 * 1000);
                                        long hours = diff / (60 * 60 * 1000);
                                        diff -= hours * (60 * 60 * 1000);
                                        long minutes = diff / (60 * 1000);
                                        diff -= minutes * (60 * 1000);
                                        long seconds = diff / 1000;
                                        txtDay.setText("" + String.format("%02d", days));
                                        txtHour.setText("" + String.format("%02d", hours));
                                        txtMinute.setText(""+ String.format("%02d", minutes));
                                        txtSecond.setText(""+ String.format("%02d", seconds));
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        handlerluck.postDelayed(runnableluck, 1000);
                    }else if (Code==201){

                        Toast.makeText(getApplicationContext(), "Claiming Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==203){

                        Toast.makeText(getApplicationContext(), "Sorry Stock Is Not Available", Toast.LENGTH_SHORT).show();

                    }else if (Code==205){

                        Toast.makeText(getApplicationContext(), "Insufficient Coin", Toast.LENGTH_SHORT).show();

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
                }catch (Exception e){
                    System.out.println("LS EXCEPTIONS   "+e.getMessage());
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    Claim_button.setEnabled(true);
                }
            }
            @Override
            public void onFailure(@NotNull Call<ClaimDip_Module> call, @NotNull Throwable t) {
                System.out.println("LS EXCEPTIONS 2  "+t.getMessage());
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                progressBar.setVisibility(View.GONE);
                Claim_button.setEnabled(true);
            }
        });
    }

}
