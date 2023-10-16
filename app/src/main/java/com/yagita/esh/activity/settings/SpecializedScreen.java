package com.yagita.esh.activity.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.ImageFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yagita.esh.R;
import com.yagita.esh.adapter.SpecializedAdapter;

import java.util.ArrayList;
import java.util.List;

public class SpecializedScreen extends AppCompatActivity {
    ImageView btnBack;
    RecyclerView rvSpec;
    List<String> list = new ArrayList<>();
    SpecializedAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialized_screen);
        getWidget();
        addAction();
        fakeData();
    }

    private void getWidget() {
        btnBack = findViewById(R.id.btnBackSpec);
        rvSpec = findViewById(R.id.rvSpecialized);

        adapter = new SpecializedAdapter(list, this);
        rvSpec.setLayoutManager(new LinearLayoutManager(this));
        rvSpec.setAdapter(adapter);
    }

    private void addAction() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void fakeData() {
        list.add("Tiếng anh công nghệ thông tin");
        list.add("Tiếng anh Thương mại");
        list.add("Tiếng anh Điện - Điện tử");
        list.add("Tiếng anh Công nghệ ô tô");
        list.add("Tiếng anh công nghệ kỹ thuật");
        list.add("Tiếng anh Cơ khí");
        adapter.notifyDataSetChanged();
    }

}