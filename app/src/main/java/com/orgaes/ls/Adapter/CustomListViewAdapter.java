package com.orgaes.ls.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.orgaes.ls.Activity_Message;
import com.orgaes.ls.R;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewAdapter extends ArrayAdapter<String> {


    Context context;
    List<String>ITEMS=new ArrayList<>();
    List<String>ITEMIMAGES=new ArrayList<>();
    List<String>Userid=new ArrayList<>();

    public CustomListViewAdapter(Context context, int resourceId, //resourceId=your layout
                                 List<String> items, List<String> mUserImage, List<String> userID) {
        super(context, resourceId, items);
        this.context = context;
        this.ITEMS = items;
        this.ITEMIMAGES = mUserImage;
        this.Userid = userID;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        ConstraintLayout item_layout;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        String rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.user_item_layout, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.usersname);
            holder.imageView = (ImageView) convertView.findViewById(R.id.userimage);
            holder.item_layout = (ConstraintLayout) convertView.findViewById(R.id.item_layout);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(ITEMS.get(position));
        Glide.with(context).load(ITEMIMAGES.get(position)).into(holder.imageView);
        holder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                Intent in=new Intent(getContext(), Activity_Message.class);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bn=new Bundle();
                bn.putString("username",ITEMS.get(position));
                bn.putString("userimage",ITEMIMAGES.get(position));
                bn.putString("userid",Userid.get(position));
                in.putExtras(bn);
                getContext().startActivity(in);

            }
        });

        return convertView;
    }
}