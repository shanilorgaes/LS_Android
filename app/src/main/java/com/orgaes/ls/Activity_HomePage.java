package com.orgaes.ls;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import com.google.firebase.messaging.FirebaseMessaging;
import com.orgaes.ls.Adapter.AdapterData;
import com.orgaes.ls.Adapter.AdapterDataWinners;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.Functions.SpeedyLinearLayoutManager;
import com.orgaes.ls.QRCODE.QRSCANNER;
import com.orgaes.ls.RETROFIT_NEW.ClaimData.ClaimDatAModel;
import com.orgaes.ls.RETROFIT_NEW.ClaimData.Claim_Response;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCountData;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCount_Response;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRate;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRateData;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRate_Response;
import com.orgaes.ls.RETROFIT_NEW.CollectCoin.CollectCoin;
import com.orgaes.ls.RETROFIT_NEW.Home_New.Home_New_Ad;
import com.orgaes.ls.RETROFIT_NEW.Home_New.Home_New_Luck;
import com.orgaes.ls.RETROFIT_NEW.Home_New.Home_New_Response;
import com.orgaes.ls.RETROFIT_NEW.Home_New.Home_New_data;
import com.orgaes.ls.RETROFIT_NEW.InstantLuck_Data.InstantLuckDATA;
import com.orgaes.ls.RETROFIT_NEW.InstantLuck_Data.InstantLuck_Response;
import com.orgaes.ls.RETROFIT_NEW.InstantLuck_Data.Instant_LuckDetail;
import com.orgaes.ls.RETROFIT_NEW.LuckWinners.Luck_Winners_Response;
import com.orgaes.ls.RETROFIT_NEW.LuckWinners.Model_Luck_Winners;
import com.orgaes.ls.RETROFIT_NEW.LuckWinners.TodaysLuck_Data;
import com.orgaes.ls.RETROFIT_NEW.LuckWinners.TodaysWinner_Data;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
 * Purpose – Activity_HomePage is an activty for displaying ads , winners and lucks
 * User will get  his luck using QR code and watching ads
 * User can share app and luck and coupons etc...
 * User will get a message when internet connection is lost
 * @author SHANIL
 * Created on 21-5-2020
 */

public class Activity_HomePage extends AppCompatActivity {

    AdapterDataWinners mAdapterwinners;
    AdapterData mAdapter;
    RecyclerView recyclerView_Winner;
    RecyclerView recyclerView_Luck;
    int stopPosition = 0;
    SeekBar seekBar;
    SeekBar seekbarvideo;
    SeekBar seekbarlink;
    APIInterface apiInterface;
    Call<CollectCoin> call4;

    String Rotated;
    String SessionID;
    String auth_token;
    String SignedIn;
    String USERNAME;
    String USERID;
    String USERIMAGE;
    String QR_SCANNED;
    String VideoID;
    GPSTracker gps;
    ConstraintLayout SkipImage;
    ConstraintLayout mute;
    ConstraintLayout Vol;
    ConstraintLayout PlayPauseLay;
    ConstraintLayout videoviewlay;
    ConstraintLayout MainLayout;
    ConstraintLayout linklayout;
    ConstraintLayout ClickArea;
    ConstraintLayout ClickAreaLeft;
    ConstraintLayout landscapemode;
    ConstraintLayout bg_fullscreen;
    ConstraintLayout bottomlayout;

    Guideline AdsGuideline4;
    Guideline Guideline;
    Guideline Guideline2;
    Guideline Guideline3;
    Guideline Guideline5;
    Guideline Guideline6;
    Guideline Guideline7;
    Guideline Guideline8;
    Guideline Guideline9;
    Guideline Guideline10;
    Guideline Guideline11;
    Guideline Guideline12;
    Guideline Guideline13;
    Guideline Guideline14;
    Guideline Guideline15;
    Guideline Guideline16;
    Guideline Guideline17;
    Guideline Guideline18;

    ImageView PlayPause;
    ImageView Ads_ImageView;
    ImageView MuteButton;
    ImageView VolUpButton;
    ImageView Luckradar;
    ImageView LinkImage;
    ImageView exitfullscreen;
    ImageView FindLuck;
    ConstraintLayout WinnerLeftArrow, LuckRightArrow;
    ConstraintLayout WinnerRightArrow, LuckLeftArrow;
    ImageView scan_luck;
    ImageView Seperator1;
    ImageView Seperator2;
    ImageView Seperator3;
    ImageView Seperator4;
    ImageView Seperator5;
    ImageView Skipimage;
    ImageView TopIcon;
    ImageView exiticon_landscape;

    ConstraintLayout MenuIcon;
    ConstraintLayout TopBar;
    ImageView ShareImage;

    LocationManager locationManager;
    private static final int REQUEST_LOCATION = 1;

    TextView circularcountdown;
    TextView ChangingTxtRotate;
    TextView Coincount;
    TextView Todayswinner;
    TextView Todayluck;

    long vdostartTime=0L;
    double vdocurrent_pos, vdototal_duration;
    Handler handlerluck;
    Runnable runnableluck;

    Dialog dialogSkipPopupVideo;
    Dialog dialogFinishPopupVideo;
    Dialog dialogSuccessPopupVideo;
    ProgressBar progressbar;
    public AVLoadingIndicatorView indicatorView;
    String link_clicked;
    MediaPlayer playersound;
    MediaPlayer playersoundvdo;
    MediaController mediacontroller;
    VideoView videoplayer;
    int playvideo=0;
    int countvideo;

    int countlink;
    int countimage;
    int CurrentCoins;
    int position=0;
    int position2=0;

    FirebaseUser firebaseUser;

    String AdsPlayingType;
    View viewright;
    View viewleft;
    View viewTop;
    View viewMain;
    String Ad_Id,Voice_Url,Ad_Url,Link_url,Duration,Coin_Count,Coin_Time,Luck_Status,Name_Ad,ADTYPE;
    String User_Ad_ID;

    List<String>LuckID=new ArrayList<>();
    List<String>LuckNAME=new ArrayList<>();
    List<String>LuckIMAGE=new ArrayList<>();
    List<String>LuckTIME=new ArrayList<>();
    boolean isViewOn;

