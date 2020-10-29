package com.orgaes.ls.QRCODE;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.orgaes.ls.Activity_HomePage;
import com.orgaes.ls.R;

public class QRSCANNER extends AppCompatActivity {
    private static final int MY_PERMISSION_REQUEST_CAMERA = 0;
    private IntentIntegrator qrScan;
    private ViewGroup mainLayout;
    String SessionID, SignedIn, ADS_TYPE, AdsID, auth_token;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_decoder);
        mainLayout = (ViewGroup) findViewById(R.id.main_layout);

        qrScan = new IntentIntegrator(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(false);
            integrator.setCaptureActivity(CameraCaptureActivity.class);
            integrator.initiateScan();

        } else {
            requestCameraPermission();
        }

    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Snackbar.make(mainLayout, "Camera access is required to display the camera preview.",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(QRSCANNER.this, new String[]{
                            Manifest.permission.CAMERA
                    }, MY_PERMISSION_REQUEST_CAMERA);
                }
            }).show();
        } else {
            Snackbar.make(mainLayout, "Permission is not available. Requesting camera permission.",
                    Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA
            }, MY_PERMISSION_REQUEST_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                if (result.getContents().equals("http://www.orgaes.com/projects/demo/apk/app-release.apk")) {
        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
        editor.putString("QR_SCANNED", "FINISHED");
        editor.apply();
        Intent inhome=new Intent(getApplicationContext(), Activity_HomePage.class);
        startActivity(inhome);
        finish();

      }else {

        Toast toast = Toast.makeText(getApplicationContext(),"QR Not match", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
      }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {

        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
        editor.putString("QR_SCANNED", "NOT_FINISHED");
        editor.apply();
        Intent inhome = new Intent(getApplicationContext(), Activity_HomePage.class);
        inhome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(inhome);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        auth_token = prefsnew.getString("auth_token", "");
        SessionID = prefsnew.getString("UserID", "");
        SignedIn = prefsnew.getString("SignedIn", "");
        ADS_TYPE = prefsnew.getString("ADS_TYPE", "");
//        AdsID = prefsnew.getString("AdsID", "");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != MY_PERMISSION_REQUEST_CAMERA) {
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Snackbar.make(mainLayout, "Camera permission was granted...", Snackbar.LENGTH_SHORT).show();
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(false);
            integrator.setCaptureActivity(CameraCaptureActivity.class);
            integrator.initiateScan();
        } else {
            Snackbar.make(mainLayout, "Camera permission request was denied...", Snackbar.LENGTH_SHORT)
                    .show();
        }
    }
}
