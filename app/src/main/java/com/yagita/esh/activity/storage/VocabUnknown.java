package com.yagita.esh.activity.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.yagita.esh.R;

import java.util.ArrayList;
import java.util.List;

public class VocabUnknown extends AppCompatActivity {
    ListView lvVocabUnKnow;
    List<String> listVocabUnKnow = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_unknown);
        getWidget();
        addAction();
        fakeData();
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
    public void fakeData(){
        listVocabUnKnow.add("1");
        listVocabUnKnow.add("2");
        listVocabUnKnow.add("3");
        listVocabUnKnow.add("4");
        listVocabUnKnow.add("5");
        adapter.notifyDataSetChanged();
    }
}