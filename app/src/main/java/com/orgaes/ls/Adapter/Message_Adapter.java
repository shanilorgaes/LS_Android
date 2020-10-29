package com.orgaes.ls.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orgaes.ls.Activity_Message;
import com.orgaes.ls.Model.Chat;
import com.orgaes.ls.Model.Users;
import com.orgaes.ls.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Message_Adapter extends RecyclerView.Adapter<Message_Adapter.MyViewHolder> {

    List<Users>mUsers=new ArrayList<>();
    List<Chat>mChat=new ArrayList<>();
    public static final int CHAT_RIGHT=1;
    public static final int CHAT_LEFT=0;
    Activity_Message activity_message;
    String imageURL;
    FirebaseUser fuser;
    DatabaseReference mref;

    public Message_Adapter(Activity_Message activity_message, List<Chat> chats, String imageurl) {

        this.activity_message=activity_message;
        this.mChat =chats;
        this.imageURL=imageurl;

    }

    @Override
    public Message_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType==CHAT_RIGHT){
             itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_right, parent, false);


        }else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_left, parent, false);
        }
        return new Message_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Message_Adapter.MyViewHolder holder, final int position) {

        Chat chat=mChat.get(position);
        holder.ItemNAME.setText(chat.getMessages());

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ItemNAME;

        public MyViewHolder(View view) {
            super(view);

            ItemNAME=view.findViewById(R.id.usersname);

        }
    }


    @Override
    public int getItemViewType(int position) {

        fuser= FirebaseAuth.getInstance().getCurrentUser();

        if (mChat.get(position).getSender().equals(fuser.getUid())){
            return CHAT_RIGHT;
        }else {
            return CHAT_LEFT;
        }
    }


}