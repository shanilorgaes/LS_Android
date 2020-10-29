package com.orgaes.ls;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.RETROFIT_NEW.CheckExtend.CheckExtendModel;
import com.orgaes.ls.RETROFIT_NEW.CheckExtend.Extend_Response;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckData_Model;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckData_Response;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckDetails_Model;
import com.orgaes.ls.RETROFIT_NEW.Redeem.RedeemData;
import com.orgaes.ls.RETROFIT_NEW.Redeem.Redeem_Response;
import com.orgaes.ls.RETROFIT_NEW.UserWinner.DrawWinner_Model;
import com.orgaes.ls.RETROFIT_NEW.UserWinner.DrawWinner_Response;
import com.orgaes.ls.RETROFIT_NEW.View_LUCK_EXCHANGE_DATA.Luck_Ext_Response;
import com.orgaes.ls.RETROFIT_NEW.View_LUCK_EXCHANGE_DATA.View_LuckDetail;
import com.orgaes.ls.RETROFIT_NEW.View_LUCK_EXCHANGE_DATA.View_Luck_Ex_DATA;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Luck_Redeem extends AppCompatActivity {

    private ImageView qrImage;
    private ImageView itemimage;
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    private Activity activity;
    ImageView generate_barcode;
    ImageView status;
    ProgressBar progressbar;
    String Type,ID;
    String userID,ClientID="0",ClaimID, Item_ID,Date;
    Call<RedeemData> call3;
    Handler handlerluck;
    Runnable runnableluck;
    ImageView MenuIcon;
    TextView Days;
    TextView Hours;
    TextView Minutes;
    TextView Seconds;
    String itemname;
    String UserID;
    String ItemPic;
    String collectioncenter;
    String EditionName;
    TextView editionname;
    TextView DateID;
    TextView Timer;
    TextView Header;
    TextView TimeID;
    TextView edt_Tetx;
    Button btn_More;
    Button btn_extend;
    String auth_token;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.draw_redeem);
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        auth_token= prefsnew.getString("auth_token", "");

        qrImage = findViewById(R.id.itemimage);
        itemimage = findViewById(R.id.redeemqr);
        progressbar = findViewById(R.id.progressbar);
        generate_barcode = findViewById(R.id.generate_barcode);
        status = findViewById(R.id.status);
        editionname = findViewById(R.id.editionname);
        edt_Tetx = findViewById(R.id.textView7);
        DateID = findViewById(R.id.date_id);
        Timer = findViewById(R.id.timer);
        Header = findViewById(R.id.header_txt);
        TimeID = findViewById(R.id.timeid);
        btn_More = findViewById(R.id.btn_more);
        btn_extend = findViewById(R.id.btn_extend);
        MenuIcon = findViewById(R.id.menu_icon);
        Days=findViewById(R.id.txtDay);
        Hours=findViewById(R.id.txtHour);
        Minutes=findViewById(R.id.txtMinute);
        Seconds=findViewById(R.id.txtSecond);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Luck_Redeem.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Luck_Redeem.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_Luck_Redeem.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Luck_Redeem.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();

        activity = this;
        MenuIcon.setOnClickListener(v -> {
            Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);
            finish();
        });
        Bundle bn=getIntent().getExtras();
        assert bn != null;
        Type=bn.getString("Type");
        ID=bn.getString("ID");
        Item_ID =bn.getString("ItemID");
        Date = bn.getString("Date");
        ItemPic = bn.getString("Image");
        EditionName = bn.getString("Edition");
        progressbar.setVisibility(View.GONE);
        Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS + ItemPic).into(itemimage);
        editionname.setText(EditionName);
        generate_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar.setVisibility(View.VISIBLE);
                String inputPattern = "dd-MM-yyyy hh:mm:ss";
                String outputPattern = "yyyy-MM-dd HH:mm:ss";
                @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

                java.util.Date date;

                try {
                    date = inputFormat.parse(Date);
                    assert date != null;

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Date d = new Date();
                CharSequence s  = DateFormat.format("yyyy-MM-dd HH:mm:ss", d.getTime());

                Redeem();
            }
        });
        String inputPattern = "dd-MM-yyyy hh:mm:ss";
        String outputPattern = "dd-MM-yyyy";
        String outputPattern2 = "HH:mm:ss";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat2 = new SimpleDateFormat(outputPattern2);

        Date date;
        String str;
        String str2;
        try {
            date = inputFormat.parse(Date);
            str = outputFormat.format(date);
            str2 = outputFormat2.format(date);
            DateID.setText(str);
            TimeID.setText(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        handlerluck = new Handler();
        runnableluck = new Runnable() {
            @Override
            public void run() {
                handlerluck.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "dd-MM-yyyy hh:mm:ss");
                    // Please here set your event date//YYYY-MM-DD
//                    Expired.setVisibility(View.GONE);
                    java.util.Date futureDate = dateFormat.parse(Date);
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
                        Days.setText("" + String.format("%02d", days));
                        Hours.setText("" + String.format("%02d", hours));
                        Minutes.setText(""
                                + String.format("%02d", minutes));
                        Seconds.setText(""
                                + String.format("%02d", seconds));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handlerluck.postDelayed(runnableluck, 1000);
        btn_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Type.equals("luck_radar")||Type.equals("exchange_item")){
                    viewLuckdata2(Item_ID,Type);
                }else {
                    viewLuckdata(Item_ID,Type);
                }

            }
        });
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        if (Type.equals("luck")){
            Header.setText(getString(R.string.luck_redeem));
        }else if (Type.equals("luck_radar")){
            Header.setText(getString(R.string.luck_radar_redeem));
        }else if (Type.equals("exchange_item")){
            Header.setText(getString(R.string.exchange_item_redeem));
        }else if (Type.equals("draw")){
            Header.setText(getString(R.string.draw_redeem));
        }

        editionname.setTypeface(avner);
        edt_Tetx.setTypeface(dosis);
        DateID.setTypeface(avner);
        TimeID.setTypeface(avner);
        Timer.setTypeface(avner);
        Header.setTypeface(avner);
        btn_More.setTypeface(dosis);
        btn_extend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check_Extend();

            }
        });
    }

    private void viewLuckdata2(String id, String type) {

        progressbar.setVisibility(View.VISIBLE);
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<View_Luck_Ex_DATA> call3 = apiInterface.Api_getLuckDATA("http://ec2-13-232-195-100.ap-south-1.compute.amazonaws.com/ls_dev/userapi/itemdetail/"+id,auth_token);
        call3.enqueue(new Callback<View_Luck_Ex_DATA>() {
            @Override
            public void onResponse(@NotNull Call<View_Luck_Ex_DATA> call, @NotNull Response<View_Luck_Ex_DATA> response) {
                try {
                    progressbar.setVisibility(View.GONE);
                    assert response.body() != null;
                    int Code =response.body().getCode();
                    System.out.println("LS LUCK DATA ::  "+Code+"   "+id+"   "+type);
                    if (Code==200){

                        Luck_Ext_Response response1=response.body().getResponse();
                        List<View_LuckDetail> luckDetails_models=response1.getLuckDetails();
                        popupLuckData2(luckDetails_models,type);
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
            public void onFailure(Call<View_Luck_Ex_DATA> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                System.out.println("LS LUCK DATA3 ::  "+t.getMessage());
                progressbar.setVisibility(View.GONE);
            }
        });

    }

    private void check_Extend() {

        progressbar.setVisibility(View.VISIBLE);
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<CheckExtendModel> call=apiInterface.check_Extend(auth_token,ID);
        call.enqueue(new Callback<CheckExtendModel>() {
            @Override
            public void onResponse(Call<CheckExtendModel> call, Response<CheckExtendModel> response) {
                progressbar.setVisibility(View.GONE);
                try {

                    int Code =response.body().getCode();
                    System.out.println("LS DIPLUCK  "+Code);
                    if (Code==200){
                        Extend_Response dipLuck_response=response.body().getResponse();
                        Intent in = new Intent(getApplicationContext(), Activity_Extend.class);
                        Bundle bn=new Bundle();
                        bn.putString("id",ID);
                        bn.putString("image",ItemPic);
                        bn.putString("EditionName",EditionName);
                        bn.putString("Date",Date);
                        in.putExtras(bn);
                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(in);

                    }else if (Code==203){

                        Toast.makeText(Activity_Luck_Redeem.this, "No option for EXTEND", Toast.LENGTH_SHORT).show();

                    }else if (Code==201){

                        Toast.makeText(Activity_Luck_Redeem.this, "Sorry Your Chance is Over", Toast.LENGTH_SHORT).show();

                    }


                }catch (Exception e){

                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<CheckExtendModel> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                progressbar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("LS", MODE_PRIVATE);
        UserID = prefs.getString("UserID", "");
        ImageView MenuIcon=findViewById(R.id.menu_icon);

        MenuIcon.setOnClickListener(v -> {
            Intent in=new Intent(getApplicationContext(), Activity_Menu_Page.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            //overridePendingTransition(R.anim.left_right, R.anim.right_left);
        });
    }

    private void Redeem() {
        if (Type.equals("exchange_item")){
            Type="exchangeitem";
        }if (Type.equals("luck_radar")){
            Type="luckradar";
        }
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        System.out.println("LS REDEEM 111 "+auth_token+"    "+ID+"    "+ Item_ID +"    "+Type);
        call3 = apiInterface.Api_draw_Redeeem(auth_token,ID, Item_ID);
        call3.enqueue(new Callback<RedeemData>() {
            @Override
            public void onResponse(@NotNull Call<RedeemData> call, @NotNull Response<RedeemData> response) {
                progressbar.setVisibility(View.GONE);
                try {
                    generate_barcode.setEnabled(false);
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){

                        Redeem_Response box_response=response.body().getResponse();
                        String QRGEN=box_response.getQrCode();
                        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                        editor.putString("QRGEN", QRGEN);
                        editor.apply();
                        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                        Display display = manager.getDefaultDisplay();
                        Point point = new Point();
                        display.getSize(point);
                        int width = point.x;
                        int height = point.y;
                        int smallerDimension = Math.min(width, height);
                        smallerDimension = smallerDimension * 3 / 4;

                        qrgEncoder = new QRGEncoder(
                                QRGEN, null,
                                QRGContents.Type.TEXT,
                                smallerDimension);
                        qrgEncoder.setColorBlack(Color.BLACK);
                        qrgEncoder.setColorWhite(Color.WHITE);
                        try {
                            bitmap = qrgEncoder.getBitmap();
                            qrImage.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast toast = Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
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
                }catch (Exception e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<RedeemData> call, @NotNull Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                progressbar.setVisibility(View.GONE);
            }

        });
    }

    private void viewLuckdata(String id, String type) {
        progressbar.setVisibility(View.VISIBLE);
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<LuckData_Model> call3 = apiInterface.Api_GetLuck("http://ec2-13-232-195-100.ap-south-1.compute.amazonaws.com/ls_dev/userapi/luckdetail/"+id+"/"+type);
        call3.enqueue(new Callback<LuckData_Model>() {
            @Override
            public void onResponse(@NotNull Call<LuckData_Model> call, @NotNull Response<LuckData_Model> response) {
                try {
                    progressbar.setVisibility(View.GONE);
                    assert response.body() != null;
                    int Code =response.body().getCode();
                    System.out.println("LS LUCK DATA ::  "+Code+"   "+id+"   "+type);
                    if (Code==200){

                        LuckData_Response response1=response.body().getResponse();
                        List<LuckDetails_Model> luckDetails_models=response1.getLuckDetails();
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
                progressbar.setVisibility(View.GONE);
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void popupLuckData(List<LuckDetails_Model> todaysLuck_homes, String type) {

        dialog = new Dialog(Activity_Luck_Redeem.this, android.R.style.Theme_Dialog);
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
        Duration.setText(""+todaysLuck_homes.get(0).getDuration()+" Sec");
        ReleasingEditions.setText(""+todaysLuck_homes.get(0).getEdition());
        EndingDate.setText(""+todaysLuck_homes.get(0).getEndDate());
        TxtRedeemdate.setText(""+todaysLuck_homes.get(0).getRedeemDate());
        TxtCollectingDate.setText(""+todaysLuck_homes.get(0).getCollectionCenter());

        Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(0).getImage()).into(LuckImage);
        Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(0).getClientUserImage()).into(SponsorImage);

        Share.setOnClickListener(v -> {
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

        });
        System.out.println("LS LUCK DATE  "+todaysLuck_homes.get(0).getEndDate());
        //Timer functions
        handlerluck = new Handler();
        runnableluck = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                handlerluck.postDelayed(this, 1000);
                try {
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(
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

    @SuppressLint("SetTextI18n")
    private void popupLuckData2(List<View_LuckDetail> todaysLuck_homes, String type) {

        dialog = new Dialog(Activity_Luck_Redeem.this, android.R.style.Theme_Dialog);
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
        System.out.println("LS TODAYS LUCK DATAS  4  "+todaysLuck_homes.get(0).getDescription());
        System.out.println("LS TODAYS LUCK DATAS  6  "+todaysLuck_homes.get(0).getEdition());
        System.out.println("LS TODAYS LUCK DATAS  6  "+todaysLuck_homes.get(0).getEndDate());
        System.out.println("LS TODAYS LUCK DATAS  6  "+todaysLuck_homes.get(0).getCollectionCenter());


        SponsorName.setText(""+todaysLuck_homes.get(0).getClientUserFname()+" "+todaysLuck_homes.get(0).getClientUserLname());
        TotalLuck.setText(""+todaysLuck_homes.get(0).getTotalCount());
        CoinSupply.setText(todaysLuck_homes.get(0).getUnitCoinCost());
        Details.setText(""+todaysLuck_homes.get(0).getDescription());
//        Type.setText(todaysLuck_homes.get(0).getAdMedia());
//        Duration.setText(""+todaysLuck_homes.get(0).getDuration()+" Sec");
        ReleasingEditions.setText(""+todaysLuck_homes.get(0).getEdition());
        EndingDate.setText(""+todaysLuck_homes.get(0).getEndDate());
//        TxtRedeemdate.setText(""+todaysLuck_homes.get(0).getRedeemDate());
        TxtCollectingDate.setText(""+todaysLuck_homes.get(0).getCollectionCenter());

        Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(0).getImage()).into(LuckImage);
        Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS + todaysLuck_homes.get(0).getClientUserImage()).into(SponsorImage);

        Share.setOnClickListener(v -> {
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

        });
        System.out.println("LS LUCK DATE  "+todaysLuck_homes.get(0).getEndDate());
        //Timer functions
        handlerluck = new Handler();
        runnableluck = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                handlerluck.postDelayed(this, 1000);
                try {
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(
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

}
