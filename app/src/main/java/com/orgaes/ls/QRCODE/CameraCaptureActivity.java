package com.orgaes.ls.QRCODE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.WindowManager;
import android.widget.CheckBox;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.orgaes.ls.Activity_HomePage;
import com.orgaes.ls.R;

public class CameraCaptureActivity extends CaptureActivity {
    CheckBox flash_light;
    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.capture_small);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        flash_light = (CheckBox) findViewById(R.id.flash_light);
        DecoratedBarcodeView decoratedBarcodeView=(DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
        flash_light.setOnCheckedChangeListener((compoundButton, isChecked) -> {

            if (isChecked){
                decoratedBarcodeView.setTorchOn();
            }else {
                decoratedBarcodeView.setTorchOff();
            }


        });
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
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
}
