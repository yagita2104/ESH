package com.yagita.esh.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.yagita.esh.R;
import com.yagita.esh.model.Specialize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WelcomeScreen extends AppCompatActivity {
    Button btnStart;
    EditText edtName;
    Spinner spnSpec;
    ImageView imgSelectProfile;
    String[] khoaList = {"Khoa", "Công nghệ thông tin", "Điện-Điện tử", "Ngoại Ngữ" ,"Cơ Khí"};
    Map<String, String> mapKhoa = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome_screen);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
         boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);

        mapKhoa.put("Khoa", "Tên bảng");
        mapKhoa.put("Công nghệ thông tin", Specialize.INFORMATION_TECHNOLOGY.getTenBang());
        mapKhoa.put("Điện-Điện tử", "Tên bảng");
        mapKhoa.put("Ngoại Ngữ", Specialize.ENGLISH_LANGUAGE.getTenBang());
        mapKhoa.put("Cơ khí", "Tên bảng");

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
        imgSelectProfile = findViewById(R.id.imgSelectProfile);

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
                } else if (spec.equals("Chọn Khoa") || (spec.equals("Khoa"))) {
                    Toast.makeText(WelcomeScreen.this, "Vui lòng chọn khoa", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", name);
                    editor.putString("spec", spec);
                    for (String khoa : khoaList) {
                        if (khoa.equals(spec)) {
                            String tenBang = mapKhoa.get(spec);
                            editor.putString("tableName", tenBang);
                            System.out.println(tenBang);
                        }
                    }
                    editor.apply();
                    startActivity(intent);
                }
            }
        });
        imgSelectProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launcher.launch(i);
    }

    private void saveImageToPreferences(Bitmap bitmap) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        editor.putString("profile_image", imageEncoded);
        editor.apply();
    }

    private Bitmap loadImageFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String imageEncoded = preferences.getString("profile_image", "");
        if (!imageEncoded.isEmpty()) {
            byte[] imageBytes = Base64.decode(imageEncoded, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        }
        return null;
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            if (data != null && data.getData() != null) {
                Uri selectedImageUri = data.getData();
                Bitmap selectedImageBitmap = null;
                try {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    // Hiển thị hình ảnh đã chọn
                    imgSelectProfile.setImageBitmap(selectedImageBitmap);
                    // Lưu hình ảnh vào SharedPreferences
                    saveImageToPreferences(selectedImageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });
}