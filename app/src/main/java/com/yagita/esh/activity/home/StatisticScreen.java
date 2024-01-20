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
import com.yagita.esh.db.VocabDAO;
import com.yagita.esh.model.Specialize;
import com.yagita.esh.model.Statistic;
import com.yagita.esh.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class StatisticScreen extends AppCompatActivity {
    ImageView btnBackStatistics;
    RecyclerView rvStatistic;
    StatisticAdapter adapter;
    List<Statistic> list = new ArrayList<>();
    VocabDAO vocabDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_screen);
        vocabDAO = new VocabDAO(this);
        getWidget();
        addData();
        addAction();
    }

    private void addData() {
        List<Vocabulary> vocabularyList = new ArrayList<>();
        vocabularyList = vocabDAO.getListVocab();
        int sumVocab = vocabularyList.size();
        System.out.println(sumVocab);
        String tenBang = vocabDAO.getTableName();
        System.out.println(tenBang);
        System.out.println(vocabDAO.getVocabWithId("English_English_Language_Read").size());
        if (tenBang.equals(Specialize.ENGLISH_LANGUAGE.getTenBang())){
            list.add(new Statistic("Read", vocabDAO.getVocabWithId("English_English_Language_Read").size()+ "/" + sumVocab));
            list.add(new Statistic("Listen", vocabDAO.getVocabWithId("English_English_Language_Listen").size()+ "/" + sumVocab));
            list.add(new Statistic("Speak", vocabDAO.getVocabWithId("English_English_Language_Speak").size()+ "/" + sumVocab));
            list.add(new Statistic("Writing", vocabDAO.getVocabWithId("English_English_Language_Writing").size()+ "/" + sumVocab));
        } else if (tenBang.equals(Specialize.INFORMATION_TECHNOLOGY.getTenBang())) {
            list.add(new Statistic("Unit 1", vocabDAO.getVocabWithId("English_Information_Technology_5.1.").size()+ "/" + sumVocab));
            list.add(new Statistic("Unit 2", vocabDAO.getVocabWithId("English_Information_Technology_5.2.").size()+ "/" + sumVocab));
            list.add(new Statistic("Unit 3", vocabDAO.getVocabWithId("English_Information_Technology_5.3.").size()+ "/" + sumVocab));
            list.add(new Statistic("Unit 4", vocabDAO.getVocabWithId("English_Information_Technology_5.4.").size()+ "/" + sumVocab));
            list.add(new Statistic("Unit 5", vocabDAO.getVocabWithId("English_Information_Technology_5.5.").size()+ "/" + sumVocab));
            list.add(new Statistic("Unit 6", vocabDAO.getVocabWithId("English_Information_Technology_5.6.").size()+ "/" + sumVocab));
            list.add(new Statistic("Unit 7", vocabDAO.getVocabWithId("English_Information_Technology_5.7.").size()+ "/" + sumVocab));
            list.add(new Statistic("Unit 8", vocabDAO.getVocabWithId("English_Information_Technology_5.8.").size()+ "/" + sumVocab));
        }

    }

    private void getWidget() {
        btnBackStatistics = findViewById(R.id.btnBackStatistic);
        rvStatistic = findViewById(R.id.rvStatistic);

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