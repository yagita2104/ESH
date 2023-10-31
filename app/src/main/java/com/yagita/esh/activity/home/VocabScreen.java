package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yagita.esh.R;
import com.yagita.esh.json.JsonReader;
import com.yagita.esh.json.JsonVocab;
import com.yagita.esh.json.ListJsonVocab;
import com.yagita.esh.model.Vocabulary;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VocabScreen extends AppCompatActivity {
    ImageView btnBackStorage, btnListVocab, btnBack, btnUnknow, btnKnow, btnNext, imgIllustration;
//    List<Vocabulary> listVocab = new ArrayList<>();
    TextView txtWord, txtSpelling, txtWordTranslate, txtSentences;

    //Xử lý json
    List<JsonVocab> jsonVocabList;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_screen);
        getData();
        getWidget();
        setItem(jsonVocabList.get(0));
        addAction();
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
    private void getWidget() {
        btnBackStorage = findViewById(R.id.btnBackStorage);
        btnListVocab = findViewById(R.id.btnListVocab);
        btnBack = findViewById(R.id.btnBack);
        btnUnknow = findViewById(R.id.btnUnknown);
        btnKnow = findViewById(R.id.btnKnow);
        btnNext = findViewById(R.id.btnNext);
        txtWord = findViewById(R.id.txtWord);
        txtSpelling = findViewById(R.id.txtSpelling);
        txtWordTranslate = findViewById(R.id.txtWordTranslate);
        txtSentences = findViewById(R.id.txtSentences);
        imgIllustration = findViewById(R.id.imgIllustration);
    }
    private void nextVocab(){
        if((index + 1) < jsonVocabList.size()){
            index++;
            setItem(jsonVocabList.get(index));
        }else{
            Toast.makeText(VocabScreen.this, "Không còn từ vựng", Toast.LENGTH_SHORT).show();
        }
    }
    private void addAction() {
        btnBackStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnListVocab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VocabScreen.this, VocabAllScreen.class));
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextVocab();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index != 0){
                    index--;
                    setItem(jsonVocabList.get(index));
                }else{
                    Toast.makeText(VocabScreen.this, "Không còn từ vựng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VocabScreen.this, "Đã học", Toast.LENGTH_SHORT).show();
                jsonVocabList.get(index).setStatus(1);
                jsonVocabList.remove(index);
                setItem(jsonVocabList.get(index));
            }
        });
        btnUnknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VocabScreen.this, "Chưa học", Toast.LENGTH_SHORT).show();
                nextVocab();
            }
        });
    }
    public void setItem(JsonVocab a){
        if (a.getStatus() == 0){
            txtWord.setText(a.getEnglish());
            txtSpelling.setText(a.getEnglish());
            txtWordTranslate.setText(a.getSub_English());
            txtSentences.setText(a.getExample());
        }
    }
}