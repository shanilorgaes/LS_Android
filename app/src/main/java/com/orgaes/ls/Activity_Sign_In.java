package com.orgaes.ls;

import android.annotation.SuppressLint;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.RETROFIT_NEW.SignIn.Post_SignIn;
import com.orgaes.ls.RETROFIT_NEW.SignIn.Signin_Response;

import org.jetbrains.annotations.NotNull;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Purpose – Activity_Sign_In is an activity which the registered user can sign in here using OTP
 * @author SHANIL
 * Created on 21-5-2020
 */
public class  Activity_Sign_In extends AppCompatActivity {

    Button btn_Signin;
    EditText edt_Phone, edt_Otp;
    ConstraintLayout constr_ScanLuck;
    String str_Userid, str_OtpResponse, str_MainOtp,str_IMEINumber;
    ProgressBar progressbar;
    APIInterface inter_Apiinterface;
    TextView txt_Signin, txt_Register;
    //Firebase auth
    private FirebaseAuth firebase_Auth;
    CountryCodePicker countryCodePicker;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
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
            /*Intent in=new Intent(getApplicationContext(), Activity_HomePage.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);*/
        });

        txt_Register.setOnClickListener(v -> {
            Intent in=new Intent(getApplicationContext(),Activity_Register.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });

        btn_Signin.setOnClickListener(v -> {
            if (edt_Phone.getText().toString().equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Please fill the fields to continue...", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();
            } else {
                if (btn_Signin.getText().toString().equals("VERIFY OTP")) {
                    if (edt_Otp.getText().toString().equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Enter OTP to continue...", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }else {
                        VerifyCode(edt_Otp.getText().toString());
                    }
                } else {
                    SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
                    str_IMEINumber= prefsnew.getString("IMEINumber", "");
                    System.out.println("LS NUMBER   "+str_IMEINumber);
                    userLogin(str_IMEINumber);
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
    }

    @SuppressLint("SetTextI18n")
    private void initFunction() {
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Sign_In.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Sign_In.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_Sign_In.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Sign_In.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        firebase_Auth = FirebaseAuth.getInstance();
        btn_Signin = findViewById(R.id.signin);
        edt_Phone = findViewById(R.id.phone_edt);
        edt_Otp = findViewById(R.id.name_edt);
        constr_ScanLuck = findViewById(R.id.scan_luck);
        txt_Register = findViewById(R.id.retry);
        progressbar = findViewById(R.id.progressbar);
        txt_Signin = findViewById(R.id.textView28);
        countryCodePicker=findViewById(R.id.ccp);
        btn_Signin.setText(getString(R.string.send_otp));

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
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
            toast.show();
            progressbar.setVisibility(View.GONE);
        }

        @Override
        public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
            super.onCodeAutoRetrievalTimeOut(s);
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            new SweetAlertDialog(Activity_Sign_In.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Login Success")
                    .setContentText("Go to home page")
                    .setConfirmText("HOME")

                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                            editor.putString("SignedIn", "1");
                            editor.apply();
                            Intent in=new Intent(getApplicationContext(),Activity_HomePage.class);
                            startActivity(in);
                            finish();

                            Toast toast= Toast.makeText(getApplicationContext(),
                                    "Successfully Verified...", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                            toast.show();
                        }
                    })
                    .show();

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast toast = Toast.makeText(getApplicationContext(),"Verification Failed...."+e.getMessage(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    };
    /*
    * verification code sent to the mobile number
    * */
    private void sendverificationcode(String phonenumber_) {
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
                        // Sign in success, update UI with the signed-in user's information

                        new SweetAlertDialog(Activity_Sign_In.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Login Success")
                                .setContentText("Go to home page")
                                .setConfirmText("HOME")

                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {

                                        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                                        editor.putString("SignedIn", "1");
                                        editor.apply();
                                        Intent in=new Intent(getApplicationContext(),Activity_HomePage.class);
                                        startActivity(in);
                                        finish();

                                        Toast toast= Toast.makeText(getApplicationContext(),
                                                "Successfully Verified...", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                                        toast.show();
                                    }
                                })
                                .show();
                    } else {
                        // Sign in failed, display a message and update the UI
                        task.getException();// The verification code entered was invalid
                    }
                });
    }

    /*
    * User login api call function
    * parameteres :  phone number , user type
    * */
    private void userLogin(String IMEINumber) {
        System.out.println("LS IMEI number  "+ IMEINumber+"      "+countryCodePicker.getSelectedCountryCode().replace("+","")+edt_Phone.getText().toString());
        progressbar.setVisibility(View.VISIBLE);
        inter_Apiinterface = APIClient.getClient().create(APIInterface.class);
        Call<Post_SignIn> call3 = inter_Apiinterface.Api_SignIn( countryCodePicker.getSelectedCountryCode().replace
                ("+","")+edt_Phone.getText().toString(), IMEINumber);
        call3.enqueue(new Callback<Post_SignIn>() {
            @Override
            public void onResponse(@NotNull Call<Post_SignIn> call, @NotNull Response<Post_SignIn> response) {
                progressbar.setVisibility(View.GONE);
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    Signin_Response response1 = response.body().getResponse();
                    str_Userid = response1.getUserId();
                    System.out.println("LS LOGIN TEST   "+Code);
                    if (Code==200){
                        /*
                        * if success the user will get an OTP from sendverificationcode function
                        * */
                        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                        editor.putString("auth_token", response1.getAuthKey());
                        editor.apply();
                        String code_Picker = countryCodePicker.getSelectedCountryCodeWithPlus();
                        sendverificationcode(code_Picker+ edt_Phone.getText().toString());
                    }else if (Code==201){
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Not a Registered Member", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                        txt_Register.setVisibility(View.VISIBLE);
                    }else if (Code==207){
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Oh ho ! Authentication Failed", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                        txt_Register.setVisibility(View.VISIBLE);
                    }else if (Code==203){
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Sorry This Device is Not Authenticated With This Number", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                        txt_Register.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            @Override
            public void onFailure(@NotNull Call<Post_SignIn> call, @NotNull Throwable t) {
                progressbar.setVisibility(View.GONE);
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

