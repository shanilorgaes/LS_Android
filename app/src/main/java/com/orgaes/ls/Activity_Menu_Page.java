package com.orgaes.ls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.RETROFIT_NEW.VIEW.ProfDATA;
import com.orgaes.ls.RETROFIT_NEW.VIEW.Prof_Response;
import com.orgaes.ls.RETROFIT_NEW.VIEW.Prof_UserProfile;
import com.orgaes.ls.RETROFIT_NEW.VIEW.Prof_UserWishlist;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class Activity_Menu_Page extends AppCompatActivity {


    ImageView constr_Leaderboard, constr_Profile, constr_WishlistMaster,constr_coinbox, constr_LuckBox, constr_Logout;
    Button buttons_continue;
    ConstraintLayout mainConstraints;
    ImageView img_Profile, imgAboutus, img_Help;
    TextView txt_Username, txt_UserId;
    String str_UserID, Auth_Token,str_SignedIn, str_Userid, str_Username, str_Userimage, str_QRscanned;
    ProgressBar progressBar;
    public static final String TAG = Activity_Menu_Page.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        /*
         * Changing the color of footbar
         * */
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        /*
         * Fullscreen in portrait mode
         * */
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_menunew);

        bindControlls();
        fontStyles();
    }

    private void fontStyles() {
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        txt_Username.setTypeface(dosis);
        txt_UserId.setTypeface(dosis);




    }

    private void bindControlls() {

        mainConstraints = findViewById(R.id.constraintLayout72);
        constr_Leaderboard = findViewById(R.id.leaderboard_lay);
        constr_Profile = findViewById(R.id.profilelay);
        constr_WishlistMaster = findViewById(R.id.wishlistmaster);
        constr_coinbox = findViewById(R.id.constr_coinbox);
        constr_LuckBox = findViewById(R.id.luckbox_lay);
        constr_Logout = findViewById(R.id.logout_lay);
        img_Profile = findViewById(R.id.profile_pic);
        txt_Username = findViewById(R.id.username);
        txt_UserId = findViewById(R.id.user_id);
        imgAboutus = findViewById(R.id.aboutus);
        img_Help = findViewById(R.id.help);
        buttons_continue = findViewById(R.id.buttons_continue);

        ImageView Scan_Luck = findViewById(R.id.scan_luck);
        progressBar=findViewById(R.id.progressbar);

        Scan_Luck.setOnClickListener(v -> {
            Intent inhome = new Intent(getApplicationContext(), Activity_HomePage.class);
            startActivity(inhome);
            finish();
        });
        buttons_continue.setOnClickListener(v -> {
            Intent inhome = new Intent(getApplicationContext(), Activity_HomePage.class);
            startActivity(inhome);
            finish();
        });
        constr_Leaderboard.setOnClickListener(v -> {
            System.out.println("LS ZOOM ITEM CLICK   ");
            if (str_SignedIn.equals("1")) {
                Intent in = new Intent(getApplicationContext(), Activity_LeaderBoard.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "You are not signed in", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        constr_Profile.setOnClickListener(v -> {
            if (str_SignedIn.equals("1")) {
                SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                editor.putString("from_wallet", "0");
            editor.apply();
                Intent in = new Intent(getApplicationContext(), Activity_ProfileView.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "You are not signed in", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        constr_WishlistMaster.setOnClickListener(v -> {

            if (str_SignedIn.equals("1")) {
                Intent in = new Intent(getApplicationContext(), Activity_Wishlist_Master.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "You are not signed in", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        constr_LuckBox.setOnClickListener(v -> {
            if (str_SignedIn.equals("1")) {
                Intent in = new Intent(getApplicationContext(), Activity_LuckBox.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "You are not signed in", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        constr_Logout.setOnClickListener(v -> {
            if (str_SignedIn.equals("1")) {
                SharedPreferences settings = getSharedPreferences("LS", Context.MODE_PRIVATE);
                settings.edit().remove("Lang_Selected").apply();
                settings.edit().clear().apply();
                Intent intlogout = new Intent(getApplicationContext(), Activity_Choose_Language.class);
                intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intlogout);
            }
        });

        imgAboutus.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), Activity_AboutUS.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);
        });
        img_Help.setOnClickListener(v -> {

        });
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Menu_Page.this)
                .target(mainConstraints)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Menu_Page.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_Menu_Page.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Menu_Page.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        str_UserID = prefsnew.getString("UserID", "");
        str_SignedIn = prefsnew.getString("SignedIn", "");
        Auth_Token = prefsnew.getString("auth_token", "");
        str_Userid = prefsnew.getString("Userid", "");
        str_Userimage = prefsnew.getString("Userpic", "");
        str_Username = prefsnew.getString("Username", "");
        str_QRscanned = prefsnew.getString("QR_SCANNED", "");
        if (str_SignedIn.equals("2")){

        }else {
            Fetch_Profile(Auth_Token);
        }
        System.out.println("LS DATAS   " + str_UserID + "    " + str_Username + "    " + str_Userimage);

        /*
         * Changing the color of sign in and register buttons
         * */
        if (str_SignedIn.equals("1")) {

            txt_Username.setText("" + str_Username);
            txt_UserId.setText("LS ID : " + str_UserID);

            Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS + str_Userimage).into(img_Profile);
            img_Profile.setOnClickListener(v -> {
                SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                editor.putString("from_wallet", "0");
            editor.apply();
                Intent in = new Intent(getApplicationContext(), Activity_ProfileView.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
            });
        }
        /*
         * enabling and disabling the buttons on the basis of sign in
         * */
        if (str_SignedIn.equals("1")) {
            constr_Logout.setEnabled(true);
        } else if (str_SignedIn.equals("2")) {
            txt_Username.setText(getString(R.string.guest_user));
            txt_UserId.setText(getString(R.string.id)+getString(R.string.xxx));
            constr_Logout.setEnabled(false);
            img_Profile.setImageResource(R.drawable.no_photo);

        } else {
            constr_Logout.setEnabled(false);
        }

    }

    private void Fetch_Profile(final String token) {
        progressBar.setVisibility(View.VISIBLE);
        System.out.println("LS AUTHKEY  " + token);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ProfDATA> call3 = apiInterface.Fetch_Profile(token);
        call3.enqueue(new Callback<ProfDATA>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<ProfDATA> call, @NotNull retrofit2.Response<ProfDATA> response) {
                progressBar.setVisibility(View.GONE);
                try {
                    assert response.body() != null;
                    int Code = response.body().getCode();
                    if (Code == 200) {
                        Prof_Response response1 = response.body().getResponse();
                        String Coins = String.valueOf(response1.getUserCoins());
                        String ExpDate = response1.getWishlistExpiryDate();
                        List<Prof_UserProfile> userprofile = response1.getUserProfile();
                        List<Prof_UserWishlist> UserWishlist = response1.getUserWishlist();
                        System.out.println("LS PROFILE WISHLIST DATA   " + UserWishlist.size());

                        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                        editor.putString("SignedIn", "1");
                        editor.putString("auth_token", token);
                        editor.putString("UserID", userprofile.get(0).getId());
                        editor.putString("Username", userprofile.get(0).getName());
                        editor.putString("Userpic", userprofile.get(0).getPhotoUrl());
                        editor.apply();
                        String Token=token;
                        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
                        str_UserID = prefsnew.getString("UserID", "");
                        str_SignedIn = prefsnew.getString("SignedIn", "");
                        Token = prefsnew.getString("auth_token", "");
                        str_Userid = prefsnew.getString("Userid", "");
                        str_Userimage = prefsnew.getString("Userpic", "");
                        str_Username = prefsnew.getString("Username", "");
                        str_QRscanned = prefsnew.getString("QR_SCANNED", "");

                        /*
                         * Changing the color of sign in and register buttons
                         * */
                        if (str_SignedIn.equals("1")) {

                            txt_Username.setText(str_Username);
                            txt_UserId.setText("LS ID : " + str_UserID);
                            Glide.with(getApplicationContext()).load(AppConfig.URL_BASE_VIDEOS + str_Userimage).into(img_Profile);
                            img_Profile.setOnClickListener(v -> {
                                editor.putString("from_wallet", "0");
                                editor.apply();
                                Intent in = new Intent(getApplicationContext(), Activity_ProfileView.class);
                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(in);
                            });
                        }
                        /*
                         * enabling and disabling the buttons on the basis of sign in
                         * */
                        if (str_SignedIn.equals("1")) {
                            constr_Logout.setEnabled(true);
                        } else if (str_SignedIn.equals("2")) {
                            constr_Logout.setEnabled(false);
                        } else {
                            constr_Logout.setEnabled(false);
                        }
                    } else if (Code == 201) {

                        Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();

                    } else if (Code == 301) {

                        Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout = new Intent(getApplicationContext(), Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intlogout);
                    } else if (Code == 303) {
                        Toast.makeText(getApplicationContext(), "Session Expired", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout = new Intent(getApplicationContext(), Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intlogout);
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            @Override
            public void onFailure(Call<ProfDATA> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                call.cancel();
            }
        });
    }

}