    List<String>get_LuckID=new ArrayList<>();
    List<String>get_Luck_Name=new ArrayList<>();
    List<String>get_LuckImage=new ArrayList<>();
    List<String>get_Luck_Claim_Date=new ArrayList<>();
    List<String>get_Luck_Fname=new ArrayList<>();
    List<String>get_Luck_Lname=new ArrayList<>();
    List<String>get_Luck_Desc=new ArrayList<>();
    List<String>get_Luck_Colle_Center=new ArrayList<>();
    String UserClaimedLuckID;
    String Public_AdsID,Public_Format;
    int scrollCount=0;
    boolean isValue=true;
    /*
     * This Function is used for handling the rotation changes when video playing
     * 21-5-2020
     * Edited By SHANIL K A
     * */

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            exitfullscreen.setImageResource(R.drawable.exitfullscreen);
            scan_luck.setVisibility(View.GONE);
            ShareImage.setVisibility(View.GONE);
            FindLuck.setVisibility(View.GONE);
            recyclerView_Winner.setVisibility(View.GONE);
            recyclerView_Luck.setVisibility(View.GONE);
            WinnerLeftArrow.setVisibility(View.GONE);
            LuckRightArrow.setVisibility(View.GONE);
            WinnerRightArrow.setVisibility(View.GONE);
            LuckLeftArrow.setVisibility(View.GONE);
            bottomlayout.setVisibility(View.GONE);
            TopBar.setVisibility(View.GONE);
            landscapemode.setVisibility(View.GONE);
            AdsGuideline4.setGuidelinePercent((float) .99);
            Guideline.setGuidelinePercent((float).99);
            Guideline2.setGuidelinePercent((float).85);
            Guideline3.setGuidelinePercent((float).0);
            Guideline5.setGuidelinePercent((float).15);
            Guideline6.setGuidelinePercent((float).0);
            Guideline7.setGuidelinePercent((float).0);
            Guideline8.setGuidelinePercent((float).0);
            Guideline9.setGuidelinePercent((float).0);
            Guideline10.setGuidelinePercent((float).0);
            Guideline11.setGuidelinePercent((float).0);
            Guideline12.setGuidelinePercent((float).0);
            Guideline13.setGuidelinePercent((float).0);
            Guideline14.setGuidelinePercent((float).0);
            Guideline15.setGuidelinePercent((float).0);
            Guideline16.setGuidelinePercent((float).0);
            Guideline17.setGuidelinePercent((float).0);
            Guideline18.setGuidelinePercent((float).0);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(landscapemode);
            constraintSet.applyTo(landscapemode);
            viewright.setVisibility(View.VISIBLE);
            viewleft.setVisibility(View.VISIBLE);
            MainLayout.setBackgroundColor(Color.BLACK);
            viewTop.setVisibility(View.GONE);
            landscapemode.setVisibility(View.GONE);
            viewMain.setVisibility(View.GONE);
            TopIcon.setVisibility(View.GONE);
            exiticon_landscape.setVisibility(View.VISIBLE);

        } else {
            // In portrait
            landscapemode.setVisibility(View.VISIBLE);
            exitfullscreen.setImageResource(R.drawable.shrink_icon);
            scan_luck.setVisibility(View.VISIBLE);
            FindLuck.setVisibility(View.VISIBLE);
            ShareImage.setVisibility(View.VISIBLE);
            recyclerView_Winner.setVisibility(View.VISIBLE);
            recyclerView_Luck.setVisibility(View.VISIBLE);
            WinnerLeftArrow.setVisibility(View.VISIBLE);
            LuckRightArrow.setVisibility(View.VISIBLE);
            WinnerRightArrow.setVisibility(View.VISIBLE);
            LuckLeftArrow.setVisibility(View.VISIBLE);
            bottomlayout.setVisibility(View.VISIBLE);
            TopBar.setVisibility(View.VISIBLE);
            AdsGuideline4.setGuidelinePercent((float) .78);
            Guideline.setGuidelinePercent((float).50);
            Guideline2.setGuidelinePercent((float).85);
            Guideline3.setGuidelinePercent((float).1);
            Guideline5.setGuidelinePercent((float).15);
            Guideline6.setGuidelinePercent((float).20);
            Guideline7.setGuidelinePercent((float).15);
            Guideline8.setGuidelinePercent((float).85);
            Guideline9.setGuidelinePercent((float).90);
            Guideline10.setGuidelinePercent((float).30);
            Guideline11.setGuidelinePercent((float).70);
            Guideline12.setGuidelinePercent((float).69);
            Guideline13.setGuidelinePercent((float).35);
            Guideline14.setGuidelinePercent((float).65);
            Guideline15.setGuidelinePercent((float).58);
            Guideline16.setGuidelinePercent((float).18);
            Guideline17.setGuidelinePercent((float).85);
            Guideline18.setGuidelinePercent((float).15);
            viewright.setVisibility(View.GONE);
            viewleft.setVisibility(View.GONE);
            exiticon_landscape.setVisibility(View.GONE);
            MainLayout.setBackgroundResource(R.drawable.bg_home);
            viewTop.setVisibility(View.VISIBLE);
            landscapemode.setVisibility(View.VISIBLE);
            viewMain.setVisibility(View.VISIBLE);
            TopIcon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            dialogSkipPopupVideo=new Dialog(new ContextThemeWrapper(this, R.style.DialogSlideAnim));
            dialogFinishPopupVideo=new Dialog(new ContextThemeWrapper(this, R.style.DialogAnimation1));
            dialogSuccessPopupVideo=new Dialog(new ContextThemeWrapper(this, R.style.DialogSlideAnim));
            Objects.requireNonNull(dialogFinishPopupVideo.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
            Objects.requireNonNull(dialogSuccessPopupVideo.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
            Objects.requireNonNull(dialogSkipPopupVideo.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        }catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_new_homepage);
        UserClaimedLuckID="0";
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        SessionID= prefsnew.getString("UserID", "");
        auth_token= prefsnew.getString("auth_token", "");
        SignedIn= prefsnew.getString("SignedIn", "");
        USERID= prefsnew.getString("Userid", "");
        USERIMAGE= prefsnew.getString("Userpic", "");
        USERNAME= prefsnew.getString("Username", "");
        QR_SCANNED= prefsnew.getString("QR_SCANNED", "");
        System.out.println("LS QR SCAN DATA   "+Constants.Const_UserCoins);
        System.out.println("LS NEW API 1   "+QR_SCANNED);

        unmute();
        initialize();
        functions();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        System.out.println("LS NEW REG TOKEN  "+token);

                    }
                });


        try {

            CoinRate(auth_token);
            CoinCount(auth_token);
            ActivityCompat.requestPermissions( this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            assert locationManager != null;
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                turnGPSOn();
            } else {
                getLocation();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void turnGPSOn(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }
    /*
     * Values initialize methode
     * 21-5-2020
     * Edited By SHANIL K A
     * */
    private void initialize() {
        try {
            ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
            Zoomy.Builder builder = new Zoomy.Builder(Activity_HomePage.this)
                    .target(constraintLayoutmain)
                    .interpolator(new OvershootInterpolator())
                    .tapListener(v -> {
                    })
                    .longPressListener(v -> {
                    }).doubleTapListener(v -> {
                    });

            builder.register();
            playersound = new MediaPlayer();
            playersound.setAudioAttributes(
                    new AudioAttributes
                            .Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build());
            playersoundvdo = new MediaPlayer();
            vdostartTime= SystemClock.uptimeMillis();
            indicatorView=findViewById(R.id.avi);
            indicatorView.setVisibility(View.INVISIBLE);
            progressbar = findViewById(R.id.progressbar);
            circularcountdown = findViewById(R.id.circularcountdown);
            Coincount = findViewById(R.id.coincount);
            ChangingTxtRotate = findViewById(R.id.data_changing_time);
            Todayluck = findViewById(R.id.todaysluck);
            Todayswinner = findViewById(R.id.todayswinner);
            seekBar = findViewById(R.id.seekbar1);
            seekbarvideo = findViewById(R.id.seekbarvideo);
            seekbarlink = findViewById(R.id.seekbarlink);
            exitfullscreen = findViewById(R.id.data_fullscreen);
            bg_fullscreen = findViewById(R.id.bg_fullscreen);
            landscapemode = findViewById(R.id.playercontrols);
            bottomlayout = findViewById(R.id.bottomlayout);
            videoviewlay = findViewById(R.id.videoviewlay);
            MainLayout = findViewById(R.id.constraintLayout10);
            TopBar = findViewById(R.id.toolbar2);
            MenuIcon = findViewById(R.id.menu_icon);
            ShareImage = findViewById(R.id.share);
            WinnerLeftArrow = findViewById(R.id.leftarrow_lay);
            WinnerRightArrow = findViewById(R.id.rightarrow_lay);
            LuckLeftArrow = findViewById(R.id.leftarrow1_lay);
            LuckRightArrow = findViewById(R.id.rightarrow2_lay);
            scan_luck = findViewById(R.id.scan_luck);
            Luckradar = findViewById(R.id.luckradar);
            FindLuck = findViewById(R.id.findluck);
            Ads_ImageView = findViewById(R.id.ads_image);
            MuteButton = findViewById(R.id.mute);
            VolUpButton = findViewById(R.id.vol);
            SkipImage = findViewById(R.id.bg_skip);
            mute = findViewById(R.id.mutescreen);
            Vol = findViewById(R.id.volscreen);
            PlayPause = findViewById(R.id.data_pause_image);
            PlayPauseLay = findViewById(R.id.bg_play);
            viewright = findViewById(R.id.view56);
            viewleft = findViewById(R.id.view57);
            Seperator1 = findViewById(R.id.seperatorpause);
            Seperator2 = findViewById(R.id.data_imageView5);
            Seperator3 = findViewById(R.id.data_imageView7);
            Seperator4 = findViewById(R.id.data_imageView10);
            Seperator5 = findViewById(R.id.mutes);
            Skipimage = findViewById(R.id.skipimage);
            TopIcon = findViewById(R.id.top_icon);
            exiticon_landscape = findViewById(R.id.exiticon_landscape);
            viewTop = findViewById(R.id.view8);
            viewMain = findViewById(R.id.view);

            seekBar.setEnabled(false);
            seekbarlink.setEnabled(false);
            seekbarvideo.setEnabled(false);
            seekbarlink.setVisibility(View.GONE);
            seekBar.setVisibility(View.GONE);

            AdsGuideline4 =findViewById(R.id.guideline4);
            Guideline =findViewById(R.id.guideline);
            Guideline2 =findViewById(R.id.guideline2);
            Guideline3 =findViewById(R.id.guideline3);
            Guideline5 =findViewById(R.id.guideline5);
            Guideline6 =findViewById(R.id.guideline6);
            Guideline7 =findViewById(R.id.guideline7);
            Guideline8 =findViewById(R.id.guideline8);
            Guideline9 =findViewById(R.id.guideline9);
            Guideline10 =findViewById(R.id.guideline10);
            Guideline11 =findViewById(R.id.guideline11);
            Guideline12 =findViewById(R.id.guideline12);
            Guideline13 =findViewById(R.id.guideline13);
            Guideline14 =findViewById(R.id.guideline14);
            Guideline15 =findViewById(R.id.guideline15);
            Guideline16 =findViewById(R.id.guideline16);
            Guideline17 =findViewById(R.id.guideline17);
            Guideline18 =findViewById(R.id.guideline18);

            recyclerView_Winner = findViewById(R.id.recyclerView);
            recyclerView_Luck = findViewById(R.id.recyclerViewluck);
            videoplayer = findViewById(R.id.videoView);
            linklayout = findViewById(R.id.linklayout);
            LinkImage = findViewById(R.id.ads_image_link);
            ClickArea = findViewById(R.id.clickarea);
            ClickAreaLeft = findViewById(R.id.clickarealeft);

            circularcountdown.setVisibility(View.GONE);

            Typeface avner = Typeface.createFromAsset(getAssets(),
                    "AvenirLTStd-Light.otf");
            ChangingTxtRotate.setTypeface(avner);
            Coincount.setTypeface(avner);

            Todayluck.setTypeface(avner);
            Todayswinner.setTypeface(avner);

            TopIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Exitall();
                    StopFunction();
                    Intent in=new Intent(getApplicationContext(),Activity_DipLuck.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                }
            });

        }catch (Exception e) {
            e.printStackTrace();
        }
        disable_All();


    }

    public void enable_All(){

        AdsGuideline4.setEnabled(true);
        Guideline.setEnabled(true);
        Guideline2.setEnabled(true);
        Guideline3.setEnabled(true);
        Guideline5.setEnabled(true);
        Guideline6.setEnabled(true);
        Guideline7.setEnabled(true);
        Guideline8.setEnabled(true);
        Guideline9.setEnabled(true);
        Guideline10.setEnabled(true);
        Guideline11.setEnabled(true);
        Guideline12.setEnabled(true);
        Guideline13.setEnabled(true);
        Guideline14.setEnabled(true);
        Guideline15.setEnabled(true);
        Guideline16.setEnabled(true);
        Guideline17.setEnabled(true);
        Guideline18.setEnabled(true);

        recyclerView_Winner.setEnabled(true);
        recyclerView_Luck.setEnabled(true);
        videoplayer.setEnabled(true);
        linklayout.setEnabled(true);
        LinkImage.setEnabled(true);
        ClickArea.setEnabled(true);
        ClickAreaLeft.setEnabled(true);
        indicatorView.setEnabled(true);
        progressbar.setEnabled(true);
        circularcountdown.setEnabled(true);
        Coincount.setEnabled(true);
        ChangingTxtRotate.setEnabled(true);
        Todayluck.setEnabled(true);
        Todayswinner.setEnabled(true);
        seekBar.setEnabled(true);
        seekbarvideo.setEnabled(true);
        seekbarlink.setEnabled(true);
        exitfullscreen.setEnabled(true);
        bg_fullscreen.setEnabled(true);
        landscapemode.setEnabled(true);
        bottomlayout.setEnabled(true);
        videoviewlay.setEnabled(true);
        MainLayout.setEnabled(true);
        TopBar.setEnabled(true);
//        MenuIcon.setEnabled(true);
        ShareImage.setEnabled(true);
        WinnerLeftArrow.setEnabled(true);
        WinnerRightArrow.setEnabled(true);
        LuckLeftArrow.setEnabled(true);
        LuckRightArrow.setEnabled(true);
        scan_luck.setEnabled(true);
        Luckradar.setEnabled(true);
        FindLuck.setEnabled(true);
        Ads_ImageView.setEnabled(true);
        MuteButton.setEnabled(true);
        VolUpButton.setEnabled(true);
        SkipImage.setEnabled(true);
        mute.setEnabled(true);
        Vol.setEnabled(true);
        PlayPause.setEnabled(true);
        PlayPauseLay.setEnabled(true);
        viewright.setEnabled(true);
        viewleft.setEnabled(true);
        Seperator1.setEnabled(true);
        Seperator2.setEnabled(true);
        Seperator3.setEnabled(true);
        Seperator4.setEnabled(true);
        Seperator5.setEnabled(true);
        Skipimage.setEnabled(true);
        TopIcon.setEnabled(true);
        exiticon_landscape.setEnabled(true);
        viewTop.setEnabled(true);
        viewMain.setEnabled(true);
    }

    public void disable_All(){

        AdsGuideline4.setEnabled(false);
        Guideline.setEnabled(false);
        Guideline2.setEnabled(false);
        Guideline3.setEnabled(false);
        Guideline5.setEnabled(false);
        Guideline6.setEnabled(false);
        Guideline7.setEnabled(false);
        Guideline8.setEnabled(false);
        Guideline9.setEnabled(false);
        Guideline10.setEnabled(false);
        Guideline11.setEnabled(false);
        Guideline12.setEnabled(false);
        Guideline13.setEnabled(false);
        Guideline14.setEnabled(false);
        Guideline15.setEnabled(false);
        Guideline16.setEnabled(false);
        Guideline17.setEnabled(false);
        Guideline18.setEnabled(false);

        recyclerView_Winner.setEnabled(false);
        recyclerView_Luck.setEnabled(false);
        videoplayer.setEnabled(false);
        linklayout.setEnabled(false);
        LinkImage.setEnabled(false);
        ClickArea.setEnabled(false);
        ClickAreaLeft.setEnabled(false);
        indicatorView.setEnabled(false);
        progressbar.setEnabled(false);
        circularcountdown.setEnabled(false);
        Coincount.setEnabled(false);
        ChangingTxtRotate.setEnabled(false);
        Todayluck.setEnabled(false);
        Todayswinner.setEnabled(false);
        seekBar.setEnabled(false);
        seekbarvideo.setEnabled(false);
        seekbarlink.setEnabled(false);
        exitfullscreen.setEnabled(false);
        bg_fullscreen.setEnabled(false);
        landscapemode.setEnabled(false);
        bottomlayout.setEnabled(false);
        videoviewlay.setEnabled(false);
        MainLayout.setEnabled(false);
        TopBar.setEnabled(false);
//        MenuIcon.setEnabled(false);
        ShareImage.setEnabled(false);
        WinnerLeftArrow.setEnabled(false);
        WinnerRightArrow.setEnabled(false);
        LuckLeftArrow.setEnabled(false);
        LuckRightArrow.setEnabled(false);
        scan_luck.setEnabled(false);
        Luckradar.setEnabled(false);
        FindLuck.setEnabled(false);
        Ads_ImageView.setEnabled(false);
        MuteButton.setEnabled(false);
        VolUpButton.setEnabled(false);
        SkipImage.setEnabled(false);
        mute.setEnabled(false);
        Vol.setEnabled(false);
        PlayPause.setEnabled(false);
        PlayPauseLay.setEnabled(false);
        viewright.setEnabled(false);
        viewleft.setEnabled(false);
        Seperator1.setEnabled(false);
        Seperator2.setEnabled(false);
        Seperator3.setEnabled(false);
        Seperator4.setEnabled(false);
        Seperator5.setEnabled(false);
        Skipimage.setEnabled(false);
        TopIcon.setEnabled(false);
        exiticon_landscape.setEnabled(false);
        viewTop.setEnabled(false);
        viewMain.setEnabled(false);
    }


    private void functions() {
        Ads_ImageView.setVisibility(View.INVISIBLE);
        mute.setVisibility(View.INVISIBLE);
        mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoplayer);
        videoplayer.setMediaController(mediacontroller);
        mediacontroller.setVisibility(View.GONE);
        TopBar.setVisibility(View.VISIBLE);
        landscapemode.setVisibility(View.VISIBLE);
        PlayPause.setImageResource(R.drawable.pause);
        videoplayer.setOnClickListener(v -> {
        });
        MenuIcon.setOnClickListener(v -> {
            Exitall();
            if (SignedIn.equals("1")){
                Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                finish();
            }else {
                Intent in=new Intent(getApplicationContext(),Activity_GuestUserMenu.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                finish();
            }
//            //overridePendingTransition(R.anim.left_right, R.anim.right_left);
        });

        WinnerLeftArrow.setOnClickListener(v -> {
            position2=position2+1;
            recyclerView_Winner.smoothScrollToPosition(position2);
        });
        LuckRightArrow.setOnClickListener(v -> {
            position=position+1;
            recyclerView_Luck.smoothScrollToPosition(position);
        });
        WinnerRightArrow.setOnClickListener(v -> {
            if (position2 != 0) {
                position2 = position2 - 1;
                recyclerView_Winner.smoothScrollToPosition(position2);
            }
        });
        LuckLeftArrow.setOnClickListener(v -> {
            if (position != 0) {
                position = position - 1;
                recyclerView_Luck.smoothScrollToPosition(position);
            }
        });
        Luckradar.setOnClickListener(v -> {
            StopFunction();
            Exitall();
            Intent in=new Intent(getApplicationContext(),Activity_LuckRadar.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);
            finish();
            //overridePendingTransition(R.anim.left_right, R.anim.right_left);
        });

        bg_fullscreen.setOnClickListener(v -> {
            stopPosition=videoplayer.getCurrentPosition();
            Rotated="Land";
            int orientationori = getResources().getConfiguration().orientation;
            if (orientationori == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                Rotated="port";
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });
        exitfullscreen.setOnClickListener(v -> {
            stopPosition=videoplayer.getCurrentPosition();
            Rotated="Land";
            int orientationori = getResources().getConfiguration().orientation;
            if (orientationori == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                Rotated="port";
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });
        exiticon_landscape.setOnClickListener(v -> {
            stopPosition=videoplayer.getCurrentPosition();
            Rotated="Land";
            int orientationori = getResources().getConfiguration().orientation;
            if (orientationori == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                Rotated="port";
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
      /*  FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent()).addOnSuccessListener(this, pendingDynamicLinkData -> {

            System.out.println("LS DYNAMIC 1  "+pendingDynamicLinkData.getLink());

            Uri deeplink=null;
            if (pendingDynamicLinkData!=null){
                deeplink=pendingDynamicLinkData.getLink();
                Toast.makeText(Activity_HomePage.this, ""+deeplink.toString(), Toast.LENGTH_SHORT).show();
            }
            if (deeplink!=null){
                System.out.println("LS DYNAMIC 2  "+deeplink.toString());
                String currPage=deeplink.getQueryParameter("curPage");
                int curPage=Integer.parseInt(currPage);
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                System.out.println("LS DYNAMIC 3  "+e.getMessage());
            }
        });*/
        ShareImage.setOnClickListener(v -> {
            DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                    .setLink(Uri.parse("http://redbuds.com/"))
                    .setDomainUriPrefix("https://orgaesluckyscan.page.link")
                    // Open links with this app on Android
                    .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                    // Open links with com.example.ios on iOS
                    .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
                    .buildDynamicLink();

            Uri dynamicLinkUri = dynamicLink.getUri();
            System.out.println("LS DYNAMIC  1  "+dynamicLinkUri);
            String sharelinktext  = "https://orgaesluckyscan.page.link/?"+
                    "link=https://www.blueappsoftware.com/"+
                    "&apn="+ getPackageName()+
                    "&st="+"Here is Lucky scan for you"+
                    "&sd="+"Reward Coins 20"+
                    "&si="+"https://logos-world.net/wp-content/uploads/2020/06/Manchester-United-Logo-1998-Present.jpg";
            Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                    .setLongLink(Uri.parse(sharelinktext))
                    // Set parameters
                    // ...
                    .buildShortDynamicLink()
                    .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                        @Override
                        public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                            if (task.isSuccessful()) {
                                // Short link created
                                Uri shortLink = task.getResult().getShortLink();
                                Uri flowchartLink = task.getResult().getPreviewLink();
                                System.out.println("LS DYNAMIC  2  "+shortLink);

                                Intent in=new Intent();
                                in.setAction(Intent.ACTION_SEND);
                                in.putExtra(Intent.EXTRA_TEXT,shortLink.toString());
                                in.setType("text/plain");
                                startActivity(in);

                            } else {
                                // Error
                                // ...
                                task.getException();
                            }
                        }
                    });

        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    /*
     * Purpose – for getting the lati and longi of current location
     * @author SHANIL
     * Created on 21-5-2020
     */

    private void getLocation() {
        System.out.println("LS GPS 1 ");
        gps = new GPSTracker(Activity_HomePage.this);
        // check if GPS enabled
        if(gps.isGPSEnabled){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Constants.Const_Latitude= String.valueOf(latitude);
            Constants.Const_Longitude= String.valueOf(longitude);
            System.out.println("LS PERMISSION CHECK 1  "+Constants.Const_Latitude+" ------------  "+Constants.Const_Longitude);
            if (latitude==0||longitude==0){
                System.out.println("LS PERMISSION CHECK 2  ");
                isValue=false;
            }else {
                isValue=true;
                System.out.println("LS PERMISSION CHECK 3  ");
                if (QR_SCANNED.equals("FINISHED"))
                {
                    System.out.println("LS PERMISSION CHECK 4  ");
                    Fetch_Ads(1);
                }else {
                    System.out.println("LS PERMISSION CHECK 5  ");
                    Fetch_Ads(0);
                }
            }
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                gps = new GPSTracker(Activity_HomePage.this);
                // Check if GPS enabled
                if (gps.isGPSEnabled) {
                    System.out.println("LS PERMISSION CHECK 6  ");
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Constants.Const_Latitude= String.valueOf(latitude);
                    Constants.Const_Longitude= String.valueOf(longitude);
                    if (!isValue) {
                        if (QR_SCANNED.equals("FINISHED")) {
                            System.out.println("LS PERMISSION CHECK 8  ");
                            Fetch_Ads(1);
                        } else {
                            System.out.println("LS PERMISSION CHECK 9  ");
                            Fetch_Ads(0);
                        }
                    }
                } else {
                    // Can't get location.
                    // GPS or network is not enabled.
                    // Ask user to enable GPS/network in settings.
                    gps.showSettingsAlert();
                }
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(getApplicationContext(), "You need to grant permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void getUserdata(String ad_Id, @NotNull VideoView videoplayer, String formatString, int i, int i1, String skip){
        videoplayer.pause();
        progressbar.setVisibility(View.VISIBLE);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        call4 = apiInterface.get_Collect(auth_token, String.valueOf(i1),Constants.Const_Latitude,Constants.Const_Longitude,
                String.valueOf(i),String.valueOf(User_Ad_ID),UserClaimedLuckID,link_clicked);
        call4.enqueue(new Callback<CollectCoin>() {
            @Override
            public void onResponse(@NotNull Call<CollectCoin> call, @NotNull Response<CollectCoin> response) {
                progressbar.setVisibility(View.INVISIBLE);
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    System.out.println("LS COIN API 3 "+Code+"              "+formatString);
                    if (Code==200){
                        if (skip.equals("finish")){
                            System.out.println("LS COIN API 4 "+Code);
                            if (formatString.equals("DEFAULT")){
                                System.out.println("LS AD FORMAT CHECK 3 ");
                                Fetch_Ads(0);
                            }else {
                                FinishedPopupVideo(videoplayer, formatString, ad_Id, i1);
                            }
                        }else {
                            System.out.println("LS COIN API 5 "+Code);
                            if (formatString.equals("DEFAULT")){
                                System.out.println("LS AD FORMAT CHECK 4 ");
                                Fetch_Ads(0);
                            }else {
                                SkippedPopupVideo(videoplayer, formatString, ad_Id,i1);
                            }
                        }
                    }else if (Code==201){
//                        videoplayer.start();
//                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
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
                    System.out.println("LS COIN API 2 "+e.getMessage());
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            @Override
            public void onFailure(@NotNull Call<CollectCoin> call, @NotNull Throwable t) {
                progressbar.setVisibility(View.INVISIBLE);
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                System.out.println("LS COIN API 1 "+t.getMessage());
            }
        });


    }

    @SuppressLint("SetTextI18n")
    private void Fetch_Ads(int  adType) {
        System.out.println("LS FRST ADS   "+adType);
        link_clicked="0";
        Exitall();
        StopFunction();
        Rotation();
        Coincount.setText("00/00");
        progressbar.setVisibility(View.VISIBLE);
        LuckID.clear();
        LuckIMAGE.clear();
        LuckNAME.clear();
        LuckTIME.clear();
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<Home_New_data>call=apiInterface.Api_ads("http://ec2-13-232-195-100.ap-south-1.compute.amazonaws.com/ls_dev/userapi/ads/"+Constants.Const_Latitude+"/"+Constants.Const_Longitude+"/"+adType,auth_token);
        call.enqueue(new Callback<Home_New_data>() {
            @Override
            public void onResponse(@NotNull Call<Home_New_data> call, @NotNull Response<Home_New_data> response) {
                System.out.println("LS EXCEPTION CHECK  1  ");
                try {

                    assert response.body() != null;
                    int Code = response.body().getCode();
                    System.out.println("LS NEW 1  " + Code);
                    if (Code == 200) {
/*
                        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo netInfo = cm.getActiveNetworkInfo();
                        //should check null because in airplane mode it will be null
                        NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
                        int downSpeed = nc.getLinkDownstreamBandwidthKbps();
                        int upSpeed = nc.getLinkUpstreamBandwidthKbps();
                        System.out.println("LS BANDWIDTH CHECK  "+downSpeed+"-------------"+upSpeed);
*/
                        enable_All();
                        Home_New_Response home_response = response.body().getResponse();
                        System.out.println("LS ADS RESPONSE  "+home_response.toString());
                        List<Home_New_Ad> ads_new = home_response.getAds();
                        if (ads_new.size()>0){
                            for (int i=0;i<ads_new.size();i++){
                                Ad_Id = ads_new.get(i).getId();
                                Voice_Url = ads_new.get(i).getVoiceUrl();
                                Ad_Url = ads_new.get(i).getAdUrl();
                                Link_url = ads_new.get(i).getLinkUrl();
                                Duration = ads_new.get(i).getDuration();
                                Coin_Count = ads_new.get(i).getBaseCoinCount();
                                Coin_Time = ads_new.get(i).getBaseCoinTime();
                                Luck_Status = ads_new.get(i).getLuckStatus();
                                Name_Ad = ads_new.get(i).getName();
                                ADTYPE = ads_new.get(i).getType();
                            }
                        }
                        List<Home_New_Luck>home_new_lucks=response.body().getResponse().getLucks();
                        System.out.println("LS LUCK TIME   "+home_new_lucks.size()+"     "+Name_Ad);
                        Constants.Const_LuckData=new ArrayList<>();
                        Constants.Const_LuckData=home_new_lucks;
                        if (home_new_lucks.size()>0){
                            for (int j=0;j<home_new_lucks.size();j++){
                                Home_New_Luck home_new_luck=home_new_lucks.get(j);
                                LuckID.add(home_new_luck.getLuckId());
                                LuckIMAGE.add(home_new_luck.getImage());
                                LuckNAME.add(home_new_luck.getName());
                                LuckTIME.add(home_new_luck.getTime());
                                System.out.println("LS LUCK TIMES LIST    "+LuckTIME);
                            }
                        }
                        User_Ad_ID= String.valueOf(response.body().getResponse().getUserAdId());
                        if (ADTYPE.equals("DEFAULT")){
                            new SweetAlertDialog(Activity_HomePage.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Hi!,")
                                    .setContentText("You are currently viewing default ads,To get lucks or coins tap 'LUCKY SCAN' button  ")
                                    .setConfirmText("SCAN LUCK")
                                    .setCancelText("CLOSE")
                                    .setCancelClickListener(Dialog::dismiss)
                                    .setConfirmClickListener(sDialog -> {
                                        {
                                            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                                            editor.putString("ADS_TYPE", "DEFAULT");
                                            editor.putString("AdsID", Ad_Id);
                                            editor.apply();
                                        }
                                        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                                        editor.putString("QR_SCANNED", "NOT_FINISHED");
                                        editor.putString("format", "DEFAULT");
                                        editor.apply();
                                        StopFunction();
                                        Exitall();
                                        Intent in=new Intent(getApplicationContext(), QRSCANNER.class);
                                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(in);
                                        finish();
                                    })
                                    .show();
                            DefaultAds_Fun(Ad_Id, Ad_Url, Coin_Time, Name_Ad,"0",Link_url);
                        }else {
                            MainAds_Function(Ad_Id, Ad_Url, Coin_Time, Name_Ad,Coin_Count,Link_url);
                        }
                    } else if (Code == 201) {
                        Toast.makeText(Activity_HomePage.this, "Ads not available", Toast.LENGTH_SHORT).show();
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
                }catch (Exception e)
                {
                    e.printStackTrace();
                    System.out.println("LS EXCEPTION CHECK  2  "+e.getMessage());
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Home_New_data> call, @NotNull Throwable t) {
                System.out.println("LS EXCEPTION CHECK  3  "+t.getMessage());
                indicatorView.setVisibility(View.VISIBLE);
                progressbar.setVisibility(View.VISIBLE);
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });
    }
    /*
     * Main Ads Function
     * Random ads
     * Sequence Ads
     *
     * */
    private void MainAds_Function(String ad_Id, String ad_Url,
                                  String coin_Time, String name_Ad, String coin_Count, String link_url) {
        Constants.Const_AdsTYPE="MAIN_ADS";
        try {
            dialogSuccessPopupVideo.dismiss();
            if (dialogSkipPopupVideo.isShowing())
            {
                dialogSkipPopupVideo.dismiss();
            }else if (dialogFinishPopupVideo.isShowing())
            {
                dialogFinishPopupVideo.dismiss();
            }else if (dialogSuccessPopupVideo.isShowing()) {
                dialogSuccessPopupVideo.dismiss();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        circularcountdown.setVisibility(View.GONE);


        //checking the ads type. if ads type is link, so it will displayed in image view and we have option to visit the link on clicking the button
        //if ad type is video, it will display the video file
        if ("VIDEO".equals(name_Ad)) {
            Ads_ImageView.setVisibility(View.INVISIBLE);
            linklayout.setVisibility(View.INVISIBLE);
            videoplayer.setVisibility(View.VISIBLE);
            landscapemode.setVisibility(View.VISIBLE);
            VideoID = ad_Id;

            seekbarvideo.setVisibility(View.VISIBLE);
            indicatorView.setVisibility(View.VISIBLE);

            MediaController mediaController1 = new MediaController(this);
            String vidAddress = AppConfig.URL_BASE_VIDEOS + ad_Url;
            mediaController1.setAnchorView(videoplayer);
            try {
                videoplayer.setVideoURI(Uri.parse(vidAddress));
            } catch (Exception e) {
                e.printStackTrace();
            }
            videoplayer.setMediaController(mediaController1);
            mediaController1.setVisibility(View.GONE);
            videoplayer.requestFocus();
            circularcountdown.setText("");
            PlayPauseLay.setOnClickListener(v -> {
                if (videoplayer.isPlaying()) {
                    videoplayer.pause();
                    playvideo = 1;
                    PlayPause.setImageResource(R.drawable.play);
                } else {
                    playvideo = 0;
                    videoplayer.start();
                    Constants.Const_CoinTime = Integer.parseInt(coin_Time);
                    Constants.Const_AdsID = ad_Id;
                    new SeekbarHandler_Video().execute();
                    PlayPause.setImageResource(R.drawable.pause);
                }
            });
            //when video is prepared the timer will start and it will check the luck time and video current position , if it matches the luck popup will displayed and you can claim
            videoplayer.setOnPreparedListener(mp -> {
                videoplayer.start();
                Constants.Const_CoinTime = Integer.parseInt(coin_Time);
                Constants.Const_AdsID = ad_Id;
                new SeekbarHandler_Video().execute();
                setVideoProgress(coin_Time, "MAIN_ADS", ad_Id,coin_Count);
            });
            videoplayer.setOnErrorListener((mp, what, extra) -> true);
        }else if ("LINK".equals(name_Ad)){
            videoplayer.setOnClickListener(v -> {
                if (link_url.length()>0) {
                    try {
                        link_clicked="1";
                        Intent shareIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "LS (LUCKY SCAN)");
                        String shareMessage = "\nLet me recommend you this application  \nLS (LUCKY SCAN)\n";
                        shareMessage = shareMessage + link_url + "\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "choose one"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /*
     * Function for displaying Default ads
     * image ads , Link ads , Video Ads
     * No lucks and Coin Earnings
     * */
    @SuppressLint("UseCompatLoadingForDrawables")
    private void DefaultAds_Fun(String ad_Id, String ad_Url,
                                String coin_Time,
                                String name_Ad, String s, String link_url) {
        Constants.Const_AdsTYPE="DEFAULT";

        try {

            dialogSuccessPopupVideo.dismiss();
            if (dialogSkipPopupVideo.isShowing())
            {
                dialogSkipPopupVideo.dismiss();
            }else if (dialogFinishPopupVideo.isShowing())
            {
                dialogFinishPopupVideo.dismiss();
            }else if (dialogSuccessPopupVideo.isShowing())
            {
                dialogSuccessPopupVideo.dismiss();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        circularcountdown.setVisibility(View.GONE);
        try {


            switch (name_Ad) {

                case "VIDEO":

                    Ads_ImageView.setVisibility(View.INVISIBLE);
                    linklayout.setVisibility(View.INVISIBLE);
                    videoplayer.setVisibility(View.VISIBLE);
                    landscapemode.setVisibility(View.VISIBLE);
                    VideoID = ad_Id;
                    seekbarvideo.setVisibility(View.VISIBLE);
                    indicatorView.setVisibility(View.VISIBLE);


                    MediaController mediaController1 = new MediaController(this);
                    String vidAddress = AppConfig.URL_BASE_VIDEOS + ad_Url;
                    mediaController1.setAnchorView(videoplayer);
                    try {
                        videoplayer.setVideoURI(Uri.parse(vidAddress));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    videoplayer.setMediaController(mediaController1);
                    mediaController1.setVisibility(View.GONE);

                    videoplayer.requestFocus();
                    PlayPauseLay.setOnClickListener(v -> {
                        if (videoplayer.isPlaying()){
                            videoplayer.pause();
                            playvideo=1;

                            PlayPause.setImageResource(R.drawable.play);
                        }else {
                            playvideo=0;
                            videoplayer.start();
                            Constants.Const_CoinTime= Integer.parseInt(coin_Time);
                            Constants.Const_AdsID=ad_Id;
                            new SeekbarHandler_Video().execute();
                            PlayPause.setImageResource(R.drawable.pause);
                        }

                    });
                    videoplayer.setOnErrorListener((mp, what, extra) -> false);
                    circularcountdown.setText("");
                    videoplayer.setOnPreparedListener(mp -> {
                        videoplayer.start();
                        PlayPause.setImageResource(R.drawable.pause);
                        Constants.Const_CoinTime= Integer.parseInt(coin_Time);
                        Constants.Const_AdsID=ad_Id;
                        new SeekbarHandler_Video().execute();
                        seekbarvideo.setThumb(getResources().getDrawable(R.drawable.circle));
                        setVideoProgress(coin_Time, "DEFAULT", ad_Id, s);
                    });

                    videoplayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            return true;
                        }
                    });
                    break;

                case "LINK":
                    videoplayer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (link_url.length()>0) {
                                try {
                                    link_clicked="1";
                                    Intent shareIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    shareIntent.setType("text/plain");
                                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "LS (LUCKY SCAN)");
                                    String shareMessage = "\nLet me recommend you this application  \nLS (LUCKY SCAN)\n";
                                    shareMessage = shareMessage + link_url + "\n\n";
                                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isViewOn=true;

        landscapemode.setVisibility(View.VISIBLE);
        exitfullscreen.setImageResource(R.drawable.shrink_icon);
        Nav_Functions();
        get_LuckWinners();
    }

    /*
     * Navigation drawer function
     * user name and images will set here after login
     * */
    private void Nav_Functions() {
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        SessionID= prefsnew.getString("UserID", "");
        SignedIn= prefsnew.getString("SignedIn", "");
        USERID= prefsnew.getString("Userid", "");
        USERIMAGE= prefsnew.getString("Userpic", "");
        USERNAME= prefsnew.getString("Username", "");
//        QR_SCANNED= prefsnew.getString("QR_SCANNED", "");

    }

    /*
     * Mute ad sound
     * */
    private void mute() {
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            assert am != null;
            am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);

        }else {
            assert am != null;
            am.setStreamMute(AudioManager.STREAM_MUSIC, true);
        }

    }

    /*
     * Unmute ad sound
     * */
    public void unmute() {
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE,0 );
            am.setStreamVolume(AudioManager.STREAM_MUSIC, 8, 0);
        }else {
            am.setStreamMute(AudioManager.STREAM_MUSIC, true);
            am.setStreamVolume(AudioManager.STREAM_MUSIC, 8, 0);
        }

    }
    /*
     * Function for video ad progress with seek bar control
     * */
    public void setVideoProgress(String coin_Time, String formatString, String ad_Id, String coin_Count) {
        try {
            Public_AdsID=ad_Id;
            Public_Format=formatString;
            countvideo= 1;
            countlink=1;
            countimage=1;
            seekbarvideo.setVisibility(View.VISIBLE);
            progressbar.setVisibility(View.GONE);

            seekbarvideo.setEnabled(false);

            mute.setOnClickListener(v -> {
                unmute();
                Vol.setVisibility(View.VISIBLE);
                mute.setVisibility(View.INVISIBLE);
            });
            Vol.setOnClickListener(v -> {
                mute();
                Vol.setVisibility(View.INVISIBLE);
                mute.setVisibility(View.VISIBLE);
            });

            Constants.Const_CoinTime= Integer.parseInt(coin_Time);
            landscapemode.setVisibility(View.VISIBLE);
            indicatorView.setVisibility(View.INVISIBLE);
            scan_luck.setOnClickListener(v -> {
                videoplayer.pause();
                System.out.println("LS SCAN LUCK TEST 1  "+formatString);
                if (formatString.equals("DEFAULT")){
                    if (QR_SCANNED.equals("FINISHED")){
                        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                        editor.putString("ADS_TYPE", "MAIN_ADS");
                        editor.putString("AdsID", ad_Id);
                        editor.apply();
                    }else {
                        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                        editor.putString("ADS_TYPE", "DEFAULT");
                        editor.putString("AdsID", ad_Id);
                        editor.apply();
                    }
                    SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                    editor.putString("QR_SCANNED", "NOT_FINISHED");
                    editor.putString("format", formatString);
                    editor.apply();
                    StopFunction();
                    Exitall();
                    Intent in=new Intent(getApplicationContext(), QRSCANNER.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                    finish();
                }else {
                    getUserdata(ad_Id,videoplayer,formatString,videoplayer.getCurrentPosition()/1000,(videoplayer.getCurrentPosition())/(Constants.Const_CoinTime*1000),"skip");
                }
            });
            videoplayer.setOnCompletionListener(mp -> {
                mp.stop();

                try{
                    seekbarvideo.setProgress(0);
                    videoplayer.pause();
                    if (formatString.equals("DEFAULT")){
                        System.out.println("LS API CHECK 4  ");
                        getUserdata(ad_Id,videoplayer,formatString,0, (int) Constants.Const_CoinTOTAL,"finish");
                    }else {
                        System.out.println("LS API CHECK 5  ");
                        int time=videoplayer.getCurrentPosition()/1000;
                        int coin=(videoplayer.getCurrentPosition())/(Constants.Const_CoinTime*1000);
                        System.out.println("LS COIN COLLECT NUMBER  "+coin+"----------------"+Constants.Const_CoinTOTAL);
                        getUserdata(ad_Id,videoplayer,formatString,time, (int) Constants.Const_CoinTOTAL,"finish");
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            });

            videoplayer.setOnErrorListener((mp, what, extra) -> true);

            SkipImage.setOnClickListener(v -> {
                try{
                    videoplayer.pause();
                    PlayPause.setImageResource(R.drawable.play);
                    if (formatString.equals("DEFAULT")){
                        System.out.println("LS API CHECK 6  ");
                        getUserdata(ad_Id,videoplayer,formatString,videoplayer.getCurrentPosition()/1000,0,"skip");

                    }else {
                        System.out.println("LS API CHECK 7  ");
                        System.out.println("LS COIN COLLECT NUMBER2  "+(videoplayer.getCurrentPosition())/(Constants.Const_CoinTime*1000));
                        getUserdata(ad_Id,videoplayer,formatString,videoplayer.getCurrentPosition()/1000,(videoplayer.getCurrentPosition())/(Constants.Const_CoinTime*1000),"skip");
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * Function will work when the Ad type is link with seekbar controls
     * User will get coins and luck here
     * */

    boolean doubleBackToExitPressedOnce = false;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onBackPressed() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            exitfullscreen.setImageResource(R.drawable.exitfullscreen);
            scan_luck.setVisibility(View.GONE);
            ShareImage.setVisibility(View.GONE);
            FindLuck.setVisibility(View.GONE);
            recyclerView_Winner.setVisibility(View.GONE);
            recyclerView_Luck.setVisibility(View.GONE);
            WinnerLeftArrow.setVisibility(View.GONE);
            LuckRightArrow.setVisibility(View.GONE);
            WinnerRightArrow.setVisibility(View.GONE);
            LuckLeftArrow.setVisibility(View.GONE);
            bottomlayout.setVisibility(View.GONE);
            TopBar.setVisibility(View.GONE);
            landscapemode.setVisibility(View.GONE);
            AdsGuideline4.setGuidelinePercent((float) .90);
            Guideline.setGuidelinePercent((float).99);
            Guideline2.setGuidelinePercent((float).85);
            Guideline3.setGuidelinePercent((float).0);
            Guideline5.setGuidelinePercent((float).15);
            Guideline6.setGuidelinePercent((float).0);
            Guideline7.setGuidelinePercent((float).0);
            Guideline8.setGuidelinePercent((float).0);
            Guideline9.setGuidelinePercent((float).0);
            Guideline10.setGuidelinePercent((float).0);
            Guideline11.setGuidelinePercent((float).0);
            Guideline12.setGuidelinePercent((float).0);
            Guideline13.setGuidelinePercent((float).0);
            Guideline14.setGuidelinePercent((float).0);
            Guideline15.setGuidelinePercent((float).0);
            Guideline16.setGuidelinePercent((float).0);
            Guideline17.setGuidelinePercent((float).0);
            Guideline18.setGuidelinePercent((float).0);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(landscapemode);
            constraintSet.connect(R.id.playercontrols,ConstraintSet.TOP,R.id.avi,ConstraintSet.BOTTOM,0);
            constraintSet.applyTo(landscapemode);
        } else {
            if (doubleBackToExitPressedOnce) {
                Exitall();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                finish();
                ////overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please click back again to exit", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50);
            toast.show();

            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        }
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);

        assert savedInstanceState != null;
        savedInstanceState.putInt("MyIntKey", 1);
        savedInstanceState.putString("MyStringKey", "2");

        super.onSaveInstanceState(savedInstanceState);


    }



    /*
     * if the user have any luck , user can claim here and user can check this in luck box
     * */
    private void LuckClaim(String ID, String DATE, TextView txtDay, TextView txtHour, TextView txtMinute,
                           TextView txtSecond, String luckId, Button claim_now, ProgressBar p_bar) {
        p_bar.setVisibility(View.VISIBLE);
        claim_now.setEnabled(false);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ClaimDatAModel> call3 = apiInterface.Api_Claim_Luck(auth_token, ID, "luck");
        call3.enqueue(new Callback<ClaimDatAModel>() {
            @Override
            public void onResponse(@NotNull Call<ClaimDatAModel> call, @NotNull Response<ClaimDatAModel> response) {
                p_bar.setVisibility(View.GONE);
                claim_now.setEnabled(false);
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        Claim_Response claim_response=response.body().getResponse();

                        UserClaimedLuckID=luckId;
                        Toast.makeText(Activity_HomePage.this, "Successfully Claimed your Luck", Toast.LENGTH_SHORT).show();
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
                    claim_now.setEnabled(true);
                }
            }
            @Override
            public void onFailure(@NotNull Call<ClaimDatAModel> call, @NotNull Throwable t) {
                System.out.println("LS EXCEPTIONS 2  "+t.getMessage());
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                p_bar.setVisibility(View.GONE);
                claim_now.setEnabled(true);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        isViewOn=false;
        System.out.println("LS ADS TEST  PAUSE   ");
    }
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("LS ADS TEST  STOP   ");
        try{
            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
            editor.putString("QR_SCANNED", "NOT_FINISHED");
            editor.apply();
            System.out.println("LS API CHECK STOP  "+Public_AdsID+"        "+Public_Format+"        "+(int) Constants.Const_CoinTOTAL);

//            getUserdata(Public_AdsID,videoplayer,Public_Format,0,(int) Constants.Const_CoinTOTAL,"skip","1");
            /*
             * if the app goes to background, all popup will be closed
             * player also stopped
             * */
            if (dialogSkipPopupVideo.isShowing())
            {
                dialogSkipPopupVideo.dismiss();
            }else if (dialogSuccessPopupVideo.isShowing())
            {
                dialogSuccessPopupVideo.dismiss();
            }
//            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
//            editor.putString("QR_SCANNED", "NOT_FINISHED");
//            editor.apply();
            videoplayer.stopPlayback();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("LS ADS TEST  RESTART   ");
        try{
            if (dialogSkipPopupVideo.isShowing())
            {
                dialogSkipPopupVideo.dismiss();
            }else if (dialogSuccessPopupVideo.isShowing())
            {
                dialogSuccessPopupVideo.dismiss();
            }
            System.out.println("LS API CHECK RESTART  ");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Exitall(){
        try {
            if (playersound.isPlaying()){
                playersound.stop();
            }else if (playersoundvdo.isPlaying()){
                playersoundvdo.stop();
            }else if (videoplayer.isPlaying()){
                videoplayer.stopPlayback();
            }else if (dialogSkipPopupVideo.isShowing())
            {
                dialogSkipPopupVideo.dismiss();
            }else if (dialogFinishPopupVideo.isShowing())
            {
                dialogFinishPopupVideo.dismiss();
            }else if (dialogSuccessPopupVideo.isShowing())
            {
                dialogSuccessPopupVideo.dismiss();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void StopFunction(){
        try{

            if (dialogSkipPopupVideo.isShowing())
            {
                dialogSkipPopupVideo.dismiss();
            }else if (dialogFinishPopupVideo.isShowing())
            {
                dialogFinishPopupVideo.dismiss();
            }else if (dialogSuccessPopupVideo.isShowing())
            {
                dialogSuccessPopupVideo.dismiss();
            }
            videoplayer.stopPlayback();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Rotation(){
        try{
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);

                exitfullscreen.setImageResource(R.drawable.exitfullscreen);
                scan_luck.setVisibility(View.GONE);
                ShareImage.setVisibility(View.GONE);
                FindLuck.setVisibility(View.GONE);
                recyclerView_Winner.setVisibility(View.GONE);
                recyclerView_Luck.setVisibility(View.GONE);
                WinnerLeftArrow.setVisibility(View.GONE);
                LuckRightArrow.setVisibility(View.GONE);
                WinnerRightArrow.setVisibility(View.GONE);
                LuckLeftArrow.setVisibility(View.GONE);
                bottomlayout.setVisibility(View.GONE);
                TopBar.setVisibility(View.GONE);
                landscapemode.setVisibility(View.GONE);
                AdsGuideline4.setGuidelinePercent((float) .90);
                Guideline.setGuidelinePercent((float).99);
                Guideline2.setGuidelinePercent((float).85);
                Guideline3.setGuidelinePercent((float).0);
                Guideline5.setGuidelinePercent((float).15);
                Guideline6.setGuidelinePercent((float).0);
                Guideline7.setGuidelinePercent((float).0);
                Guideline8.setGuidelinePercent((float).0);
                Guideline9.setGuidelinePercent((float).0);
                Guideline10.setGuidelinePercent((float).0);
                Guideline11.setGuidelinePercent((float).0);
                Guideline12.setGuidelinePercent((float).0);
                Guideline13.setGuidelinePercent((float).0);
                Guideline14.setGuidelinePercent((float).0);
                Guideline15.setGuidelinePercent((float).0);
                Guideline16.setGuidelinePercent((float).0);
                Guideline17.setGuidelinePercent((float).0);
                Guideline18.setGuidelinePercent((float).0);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(landscapemode);
                constraintSet.applyTo(landscapemode);
                viewright.setVisibility(View.VISIBLE);
                viewleft.setVisibility(View.VISIBLE);
                MainLayout.setBackgroundColor(Color.BLACK);
                viewTop.setVisibility(View.GONE);
                landscapemode.setVisibility(View.GONE);
                viewMain.setVisibility(View.GONE);
                TopIcon.setVisibility(View.GONE);
                exiticon_landscape.setVisibility(View.VISIBLE);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * Function for Skip the video ad
     * */
    @SuppressLint("SetTextI18n")
    private void SkippedPopupVideo(VideoView player, String format, String adsID, int coins) {
        try{
            Rotation();
            Exitall();
            player.pause();
            dialogSkipPopupVideo.setContentView(R.layout.ad_skip_popup);
            dialogSkipPopupVideo.setCanceledOnTouchOutside(false);
            dialogSkipPopupVideo.setCancelable(false);
            Objects.requireNonNull(dialogSkipPopupVideo.getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialogSkipPopupVideo.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            dialogSkipPopupVideo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = dialogSkipPopupVideo.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            ImageView CoinBox = dialogSkipPopupVideo.findViewById(R.id.coinbox);
            CoinBox.setOnClickListener(v -> {
                if (SignedIn.equals("1")) {
                    Exitall();
                    player.stopPlayback();
                    Intent in = new Intent(getApplicationContext(), Activty_CoinBox.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please Signed in to Continue...", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
            });
            TextView ErrorTXT = dialogSkipPopupVideo.findViewById(R.id.coin);
            ErrorTXT.setText("" + coins + getString(R.string.coins));
            TextView AddedCoins = dialogSkipPopupVideo.findViewById(R.id.coin2);
            int TotalCoins = Constants.Const_UserCoins + coins;
            AddedCoins.setText("" + Constants.Const_UserCoins + "+" + coins + "=" + TotalCoins +getString(R.string.coins));
            Constants.Const_UserCoins = TotalCoins;
            ConstraintLayout constraintLayoutmain=dialogSkipPopupVideo.findViewById(R.id.main_lay);
            Zoomy.Builder builder = new Zoomy.Builder(Activity_HomePage.this)
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
            ConstraintLayout SCAN=dialogSkipPopupVideo.findViewById(R.id.scanbutton);
            ConstraintLayout CONTINUE=dialogSkipPopupVideo.findViewById(R.id.cntinue);
            ImageView CLOSE=dialogSkipPopupVideo.findViewById(R.id.ic_close);

            SCAN.setOnClickListener(v -> {
                player.stopPlayback();
                dialogSkipPopupVideo.dismiss();
                if (QR_SCANNED.equals("FINISHED")){
                    SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                    editor.putString("ADS_TYPE", "MAIN_ADS");
                    editor.putString("AdsID", adsID);
                    editor.apply();
                }else {
                    SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                    editor.putString("ADS_TYPE", "DEFAULT");
                    editor.putString("AdsID", adsID);
                    editor.apply();
                }
                SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                editor.putString("QR_SCANNED", "NOT_FINISHED");
                editor.putString("format", format);
                editor.apply();
                StopFunction();
                Exitall();
                Intent in=new Intent(getApplicationContext(), QRSCANNER.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                finish();
            });
            CONTINUE.setOnClickListener(v -> {
                dialogSkipPopupVideo.dismiss();
                player.start();
                new SeekbarHandler_Video().execute();
                PlayPause.setImageResource(R.drawable.pause);
            });
            CLOSE.setOnClickListener(v -> {
                player.stopPlayback();
                dialogSkipPopupVideo.dismiss();
                if (QR_SCANNED.equals("FINISHED")){
                    SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                    editor.putString("ADS_TYPE", "MAIN_ADS");
                    editor.putString("AdsID", adsID);
                    editor.apply();
                }else {
                    SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                    editor.putString("ADS_TYPE", "DEFAULT");
                    editor.putString("AdsID", adsID);
                    editor.apply();
                }
                SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                editor.putString("QR_SCANNED", "NOT_FINISHED");
                editor.putString("format", format);
                editor.apply();
                StopFunction();
                Exitall();
                Intent in=new Intent(getApplicationContext(), QRSCANNER.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);

                finish();
            });
            wlp.gravity = Gravity.BOTTOM;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
            window.setAttributes(wlp);
            try {
                dialogSkipPopupVideo.getWindow().getAttributes().windowAnimations = R.style.MyAlertDialogStyle;
                dialogSkipPopupVideo.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    /*
     *Function will work if the video ad completely finished without skip
     * it have 2 optons close or scan
     * if user scan the qr it will redirect to the next and user will get coin or luck
     * */
    @SuppressLint("SetTextI18n")
    private void FinishedPopupVideo(VideoView player1, String format, String adsID, int coins) {
        try {
            Rotation();
            Exitall();
            player1.stopPlayback();
//
            dialogFinishPopupVideo.setContentView(R.layout.ad_finish_popup);
            dialogFinishPopupVideo.setCanceledOnTouchOutside(false);
            dialogFinishPopupVideo.setCancelable(false);
            Objects.requireNonNull(dialogFinishPopupVideo.getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialogFinishPopupVideo.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            dialogFinishPopupVideo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = dialogFinishPopupVideo.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            TextView ErrorTXT = dialogFinishPopupVideo.findViewById(R.id.coin);
            TextView textView6 = dialogFinishPopupVideo.findViewById(R.id.textView6);
            ConstraintLayout TryButton = dialogFinishPopupVideo.findViewById(R.id.scanluck);
            ImageView CloseButton = dialogFinishPopupVideo.findViewById(R.id.ic_close);
            ErrorTXT.setText("" + coins + getString(R.string.coins));
            unmute();
            playersound = MediaPlayer.create(Activity_HomePage.this, R.raw.coinsdrop);
            playersound.start();
            playersound.setVolume(1f , 0);
            ImageView CoinBox = dialogFinishPopupVideo.findViewById(R.id.coinbox);
            TextView AddedCoins = dialogFinishPopupVideo.findViewById(R.id.coin2);
            int TotalCoins = Constants.Const_UserCoins + coins;
            AddedCoins.setText("" + Constants.Const_UserCoins + "+" + coins + "=" + TotalCoins /*+getString(R.string.coins)*/);
            Constants.Const_UserCoins = TotalCoins;
            ConstraintLayout constraintLayoutmain=dialogFinishPopupVideo.findViewById(R.id.main_lay);
            Zoomy.Builder builder = new Zoomy.Builder(Activity_HomePage.this)
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

            Typeface avner = Typeface.createFromAsset(getAssets(),
                    "AvenirLTStd-Light.otf");

            textView6.setTypeface(avner);
            ErrorTXT.setTypeface(avner);
            AddedCoins.setTypeface(avner);


            CoinBox.setOnClickListener(v -> {
                if (SignedIn.equals("1")) {
                    Exitall();
                    player1.stopPlayback();
                    Intent in = new Intent(getApplicationContext(), Activty_CoinBox.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please Signed in to Continue...", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
            });
            TryButton.setOnClickListener(v -> {
                dialogFinishPopupVideo.dismiss();
                player1.stopPlayback();
                playersound.stop();
                if (QR_SCANNED.equals("FINISHED")) {
                    SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                    editor.putString("ADS_TYPE", "MAIN_ADS");
                    editor.putString("AdsID", adsID);
                    editor.apply();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                    editor.putString("ADS_TYPE", "DEFAULT");
                    editor.putString("AdsID", adsID);
                    editor.apply();
                }
                SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                editor.putString("QR_SCANNED", "NOT_FINISHED");
                editor.putString("format", format);
                editor.apply();
                StopFunction();
                Exitall();
                Intent in = new Intent(getApplicationContext(), QRSCANNER.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                finish();
            });
            CloseButton.setOnClickListener(v -> {
                playersound.stop();
                player1.stopPlayback();
                SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                editor.putString("QR_SCANNED", "NOT_FINISHED");
                editor.putString("format", format);
                editor.apply();
                System.out.println("LS API CHECK 18  ");
                System.out.println("LS AD FORMAT CHECK 6 ");
                Fetch_Ads(0);
                dialogFinishPopupVideo.dismiss();
                PlayPause.setImageResource(R.drawable.pause);
            });
            wlp.gravity = Gravity.BOTTOM;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
            window.setAttributes(wlp);
            try {
                dialogFinishPopupVideo.getWindow().getAttributes().windowAnimations = R.style.MyAlertDialogStyle;
                dialogFinishPopupVideo.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    For Video ads only
     * popup function if the user get any luck , this popup will display and user can claim from here and check luck box
     * If user cliam the luck , the timer will start
     * if the user close the the next ad will come
     *
     * */
    @SuppressLint("SetTextI18n")
    private void POPUP_SUCCESSVideo(String luckId, String Collection_Center, VideoView videoplayer, String ID, String IMAGE, String DATE,
                                    String AdsID, String Fname, String Lname, String Desc, ProgressBar progressbar) {

        try{
            Exitall();
            Rotation();
            TextView txtDay, txtHour, txtMinute, txtSecond;

            dialogSuccessPopupVideo.setContentView(R.layout.scan_success);
            dialogSuccessPopupVideo.setCanceledOnTouchOutside(false);
            dialogSuccessPopupVideo.setCancelable(false);
            Objects.requireNonNull(dialogSuccessPopupVideo.getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialogSuccessPopupVideo.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            dialogSuccessPopupVideo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = dialogSuccessPopupVideo.getWindow();
            ImageView Imagclose=dialogSuccessPopupVideo.findViewById(R.id.close_button);
            ConstraintLayout luckbox=dialogSuccessPopupVideo.findViewById(R.id.luckbox);
            ImageView luckimage=dialogSuccessPopupVideo.findViewById(R.id.profilepic);
            ImageView scanbutton=dialogSuccessPopupVideo.findViewById(R.id.scanbutton);
            Button Claim_now=dialogSuccessPopupVideo.findViewById(R.id.claim_now);

            TextView TimerTxt=dialogSuccessPopupVideo.findViewById(R.id.redeemdate);
            TextView TimerTxt1=dialogSuccessPopupVideo.findViewById(R.id.coincount);
            TextView TimerTxt2=dialogSuccessPopupVideo.findViewById(R.id.details2);
            TextView TimerTxt3=dialogSuccessPopupVideo.findViewById(R.id.collection);
            TextView textView20=dialogSuccessPopupVideo.findViewById(R.id.textView20);
            TextView textView9=dialogSuccessPopupVideo.findViewById(R.id.textView9);
            TextView textView12=dialogSuccessPopupVideo.findViewById(R.id.textView12);
            TextView textView10=dialogSuccessPopupVideo.findViewById(R.id.textView10);
            TextView textView13=dialogSuccessPopupVideo.findViewById(R.id.textView13);

            txtDay = dialogSuccessPopupVideo.findViewById(R.id.txtDay);
            txtHour = dialogSuccessPopupVideo.findViewById(R.id.txtHour);
            txtMinute = dialogSuccessPopupVideo.findViewById(R.id.txtMinute);
            txtSecond = dialogSuccessPopupVideo.findViewById(R.id.txtSecond);
            ConstraintLayout constraintLayoutmain=dialogSkipPopupVideo.findViewById(R.id.main_lay);
            Zoomy.Builder builder = new Zoomy.Builder(Activity_HomePage.this)
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
            luckbox.setVisibility(View.GONE);

            Typeface avner = Typeface.createFromAsset(getAssets(),
                    "AvenirLTStd-Light.otf");
            TimerTxt.setTypeface(avner);
            TimerTxt1.setTypeface(avner);
            TimerTxt2.setTypeface(avner);
            TimerTxt3.setTypeface(avner);
            txtDay.setTypeface(avner);
            txtHour.setTypeface(avner);
            txtMinute.setTypeface(avner);
            txtSecond.setTypeface(avner);
            textView20.setTypeface(avner);
            textView9.setTypeface(avner);
            textView12.setTypeface(avner);
            textView10.setTypeface(avner);
            textView13.setTypeface(avner);
            luckbox.setOnClickListener(v -> {
                Exitall();
                Intent in =new Intent(getApplicationContext(), Activity_HomePage.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
            });
            TimerTxt1.setText(""+Fname+" "+Lname);
            TimerTxt.setText(""+DATE);
            TimerTxt2.setText(""+Desc);
            Glide.with(Activity_HomePage.this).load(AppConfig.URL_BASE_VIDEOS + IMAGE).into(luckimage);
            TimerTxt3.setText(Collection_Center);
            WindowManager.LayoutParams wlp = window.getAttributes();
            Imagclose.setOnClickListener(v -> {
                dialogSuccessPopupVideo.dismiss();
                videoplayer.start();
                PlayPause.setImageResource(R.drawable.pause);
                new SeekbarHandler_Video().execute();
            });
            luckbox.setOnClickListener(v -> {
                Exitall();
                Intent in =new Intent(getApplicationContext(), Activity_HomePage.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
            });
            scanbutton.setOnClickListener(v -> {
                dialogSuccessPopupVideo.dismiss();
                Exitall();
                SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                editor.putString("QR_SCANNED", "FINISHED");
                editor.putString("ADS_TYPE", "MAIN_ADS");
                editor.putString("AdsID", AdsID);
                editor.apply();
                StopFunction();
                Intent in=new Intent(getApplicationContext(), QRSCANNER.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                finish();
            });
            Claim_now.setOnClickListener(v -> {
                scanbutton.setVisibility(View.VISIBLE);
                luckbox.setVisibility(View.VISIBLE);
                LuckClaim(ID,DATE, txtDay, txtHour, txtMinute, txtSecond,luckId,Claim_now,progressbar);
            });
            wlp.gravity = Gravity.BOTTOM;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
            window.setAttributes(wlp);
            try {
                dialogSuccessPopupVideo.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onClickCalled(String clicked) {
        try{
            if (clicked.equals("1")){
                PlayPause.setImageResource(R.drawable.play);
                if (playersound.isPlaying()){
                    playersound.pause();
                }else if (playersoundvdo.isPlaying()){
                    playersoundvdo.pause();
                } if (videoplayer.isPlaying()){
                    videoplayer.pause();
                }
            }else {
                PlayPause.setImageResource(R.drawable.pause);
                videoplayer.start();
                new SeekbarHandler_Video().execute();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get_LuckWinners(){
        progressbar.setVisibility(View.VISIBLE);
        APIInterface apiInterface=APIClient.getClient().create(APIInterface.class);
        Call<Model_Luck_Winners> call3 = apiInterface.Fetch_L_W();
        call3.enqueue(new Callback<Model_Luck_Winners>() {
            @Override
            public void onResponse(@NotNull Call<Model_Luck_Winners> call, @NotNull Response<Model_Luck_Winners> response) {
                try {

                    progressbar.setVisibility(View.GONE);
                    assert response.body() != null;
                    int Code =response.body().getCode();

                    if (Code==200){
                        Luck_Winners_Response luck_winners_response=response.body().getResponse();
                        List<TodaysLuck_Data>todaysLuck_data=luck_winners_response.getTodaysLuck();
                        List<TodaysWinner_Data>todaysWinner_data=luck_winners_response.getTodaysWinner();
                        System.out.println("LS TODAY WINNERS   "+todaysWinner_data.size()+"                "+todaysLuck_data.size());
                        if (todaysWinner_data.size()>0){
                            mAdapterwinners = new AdapterDataWinners(auth_token,SignedIn, progressbar,todaysWinner_data,Activity_HomePage.this,getApplicationContext());
                            recyclerView_Winner.setLayoutManager(new SpeedyLinearLayoutManager(Activity_HomePage.this, SpeedyLinearLayoutManager.HORIZONTAL, false));
                            recyclerView_Winner.setItemAnimator(new DefaultItemAnimator());
                            recyclerView_Winner.setAdapter(mAdapterwinners);
                            scrollFunctionWinners(todaysWinner_data);
                        }
                        if (todaysLuck_data.size()>0){

                            mAdapter = new AdapterData(progressbar,todaysLuck_data,Activity_HomePage.this,getApplicationContext());
                            recyclerView_Luck.setLayoutManager(new SpeedyLinearLayoutManager(Activity_HomePage.this, SpeedyLinearLayoutManager.HORIZONTAL, false));
                            recyclerView_Luck.setItemAnimator(new DefaultItemAnimator());
                            recyclerView_Luck.setAdapter(mAdapter);
                            scrollFunction(todaysLuck_data);

                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Model_Luck_Winners> call, @NotNull Throwable t) {
                progressbar.setVisibility(View.GONE);
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });
    }

    private void scrollFunctionWinners(List<TodaysWinner_Data> todaysWinner_data) {
        Timer timer;
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTaskWinners(todaysWinner_data), 0, 1000); // delay*/
    }

    private void scrollFunction(List<TodaysLuck_Data> todaysLuck_data) {
        Timer timer;
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(todaysLuck_data), 0, 2000); // delay*/
    }
    private class RemindTask extends TimerTask {
        int position =0;
        List<TodaysLuck_Data> todaysLuck_data1;
        public RemindTask(List<TodaysLuck_Data> todaysLuck_data) {

            todaysLuck_data1=todaysLuck_data;
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                public void run() {
                    if(position==todaysLuck_data1.size()) {
                        position = 0;
                        position++;
                    }else{
                        position++;
                    }
                    Objects.requireNonNull(recyclerView_Luck.getLayoutManager()).scrollToPosition(position);
//                    recyclerView_Luck.startAnimation((Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_right));
                }
            });

        }
    }
    private class RemindTaskWinners extends TimerTask {
        int position =0;
        List<TodaysWinner_Data> todaysLuck_data1;
        public RemindTaskWinners(List<TodaysWinner_Data> todaysLuck_data) {
            todaysLuck_data1=todaysLuck_data;
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                public void run() {
                    if(position==todaysLuck_data1.size()) {
                        position = 0;
                        position++;
                    }else{
                        position++;
                    }
                    Objects.requireNonNull(recyclerView_Winner.getLayoutManager()).scrollToPosition(position);
                }
            });

        }
    }
    private void fun_Scroll(AdapterData adapter) {
        this.recyclerView_Luck.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()) {

            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(Activity_HomePage.this) {
                    private static final float SPEED = 3500f;// Change this value (default=25f)

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }
                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }
        };
//      LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        autoScrollAnother(adapter);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        this.recyclerView_Luck.setLayoutManager(layoutManager);
        this.recyclerView_Luck.setHasFixedSize(true);
        this.recyclerView_Luck.setItemViewCacheSize(1000);
        this.recyclerView_Luck.setDrawingCacheEnabled(true);
        this.recyclerView_Luck.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        this.recyclerView_Luck.setAdapter(adapter);

    }
    public void autoScrollAnother(AdapterData adapter) {
        scrollCount = 0;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                recyclerView_Winner.smoothScrollToPosition((scrollCount++));
                if (scrollCount == adapter.getItemCount() - 4) {
//                    stockListModels.addAll(stockListModels);
                    adapter.notifyDataSetChanged();
                }
                adapter.notifyDataSetChanged();
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
    }
    @SuppressLint("StaticFieldLeak")
    public class SeekbarHandler_Video extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            System.out.println("LS PROGRESS VDO UPDATE 1 ");
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            seekbarvideo.setProgress(videoplayer.getCurrentPosition());
            super.onProgressUpdate(values);
            seekbarvideo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint({"SetTextI18n", "DefaultLocale"})
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    AdsPlayingType = "VIDEO";
                    int duration=videoplayer.getDuration();
                    System.out.println("LS SEEKBAR COUNT  "+progress);
//                    int sec = progress / 1000;
//                    int mins = progress / 60000;
//                    sec %= 60;
//                    int millisec = progress % 10;
//                    int seektime=progress / 1000;
//                    CurrentCoins = (progress) / (Constants.Const_CoinTime * 1000);
//                    ChangingTxtRotate.setText(String.format("%1$02d", mins) + ":" + String.format("%1$02d", sec) +
//                            ":" + String.format("%1$02d", millisec));
                    int millis=duration-progress;
                    long hours = TimeUnit.MILLISECONDS.toHours(millis);
                    long mins = TimeUnit.MILLISECONDS.toMinutes(millis);
                    long secs = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(mins);
//                    long mil =  millis - TimeUnit.MILLISECONDS.toSeconds(millis)*1000;

                    String strTime = String.format("%02d:%02d ", /*hours,*/mins,secs/*, mil*/);
                    ChangingTxtRotate.setText(strTime);
//                    ChangingTxtRotate.setText(String.format("%d, %d",
//                            TimeUnit.MILLISECONDS.toMinutes(millis),
//                            TimeUnit.MILLISECONDS.toSeconds(millis) -
//                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
//                    ));
                    System.out.println("LS ADS DURATION  "+millis+"     "+ChangingTxtRotate.getText().toString());
                    System.out.println("LS COIN TIME VDO  " +Constants.Const_AdsTYPE+"         "+ (progress) / (Constants.Const_CoinTime * 1000));
                    if (!Constants.Const_AdsTYPE.equals("DEFAULT")) {
                        CurrentCoins =(progress)/(Constants.Const_CoinTime*1000);
                        for (int i=0;i<Constants.Const_LuckData.size();i++){
                            int times= Integer.parseInt(Constants.Const_LuckData.get(i).getTime());
                            System.out.println("LS LUCK TIMES LIST 2   "+millis/1000+"                   "+((millis/1000)+1)+"-------------------"+times);
                            if ((millis/1000)==times||((millis/1000)+1)==times){
                                fun_Luck(Constants.Const_LuckData.get(i).getLuckId());
                            }
                        }
                        System.out.println("LS NEW VDO   " + videoplayer.getCurrentPosition() + "     " + Constants.Const_CoinTOTAL);
                        Coincount.setText(" " + String.format("%1$02d", CurrentCoins) + "/" + String.format("%1$02d", Constants.Const_CoinTOTAL));
                    }
                    if (countvideo == (progress) / (Constants.Const_CoinTime * 1000)) {
                        try {
                            playersoundvdo.setAudioAttributes(
                                    new AudioAttributes
                                            .Builder()
                                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                            .build());

                            if (!Constants.Const_AdsTYPE.equals("DEFAULT")) {

                                System.out.println("LS LUCK TIMES LIST 1   "+((progress/1000)+1)+"-------------------"+progress+"-------------------");

                                playersoundvdo = MediaPlayer.create(getApplicationContext(), R.raw.coinonce);
                                playersoundvdo.start();
                                playersoundvdo.setVolume(0.98f , 0);
                            }
                        } catch (Exception ignored) {
                            playersoundvdo.stop();
                        }
                        countvideo++;
                    } else if (countvideo - 1 == Constants.Const_CoinTOTAL) {
                        playersoundvdo.stop();
                        countvideo = 1;
                    }
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    vdocurrent_pos = seekbarvideo.getProgress();
                    videoplayer.seekTo((int) vdocurrent_pos);
                }
            });
        }
        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... arg0) {
            vdocurrent_pos = videoplayer.getCurrentPosition();
            vdototal_duration = videoplayer.getDuration();
            seekbarvideo.setMax((int) vdototal_duration);
            final float totalcoin = ((float)(vdototal_duration))/(Constants.Const_CoinTime*1000);
            Constants.Const_CoinTOTAL= Math.round(totalcoin);
            System.out.println("LS PROGRESS UPDATE 3 ");
            try {
                while (videoplayer.isPlaying() && isViewOn) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    onProgressUpdate();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
    private void fun_Luck(String luckId) {
        get_LuckID.clear();
        get_Luck_Name.clear();
        get_LuckImage.clear();
        get_Luck_Claim_Date.clear();
        get_Luck_Fname.clear();
        get_Luck_Lname.clear();
        get_Luck_Desc.clear();
        get_Luck_Colle_Center.clear();
        System.out.println("LS LUCK TIMES LIST 3  "+luckId+"    "+Constants.Const_AdsID);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<InstantLuckDATA> call3 = apiInterface.Api_InstLuck(auth_token,luckId,Constants.Const_AdsID);
        call3.enqueue(new Callback<InstantLuckDATA>() {
            @Override
            public void onResponse(@NotNull Call<InstantLuckDATA> call, @NotNull Response<InstantLuckDATA> response) {
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    System.out.println("LS LUCK TIME  3   "+Code);
                    if (Code==200){
                        InstantLuck_Response instantLuck_response=response.body().getResponse();
                        List<Instant_LuckDetail>LuckDetails=instantLuck_response.getLuckDetails();
                        if (LuckDetails.size()>0){

                            for (int i=0;i<LuckDetails.size();i++){

                                Instant_LuckDetail luck=LuckDetails.get(i);
                                get_LuckID.add(luck.getId());
                                get_Luck_Name.add(luck.getName());
                                get_LuckImage.add(luck.getImage());
                                get_Luck_Claim_Date.add(luck.getEndDate());
                                get_Luck_Fname.add(luck.getClientUserFname());
                                get_Luck_Lname.add(luck.getClientUserLname());
                                get_Luck_Desc.add(luck.getDescription());
                                get_Luck_Colle_Center.add(luck.getCollectionCenter());
                            }
                            videoplayer.pause();
                            POPUP_SUCCESSVideo(luckId,get_Luck_Colle_Center.get(0),videoplayer,LuckID.get(0),
                                    get_LuckImage.get(0),get_Luck_Claim_Date.get(0),Constants.Const_AdsID,get_Luck_Fname.get(0),
                                    get_Luck_Lname.get(0),get_Luck_Desc.get(0),progressbar);
                        }
                    }else if (Code==201){
//                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
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
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    videoplayer.start();
                    System.out.println("LS LUCK TIME  4   " + e.getMessage());
                }
            }

            @Override
            public void onFailure(@NotNull Call<InstantLuckDATA> call, @NotNull Throwable t) {
                t.printStackTrace();
                System.out.println("LS LUCK TIME  5   "+t.getMessage());
                videoplayer.start();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }

        });
    }

    private void CoinRate(String token){

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CoinRateData> call3 = apiInterface.Fetch_Coinrate(token);
        call3.enqueue(new Callback<CoinRateData>() {
            @Override
            public void onResponse(@NotNull Call<CoinRateData> call, @NotNull Response<CoinRateData> response) {

                try {
                    int Code=response.body().getCode();
                    System.out.println("LS COIN RATE 1 "+Code);
                    if (Code==200){

                        CoinRate_Response coinRate_response=response.body().getResponse();
                        List<CoinRate>coinRates=coinRate_response.getCoinRate();
                        if (coinRates.size()>0){
                            for (int i=0;i<coinRates.size();i++){
                                CoinRate coinRate=coinRates.get(i);
                                Constants.Const_UserCoinValue= (coinRate.getCoinValue());
//                                Toast.makeText(Activity_HomePage.this, "Coin Collected", Toast.LENGTH_SHORT).show();
                            }
                            System.out.println("LS COIN RATE 2 "+Constants.Const_UserCoins);
                        }

                    }else {
//                        Toast.makeText(Activity_HomePage.this, "Coin Error", Toast.LENGTH_SHORT).show();
                    }


                }catch (Exception ignored){
                    ignored.printStackTrace();
                    System.out.println("LS COIN RATE 2 "+ignored.getMessage());
                }

            }

            @Override
            public void onFailure(@NotNull Call<CoinRateData> call, @NotNull Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                System.out.println("LS COIN RATE 3 "+t.getMessage());
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

}
