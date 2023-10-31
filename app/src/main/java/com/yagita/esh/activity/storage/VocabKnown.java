package com.yagita.esh.activity.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yagita.esh.R;
import com.yagita.esh.json.JsonReader;
import com.yagita.esh.json.JsonVocab;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VocabKnown extends AppCompatActivity {
    ListView lvVocabKnow;
    List<String> listVocabKnow = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ImageView btnBack;
    List<JsonVocab> jsonVocabList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_known);
        getWidget();
        addAction();
        getData();
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
    public void getData(){
        JSONArray jsonArray = JsonReader.loadJSONArrayFromRaw(this, R.raw.data);
        String data = "";
        if(jsonArray != null){
            data = jsonArray.toString();
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<JsonVocab>>() {}.getType();
        jsonVocabList = gson.fromJson(data, listType);
    }
    public void setData(){
        for (JsonVocab a : jsonVocabList) {
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