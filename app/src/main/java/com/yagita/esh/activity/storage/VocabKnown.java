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

public class VocabKnown extends AppCompatActivity {
    ListView lvVocabKnow;
    List<String> listVocabKnow = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ImageView btnBack;
    List<Vocabulary> vocabularyList;
    VocabDAO vocabDAO = new VocabDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_known);
        getWidget();
        addAction();
        vocabularyList = vocabDAO.getListVocab();
        setData();

    }
    public void getWidget(){
        lvVocabKnow = findViewById(R.id.lvVocabKnown);
        btnBack = findViewById(R.id.btnBackStorageKno);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listVocabKnow);
        lvVocabKnow.setAdapter(adapter);
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
            if(a.getStatus() == 1){
                listVocabKnow.add(a.getEnglish());
            }
        }
        if(listVocabKnow.size() == 0){
            listVocabKnow.add("Không có từ nào");
        }
        adapter.notifyDataSetChanged();
    }
}