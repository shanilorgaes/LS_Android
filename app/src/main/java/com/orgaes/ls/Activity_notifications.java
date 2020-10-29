package com.orgaes.ls;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.orgaes.ls.Adapter.Notifications_Adapter;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.RETROFIT_NEW.Notifications.NotificationsMain;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

/*
 * Purpose – Activity_notifications is an activity which the user can see all notifications here
 * @author SHANIL
 * Created on 21-5-2020
 */
public class Activity_notifications extends AppCompatActivity {

    String SignedIn;

    RecyclerView Rcyclerview_Lucks;
    APIInterface apiInterface;
    List<String> notification_id=new ArrayList<>();
    List<String>notification_name=new ArrayList<>();
    List<String>edition=new ArrayList<>();
    List<String>category=new ArrayList<>();
    List<String>gender_Id=new ArrayList<>();
    List<String>age_group=new ArrayList<>();
    List<String>wishlist_trans_id=new ArrayList<>();
    List<String>wishlist_trans_master_id=new ArrayList<>();
    List<String>wishlist_trans_language_code=new ArrayList<>();
    List<String>wishlist_trans_name=new ArrayList<>();
    List<String>edition_id=new ArrayList<>();
    List<String>country_id=new ArrayList<>();
    List<String>state_id=new ArrayList<>();
    List<String>district=new ArrayList<>();
    List<String>location=new ArrayList<>();
    List<String>pincode=new ArrayList<>();
    List<String>language=new ArrayList<>();
    List<String>edition_name=new ArrayList<>();
    List<String>x1_latitude=new ArrayList<>();
    List<String>y1_longitude=new ArrayList<>();
    List<String>x2_latitude=new ArrayList<>();
    List<String>y2_longitude=new ArrayList<>();
    List<String>distance=new ArrayList<>();
    List<String>unit=new ArrayList<>();
    ProgressBar progressbar;
    Notifications_Adapter mAdapter;
    int position2=0;
    ImageView LuckRadarImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_notifications);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_notifications.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_notifications.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_notifications.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_notifications.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        Rcyclerview_Lucks=findViewById(R.id.expandable_recyclerview);
        progressbar=findViewById(R.id.progressbar);
        LuckRadarImage=findViewById(R.id.luckradar);
        LuckRadarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),Activity_LuckRadar.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
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
        arrowclickFunction();
    }

    public void arrowclickFunction(){
        ImageView Toparrow,Bottomarrow;

        Toparrow=findViewById(R.id.toparrow);
        Bottomarrow=findViewById(R.id.bottomarrow);
        Toparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position2=position2+1;
                Rcyclerview_Lucks.smoothScrollToPosition(position2);

            }
        });

        Bottomarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position2==0){
                }else {
                    position2=position2-1;
                    Rcyclerview_Lucks.smoothScrollToPosition(position2);
                }
            }
        });
    }
    /*
    * navigation drawer function
    * */
    private void NavDrawer() {
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
        ImageView scan_luck;
        scan_luck = (ImageView) findViewById(R.id.scan_luck);
        scan_luck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(), Activity_HomePage.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                ////overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        String Name= prefsnew.getString("Username", "");
        String Pic= prefsnew.getString("Userpic", "");
        String Userid= prefsnew.getString("Userid", "");

    }

    @Override
    protected void onResume() {
        super.onResume();


        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        String UserID= prefsnew.getString("UserID", "");
        SignedIn= prefsnew.getString("SignedIn", "");
        NavDrawer();
        Fetch_Notifications(UserID);
    }

    @Override
    public void onBackPressed() {

        Intent inhome=new Intent(this, Activity_HomePage.class);
        startActivity(inhome);
        finish();
        ////overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    private void Fetch_Notifications(String userID) {

        progressbar.setVisibility(View.VISIBLE);
        notification_id.clear();
        notification_name.clear();
        edition.clear();
        category.clear();
        gender_Id.clear();
        age_group.clear();
        wishlist_trans_id.clear();
        wishlist_trans_master_id.clear();
        wishlist_trans_language_code.clear();
        wishlist_trans_name.clear();
        edition_id.clear();
        country_id.clear();
        state_id.clear();
        district.clear();
        location.clear();
        pincode.clear();
        language.clear();
        edition_name.clear();
        x1_latitude.clear();
        y1_longitude.clear();
        x2_latitude.clear();
        y2_longitude.clear();
        distance.clear();
        unit.clear();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<NotificationsMain> call3 = apiInterface.Fetch_Notifications("adhf-236b-dhfh-gdfv-3cjh", "notifications",userID);
        call3.enqueue(new Callback<NotificationsMain>() {
            @Override
            public void onResponse(Call<NotificationsMain> call, retrofit2.Response<NotificationsMain> response) {
                progressbar.setVisibility(View.GONE);

                try {int Code=response.body().getCode();
                    if (Code==200){

                      /*  List<WishlistNotification> luck=response.body().getWishlistNotifications();
                        if (luck.size()>0){

                            for (int i=0;i<luck.size();i++){
                                WishlistNotification wishlistNotification=luck.get(i);
                                notification_id.add(wishlistNotification.getNotificationId());
                                notification_name.add(wishlistNotification.getNotificationName());
                                edition.add(wishlistNotification.getEdition());
                                category.add(wishlistNotification.getCategory());
                                gender_Id.add(wishlistNotification.getGenderId());
                                age_group.add(wishlistNotification.getAgeGroup());
                                wishlist_trans_id.add(wishlistNotification.getWishlistTransId());
                                wishlist_trans_master_id.add(wishlistNotification.getWishlistTransMasterId());
                                wishlist_trans_language_code.add(wishlistNotification.getWishlistTransLanguageCode());
                                wishlist_trans_name.add(wishlistNotification.getWishlistTransLanguageCode());
                                edition_id.add(wishlistNotification.getEditionId());
                                country_id.add(wishlistNotification.getCountryId());
                                state_id.add(wishlistNotification.getStateId());
                                district.add(wishlistNotification.getDistrict());
                                location.add(wishlistNotification.getLocation());
                                pincode.add(wishlistNotification.getPincode());
                                language.add(wishlistNotification.getLanguage());
                                edition_name.add(wishlistNotification.getEditionName());
                                x1_latitude.add(wishlistNotification.getX1Latitude());
                                y1_longitude.add(wishlistNotification.getY1Longitude());
                                x2_latitude.add(wishlistNotification.getX2Latitude());
                                y2_longitude.add(wishlistNotification.getY2Longitude());
                                distance.add(wishlistNotification.getDistance());
                                unit.add(wishlistNotification.getUnit());
                            }
                            mAdapter = new Notifications_Adapter(
                                    notification_id,
                                    notification_name,
                                    edition,
                                    category,
                                    gender_Id,
                                    age_group,
                                    wishlist_trans_id,
                                    wishlist_trans_master_id,
                                    wishlist_trans_language_code,
                                    wishlist_trans_name,
                                    edition_id,
                                    country_id,
                                    state_id,
                                    district,
                                    location,
                                    pincode,
                                    language,
                                    edition_name,
                                    x1_latitude,
                                    y1_longitude,
                                    x2_latitude,
                                    y2_longitude,
                                    distance,
                                    unit,getApplicationContext(),Activity_notifications.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            Rcyclerview_Lucks.setLayoutManager(mLayoutManager);
                            Rcyclerview_Lucks.setItemAnimator(new DefaultItemAnimator());
                            Rcyclerview_Lucks.setAdapter(mAdapter);
                        }else {
                            Toast toast = Toast.makeText(getApplicationContext(),"No notifications", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }*/

                    }else {

                        Toast toast = Toast.makeText(getApplicationContext(),"No notifications", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }

                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<NotificationsMain> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                call.cancel();
            }
        });
    }

}
