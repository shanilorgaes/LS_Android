package com.orgaes.ls;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.InputFilter;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;
import com.google.firebase.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;import androidx.appcompat.app.AppCompatActivity;
import com.orgaes.ls.Adapter.FirenReqAdapter;
import com.orgaes.ls.Adapter.FriendslistAdapter;
import com.orgaes.ls.Adapter.WishList_Adapter2;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.RETROFIT_NEW.Fetch_All_Requests.FetchAllReqData;
import com.orgaes.ls.RETROFIT_NEW.Fetch_All_Requests.FetchAllReqData_Request;
import com.orgaes.ls.RETROFIT_NEW.Fetch_All_Requests.FetchAllReqData_Response;
import com.orgaes.ls.RETROFIT_NEW.FriendsList.FriendsList_Data;
import com.orgaes.ls.RETROFIT_NEW.FriendsList.FriendsList_Main;
import com.orgaes.ls.RETROFIT_NEW.FriendsList.Friendslist_Response;
import com.orgaes.ls.RETROFIT_NEW.Profile_Wishlist.Main_Profile_Wishlist;
import com.orgaes.ls.RETROFIT_NEW.Profile_Wishlist.Prof_Wishlist_Response;
import com.orgaes.ls.RETROFIT_NEW.Profile_Wishlist.WishlistData;
import com.orgaes.ls.RETROFIT_NEW.Update_Profile.Update_Prof;
import com.orgaes.ls.RETROFIT_NEW.VIEW.ProfDATA;
import com.orgaes.ls.RETROFIT_NEW.VIEW.Prof_Response;
import com.orgaes.ls.RETROFIT_NEW.VIEW.Prof_UserProfile;
import com.orgaes.ls.RETROFIT_NEW.VIEW.Prof_UserWishlist;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

/*
 * Purpose – Activity_ProfileView is an activity for User Profile details checking and update
 * User can check Friend requests,Friendslist,LuckBox,RadarBox,Footprints,Exchange Box,etc...
 * @author SHANIL
 * Created on 21-5-2020
 */
public class Activity_ProfileView extends AppCompatActivity {


    String SignedIn,type,userChoosenTask;

//    RecyclerView Recyclerview_Friends;
    int position1=0;
    int position2=0;
    RecyclerView recyclerView2;
    WishList_Adapter2 mAdapter;
//    ImageView LeftArrow,RightArrow;
    List<String>User_id=new ArrayList<>();
    List<String>User_wishlist_id=new ArrayList<>();
    List<String>User_name=new ArrayList<>();
    List<String>User_email=new ArrayList<>();
    List<String>User_phone=new ArrayList<>();
    List<String>User_gender=new ArrayList<>();
    List<String>User_photo=new ArrayList<>();
    List<String>User_Privacy=new ArrayList<>();
    List<String>wishlist_trans_name=new ArrayList<>();
    List<String>wishlist_trans_id=new ArrayList<>();

    List<String>wishlist_id=new ArrayList<>();
    List<String>wishlist_trans_master_id=new ArrayList<>();
    List<String>wishlist_trans_language_code=new ArrayList<>();
    List<String>wishlistname=new ArrayList<>();
    String WishDataString;
    String UserID;
    String auth_token;
    String listString = "";
    static Bitmap bmp;
    APIInterface apiInterface;
    EditText Name_edt,Email_edt;
    TextView Phone_Edt;
    ImageView Userprofilepic;
    TextView edit_layout;
    ImageView Scan_Luck;
    ImageView Share;
    ImageView LuckRadar;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    Button Privacy_Settings;
    String NewWish="0";
    String Check="0";
    String GenderString;
    public static final int REQUEST_IMAGE = 100;
    ImageView LuckBox;
    Switch Privacy;
    TextView wishlistcount;
    TextView EXPDATE;
    String PrivacyString;

