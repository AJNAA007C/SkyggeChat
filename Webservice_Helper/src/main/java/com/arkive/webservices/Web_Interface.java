package com.arkive.webservices;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by az-sys on 19/8/17.
 */

public interface Web_Interface {



    @Multipart
    @POST("file_upload.php")
    Call<JsonObject> postImage(
            @Part MultipartBody.Part image,
            @Part("id") RequestBody id
    );


    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);

    @FormUrlEncoded
    @POST("Mobile_reg/put_otp")
    public Call<JsonObject> getLogin_Credentials(@Header("X-API-KEY") String value, @Field("mobile") String mobile, @Field("name") String name);

    @FormUrlEncoded
    @POST("Search/data_search_get")
    public Call<JsonObject> getSearchResult(@Header("X-API-KEY") String value, @Field("search") String mobile, @Field("row") String raw, @Field("latitude") String lat, @Field("longitude") String lon, @Field("radius") String radius, @Field("type") String type);

    @FormUrlEncoded
    @POST("User_register/user_delete")
    public Call<JsonObject> deleteEducation(@Header("X-API-KEY") String value, @Field("id") String id);

    @FormUrlEncoded
    @POST("User_register/user_education_get")
    public Call<JsonObject> getEducationalData(@Header("X-API-KEY") String value, @Field("id") String id);

    @FormUrlEncoded
    @POST("Business_register/business_get")
    public Call<JsonObject> getCategories(@Header("X-API-KEY") String value, @Field("id") String id);

    @FormUrlEncoded
    @POST("Cart_product/product_seller_rating")
    public Call<JsonObject> postSellerRating(@Header("X-API-KEY") String value, @Field("seller_id") String stock_id, @Field("user_id") String user_id, @Field("rating_score") String rating_score, @Field("review") String comments);


    @FormUrlEncoded
    @POST("Cart_product/seller_user_like")
    public Call<JsonObject> setFollow(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("seller_id") String seller_id, @Field("like_status") String like_status, @Field("user_name") String user_name);

    @FormUrlEncoded
    @POST("Quotation/quotation_put")
    public Call<JsonObject> postQutation(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("cat_id") String cat_id, @Field("budjet_from") String budjet_from, @Field("requirements") String requirements, @Field("budjet_to") String budjet_to, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius);

    @FormUrlEncoded
    @POST("Quotation/get_quotation")
    public Call<JsonObject> getQutation(@Header("X-API-KEY") String value, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_product/category_by_area")
    // public Call<JsonObject> getCategoryByArea(@Header("X-API-KEY") String value, @Field("cat_id") int cat);
    public Call<JsonObject> getCategoryByArea(@Header("X-API-KEY") String value, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius, @Field("catid") String cat);

    @FormUrlEncoded
    @POST("Home_page/home_get")
    public Call<JsonObject> getHomeData(@Header("X-API-KEY") String value, @Field("user_id") String cat, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius, @Field("row") String raw);

    @FormUrlEncoded
    @POST("Cart_product/product_cart_order")
    // public Call<JsonObject> getCategoryByArea(@Header("X-API-KEY") String value, @Field("cat_id") int cat);
    public Call<JsonObject> checkOut(@Header("X-API-KEY") String value, @Field("stock_id") JSONArray product_id, @Field("user_id") String user_id, @Field("seller_id") JSONArray seller_id, @Field("qty") JSONArray qty, @Field("sub_total") JSONArray sub_total, @Field("total_amt") String total_amt, @Field("address_id") String address_id);

    @FormUrlEncoded
    @POST("Cart_product/deal_cart_order")
    // public Call<JsonObject> getCategoryByArea(@Header("X-API-KEY") String value, @Field("cat_id") int cat);
    public Call<JsonObject> checkOutDeal(@Header("X-API-KEY") String value,
                                         @Field("stock_id") JSONArray product_id,
                                         @Field("user_id") String user_id,
                                         @Field("seller_id") JSONArray seller_id,
                                         @Field("qty") JSONArray qty,
                                         @Field("sub_total") JSONArray sub_total,
                                         @Field("total_amt") String total_amt,
                                         @Field("deal_id") String deal_id,
                                         @Field("type") String type,
                                         @Field("address_id") String address_id);

    @FormUrlEncoded
    @POST("Cart_product/product_cart_list")
    public Call<JsonObject> getCart(@Header("X-API-KEY") String value, @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Cart_orders/cart_order_get")
    public Call<JsonObject> getOrders(@Header("X-API-KEY") String value, @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Cart_orders/cart_userstore_get")
    public Call<JsonObject> getStores(@Header("X-API-KEY") String value, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_product/seller_user_like")
    public Call<JsonObject> sellerLike(@Header("X-API-KEY") String value, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_seller_home/seller_wise_about")
    public Call<JsonObject> getAboutSeller(@Header("X-API-KEY") String value, @Field("user_id") String use_id, @Field("seller_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_product/seller_filter_list")
    public Call<JsonObject> getFilterSeller(@Header("X-API-KEY") String value, @Field("user_id") String use_id, @Field("seller_id") String user_id);


    @FormUrlEncoded
    @POST("Cart_seller_home/seller_bestproduct_get")
    public Call<JsonObject> getSellerHome(@Header("X-API-KEY") String value, @Field("user_id") String use_id, @Field("seller_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_seller_home/seller_wise_offers")
    public Call<JsonObject> getOfferSeller(@Header("X-API-KEY") String value, @Field("user_id") String use_id, @Field("seller_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_seller_home/seller_category_offers")
    public Call<JsonObject> getOfferCategoryWiseRefresh(@Header("X-API-KEY") String value, @Field("seller_id") String user_id, @Field("cat_id") String cat_id);

    @FormUrlEncoded
    @POST("Cart_seller_home/seller_wise_filter")
    public Call<JsonObject> getProductSeller(@Header("X-API-KEY") String value, @Field("user_id") String use_id, @Field("seller_id") String user_id, @Field("order") String order, @Field("min_price") String min_price, @Field("max_price") String max_price, @Field("option") JSONArray optarr, @Field("feature") JSONArray ftrarr);

    @FormUrlEncoded
    @POST("Cart_seller_home/seller_wise_filter")
    public Call<JsonObject> getProductSeller1(@Header("X-API-KEY") String value, @Field("user_id") String use_id, @Field("seller_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_seller_home/seller_wise_photos")
    public Call<JsonObject> getPhotosSeller(@Header("X-API-KEY") String value, @Field("user_id") String use_id, @Field("seller_id") String user_id);


    @FormUrlEncoded
    @POST("Cart_seller_home/seller_wise_contact")
    public Call<JsonObject> getContactSeller(@Header("X-API-KEY") String value, @Field("user_id") String use_id, @Field("seller_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_seller_home/seller_wise_rating")
    public Call<JsonObject> getReviewSeller(@Header("X-API-KEY") String value, @Field("user_id") String use_id, @Field("seller_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_product/product_wish_list")
    public Call<JsonObject> getWishList(@Header("X-API-KEY") String value, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_product/product_add_cart")
    public Call<JsonObject> addToCart(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("stock_id") String product_id, @Field("qty") String qty, @Field("deal_id") String deal_id, @Field("type") String type);

    @FormUrlEncoded
    @POST("Cart_product/product_add_cart_qty")
    public Call<JsonObject> addToCartqty(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("stock_id") String product_id, @Field("qty") String qty);

    @FormUrlEncoded
    @POST("Cart_product/payment_mode_put")
    public Call<JsonObject> changePaymentStatus(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("cart_id") String product_id, @Field("mode") String payment_mode);

    @FormUrlEncoded
    @POST("Cart_product/product_by_area")
    public Call<JsonObject> getProductByArea(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius, @Field("row") String raw, @Field("cat_id") String cat_id, @Field("order") String order, @Field("data") JSONObject data);


    @FormUrlEncoded
    @POST("Cart_seller_home/best_product_all")
    public Call<JsonObject> getSellerBestProduct(@Header("X-API-KEY") String value, @Field("user_id") String user_id,@Field("seller_id") String seller_id,@Field("row") String raw);


    @FormUrlEncoded
    @POST("Cart_seller_home/top_product_all")
    public Call<JsonObject> getSellerBestTopProduct(@Header("X-API-KEY") String value, @Field("user_id") String user_id,@Field("seller_id") String seller_id,@Field("row") String raw);


    @FormUrlEncoded
    @POST("Cart_seller_home//trending_product_all")
    public Call<JsonObject> getSellerBestTrendingProduct(@Header("X-API-KEY") String value, @Field("user_id") String user_id,@Field("seller_id") String seller_id,@Field("row") String raw);


    @FormUrlEncoded
    @POST("Home_page/product_by_area")
    public Call<JsonObject> getProductByAreaFromHome(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius, @Field("row") String raw, @Field("home_id") String home_id, @Field("order") String order, @Field("data") JSONObject data);

    @FormUrlEncoded
    @POST("Cart_product/bestdeal_viewall_get")
    public Call<JsonObject> getSpecialProductByArea(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius, @Field("row") String raw, @Field("home_id") String home_id, @Field("order") String order, @Field("data") JSONObject data, @Field("cat_id") String cat_id);

    @FormUrlEncoded
    @POST("Cart_product/product_sort_list")
    public Call<JsonObject> sortProduct(@Header("X-API-KEY") String value, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius, @Field("order") String order, @Field("cat_id") String cat_id, @Field("option") JSONArray option, @Field("feature") JSONArray JSONArray);

    @FormUrlEncoded
    @POST("Cart_product/product_filter_list")
    public Call<JsonObject> filterProduct(@Header("X-API-KEY") String value, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius, @Field("cat_id") String cat_id, @Field("min_price") String min_price, @Field("max_price") String max_price, @Field("option") JSONArray option, @Field("feature") JSONArray JSONArray);


    @FormUrlEncoded
    @POST("Notification/notification_get")
    public Call<JsonObject> getNotification(@Header("X-API-KEY") String value, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Quotation/get_quotation_replay")
    public Call<JsonObject> getQutationReplay(@Header("X-API-KEY") String value, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Quotation/quotation_chat_put")
    public Call<JsonObject> sendReply(@Header("X-API-KEY") String value, @Field("seller_id") String seller_id, @Field("user_id") String user_id, @Field("message") String message);

    @FormUrlEncoded
    @POST("Cart_product/get_user_list")
    public Call<JsonObject> getUser(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("number") String mobinumber);

    @FormUrlEncoded
    @POST("Cart_deals/deal_get_type")
    public Call<JsonObject> getDeal(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("deal_id") String deal_id, @Field("type") String type);

    @FormUrlEncoded
    @POST("Cart_product/deal_add_cart")
    public Call<JsonObject> addtocartDeal(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("deal_id") String deal_id, @Field("type") String type, @Field("seller_id") String seller_id, @Field("stock_id") JSONArray stock_id);

    @FormUrlEncoded
    @POST("Cart_deals/deal_user_rating")
    public Call<JsonObject> postDealRating(@Header("X-API-KEY") String value, @Field("deal_id") String deal_id, @Field("user_id") String user_id, @Field("rating_score") String rating_score, @Field("comments") String comments, @Field("type") String type);

    @FormUrlEncoded
    @POST("Cart_product/product_user_rating")
    public Call<JsonObject> postProductRating(@Header("X-API-KEY") String value, @Field("stock_id") String stock_id, @Field("user_id") String user_id, @Field("rating_score") String rating_score, @Field("comments") String comments, @Field("user_name") String user_name, @Field("seller_id") String seller_id);

    @FormUrlEncoded
    @POST("Cart_deals/deals_by_area")
    public Call<JsonObject> getDeals(@Header("X-API-KEY") String value, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius);

    @FormUrlEncoded
    @POST("Cart_deals/daily_offer_product")
    public Call<JsonObject> loadMoreDeals(@Header("X-API-KEY") String value, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius, @Field("row") String row);

    //  Call<JsonObject> filterVariant(String static_key, String item_id, String item_type);
    @FormUrlEncoded
    @POST("Cart_product/product_variant_get")
    public Call<JsonObject> filterVariant(@Header("X-API-KEY") String value, @Field("cat_id") String cat_id, @Field("variant_id") String varid, @Field("type") String type, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius);

    @FormUrlEncoded
    @POST("Cart_product/product_filter_category")
    public Call<JsonObject> filterCategory(@Header("X-API-KEY") String value, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius, @Field("cat_id") String cat_id, @Field("min_price") String min_price, @Field("max_price") String max_price, @Field("option") JSONArray option, @Field("feature") JSONArray feaures);

    @FormUrlEncoded
    @POST("Cart_product/product_filter_seller")
    public Call<JsonObject> filterSeller(@Header("X-API-KEY") String value, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius, @Field("cat_id") String cat_id, @Field("min_price") String min_price, @Field("max_price") String max_price, @Field("option") JSONArray option, @Field("feature") JSONArray feaures);

    @FormUrlEncoded
    @POST("Cart_product/product_wise_list")
    public Call<JsonObject> getProduct(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("stock_id") String stock_id, @Field("product_id") String pr_id, @Field("option") JSONArray option);

    @FormUrlEncoded
    @POST("Home_page/stock_wise_list")
    public Call<JsonObject> getOfferProduct(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("stock_id") String stock_id);

    @FormUrlEncoded
    @POST("Slug_url/product_wise_list")
    public Call<JsonObject> getProductFromLink(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("stock_id") String stock_id, @Field("product_id") String pr_id, @Field("option") JSONArray option, @Field("encrypted_id") String encrypted_id);

    @FormUrlEncoded
    @POST("Cart_product/product_liketowishlist")
    public Call<JsonObject> likeProduct(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("stock_id") String product_id, @Field("like_status") String like_status);

    @FormUrlEncoded
    @POST("Cart_product/product_user_like")
    public Call<JsonObject> likeSeller(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("like_status") String like_status);

    @FormUrlEncoded
    @POST("Cart_product/product_liketowishlist")
    public Call<JsonObject> saveProduct(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("stock_id") String product_id, @Field("status") String like_status);

    @FormUrlEncoded
    @POST("Cart_product/product_remove_wishlist")
    public Call<JsonObject> unsaveProduct(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("product_id") String product_id, @Field("like_status") String like_status);

    @FormUrlEncoded
    @POST("Cart_product/product_remove_cart")
    public Call<JsonObject> uncartProduct(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("product_id") String product_id, @Field("like_status") String like_status);

    @FormUrlEncoded
    @POST("Cart_product/product_category_wise")
    public Call<JsonObject> getProductCategoryWise(@Header("X-API-KEY") String value, @Field("cat_id") String cat_id, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius);

    @FormUrlEncoded
    @POST("User_setting/notification_get")
    public Call<JsonObject> getNotificatonStatus(@Header("X-API-KEY") String value, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_orders/delivery_address_get")
    public Call<JsonObject> getDeliveryAddressList(@Header("X-API-KEY") String value, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Cart_orders/delivery_address_delete")
    public Call<JsonObject> deleteDeliveryAddressList(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("id") String id);

    @FormUrlEncoded
    @POST("Cart_orders/address_get_id")
    public Call<JsonObject> getDeliveryAddress(@Header("X-API-KEY") String value, @Field("user_id") String user_id);


    /*$id = $this->post('id');

    $deliveryData['user_id'] = $this->post('user_id');
$deliveryData['name'] = $this->post('name');
$deliveryData['pincode'] = $this->post('pincode');
$deliveryData['address'] = $this->post('address');
$deliveryData['land_mark'] = $this->post('land_mark');
$deliveryData['country'] = $this->post('country');
$deliveryData['phone'] = $this->post('phone');
$deliveryData['alter_phone'] = $this->post('alter_phone');*/
    @FormUrlEncoded
    @POST("Cart_orders/order_delivery_address")
    public Call<JsonObject> setDeliveryAddress(@Header("X-API-KEY") String value, @Field("id") String id, @Field("user_id") String user_id, @Field("name") String name, @Field("pincode") String pincode
            , @Field("address") String address
            , @Field("land_mark") String land_mark
            , @Field("country") String country
            , @Field("phone") String phone
            , @Field("alter_phone") String alter_phone

    );

    @FormUrlEncoded
    @POST("Cart_orders/order_delivery_address")
    public Call<JsonObject> submitDeliveryAddress(@Header("X-API-KEY") String value
            , @Field("user_id") String user_id
            , @Field("address_id") String address

    );

    @FormUrlEncoded
    @POST("User_setting/user_phone_check")
    public Call<JsonObject> getChangeNumberOtp(@Header("X-API-KEY") String value, @Field("user_id") String reg_id, @Field("new_number") String new_number, @Field("old_number") String old_number);

    @FormUrlEncoded
    @POST("User_setting/change_number_put")
    public Call<JsonObject> changeNumber(@Header("X-API-KEY") String value, @Field("user_id") String reg_id, @Field("number") String number, @Field("new_number") String new_number, @Field("otp") String otp, @Field("fcm_token") String fcm_tocken);

    @FormUrlEncoded
    @POST("User_setting/notification_get_put")
    public Call<JsonObject> enableNotification(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("status") String status);

    @FormUrlEncoded
    @POST("User_setting/user_deactivate_get")
    public Call<JsonObject> deactivateUser(@Header("X-API-KEY") String value, @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Cart_product/product_home_get")
    public Call<JsonObject> getHomeDatas(@Header("X-API-KEY") String value, @Field("cat_id") String cat_id, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("radius") String radius);


    @FormUrlEncoded
    @POST("Cart_product/product_remove_cart")
    public Call<JsonObject> removeFromCart(@Header("X-API-KEY") String value, @Field("cart_id") String product_id);

    @FormUrlEncoded
    @POST("Cart_product/product_remove_wishlist")
    public Call<JsonObject> removeFromWishlist(@Header("X-API-KEY") String value, @Field("move_id") String wish_id);

    @FormUrlEncoded
    @POST("Cart_product/product_movetowishlist")
    public Call<JsonObject> moveToWishList(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("stock_id") String product_id);

    @FormUrlEncoded
    @POST("Feedback/reportissue_user_put")
    public Call<JsonObject> reportIssue(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("problem") String problem, @Field("priority") String priority, @Field("report_message") String message);

    @FormUrlEncoded
    @POST("Feedback/feedback_user_put")
    public Call<JsonObject> sentFeedback(@Header("X-API-KEY") String value, @Field("user_id") String user_id, @Field("feel_about") String feedback, @Field("feedback_message") String message);


    @FormUrlEncoded
    @POST("Mobile_reg/otp_check")
    public Call<JsonObject> getOtpStatus(@Header("X-API-KEY") String value, @Field("otp") String otp, @Field("number") String mobile, @Field("name") String name, @Field("fcm_token") String fcm_token);


    @FormUrlEncoded
    @POST("User_register/userput")
    public Call<JsonObject> getUser_reg(@Header("X-API-KEY") String value, @Field("id") String id, @Field("user_name") String user_name,
                                        @Field("image_file") String image_file
    );

    @FormUrlEncoded
    @POST("User_register/user_locput")
    public Call<JsonObject> setUser_loc(@Header("X-API-KEY") String value, @Field("id") String id, @Field("location") String locatio,
                                        @Field("postal_code") String postal_code, @Field("lat") String lat, @Field("lon") String lon,
                                        @Field("country") String country
    );


    @FormUrlEncoded
    @POST("User_register/user_location_update")
    public Call<JsonObject> setUser_locandName(@Header("X-API-KEY") String value, @Field("user_id") String id, @Field("location") String locatio,
                                               @Field("postal_code") String postal_code, @Field("lat") String lat, @Field("lon") String lon,
                                               @Field("country") String country, @Field("name") String user_name
    );

    @FormUrlEncoded
    @POST("User_register/user_personalput")
    public Call<JsonObject> setEducation(@Header("X-API-KEY") String value,
                                         @Field("id") String id, @Field("education_type") String ed_type, @Field("school_name") String name,
                                         @Field("date_from") String date_from, @Field("subject") String subject, @Field("date_to") String date_to, @Field("action") String action, @Field("ed_id") String ed_id
    );

    /*
     $id = $this->post('id');
     $user_name = $this->post('user_name');
     $address = $this->post('address');
     $gender = $this->post('gender');
     $birth_date = $this->post('birth_date');
     $birth_date = date('Y-m-d H:i:s', strtotime($birth_date));
     $joining_date = $this->post('joining_date');
     $joining_date = date('Y-m-d H:i:s', strtotime($joining_date));
     $martial_status = $this->post('martial_status');
     $bio_quto = $this->post('bio_quto');

     */
    @FormUrlEncoded
    @POST("User_register/user_basicput")
    public Call<JsonObject> setUser_Basic_details(@Header("X-API-KEY") String value, @Field("id") String id, @Field("user_name") String user_name,
                                                  @Field("address") String address, @Field("address1") String address1, @Field("address2") String address2, @Field("address3") String address3,
                                                  @Field("gender") String gender, @Field("birth_date") String birth_date, @Field("joining_date") String joining_date,
                                                  @Field("martial_status") String martial_status, @Field("bio_quto") String bio_quto);

    @FormUrlEncoded
    @POST("User_register/user_contactput")
    public Call<JsonObject> setUser_Contact_details(@Header("X-API-KEY") String value, @Field("id") String id,
                                                    @Field("facebook") String facebook, @Field("whatsapp") String watsapp, @Field("youtube") String youtube, @Field("instagram") String instagram,
                                                    @Field("twitter") String twitter, @Field("linkedin") String linkedin, @Field("phone") JSONArray phone, @Field("email") String email
    );

    @FormUrlEncoded
    @POST("Cart_product/product_filter_get")
    public Call<JsonObject> filterallProduct(@Header("X-API-KEY") String value, @Field("latitude") String lat, @Field("longitude") String longitude, @Field("radius") String radius, @Field("cat_id") String cat_id, @Field("min_pr") String min_pr, @Field("max_pr") String max_pr, @Field("option") JSONArray option, @Field("feature") JSONArray feature);


 /*@FormUrlEncoded
    @POST("User_register/user_put")
    public Call<JsonObject>getUser_reg( @Field("id")String id,@Field("user_name")String user_name,@Field("address")String address,
                                        @Field("birth_date")String birth_date,@Field("joining_date")String joining_date,@Field("martial_status")String martial_status,
                                        @Field("bio_quto")String bio_quto,@Field("facebook")String facebook,@Field("twitter")String twitter,
                                        @Field("linkedin")String linkedin,@Field("youtube")String youtube,@Field("whatsapp")String whatsapp,
                                        @Field("location")String location,@Field("postal")String postal,@Field("lat")String lat,
                                        @Field("lon")String lon,@Field("country")String country,@Field("image_name")String image_name,
                                        @Field("image_file_cover")String image_file_cover,@Field("phone")String phone,@Field("email")String email,
                                        @Field("course")String course,@Field("univercity")String univercity,@Field("date_from")String date_from,
                                        @Field("date_to")String date_to,@Field("company_name")String company_name,@Field("position")String position,
                                        @Field("work_date_from")String work_date_from,@Field("work_date_to")String work_date_to
                                        );*/


}










































/* @FormUrlEncoded
    @POST("login/en")
    public Call<JsonObject>getLogin_Credentials(@Field("email_phone")String email_phone, @Field("password")String password,
                                                @Field("device_id")String device_id, @Field("device_token")String device_token);





    @FormUrlEncoded
    @POST("register/en")
    public Call<JsonObject>UserRegister(@Field("name")String name, @Field("email")String email,@Field("password")String password, @Field("phone_number")String phone_number);


    @FormUrlEncoded
    @POST("send-otp/en")
    public Call<JsonObject>sendOtp(@Field("id")String id, @Field("phone_number")String phone_number);


    @FormUrlEncoded
    @POST("verify-otp/en")
    public Call<JsonObject>verifyOtp(@Field("id")String id, @Field("phone_number")String phone_number, @Field("otp")String otp,
                                     @Field("device_id")String device_id, @Field("device_token")String device_token);

    @FormUrlEncoded
    @POST("categories/en")
    public Call<JsonObject>getCategories(@Field("id")String id, @Field("token")String token);

    @FormUrlEncoded
    @POST("services/en")
    public Call<JsonObject>getServices(@Field("id")String id, @Field("token")String token, @Field("category")String category, @Field("value")String category_slug);

    @FormUrlEncoded
    @POST("booking/en")
    public Call<JsonObject>setBooking(@Field("id")String id, @Field("token")String token, @Field("latitude")String latitude,
                                      @Field("longtitude")String longtitude, @Field("services")JSONArray services,
                                      @Field("booking_date")String booking_date, @Field("job_type")String job_type,
                                      @Field("booking_time")String booking_time, @Field("name")String name,
                                      @Field("phone_number")String phone_number, @Field("address")String address,
                                      @Field("coupon")String coupon, @Field("message")String message, @Field("city")String city,
                                      @Field("amount") String amount, @Field("country")String country, @Field("state")String state);






    @FormUrlEncoded
    @POST("mybookings/en")
    public Call<JsonObject>getMyBookings(@Field("id")String id, @Field("token")String token, @Field("mode")String mode);

    @FormUrlEncoded
    @POST("logout/en")
    public Call<JsonObject>logout(@Field("id")String id, @Field("token")String token);

    @FormUrlEncoded
    @POST("locations/en")
    public Call<JsonObject>getLocations(@Field("id")String id, @Field("token")String token);

    @FormUrlEncoded
    @POST("notifications/en")
    public Call<JsonObject>getNotifications(@Field("id")String id, @Field("token")String token);



    @FormUrlEncoded
    @POST("change-password/en")
    public Call<JsonObject>changePassword(@Field("id")String id, @Field("token")String token, @Field("email")String email,
                                          @Field("old_password")String old_password,@Field("new_password")String new_password);


    @FormUrlEncoded
    @POST("reset-password/en")
    public Call<JsonObject>resetPassword(@Field("password")String new_password, @Field("email")String email,@Field("code")String code
                                          );



    @GET("getversion/1")
    public Call<JsonObject>getVersion();

    @FormUrlEncoded
    @POST("check-token/en")
    public Call<JsonObject>checkToken(@Field("id")String id, @Field("token")String token);


    @FormUrlEncoded
    @POST("search/en")
    public Call<JsonObject>search(@Field("id")String id, @Field("token")String token,@Field("query")String query);

    @FormUrlEncoded
    @POST("inspection-charge/en")
    public Call<JsonObject>getInspectionCharge(@Field("id")String id, @Field("token")String token,@Field("services")JSONArray services);

    @FormUrlEncoded
    @POST("customer-addresses/add/en")
    public Call<JsonObject>setMyAddress(@Field("id")String id, @Field("token")String token, @Field("name")String name,
                                        @Field("address")String address, @Field("city")String city,
                                        @Field("state")String state, @Field("country")String country,
                                        @Field("zipcode")String zipcode, @Field("mobile")String mobile,
                                        @Field("latitude")String latitude,@Field("longtitude")String longtitude,@Field("type")String type);
    @FormUrlEncoded
    @POST("customer-addresses/update/en")
    public Call<JsonObject>updateMyAddress(@Field("id")String id, @Field("token")String token, @Field("address_id")String address_id, @Field("name")String name,
                                           @Field("address")String address, @Field("city")String city,
                                           @Field("state")String state, @Field("country")String country,
                                           @Field("zipcode")String zipcode, @Field("mobile")String mobile,
                                           @Field("latitude")String latitude,@Field("longtitude")String longtitude,@Field("type")String type);
    @FormUrlEncoded
    @POST("customer-addresses/delete/en")
    public Call<JsonObject>deleteMyAddress(@Field("id")String id, @Field("token")String token, @Field("address_id")String address_id);


    @FormUrlEncoded
    @POST("customer-addresses/en")
    public Call<JsonObject>getMyAddress(@Field("id")String id, @Field("token")String token);


    @FormUrlEncoded
    @POST("send-reset-password-code/en")
    public Call<JsonObject>forgotPassword(@Field("email")String id);*/
