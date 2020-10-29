package com.orgaes.ls.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.orgaes.ls.Activity_LuckRadar;
import com.orgaes.ls.Activity_LuckRadar_Claim;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.Luck_Radar_Data.LuckRadar_Details;

import java.util.ArrayList;
import java.util.List;

public class Luckradar_Adapter extends RecyclerView.Adapter<Luckradar_Adapter.MyViewHolder> {

    List<LuckRadar_Details>Luckradar=new ArrayList<>();
    Context context;
    Activity_LuckRadar activity_luckRadar;
    Dialog dialogradar;
    Handler handlerluck;
    Runnable runnableluck;
    String UserID;
    String itemName;
    String auth_token;
    int UserCoins;

    public Luckradar_Adapter(String auth_token, String userID, List<LuckRadar_Details> radar, int userCoins, String itemName, Context applicationContext, Activity_LuckRadar activity_luckRadar) {

        this.Luckradar=radar;
        this.UserCoins=userCoins;
        this.UserID=userID;
        this.context=applicationContext;
        this.itemName=itemName;
        this.auth_token=auth_token;
        this.activity_luckRadar=activity_luckRadar;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_luckradar_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        int Coins= Integer.parseInt((Luckradar.get(position).getUnitCoinCost()));
        int CoinsReq= Coins-UserCoins;



        Typeface avner = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                "Dosis-Medium.ttf");

        holder.ItemCoin.setTypeface(dosis);
        holder.ItemNAME.setTypeface(dosis);
        holder.ItemPrice.setTypeface(dosis);
        holder.Itemdate.setTypeface(dosis);

        holder.textcoin.setText(""+Luckradar.get(position).getUnitCoinCost());
        Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + Luckradar.get(position).getImage()).into(holder.userprofilepic);
        if (CoinsReq<0){
            holder.ItemCoin.setText("You can buy it with your coins");
        }else {
            holder.ItemCoin.setText(Luckradar.get(position).getUnitCoinCost()+" Coins Required");
        }

        holder.ItemNAME.setText(Luckradar.get(position).getName());
        holder.ItemPrice.setText(Luckradar.get(position).getClientUserFname()+" "+Luckradar.get(position).getClientUserLname());

        holder.Itemdate.setText(Luckradar.get(position).getEndDate());

        holder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();

/*

                ViewDetails(Luckradar.get(position).getLrImage(),Luckradar.get(position).getClientUserFname(),
                        Luckradar.get(position).getClientUserLname(),Luckradar.get(position).getLrCoins(),Luckradar.get(position).getLrDescription(),
                        Luckradar.get(position).getLrEndDate(),Luckradar.get(position).getLrClaimDate(),Luckradar.get(position).getClientUserId(),Luckradar.get(position).getLrId(),UserID);
*/
                int itemCoin= Integer.parseInt(Luckradar.get(position).getUnitCoinCost());
                int balance_coin=UserCoins-itemCoin;
                String CoinTotal= String.valueOf(balance_coin);
                if (itemCoin>UserCoins){
                    Toast toast = Toast.makeText(context,"You have no coins to buy this item", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else {
                try {
                    Intent in=new Intent(context, Activity_LuckRadar_Claim.class);
                    in.putExtra("sponsorname",Luckradar.get(position).getClientUserFname()+" "+Luckradar.get(position).getClientUserLname());
                    in.putExtra("redeemdate",Luckradar.get(position).getEndDate());
                    in.putExtra("collectioncenter",Luckradar.get(position).getCollectionCenter());
                    in.putExtra("details",Luckradar.get(position).getDescription());
                    in.putExtra("itempic",Luckradar.get(position).getImage());
                    in.putExtra("lrdi",Luckradar.get(position).getId());
//                in.putExtra("clientuserid",Luckradar.get(position).getClientUserId());
                    in.putExtra("itemName",itemName);
                    in.putExtra("balance_coin",CoinTotal);
                    in.putExtra("coin_value", "1000");
                    in.putExtra("itemamount",Luckradar.get(position).getUnitCoinCost());
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity_luckRadar.startActivity(in);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
            }
        });


    }

    @Override
    public int getItemCount() {
        return Luckradar.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ItemNAME,ItemCoin,ItemPrice,Itemdate,textcoin;
        ConstraintLayout item_layout;
        ImageView userprofilepic;

        public MyViewHolder(View view) {
            super(view);

            ItemNAME=view.findViewById(R.id.itemname);
            ItemCoin=view.findViewById(R.id.itemcoin);
            ItemPrice=view.findViewById(R.id.itemprice);
            Itemdate=view.findViewById(R.id.itemdate);
            item_layout=view.findViewById(R.id.main_layout);
            textcoin=view.findViewById(R.id.textcoin);
            userprofilepic=view.findViewById(R.id.profilepic);


        }
    }


}