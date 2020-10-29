package com.orgaes.ls.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.orgaes.ls.Activity_Extend;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.ExtendTime.Extend_Times_Extend;

import java.util.List;

public class ExtendTime_Adapter extends RecyclerView.Adapter<ExtendTime_Adapter.MyViewHolder> {

    Context context;
    Activity_Extend activity_extend;
    List<Extend_Times_Extend> extend_times_extends;
    String AssignedItem;
    String Auth_Key;
    int row_index;
    int clicked_position=-1;
    Colorchange colorchange;
    TextView textView;
    public ExtendTime_Adapter(TextView ex_buttontxt, String auth_token, List<Extend_Times_Extend> extend_times_extends, Activity_Extend activity_extend, Context applicationContext, Colorchange colorchange1) {
        this.Auth_Key=auth_token;
        this.extend_times_extends=extend_times_extends;
        this.activity_extend=activity_extend;
        this.context=applicationContext;
        this.colorchange=colorchange1;
        this.textView=ex_buttontxt;

    }


    @NonNull
    @Override
    public ExtendTime_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ex_time_sub, parent, false);

        return new ExtendTime_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ExtendTime_Adapter.MyViewHolder holder, int position) {
        Typeface avner = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                "Dosis-Medium.ttf");

        holder.txt_time.setTypeface(dosis);
        holder.txt_coins.setTypeface(dosis);
        holder.txtassign.setTypeface(dosis);
        System.out.println("LS COLORS   "+extend_times_extends.get(position).getStatus());
        if (extend_times_extends.get(position).getStatus().equals("assigned")){
//            holder.txtassign.setText("ASSIGNED");
            holder.alert.setImageResource(R.drawable.green_bg_assign);
            holder.txt_time.setText(extend_times_extends.get(position).getDuration());
            holder.txt_coins.setText(extend_times_extends.get(position).getCoin());
            holder.txtassign.setTextColor(context.getResources().getColor(R.color.DarkOrange));
            holder.txt_time.setTextColor(context.getResources().getColor(R.color.DarkOrange));
            holder.txt_coins.setTextColor(context.getResources().getColor(R.color.DarkOrange));
            System.out.println("LS EXT VALUES  3 "+Constants.CONST_EXTVALUE);
        }else {
//            holder.txtassign.setText("ASSIGN");
            holder.alert.setImageResource(R.drawable.red_bg_assisgn);
            holder.txt_time.setText(extend_times_extends.get(position).getDuration());
            holder.txt_coins.setText(extend_times_extends.get(position).getCoin());
            holder.txtassign.setTextColor(context.getResources().getColor(R.color.Gray));
            holder.txt_time.setTextColor(context.getResources().getColor(R.color.Gray));
            holder.txt_coins.setTextColor(context.getResources().getColor(R.color.Gray));
            System.out.println("LS EXT VALUES  4 "+Constants.CONST_EXTVALUE);
        }

        holder.txtassign.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
            mp.start();
            row_index=position;
            colorchange.colors(position);
            AssignedItem=extend_times_extends.get(position).getId();
            System.out.println("LS EXTEND ITEM   "+AssignedItem);
            Constants.CONST_EXTVALUE=AssignedItem;
            textView.setText("EXTEND TIME");
        });

        if (clicked_position==position){
            if (holder.txtassign.getText().toString().equals("ASSIGNED")){
                holder.txtassign.setText("ASSIGN");
                holder.alert.setImageResource(R.drawable.red_bg_assisgn);
                holder.txt_time.setText(extend_times_extends.get(position).getDuration());
                holder.txt_coins.setText(extend_times_extends.get(position).getCoin());
                holder.txtassign.setTextColor(context.getResources().getColor(R.color.Gray));
                holder.txt_time.setTextColor(context.getResources().getColor(R.color.Gray));
                holder.txt_coins.setTextColor(context.getResources().getColor(R.color.Gray));
            }else {
                holder.txtassign.setText("ASSIGNED");
                holder.alert.setImageResource(R.drawable.green_bg_assign);
                holder.txt_time.setText(extend_times_extends.get(position).getDuration());
                holder.txt_coins.setText(extend_times_extends.get(position).getCoin());
                holder.txtassign.setTextColor(context.getResources().getColor(R.color.DarkOrange));
                holder.txt_time.setTextColor(context.getResources().getColor(R.color.DarkOrange));
                holder.txt_coins.setTextColor(context.getResources().getColor(R.color.DarkOrange));
            }
        }else {
            holder.txtassign.setText("ASSIGN");
            holder.alert.setImageResource(R.drawable.red_bg_assisgn);
            holder.txt_time.setText(extend_times_extends.get(position).getDuration());
            holder.txt_coins.setText(extend_times_extends.get(position).getCoin());
            holder.txtassign.setTextColor(context.getResources().getColor(R.color.Gray));
            holder.txt_time.setTextColor(context.getResources().getColor(R.color.Gray));
            holder.txt_coins.setTextColor(context.getResources().getColor(R.color.Gray));
        }
        if (extend_times_extends.get(position).getStatus().equals("assigned")){
            holder.txtassign.setText("ASSIGNED");
            holder.alert.setImageResource(R.drawable.green_bg_assign);
            holder.txt_time.setText(extend_times_extends.get(position).getDuration());
            holder.txt_coins.setText(extend_times_extends.get(position).getCoin());
            holder.txtassign.setTextColor(context.getResources().getColor(R.color.DarkOrange));
            holder.txt_time.setTextColor(context.getResources().getColor(R.color.DarkOrange));
            holder.txt_coins.setTextColor(context.getResources().getColor(R.color.DarkOrange));
        }

    }

    public void put_colors(int position) {

        clicked_position=position;
        notifyDataSetChanged();

    }

    public interface Colorchange{

        public void colors(int position);

    }

    @Override
    public int getItemCount() {
        return extend_times_extends.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_time;
        TextView txt_coins;
        TextView txtassign;
        ImageView alert;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_time = itemView.findViewById(R.id.txt_time);
            txt_coins = itemView.findViewById(R.id.txt_coins);
            txtassign = itemView.findViewById(R.id.txtassign);
            alert = itemView.findViewById(R.id.alert_icon);

        }
    }
}