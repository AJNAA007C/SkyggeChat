package com.android.rfchat.Steganography;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.rfchat.R;

public class MainStegnoActivity extends AppCompatActivity {

    Button encode, decode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stmain);

        encode = (Button) findViewById(R.id.encode_button);
        decode = (Button) findViewById(R.id.decode_button);

        encode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Encode.class));
            }
        });

        decode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Decode.class));
            }
        });

    }

}