package com.orgaes.ls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.RETROFIT_NEW.PrivacyUpdate.PrivacyModule;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Privacy_Settings extends AppCompatActivity {

    ConstraintLayout Menu_icon;
    ProgressBar progressBar;
    Button Update;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch PhotoSwitch,ChatSwitch,phoneSwitch,CoinHand_Switch,ActiveLuck_Switch,Voucherhand_Switch,Wallet_Switch,LuckWin_Switch,TotalShareCoins_Switch, TotalShare_Luck_Switch, Total_Chunq_Switch, Total_Exchange_Switch;
    String S_PhotoSwitch,S_ChatSwitch,S_phoneSwitch,S_CoinHand_Switch,S_ActiveLuck_Switch,S_Voucherhand_Switch,S_Wallet_Switch,S_LuckWin_Switch,S_TotalShareCoins_Switch, S_TotalShare_Luck_Switch, S_Total_Chunq_Switch, S_Total_Exchange_Switch;
    TextView Photo,Phone,Coin,Active,Voucher,Wallet,Total_Luck,TotalSharesCoins,Total_SharesLuck,TotalChunq,TotalExchange,Chat;
    EditText status;
    String StatusData;
    ImageView LuckRadarImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_privacy_settings);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Privacy_Settings.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Privacy_Settings.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_Privacy_Settings.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Privacy_Settings.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        phoneSwitch=findViewById(R.id.switch_phone);
        PhotoSwitch=findViewById(R.id.photo_switch);
        Photo=findViewById(R.id.qtxt1_photo_privacy);
        CoinHand_Switch=findViewById(R.id.switch_coins_hand);
        ActiveLuck_Switch=findViewById(R.id.switch_active_luck);
        Voucherhand_Switch=findViewById(R.id.switch_voucher);
        Wallet_Switch=findViewById(R.id.switch_walletmoney);
        LuckWin_Switch=findViewById(R.id.switch_total_luck);
        TotalShareCoins_Switch=findViewById(R.id.switch_sharecoins);
        TotalShare_Luck_Switch =findViewById(R.id.switch_shareluck);
        Total_Chunq_Switch =findViewById(R.id.switch_chunkz);
        Total_Exchange_Switch =findViewById(R.id.switch_exchange);
        progressBar=findViewById(R.id.progressbar);
        ChatSwitch=findViewById(R.id.chat_switch);
        Update=findViewById(R.id.update);
        Menu_icon=findViewById(R.id.menu_icon);
        status=findViewById(R.id.status);
        progressBar.setVisibility(View.GONE);

        Phone=findViewById(R.id.txt1_phone);
        Coin=findViewById(R.id.txt1_coinshand);
        Active=findViewById(R.id.txt1_active_luck);
        Voucher=findViewById(R.id.vtxt1_voucher_hand);
        Wallet=findViewById(R.id.txt1_wallet_money);
        Total_Luck=findViewById(R.id.txt1_total_luck_win);
        TotalSharesCoins=findViewById(R.id.txt1_shares_of_coin);
        Total_SharesLuck=findViewById(R.id.txt1_sharesof_luck);
        TotalChunq=findViewById(R.id.txt1__chunks);
        TotalExchange=findViewById(R.id.txt1_total_exchanges);
        Chat=findViewById(R.id.txt1_chat_privacy);
        LuckRadarImage=findViewById(R.id.luckradar);
        LuckRadarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),Activity_LuckRadar.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
            }
        });

        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        Phone.setTypeface(dosis);
        Coin.setTypeface(dosis);
        Active.setTypeface(dosis);
        Voucher.setTypeface(dosis);
        Wallet.setTypeface(dosis);
        Total_Luck.setTypeface(dosis);
        TotalSharesCoins.setTypeface(dosis);
        Total_SharesLuck.setTypeface(dosis);
        TotalChunq.setTypeface(dosis);
        TotalExchange.setTypeface(dosis);
        Chat.setTypeface(dosis);
        status.setTypeface(dosis);
        Photo.setTypeface(dosis);

        Menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                //overridePendingTransition(R.anim.left_right, R.anim.right_left);
            }
        });
        ImageView Scan_Luck=findViewById(R.id.scan_luck);
        Scan_Luck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inhome=new Intent(getApplicationContext(), Activity_HomePage.class);
                startActivity(inhome);
                finish();
            }
        });

        status.setText(Constants.Const_Profile_Data.get(0).getSelfInfo());

        if (Constants.Const_Profile_Data.get(0).getPhone().equals("1")){
            phoneSwitch.setChecked(true);
            S_phoneSwitch="1";
        }else{
            S_phoneSwitch="0";
        } if (Constants.Const_Profile_Data.get(0).getActiveLuck().equals("1")){
            ActiveLuck_Switch.setChecked(true);
            S_ActiveLuck_Switch="1";
        }else{
            S_ActiveLuck_Switch="0";
        } if (Constants.Const_Profile_Data.get(0).getCoin().equals("1")){
            CoinHand_Switch.setChecked(true);
            S_CoinHand_Switch="1";
        }else{
            S_CoinHand_Switch="0";
        } if (Constants.Const_Profile_Data.get(0).getVoucher().equals("1")){
            Voucherhand_Switch.setChecked(true);
            S_Voucherhand_Switch="1";
        }else{
            S_Voucherhand_Switch="0";
        } if (Constants.Const_Profile_Data.get(0).getWallet().equals("1")){
            Wallet_Switch.setChecked(true);
            S_Wallet_Switch="1";
        }else{
            S_Wallet_Switch="0";
        } if (Constants.Const_Profile_Data.get(0).getTotalLuck().equals("1")){
            LuckWin_Switch.setChecked(true);
            S_LuckWin_Switch="1";
        }else{
            S_LuckWin_Switch="0";
        } if (Constants.Const_Profile_Data.get(0).getShareCoin().equals("1")){
            TotalShareCoins_Switch.setChecked(true);
            S_TotalShareCoins_Switch="1";
        }else{
            S_TotalShareCoins_Switch="0";
        } if (Constants.Const_Profile_Data.get(0).getShareLuck().equals("1")){
            TotalShare_Luck_Switch.setChecked(true);
            S_TotalShare_Luck_Switch="1";
        }else{
            S_TotalShare_Luck_Switch="0";
        } if (Constants.Const_Profile_Data.get(0).getFriends().equals("1")){
            Total_Chunq_Switch.setChecked(true);
            S_Total_Chunq_Switch="1";
        }else{
            S_Total_Chunq_Switch="0";
        } if (Constants.Const_Profile_Data.get(0).getExchanges().equals("1")){
            Total_Exchange_Switch.setChecked(true);
            S_Total_Exchange_Switch="1";
        }else{
            S_Total_Exchange_Switch="0";
        }if (Constants.Const_Profile_Data.get(0).getChat().equals("1")){
            ChatSwitch.setChecked(true);
            S_ChatSwitch="1";
        }else{
            S_ChatSwitch="0";
        }if (Constants.Const_Profile_Data.get(0).getPhoto().equals("1")){
            PhotoSwitch.setChecked(true);
            S_PhotoSwitch="1";
        }else{
            S_PhotoSwitch="0";
        }
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        String auth_token= prefsnew.getString("auth_token", "");
        Update.setOnClickListener(v -> UPDATE_PRIVACY(auth_token));

        phoneSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_phoneSwitch="1";
            }else {
                S_phoneSwitch="0";
            }
        });
        PhotoSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_PhotoSwitch="1";
            }else {
                S_PhotoSwitch="0";
            }
        });
        CoinHand_Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_CoinHand_Switch="1";
            }else {
                S_CoinHand_Switch="0";
            }
        });
        ActiveLuck_Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_ActiveLuck_Switch="1";
            }else {
                S_ActiveLuck_Switch="0";
            }
        });
        Voucherhand_Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_Voucherhand_Switch="1";
            }else {
                S_Voucherhand_Switch="0";
            }
        });
        Wallet_Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_Wallet_Switch="1";
            }else {
                S_Wallet_Switch="0";
            }
        });
        LuckWin_Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_LuckWin_Switch="1";
            }else {
                S_LuckWin_Switch="0";
            }
        });
        TotalShareCoins_Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_TotalShareCoins_Switch="1";
            }else {
                S_TotalShareCoins_Switch="0";
            }
        });
        TotalShare_Luck_Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_TotalShare_Luck_Switch="1";
            }else {
                S_TotalShare_Luck_Switch="0";
            }
        });
        Total_Chunq_Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_Total_Chunq_Switch="1";
            }else {
                S_Total_Chunq_Switch="0";
            }
        });
        Total_Exchange_Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_Total_Exchange_Switch="1";
            }else {
                S_Total_Exchange_Switch="0";
            }
        });
        ChatSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                S_ChatSwitch="1";
            }else {
                S_ChatSwitch="0";
            }
        });
    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
        editor.putString("from_wallet", "0");
            editor.apply();
        Intent in=new Intent(getApplicationContext(),Activity_ProfileView.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }

    private void UPDATE_PRIVACY(String token) {
        System.out.println("LS AUTH CHECK   "+token+"    "+status.getText().toString());

        if (status.getText().toString().equals("")){
            StatusData="Lucky scan User";
        }else {
            StatusData=status.getText().toString();
        }
        progressBar.setVisibility(View.VISIBLE);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PrivacyModule> call3 = apiInterface.Api_Update_Privacy(token,
                StatusData,S_phoneSwitch,S_CoinHand_Switch,S_ActiveLuck_Switch,S_LuckWin_Switch,S_Voucherhand_Switch,S_Wallet_Switch,
                S_TotalShareCoins_Switch,S_Total_Chunq_Switch,S_Total_Exchange_Switch,S_TotalShare_Luck_Switch,S_ChatSwitch,S_PhotoSwitch);
        call3.enqueue(new Callback<PrivacyModule>() {
            @Override
            public void onResponse(@NotNull Call<PrivacyModule> call, @NotNull Response<PrivacyModule> response) {
                progressBar.setVisibility(View.GONE);
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();

                    if (Code==200){
                        if (S_phoneSwitch.equals("1")){
                            phoneSwitch.setChecked(true);
                            S_phoneSwitch="1";
                        }else{
                            S_phoneSwitch="0";
                        } if (S_ActiveLuck_Switch.equals("1")){
                            ActiveLuck_Switch.setChecked(true);
                            S_ActiveLuck_Switch="1";
                        }else{
                            S_ActiveLuck_Switch="0";
                        } if (S_CoinHand_Switch.equals("1")){
                            CoinHand_Switch.setChecked(true);
                            S_CoinHand_Switch="1";
                        }else{
                            S_CoinHand_Switch="0";
                        } if (S_Voucherhand_Switch.equals("1")){
                            Voucherhand_Switch.setChecked(true);
                            S_Voucherhand_Switch="1";
                        }else{
                            S_Voucherhand_Switch="0";
                        } if (S_Wallet_Switch.equals("1")){
                            Wallet_Switch.setChecked(true);
                            S_Wallet_Switch="1";
                        }else{
                            S_Wallet_Switch="0";
                        } if (S_LuckWin_Switch.equals("1")){
                            LuckWin_Switch.setChecked(true);
                            S_LuckWin_Switch="1";
                        }else{
                            S_LuckWin_Switch="0";
                        } if (S_TotalShareCoins_Switch.equals("1")){
                            TotalShareCoins_Switch.setChecked(true);
                            S_TotalShareCoins_Switch="1";
                        }else{
                            S_TotalShareCoins_Switch="0";
                        } if (S_TotalShare_Luck_Switch.equals("1")){
                            TotalShare_Luck_Switch.setChecked(true);
                            S_TotalShare_Luck_Switch="1";
                        }else{
                            S_TotalShare_Luck_Switch="0";
                        } if (S_Total_Chunq_Switch.equals("1")){
                            Total_Chunq_Switch.setChecked(true);
                            S_Total_Chunq_Switch="1";
                        }else{
                            S_Total_Chunq_Switch="0";
                        } if (S_Total_Exchange_Switch.equals("1")){
                            Total_Exchange_Switch.setChecked(true);
                            S_Total_Exchange_Switch="1";
                        }else{
                            S_Total_Exchange_Switch="0";
                        }if (S_ChatSwitch.equals("1")){
                            ChatSwitch.setChecked(true);
                            S_ChatSwitch="1";
                        }else{
                            S_ChatSwitch="0";
                        }if (S_PhotoSwitch.equals("1")){
                            PhotoSwitch.setChecked(true);
                            S_PhotoSwitch="1";
                        }else{
                            S_PhotoSwitch="0";
                        }

                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Successfully Updated", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
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
            public void onFailure(Call<PrivacyModule> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();


    }


}
