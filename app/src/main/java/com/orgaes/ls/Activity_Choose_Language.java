package com.orgaes.ls;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.orgaes.ls.Adapter.Languages_Adapter;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.Functions.EndlessRecyclerOnScrollListener;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Languages.FetchLanguage;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Languages.Language_Response;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Languages.Langugaedata;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

import static com.orgaes.ls.AppData.Appclass.getContext;


/*
 * Purpose – Activity_Choose_Language is an activity for Choose language
 * @author SHANIL
 * Created on 21-5-2020
 */
public class Activity_Choose_Language extends AppCompatActivity {


    Languages_Adapter mAdapter;
    private EndlessRecyclerOnScrollListener scrollListener;
    RecyclerView recycler_languages;
    ConstraintLayout img_Toparrow, img_BottomArrow;
    ProgressBar progressBar;

    List<String>list_LangId =new ArrayList<>();
    List<String> list_Flag =new ArrayList<>();
    List<String> list_Rtl_Ltr =new ArrayList<>();
    List<String> list_Code =new ArrayList<>();
    List<String> list_Name =new ArrayList<>();
    List<String> list_Default =new ArrayList<>();
    List<String> list_Status =new ArrayList<>();
    APIInterface apiInterface;
    String str_Android_ID;
    private static final int REQUEST_CODE = 101;
    int itemPo=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        /*
         * Changing the color of the footbar
         * */
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_choose_language);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        BindControls();
    }
    private void BindControls(){
        recycler_languages =findViewById(R.id.listview);
        progressBar=findViewById(R.id.progressbar);
        img_Toparrow =findViewById(R.id.uparrow);
        img_BottomArrow =findViewById(R.id.downarrow);
        progressBar.setVisibility(View.VISIBLE);
        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Choose_Language.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(v -> {
                })
                .longPressListener(v -> {
                }).doubleTapListener(v -> {
                });
        builder.register();
        img_BottomArrow.setOnClickListener(v -> {
            if (itemPo != mAdapter.getItemCount()) {
                final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.keyboard_button);
                mp.start();
                itemPo = itemPo + 1;
                recycler_languages.smoothScrollToPosition(itemPo);
            }
        });
        img_Toparrow.setOnClickListener(v -> {
            if (itemPo != 0) {
                final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.keyboard_button);
                mp.start();
                itemPo = itemPo - 1;
                recycler_languages.smoothScrollToPosition(itemPo);
            }
        });
    }
    @SuppressLint("HardwareIds")
    @Override
    public void onResume() {
        super.onResume();
        try {
            str_Android_ID = Settings.Secure.getString(getContext().getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            System.out.println("LS KEY  "+str_Android_ID);
            Fetch_Languages(str_Android_ID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        try{
            if (requestCode == REQUEST_CODE) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * Api call for fetching languages
     * */
    private void Fetch_Languages(String IMEI) {
        list_LangId.clear();
        list_Code.clear();
        list_Default.clear();
        list_Flag.clear();
        list_Name.clear();
        list_Rtl_Ltr.clear();
        list_Status.clear();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<FetchLanguage> call3 = apiInterface.Fetch_Language();
        call3.enqueue(new Callback<FetchLanguage>() {
            @Override
            public void onResponse(@NotNull Call<FetchLanguage> call, @NotNull retrofit2.Response<FetchLanguage> response) {
                progressBar.setVisibility(View.GONE);

                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        Language_Response response1=response.body().getResponse();
                        List<Langugaedata>languages=response1.getLangugaes();
                        if (languages.size()>0){

                            for (int i=0;i<languages.size();i++){

                                Langugaedata language=languages.get(i);
                                list_LangId.add(language.getLanguagesId());
                                list_Code.add(language.getLanguagesCode());
                                list_Default.add(language.getLanguagesDefault());
                                list_Flag.add(language.getLanguagesFlag());
                                list_Name.add(language.getLanguagesName());
                                list_Rtl_Ltr.add(language.getLanguagesRtlLtr());
                                list_Status.add(language.getLanguagesStatus());

                            }
                            /*
                             * Storing the Userid to sharedpreference
                             * */
//                            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
//                            editor.putString("UserID", SessionID);
//                            editor.apply();
                            /*
                             * Loading the languages to Adapter class
                             * */
                            mAdapter = new Languages_Adapter(IMEI,languages,getApplicationContext(),Activity_Choose_Language.this);
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            recycler_languages.setLayoutManager(mLayoutManager);
                            recycler_languages.setItemAnimator(new DefaultItemAnimator());
                            recycler_languages.setAdapter(mAdapter);
//                            scrollListener = new EndlessRecyclerOnScrollListener(mLayoutManager) {
//                                @Override
//                                public void onLoadMore(int current_page) {
//                                    Fetch_Languages(IMEI);
//                                }
//
//                            };
//                            // Adds the scroll listener to RecyclerView
//                            recycler_languages.addOnScrollListener(scrollListener);
                        }else {
                            Toast toast= Toast.makeText(getApplicationContext(),
                                    "No Languages Found", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                            toast.show();
                        }


                    }else if (Code==201){

                        Toast.makeText(getApplicationContext(), "No languages found", Toast.LENGTH_SHORT).show();

                    }else if (Code==207){

                        Toast.makeText(getApplicationContext(), "Validation Error", Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences("LS", Context.MODE_PRIVATE);
                        settings.edit().remove("Lang_Selected").apply();
                        settings.edit().clear().apply();
                        Intent intlogout=new Intent(getApplicationContext(), Activity_Choose_Language.class);
                        intlogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intlogout);
                    }
                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Oh! ho..Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<FetchLanguage> call, @NotNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                call.cancel();
            }
        });
    }



}
