package com.android.rfchat.Steganography;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PowerManager;
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
import com.ayush.imagesteganographylibrary.Text.AsyncTaskCallback.TextDecodingCallback;
import com.ayush.imagesteganographylibrary.Text.ImageSteganography;
import com.ayush.imagesteganographylibrary.Text.TextDecoding;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class Decode extends AppCompatActivity implements TextDecodingCallback {

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "Decode Class";

    //Initializing the UI components
    TextView message,textView;
    ImageView imageView;
    EditText  secret_key;
    Button choose_image_button, decode_button;

    //ImageSteganography object
    ImageSteganography result;
    String imageuri="";
    private PowerManager.WakeLock mWakeLock;
    private ProgressDialog progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);

        imageuri=getIntent().getStringExtra("uri");

        textView = findViewById(R.id.whether_decoded);

        imageView = findViewById(R.id.imageview);
        //imageView.setImageURI(Uri.parse(imageuri));
        Glide
                .with(getApplicationContext())
                .asBitmap()
                .load(imageuri)
                .into(imageView);

        message = findViewById(R.id.message);
        secret_key = findViewById(R.id.secret_key);
        progressBar=new ProgressDialog(Decode.this);
        choose_image_button = findViewById(R.id.choose_image_button);
        choose_image_button.setVisibility(View.INVISIBLE);
        decode_button = findViewById(R.id.decode_button);

        //Choose Image Button
        choose_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageChooser();
            }
        });

        //Decode Button
        decode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(secret_key.getText().toString())){
                    Toast.makeText(Decode.this, "Secret Key cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (imageuri != null){
                    retrofitDownload(imageuri);
                }
            }
        });


    }

    void ImageChooser(){
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

            Uri filepath = data.getData();
            try{
                //Bitmap
                Bitmap original_image = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);

                imageView.setImageBitmap(original_image);
            }
            catch (IOException e){
                Log.d(TAG, "Error : " + e);
            }
        }

    }

    @Override
    public void onStartTextEncoding() {
        //Whatever you want to do by the start of textDecoding
    }

    @Override
    public void onCompleteTextEncoding(ImageSteganography result) {

        //By the end of textDecoding
        progressBar.dismiss();

        this.result = result;

        if (result != null){
            if (!result.isDecoded())
                textView.setText("No message found");
            else{
                if (!result.isSecretKeyWrong()){
                   // textView.setText(result.getMessage());
                    message.setText("" + result.getMessage());
                    message.setTextColor(getResources().getColor(R.color.black));
                    textView.setText("");
                }
                else {
                    textView.setText("Wrong secret key");
                }
            }
        }
        else {
            textView.setText("Select Image First");
        }


    }

//  download

  public void   retrofitDownload(String fileUrl){
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
              .url(fileUrl)
              .build();

      client.newCall(request).enqueue(new Callback() {

          @Override
          public void onFailure(okhttp3.Call call, IOException e) {
              Log.i("ssaa",e.getMessage());
              Toast.makeText(Decode.this, "Network connection time out....!!!,try again...", Toast.LENGTH_SHORT).show();

          }

          @Override
          public void onResponse(okhttp3.Call call, okhttp3.Response response) {
              Log.i("ssaa123",response.message());
              AsyncDownloader s=new AsyncDownloader();
              s.body=response.body();
              s.execute();
              //writeResponseBodyToDisk(response.body());

          }

//          @Override
//          public void onFailure(Request request, IOException e) {
//          }
//
//          @Override
//          public void onResponse(Response response) throws IOException {
//          }
      });

    }
    public class AsyncDownloader extends AsyncTask<Void, Long, Boolean> {
        private final String URL = "file_url";
        public ResponseBody body;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           runOnUiThread(new Thread(){public void run(){
               progressBar.setTitle("Decoding file…");
               progressBar.setIndeterminate(false);
               progressBar.setMax(100);
               progressBar.setCancelable(false);
               progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

               progressBar.show();
            }});


        }

        public void AsyncDownloader(ResponseBody body){
    this.body=body;

}
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // todo change the file location/name according to your needs
                File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "hijkuo.JPEG");

                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {
                    byte[] fileReader = new byte[4096];

                    long fileSize = body.contentLength();
                    long fileSizeDownloaded = 0;

                    inputStream = body.byteStream();
                    outputStream = new FileOutputStream(futureStudioIconFile);

                    while (true) {
                        int read = inputStream.read(fileReader);

                        if (read == -1) {
                            break;
                        }

                        outputStream.write(fileReader, 0, read);

                        fileSizeDownloaded += read;

                        Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                        publishProgress((long)((fileSizeDownloaded*100)/fileSize));

                    }
                    File file            = getApplicationContext().getFileStreamPath("hijkuo.JPEG");

                    String imageFullPath = file.getAbsolutePath();
                    //     outputStream = getApplicationContext().openFileOutput(System.currentTimeMillis()+"", Context.MODE_PRIVATE);
                    //  b.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    //  foStream.close();
                    //byte[] bitmapdata = outputStream.toByteArray();
                    //File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+transaction.getUniqueId()+".pdf");
                    //Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);


                    ImageSteganography imageSteganography = null;
                    try {
                        imageSteganography = new ImageSteganography(secret_key.getText().toString(),
                                MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse("file://"+getExternalFilesDir(null) + File.separator + "hijkuo.JPEG")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Making the TextDecoding object
                    final ImageSteganography finalImageSteganography = imageSteganography;
                    final OutputStream finalOutputStream = outputStream;
                    runOnUiThread(new Runnable() {
                        public void run() {
                            TextDecoding textDecoding = new TextDecoding(Decode.this, Decode.this);

                            //Execute Task
                            textDecoding.execute(finalImageSteganography);
                            try {
                                finalOutputStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    return true;
                } catch (IOException e) {
                    return false;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (IOException e) {
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            try {

                //progressBar.setMax(values[1].intValue());
                progressBar.setProgress(values[0].intValue());
                progressBar.setMessage("Please wait");

            }catch (Exception e){e.printStackTrace();}
            //textViewProgress.setText(String.format("%d / %d", values[0], values[1]));
        }

        @Override
        protected void onPostExecute(Boolean result) {
           // textViewStatus.setText(result ? "Downloaded" : "Failed");
        }
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "hijkuo.JPEG");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                File file            = getApplicationContext().getFileStreamPath("hijkuo.JPEG");

                String imageFullPath = file.getAbsolutePath();
           //     outputStream = getApplicationContext().openFileOutput(System.currentTimeMillis()+"", Context.MODE_PRIVATE);
              //  b.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
              //  foStream.close();
                //byte[] bitmapdata = outputStream.toByteArray();
                //File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+transaction.getUniqueId()+".pdf");
                //Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);


                ImageSteganography imageSteganography = null;
                try {
                    imageSteganography = new ImageSteganography("0000000000000000",
                            MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse("file://"+getExternalFilesDir(null) + File.separator + "hijkuo.JPEG")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Making the TextDecoding object
                final ImageSteganography finalImageSteganography = imageSteganography;
                final OutputStream finalOutputStream = outputStream;
                runOnUiThread(new Runnable() {
                    public void run() {
                        TextDecoding textDecoding = new TextDecoding(Decode.this, Decode.this);

                        //Execute Task
                        textDecoding.execute(finalImageSteganography);
                        try {
                            finalOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }


    }

}