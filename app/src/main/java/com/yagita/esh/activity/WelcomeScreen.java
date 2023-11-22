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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.yagita.esh.R;
import com.yagita.esh.fragment.HomeFragment;

import java.io.IOException;

public class WelcomeScreen extends AppCompatActivity {
    Button btnStart;
    EditText edtName;
    Spinner spnSpec;
    String[] khoaList = {"Khoa", "Công nghệ thông tin", "Điện-Điện tử", "Ngoại Ngữ" ,"Cơ Khí"};

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
        spnSpec = findViewById(R.id.spnSpec);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, khoaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSpec.setAdapter(adapter);
    }

    private void addAction() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
                String name = edtName.getText().toString();
                String spec = spnSpec.getSelectedItem().toString();

                if (name.isEmpty()) {
                    Toast.makeText(WelcomeScreen.this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
                } else if (spec.equals("Chọn Khoa") || (spec == "Khoa")) {
                    Toast.makeText(WelcomeScreen.this, "Vui lòng chọn khoa", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", name);
                    editor.putString("spec", spec);
                    editor.apply();
                    startActivity(intent);
                }
            }
        });
    }
}