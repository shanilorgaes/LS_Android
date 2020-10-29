package com.orgaes.ls.Adapter;

import android.annotation.SuppressLint;
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

import com.orgaes.ls.Activity_HomePage;
import com.orgaes.ls.Activity_ProfileView;
import com.orgaes.ls.Activity_Wishlist_Master;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.WishlistMASTERDATA.WishlistMasterDetails;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishList_AdapterMastermian extends RecyclerView.Adapter<WishList_AdapterMastermian.MyViewHolder> {


    List<WishlistMasterDetails> Wishlist=new ArrayList<>();
    String userID;
    ProgressDialog progressDialog;
    Activity_Wishlist_Master activity_ProfileView;
    Context context;
    WishList_AdapterMastermian mAdapter;
    WishList_AdapterMaster adapter;
    Activity_Wishlist_Master.RecyclerViewClickListener mListener;
    public WishList_AdapterMastermian(List<WishlistMasterDetails> wishlists, String userID, WishList_AdapterMastermian mAdapter,
                                      WishList_AdapterMaster adapter, Context applicationContext, Activity_Wishlist_Master activity_profileView, Activity_Wishlist_Master.RecyclerViewClickListener listener) {

        this.context=applicationContext;
        this.userID=userID;
        this.mListener=listener;
        this.Wishlist=wishlists;
        this.mAdapter=mAdapter;
        this.activity_ProfileView=activity_profileView;
        this.adapter=adapter;
    }


    @Override
    public WishList_AdapterMastermian.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_wish2, parent, false);

        return new WishList_AdapterMastermian.MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(WishList_AdapterMastermian.MyViewHolder holder, final int position) {
        Typeface avner = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                "Dosis-Medium.ttf");
        holder.title.setTypeface(avner);
        holder.title.setText(""+Wishlist.get(position).getWishName().replace("|",""));

        holder.DeleteWishList.setVisibility(View.GONE);

        holder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();

                mListener.onClick(userID,Wishlist.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return Wishlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ConstraintLayout item_layout;
        ImageView DeleteWishList;
         Activity_Wishlist_Master.RecyclerViewClickListener mListener;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.lang_name);
            DeleteWishList = (ImageView) view.findViewById(R.id.delete);
            item_layout = (ConstraintLayout) view.findViewById(R.id.main_layout);

        }
    }

}