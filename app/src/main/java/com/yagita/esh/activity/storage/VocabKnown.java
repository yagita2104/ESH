package com.yagita.esh.activity.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.yagita.esh.R;

import java.util.ArrayList;
import java.util.List;

public class VocabKnown extends AppCompatActivity {
    ListView lvVocabKnow;
    List<String> listVocabKnow = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_known);
        getWidget();
        addAction();
        fakeData();

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
    public void fakeData(){
        listVocabKnow.add("Blockchain");
        listVocabKnow.add("Blockchain");
        listVocabKnow.add("Blockchain");
        listVocabKnow.add("Blockchain");
        listVocabKnow.add("Blockchain");
        adapter.notifyDataSetChanged();
    }
}