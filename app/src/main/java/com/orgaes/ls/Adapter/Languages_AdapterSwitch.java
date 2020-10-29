package com.orgaes.ls.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.orgaes.ls.Activity_Choose_Language_Switch;
import com.orgaes.ls.Activity_HomePage;
import com.orgaes.ls.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Languages_AdapterSwitch extends RecyclerView.Adapter<Languages_AdapterSwitch.MyViewHolder> {


        List<String> Lang_ID=new ArrayList<>();
        List<String>Lang_FLAG=new ArrayList<>();
        List<String>Lang_RTL_LTR=new ArrayList<>();
        List<String>Lang_CODE=new ArrayList<>();
        List<String>Lang_NAME=new ArrayList<>();
        List<String>Lang_DEFAULT=new ArrayList<>();
        List<String>Lang_STATUS=new ArrayList<>();
        String SwitchString;

        Activity_Choose_Language_Switch activity_choose_language;
        private Context context;
public Languages_AdapterSwitch(List<String> lang_id, List<String> lang_code, List<String> lang_default, List<String> lang_flag, List<String> lang_name, List<String> lang_rtl_ltr,
        List<String> lang_status, String switchString, Context applicationContext, Activity_Choose_Language_Switch activity_choose_language) {

        this.Lang_ID=lang_id;
        this.Lang_FLAG=lang_flag;
        this.Lang_RTL_LTR=lang_rtl_ltr;
        this.Lang_CODE=lang_code;
        this.Lang_NAME=lang_name;
        this.Lang_DEFAULT=lang_default;
        this.Lang_STATUS=lang_status;
        this.SwitchString=switchString;
        this.context=applicationContext;
        this.activity_choose_language=activity_choose_language;

        }

@Override
public Languages_AdapterSwitch.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.layout_language, parent, false);

        return new Languages_AdapterSwitch.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(Languages_AdapterSwitch.MyViewHolder holder, final int position) {

        holder.title.setText(""+Lang_NAME.get(position));
        holder.item_layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final MediaPlayer mp = MediaPlayer.create(context, R.raw.btn_click);
            mp.start();
            if (SwitchString.equals("YES")){

                Intent in=new Intent(v.getContext(), Activity_HomePage.class);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity_choose_language.startActivity(in);
                 }
            else {
        SharedPreferences.Editor editor = context.getSharedPreferences("LS", MODE_PRIVATE).edit();
        editor.putString("Lang_Selected", "1");
        editor.apply();
        Intent in=new Intent(v.getContext(), Activity_HomePage.class);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity_choose_language.startActivity(in);
        }


//                Toast.makeText(activity_choose_language, "Selected -  "+Lang_NAME.get(position), Toast.LENGTH_SHORT).show();
//                activity_choose_language.////overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        }
        });


        }

        @Override
        public int getItemCount() {
        return Lang_ID.size();
        }

    public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public ConstraintLayout item_layout;

    public MyViewHolder(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.lang_name);
        item_layout = (ConstraintLayout) view.findViewById(R.id.main_layout);

    }
}
}