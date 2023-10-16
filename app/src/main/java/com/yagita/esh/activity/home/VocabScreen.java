package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yagita.esh.R;
import com.yagita.esh.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class VocabScreen extends AppCompatActivity {
    ImageView btnBackStorage, btnListVocab, btnBack, btnUnknow, btnKnow, btnNext, imgIllustration;
    List<Vocabulary> listVocab = new ArrayList<>();
    TextView txtWord, txtSpelling, txtWordTranslate, txtSentences;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_screen);
        getWidget();
        addAction();
        listVocab.add(new Vocabulary("Blockchain", "/ˈblɑːk.tʃeɪn /", R.drawable.vocab_test, "Chuỗi khối", "Blockchain is likely to become the key technology behind many central bank digital currencies."));
        listVocab.add(new Vocabulary("Cloud computing", "/klaʊd kəmpjuːtɪŋ/", R.drawable.vocab_test2, "Điện toán đám mây", "Cloud computing is becomeing the most popular and key service that is offered by many IT service providers."));
        listVocab.add(new Vocabulary("Test2", "/ˈblɑːk.tʃeɪn /", R.drawable.vocab_test, "Test2", "Blockchain is likely to become the key technology behind many central bank digital currencies."));
        listVocab.add(new Vocabulary("Test3", "/ˈblɑːk.tʃeɪn /", R.drawable.vocab_test2, "Test3", "Blockchain is likely to become the key technology behind many central bank digital currencies."));
        listVocab.add(new Vocabulary("Test4", "/ˈblɑːk.tʃeɪn /", R.drawable.vocab_test, "Test4", "Blockchain is likely to become the key technology behind many central bank digital currencies."));
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
                if((index + 1) < listVocab.size()){
                    index++;
                    setItem(listVocab.get(index));
                }else{
                    Toast.makeText(VocabScreen.this, "Không còn từ vựng", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index != 0){
                    index--;
                    setItem(listVocab.get(index));
                }else{
                    Toast.makeText(VocabScreen.this, "Không còn từ vựng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VocabScreen.this, "Đã học", Toast.LENGTH_SHORT).show();
            }
        });
        btnUnknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VocabScreen.this, "Chưa học", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setItem(Vocabulary a){
        txtWord.setText(a.getWord());
        txtSpelling.setText(a.getSpelling());
        txtWordTranslate.setText(a.getWordTranslate());
        txtSentences.setText(a.getSentences());
        imgIllustration.setImageResource(a.getIllustration());
    }
}