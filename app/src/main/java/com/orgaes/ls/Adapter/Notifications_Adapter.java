package com.orgaes.ls.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.orgaes.ls.Activity_notifications;
import com.orgaes.ls.R;

import java.util.ArrayList;
import java.util.List;

public class Notifications_Adapter extends RecyclerView.Adapter<Notifications_Adapter.MyViewHolder> {


    List<String> notification_id=new ArrayList<>();
    List<String>notification_name=new ArrayList<>();
    List<String>edition=new ArrayList<>();
    List<String>category=new ArrayList<>();
    List<String>gender_Id=new ArrayList<>();
    List<String>age_group=new ArrayList<>();
    List<String>wishlist_trans_id=new ArrayList<>();
    List<String>wishlist_trans_master_id=new ArrayList<>();
    List<String>wishlist_trans_language_code=new ArrayList<>();
    List<String>wishlist_trans_name=new ArrayList<>();
    List<String>edition_id=new ArrayList<>();
    List<String>country_id=new ArrayList<>();
    List<String>state_id=new ArrayList<>();
    List<String>district=new ArrayList<>();
    List<String>location=new ArrayList<>();
    List<String>pincode=new ArrayList<>();
    List<String>language=new ArrayList<>();
    List<String>edition_name=new ArrayList<>();
    List<String>x1_latitude=new ArrayList<>();
    List<String>y1_longitude=new ArrayList<>();
    List<String>x2_latitude=new ArrayList<>();
    List<String>y2_longitude=new ArrayList<>();
    List<String>distance=new ArrayList<>();
    List<String>unit=new ArrayList<>();
    Context context;
    Activity_notifications activity_notifications1;

    public Notifications_Adapter(List<String> notification_id, List<String> notification_name,
                                 List<String> edition, List<String> category, List<String> gender_Id, List<String> age_group, List<String> wishlist_trans_id, List<String> wishlist_trans_master_id,
                                 List<String> wishlist_trans_language_code, List<String> wishlist_trans_name, List<String> edition_id, List<String> country_id, List<String> state_id,
                                 List<String> district, List<String> location, List<String> pincode, List<String> language, List<String> edition_name, List<String> x1_latitude,
                                 List<String> y1_longitude, List<String> x2_latitude, List<String> y2_longitude, List<String> distance, List<String> unit,
                                 Context applicationContext, Activity_notifications activity_notifications) {

        this.notification_id = notification_id;
        this.notification_name = notification_name;
        this.edition = edition;
        this.category = category;
        this.gender_Id = gender_Id;
        this.age_group = age_group;
        this.wishlist_trans_id = wishlist_trans_id;
        this.wishlist_trans_master_id = wishlist_trans_master_id;
        this.wishlist_trans_language_code = wishlist_trans_language_code;
        this.wishlist_trans_name = wishlist_trans_name;
        this.edition_id = edition_id;
        this.country_id = country_id;
        this.state_id = state_id;
        this.district = district;
        this.location = location;
        this.pincode = pincode;
        this.language = language;
        this.edition_name = edition_name;
        this.x1_latitude = x1_latitude;
        this.y1_longitude = y1_longitude;
        this.x2_latitude = x2_latitude;
        this.y2_longitude = y2_longitude;
        this.distance = distance;
        this.unit = unit;
        this.context=applicationContext;
        this.activity_notifications1=activity_notifications;
    }

    @Override
    public Notifications_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_notifications_items, parent, false);

        return new Notifications_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Notifications_Adapter.MyViewHolder holder, final int position) {



        Typeface avner = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                "Dosis-Medium.ttf");

        holder.title.setTypeface(dosis);

        holder.title.setText(notification_name.get(position));
        if (position % 2 == 0) {
            holder.item_layout.setBackgroundColor(Color.parseColor("#fcffa3"));
        } else {
            holder.item_layout.setBackgroundColor(Color.parseColor("#cefa91"));
        }
    }

    @Override
    public int getItemCount() {
        return notification_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ConstraintLayout item_layout;
        ImageView More,Close;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.noti_txt);
            item_layout = (ConstraintLayout) view.findViewById(R.id.main_layout);
            Close = (ImageView) view.findViewById(R.id.ic_close);
            More = (ImageView) view.findViewById(R.id.more);


        }
    }
}