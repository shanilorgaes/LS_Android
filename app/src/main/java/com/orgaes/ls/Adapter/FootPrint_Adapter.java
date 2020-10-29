package com.orgaes.ls.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.orgaes.ls.Activity_FootprintDetails;
import com.orgaes.ls.Activty_Footprints;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.FootPrintData.FootprintData;

import java.util.ArrayList;
import java.util.List;

public class FootPrint_Adapter extends RecyclerView.Adapter<FootPrint_Adapter.MyViewHolder> {


    Context context;
    String userID1;
    Activty_Footprints activity_editions1;
    Dialog dialog;
    FootPrint_Adapter adapter;
    List<FootprintData>footPrint_data=new ArrayList<>();

    List<String>Name=new ArrayList<>();
    List<String>Date=new ArrayList<>();
    List<String>Time=new ArrayList<>();
    List<String>Type=new ArrayList<>();

    public FootPrint_Adapter(List<FootprintData> footprintDetails, List<String> type, List<String> date, List<String> time, List<String> name1, Context applicationContext, Activty_Footprints activty_footprints) {

        this.footPrint_data=footprintDetails;
        this.context=applicationContext;
        this.activity_editions1=activty_footprints;
        this.Date=date;
        this.Time=time;
        this.Type=type;
        this.Name=name1;
    }
    @Override
    public FootPrint_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_footprint_item, parent, false);

        return new FootPrint_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FootPrint_Adapter.MyViewHolder holder, final int position) {
        if (position % 2 == 0) {
            holder.constraintLayout3.setBackgroundResource(R.drawable.dark_pink);
        } else {
            holder.constraintLayout3.setBackgroundResource(R.drawable.light_pink);
        }
        Typeface avner = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                "Dosis-Medium.ttf");

        holder.Date.setTypeface(avner);
        holder.ItemName.setTypeface(avner);
        holder.Time.setTypeface(avner);


        holder.Date.setText(Date.get(position));
        holder.ItemName.setText(Name.get(position));
        holder.Time.setText(Time.get(position));
        holder.constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                Intent in=new Intent(context, Activity_FootprintDetails.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                in.putExtra("Itemname",footPrint_data.get(position).getType());
                in.putExtra("date",footPrint_data.get(position).getDate());
                in.putExtra("time",footPrint_data.get(position).getTime());
                in.putExtra("id",footPrint_data.get(position).getId());
                in.putExtra("type",footPrint_data.get(position).getType());
                activity_editions1.startActivity(in);

            }
        });

    }

    @Override
    public int getItemCount() {
        return Date.size();
    }

    public void Update(String ITEM, List<String> date, List<String> time, List<String> type, FootPrint_Adapter mAdapter) {

        this.Time=time;
        this.Date=date;
        this.Type=type;
        this.Name=type;
//        this.Image=image;
        this.adapter=mAdapter;
        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ItemName,Date,Time;
        ImageView ItemImage;
        ConstraintLayout constraintLayout3;
        public MyViewHolder(View view) {
            super(view);

            ItemName=view.findViewById(R.id.itemname);
            Date=view.findViewById(R.id.date);
            Time=view.findViewById(R.id.timer);
            ItemImage=view.findViewById(R.id.userpic);
            constraintLayout3=view.findViewById(R.id.constraintLayout3);

        }
    }

}