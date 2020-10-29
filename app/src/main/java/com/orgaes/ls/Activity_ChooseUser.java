package com.orgaes.ls;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.PUSHNOTIFICATION.MyFirebaseRegister;
import com.orgaes.ls.RETROFIT_NEW.UserCheck.UserCheck;
import com.orgaes.ls.RETROFIT_NEW.UserCheck.UserCheckResponse;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_ChooseUser extends AppCompatActivity {

    Button btn_Signin;
    EditText edt_Phone, edt_Otp;
    ConstraintLayout constr_ScanLuck;
    String str_Userid, str_MainOtp,str_IMEINumber;
    String AuthKey,UserType,UserID,UserName,UserImage,UserPhone;
    ProgressBar progressbar;
    TextView txt_Signin, txt_Register,PinchZoom_Txt;
    ImageView PinchZoom;
    ConstraintLayout PinchZoom_Lay;
    boolean shown=false;
    //Firebase auth
    private FirebaseAuth firebase_Auth;
    CountryCodePicker countryCodePicker;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in=new Intent(getApplicationContext(), Activity_Choose_Language.class);
        startActivity(in);
        finish();
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        /*
         * changing the color of footbar
         * */
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        /*
         * screen launch animation
         * */
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        /*
         * Fullscreen mode enabling
         * */
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_sign_in);

        initFunction();
        fontSetting();

        constr_ScanLuck.setEnabled(false);
        edt_Otp.setVisibility(View.GONE);
        txt_Register.setVisibility(View.GONE);
        constr_ScanLuck.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.btn_click);
            mp.start();
            Intent in=new Intent(getApplicationContext(), Activity_HomePage.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);
        });

        txt_Register.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.btn_click);
            mp.start();
            sendverificationcode(AuthKey,countryCodePicker.getSelectedCountryCodeWithPlus()+ edt_Phone.getText().toString());
        });

        edt_Otp.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (btn_Signin.getText().toString().equals("VERIFY OTP")) {
                        if (edt_Otp.getText().toString().equals("")){
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Enter OTP to continue...", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 50);
                            toast.show();
                        }else {
                            VerifyCode(edt_Otp.getText().toString());
                        }
                    } else {
                        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
                        str_IMEINumber= prefsnew.getString("IMEINumber", "");
                        System.out.println("LS NUMBER   "+str_IMEINumber);
                        CheckUser(str_IMEINumber);
                    }
                    handled = true;
                }
                return handled;
            }
        });

        edt_Phone.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                if (edt_Phone.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please enter your mobile number", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                } else {
                    if (btn_Signin.getText().toString().equals("VERIFY OTP")) {
                        if (edt_Otp.getText().toString().equals("")){
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Enter OTP to continue...", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 50);
                            toast.show();
                        }else {
                            VerifyCode(edt_Otp.getText().toString());
                        }
                    } else {
                        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
                        str_IMEINumber= prefsnew.getString("IMEINumber", "");
                        System.out.println("LS NUMBER   "+str_IMEINumber);
                        CheckUser(str_IMEINumber);
                    }
                }
                handled = true;
            }
            return handled;
        });
        btn_Signin.setElevation(50);
        btn_Signin.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                    v.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    v.getBackground().clearColorFilter();
                    v.invalidate();
                    break;
                }
            }
            return false;
        });

        btn_Signin.setOnClickListener(v -> {

            final MediaPlayer mp = MediaPlayer.create(this, R.raw.btn_click);
            mp.start();
            if (edt_Phone.getText().toString().equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Please enter your mobile number", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();
            } else {
                if (btn_Signin.getText().toString().equals("VERIFY OTP")) {
                    if (edt_Otp.getText().toString().equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Enter OTP to continue...", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }else {
                        VerifyCode(edt_Otp.getText().toString());
                    }
                } else {
                    SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
                    str_IMEINumber= prefsnew.getString("IMEINumber", "");
                    System.out.println("LS NUMBER   "+str_IMEINumber);
                    CheckUser(str_IMEINumber);
                }
            }
        });
    }

    private void fontSetting() {
        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "Dosis-Medium.ttf");
        Typeface avner = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "AvenirLTStd-Light.otf");
        txt_Signin.setTypeface(face);
        edt_Phone.setTypeface(avner);
        edt_Otp.setTypeface(avner);
        PinchZoom_Txt.setTypeface(face);
    }

    @SuppressLint("SetTextI18n")
    private void initFunction() {
        firebase_Auth = FirebaseAuth.getInstance();
        btn_Signin = findViewById(R.id.signin);
        edt_Phone = findViewById(R.id.phone_edt);
        edt_Otp = findViewById(R.id.name_edt);
        constr_ScanLuck = findViewById(R.id.scan_luck);
        txt_Register = findViewById(R.id.retry);
        progressbar = findViewById(R.id.progressbar);
        txt_Signin = findViewById(R.id.textView28);
        PinchZoom_Txt = findViewById(R.id.pinch_zoom_txt);
        countryCodePicker=findViewById(R.id.ccp);
        btn_Signin.setText(getString(R.string.req_otp));
        PinchZoom=findViewById(R.id.zoom_image);
        PinchZoom_Lay=findViewById(R.id.coach_marks);
        PinchZoom_Lay.setVisibility(View.VISIBLE);
        Glide.with(getApplicationContext()).load(R.drawable.pinch_zoom).into(PinchZoom);
        new Handler().postDelayed(() -> {

            PinchZoom_Lay.setVisibility(View.GONE);

        },4000);
        @SuppressLint("CutPasteId") View layout = findViewById(R.id.signin);
//
//        GradientDrawable gd = new GradientDrawable(
//                GradientDrawable.Orientation.TOP_BOTTOM,
//                new int[] {0xFF616261,0xFF131313});
//        gd.setCornerRadius(10f);
//        gd.setStroke(1,0xFF5733);
//
//        layout.setBackgroundDrawable(gd);
        txt_Signin.setText(getString(R.string.sign_in));
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_ChooseUser.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(v -> {
//                        Toast.makeText(Activity_ChooseUser.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                })
                .longPressListener(v -> {
//                        Toast.makeText(Activity_ChooseUser.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                }).doubleTapListener(v -> {
//                        Toast.makeText(Activity_ChooseUser.this,"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                });

        builder.register();

    }
    /*
     * phone number authentication with firebase
     * */
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            str_MainOtp =s;
            btn_Signin.setText(getString(R.string.verify_otp));
            edt_Otp.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
            editor.putString("UserID", str_Userid);
            editor.apply();
            Toast toast= Toast.makeText(getApplicationContext(),
                    "Enter otp to continue", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
            toast.show();
            progressbar.setVisibility(View.GONE);
        }

        @Override
        public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
            txt_Register.setVisibility(View.VISIBLE);
            super.onCodeAutoRetrievalTimeOut(s);
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            shown=true;
            new SweetAlertDialog(Activity_ChooseUser.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Login Success")
                    .setContentText("Go to home page")
                    .setConfirmText("HOME")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            shown=false;
                            if (UserType.equals("guest_user")){
                                sDialog.dismiss();
                                SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                                editor.putString("SignedIn", "2");
                                editor.putString("auth_token", AuthKey);
                                editor.putString("UserID", UserID);
                                editor.putString("Username", UserName);
                                editor.putString("UserPhone",UserPhone );
                                editor.putString("Userpic", UserImage);
                                editor.apply();
                                Intent in=new Intent(getApplicationContext(),Activity_GuestUserMenu.class);
                                startActivity(in);
                                finish();

                                Toast toast= Toast.makeText(getApplicationContext(),
                                        "Successfully Verified...", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
                                toast.show();
                            }else if (UserType.equals("registered_user")){
                                sDialog.dismiss();
                                SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                                editor.putString("SignedIn", "1");
                                editor.putString("auth_token", AuthKey);
                                editor.putString("UserID", UserID);
                                editor.putString("Username", UserName);
                                editor.putString("UserPhone",UserPhone );
                                editor.putString("Userpic", UserImage);
                                editor.apply();
                                MyFirebaseRegister myFirebaseRegister=new MyFirebaseRegister(Activity_ChooseUser.this);
                                myFirebaseRegister.RegisterUser(UserID);
                                Intent in=new Intent(getApplicationContext(),Activity_HomePage.class);
                                startActivity(in);
                                finish();

                                Toast toast= Toast.makeText(getApplicationContext(),
                                        "Successfully Verified...", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
                                toast.show();
                            }else {
                                sDialog.dismiss();
                                SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                                editor.putString("SignedIn", "1");
                                editor.putString("auth_token", AuthKey);
                                editor.putString("UserID", UserID);
                                editor.putString("Username", UserName);
                                editor.putString("UserPhone",UserPhone );
                                editor.putString("Userpic", UserImage);
                                editor.apply();
                                Intent in=new Intent(getApplicationContext(),Activity_HomePage.class);
                                startActivity(in);
                                finish();

                                Toast toast= Toast.makeText(getApplicationContext(),
                                        "Successfully Verified...", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
                                toast.show();
                            }
                        }
                    })
                    .show();

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            txt_Register.setVisibility(View.VISIBLE);
            Toast toast = Toast.makeText(getApplicationContext(),""+e.getMessage(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    };
    /*
     * verification code sent to the mobile number
     * */
    private void sendverificationcode(String authKey, String phonenumber_) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phonenumber_, 60, TimeUnit.SECONDS, this, mCallbacks);
        progressbar.setVisibility(View.VISIBLE);

    }
    /*
     * Entered otp verifying
     * */
    private void VerifyCode(String otpCode){
        progressbar.setVisibility(View.VISIBLE);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(str_MainOtp, otpCode);

        signInWithPhoneAuthCredential(credential);

    }
    /*
     * phone number sign in with credentials
     * */
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebase_Auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        progressbar.setVisibility(View.GONE);

                        shown=true;
                        new SweetAlertDialog(Activity_ChooseUser.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Login Success")
                                .setContentText("Go to home page")
                                .setConfirmText("HOME")

                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        shown=false;
                                        if (UserType.equals("guest_user")){
                                            sDialog.dismiss();
                                            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                                            editor.putString("SignedIn", "2");
                                            editor.putString("auth_token", AuthKey);
                                            editor.putString("UserID", UserID);
                                            editor.putString("Username", UserName);
                                            editor.putString("UserPhone",UserPhone );
                                            editor.putString("Userpic", UserImage);
                                            editor.apply();
                                            Intent in=new Intent(getApplicationContext(),Activity_GuestUserMenu.class);
                                            startActivity(in);
                                            finish();

                                            Toast toast= Toast.makeText(getApplicationContext(),
                                                    "Successfully Verified...", Toast.LENGTH_SHORT);
                                            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
                                            toast.show();
                                        }else if (UserType.equals("registered_user")){
                                            sDialog.dismiss();
                                            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                                            editor.putString("SignedIn", "1");
                                            editor.putString("auth_token", AuthKey);
                                            editor.putString("UserID", UserID);
                                            editor.putString("Username", UserName);
                                            editor.putString("UserPhone",UserPhone );
                                            editor.putString("Userpic", UserImage);
                                            editor.apply();
                                            MyFirebaseRegister myFirebaseRegister=new MyFirebaseRegister(Activity_ChooseUser.this);
                                            myFirebaseRegister.RegisterUser(UserID);
                                            Intent in=new Intent(getApplicationContext(),Activity_HomePage.class);
                                            startActivity(in);
                                            finish();

                                            Toast toast= Toast.makeText(getApplicationContext(),
                                                    "Successfully Verified...", Toast.LENGTH_SHORT);
                                            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
                                            toast.show();
                                        }else {
                                            sDialog.dismiss();
                                            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                                            editor.putString("SignedIn", "1");
                                            editor.putString("auth_token", AuthKey);
                                            editor.putString("UserID", UserID);
                                            editor.putString("Username", UserName);
                                            editor.putString("UserPhone",UserPhone );
                                            editor.putString("Userpic", UserImage);
                                            editor.apply();
                                            Intent in=new Intent(getApplicationContext(),Activity_HomePage.class);
                                            startActivity(in);
                                            finish();

                                            Toast toast= Toast.makeText(getApplicationContext(),
                                                    "Successfully Verified...", Toast.LENGTH_SHORT);
                                            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
                                            toast.show();
                                        }
                                    }
                                })
                                .show();


                        // Sign in success, update UI with the signed-in user's information

                    } else {
                        // Sign in failed, display a message and update the UI
                        task.getException();// The verification code entered was invalid
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Wrong OTP", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }
                });
    }


    private void CheckUser(String IMEINumber)
    {
        System.out.println("LS CHECK USER  "+IMEINumber+"    "+countryCodePicker.getSelectedCountryCode().
                replace("+","")+edt_Phone.getText().toString());
        progressbar.setVisibility(View.VISIBLE);
        APIInterface interfaceApi = APIClient.getClient().create(APIInterface.class);
        Call<UserCheck> call3 = interfaceApi.Api_User_Check(countryCodePicker.getSelectedCountryCode().
                replace("+","")+edt_Phone.getText().toString(), IMEINumber);
        call3.enqueue(new Callback<UserCheck>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<UserCheck> call, @NotNull Response<UserCheck> response) {

                try {
                    progressbar.setVisibility(View.GONE);
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        UserCheckResponse response1=response.body().getResponse();
                        AuthKey=response1.getAuthKey();
                        UserType=response1.getUserType();
                        Constants.Const_UserCoins= Integer.parseInt(response1.getUsercoin());
                        for (int i=0;i<response1.getUserprofile().size();i++){

                            UserID=response1.getUserprofile().get(i).getId();
                            UserImage=response1.getUserprofile().get(i).getPhotoUrl();
                            UserName=response1.getUserprofile().get(i).getName();
                            UserPhone=response1.getUserprofile().get(i).getMobile();
                            System.out.println("LS PHONE NUMBER  "+UserPhone);
                        }
                        btn_Signin.setText(getString(R.string.verify_otp));
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Enter otp to continue "+UserType, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                        sendverificationcode(AuthKey,countryCodePicker.getSelectedCountryCodeWithPlus()+ edt_Phone.getText().toString());
                    }else if (Code==201){
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Already Registered", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }else if (Code==203){
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Error", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }else if (Code==207){
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Validation Error", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            @Override
            public void onFailure(@NotNull Call<UserCheck> call, @NotNull Throwable t) {
                progressbar.setVisibility(View.GONE);
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                t.printStackTrace();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}