    ImageView coinbox;
    ImageView radarbox;
    ImageView footprints;
    ImageView ExchangebOx;
    ImageView friendslist;
    ImageView walletbox;
    ImageView friendrequest;
    String Userpic;
    String Type;
    TextView UseriDText;
    TextView Lock_Profile;
    ProgressBar progressbar;
    FirebaseUser firebaseUser;
    String AgeGroup;
    String Profession;
    List<String>Userid=new ArrayList<>();
    List<String>UserCoins=new ArrayList<>();
    List<String>PRIVACY=new ArrayList<>();
    List<String>UserName=new ArrayList<>();
    List<String>UserPhoto=new ArrayList<>();
    FriendslistAdapter FriendslistAdapter;
    Dialog dialog;
    Dialog dialogfriendreq;
    List<String> REQ_Userid=new ArrayList<>();
    List<String>REQ_UserCoins=new ArrayList<>();
    List<String>REQ_PRIVACY=new ArrayList<>();
    List<String>REQ_UserName=new ArrayList<>();
    List<String>REQ_UserPhoto=new ArrayList<>();
    FirenReqAdapter FriendsreqAdapter;
    ImageView close_button;
    ConstraintLayout wishlistmaster;
    ConstraintLayout leaderboard;
    ImageView AddWish;

    //Wishlist
    private static final String[] SUGGESTIONS = {
            "Bauru", "Sao Paulo", "Rio de Janeiro",
            "Bahia", "Mato Grosso", "Minas Gerais",
            "Tocantins", "Rio Grande do Sul"
    };
    private SimpleCursorAdapter mAdapter2;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    String Updated="0";
    TextView GenderRing,AgeGroupRing;
    String from_wallet;
//    ImageView LuckRadarImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
//        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_profile);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_ProfileView.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_ProfileView.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_ProfileView.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_ProfileView.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        final String[] from = new String[] {"Wishlist"};
        final int[] to = new int[] {android.R.id.text1};
        mAdapter2 = new SimpleCursorAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                null,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mAuth = FirebaseAuth.getInstance();

        dialog = new Dialog(this, android.R.style.Theme_Dialog);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogfriendreq = new Dialog(this, android.R.style.Theme_Dialog);
        dialogfriendreq.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
       initialize();
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        UserID= prefsnew.getString("UserID", "");
        auth_token= prefsnew.getString("auth_token", "");
        from_wallet= prefsnew.getString("from_wallet", "");
        LuckRadar.setEnabled(true);
        LuckRadar.setClickable(true);
        LuckRadar.setOnClickListener(v -> {
            Intent in=new Intent(getApplicationContext(),Activity_LuckRadar.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);

        });
        Privacy_Settings.setOnClickListener(v -> {
            Intent in=new Intent(getApplicationContext(),Activity_Privacy_Settings.class);
            startActivity(in);
        });
        walletbox.setOnClickListener(v -> {
            Intent in=new Intent(getApplicationContext(),Activity_Wallet.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });
        AddWish.setOnClickListener(v -> {
            Intent in=new Intent(getApplicationContext(), Activity_Wishlist_Master.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();
        });

        Phone_Edt.setEnabled(false);
        leaderboard.setOnClickListener(v -> {
            Intent inwish=new Intent(getApplicationContext(), Activity_LeaderBoard.class);
            startActivity(inwish);
        });
        wishlistmaster.setOnClickListener(v -> {
            Intent inwish=new Intent(getApplicationContext(), Activity_Wishlist_Master.class);
            startActivity(inwish);
            finish();
        });


        /*
        * LuckBox Activity Intent
        * */
        LuckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in =new Intent(getApplicationContext(),Activity_LuckBox.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);

            }
        });

        /*
        * Exchange Box intent
        * */
        ExchangebOx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in =new Intent(getApplicationContext(),Activity_Exchange.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);

            }
        });

        /*
        * CoinBox Intent
        * */
        coinbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in =new Intent(getApplicationContext(), Activty_CoinBox.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);

            }
        });
        /*
        * Footprint Popup intent
        * */
        footprints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in =new Intent(getApplicationContext(), Activty_Footprints.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);

            }
        });
        System.out.println("LS PROFILE WALLET  "+from_wallet);
        if (from_wallet.equals("1")){
            ChunqSList();
        }

        /*
        * Intent for Friendslist Popup
        * */
        friendslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChunqSList();
            }
        });

        /*
        * Friendsklist Popup
        * */
        friendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent in =new Intent(getApplicationContext(), Activty_FriendRequests.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);*/

                FrienRequests();

            }
        });

        /*
        * Intent to radar box
        * */
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        radarbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firebaseUser!=null){
                    Intent in=new Intent(getApplicationContext(), Activity_Chat.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                    finish();
                    //overridePendingTransition(R.anim.left_right, R.anim.right_left);
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(),"No user", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }
        });


        Scan_Luck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inhome=new Intent(getApplicationContext(), Activity_HomePage.class);
                startActivity(inhome);
                finish();
            }
        });

        Email_edt.setEnabled(false);
        Name_edt.setEnabled(false);
        Phone_Edt.setEnabled(false);
        Userprofilepic.setEnabled(false);
