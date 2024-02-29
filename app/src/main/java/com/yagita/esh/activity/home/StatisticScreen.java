package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    TextView txtNameStatistic;
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
        String tenBang = vocabDAO.getTableName();
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String term = preferences.getString("term", "");

        if (tenBang.equals(Specialize.ENGLISH_LANGUAGE.getTenBang())){
            txtNameStatistic.setText("Tiếng anh chuyên ngành Ngôn ngữ anh " + term);
            List<Vocabulary> l = new ArrayList<>();
            l = vocabDAO.getVocabWithId("English_English_Language_%_" + term);
            int s = l.size();
            String id = "English_English_Language_";
            list.add(new Statistic("Read", vocabDAO.getVocabWithId(id + "Read_" + term).size()+ "/" + s));
            list.add(new Statistic("Listen", vocabDAO.getVocabWithId(id + "Listen_" + term).size()+ "/" + s));
            list.add(new Statistic("Speak", vocabDAO.getVocabWithId(id + "Speak_" + term).size()+ "/" + s));
            list.add(new Statistic("Writing", vocabDAO.getVocabWithId(id + "Writing_" + term).size()+ "/" + s));
        } else if (tenBang.equals(Specialize.INFORMATION_TECHNOLOGY.getTenBang())) {
            txtNameStatistic.setText("Tiếng anh Công nghệ thông tin " + term);
            String id = vocabDAO.getIdStatistic();
            List<Vocabulary> vocabList = new ArrayList<>();
            vocabList = vocabDAO.getVocabWithId(id);
            int sum = vocabList.size();
            list.add(new Statistic("Unit 1", vocabDAO.getVocabWithId(id + "1.").size()+ "/" + sum));
            list.add(new Statistic("Unit 2", vocabDAO.getVocabWithId(id + "2.").size()+ "/" + sum));
            list.add(new Statistic("Unit 3", vocabDAO.getVocabWithId(id + "3.").size()+ "/" + sum));
            list.add(new Statistic("Unit 4", vocabDAO.getVocabWithId(id + "4.").size()+ "/" + sum));
            list.add(new Statistic("Unit 5", vocabDAO.getVocabWithId(id + "5.").size()+ "/" + sum));
            list.add(new Statistic("Unit 6", vocabDAO.getVocabWithId(id + "6.").size()+ "/" + sum));
            list.add(new Statistic("Unit 7", vocabDAO.getVocabWithId(id + "7.").size()+ "/" + sum));
            list.add(new Statistic("Unit 8", vocabDAO.getVocabWithId(id + "8.").size()+ "/" + sum));
        }

    }

    private void getWidget() {
        btnBackStatistics = findViewById(R.id.btnBackStatistic);
        rvStatistic = findViewById(R.id.rvStatistic);
        txtNameStatistic = findViewById(R.id.txtNameStatistic);
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