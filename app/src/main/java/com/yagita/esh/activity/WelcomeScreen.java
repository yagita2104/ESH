package com.yagita.esh.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.yagita.esh.R;

import java.io.IOException;

public class WelcomeScreen extends AppCompatActivity {
    Button btnStart;
    EditText edtName, edtSpec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);

        if (!isFirstTime) {
            // Giá trị cờ là false, chuyển đến MainActivity và kết thúc WelcomeScreen
            startActivity(new Intent(WelcomeScreen.this, MainActivity.class));
            finish();
        } else {
            // Giá trị cờ là true, hiển thị WelcomeScreen và đặt giá trị cờ thành false
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();

            setContentView(R.layout.activity_welcome_screen);
            getWidget();
            addAction();
        }
    }

    private void getWidget() {
        btnStart = findViewById(R.id.btnStart);
        edtName = findViewById(R.id.edtName);
        edtSpec = findViewById(R.id.edtSpec);
    }

    private void addAction() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
                String name = edtName.getText().toString();
                String spec = edtSpec.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", name);
                editor.putString("spec", spec);
                editor.apply();
//                intent.putExtra("name", edtName.getText().toString());
//                intent.putExtra("spec", edtSpec.getText().toString());
                startActivity(intent);
            }
        });
    }

}