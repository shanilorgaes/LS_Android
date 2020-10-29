package com.orgaes.ls.AppData;


import com.orgaes.ls.RETROFIT_NEW.AcceptReques.AccptReq;
import com.orgaes.ls.RETROFIT_NEW.BOX.BOXDATA;
import com.orgaes.ls.RETROFIT_NEW.CheckExtend.CheckExtendModel;
import com.orgaes.ls.RETROFIT_NEW.ClaimData.ClaimDatAModel;
import com.orgaes.ls.RETROFIT_NEW.ClaimDip.ClaimDip_Module;
import com.orgaes.ls.RETROFIT_NEW.CoinCount.CoinCountData;
import com.orgaes.ls.RETROFIT_NEW.CoinData.CoinDATA_Model;
import com.orgaes.ls.RETROFIT_NEW.CoinRATE.CoinRateData;
import com.orgaes.ls.RETROFIT_NEW.CollectCoin.CollectCoin;
import com.orgaes.ls.RETROFIT_NEW.DeleteWishList.DeleteWishList;
import com.orgaes.ls.RETROFIT_NEW.DipLucks.DipLucks_Model;
import com.orgaes.ls.RETROFIT_NEW.DrawWinners.DrawWinners_Model;
import com.orgaes.ls.RETROFIT_NEW.Edition_Check.EditionCheck;
import com.orgaes.ls.RETROFIT_NEW.Editions_Data.EditionModule;
import com.orgaes.ls.RETROFIT_NEW.EnrollLuck.Enroll_Luck_Model;
import com.orgaes.ls.RETROFIT_NEW.Exchange_Items.EXDATAMODEL;
import com.orgaes.ls.RETROFIT_NEW.ExtendTime.Extend_Time_Module;
import com.orgaes.ls.RETROFIT_NEW.Fetch_All_Requests.FetchAllReqData;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Languages.FetchLanguage;
import com.orgaes.ls.RETROFIT_NEW.Fetch_Wishlist.Fetch_Wishlists;
import com.orgaes.ls.RETROFIT_NEW.FootPrintData.FootprintMain;
import com.orgaes.ls.RETROFIT_NEW.FootprintDetails.Footprint_mainData;
import com.orgaes.ls.RETROFIT_NEW.FriendsList.FriendsList_Main;
import com.orgaes.ls.RETROFIT_NEW.FrndReq_Sent.Req_Sent;
import com.orgaes.ls.RETROFIT_NEW.GETWallet.WalletData;
import com.orgaes.ls.RETROFIT_NEW.Home_New.Home_New_data;
import com.orgaes.ls.RETROFIT_NEW.InstantLuck_Data.InstantLuckDATA;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile.LB_Prof_Data;
import com.orgaes.ls.RETROFIT_NEW.LeaderBoard_Model.LeaderBoard_data;
import com.orgaes.ls.RETROFIT_NEW.LuckData.LuckData_Model;
import com.orgaes.ls.RETROFIT_NEW.LuckWinners.Model_Luck_Winners;
import com.orgaes.ls.RETROFIT_NEW.Luck_Radar_Data.LuckRadarData;
import com.orgaes.ls.RETROFIT_NEW.Notifications.NotificationsMain;
import com.orgaes.ls.RETROFIT_NEW.POSTEXTEND.PostExt_Module;
import com.orgaes.ls.RETROFIT_NEW.PrivacyUpdate.PrivacyModule;
import com.orgaes.ls.RETROFIT_NEW.Profile_Wishlist.Main_Profile_Wishlist;
import com.orgaes.ls.RETROFIT_NEW.Redeem.RedeemData;
import com.orgaes.ls.RETROFIT_NEW.RegData.RegData_Example;
import com.orgaes.ls.RETROFIT_NEW.SendRequest.SendRequest;
import com.orgaes.ls.RETROFIT_NEW.SignIn.Post_SignIn;
import com.orgaes.ls.RETROFIT_NEW.Update_Profile.Update_Prof;
import com.orgaes.ls.RETROFIT_NEW.UserCheck.UserCheck;
import com.orgaes.ls.RETROFIT_NEW.UserWinner.DrawWinner_Model;
import com.orgaes.ls.RETROFIT_NEW.VIEW.ProfDATA;
import com.orgaes.ls.RETROFIT_NEW.View_LUCK_EXCHANGE_DATA.View_Luck_Ex_DATA;
import com.orgaes.ls.RETROFIT_NEW.WinnerData.WinnerData_Main;
import com.orgaes.ls.RETROFIT_NEW.WishlistMASTERDATA.WishlistMasterModel;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

       @GET("radar/")
       Call<LuckRadarData> Fetch_LuckRadar(@Header("authKey") String authKey, @Query("edition_id") String edition_id);

        @GET("language")
        Call<FetchLanguage> Fetch_Language();

        @GET("view/")
        Call<ProfDATA> Fetch_Profile(@Header("authKey") String authKey);

        @FormUrlEncoded
        @POST("profileupdate/")
        Call<Update_Prof> Update_Profile(@Header("authKey") String authKey, @Field("user_name") String user_name,
                                         @Field("user_phone") String user_phone, @Field("user_gender") String user_gender, @Field("user_wishlist") String user_wishlist,
                                         @Field("user_photo") String user_photo, @Field("user_email") String user_email, @Field("user_privacy") String user_privacy,
                                         @Field("user_age_group") String user_age_group, @Field("user_profession") String user_profession);

        @GET("wishlist/")
        Call<Fetch_Wishlists> Fetch_WishList();
