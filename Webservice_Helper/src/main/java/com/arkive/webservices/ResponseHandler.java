package com.arkive.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.autograde.webservices.R;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by az-sys on 19/8/17.
 */

public class ResponseHandler implements Callback<JsonObject> {

    public static boolean isshowprogress=true;
    Context context;
    ResponseCallback web_interface;
    Call<JsonObject> jsonObjectCall;
    ProgressDialog progressDialog;



    public ResponseHandler(Context context, ResponseCallback web_interface, Call<JsonObject> jsonObjectCall) {
        this.context = context;
        this.web_interface = web_interface;
        this.jsonObjectCall = jsonObjectCall;

        if(!context.getClass().getName().contains("LivetrackActivity")) {
            progressDialog = new ProgressDialog(context);
            if(progressDialog!=null&&!progressDialog.isShowing()) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Loading.....");
                progressDialog.setCancelable(false);
                try {
                    if (isshowprogress && !progressDialog.isShowing())
                        progressDialog.show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }



    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        switch (response.code()) {
            case Responsecode.ok:
                progressDialog.dismiss();
                if (web_interface != null) {
                    if (response.body() != null) {
                        web_interface.getResponse(response.code(), response.body());
                    }
                }
                break;
            case Responsecode.server_error:
                progressDialog.dismiss();
                web_interface.getError(call);
                break;
            case Responsecode.notfound:
                progressDialog.dismiss();
                web_interface.getError(call);
                break;
        }

    }

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {

       /* if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }*/
        progressDialog.dismiss();

        if (!Retrofit_Helper.isNetworkAvailable(context)) {
            web_interface.getError(call);
        } else {
            web_interface.getError(call);
        }

    }


    private void showalert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Onlister");
        builder.setMessage(message);
//        builder.setCancelable(false);
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                jsonObjectCall.clone().enqueue(new ResponseHandler(context, web_interface, jsonObjectCall));

            }
        });

        builder.show();
    } private void showalert1(String message) {
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.nodata));
        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) imageView.getLayoutParams();

        imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

//        builder.setCancelable(false);
       /* builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                // new ResponseHandler(context,ResponseHandler.this,jsonObjectCall);
                jsonObjectCall.clone().enqueue(new ResponseHandler(context, web_interface, jsonObjectCall));

            }
        });*/
/*
        builder.show();*/
    }


}
