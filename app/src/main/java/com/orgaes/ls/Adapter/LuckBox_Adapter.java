package com.orgaes.ls.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.orgaes.ls.Activity_LuckBox;
import com.orgaes.ls.Activity_Luck_Redeem;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.QRCODE.QrGeneratorActivity;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.BOX.BOX_BoxItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LuckBox_Adapter extends RecyclerView.Adapter<LuckBox_Adapter.ViewHolder>{

    List<BOX_BoxItem> luckBoxes;
    Activity_LuckBox luckBox;
    Context context;
    List<String>ITEMNAME;
    List<String>EDITION;
    List<String>DATE;
    List<String>IMAGE;
    List<String>ID;
    List<String>ITEMID;
    List<String>TYPE;
    LuckBox_Adapter mAdapt;
    public LuckBox_Adapter(List<BOX_BoxItem> users, List<String> date, List<String> image, List<String> type, List<String> ITEMNAME,List<String> EDITIONNAME,
                           List<String> ITEMID, List<String> ID, Activity_LuckBox activity_luckBox, Context applicationContext) {

        this.luckBoxes=users;
        this.DATE=date;
        this.IMAGE=image;
        this.EDITION=EDITIONNAME;
        this.TYPE=type;
        this.ID=ID;
        this.ITEMID=ITEMID;
        this.ITEMNAME=ITEMNAME;
        this.context=applicationContext;
        this.luckBox=activity_luckBox;
    }


    @NonNull
    @Override
    public LuckBox_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.luck_box_sub, parent, false);
        return new ViewHolder(listItem);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull final LuckBox_Adapter.ViewHolder holder, final int position) {
        switch (TYPE.get(position)) {
            case "luck":
                holder.MainLay.setBackgroundResource(R.drawable.redeemred);
                holder.Status.setBackgroundResource(R.drawable.l);
                break;
            case "luck_radar":
                holder.MainLay.setBackgroundResource(R.drawable.redeemyello);
                holder.Status.setBackgroundResource(R.drawable.r);
                break;
            case "exchange_item":
                holder.MainLay.setBackgroundResource(R.drawable.redeemblue);
                holder.Status.setBackgroundResource(R.drawable.e);
                break;
                case "draw":
                holder.MainLay.setBackgroundResource(R.drawable.redeemblue);
                holder.Status.setBackgroundResource(R.drawable.e);
                break;
        }
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "dd-MM-yyyy hh:mm:ss");
            // Please here set your event date//YYYY-MM-DD
            Date futureDate = null;
            System.out.println("LS DATES CHECK   "+DATE.get(position));
            futureDate = dateFormat.parse(DATE.get(position));

            Date currentDate = new Date();
            if (!currentDate.after(futureDate)) {
                assert futureDate != null;
                long diff = futureDate.getTime()
                        - currentDate.getTime();
                long days = diff / (24 * 60 * 60 * 1000);
                diff -= days * (24 * 60 * 60 * 1000);
                long hours = diff / (60 * 60 * 1000);
                diff -= hours * (60 * 60 * 1000);
                long minutes = diff / (60 * 1000);
                diff -= minutes * (60 * 1000);
                long seconds = diff / 1000;
                holder.Txtday.setText("" + String.format("%02d", days));
                holder.TxtHour.setText("" + String.format("%02d", hours));
                holder.TxtMin.setText(""
                        + String.format("%02d", minutes));
                holder.TxtSec.setText(""
                        + String.format("%02d", seconds));
            } else {
//                Toast.makeText(luckBox, "Expired", Toast.LENGTH_SHORT).show();
            }
            Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + IMAGE.get(position)).into(holder.ImageIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.MainLay.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
            mp.start();

            Intent in=new Intent(luckBox, Activity_Luck_Redeem.class);
                Bundle bn=new Bundle();
                bn.putString("Date",DATE.get(position));
                bn.putString("Image",IMAGE.get(position));
                bn.putString("Type",TYPE.get(position));
                bn.putString("Name",ITEMNAME.get(position));
                bn.putString("ID",ID.get(position));
                bn.putString("ItemID",ITEMID.get(position));
                bn.putString("Edition",EDITION.get(position));
                in.putExtras(bn);
                luckBox.startActivity(in);
        });

    }

    public void Update(List<String> date1, List<String> image1, List<String> itemname1, List<String> edition1, List<String> type1, LuckBox_Adapter mAdapter, List<String> ITEMID1, List<String> ID1) {

        this.IMAGE=image1;
        this.DATE=date1;
        this.ITEMNAME=itemname1;
        this.EDITION=edition1;
        this.TYPE=type1;
        this.ITEMID=ITEMID1;
        this.ID=ID1;
        this.mAdapt=mAdapter;
        notifyDataSetChanged();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout MainLay;
        ImageView ImageIcon;
        ImageView Status;
        TextView Txtday,TxtHour,TxtSec,TxtMin;

        public ViewHolder(View itemView) {
            super(itemView);

            MainLay=itemView.findViewById(R.id.mainlayout);
            ImageIcon=itemView.findViewById(R.id.userimage);
            Status=itemView.findViewById(R.id.status);
            Txtday=itemView.findViewById(R.id.txtDay);
            TxtHour=itemView.findViewById(R.id.txtHour);
            TxtSec=itemView.findViewById(R.id.txtSecond);
            TxtMin=itemView.findViewById(R.id.txtMinute);
        }
    }
    @Override
    public int getItemCount() {
        return DATE.size();
    }


    @Override
    public int getItemViewType(int position) {

        return position;
    }

}
