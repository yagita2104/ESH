package com.yagita.esh.activity.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
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
import com.yagita.esh.adapter.MyAdapter;
import com.yagita.esh.db.VocabDAO;
import com.yagita.esh.model.Vocabulary;

import java.util.List;
import java.util.Locale;

public class VocabScreen extends AppCompatActivity {
    ImageView btnBackStorage, btnListVocab, btnUnknow, btnKnow, imgIllustration;
    TextView  txtAmountVocabUnknow, txtAmountVocabKnow;
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
    ViewPager2 viewPager2;
    MyAdapter adapter;
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
        viewPager2.setCurrentItem(0);
        addAction();


    }

    private void getWidget() {
        btnBackStorage = findViewById(R.id.btnBackStorage);
        btnListVocab = findViewById(R.id.btnListVocab);
        btnUnknow = findViewById(R.id.btnUnknown);
        btnKnow = findViewById(R.id.btnKnow);

        txtAmountVocabUnknow = findViewById(R.id.txtAmountVocabUnknow);
        txtAmountVocabKnow = findViewById(R.id.txtAmountVocabKnow);
        txtAmountVocabKnow.setText(know+"");
        txtAmountVocabUnknow.setText(unknown+"");
//        imgIllustration = findViewById(R.id.imgIllustration);
//        txtSpelling = findViewById(R.id.txtSpelling);

        llNNA = findViewById(R.id.llNNA);
        spnNNA = findViewById(R.id.spnNNA);
        String[] listNNA = {"Listen", "Speak", "Read", "Writing"};
        ArrayAdapter<String> adapterNNA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listNNA);
        adapterNNA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNNA.setAdapter(adapterNNA);
        if(vocabDAO.getIdNNA().equals("English_English_Language_")){
            llNNA.setVisibility(View.VISIBLE);
        }

        viewPager2 = findViewById(R.id.viewPager2);
        adapter = new MyAdapter(this  , vocabListUnknown);
        viewPager2.setAdapter(adapter);
        viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float MIN_SCALE = 0.85f;
                float MIN_ALPHA = 0.5f;

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    page.setAlpha(0);
                } else if (position <= 1) { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float verticalMargin = page.getHeight() * (1 - scaleFactor) / 2;
                    float horizontalMargin = page.getWidth() * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        page.setTranslationX(horizontalMargin - verticalMargin / 2);
                    } else {
                        page.setTranslationX(-horizontalMargin + verticalMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    page.setScaleX(scaleFactor);
                    page.setScaleY(scaleFactor);

                    // Fade the page relative to its size.
                    page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    page.setAlpha(0);
                }
            }
        });

    }
    private void getCurrentVocabulary() {
        int currentPosition = viewPager2.getCurrentItem();
        Vocabulary currentVocabulary = ((MyAdapter) viewPager2.getAdapter()).getVocabularyAtPosition(currentPosition);
        // Bây giờ bạn có thể sử dụng currentVocabulary để làm gì đó
    }

    private void nextVocab(){
        int index = viewPager2.getCurrentItem();
        if(index < vocabListUnknown.size()){
            index++;
            viewPager2.setCurrentItem(index, true);

        }else if(index == 0){
            index++;
            viewPager2.setCurrentItem(index, true);
        }
        else{
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
        btnKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = viewPager2.getCurrentItem();
                if(index > 0){
                    vocabListUnknown.get(index).setStatus(1);
                    vocabDAO.setStatus(vocabListUnknown.get(index));
                    vocabListUnknown.remove(index);
                    viewPager2.setAdapter(new MyAdapter(VocabScreen.this, vocabListUnknown));
                    viewPager2.setCurrentItem(index);
                    know++;
                    unknown--;
                    txtAmountVocabKnow.setText(know+"");
                    txtAmountVocabUnknow.setText(unknown+"");
                } else if (index == 0) {
                    vocabListUnknown.get(index).setStatus(1);
                    vocabDAO.setStatus(vocabListUnknown.get(index));
                    vocabListUnknown.remove(index);
                    viewPager2.setAdapter(new MyAdapter(VocabScreen.this, vocabListUnknown));
                    viewPager2.setCurrentItem(index);
                    know++;
                    unknown--;
                    txtAmountVocabKnow.setText(know+"");
                    txtAmountVocabUnknow.setText(unknown+"");
                } else {
                    Toast.makeText(VocabScreen.this, "Đã hết từ vựng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUnknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(VocabScreen.this, "Chưa học", Toast.LENGTH_SHORT).show();
                nextVocab();
                viewPager2.setAdapter(new MyAdapter(VocabScreen.this, vocabListUnknown));
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
//        imgVocabSpeech.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                textToSpeech.speak(txtWord.getText(), TextToSpeech.QUEUE_FLUSH, null, null);
//
//            }
//        });
        if(vocabDAO.getIdNNA().equals("English_English_Language_")){
            spnNNA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String str = spnNNA.getSelectedItem().toString();
                    vocabListKnown = vocabDAO.getListVocabKnownNNA(str);
                    vocabListUnknown = vocabDAO.getListVocabUnknownNNA(str);
                    viewPager2.setAdapter(new MyAdapter(VocabScreen.this, vocabListUnknown));
                    viewPager2.setCurrentItem(0);
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


}