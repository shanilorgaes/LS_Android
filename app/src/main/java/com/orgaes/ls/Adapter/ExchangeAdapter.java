package com.orgaes.ls.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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
import com.orgaes.ls.Activity_Exchange;
import com.orgaes.ls.Activity_Exchange_Claim;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.Exchange_Items.EX_Item;

import java.util.ArrayList;
import java.util.List;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.MyViewHolder> {


    Context context;
    Activity_Exchange activity_exchange;

    List<EX_Item>exchangeItems=new ArrayList<>();
    Dialog dialogExchange;
    Dialog dialogclaim;
    String UserID,itemName;
    Runnable runnableluck;
    Handler handlerluck;
    String userCoins;


    public ExchangeAdapter(List<EX_Item> exchangeItems, String itemName, String sessionID, String userCoins, Context applicationContext, Activity_Exchange activity_exchange) {

        this.context=applicationContext;
        this.UserID=sessionID;
        this.activity_exchange=activity_exchange;
        this.exchangeItems=exchangeItems;
        this.itemName=itemName;
        this.userCoins=userCoins;

    }

    @Override
    public ExchangeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_exchange_item, parent, false);

        return new ExchangeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExchangeAdapter.MyViewHolder holder, final int position) {

        holder.ItemAmount.setText(exchangeItems.get(position).getUnitCoinCost());
        Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + exchangeItems.get(position).getImage()).into(holder.ItemImage);

        holder.exchangeitem.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
            mp.start();
            int userCoin= (Constants.Const_UserCoins);
            int itemCoin= Integer.parseInt(exchangeItems.get(position).getUnitCoinCost());
            int balance_coin=userCoin-itemCoin;

            if (balance_coin>0){
                Intent in=new Intent(context, Activity_Exchange_Claim.class);
                Bundle bn=new Bundle();
                bn.putString("totalcoin",exchangeItems.get(position).getUnitCoinCost());
                bn.putString("coinvalue",Constants.Const_UserCoinValue);
                bn.putString("exchange_sponsor",exchangeItems.get(position).getClientUserFname()+exchangeItems.get(position).getClientUserLname());
                bn.putString("exchange_image",exchangeItems.get(position).getImage());
                bn.putString("item_details",exchangeItems.get(position).getDescription());
                bn.putString("item_itemID",exchangeItems.get(position).getId());
                bn.putString("item_date",exchangeItems.get(position).getEndDate());
                bn.putString("item_edition_name",itemName);
                bn.putString("userid",UserID);
                bn.putString("item_collection_center",exchangeItems.get(position).getCollectionCenter());
                in.putExtras(bn);
                activity_exchange.startActivity(in);
                activity_exchange.finish();
            }else {
                Toast toast = Toast.makeText(context,"You have no coins to exchange this item", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return exchangeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ItemAmount;
        ImageView ItemImage;
        ConstraintLayout exchangeitem;

        public MyViewHolder(View view) {
            super(view);

            ItemAmount=view.findViewById(R.id.itemamount);
            ItemImage=view.findViewById(R.id.itempic);
            exchangeitem=view.findViewById(R.id.exchangeitem);

        }
    }
/*

    private void LuckClaim(String userID, String exItemId, TextView txtDay, TextView txtHour, TextView txtMinute, TextView txtSecond, TextView tvEventStart, String exItemEndDate) {


        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ExchangeClaim> call3 = apiInterface.Api_Claim_Exchnage("adhf-236b-dhfh-gdfv-3cjh", "exchange_item_claim",userID,exItemId );
        call3.enqueue(new Callback<ExchangeClaim>() {
            @Override
            public void onResponse(Call<ExchangeClaim> call, Response<ExchangeClaim> response) {

                try {

                    int Code=response.body().getCode();
                    if (Code==200){

                        Toast toast = Toast.makeText(context,"Successfully claimed your luck", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        handlerluck = new Handler();
                        runnableluck = new Runnable() {
                            @Override
                            public void run() {
                                handlerluck.postDelayed(this, 1000);
                                try {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                            "dd-MM-yyyy hh:mm:ss");
                                    // Please here set your event date//YYYY-MM-DD
                                    Date futureDate = dateFormat.parse(exItemEndDate);
                                    Date currentDate = new Date();
                                    if (!currentDate.after(futureDate)) {
                                        long diff = futureDate.getTime()
                                                - currentDate.getTime();
                                        long days = diff / (24 * 60 * 60 * 1000);
                                        diff -= days * (24 * 60 * 60 * 1000);
                                        long hours = diff / (60 * 60 * 1000);
                                        diff -= hours * (60 * 60 * 1000);
                                        long minutes = diff / (60 * 1000);
                                        diff -= minutes * (60 * 1000);
                                        long seconds = diff / 1000;
                                        txtDay.setText("" + String.format("%02d", days));
                                        txtHour.setText("" + String.format("%02d", hours));
                                        txtMinute.setText(""
                                                + String.format("%02d", minutes));
                                        txtSecond.setText(""
                                                + String.format("%02d", seconds));
                                    } else {
                                        tvEventStart.setVisibility(View.VISIBLE);
                                        tvEventStart.setText("The event started!");
//                                textViewGone();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        handlerluck.postDelayed(runnableluck, 1 * 1000);
                    }
                }catch (Exception e){


                }

            }

            @Override
            public void onFailure(Call<ExchangeClaim> call, Throwable t) {
                t.printStackTrace();
            }

        });





    }


*/

}