package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yagita.esh.R;
import com.yagita.esh.db.VocabDAO;
import com.yagita.esh.model.Vocabulary;

import java.util.List;
import java.util.Locale;

public class VocabScreen extends AppCompatActivity {
    ImageView btnBackStorage, btnListVocab, btnBack, btnUnknow, btnKnow, btnNext, imgIllustration, imgVocabSpeech;
    TextView txtWord, txtWordTranslate, txtSentences, txtAmountVocabUnknow, txtAmountVocabKnow;
//    TextView txtSpelling;
    TextToSpeech textToSpeech;
    //Xử lý json
    List<Vocabulary> vocabularyAllList;
    List<Vocabulary> vocabListUnknown;
    List<Vocabulary> vocabListKnown;
    int index = 0;
    VocabDAO vocabDAO;
    int know = 0, unknown = 0;
    LinearLayout llNNA;
    Spinner spnNNA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_screen);
        vocabDAO = new VocabDAO(this);
        //vocabularyAllList = vocabDAO.getListVocab();
        if(vocabDAO.getIdNNA().equals("English_English_Language_")){
            vocabListKnown = vocabDAO.getListVocabKnownNNA("Listen");
            vocabListUnknown = vocabDAO.getListVocabUnknownNNA("Listen");
        }else{
            vocabListKnown = vocabDAO.getListVocabKnown();
            vocabListUnknown = vocabDAO.getListVocabUnknown();
        }


        know = vocabListKnown.size();
        unknown = vocabListUnknown.size();

        getWidget();
        setItem(vocabListUnknown.get(0));
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
        txtWordTranslate = findViewById(R.id.txtWordTranslate);
        txtSentences = findViewById(R.id.txtSentences);

        txtAmountVocabUnknow = findViewById(R.id.txtAmountVocabUnknow);
        txtAmountVocabKnow = findViewById(R.id.txtAmountVocabKnow);
        txtAmountVocabKnow.setText(know+"");
        txtAmountVocabUnknow.setText(unknown+"");
//        imgIllustration = findViewById(R.id.imgIllustration);
//        txtSpelling = findViewById(R.id.txtSpelling);
        imgVocabSpeech = findViewById(R.id.imgVocabSpeech);

        llNNA = findViewById(R.id.llNNA);
        spnNNA = findViewById(R.id.spnNNA);
        String[] listNNA = {"Listen", "Speak", "Read", "Writing"};
        ArrayAdapter<String> adapterNNA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listNNA);
        adapterNNA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNNA.setAdapter(adapterNNA);
        if(vocabDAO.getIdNNA().equals("English_English_Language_")){
            llNNA.setVisibility(View.VISIBLE);
            Toast.makeText(this, vocabDAO.getIdNNA(), Toast.LENGTH_SHORT).show();
        }
    }
    private void nextVocab(){
        if((index + 1) < vocabListUnknown.size()){
            index++;
            setItem(vocabListUnknown.get(index));
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
                    setItem(vocabListUnknown.get(index));
                }else{
                    Toast.makeText(VocabScreen.this, "Không còn từ vựng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(VocabScreen.this, "Đã học", Toast.LENGTH_SHORT).show();
                vocabListUnknown.get(index).setStatus(1);
                vocabDAO.setStatus(vocabListUnknown.get(index));
                vocabListUnknown.remove(index);
                setItem(vocabListUnknown.get(index));
                know++;
                unknown--;
                txtAmountVocabKnow.setText(know+"");
                txtAmountVocabUnknow.setText(unknown+"");
            }
        });
        btnUnknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(VocabScreen.this, "Chưa học", Toast.LENGTH_SHORT).show();
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
        if(vocabDAO.getIdNNA().equals("English_English_Language_")){
            spnNNA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String str = spnNNA.getSelectedItem().toString();
                    vocabListKnown = vocabDAO.getListVocabKnownNNA(str);
                    vocabListUnknown = vocabDAO.getListVocabUnknownNNA(str);

                    setItem(vocabListUnknown.get(0));
                    know = vocabListKnown.size();
                    unknown = vocabListUnknown.size();

                    txtAmountVocabKnow.setText(know+"");
                    txtAmountVocabUnknow.setText(unknown+"");
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }

    private void setItem(Vocabulary a){
        if (a.getStatus() == 0){
            txtWord.setText(a.getEnglish());
//            txtSpelling.setText(a.getEnglish());
            txtWordTranslate.setText(a.getSub_English());
            txtSentences.setText(a.getExample());
        }
    }

}