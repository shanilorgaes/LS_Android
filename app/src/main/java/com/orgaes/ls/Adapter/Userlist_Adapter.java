package com.orgaes.ls.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.orgaes.ls.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Userlist_Adapter extends RecyclerView.Adapter<Userlist_Adapter.MyViewHolder> {

    Context context;
    List<String>users=new ArrayList<>();

    public Userlist_Adapter(Context context, List<String> mUsers) {

        this.context=context;
        this.users=mUsers;

    }

    @Override
    public Userlist_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Userlist_Adapter.MyViewHolder holder, final int position) {
        holder.Usersname.setText(users.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView UsersImage;
        TextView Usersname;

        public MyViewHolder(View view) {
            super(view);

            UsersImage=view.findViewById(R.id.userimage);
            Usersname=view.findViewById(R.id.usersname);

        }
    }

}