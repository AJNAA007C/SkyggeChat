package com.android.rfchat.Steganography;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.rfchat.R;
import com.arkive.webservices.Retrofit_Helper;
import com.arkive.webservices.Web_Interface;
import com.ayush.imagesteganographylibrary.Text.AsyncTaskCallback.TextDecodingCallback;
import com.ayush.imagesteganographylibrary.Text.AsyncTaskCallback.TextEncodingCallback;
import com.ayush.imagesteganographylibrary.Text.ImageSteganography;
import com.ayush.imagesteganographylibrary.Text.TextDecoding;
import com.ayush.imagesteganographylibrary.Text.TextEncoding;
import com.google.gson.JsonObject;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class Encode extends AppCompatActivity implements TextEncodingCallback , TextDecodingCallback {

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "Encode Class";

    private Uri filepath;

    //Bitmaps
    private Bitmap original_image;
    private Bitmap encoded_image;

    //Created variables for UI
    TextView whether_encoded;
    ImageView imageView;
    EditText message, secret_key;
    Button choose_image_button, encode_button, save_image_button;

    //Objects needed for encoding
    TextEncoding textEncoding;
    ImageSteganography imageSteganography, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_encode);

        //initialized the UI components

        whether_encoded = (TextView) findViewById(R.id.whether_encoded);

        imageView = (ImageView) findViewById(R.id.imageview);

        message = (EditText) findViewById(R.id.message);
        secret_key = (EditText) findViewById(R.id.secret_key);

        choose_image_button = (Button) findViewById(R.id.choose_image_button);
        encode_button = (Button) findViewById(R.id.encode_button);
        save_image_button = (Button) findViewById(R.id.save_image_button);

        //Choose image button
        choose_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageChooser();
            }
        });

        //Encode Button
        encode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(message.getText().toString())||TextUtils.isEmpty(secret_key.getText().toString())){
                    Toast.makeText(Encode.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    return;

                }
                whether_encoded.setText("");
                if (filepath != null){
                    if (message.getText() != null ){

                        //ImageSteganography Object instantiation
                        imageSteganography = new ImageSteganography(message.getText().toString(),
                                secret_key.getText().toString(),
                                original_image);
                        //TextEncoding object Instantiation
                        textEncoding = new TextEncoding(Encode.this, Encode.this);
                        //Executing the encoding
                        textEncoding.execute(imageSteganography);
                    }
                }
//                ImageSteganography imageSteganography = null;
//                try {
//                    imageSteganography = new ImageSteganography(secret_key.getText().toString(),
//                            MediaStore.Images.Media.getBitmap(getContentResolver(), filepath));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


//                //Making the TextDecoding object
//                TextDecoding textDecoding = new TextDecoding(Encode.this, Encode.this);
//
//                //Execute Task
//                textDecoding.execute(imageSteganography);
            }
        });

        //Save image button
        save_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog = new ProgressDialog(Encode.this);
                progressDialog.setTitle("Saving Image");
                progressDialog.setMessage("Loading Please Wait...");
                progressDialog.show();

                if(TextUtils.isEmpty(message.getText().toString())||TextUtils.isEmpty(secret_key.getText().toString())){
                    Toast.makeText(Encode.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    return;

                }
                whether_encoded.setText("");
                if (filepath != null){
                    if (message.getText() != null ){

                        //ImageSteganography Object instantiation
                        imageSteganography = new ImageSteganography(message.getText().toString(),
                                secret_key.getText().toString(),
                                original_image);
                        //TextEncoding object Instantiation
                        textEncoding = new TextEncoding(Encode.this, Encode.this);
                        //Executing the encoding
                        textEncoding.execute(imageSteganography);
                    }
                }
                String name = UUID.randomUUID().toString();



                File file = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                File rootdir = new File(file, name);
                rootdir.mkdir();

                if (encoded_image != null){

                    String name_image = name + "_encoded" + ".png";
                    File encoded_file = new File(rootdir, name_image);
                    try {
                        encoded_file.createNewFile();
                        FileOutputStream fout_encoded_image = new FileOutputStream(encoded_file);
                        encoded_image.compress(Bitmap.CompressFormat.PNG, 100, fout_encoded_image);
                        fout_encoded_image.flush();
                        fout_encoded_image.close();


                        String charset = "UTF-8";
                        String requestURL = "www.coddeen.com/Android/Project_1/file_upload.php";


                        //getting the actual path of the image
                        String path = encoded_file.getAbsolutePath();
//                        ImageSteganography imageSteganography = null;
//                        try {
//                            imageSteganography = new ImageSteganography("0000000000000000",
//                                    MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse("file://"+path)));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }printStackTrace


                        //Making the TextDecoding object
//                        TextDecoding textDecoding = new TextDecoding(Encode.this, Encode.this);
//
//                        //Execute Task
//                        textDecoding.execute(imageSteganography);
                        imageUpload(path);
                        Log.d("Path",path);

                        //Uploading code
                        try {
                            String uploadId = UUID.randomUUID().toString();

                            //Creating a multi part request
                            new MultipartUploadRequest(Encode.this, uploadId, requestURL)
                                    .addFileToUpload(path, "uploaded_file") //Adding file
//                                    .addParameter("name", name) //Adding text parameter to the request
                                    .setNotificationConfig(new UploadNotificationConfig())
                                    .setMaxRetries(2)
                                    .startUpload(); //Starting the upload

                        } catch (Exception exc) {
                            Toast.makeText(Encode.this, exc.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                if (original_image != null){

                    String name_image = name + "_original" + ".png";
                    File original_file = new File(rootdir, name_image);
                    try {
                        original_file.createNewFile();
                        FileOutputStream fout_original_image = new FileOutputStream(original_file);
                        original_image.compress(Bitmap.CompressFormat.PNG, 100, fout_original_image);
                        fout_original_image.flush();
                        fout_original_image.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                progressDialog.dismiss();
            }
        });

    }

    void imageUpload(String mediaPath){
//        ProgressDialog dialog=new ProgressDialog(Encode.this);
//        dialog.show();
        Retrofit retrofit=new Retrofit.Builder().
                baseUrl("http://www.coddeen.com/Android/Project_1/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        Web_Interface services = retrofit.create(Web_Interface.class);

//Bikin request body
        File file = new File(mediaPath);
        final RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", file.getName(), reqFile);
        RequestBody reqId = RequestBody.create(MediaType.parse("text/plain"), "123");

//Tembak APInya
        services.postImage(body, reqId)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        //tulis aksi disini
                        if(response.code()==200) {
                            Log.i("resp", response.body() + " " + response.code());
                            JsonObject object = response.body();
                            String msg = String.valueOf(object);
                            try {

                                JSONObject object1 = new JSONObject(msg);
                                String s = object1.getString("image_path");
                                Intent intent = new Intent();
                                intent.putExtra("image", Retrofit_Helper.image_url+s);
                                setResult(RESULT_OK, intent);
                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(Encode.this, "Image not uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        //tulis aksi disini
                    }
                });
    }void ImageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Image set to imageView
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null){

            filepath = data.getData();
            try{
                original_image = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);

                imageView.setImageBitmap(original_image);
            }
            catch (IOException e){
                Log.d(TAG, "Error : " + e);
            }
        }

    }

    // Override method of TextEncodingCallback

    @Override
    public void onStartTextEncoding() {
        //Whatever you want to do at the start of text encoding
    }

    @Override
    public void onCompleteTextEncoding(ImageSteganography result) {

        //By the end of textEncoding

        this.result = result;

        if (result != null && result.isEncoded()){
            encoded_image = result.getEncoded_image();
            whether_encoded.setText("Encoded");
            imageView.setImageBitmap(encoded_image);
            whether_encoded.setText("" + result.getMessage());


            String name = UUID.randomUUID().toString();

            ProgressDialog progressDialog = new ProgressDialog(Encode.this);
            progressDialog.setTitle("Saving Image");
            progressDialog.setMessage("Loading Please Wait...");
            progressDialog.show();

            File file = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            File rootdir = new File(file, name);
            rootdir.mkdir();

            if (encoded_image != null) {

                String name_image = name + "_encoded" + ".png";
                File encoded_file = new File(rootdir, name_image);
                try {
                    encoded_file.createNewFile();
                    FileOutputStream fout_encoded_image = new FileOutputStream(encoded_file);
                    encoded_image.compress(Bitmap.CompressFormat.PNG, 100, fout_encoded_image);
                    fout_encoded_image.flush();
                    fout_encoded_image.close();


                    String charset = "UTF-8";
                    String requestURL = "www.coddeen.com/Android/Project_1/file_upload.php";


                    //getting the actual path of the image
                    String path = encoded_file.getAbsolutePath();
                    ImageSteganography imageSteganography = null;
//                    try {
//                        imageSteganography = new ImageSteganography("0000000000000000",
//                                MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse("file://" + path)));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }


                    //Making the TextDecoding object
//                        TextDecoding textDecoding = new TextDecoding(Encode.this, Encode.this);
//
//                        //Execute Task
//                        textDecoding.execute(imageSteganography);
                    imageUpload(path);
                    Log.d("Path", path);

                    //Uploading code
                    try {
                        String uploadId = UUID.randomUUID().toString();

                        //Creating a multi part request
                        new MultipartUploadRequest(Encode.this, uploadId, requestURL)
                                .addFileToUpload(path, "uploaded_file") //Adding file
//                                    .addParameter("name", name) //Adding text parameter to the request
                                .setNotificationConfig(new UploadNotificationConfig())
                                .setMaxRetries(2)
                                .startUpload(); //Starting the upload

                    } catch (Exception exc) {
                        Toast.makeText(Encode.this, exc.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}