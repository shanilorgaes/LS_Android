package com.orgaes.ls.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orgaes.ls.Adapter.UsersAdapter;
import com.orgaes.ls.Model.ChatList;
import com.orgaes.ls.Model.Users;
import com.orgaes.ls.R;

import java.util.ArrayList;
import java.util.List;

public class chat_fragment extends Fragment {

    RecyclerView recyclerView;
    UsersAdapter usersAdapter;
    List<Users>mUsers=new ArrayList<>();
    FirebaseUser fuser;
    DatabaseReference reference;
    List<ChatList>mChatlist=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chat_fragment, container, false);
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        recyclerView=view.findViewById(R.id.recent_chat_details);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        reference= FirebaseDatabase.getInstance().getReference("ChatList").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mChatlist.clear();
                //loop for all users
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    ChatList chatList=snapshot.getValue(ChatList.class);
                    mChatlist.add(chatList);

                }
                CHATLIST();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void CHATLIST(){

        //Getting all chats
        mUsers=new ArrayList<>();
        reference=FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    Users users=snapshot.getValue(Users.class);
                    for (ChatList chatList:mChatlist){
                        if (users.getId().equals(chatList.getId())){
                            mUsers.add(users);
                        }
                    }
                }
                usersAdapter=new UsersAdapter(getContext(),mUsers);
                recyclerView.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}