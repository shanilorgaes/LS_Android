package com.orgaes.ls;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.LongPressListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;import androidx.appcompat.app.AppCompatActivity;
import com.orgaes.ls.Adapter.AdapterWishlist;
import com.orgaes.ls.AppData.APIClient;
import com.orgaes.ls.AppData.APIInterface;
import com.orgaes.ls.AppData.Constants;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Wishlist.Fecth_Wish_Response;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Wishlist.Fetch_Wishlists;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Wishlist.Fettch_data_Wishlist;
import com.orgaes.ls.RETROFIT_NEW.RegData.RegData_Example;
import com.orgaes.ls.RETROFIT_NEW.RegData.RegData_Response;
import com.orgaes.ls.RETROFIT_NEW.UserCheck.UserCheck;
import com.orgaes.ls.RETROFIT_NEW.UserCheck.UserCheckResponse;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
 * Purpose – Activity_Register is an activity for the new users can register here and verify with OTP
 * Name
 * phonenumber
 * selecting wishlists
 * email
 * gender
 * @author SHANIL
 * Created on 21-5-2020
 */
public class Activity_Register extends AppCompatActivity {

    Button btnSignin;
    EditText edtPhone, edtName, edtEmail, edtOtp, edtProffession;
    String strWishlist, strUserId,UserPhone, strGendercode ="1", strAgegroup,strGender, strAgegroupnumber,strgendernumber, strFirebaseOtp;
    ProgressBar progressBar;
    APIInterface interfaceApi;
    Spinner spinnerAgegroup;
    Spinner spinnergender;
    List<String> listAgegroup =new ArrayList<>();
    List<String> list_Gender =new ArrayList<>();
    List<String> listWishlist =new ArrayList<>();
    ArrayList<String> listSuggestions =new ArrayList<>();
    ArrayList<String> listNew =new ArrayList<>();
    List<String> listShowinglist =new ArrayList<>();
    TextView txtAge, txtGender;
    SearchBox searchBox;
    String str_IMEINumber;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    RecyclerView recyWishlist;
    AdapterWishlist adapterWishlist;
    //    CountryCodePicker countryCodePicker;
    String AuthKey,UserType,UserID,UserImage,UserName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        /*
         * Changing the color for footbar
         * */
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Black));
        }
        //overridePendingTransition(R.anim.anim_scale_in, R.anim.anim_scale_out);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        /*
         *Portrait Fullscreen mode
         * */
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_registernew);
        /*
         * Storing the userid to the sharedpreference
         * */
        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        strUserId = prefsnew.getString("UserID", "");
        UserPhone = prefsnew.getString("UserPhone", "");
        initFunction();
        fontStyle();
        funMain();
    }

    private void funMain() {
        /*
         * Adding values to the agegroup spinner arraylist
         * */
        listAgegroup.add("  AGE GROUP");
        listAgegroup.add("  10 - 20");
        listAgegroup.add("  20 - 30");
        listAgegroup.add("  30 - 40");
        listAgegroup.add("  40 - 50");
        listAgegroup.add("  50 - 60");
        listAgegroup.add("  60 - 80");
        /*
         * creating adapter to add values to the spinner
         * */
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listAgegroup);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgegroup.setAdapter(arrayAdapter);
        spinnerAgegroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAgegroup = parent.getItemAtPosition(position).toString();
                strAgegroupnumber = String.valueOf(position);
                txtAge.setText(strAgegroup);
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        txtAge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                spinnerAgegroup.performClick();
            }
        }); /*
         * Adding values to the gender spinner arraylist
         * */
        list_Gender.add("  SELECT GENDER");
        list_Gender.add("  TRANSGENDER");
        list_Gender.add("  MALE");
        list_Gender.add("  FEMALE");
        /*
         * creating adapter to add values to the spinner
         * */
        ArrayAdapter<String> arrayAdaptergender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_Gender);
        arrayAdaptergender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnergender.setAdapter(arrayAdaptergender);
        spinnergender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGender = parent.getItemAtPosition(position).toString();
                strgendernumber = String.valueOf(position);
                if (strGender.equals("TRANSGENDER")){
                    strGendercode ="4";
                }else if (strGender.equals("MALE")){
                    strGendercode ="3";
                }else if (strGender.equals("FEMALE")){
                    strGendercode ="2";
                }
                txtGender.setText(strGender);
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        txtGender.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                spinnergender.performClick();
            }
        });
        btnSignin.setOnClickListener(v -> {

            if (edtPhone.getText().toString().equals("") || edtName.getText().toString().equals("") || edtEmail.getText().toString().equals("")|| strGender.equals("")||strGender.equals("SELECT GENDER")||
                    strAgegroup.equals("AGE GROUP")|| strAgegroup.equals("")) {

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Please fill the fields to continue...", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();
            } else {
                /*
                 * Checking the name is valid or not
                 * */
                if (edtName.getText().toString().matches("[a-zA-Z ]+")){

                    /*
                     * Checking the email id is valid or not
                     * */
                    if (isEmailValid(edtEmail.getText().toString().trim())){

                        if (btnSignin.getText().toString().equals("REQUEST OTP")){

                            RegisterOTP();

                        }else if (btnSignin.getText().toString().equals("VERIFY OTP")){
                            VerifyCode(edtOtp.getText().toString());
                        }
                    }else {
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Email is not valid", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }
                }else {
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "Name is not valid", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
            }
        });
        /*
         * Fetching the data from the wishlist api
         * */
        getWishlist();
    }

    private void fontStyle() {
        Typeface avner = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "AvenirLTStd-Light.otf");

        edtPhone.setTypeface(avner);
        edtProffession.setTypeface(avner);
        edtEmail.setTypeface(avner);
        txtAge.setTypeface(avner);
        edtName.setTypeface(avner);
        edtOtp.setTypeface(avner);
        txtGender.setTypeface(avner);
    }

    private void initFunction() {

        ConstraintLayout constraintLayoutmain=findViewById(R.id.constraintLayoutmain);
        Zoomy.Builder builder = new Zoomy.Builder(Activity_Register.this)
                .target(constraintLayoutmain)
                .interpolator(new OvershootInterpolator())
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {
//                        Toast.makeText(Activity_Register.this, "Tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                })
                .longPressListener(new LongPressListener() {
                    @Override
                    public void onLongPress(View v) {
//                        Toast.makeText(Activity_Register.this, "Long press on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                }).doubleTapListener(new DoubleTapListener() {
                    @Override
                    public void onDoubleTap(View v) {
//                        Toast.makeText(Activity_Register.this, //"Double tap on "
//                                + v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

        builder.register();
        spinnerAgegroup =findViewById(R.id.agegroup_spinner);
        spinnergender =findViewById(R.id.gender_spinner);
        edtName =findViewById(R.id.edt_name);
        edtEmail =findViewById(R.id.edt_email);
        edtPhone =findViewById(R.id.edt_mob);
        edtPhone.setEnabled(false);
        edtPhone.setText(UserPhone);
        edtOtp =findViewById(R.id.edt_otp);
        txtAge =findViewById(R.id.agegroup);
        edtProffession =findViewById(R.id.edt_proffession);
        btnSignin =findViewById(R.id.buttons_continue);
        progressBar=findViewById(R.id.progressbar);
        txtGender =findViewById(R.id.gender);
        searchBox =findViewById(R.id.searchview);
        recyWishlist =findViewById(R.id.added_wish);
//        countryCodePicker =findViewById(R.id.ccp);
        btnSignin.setText("REQUEST OTP");

        SharedPreferences prefsnew = getSharedPreferences("LS", MODE_PRIVATE);
        str_IMEINumber= prefsnew.getString("IMEINumber", "");
        System.out.println("LS NUMBER   "+str_IMEINumber);

    }

    /*
     * email validation test
     * */
    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Pattern pattern;
        pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    /*
     * Registering the user with strUserId,edtName,edtEmail,edtPhone,strGendercode,strWishlist,userPrivacy,strAgegroupnumber,edtProffession
     * */
    private void Register() {
        StringBuilder builder = new StringBuilder();
        for(String s : listNew) {
            builder.append(s);
        }
        strWishlist = builder.toString();
        if (strWishlist.equals("")){
            Toast toast= Toast.makeText(getApplicationContext(),
                    "Please select atleast one wishlist", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
            toast.show();
        }else {
            progressBar.setVisibility(View.VISIBLE);
            interfaceApi = APIClient.getClient().create(APIInterface.class);
            Call<RegData_Example> call3 = interfaceApi.Api_Register(AuthKey,  edtName.getText().toString(),
                    edtEmail.getText().toString(), UserPhone, strGendercode, strWishlist,"1", strAgegroupnumber,
                    edtProffession.getText().toString());
            call3.enqueue(new Callback<RegData_Example>() {
                @Override
                public void onResponse(@NotNull Call<RegData_Example> call, @NotNull Response<RegData_Example> response) {
                    progressBar.setVisibility(View.GONE);
                    try {
                        assert response.body() != null;
                        int Code=response.body().getCode();
                        if (Code==200){

                            RegData_Response regData_response=response.body().getResponse();
                            String AUTHKEY=regData_response.getAuthKey();
                            SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                            editor.putString("SignedIn", "1");
                            editor.putString("auth_token", AUTHKEY);
                            editor.apply();
                            Intent in=new Intent(getApplicationContext(),Activity_HomePage.class);
                            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                        }else if (Code==201){
                            Toast toast = Toast.makeText(getApplicationContext(),"Registration Failed", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }else if (Code==207){
                            System.out.println("LS VALIDATION 1  "+response.body().getResponse().getUserAgeGroup());
                            System.out.println("LS VALIDATION 2  "+response.body().getResponse().getUserGender());
                            System.out.println("LS VALIDATION 3  "+response.body().getResponse().getUserName());
                            System.out.println("LS VALIDATION 4  "+response.body().getResponse().getUserPhone());
                            System.out.println("LS VALIDATION 5  "+response.body().getResponse().getUserProfession());
                            System.out.println("LS VALIDATION 6  "+response.body().getResponse().getUserWishlist());
                            Toast toast = Toast.makeText(getApplicationContext(),"Validation Error  \n", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println("LS REGISTER ERROR 1   "+e.getMessage());
                        Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
                @Override
                public void onFailure(@NotNull Call<RegData_Example> call, @NotNull Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    t.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    System.out.println("LS REGISTER ERROR 2  "+t.getMessage());
                }

            });
        }


    }
    /*
     * phone authentication provider
     * */
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            strFirebaseOtp =s;
            btnSignin.setText("VERIFY OTP");
            edtOtp.setVisibility(View.VISIBLE);
            edtPhone.setVisibility(View.GONE);
//            countryCodePicker.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
            super.onCodeAutoRetrievalTimeOut(s);
            btnSignin.setText("Resend OTP");
        }
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            progressBar.setVisibility(View.GONE);
            signInWithPhoneAuthCredential(phoneAuthCredential);
        }
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            System.out.println("LS FIREBASE  FAILED "+e.getMessage());

        }
    };
    /*
     * sending verification code to the user mobile number
     * */
    private void sendverificationcode(String phonenumber_) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phonenumber_, 60, TimeUnit.SECONDS, this, mCallbacks);
        progressBar.setVisibility(View.VISIBLE);

    }

    /*
     * verifying code with firebase
     * */
    private void VerifyCode(String otpCode){
        progressBar.setVisibility(View.VISIBLE);
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(strFirebaseOtp, otpCode);

            signInWithPhoneAuthCredential(credential);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        System.out.println("LS FIREBASE  SUCCESS ");
                        progressBar.setVisibility(View.VISIBLE);
                        mAuth = FirebaseAuth.getInstance();
                        FirebaseUser firebaseUser=mAuth.getCurrentUser();
                        assert firebaseUser != null;
                        String UserID=firebaseUser.getUid();
                        /*
                         * Registering the user to firebase
                         * */
                        reference= FirebaseDatabase.getInstance().getReference("Users").child(UserID);
                        HashMap<String,String>hashMap=new HashMap<>();
                        hashMap.put("id",UserID);
                        hashMap.put("username", edtName.getText().toString());
                        hashMap.put("userImage","default");
                        hashMap.put("UserID",strUserId);
                        /*
                         * if the values are added completed , the user will registered data will stored  in db
                         * */
                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                Register();
                            }
                        });

                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
    /*
     * wishlist searching and adding function
     * */
    private void getSearch() {
        adapterWishlist =new AdapterWishlist(this, listShowinglist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyWishlist.setLayoutManager(mLayoutManager);
        recyWishlist.setItemAnimator(new DefaultItemAnimator());
        recyWishlist.setAdapter(adapterWishlist);
        adapterWishlist.notifyDataSetChanged();

        for(int x = 0; x < listSuggestions.size(); x++){
            SearchResult option = new SearchResult(listSuggestions.get(x), null);
            searchBox.setAnimateDrawerLogo(false);
            searchBox.addSearchable(option);
            searchBox.setHint("Search Wishlist");
            searchBox.setMaxLength(25);
        }
        searchBox.setMenuListener(() -> {
            //Hamburger has been clicked
            searchBox.getResults();
            searchBox.toggleSearch();
        });
        searchBox.setSearchListener(new SearchBox.SearchListener(){

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
            }
            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }
            @Override
            public void onSearchTermChanged(String term) {
                //React to the search term changing
                //Called after it has updated results
            }
            @Override
            public void onSearch(String searchTerm) {
                if (listShowinglist.contains(searchTerm)) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Already added", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else{
                    String WishlistID=searchTerm.replace("[","");
                    listNew.add(WishlistID+"|");
                    listShowinglist.add(searchTerm);
                    adapterWishlist.notifyDataSetChanged();
                    searchBox.clearResults();
                    searchBox.ClearText();
                }
            }

            @Override
            public void onResultClick(SearchResult result) {
                //React to a result being clicked
                if (listShowinglist.contains(result.title)) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Already added", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else{
                    String str = result.title;
                    String answer = str.substring(str.indexOf("[")+1,str.indexOf("]"));
                    String WishlistID=answer.replace("[","");
                    listNew.add(WishlistID+"|");
                    listShowinglist.add(result.title);
                    adapterWishlist.notifyDataSetChanged();
                    searchBox.clearResults();
                    searchBox.ClearText();
                }
            }
            @Override
            public void onSearchCleared() {
                //Called when the clear button is clicked
            }
        });
    }
    /*
     * fetch wishlist data from backend and added to searchview
     * */
    private  void getWishlist(){
        listWishlist.clear();
        listSuggestions.clear();
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Fetch_Wishlists> call3 = apiInterface.Fetch_WishList();
        call3.enqueue(new Callback<Fetch_Wishlists>() {
            @Override
            public void onResponse(@NotNull Call<Fetch_Wishlists> call, @NotNull retrofit2.Response<Fetch_Wishlists> response) {
                try {
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        Fecth_Wish_Response response1=response.body().getResponse();
                        List<Fettch_data_Wishlist> wishlists=response1.getWishlist();

                        if (wishlists.size()>0){
                            for (int i=0;i<wishlists.size();i++){
                                Fettch_data_Wishlist wishlistNotification=wishlists.get(i);
                                listWishlist.add(wishlistNotification.getWishlistTransName());
                                listSuggestions.add(wishlistNotification.getWishlistTransName()+"["+wishlistNotification.getWishlistTransId()+"]");
                                System.out.println("LS NEW WISHLIST   "+wishlistNotification.getWishlistTransName());
                            }
                            getSearch();
                        }
                    }else if (Code==201){

                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception  e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            @Override
            public void onFailure(@NotNull Call<Fetch_Wishlists> call, @NotNull Throwable t) {
                call.cancel();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });
    }

    private void RegisterOTP()
    {
        System.out.println("LS PHONE NUMBER  "+UserPhone);
        progressBar.setVisibility(View.VISIBLE);
        interfaceApi = APIClient.getClient().create(APIInterface.class);
        Call<UserCheck> call3 = interfaceApi.Api_Num_Check(UserPhone,str_IMEINumber);
        call3.enqueue(new Callback<UserCheck>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<UserCheck> call, @NotNull Response<UserCheck> response) {

                try {
                    progressBar.setVisibility(View.GONE);
                    assert response.body() != null;
                    int Code=response.body().getCode();
                    if (Code==200){
                        UserCheckResponse response1=response.body().getResponse();
                        AuthKey=response1.getAuthKey();
                        UserType=response1.getUserType();
                        btnSignin.setText("VERIFY OTP");
                        Constants.Const_UserCoins= Integer.parseInt(response1.getUsercoin());

                        SharedPreferences.Editor editor = getSharedPreferences("LS", MODE_PRIVATE).edit();
                        editor.putString("auth_token", AuthKey);
                        editor.apply();
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Enter otp to continue", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                        sendverificationcode("+"+UserPhone);
                    }else if (Code==201){
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Already Registered", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }else if (Code==203){
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Already user exist in this device", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }else if (Code==207){
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Phone number field is required", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            @Override
            public void onFailure(@NotNull Call<UserCheck> call, @NotNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(),"Some Error Occured, Contact Our Customer Care", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });
    }


}