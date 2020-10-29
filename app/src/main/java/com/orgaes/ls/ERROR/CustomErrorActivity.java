package com.orgaes.ls.ERROR;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.orgaes.ls.Activity_Splash;
import com.orgaes.ls.R;


import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;


public class CustomErrorActivity extends Activity {

    public static String getNetworkType(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "2g";
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                /**
                 From this link https://en.wikipedia.org/wiki/Evolution-Data_Optimized ..NETWORK_TYPE_EVDO_0 & NETWORK_TYPE_EVDO_A
                 EV-DO is an evolution of the CDMA2000 (IS-2000) standard that supports high data rates.

                 Where CDMA2000 https://en.wikipedia.org/wiki/CDMA2000 .CDMA2000 is a family of 3G[1] mobile technology standards for sending voice,
                 data, and signaling data between mobile phones and cell sites.
                 */
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                //Log.d("Type", "3g");
                //For 3g HSDPA , HSPAP(HSPA+) are main  networktype which are under 3g Network
                //But from other constants also it will 3g like HSPA,HSDPA etc which are in 3g case.
                //Some cases are added after  testing(real) in device with 3g enable data
                //and speed also matters to decide 3g network type
                //https://en.wikipedia.org/wiki/4G#Data_rate_comparison
                return "3g";
            case TelephonyManager.NETWORK_TYPE_LTE:
                //No specification for the 4g but from wiki
                //I found(LTE (Long-Term Evolution, commonly marketed as 4G LTE))
                //https://en.wikipedia.org/wiki/LTE_(telecommunication)
                return "4g";
            default:
                return "Notfound";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        //Remove title bar
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_custom_errors);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        final CaocConfig config = CustomActivityOnCrash.getConfigFromIntent(getIntent());

        final String error = CustomActivityOnCrash.getStackTraceFromIntent(getIntent());


        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
                .enabled(false) //default: true
                .showErrorDetails(true) //default: true
                .showRestartButton(false) //default: true
                .logErrorOnRestart(true) //default: true
                .trackActivities(true) //default: false
                .minTimeBetweenCrashesMs(2000) //default: 3000
                .errorDrawable(R.drawable.error) //default: bug image
                .restartActivity(Activity_Splash.class) //default: null (your app's launch activity)
                .errorActivity(CustomErrorActivity.class) //default: null (default error activity)
                .apply();

        TextView restartButton = findViewById(R.id.restart_button);
        TextView feeedback = findViewById(R.id.shareid);
        feeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendLogcatMail(error);

            }
        });


        if (config == null) {
            //This should never happen - Just finish the activity to avoid a recursive crash.
            finish();
            return;
        }

        if (config.isShowRestartButton() && config.getRestartActivityClass() != null) {
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomActivityOnCrash.restartApplication(CustomErrorActivity.this, config);
                }
            });
        } else {
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomActivityOnCrash.closeApplication(CustomErrorActivity.this, config);

                    SendLogcatMail(error);
                }
            });


        }
    }

    public void SendLogcatMail(String error) {

        // save logcat in file


        System.getProperty("os.version"); // OS version
        String SDK = android.os.Build.VERSION.SDK;          // API Level
        String DEVICE = android.os.Build.DEVICE;           // Device
        String MODEL = android.os.Build.MODEL;            // Model
        String PRODUCT = android.os.Build.PRODUCT;       // Product

        TelephonyManager manager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String carrierName = manager.getSimOperatorName();

        String version = null;
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        // TODO: Use the current user's information
        // You can call any combination of these three methods

        String data = "CARRIER: " + carrierName + " , Network Type: " + chkStatus() + " , SDK: " + SDK + " , DEVICE:" + DEVICE + " , MODEL:" + MODEL + " , PRODUCT:" + PRODUCT + " , APP VERSION:" + version +
                "\n \n" + error;

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"orgaesit@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "LS BUGS");
        i.putExtra(Intent.EXTRA_TEXT   , " "+data);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CustomErrorActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:orgaesit@gmail.com"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "LS Error Report");
//        intent.putExtra(Intent.EXTRA_TEXT, data);
//
//
//        try {
//            startActivity(intent);
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(CustomErrorActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//        }
    }

    public String chkStatus() {
        final ConnectivityManager connMgr = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi.isConnectedOrConnecting()) {
            return "Wifi";
        } else if (mobile.isConnectedOrConnecting()) {

            return getNetworkType(getApplicationContext());
        } else {
            return "No Network";
        }
    }
}
