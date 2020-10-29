package com.orgaes.ls.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.orgaes.ls.Activity_Choose_Language;
import com.orgaes.ls.Activity_ProfileView;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.DeleteWishList.DeleteWishList;
import com.orgaes.ls.RETROFIT_NEW.VIEW.Prof_UserWishlist;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class WishList_Adapter2 extends RecyclerView.Adapter<WishList_Adapter2.MyViewHolder> {


    List<String>Wish_NAME=new ArrayList<>();
    List<String>Wish_ID=new ArrayList<>();
    String userID;
    ProgressDialog progressDialog;
    Activity_ProfileView activity_ProfileView;
    Context context;
    WishList_Adapter2 mAdapter;
    String auth_token;
    WindowManager windowManager2;
    List<Prof_UserWishlist> userWishlist;
    public WishList_Adapter2(List<Prof_UserWishlist> userWishlist, String auth_token, String userID, List<String> wishlist_trans_name, List<String> wishlist_trans_id, WishList_Adapter2 mAdapter, Context applicationContext, Activity_ProfileView activity_profileView) {

        this.Wish_NAME=wishlist_trans_name;
        this.Wish_ID=wishlist_trans_id;
        this.context=applicationContext;
        this.userID=userID;
        this.mAdapter=mAdapter;
        this.auth_token=auth_token;
        this.activity_ProfileView=activity_profileView;
        this.userWishlist=userWishlist;
    }


    @Override
    public WishList_Adapter2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_wish2, parent, false);

        return new WishList_Adapter2.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WishList_Adapter2.MyViewHolder holder, final int position) {

        System.out.println("LS WISHLIST  "+Wish_NAME.get(position));
        Typeface avner = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                "Dosis-Medium.ttf");
        holder.title.setTypeface(dosis);
        holder.title.setText(""+Wish_NAME.get(position).replace("|",""));

        holder.DeleteWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();

                new SweetAlertDialog(activity_ProfileView, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Delete "+Wish_NAME.get(position)+" from your wishlist ?")
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
                                Delete_WishList(userWishlist,sDialog);
                            }
                        })
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Wish_NAME.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ConstraintLayout item_layout;
        ImageView DeleteWishList;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.lang_name);
            DeleteWishList = (ImageView) view.findViewById(R.id.delete);
            item_layout = (ConstraintLayout) view.findViewById(R.id.main_layout);

        }
    }

    private void Delete_WishList(List<Prof_UserWishlist> userWishlist, SweetAlertDialog sDialog) {

        progressDialog = new ProgressDialog(activity_ProfileView);
        progressDialog.setMessage("Deleting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<DeleteWishList> call3 = apiInterface.DeleteWishlist(auth_token,userWishlist.get(0).getId().replace("|",""));
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
                                        SharedPreferences.Editor editor = context.getSharedPreferences("LS", MODE_PRIVATE).edit();
                                        editor.putString("from_wallet", "0");
            editor.apply();
                                        Intent in=new Intent(context,Activity_ProfileView.class);
                                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        activity_ProfileView.startActivity(in);
                                        activity_ProfileView.finish();
                                    }
                                })
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);


                    }else if (Code==201){

                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==301||Code==207){

                        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==303){

                        Toast.makeText(context, "Session Expired", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==305){

                        Toast.makeText(context, "Token Missing", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(context,"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<DeleteWishList> call, Throwable t) {
                t.printStackTrace();
                Toast toast= Toast.makeText(context,
                        "Some Error Occured, Contact Our Customer Care", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();
                progressDialog.dismiss();
            }

        });

    }

}