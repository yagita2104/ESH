package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.yagita.esh.R;

import java.util.ArrayList;
import java.util.List;

public class VocabAllScreen extends AppCompatActivity {
    ListView lvListVocab;
    ImageView btnBackList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_vocab_screen);
        lvListVocab = findViewById(R.id.lvListVocab);
        btnBackList = findViewById(R.id.btnBackList);
        List<String> list = new ArrayList<>();
        list.add("Blockchain");
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.add("test4");
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