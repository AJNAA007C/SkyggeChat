package com.arkive.webservices;

import com.google.gson.JsonObject;

import retrofit2.Call;

/**
 * Created by az-sys on 19/8/17.
 */

public interface ResponseCallback {

    public void getResponse(int code,JsonObject jsonObject);

    public void getError(Call<JsonObject> call);
}
