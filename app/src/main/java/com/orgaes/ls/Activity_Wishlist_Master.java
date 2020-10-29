package com.orgaes.ls;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;import androidx.appcompat.app.AppCompatActivity;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.orgaes.ls.Adapter.WishList_AdapterMaster;
import com.orgaes.ls.Adapter.WishList_AdapterMastermian;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.RETROFIT_NEW.DeleteWishList.DeleteWishList;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Wishlist.Fecth_Wish_Response;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Wishlist.Fetch_Wishlists;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Wishlist.Fettch_data_Wishlist;
import com.orgaes.ls.RETROFIT_NEW.WishlistMASTERDATA.WishlistMasterDetails;
import com.orgaes.ls.RETROFIT_NEW.WishlistMASTERDATA.WishlistMasterModel;
import com.orgaes.ls.RETROFIT_NEW.WishlistMASTERDATA.WishlistMasterResponse;
import com.orgaes.ls.RETROFIT_NEW.WishlistMASTERDATA.WishlistMasterWishlistUser;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Wishlist_Master extends AppCompatActivity {

    RecyclerView recyclerView2;
    RecyclerView master_recycler;
    WishList_AdapterMaster mAdapter;
    WishList_AdapterMastermian mAdapter2;

    List<String>wishlist_id=new ArrayList<>();/*
    List<String>wishlist_trans_master_id=new ArrayList<>();
    List<String>wishlist_trans_language_code=new ArrayList<>();*/
    List<String>wishlistname=new ArrayList<>();
    String UserIDString;
    TextView Exp_Date;
    TextView Count;
    TextView hotthaughts;
    int position2=0;
    SearchBox SearchView;
    ArrayList<String>SuggestionsList=new ArrayList<>();
    ProgressDialog progressDialog;
    List<WishlistMasterWishlistUser> wishlists;
    String auth_token;
    String open="";
    ImageView LuckRadarImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_wishlist_master);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Wishlist_Master.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Wishlist_Master.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_Wishlist_Master.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Wishlist_Master.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView3);
        master_recycler = (RecyclerView) findViewById(R.id.master_recycler);
        Exp_Date=findViewById(R.id.expiry_date);
        Count=findViewById(R.id.count);
        hotthaughts=findViewById(R.id.hotthaughts);
        SearchView=findViewById(R.id.searchview);
        Typeface avner = Typeface.createFromAsset(getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(getAssets(),
                "Dosis-Medium.ttf");
        Exp_Date.setTypeface(avner);
        Count.setTypeface(dosis);
        hotthaughts.setTypeface(dosis);
        arrowclickFunction();
        ImageView Scan_Luck=findViewById(R.id.scan_luck);
        LuckRadarImage=findViewById(R.id.luckradar);
        LuckRadarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),Activity_LuckRadar.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
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
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        auth_token= prefsnew.getString("auth_token", "");
        Fetch_WishListMain();
    }

    private void getSearch() {
        for(int x = 0; x < SuggestionsList.size(); x++){
            @SuppressLint("UseCompatLoadingForDrawables") SearchResult option = new SearchResult(SuggestionsList.get(x), getResources().getDrawable(R.drawable.history));
            SearchView.setAnimateDrawerLogo(false);
            SearchView.addSearchable(option);
            SearchView.setMaxLength(25);
        }
        SearchView.setMenuListener(() -> {
            //Hamburger has been clicked
            SearchView.getResults();
            SearchView.toggleSearch();
        });
        SearchView.setSearchListener(new SearchBox.SearchListener(){
            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
            }
            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }
            @Override
            public void onSearchTermChanged(String term) {
                //React to the search term changing
                //Called after it has updated results
            }

            @Override
            public void onSearch(String searchTerm) {

                String WishlistID=searchTerm.replace("[","");
                ADD_WishList(WishlistID.replace("]",""));
            }
            @Override
            public void onResultClick(SearchResult result) {
                //React to a result being clicked
                String str = result.title;
                String answer = str.substring(str.indexOf("[")+1,str.indexOf("]"));
                String WishlistID=answer.replace("[","");
                ADD_WishList(WishlistID.replace("]",""));
            }
            @Override
            public void onSearchCleared() {
                //Called when the clear button is clicked
            }
        });
    }

    public void Fetch_WishList(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<WishlistMasterModel> call3 = apiInterface.Fetch_Wishlist_Master(auth_token);
        call3.enqueue(new Callback<WishlistMasterModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<WishlistMasterModel> call, retrofit2.Response<WishlistMasterModel> response) {
                progressDialog.dismiss();
                try {int Code=response.body().getCode();
                    if (Code==200){
                        WishlistMasterResponse response1=response.body().getResponse();
                        wishlists=response1.getWishlistUser();
                        final List<WishlistMasterDetails> wishlistMasters=response1.getWishlistMaster();
                        Exp_Date.setText("EXPIRES ON : "+response1.getWishlistExpiry());
                        Count.setText(""+wishlists.size()+" NOS");
                        if (wishlists.size()>0){

                            for (int i=0;i<wishlists.size();i++){

                                WishlistMasterWishlistUser wishlistNotification=wishlists.get(i);
                                wishlist_id.add(wishlistNotification.getWishlistId());
                                wishlistname.add(wishlistNotification.getWishName());
                            }
                            RecyclerViewDeleteClicklistner listener = (String userID, String wishlistTransId, int position, String wishlistTransName) -> {
                                new SweetAlertDialog(Activity_Wishlist_Master.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Are you sure?")
                                        .setContentText("Delete "+wishlistTransName+" from your wishlist ?")
                                        .setConfirmText("Yes,delete it!")
                                        .setCancelText("Exit")
                                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                sweetAlertDialog.dismiss();
                                            }
                                        })
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                Delete_WishList(userID,wishlistTransId,position,sDialog);
                                            }
                                        })
                                        .show();
                            };
                            mAdapter = new WishList_AdapterMaster(wishlists,UserIDString,mAdapter,getApplicationContext(),Activity_Wishlist_Master.this,listener);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView2.setLayoutManager(mLayoutManager);
                            recyclerView2.setItemAnimator(new DefaultItemAnimator());
                            recyclerView2.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();

                        }
                        if (wishlistMasters.size()>0){

                            RecyclerViewClickListener listener = (String userID, String wishlistId) -> {
                                ADD_WishList1(userID,wishlistId);
                            };
                            mAdapter2 = new WishList_AdapterMastermian(wishlistMasters,UserIDString,mAdapter2,mAdapter,getApplicationContext(),Activity_Wishlist_Master.this,listener);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            master_recycler.setLayoutManager(mLayoutManager);
                            master_recycler.setItemAnimator(new DefaultItemAnimator());
                            master_recycler.setAdapter(mAdapter2);
                            mAdapter2.notifyDataSetChanged();

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
            public void onFailure(Call<WishlistMasterModel> call, Throwable t) {
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                progressDialog.dismiss();
            }
        });

    }

    private void ADD_WishList(String Wishlistid) {
        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<DeleteWishList> call3 = apiInterface.WishlistMasterSelected(auth_token,Wishlistid.replace("[ ]",""));
        call3.enqueue(new Callback<DeleteWishList>() {
            @Override
            public void onResponse(Call<DeleteWishList> call, Response<DeleteWishList> response) {

                try {

                    int Code=response.body().getCode();
                    if (Code==200){
                        SearchView.ClearText();
                        Toast toast = Toast.makeText(getApplicationContext(),"Your wishlist will added soon", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        Fetch_WishList();
                    }else if (Code==201){
                        Toast toast = Toast.makeText(getApplicationContext(),"Failed", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }else if (Code==203){
                        SearchView.ClearText();
                        Toast toast = Toast.makeText(getApplicationContext(),"Already added", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

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
                }catch (Exception e){

                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<DeleteWishList> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }

        });

    }
    public void arrowclickFunction(){
        ImageView Toparrow,Bottomarrow;

        Toparrow=findViewById(R.id.toparrow);
        Bottomarrow=findViewById(R.id.bottomarrow);
        Toparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position2=position2+1;
                recyclerView2.smoothScrollToPosition(position2);

            }
        });

        Bottomarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position2==0){
                }else {
                    position2=position2-1;
                    recyclerView2.smoothScrollToPosition(position2);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (open.equals("")){
            if (SearchView.isShown()) {
                System.out.println("LS BACKPRESS clicked   1");
                SearchView.hideResults();
                open="0";
            }
        }else {
            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
            editor.putString("from_wallet", "0");
            editor.apply();
            System.out.println("LS BACKPRESS clicked   2");
            Intent in = new Intent(getApplicationContext(), Activity_ProfileView.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);
            finish();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();



        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        UserIDString= prefsnew.getString("UserID", "");
        Fetch_WishList();
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
    }


    private  void Fetch_WishListMain(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Fetch_Wishlists> call3 = apiInterface.Fetch_WishList();
        call3.enqueue(new Callback<Fetch_Wishlists>() {
            @Override
            public void onResponse(Call<Fetch_Wishlists> call, retrofit2.Response<Fetch_Wishlists> response) {

                try {int Code=response.body().getCode();
                    if (Code==200){
                        Fecth_Wish_Response response1=response.body().getResponse();
                        List<Fettch_data_Wishlist> wishlists=response1.getWishlist();
                        if (wishlists.size()>0){
                            for (int i=0;i<wishlists.size();i++){
                                Fettch_data_Wishlist wishlistNotification=wishlists.get(i);
                                SuggestionsList.add(wishlistNotification.getWishlistTransName()+"     ["+wishlistNotification.getWishlistTransId()+"]");
                            }
                            getSearch();
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
            public void onFailure(Call<Fetch_Wishlists> call, Throwable t) {
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });

    }


    private void ADD_WishList1(String userID, String Wishlistid) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Adding...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<DeleteWishList> call3 = apiInterface.WishlistMasterSelected(auth_token,Wishlistid.replace("|",""));
        call3.enqueue(new Callback<DeleteWishList>() {
            @Override
            public void onResponse(Call<DeleteWishList> call, Response<DeleteWishList> response) {
                progressDialog.dismiss();

                try {
                    int Code=response.body().getCode();
                    if (Code==200){

                        Toast toast = Toast.makeText(getApplicationContext(),"Added successfully", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        Fetch_WishList();
                    }else if (Code==201){

                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==203){

                        Toast.makeText(getApplicationContext(), "Already Added", Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<DeleteWishList> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                progressDialog.dismiss();
            }

        });

    }

    public interface RecyclerViewClickListener {
        void onClick(String userID, String wishlistId);

    }
    public interface RecyclerViewDeleteClicklistner {
        void onClickDelete(String userID, String wishlistTransId, int position, String wishlistTransName);
    }

    private void Delete_WishList(String userID, String Wishlistid, final int position, SweetAlertDialog sDialog) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deleting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<DeleteWishList> call3 = apiInterface.DeleteWishlist(auth_token,Wishlistid.replace("|",""));
        call3.enqueue(new Callback<DeleteWishList>() {
            @Override
            public void onResponse(Call<DeleteWishList> call, Response<DeleteWishList> response) {
                progressDialog.dismiss();

                try {

                    int Code=response.body().getCode();
                    if (Code==200){
                        sDialog
                                .setTitleText("Deleted!")
                                .setContentText("Your Wishlist has been deleted!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Toast toast = Toast.makeText(getApplicationContext(),"Deleted", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                        wishlists.remove(position);
                                        mAdapter.updateList(wishlists);
                                        sDialog.dismiss();
                                    }
                                })
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                    }else if (Code==201){

                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==301||Code==207){

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
                }catch (Exception e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<DeleteWishList> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                progressDialog.dismiss();
            }

        });

    }




}
