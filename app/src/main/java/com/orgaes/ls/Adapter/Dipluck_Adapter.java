package com.orgaes.ls.Adapter;

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
import com.orgaes.ls.Activity_DipLuck;
import com.orgaes.ls.Activity_HomePage;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.AppConfig;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.DipLucks.DipLuck;
import com.orgaes.ls.RETROFIT_NEW.EnrollLuck.Enroll_Luck_Model;
import com.orgaes.ls.RETROFIT_NEW.FrndReq_Sent.Req_Sent;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class Dipluck_Adapter extends RecyclerView.Adapter<Dipluck_Adapter.MyViewHolder> {

    List<DipLuck> dipLucks=new ArrayList<>();
    Context context;
    Activity_DipLuck activity_dipLuck;
    String auth_token;
    ProgressBar progressBar;
    public Dipluck_Adapter(ProgressBar progressbar, String auth_token, List<DipLuck> dipLucks, Context applicationContext, Activity_DipLuck activity_dipLuck) {
        this.dipLucks=dipLucks;
        this.context=applicationContext;
        this.activity_dipLuck=activity_dipLuck;
        this.auth_token=auth_token;
        this.progressBar=progressbar;
    }

    @NonNull
    @Override
    public Dipluck_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_sub_dipluck, parent, false);

        return new Dipluck_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Dipluck_Adapter.MyViewHolder holder, int position) {
        Typeface avner = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        Typeface dosis = Typeface.createFromAsset(context.getAssets(),
                "Dosis-Medium.ttf");

        holder.Draw_txt.setTypeface(dosis);
        holder.LuckType_txt.setTypeface(dosis);
        holder.Item_Name.setTypeface(dosis);
        holder.Sponsorname.setTypeface(dosis);
        holder.Req_Coins.setTypeface(dosis);
        holder.Req_Coins_txt.setTypeface(dosis);
        holder.Btn_more.setTypeface(dosis);

        System.out.println("LS DIPLUCK DATA 1  "+dipLucks.get(position).getDescription());
        System.out.println("LS DIPLUCK DATA 2  "+dipLucks.get(position).getName());

        holder.Draw_txt.setText("NEXT DRAW @ "+dipLucks.get(position).getDrawTime());
        holder.LuckType_txt.setText(dipLucks.get(position).getType()+" LUCK");
        holder.Item_Name.setText(dipLucks.get(position).getName());
        holder.Sponsorname.setText(dipLucks.get(position).getDescription());
        holder.Req_Coins.setText(dipLucks.get(position).getEnrollFee());
        Glide.with(context).load(AppConfig.URL_BASE_VIDEOS + dipLucks.get(position).getImage())
                .into(holder.Luck_Image);

        if (dipLucks.get(position).getType().equals("HOURLY")){
            holder.Luck_Logo.setImageResource(R.drawable.hourley_luck_logo);
            holder.top_banner.setBackgroundResource(R.drawable.hourley_luck_tag);
        }else if (dipLucks.get(position).getType().equals("WEEKLY")){
            holder.Luck_Logo.setImageResource(R.drawable.weekly_luck_logo);
            holder.top_banner.setBackgroundResource(R.drawable.weekly_luck_tag);
        }else if (dipLucks.get(position).getType().equals("DAILY")){
            holder.Luck_Logo.setImageResource(R.drawable.daily_luck_logo);
            holder.top_banner.setBackgroundResource(R.drawable.daily_luck_tag);
        }else if (dipLucks.get(position).getType().equals("MONTHLY")){
            holder.Luck_Logo.setImageResource(R.drawable.monthly_luck_logo);
            holder.top_banner.setBackgroundResource(R.drawable.monthly_luck_tag);
        }

        holder.trigger.setOnClickListener(v -> {
            final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
            mp.start();
            addto_Draw(dipLucks.get(position).getId());

        });

    }

    private void addto_Draw(String id) {
        progressBar.setVisibility(View.VISIBLE);
        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Enroll_Luck_Model> call3 = apiInterface.Join_Dipluck(auth_token,id);
        call3.enqueue(new Callback<Enroll_Luck_Model>() {
            @Override
            public void onResponse(@NotNull Call<Enroll_Luck_Model> call, @NotNull retrofit2.Response<Enroll_Luck_Model> response) {
                progressBar.setVisibility(View.GONE);
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        new SweetAlertDialog(activity_dipLuck, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("SUCCESS")
                                .setContentText("You are successfully enrolled to the LUCK DRAW")
                                .setConfirmText("THANK YOU")
                                .setCancelText("HOME")
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        Intent inhome=new Intent(activity_dipLuck, Activity_HomePage.class);
                                        activity_dipLuck.startActivity(inhome);
                                        activity_dipLuck.finish();
                                    }
                                })
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                    }
                                })
                                .show();
                    }else if (Code==203){

                        Toast toast = Toast.makeText(context,"Insufficient Coin", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }else if (Code==201){

                        Toast.makeText(context, "Enrolling Failed", Toast.LENGTH_SHORT).show();

                    }else if (Code==205){

                        Toast.makeText(context, "Already Enrolled", Toast.LENGTH_SHORT).show();

                    }

                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(context,"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Enroll_Luck_Model> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                call.cancel();
                Toast toast= Toast.makeText(context,
                        "Some Error Occured, Contact Our Customer Care", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();

                t.printStackTrace();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dipLucks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Draw_txt;
        TextView LuckType_txt;
        TextView Item_Name;
        TextView Sponsorname;
        TextView Req_Coins;
        TextView Req_Coins_txt;
        TextView Btn_more;
        ConstraintLayout top_banner;
        ImageView Luck_Logo;
        ImageView Luck_Image;
        ConstraintLayout trigger;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Draw_txt=itemView.findViewById(R.id.nxt_draw);
            LuckType_txt=itemView.findViewById(R.id.luck_type);
            Item_Name=itemView.findViewById(R.id.itemname);
            Sponsorname=itemView.findViewById(R.id.sponsor_name);
            Req_Coins=itemView.findViewById(R.id.required_coins);
            Req_Coins_txt=itemView.findViewById(R.id.required_cointxt);
            Btn_more=itemView.findViewById(R.id.btn_more);
            top_banner=itemView.findViewById(R.id.top_banner);
            Luck_Logo=itemView.findViewById(R.id.luck_type_logo);
            Luck_Image=itemView.findViewById(R.id.luck_image);
            trigger=itemView.findViewById(R.id.trigger);

        }
    }
}
