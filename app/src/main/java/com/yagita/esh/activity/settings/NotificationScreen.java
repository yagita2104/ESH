package com.yagita.esh.activity.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yagita.esh.R;
import com.yagita.esh.adapter.NotificationAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotificationScreen extends AppCompatActivity {
    RecyclerView rvNotification;
    NotificationAdapter adapter;
    List<String> listNotification = new ArrayList<>();
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_screen);
        getWidget();
        addAction();
        fakeData();
    }

    private void getWidget() {
        rvNotification = findViewById(R.id.rvNotification);
        btnBack = findViewById(R.id.btnBackNotification);
        adapter = new NotificationAdapter(listNotification, this);
        rvNotification.setLayoutManager(new LinearLayoutManager(this));
        rvNotification.setAdapter(adapter);
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
        listNotification.add("Hôm nay bạn đã làm tốt hơn hôm qua 0.1% rồi đó");
        listNotification.add("Thông báo cập nhật phiên bản");
        listNotification.add("Trong tuần vừa qua, câu trả lời đúng: 70%, sai: 30%,.....");
        listNotification.add("Bạn đã lưu lại một ghi chú");
        listNotification.add("Đã tải dữ liệu thành công");

        adapter.notifyDataSetChanged();
    }
}