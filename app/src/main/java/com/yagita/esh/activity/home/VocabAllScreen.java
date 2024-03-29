package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yagita.esh.R;
import com.yagita.esh.db.VocabDAO;
import com.yagita.esh.model.Vocabulary;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VocabAllScreen extends AppCompatActivity {
    ListView lvListVocab;
    ImageView btnBackList;
    ArrayAdapter<String> adapter;
    List<Vocabulary> vocabularyAllList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_vocab_screen);
        VocabDAO vocabDAO = new VocabDAO(this);
        lvListVocab = findViewById(R.id.lvListVocab);
        btnBackList = findViewById(R.id.btnBackList);
        vocabularyAllList = vocabDAO.getListVocab();
        List<String> list = new ArrayList<>();
        for (Vocabulary a : vocabularyAllList) {
            list.add(a.getEnglish());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lvListVocab.setAdapter(adapter);
        btnBackList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}