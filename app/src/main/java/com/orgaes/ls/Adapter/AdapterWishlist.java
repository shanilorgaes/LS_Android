package com.orgaes.ls.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.orgaes.ls.Activity_Register;
import com.orgaes.ls.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterWishlist extends RecyclerView.Adapter<AdapterWishlist.viewHolder> {

    private Context context;
    List<String>ShowList=new ArrayList<>();
    Activity_Register activity_register;

    public AdapterWishlist(Activity_Register activity_register, List<String> showlist) {

        this.activity_register=activity_register;
        this.ShowList=showlist;

    }


    @NonNull
    @Override
    public AdapterWishlist.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(activity_register).inflate(R.layout.text_item,parent,false);

        return new AdapterWishlist.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWishlist.viewHolder holder, int position) {


        Typeface dosis = Typeface.createFromAsset(activity_register.getAssets(),
                "Dosis-Medium.ttf");
        Typeface avner = Typeface.createFromAsset(activity_register.getAssets(),
                "AvenirLTStd-Light.otf");

        holder.Username.setTypeface(dosis);

        holder.Username.setText(ShowList.get(position));

    }


    public class viewHolder extends RecyclerView.ViewHolder{

        TextView Username;

        ConstraintLayout item_layout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            Username=itemView.findViewById(R.id.usersname);
            item_layout=itemView.findViewById(R.id.item_layout);

        }
    }

    @Override
    public int getItemCount() {
        return ShowList.size();
    }
}
