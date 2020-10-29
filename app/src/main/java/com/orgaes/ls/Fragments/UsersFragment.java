package com.orgaes.ls.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orgaes.ls.Adapter.CustomListViewAdapter;
import com.orgaes.ls.Adapter.Userlist_Adapter;
import com.orgaes.ls.Adapter.UsersAdapter;
import com.orgaes.ls.Model.Users;
import com.orgaes.ls.R;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {

    RecyclerView recyclerView;
    UsersAdapter userlist_adapter;
    FirebaseUser fuser;


    List<Users>mUsers=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_users, container, false);

        recyclerView=view.findViewById(R.id.userslist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        ReadUsers();
                return view;
    }

    private void ReadUsers() {
        fuser=FirebaseAuth.getInstance().getCurrentUser();
        mUsers.clear();
        final  FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mref=FirebaseDatabase.getInstance().getReference("Users");

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Users users=snapshot.getValue(Users.class);
                    if (!users.getId().equals(firebaseUser.getUid())){
                        mUsers.add(users);
                    }
                    userlist_adapter=new UsersAdapter(getContext(),mUsers);
                    recyclerView.setAdapter(userlist_adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}