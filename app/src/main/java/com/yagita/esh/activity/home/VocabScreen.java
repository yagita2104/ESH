package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yagita.esh.R;
import com.yagita.esh.db.VocabDAO;
import com.yagita.esh.model.Vocabulary;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VocabScreen extends AppCompatActivity {
    ImageView btnBackStorage, btnListVocab, btnBack, btnUnknow, btnKnow, btnNext, imgIllustration, imgVocabSpeech;
    TextView txtWord, txtSpelling, txtWordTranslate, txtSentences;
    TextToSpeech textToSpeech;
    //Xử lý json
    List<Vocabulary> vocabularyAllList;
    List<Vocabulary> vocabList = new ArrayList<>();
    int index = 0;
    VocabDAO vocabDAO = new VocabDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_screen);
        vocabularyAllList = vocabDAO.getListVocab();
        vocabList = vocabDAO.getListVocabUnknown();
        getWidget();
        setItem(vocabList.get(0));
        addAction();
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

        imgVocabSpeech = findViewById(R.id.imgVocabSpeech);
    }
    private void nextVocab(){
        if((index + 1) < vocabList.size()){
            index++;
            setItem(vocabList.get(index));
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
                    setItem(vocabList.get(index));
                }else{
                    Toast.makeText(VocabScreen.this, "Không còn từ vựng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VocabScreen.this, "Đã học", Toast.LENGTH_SHORT).show();
                vocabList.get(index).setStatus(1);
                vocabDAO.setStatus(vocabList.get(index));

                vocabList.remove(index);
                setItem(vocabList.get(index));
            }
        });
        btnUnknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VocabScreen.this, "Chưa học", Toast.LENGTH_SHORT).show();
                nextVocab();
            }
        });
        // Text to speech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    // Thiết lập ngôn ngữ cho Text-to-Speech (VD: English)
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        imgVocabSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(txtWord.getText(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }
    private void setItem(Vocabulary a){
        if (a.getStatus() == 0){
            txtWord.setText(a.getEnglish());
            txtSpelling.setText(a.getEnglish());
            txtWordTranslate.setText(a.getSub_English());
            txtSentences.setText(a.getExample());
        }
    }
}