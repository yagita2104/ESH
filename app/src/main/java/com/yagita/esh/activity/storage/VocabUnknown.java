package com.yagita.esh.activity.storage;

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

public class VocabUnknown extends AppCompatActivity {
    ListView lvVocabUnKnow;
    List<String> listVocabUnKnow = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ImageView btnBack;
    List<Vocabulary> vocabularyList;
    VocabDAO vocabDAO = new VocabDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_unknown);
        getWidget();
        addAction();
        vocabularyList = vocabDAO.getListVocab();
        setData();
    }
    public void getWidget(){
        lvVocabUnKnow = findViewById(R.id.lvVocabUnknown);
        btnBack = findViewById(R.id.btnBackStorageUnk);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listVocabUnKnow);
        lvVocabUnKnow.setAdapter(adapter);
    }
    public void addAction(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    public void setData(){
        for (Vocabulary a : vocabularyList) {
            if(a.getStatus() == 0){
                listVocabUnKnow.add(a.getEnglish());
            }
        }
        adapter.notifyDataSetChanged();
    }
}