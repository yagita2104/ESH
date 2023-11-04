package com.yagita.esh.activity.home.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yagita.esh.R;
import com.yagita.esh.activity.home.VocabScreen;
import com.yagita.esh.db.VocabDAO;
import com.yagita.esh.model.Vocabulary;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class FillBlankScreen extends AppCompatActivity {
    ImageView btnBackFillBlank;
    TextView txtFillblank, txtCountDown;
    Button btnFillAnswer0, btnFillAnswer1, btnFillAnswer2, btnFillAnswer3, btnNextFill;
    List<Vocabulary> vocabularyList;
    int index = 0;
    private CountDownTimer countDownTimer;
    TextToSpeech textToSpeech;
    VocabDAO vocabDAO = new VocabDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_blank_screen);
        getWidget();
        vocabularyList = vocabDAO.getListVocab();
        addAction();
        setItem(vocabularyList.get(0));
    }
    private void getWidget(){
        btnBackFillBlank = findViewById(R.id.btnBackFillblank);
        txtFillblank = findViewById(R.id.txtFillblank);
        btnFillAnswer0 = findViewById(R.id.btnFillAnswer0);
        btnFillAnswer1 = findViewById(R.id.btnFillAnswer1);
        btnFillAnswer2 = findViewById(R.id.btnFillAnswer2);
        btnFillAnswer3 = findViewById(R.id.btnFillAnswer3);

        btnNextFill = findViewById(R.id.btnNextFill);
        txtCountDown = findViewById(R.id.txtCountDownFillBlank);
    }
    private void addAction(){
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    // Thiết lập ngôn ngữ cho Text-to-Speech (VD: English)
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        btnBackFillBlank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnNextFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((index + 1) < vocabularyList.size()){
                    index++;
                    setItem(vocabularyList.get(index));
                    btnFillAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.bottom_nav)));
                    btnFillAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.bottom_nav)));
                    btnFillAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.bottom_nav)));
                    btnFillAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.bottom_nav)));
                    btnNextFill.setVisibility(View.INVISIBLE);
                }else {
                    btnNextFill.setText("Bạn đã hoàn thành bài tập hôm nay");
                    startCountdownTimer();
                }
            }
        });
        btnFillAnswer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnFillAnswer0.getText().toString();
                if(vocabularyList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                    textToSpeech.speak("Correct", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    btnFillAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                    textToSpeech.speak("Incorrect", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });
        btnFillAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnFillAnswer1.getText().toString();
                if(vocabularyList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                    textToSpeech.speak("Correct", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    btnFillAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                    textToSpeech.speak("Incorrect", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });
        btnFillAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnFillAnswer2.getText().toString();
                if(vocabularyList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                    textToSpeech.speak("Correct", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    btnFillAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                    textToSpeech.speak("Incorrect", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });
        btnFillAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnFillAnswer3.getText().toString();
                if(vocabularyList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                    textToSpeech.speak("Correct", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    btnFillAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                    textToSpeech.speak("Incorrect", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });

    }
    private void startCountdownTimer() {
        countDownTimer = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                txtCountDown.setText("Đang quay lại trong " + seconds + " giây...");
            }

            public void onFinish() {
                onBackPressed();
            }
        }.start();
    }
    public ArrayList<Integer> selectNumberRandom(){
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> choices = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            choices.add(i);
        }
        Random random = new Random();
        for (int i = 0; i <= 3; i++){
            // Chọn số ngẫu nhiên từ 0 đến 3
            int selectedIndex = random.nextInt(choices.size());
            int selectedNumber = choices.get(selectedIndex);
            result.add(selectedNumber);
            // Loại trừ số đã chọn khỏi danh sách
            choices.remove(selectedIndex);
        }
        return result;
    }
    public void setItem(Vocabulary vocabulary){
        txtFillblank.setText(vocabulary.getFill_Blank());
        ArrayList<Integer> listAnswer = selectNumberRandom();
        List<String> answer = vocabulary.getMistake();
        answer.add(vocabulary.getEnglish());
        System.out.println(listAnswer);
        btnFillAnswer0.setText(answer.get(listAnswer.get(0)));
        btnFillAnswer1.setText(answer.get(listAnswer.get(1)));
        btnFillAnswer2.setText(answer.get(listAnswer.get(2)));
        btnFillAnswer3.setText(answer.get(listAnswer.get(3)));
    }

}