//        AddWishList.setEnabled(false);
        Privacy.setEnabled(false);

        Userprofilepic.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Privacy.setVisibility(View.VISIBLE);
        edit_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edit_layout.getText().toString().equals("ALL EDIT")){
                    Email_edt.setEnabled(true);
                    Name_edt.setEnabled(true);
                    Phone_Edt.setEnabled(true);
                    Userprofilepic.setEnabled(true);
//                    AddWishList.setEnabled(true);
                    Privacy_Settings.setEnabled(true);
                    Privacy_Settings.setVisibility(View.VISIBLE);
                    Privacy.setEnabled(true);
                    edit_layout.setText(getString(R.string.submit));
                }else if (edit_layout.getText().toString().equals("SUBMIT")){

                    Update_Profile(UserID);

                }




            }
        });

        Privacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked){
//                    Toast toast = Toast.makeText(getApplicationContext(),"Public", Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.CENTER, 0, 0);
//                    toast.show();
                }else {
                    PrivacyString="0";
//                    Toast toast = Toast.makeText(getApplicationContext(),"Private", Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.CENTER, 0, 0);
//                    toast.show();
                }

            }
        });

        /*
        * Share App to others via social media
        * */
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "LS (LUCKY SCAN)");
                    String shareMessage= "\nLet me recommend you this application  \nLS (LUCKY SCAN)\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }

            }
        });

        /*
        * Intent for Exchange page
        * */
        Userprofilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(Activity_ProfileView.this)
                        .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    showImagePickerOptions();
                                }

                                if (report.isAnyPermissionPermanentlyDenied()) {
                                    showSettingsDialog();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });


        ImagePickerActivity.clearCache(this);

        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        Name_edt.setTypeface(dosis);
        Email_edt.setTypeface(dosis);
        Phone_Edt.setTypeface(dosis);
        edit_layout.setTypeface(avner);
        UseriDText.setTypeface(avner);
        wishlistcount.setTypeface(dosis);
        EXPDATE.setTypeface(avner);
        Privacy_Settings.setTypeface(avner);
        Lock_Profile.setTypeface(avner);

        Fetch_Profile(UserID);
        Name_edt.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        Email_edt.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        Phone_Edt.setFilters(new InputFilter[] {new InputFilter.AllCaps()});


    }

    private void initialize() {
        coinbox = (ImageView) findViewById(R.id.coinbox);
        friendrequest = (ImageView) findViewById(R.id.requestsbox);
        radarbox = (ImageView) findViewById(R.id.radarbox);
        footprints = (ImageView) findViewById(R.id.footprints);
        ExchangebOx = (ImageView) findViewById(R.id.exchangebox);
        friendslist = (ImageView) findViewById(R.id.chunqsbox);
        walletbox = (ImageView) findViewById(R.id.walletbox);
        AddWish = (ImageView) findViewById(R.id.icon_searchvisible);
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        edit_layout = (TextView) findViewById(R.id.editlayout);
        Privacy = (Switch) findViewById(R.id.switchs);
        LuckBox = (ImageView) findViewById(R.id.luckbox);
        wishlistcount = (TextView) findViewById(R.id.wishlistcount);
        Lock_Profile = (TextView) findViewById(R.id.textView2);
        Userprofilepic=findViewById(R.id.profilepic);
        EXPDATE=findViewById(R.id.textView24);
        progressbar=findViewById(R.id.progressbar);
        UseriDText=findViewById(R.id.username);
        wishlistmaster=findViewById(R.id.wishlistmaster);
        leaderboard=findViewById(R.id.leaderboard);
        Privacy_Settings =findViewById(R.id.update);
        AgeGroupRing =findViewById(R.id.agegroup);
        GenderRing =findViewById(R.id.gender);
        Name_edt=findViewById(R.id.editText);
        Email_edt=findViewById(R.id.editText2);
        Phone_Edt=findViewById(R.id.editText3);
        Share=findViewById(R.id.share);
        LuckRadar=findViewById(R.id.luckradar);
        Scan_Luck=findViewById(R.id.scan_luck);
        disable_All();
    }

    private void disable_All() {
        coinbox.setEnabled(false);
        friendrequest.setEnabled(false);
        radarbox.setEnabled(false);
        footprints.setEnabled(false);
        ExchangebOx.setEnabled(false);
        friendslist.setEnabled(false);
        walletbox.setEnabled(false);
        AddWish.setEnabled(false);
        recyclerView2.setEnabled(false);
        edit_layout.setEnabled(false);
        Privacy.setEnabled(false);
        LuckBox.setEnabled(false);
        wishlistcount.setEnabled(false);
        Lock_Profile.setEnabled(false);
        Userprofilepic.setEnabled(false);
        EXPDATE.setEnabled(false);
        progressbar.setEnabled(false);
        UseriDText.setEnabled(false);
        wishlistmaster.setEnabled(false);
        leaderboard.setEnabled(false);
        Privacy_Settings.setEnabled(false);
        AgeGroupRing.setEnabled(false);
        GenderRing .setEnabled(false);
        Name_edt.setEnabled(false);
        Email_edt.setEnabled(false);
        Phone_Edt.setEnabled(false);
        Share.setEnabled(false);
        LuckRadar.setEnabled(false);
        Scan_Luck.setEnabled(false);
    }

    private void enable_All() {
        coinbox.setEnabled(true);
        friendrequest.setEnabled(true);
        radarbox.setEnabled(true);
        footprints.setEnabled(true);
        ExchangebOx.setEnabled(true);
        friendslist.setEnabled(true);
        walletbox.setEnabled(true);
        AddWish.setEnabled(true);
        recyclerView2.setEnabled(true);
        edit_layout.setEnabled(true);
        Privacy.setEnabled(true);
        LuckBox.setEnabled(true);
        wishlistcount.setEnabled(true);
        Lock_Profile.setEnabled(true);
        EXPDATE.setEnabled(true);
        progressbar.setEnabled(true);
        UseriDText.setEnabled(true);
        wishlistmaster.setEnabled(true);
        leaderboard.setEnabled(true);
        Privacy_Settings.setEnabled(true);
        AgeGroupRing.setEnabled(true);
        GenderRing .setEnabled(true);
        Name_edt.setEnabled(true);
        Email_edt.setEnabled(true);
        Phone_Edt.setEnabled(true);
        Share.setEnabled(true);
        LuckRadar.setEnabled(true);
        Scan_Luck.setEnabled(true);
    }



    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    /*
    * launch camera for take picture and crop
    * */
    private void launchCameraIntent() {
        Intent intent = new Intent(Activity_ProfileView.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    /*
    * Launching gallery for selecting images
    * */
    private void launchGalleryIntent() {
        Intent intent = new Intent(Activity_ProfileView.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    /*
    * User wishlist
    * */
    private  void Fetch_WishList(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Main_Profile_Wishlist> call3 = apiInterface.Fetch_WishList_Profile(auth_token);
        call3.enqueue(new Callback<Main_Profile_Wishlist>() {
            @Override
            public void onResponse(Call<Main_Profile_Wishlist> call, retrofit2.Response<Main_Profile_Wishlist> response) {

                try {int Code=response.body().getCode();
                    if (Code==200){
                        Prof_Wishlist_Response response1=response.body().getResponse();
                        List<WishlistData> wishlists=response1.getWishlist();
                        if (wishlists.size()>0){

                            for (int i=0;i<wishlists.size();i++){

                                WishlistData wishlistNotification=wishlists.get(i);
                                wishlist_id.add(wishlistNotification.getWishlistTransId());
                                wishlist_trans_master_id.add(wishlistNotification.getWishlistTransMasterId());
                                wishlist_trans_language_code.add(wishlistNotification.getWishlistTransLanguageCode());
                                wishlistname.add(wishlistNotification.getWishlistTransName());
                            }

                        }else {

                            Toast toast= Toast.makeText(getApplicationContext(),
                                    "No WishList", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                            toast.show();

                        }

                    }else if (Code==201){

                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==301){

                        Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(getApplicationContext(), Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intlogout);
                    }else if (Code==303||Code==305){

                        Toast.makeText(getApplicationContext(), "Session Expired", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(getApplicationContext(), Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intlogout);
                    }
                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Main_Profile_Wishlist> call, Throwable t) {
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent in=new Intent(getApplicationContext(),Activity_HomePage.class);
        startActivity(in);
        finish();
    }

    private void NavDrawer(String name, String userpic, String sessionID) {


        ConstraintLayout toolbar = (ConstraintLayout) findViewById(R.id.toolbar2);
        ConstraintLayout MenuIcon=findViewById(R.id.menu_icon);

        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                //overridePendingTransition(R.anim.left_right, R.anim.right_left);
            }
        });

//        Fetch_WishList();

    }
    @Override
    protected void onResume() {
        super.onResume();

        progressbar.setVisibility(View.GONE);

        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        SignedIn= prefsnew.getString("SignedIn", "");
        String SessionID= prefsnew.getString("UserID", "");
        Userpic= prefsnew.getString("Userpic", "");
        String Name= prefsnew.getString("Username", "");
        NavDrawer(Name,Userpic,SessionID);

    }
    private void Fetch_Profile(final String userID) {
        System.out.println("LS USER ID DATA   "+userID);
        progressbar.setVisibility(View.VISIBLE);
        User_id.clear();
        User_wishlist_id.clear();
        User_name.clear();
        User_email.clear();
        User_phone.clear();
        User_gender.clear();
        User_photo.clear();
        User_Privacy.clear();
        wishlist_trans_name.clear();
        wishlist_trans_id.clear();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ProfDATA> call3 = apiInterface.Fetch_Profile(auth_token);
        call3.enqueue(new Callback<ProfDATA>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ProfDATA> call, retrofit2.Response<ProfDATA> response) {
                progressbar.setVisibility(View.GONE);

                try {int Code=response.body().getCode();
                    if (Code==200){
                        enable_All();
                        Prof_Response response1=response.body().getResponse();
                        String Coins= String.valueOf(response1.getUserCoins());
                        String ExpDate=response1.getWishlistExpiryDate();
                        EXPDATE.setText(getString(R.string.expires_on)+ExpDate);
                        List<Prof_UserProfile>userprofile=response1.getUserProfile();
                        List<Prof_UserWishlist>UserWishlist=response1.getUserWishlist();
                        System.out.println("LS PROFILE WISHLIST DATA   "+UserWishlist.size());
                        if (userprofile.size()>0){
                            Constants.Const_Profile_Data=userprofile;
                            for (int i=0;i<userprofile.size();i++){
                                Prof_UserProfile language=userprofile.get(i);
                                User_id.add(language.getId());
//                                User_wishlist_id.add(language.getUserWishlistId());
                                User_name.add(language.getName());
                                User_email.add(language.getEmail());
                                User_phone.add(language.getMobile());
                                User_gender.add(language.getGender());
                                User_photo.add(language.getPhotoUrl());
                                User_Privacy.add(language.getIsActive());
                                AgeGroup=userprofile.get(0).getGroupCode();
                            }
                            Profession=Constants.Const_Profile_Data.get(0).getProfession();
                            if (User_gender.get(0).equals("Male")){
                                GenderRing.setText("M");
                                GenderString="3";
                                AgeGroupRing.setText("3");
                            }else if (User_gender.get(0).equals("Female")){
                                GenderRing.setText("F");
                                GenderString="2";
                                AgeGroupRing.setText("2");
                            }else {
                                GenderRing.setText("T");
                                GenderString="4";
                                AgeGroupRing.setText("4");
                            }
                            System.out.println("LS PROFILE DATA  "+User_id.get(0)+"    "+User_name.get(0)+"        "+User_email.get(0)+"      "+User_phone.get(0));
                            UseriDText.setText(User_id.get(0));
                            Name_edt.setText(User_name.get(0));
                            Phone_Edt.setText(User_phone.get(0));
                            Email_edt.setText(User_email.get(0));

                            PrivacyString=User_Privacy.get(0);

                            if (PrivacyString.equals("1")){
                                Privacy.setChecked(true);
                            }else {
                                Privacy.setChecked(false);
                            }
                            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                            editor.putString("Username", User_name.get(0).toString());
                            editor.putString("Userphone", User_phone.get(0).toString());
                            editor.putString("Userpic", User_photo.get(0).toString());
                            editor.apply();
                            Glide.with(getApplicationContext()).
                                    load(AppConfig.URL_BASE_VIDEOS+User_photo.get(0)).into(Userprofilepic);
//
//                            FirebaseUser firebaseUser=mAuth.getCurrentUser();
//                            assert firebaseUser != null;
//                            String UserID=firebaseUser.getUid();
//
//                            reference= FirebaseDatabase.getInstance().getReference("Users").child(UserID);
//                            HashMap<String,String> hashMap=new HashMap<>();
//                            hashMap.put("id",UserID);
//                            hashMap.put("username",Name_edt.getText().toString());
//                            hashMap.put("userImage",AppConfig.URL_BASE_VIDEOS+User_photo.get(0));
//                            hashMap.put("userid",User_id.get(0));
//
//                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//
//
//
//                                }
//                            });

                        } System.out.println("LS WISHLIST  "+UserWishlist.size());
                        if (UserWishlist.size()>0){
                            for (int i=0;i<UserWishlist.size();i++){

                                Prof_UserWishlist wishlist=UserWishlist.get(i);
                                wishlist_trans_name.add(wishlist.getWishName());
                                wishlist_trans_id.add(wishlist.getWishlistId()+"|");

                            }
                            Constants.Const_WishID=wishlist_trans_id;
                            Constants.Const_WishName=wishlist_trans_name;
                            wishlistcount.setText(""+UserWishlist.size()+" NOS");
                            mAdapter = new WishList_Adapter2(UserWishlist,auth_token,userID,wishlist_trans_name,wishlist_trans_id,mAdapter,getApplicationContext(),Activity_ProfileView.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView2.setLayoutManager(mLayoutManager);
                            recyclerView2.setItemAnimator(new DefaultItemAnimator());
                            recyclerView2.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();

                        }

                    }else if (Code==201){

                        Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();

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

                }catch (Exception  e){

                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ProfDATA> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                call.cancel();
            }
        });
    }


    public static String getStringImage2(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void Update_Profile(String userID) {

        System.out.println("LS PROFILE UPDATE  "+userID+"        "+Name_edt.getText().toString()+"          "+Phone_Edt.getText().toString()+"       "+GenderString+"        "+listString
                +"       "+Email_edt.getText().toString()+"      "+PrivacyString+"       "+AgeGroup+"          "+Profession);

        if (NewWish.equals("1")){

            for (String s : wishlist_trans_id)
            {
                listString += s ;
            }
            listString=WishDataString;
        }else {

            for (String s : wishlist_trans_id)
            {
                listString += s ;
            }
        }




        progressbar.setVisibility(View.VISIBLE);
        String Userimage = "";
        if (Check.equals("0")){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<Update_Prof> call3 = apiInterface.Update_Profile(auth_token,Name_edt.getText().toString(),Phone_Edt.getText().toString(),GenderString,listString,"",Email_edt.getText().toString(),
                    PrivacyString,AgeGroup,Profession);
            call3.enqueue(new Callback<Update_Prof>() {
                @Override
                public void onResponse(@NotNull Call<Update_Prof> call, @NotNull retrofit2.Response<Update_Prof> response) {
                    progressbar.setVisibility(View.GONE);

                    try {
                        assert response.body() != null;
                        int Code=response.body().getCode();
                        if (Code==200){
                            edit_layout.setText(getString(R.string.all_edit));
                            Toast toast= Toast.makeText(getApplicationContext(),
                                    "Successfully updated", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                            toast.show();
                            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                            editor.putString("from_wallet", "0");
            editor.apply();
                            Intent inprof=new Intent(Activity_ProfileView.this, Activity_ProfileView.class);
                            startActivity(inprof);
                            finish();
                            ////overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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

                    }catch (Exception  e) {
                        e.printStackTrace();
                        Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }

                @Override
                public void onFailure(Call<Update_Prof> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);
                    call.cancel();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }
            });
        }else {
            Userimage = getStringImage2(bmp);
            apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<Update_Prof> call3 = apiInterface.Update_Profile(auth_token,Name_edt.getText().toString(),Phone_Edt.getText().toString(),GenderString,listString,Userimage,Email_edt.getText().toString(),PrivacyString,AgeGroup,Profession);
            call3.enqueue(new Callback<Update_Prof>() {
                @Override
                public void onResponse(Call<Update_Prof> call, retrofit2.Response<Update_Prof> response) {
                    progressbar.setVisibility(View.GONE);
                    edit_layout.setText(getString(R.string.all_edit));
                    try {int Code=response.body().getCode();
                        if (Code==200){

                            Toast toast= Toast.makeText(getApplicationContext(),
                                    "Updated Successfully", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                            toast.show();
                            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                            editor.putString("from_wallet", "0");
            editor.apply();
                            Intent inprof=new Intent(Activity_ProfileView.this, Activity_ProfileView.class);
                            startActivity(inprof);
                            finish();
                            ////overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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

                    }catch (Exception  e){
                        e.printStackTrace();
                        Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }

                @Override
                public void onFailure(Call<Update_Prof> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);
                    call.cancel();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }
            });
        }


    }


    private void selectImage() {

        final CharSequence[] items = { "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_ProfileView.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Uttility.checkPermission(Activity_ProfileView.this);
                if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), 1);

    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    Userprofilepic.setImageBitmap(bmp);
                    ContentResolver cR = getApplicationContext().getContentResolver();
                    MimeTypeMap mime = MimeTypeMap.getSingleton();
                    assert uri != null;
                    type = mime.getExtensionFromMimeType(cR.getType(uri));
                    Check="1";
                    InputStream imageStream = getContentResolver().openInputStream(uri);
                    bmp = BitmapFactory.decodeStream(imageStream);
                    bmp = getResizedBitmap(bmp, 400);// 400 is for example, replace with desired size
                    Userprofilepic.setImageBitmap(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_ProfileView.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();
    }
    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    private void ChunqSList() {
        dialog.setContentView(R.layout.list_friends);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        RecyclerView recyclerView;
        ImageView close_button;
        recyclerView = dialog.findViewById(R.id.recycler_view);
        progressbar = dialog.findViewById(R.id.progressbar);
        TextView Total_Count=dialog.findViewById(R.id.total_count_frnds);
        TextView Total_New_Count=dialog.findViewById(R.id.last_count);
        TextView Total_Sent_Count=dialog.findViewById(R.id.total_sent);
        TextView textView31=dialog.findViewById(R.id.txt18);
        ImageView Toparrow=dialog.findViewById(R.id.top_arrow);
        ImageView Bottomarrow=dialog.findViewById(R.id.bottomarrow);

        ConstraintLayout constraintLayoutmain=dialog.findViewById(R.id.main_lay);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_ProfileView.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(v -> {
                })
                .longPressListener(v -> {
                }).doubleTapListener(v -> {
                });

        builder.register();
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        Total_Count.setTypeface(avner);
        Total_New_Count.setTypeface(avner);
        Total_Sent_Count.setTypeface(avner);
        textView31.setTypeface(dosis);

        close_button = dialog.findViewById(R.id.close_button);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });

        Toparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position1=position1+1;
                recyclerView.smoothScrollToPosition(position1);

            }
        });

        Bottomarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position1==0){
                }else {
                    position1=position1-1;
                    recyclerView.smoothScrollToPosition(position1);
                }
            }
        });

        Fetch_Users(UserID,recyclerView,Total_Count,Total_New_Count,Total_Sent_Count,progressbar);


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
    private void FrienRequests() {


        dialogfriendreq.setContentView(R.layout.list_friends_req);
        dialogfriendreq.setCanceledOnTouchOutside(false);
        dialogfriendreq.setCancelable(false);
        dialogfriendreq.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialogfriendreq.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogfriendreq.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialogfriendreq.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        RecyclerView recyclerView;
        ImageView close_button;
        TextView Total=dialogfriendreq.findViewById(R.id.total_req);
        TextView Sub_Total=dialogfriendreq.findViewById(R.id.sub_total);
        recyclerView = dialogfriendreq.findViewById(R.id.recycler_view);
        close_button = dialogfriendreq.findViewById(R.id.close_button);
        ImageView Toparrow = dialogfriendreq.findViewById(R.id.toparrow);
        ImageView Bottomarrow = dialogfriendreq.findViewById(R.id.bottomarrow);

        ConstraintLayout constraintLayoutmain=dialogfriendreq.findViewById(R.id.main_lay);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_ProfileView.this)
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
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");

        Total.setTypeface(avner);
        Sub_Total.setTypeface(avner);

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogfriendreq.dismiss();
            }
        });

        Fetch_User_Rq(UserID,recyclerView,Total,Sub_Total);

        Toparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position2=position2+1;
                recyclerView.smoothScrollToPosition(position2);

            }
        });

        Bottomarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position2==0){
                }else {
                    position2=position2-1;
                    recyclerView.smoothScrollToPosition(position2);
                }
            }
        });

        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);

        try {
            dialogfriendreq.getWindow().getAttributes().windowAnimations = R.style.MyAlertDialogStyle;
            dialogfriendreq.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Fetch_User_Rq(final String userID, RecyclerView recyclerViewreq, TextView total, TextView sub_Total) {
        REQ_Userid.clear();
        REQ_UserCoins.clear();
        REQ_PRIVACY.clear();
        REQ_UserName.clear();
        REQ_UserPhoto.clear();

        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<FetchAllReqData> call3 = apiInterface.Fetch_RqFriends(auth_token);
        call3.enqueue(new Callback<FetchAllReqData>() {
            @Override
            public void onResponse(Call<FetchAllReqData> call, retrofit2.Response<FetchAllReqData> response) {

                try {
                    int Code=response.body().getCode();
                    if (Code==200){
                        FetchAllReqData_Response response1=response.body().getResponse();
                        List<FetchAllReqData_Request> request=response1.getRequests();
                        total.setText(getString(R.string.total)+request.size()+" NOS");
                        sub_Total.setText(getString(R.string.total)+response1.getRequestCount()+" NOS");
                        if (request.size()>0){
                            for (int i=0;i<request.size();i++){


                            }
                            FriendsreqAdapter = new FirenReqAdapter(progressbar,auth_token,userID,request
                                    ,getApplicationContext(),Activity_ProfileView.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerViewreq.setLayoutManager(mLayoutManager);
                            recyclerViewreq.setItemAnimator(new DefaultItemAnimator());
                            recyclerViewreq.setAdapter(FriendsreqAdapter);
                        }else {
                            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else if (Code==201){

                        new SweetAlertDialog(Activity_ProfileView.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("SORRY")
                                .setContentText("NO REQUESTS FOUND")
                                .setConfirmText("CLOSE")

                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                    }
                                })
                                .show();

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
                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<FetchAllReqData> call, Throwable t) {
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });
    }

    private void Fetch_Users(final String userID, RecyclerView recyclerView, TextView total_Count,
                             TextView total_New_Count, TextView total_Sent_Count, ProgressBar progressbar) {
        Userid.clear();
        UserCoins.clear();
        PRIVACY.clear();
        UserName.clear();
        UserPhoto.clear();
        progressbar.setVisibility(View.VISIBLE);
        System.out.println("LS AUTH KEY   "+auth_token);
        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<FriendsList_Main> call3 = apiInterface.Fetch_Friends(auth_token);
        call3.enqueue(new Callback<FriendsList_Main>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<FriendsList_Main> call, retrofit2.Response<FriendsList_Main> response) {
                progressbar.setVisibility(View.GONE);
                try {
                    int Code=response.body().getCode();
                    System.out.println("LS FRIENDS CODE    "+Code);
                    if (Code==200){
                        Friendslist_Response friendsList_response=response.body().getResponse();
                        List<FriendsList_Data> users=friendsList_response.getFriends();
                        System.out.println("LS FRIENDS COUNT    "+users.size());
                        if (users.size()>0){

                            for (int i=0;i<users.size();i++){
                                FriendsList_Data userslist=users.get(i);
                                Userid.add(userslist.getId());
                                UserName.add(userslist.getName());
                                UserPhoto.add(userslist.getPhotoUrl());
                                UserCoins.add(userslist.getTotalCoins());
                                PRIVACY.add(userslist.getPhotoApprovalStatus());
                            }
                            total_Count.setText(getString(R.string.total)+users.size()+" NOS");
                            total_New_Count.setText(friendsList_response.getRequestComing()+" NOS");
                            total_Sent_Count.setText(getString(R.string.total)+friendsList_response.getRequestSend()+" NOS");
                            FriendslistAdapter = new FriendslistAdapter(auth_token,userID,Userid,UserName,UserPhoto,PRIVACY,users
                                    ,getApplicationContext(),Activity_ProfileView.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(FriendslistAdapter);
                        }else {

                            Toast.makeText(getApplicationContext(), "No Friends...", Toast.LENGTH_SHORT).show();


                        }

                    }else if (Code==201){
                        System.out.println("LS FRIENDS NO    ");
                        new SweetAlertDialog(Activity_ProfileView.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("SORRY")
                                .setContentText("NO FRIENDS FOUND")
                                .setConfirmText("CLOSE")

                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                    }
                                })
                                .show();

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

                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    progressbar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<FriendsList_Main> call, Throwable t) {
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                t.printStackTrace();
                progressbar.setVisibility(View.GONE);
            }
        });
    }

}
