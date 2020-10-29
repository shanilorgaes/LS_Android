package com.orgaes.ls;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orgaes.ls.Adapter.Message_Adapter;
import com.orgaes.ls.Model.Chat;
import com.orgaes.ls.Model.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_Message extends AppCompatActivity {
//
    TextView Username;
    CircleImageView Userimage;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    Intent intent;

    RecyclerView recyclerView;
    EditText edt_Message;
    ImageView SendButton;
    Message_Adapter message_adapter;
    List<Chat>mChat=new ArrayList<>();
    String Userid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        getWindow().setStatusBarColor(ContextCompat.getColor(Activity_Message.this,R.color.chip_default_text_color));

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.layout_chat_page);
        Userimage=findViewById(R.id.userimage);
        Username=findViewById(R.id.username);
        intent=getIntent();
        Userid=intent.getStringExtra("userid");
        String userimage=intent.getStringExtra("userimage");

        recyclerView=findViewById(R.id.chat_data);
        edt_Message=findViewById(R.id.msg_field);
        SendButton=findViewById(R.id.send_button);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Message.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Message.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_Message.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Message.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users").child(Userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Users users=dataSnapshot.getValue(Users.class);
                Username.setText(users.getUsername());
                if (users.getUserImage().equals("default")){
                    Userimage.setImageResource(R.drawable.default_icon);
                }else {
                    Glide.with(getApplicationContext()).load(users.getUserImage()).into(Userimage);
                }
                ReadMessages(firebaseUser.getUid(),Userid,users.getUserImage());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (edt_Message.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"Enter any message", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else {
                    SendMessage(firebaseUser.getUid(),Userid,edt_Message.getText().toString());
                }
                
            }
        });

        ConstraintLayout menu=findViewById(R.id.menu_icon);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getApplicationContext(),Activity_Menu_Page.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                //overridePendingTransition(R.anim.left_right, R.anim.right_left);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void SendMessage(String sender, String reciever, String Messages) {

        DatabaseReference dbref=FirebaseDatabase.getInstance().getReference();

        HashMap<String,String>hashMap=new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("reciever",reciever);
        hashMap.put("Messages",Messages);

        dbref.child("CHATS").push().setValue(hashMap);
        edt_Message.setText("");

        //Adding the user on chat fragment: Latest chat with contacts
        final DatabaseReference chatref=FirebaseDatabase.getInstance().getReference("ChatList").child(firebaseUser.getUid()).child(Userid);
        chatref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    chatref.child("id").setValue(Userid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void ReadMessages(String myid,String userid,String image) {

        mChat=new ArrayList<>();
        reference=FirebaseDatabase.getInstance().getReference("CHATS");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    Chat chat=snapshot.getValue(Chat.class);
                    if (chat.getReciever().equals(myid)&&chat.getSender().equals(userid)||chat.getReciever().equals(userid)&&chat.getSender().equals(myid)){

                        mChat.add(chat);
                    }
                    message_adapter=new Message_Adapter(Activity_Message.this,mChat,image);
                    recyclerView.setAdapter(message_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
