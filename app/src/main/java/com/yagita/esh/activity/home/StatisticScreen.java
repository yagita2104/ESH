package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yagita.esh.R;

public class StatisticScreen extends AppCompatActivity {
    ImageView btnBackStatistics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_screen);
        getWidget();
        addAction();
    }
    private void getWidget() {
        btnBackStatistics = findViewById(R.id.btnBackStatistic);
    }
    private void addAction() {
        btnBackStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}