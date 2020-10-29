package com.orgaes.ls.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.orgaes.ls.Activity_ChooseUser;
import com.orgaes.ls.Activity_Choose_Language;
import com.orgaes.ls.R;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Languages.Langugaedata;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Languages_Adapter extends RecyclerView.Adapter<Languages_Adapter.MyViewHolder> {


    List<Langugaedata> languages;

    Activity_Choose_Language activity_choose_language;
    Context context;
    String IMEINumber;

    public Languages_Adapter(String imei, List<Langugaedata> languages, Context applicationContext, Activity_Choose_Language activity_choose_language) {

        this.IMEINumber=imei;
        this.context=applicationContext;
        this.activity_choose_language=activity_choose_language;
        this.languages=languages;

    }

    @NotNull
    @Override
    public Languages_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_language, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(Languages_Adapter.MyViewHolder holder, final int position) {

        Typeface face = Typeface.createFromAsset(context.getAssets(),
                "AvenirLTStd-Light.otf");
        holder.title.setTypeface(face);
        holder.title.setText(""+languages.get(position).getLanguagesName());

        holder.item_layout.setOnClickListener(v -> {
            if (languages.get(position).getLanguagesName().equals("English")){
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
                mp.start();
                SharedPreferences.Editor editor = context.getSharedPreferences("LS", MODE_PRIVATE).edit();
                editor.putString("Lang_Selected", "1");
                editor.putString("IMEINumber", IMEINumber);
                editor.apply();
                Intent in=new Intent(v.getContext(), Activity_ChooseUser.class);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity_choose_language.startActivity(in);
            }else {
                Toast toast = Toast.makeText(context,"Language not exist", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }

        });


    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ConstraintLayout item_layout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.lang_name);
            item_layout = (ConstraintLayout) view.findViewById(R.id.main_layout);

        }
    }
}