package com.orgaes.ls.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.orgaes.ls.Activity_Choose_Language;
import com.orgaes.ls.Activity_ProfileView;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.AcceptReques.AccptReq;
import com.orgaes.ls.RETROFIT_NEW.Fetch_All_Requests.FetchAllReqData;
import com.orgaes.ls.RETROFIT_NEW.Fetch_All_Requests.FetchAllReqData_Request;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;

public class FirenReqAdapter extends RecyclerView.Adapter<FirenReqAdapter.ViewHolder>{


    Activity_ProfileView activity;
    Context context;

    List<FetchAllReqData_Request> request=new ArrayList<>();
    String userID;
    String token;
    ProgressBar progressBar;


    public FirenReqAdapter(ProgressBar progressbar, String auth_token, String userID, List<FetchAllReqData_Request> request, Context applicationContext, Activity_ProfileView activty_friendRequests) {

        this.userID=userID;
        this.request=request;
        this.activity=activty_friendRequests;
        this.context=applicationContext;
        this.token=auth_token;
        this.progressBar=progressbar;

    }


    @NonNull
    @Override
    public FirenReqAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.request_list, parent, false);
        FirenReqAdapter.ViewHolder viewHolder = new FirenReqAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FirenReqAdapter.ViewHolder holder, final int position) {

        try{


            Typeface avner = Typeface.createFromAsset(context.getAssets(),
                    "AvenirLTStd-Light.otf");
            Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                    "Dosis-Medium.ttf");

            holder.ACCEPT.setTypeface(dosis);
            holder.Cancel.setTypeface(dosis);
            holder.Username.setTypeface(dosis);

            Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + request.get(position).getPhotoUrl()).into(holder.imageView);

            if (request.get(position).getType().equals("coin_request")){
                holder.Username.setText(request.get(position).getName()+" \nSent you a COIN request");
                holder.reqcount.setText(request.get(position).getValue()+" Coins Requested");
                holder.main_layout.setBackgroundResource(R.drawable.coin_bg);
                holder.Colorebg.setBackgroundResource(R.drawable.coin_colorbg);
                holder.Status.setBackgroundResource(R.drawable.coin_status);
                holder.ACCEPT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                        mp.start();
                        Accept_Coin_Request(request.get(position).getSenderId(),userID,request.get(position).getId(),
                                holder.ACCEPT,holder.Cancel,request.get(position).getValue());

                    }
                });
                holder.Cancel.setOnClickListener(v -> {
                    final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                    mp.start();
                    RejectCoin_Request(request.get(position).getSenderId(),userID,request.get(position).getId(),
                            holder.ACCEPT,holder.Cancel);

                });
            }
            else if (request.get(position).getType().equals("friend_request")){
                holder.Username.setText(request.get(position).getName()+" \nSent you a FRIEND request");
                holder.main_layout.setBackgroundResource(R.drawable.frnd_bg);
                holder.Colorebg.setBackgroundResource(R.drawable.frnd_colorbg);
                holder.Status.setBackgroundResource(R.drawable.frnd_bg_status);
                holder.reqcount.setText("");
                holder.ACCEPT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                        mp.start();
                        Accept_Request(request.get(position).getSenderId(),userID,request.get(position).getId()
                                ,holder.ACCEPT,holder.Cancel);

                    }
                });
                holder.Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                        mp.start();
                        Reject_Request(request.get(position).getSenderId(),userID,request.get(position).getId()
                                ,holder.ACCEPT,holder.Cancel);

                    }
                });
            }
            else {
                holder.main_layout.setBackgroundResource(R.drawable.coin_bg);
                holder.Colorebg.setBackgroundResource(R.drawable.coin_colorbg);
                holder.Status.setBackgroundResource(R.drawable.coin_status);
                holder.ACCEPT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                        mp.start();
                        Accept_Request(request.get(position).getSenderId(),userID,request.get(position).getId()
                                ,holder.ACCEPT,holder.Cancel);

                    }
                });
                holder.Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                        mp.start();
                        Reject_Request(request.get(position).getSenderId(),userID,request.get(position).getId()
                                ,holder.ACCEPT,holder.Cancel);
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView Username;
        private final TextView reqcount;
        private final TextView ACCEPT,Cancel;
        ConstraintLayout Colorebg;
        ConstraintLayout main_layout;
        ConstraintLayout Status;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.userimage);
            Username = itemView.findViewById(R.id.username);
            reqcount = itemView.findViewById(R.id.reqcount);
            ACCEPT = itemView.findViewById(R.id.sent);
            Cancel = itemView.findViewById(R.id.reject);
            Colorebg = itemView.findViewById(R.id.colored_bg);
            Status = itemView.findViewById(R.id.status);
            main_layout = itemView.findViewById(R.id.main_layout);

        }
    }
    @Override
    public int getItemCount() {
        return request.size();
    }


    @Override
    public int getItemViewType(int position) {

        return position;
    }

    private void Accept_Request(String profRqstSenderId, String userID, String profileRqstId, TextView ACCEPT, TextView cancel) {

        APIInterface apiInterface;
        progressBar.setVisibility(View.VISIBLE);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AccptReq> call3 = apiInterface.Api_AcceptReqFRND(token,profileRqstId,"Accepted");
        call3.enqueue(new Callback<AccptReq>() {
            @Override
            public void onResponse(Call<AccptReq> call, retrofit2.Response<AccptReq> response) {
                progressBar.setVisibility(View.GONE);
                try {int Code=response.body().getCode();
                    if (Code==200){

                        ACCEPT.setText("Accepted");
                        ACCEPT.setEnabled(false);
                        cancel.setVisibility(View.GONE);

                    }else if (Code==201){

                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==207){

                        Toast.makeText(context, "Validation Error", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==301){

                        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==303){

                        Toast.makeText(context, "Session Expired", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==305){

                        Toast.makeText(context, "Token Missing", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }
                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(context,"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<AccptReq> call, Throwable t) {
                call.cancel();
                Toast toast= Toast.makeText(context,
                        "Some Error Occured, Contact Our Customer Care", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();

                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void Accept_Coin_Request(String coinRqstSenderId, String userID, String coinRequestId,
                                     TextView ACCEPT, TextView cancel, String coinRqstNo) {

        APIInterface apiInterface;
        progressBar.setVisibility(View.VISIBLE);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AccptReq> call3 = apiInterface.Api_AcceptReq(token,coinRequestId,"Coin");
        call3.enqueue(new Callback<AccptReq>() {
            @Override
            public void onResponse(Call<AccptReq> call, retrofit2.Response<AccptReq> response) {
                progressBar.setVisibility(View.GONE);
                try {int Code=response.body().getCode();
                    if (Code==200){

                        ACCEPT.setText("Accepted");
                        ACCEPT.setEnabled(false);
                        cancel.setVisibility(View.GONE);

                    }else if (Code==201){

                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==207){

                        Toast.makeText(context, "Validation Error", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==301){

                        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==303){

                        Toast.makeText(context, "Session Expired", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==305){

                        Toast.makeText(context, "Token Missing", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }
                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(context,"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<AccptReq> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                Toast toast= Toast.makeText(context,
                        "Some Error Occured, Contact Our Customer Care", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();

                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void Reject_Request(String profileRqstSenderId, String userID, String profileRqstId, TextView ACCEPT, TextView cancel) {

        APIInterface apiInterface;
        progressBar.setVisibility(View.VISIBLE);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AccptReq> call3 = apiInterface.Api_RejectReqFRND(token,profileRqstId,"Reject");
        call3.enqueue(new Callback<AccptReq>() {
            @Override
            public void onResponse(Call<AccptReq> call, retrofit2.Response<AccptReq> response) {
                progressBar.setVisibility(View.GONE);
                try {int Code=response.body().getCode();
                    if (Code==200){

                        cancel.setText("Rejected");
                        ACCEPT.setVisibility(View.GONE);
                        cancel.setEnabled(false);

                    }else if (Code==201){

                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==207){

                        Toast.makeText(context, "Validation Error", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==301){

                        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==303){

                        Toast.makeText(context, "Session Expired", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==305){

                        Toast.makeText(context, "Token Missing", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }

                }catch (Exception  e){
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                    Toast toast = Toast.makeText(context,"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<AccptReq> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                Toast toast= Toast.makeText(context,
                        "Some Error Occured, Contact Our Customer Care", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();

                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void RejectCoin_Request(String coinRqstSenderId, String userID, String coinRequestId, TextView ACCEPT, TextView cancel) {

        APIInterface apiInterface;
        progressBar.setVisibility(View.VISIBLE);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AccptReq> call3 = apiInterface.Api_Reject_ReqCoin(token,coinRequestId,"Reject");
        call3.enqueue(new Callback<AccptReq>() {
            @Override
            public void onResponse(Call<AccptReq> call, retrofit2.Response<AccptReq> response) {
                progressBar.setVisibility(View.GONE);
                try {
                    int Code=response.body().getCode();
                    if (Code==200){

                        cancel.setText("Rejected");
                        ACCEPT.setVisibility(View.GONE);
                        cancel.setEnabled(false);

                    }else if (Code==201){

                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==207){

                        Toast.makeText(context, "Validation Error", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==301){

                        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==303){

                        Toast.makeText(context, "Session Expired", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }else if (Code==305){

                        Toast.makeText(context, "Token Missing", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = context.getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(context, Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intlogout);
                    }
                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(context,"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<AccptReq> call, Throwable t) {
                call.cancel();
                Toast toast= Toast.makeText(context,
                        "Some Error Occured, Contact Our Customer Care", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();

                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
