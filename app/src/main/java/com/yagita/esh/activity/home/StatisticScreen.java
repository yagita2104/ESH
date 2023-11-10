package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yagita.esh.R;
import com.yagita.esh.adapter.StatisticAdapter;
import com.yagita.esh.model.Statistic;

import java.util.ArrayList;
import java.util.List;

public class StatisticScreen extends AppCompatActivity {
    ImageView btnBackStatistics;
    RecyclerView rvStatistic;
    StatisticAdapter adapter;
    List<Statistic> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_screen);
        getWidget();
        addAction();
    }
    private void getWidget() {
        btnBackStatistics = findViewById(R.id.btnBackStatistic);
        rvStatistic = findViewById(R.id.rvStatistic);
        // Thêm dữ liệu test
        list.add(new Statistic("Unit 1", "20/50"));
        list.add(new Statistic("Unit 2", "12/50"));
        list.add(new Statistic("Unit 3", "32/50"));
        list.add(new Statistic("Unit 4", "21/50"));
        list.add(new Statistic("Unit 5", "6/50"));
        list.add(new Statistic("Unit 6", "2/50"));
        list.add(new Statistic("Unit 7", "1/50"));
        list.add(new Statistic("Unit 8", "10/50"));

        // Hiển thị
        adapter = new StatisticAdapter(list, this);
        rvStatistic.setLayoutManager(new LinearLayoutManager(this));
        rvStatistic.setAdapter(adapter);
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