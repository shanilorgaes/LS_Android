package com.orgaes.ls.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.orgaes.ls.Activity_Chat;
import com.orgaes.ls.Activity_Message;
import com.orgaes.ls.Model.Users;
import com.orgaes.ls.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.viewHolder> {

    private Context context;
    private List<Users>mUsers;

    public UsersAdapter(Context context, List<Users> mUsers) {
        this.context = context;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.user_item_layout,parent,false);

        return new UsersAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Users users=mUsers.get(position);
        holder.Username.setText(users.getUsername());
        if (users.getUserImage().equals("default")){
            holder.Userimage.setImageResource(R.drawable.default_icon);
        }else {
            Glide.with(context).load(users.getUserImage()).into(holder.Userimage);

        }

        holder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();

                Intent in=new Intent(context, Activity_Message.class);
                in.putExtra("userid",users.getId());
                in.putExtra("userimage",users.getUserImage());
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(in);

            }
        });


    }


    public class viewHolder extends RecyclerView.ViewHolder{

        TextView Username;
        CircleImageView Userimage;
        ConstraintLayout item_layout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            Username=itemView.findViewById(R.id.usersname);
            Userimage=itemView.findViewById(R.id.userimage);
            item_layout=itemView.findViewById(R.id.item_layout);

        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
