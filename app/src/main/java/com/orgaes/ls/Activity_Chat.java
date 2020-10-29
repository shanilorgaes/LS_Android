package com.orgaes.ls;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orgaes.ls.Fragments.UsersFragment;
import com.orgaes.ls.Fragments.chat_fragment;
import com.orgaes.ls.Model.Users;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/*
 * Purpose – Activity_Chat is a chat activity
 * 2 fragments
 * Recent chats and Users list
 * @author SHANIL
 * Created on 20-6-2020
 */
public class Activity_Chat extends AppCompatActivity {

    public CircleImageView img_Userimage;
    public TextView txtUsername;

    public FirebaseUser firebaseUser;
    public  DatabaseReference reference;
    public ConstraintLayout constrProfile;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        /*
        * Changing the color of footbar (black color)
        * */
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity__chat);
        bindControlls();
    }

    private void bindControlls() {

        progressDialog = new ProgressDialog(Activity_Chat.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        img_Userimage =findViewById(R.id.userimage);
        txtUsername =findViewById(R.id.username);
        constrProfile =findViewById(R.id.constraintLayout8);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Chat.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Chat.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_Chat.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Chat.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        /*
         * Intent to profile page on user photo click
         * */
        constrProfile.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
            editor.putString("from_wallet", "0");
            editor.apply();
            Intent in=new Intent(getApplicationContext(),Activity_ProfileView.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);

        });
        /*
         * Font style changing
         * */
        Typeface avner = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "AvenirLTStd-Light.otf");

        txtUsername.setTypeface(avner);

        /*
         * Firebase authentication
         * */
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                Users users=dataSnapshot.getValue(Users.class);
                assert users != null;
                Toast toast = Toast.makeText(getApplicationContext(),"Logged in as "+users.getUsername(), Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                txtUsername.setText(users.getUsername());
                if (users.getUserImage().equals("default")){
                    img_Userimage.setImageResource(R.drawable.default_icon);
                }else {
                    Glide.with(Activity_Chat.this).load(users.getUserImage()).into(img_Userimage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*
         * tablayout and viewpager for fragment
         * */
        TabLayout tableLayout=findViewById(R.id.tableLayout);
        ViewPager viewPager=findViewById(R.id.viewpager);

        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new chat_fragment(),"Chats");
        viewPagerAdapter.addFragment(new UsersFragment(),"Users");
        viewPager.setAdapter(viewPagerAdapter);

        tableLayout.setupWithViewPager(viewPager);

        ConstraintLayout MenuIcon=findViewById(R.id.menu_icon);

        /*
         * Intent to menu page
         * */
        MenuIcon.setOnClickListener(v -> {

            Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            //overridePendingTransition(R.anim.left_right, R.anim.right_left);
        });

    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
        editor.putString("from_wallet", "0");
            editor.apply();
        Intent in=new Intent(getApplicationContext(),Activity_ProfileView.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }




    /*
    * Viewpager Fragment class
    * */
    static class ViewPagerAdapter extends FragmentPagerAdapter{

        private ArrayList<Fragment>fragments;
        private ArrayList<String>titles;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        /*
        * Adding Fragments
        * */
        public void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}