//
//        @FormUrlEncoded
//        @POST("authentication")
//        Call<Userlist> Fetch_Users(@Field("authKey") String authKey, @Field("source") String source, @Field("user_id") String user_id);

        @GET("friends")
        Call<FriendsList_Main> Fetch_Friends(@Header("authKey") String authKey);

        @GET("box/")
        Call<BOXDATA> Fetch_LuckBox(@Header("authKey") String authKey);

        @GET("postextend/")
        Call<PostExt_Module> PostExtend(@Header("authKey") String authKey,  @Query("claim_id") String claim_id,  @Query("extend_id") String extend_id);

        @GET("requests")
        Call<FetchAllReqData> Fetch_RqFriends(@Header("authKey") String authKey);

        @GET("coindata/")
        Call<CoinDATA_Model> Fetch_Coins(@Header("authKey") String authKey);

        @GET("leaderboardprofile/")
        Call<LB_Prof_Data> Fetch_Profile_user(@Header("authKey") String authKey,  @Query("user_id") String user_id);

        @GET("leaderboardprofile/")
        Call<LB_Prof_Data> Fetch_LB_Profile_user(@Header("authKey") String authKey, @Query("user_id") String user_id);

        @GET("coinrate/")
        Call<CoinRateData> Fetch_Coinrate(@Header("authKey") String authKey);

        @GET("wallet/")
        Call<WalletData> Fetch_Wallet(@Header("authKey") String authKey);

        @GET("coincount/")
        Call<CoinCountData> Fetch_Coincount(@Header("authKey") String authKey);

        @GET("extends/")
        Call<Extend_Time_Module> Fetch_ExtendTimes(@Header("authKey") String authKey, @Query("id") String id);

        @GET
        Call<FootprintMain> FETCH_FOOTPRINTS_CALL(@Url String footprint,@Header("authKey") String authKey);

        @GET
        Call<Footprint_mainData> FETCH_FOOTPRINTS_Details(@Url String footprint, @Header("authKey") String authKey);

        @GET
        Call<DipLucks_Model> FETCH_DipLuck(@Url String dipluck, @Header("authKey") String authKey);

        @GET
        Call<LeaderBoard_data> FETCH_LeaderBoard(@Url String leaderboard, @Header("authKey") String authKey);

        @GET("friendrequestsend/")
        Call<Req_Sent> SendRequest(@Header("authKey") String authKey, @Query("receiver_id") String receiver_id);

        @GET("userwinner/")
        Call<DrawWinner_Model> getWInnerDetails(@Header("authKey") String authKey);

        @GET("checkextend/")
        Call<CheckExtendModel> check_Extend(@Header("authKey") String authKey, @Query("id") String id);

        @GET("drawwinners/")
        Call<DrawWinners_Model> get_Winners(@Header("authKey") String authKey, @Query("type") String type);

        @GET("enrollluck/")
        Call<Enroll_Luck_Model> Join_Dipluck(@Header("authKey") String authKey, @Query("draw_id") String receiver_id);

        @GET("coinrequestsend/")
        Call<SendRequest> SendCoinRequest(@Header("authKey") String authKey, @Query("sender_id") String sender_id, @Query("receiver_id") String receiver_id, @Query("coin_no") String coin_no);

        @GET("coinrequestaccept/")
        Call<AccptReq> Api_AcceptReq(@Header("authKey") String authKey, @Query("rqst_id") String rqst_id, @Query("remarks") String remarks);

        @GET("friendrequestaccept/")
        Call<AccptReq> Api_AcceptReqFRND(@Header("authKey") String authKey,@Query("rqst_id") String rqst_id,@Query("remarks") String remarks);

        @GET("friendrequestreject/")
        Call<AccptReq> Api_RejectReqFRND(@Header("authKey") String authKey,@Query("rqst_id") String rqst_id,@Query("remarks") String remarks);

        @GET("coinrequestreject/")
        Call<AccptReq> Api_Reject_ReqCoin(@Header("authKey") String authKey,@Query("rqst_id") String rqst_id,@Query("remarks") String remarks);

        @GET("wishlistprofile/")
        Call<Main_Profile_Wishlist> Fetch_WishList_Profile(@Header("authKey") String authKey);

        @GET("wishlistmaster/")
        Call<WishlistMasterModel> Fetch_Wishlist_Master(@Header("authKey") String authKey);

        @GET("edition/")
        Call<EditionModule> Fetch_Editions(@Header("authKey") String authKey);

        @GET
        Call<EditionCheck> Fetch_Editions_WithLatLong(@Url String fetch_edition, @Header("authKey") String authKey);

        @GET("item/")
        Call<EXDATAMODEL> Fetch_Exchange(@Header("authKey") String authKey, @Query("edition_id") String edition_id);

        @GET("luckwinner")
        Call<Model_Luck_Winners> Fetch_L_W();

        //Corrections
        @FormUrlEncoded
        @POST("authentication")
        Call<NotificationsMain> Fetch_Notifications(@Field("authKey") String authKey, @Field("source") String source, @Field("user_id") String user_id);

        @FormUrlEncoded
        @POST("login/")
        Call<Post_SignIn> Api_SignIn(@Field("user_phone") String user_phone, @Field("user_imei") String user_imei);

        @FormUrlEncoded
        @POST("privacysetting/")
        Call<PrivacyModule> Api_Update_Privacy(@Header("authKey") String authKey, @Field("user_self_info") String user_self_info,
                                               @Field("prof_phone_number") String prof_phone_number, @Field("prof_coin") String prof_coin,
                                               @Field("prof_active_luck") String prof_active_luck,
                                               @Field("prof_total_luck") String prof_total_luck, @Field("prof_voucher") String prof_voucher,
                                               @Field("prof_wallet") String prof_wallet,
                                               @Field("prof_total_shares_coin") String prof_total_shares_coin, @Field("prof_total_chanks") String prof_total_chanks,
                                               @Field("prof_total_exchanges") String Prof_total_exchanges,
                                               @Field("prof_total_share_lucks") String prof_total_share_lucks, @Field("prof_chat") String prof_chat,
                                               @Field("prof_photo") String prof_photo);


        @GET("redeem/")
        Call<RedeemData> Api_Redeeem(@Header("authKey") String authKey, @Query("id") String id,
                                     @Query("item_id") String item_id, @Query("redeem_type") String redeem_type);

        @GET("drawredeem/")
        Call<RedeemData> Api_draw_Redeeem(@Header("authKey") String authKey, @Query("id") String luck_radar_id,
                                          @Query("item_id") String item_id);


    @GET("claim")
        Call<ClaimDatAModel> Api_radarClaim(@Header("authKey") String authKey, @Query("id") String luck_radar_id,
                                            @Query("claim_type") String claim_type);

        @GET("claim")
        Call<ClaimDatAModel> Api_Claim_Exchnage(@Header("authKey") String authKey, @Query("id") String id,
                                               @Query("claim_type") String claim_type);

        @GET("claim")
        Call<ClaimDatAModel> Api_Claim_Luck(@Header("authKey") String authKey, @Query("id") String id,
                                               @Query("claim_type") String claim_type);
      @GET("drawclaim")
      Call<ClaimDip_Module> Api_DipLuck_Claim(@Header("authKey") String authKey, @Query("id") String id);

        @GET("luck")
        Call<InstantLuckDATA> Api_InstLuck(@Header("authKey") String authKey,
                                           @Query("luck_id") String luck_id, @Query("ads_id") String ads_id);

        @GET("wishlistdelete/")
        Call<DeleteWishList> DeleteWishlist(@Header("authKey") String authKey, @Query("wishlist_id") String wishlist_id);

        @GET("wishlistupdate/")
        Call<DeleteWishList> WishlistMasterSelected(@Header("authKey") String authKey, @Query("wishlist_id") String wishlist_id);


        @GET("userads/")
        Call<CollectCoin> get_Collect(@Header("authKey") String authKey, @Query("coin_no") String coin_no,
                                      @Query("latitude") String latitude, @Query("longitude") String longitude,
                                      @Query("view_time") String view_time, @Query("id") String id, @Query("luck_id") String luck_id, @Query("link_click") String link_click);

        @FormUrlEncoded
        @POST("check/")
        Call<UserCheck> Api_Num_Check(@Field("user_phone") String user_phone, @Field("user_imei") String user_imei);

        @FormUrlEncoded
        @POST("check/")
        Call<UserCheck> Api_User_Check(@Field("user_phone") String user_phone, @Field("user_imei") String user_imei);

        @GET
        Call<Home_New_data> Api_ads(@Url String ads,@Header("authKey") String authKey);
        @GET
        Call<View_Luck_Ex_DATA> Api_getLuckDATA(@Url String luckdata,@Header("authKey") String authKey);
         @GET
        Call<LuckData_Model> Api_GetLuck(@Url String luckdata);
        @GET
        Call<WinnerData_Main> Api_GetWinnersData(@Url String luckdata);

        @FormUrlEncoded
        @POST("register/")
        Call<RegData_Example> Api_Register(@Header("authKey") String authKey, @Field("user_name") String user_name,
                                           @Field("user_email") String user_email, @Field("user_phone") String user_phone, @Field("user_gender") String user_gender,
                                           @Field("user_wishlist") String user_wishlist, @Field("user_privacy") String user_privacy, @Field("user_age_group") String user_age_group, @Field("user_profession") String user_profession);

}
