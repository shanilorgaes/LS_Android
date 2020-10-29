package com.orgaes.ls.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.orgaes.ls.Activity_ProfileView;
import com.orgaes.ls.Activity_Wishlist_Master;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.WishlistMASTERDATA.WishlistMasterWishlistUser;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishList_AdapterMaster extends RecyclerView.Adapter<WishList_AdapterMaster.MyViewHolder> {


    List<WishlistMasterWishlistUser>Wishlist=new ArrayList<>();
    String userID;
    ProgressDialog progressDialog;
    Activity_Wishlist_Master activity_ProfileView;
    Context context;
    WishList_AdapterMaster mAdapter;
    Activity_Wishlist_Master.RecyclerViewDeleteClicklistner mListener;
    public WishList_AdapterMaster(List<WishlistMasterWishlistUser> wishlists, String userID, WishList_AdapterMaster mAdapter,
                                  Context applicationContext, Activity_Wishlist_Master activity_profileView, Activity_Wishlist_Master.RecyclerViewDeleteClicklistner listener) {

        this.context=applicationContext;
        this.userID=userID;
        this.Wishlist=wishlists;
        this.mAdapter=mAdapter;
        this.mListener=listener;
        this.activity_ProfileView=activity_profileView;
    }


    @Override
    public WishList_AdapterMaster.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_wish2, parent, false);

        return new WishList_AdapterMaster.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WishList_AdapterMaster.MyViewHolder holder, final int position) {

        Typeface avner = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                "Dosis-Medium.ttf");
        holder.title.setTypeface(avner);

        holder.title.setText(""+Wishlist.get(position).getWishName().replace("|",""));

        holder.DeleteWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();

                mListener.onClickDelete(userID,Wishlist.get(position).getId(),position,Wishlist.get(position).getWishName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return Wishlist.size();
    }

    public void updateList(List<WishlistMasterWishlistUser> wishlists) {
        
        this.Wishlist=wishlists;
        this.notifyDataSetChanged();

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



}