package com.arkive.webservices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by az-sys on 19/8/17.
 */

public class Retrofit_Helper {

/************************  cloud test*********************************/

//onlistertest.ap-south-1.elasticbeanstalk.com/shop/api/
    //String base_url = "http://onlistertest.ap-south-1.elasticbeanstalk.com/api/";
    String base_url = "http://onlister.com/production/v1/api/";
    public static String image_url = "http://coddeen.com/Android/Project_1/";
    public static String deals_image_url = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/deals/";
    public static String image_url_product = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/product_multimage/";
    public static String image_url_cat = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/cart_category/";
    public static String image_url_cat_icon = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/cat_icon/";
    public static String image_url_user = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/userprofile/";
    public static String image_url_prof = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/profile/";
    public static String image_url_cover = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/usercover/";
    public static String image_seller_cover = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/cover/";
    public static String image_url_sellercover = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/cover/";
    public static String cat_image = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/category/";
    public static String image_url_banner = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/banner/";
    public static String image_url_home_banner = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/home-banner/";
    public static String image_url_banner_seller = "https://s3.ap-south-1.amazonaws.com/onlister-upload/uploads/seller-banner/";
    /************************  cloud *********************************//*
public static String image_url_banner = "http://onlister.com/testing/uploads/banner/";
//onlistertest.ap-south-1.elasticbeanstalk.com/shop/api/
    String base_url = "http://onlister.ap-south-1.elasticbeanstalk.com/shop/api/";
    public static String deals_image_url = "http://onlister.ap-south-1.elasticbeanstalk.com/shop/api/uploads/deals/";
    public static String image_url_product = "http://onlister.ap-south-1.elasticbeanstalk.com/shop/api/uploads/product_multimage/";
    public static String image_url_cat = "http://onlister.ap-south-1.elasticbeanstalk.com/shop/api/uploads/cart_category/";
    public static String image_url_user = "http://onlister.ap-south-1.elasticbeanstalk.com/shop/api/uploads/userprofile/";
    public static String image_url_prof = "http://onlister.ap-south-1.elasticbeanstalk.com/shop/api/uploads/profile/";
    public static String image_url_cover = "http://onlister.ap-south-1.elasticbeanstalk.com/shop/api/uploads/usercover/";
    public static String image_url_sellercover = "http://onlister.ap-south-1.elasticbeanstalk.com/shop/api/uploads/cover/";
    public static String cat_image = "http:////onlister.ap-south-1.elasticbeanstalk.com/shop/api/uploads/category/";
*/

    /************************demo*********************************/
  /*  public static String image_url_banner = "http://onlister.com/onlistdemo/uploads/banner/";
    public static String cat_image = "http://onlister.com/onlistdemo/uploads/category/";
    //http://192.168.0.7/shop/api/Mobile_reg/put_otp
//     String host_url = "http://onlister.com/onlistdemo/api/";
    String base_url = "http://onlister.com/onlistdemo/api/";
    //     String base_url = "http://192.168.0.6/shop/api/";
    public static String deals_image_url = "http://onlister.com/onlistdemo/uploads/deals/";
    public static String image_url_product = "http://onlister.com/onlistdemo/uploads/product_multimage/";
    public static String image_url_cat = "http://onlister.com/onlistdemo/uploads/cart_category/";
    public static String image_url_user = "http://onlister.com/onlistdemo/uploads/userprofile/";
    public static String image_url_prof = "http://onlister.com/onlistdemo/uploads/profile/";
    public static String image_url_cover = "http://onlister.com/onlistdemo/uploads/usercover/";
    public static String image_url_sellercover = "http://onlister.com/onlistdemo/uploads/cover/";*/

    /************************server*********************************/
 /*   public static String image_url_banner = "http://onlister.com/testing/uploads/banner/";
    public static String cat_image="http://onlister.com/onlistdemo/uploads/category/";
    //http://192.168.0.7/shop/api/Mobile_reg/put_otp
//     String host_url = "http://onlister.com/testing/api/";
    String base_url = "http://onlister.com/testing/api/";
    //     String base_url = "http://192.168.0.6/shop/api/";
    public static String deals_image_url = "http://onlister.com/testing/uploads/deals/";
    public static String image_url_product = "http://onlister.com/testing/uploads/product_multimage/";
    public static String image_url_cat = "http://onlister.com/testing/uploads/cart_category/";
    public static String image_url_user = "http://onlister.com/testing/uploads/userprofile/";
    public static String image_url_prof = "http://onlister.com/testing/uploads/profile/";
    public static String image_url_cover = "http://onlister.com/testing/uploads/usercover/";
    public static String image_url_sellercover = "http://onlister.com/testing/uploads/cover/";
*/
    /*public static String image_url_banner="http://onlister.com/testing/uploads/banner/";
    //http://192.168.0.7/shop/api/Mobile_reg/put_otp
//     String host_url = "http://onlister.com/testing/api/";
     String base_url = "http://onlister.com/onlistdemo/api/";
//     String base_url = "http://192.168.0.6/shop/api/";
    public static String image_url_product = "http://onlister.com/onlistdemo/uploads/product_multimage/";
    public static String image_url_cat = "http://onlister.com/onlistdemo/uploads/cart_category/";
    public static String image_url_user = "http://onlister.com/onlistdemo/uploads/userprofile/";
    public static String image_url_prof = "http://onlister.com/onlistdemo/uploads/profile/";
    public static String image_url_cover = "http://onlister.com/onlistdemo/uploads/usercover/";
    public static String image_url_sellercover = "http://onlister.com/onlistdemo/uploads/cover/";
*/
    public Retrofit_Helper() {
    }

    public Web_Interface getRetrofitBuilder() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(new BasicAuthInterceptor("onadminbusl", "onspls_12!@"))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Web_Interface web_interface = retrofit.create(Web_Interface.class);
        Log.i("url_base34", base_url + "," + web_interface);

        return web_interface;


    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
