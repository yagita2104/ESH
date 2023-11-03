package com.yagita.esh.activity.home.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yagita.esh.R;
import com.yagita.esh.activity.home.VocabScreen;
import com.yagita.esh.json.JsonReader;
import com.yagita.esh.json.JsonVocab;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class QuizScreen extends AppCompatActivity {
    ImageView btnBackQuiz;
    TextView txtQuizText, txtCountDown;
    Button btnQuizAnswer0, btnQuizAnswer1, btnQuizAnswer2, btnQuizAnswer3, btnNextQuiz;
    int random_number = 0;
    List<JsonVocab> jsonVocabList;
    int index = 0;
    private CountDownTimer countDownTimer;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        getWidget();
        getData();
        addAction();
        setItem(jsonVocabList.get(0));

    }

    private void getWidget() {
        btnBackQuiz = findViewById(R.id.btnBackQuiz);
        txtQuizText = findViewById(R.id.txtQuizText);

        btnNextQuiz = findViewById(R.id.btnNextQuiz);
        btnQuizAnswer0 = findViewById(R.id.btnQuizAnswer0);
        btnQuizAnswer1 = findViewById(R.id.btnQuizAnswer1);
        btnQuizAnswer2 = findViewById(R.id.btnQuizAnswer2);
        btnQuizAnswer3 = findViewById(R.id.btnQuizAnswer3);

        txtCountDown = findViewById(R.id.txtCountDownQuiz);
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
    public void setItem(JsonVocab jsonVocab){
        txtQuizText.setText(jsonVocab.getEnglish());
        ArrayList<Integer> listAnswer = selectNumberRandom();
        List<String> answer = jsonVocab.getEnglish_Vocabulary_Test();
        answer.add(jsonVocab.getSub_English());
        System.out.println(listAnswer);
        btnQuizAnswer0.setText(answer.get(listAnswer.get(0)));
        btnQuizAnswer1.setText(answer.get(listAnswer.get(1)));
        btnQuizAnswer2.setText(answer.get(listAnswer.get(2)));
        btnQuizAnswer3.setText(answer.get(listAnswer.get(3)));
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
    private void addAction() {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    // Thiết lập ngôn ngữ cho Text-to-Speech (VD: English)
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        btnBackQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnNextQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((index + 1) < jsonVocabList.size()){
                    index++;
                    setItem(jsonVocabList.get(index));
                    btnQuizAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.bottom_nav)));
                    btnQuizAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.bottom_nav)));
                    btnQuizAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.bottom_nav)));
                    btnQuizAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.bottom_nav)));
                    btnNextQuiz.setVisibility(View.INVISIBLE);
                }else {
                    btnNextQuiz.setText("Bạn đã hoàn thành bài tập hôm nay");
                    startCountdownTimer();
                }
            }
        });
        btnQuizAnswer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnQuizAnswer0.getText().toString();
                if(jsonVocabList.get(index).getSub_English().equals(answer)){
                    btnQuizAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_1)));
                    btnNextQuiz.setVisibility(View.VISIBLE);
                    textToSpeech.speak("Correct", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    btnQuizAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_2)));
                    textToSpeech.speak("Incorrect", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });
        btnQuizAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnQuizAnswer1.getText().toString();
                if(jsonVocabList.get(index).getSub_English().equals(answer)){
                    btnQuizAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_1)));
                    btnNextQuiz.setVisibility(View.VISIBLE);
                    textToSpeech.speak("Correct", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    btnQuizAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_2)));
                    textToSpeech.speak("Incorrect", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });
        btnQuizAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnQuizAnswer2.getText().toString();
                if(jsonVocabList.get(index).getSub_English().equals(answer)){
                    btnQuizAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_1)));
                    btnNextQuiz.setVisibility(View.VISIBLE);
                    textToSpeech.speak("Correct", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    btnQuizAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_2)));
                    textToSpeech.speak("Incorrect", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });
        btnQuizAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnQuizAnswer3.getText().toString();
                if(jsonVocabList.get(index).getSub_English().equals(answer)){
                    btnQuizAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_1)));
                    btnNextQuiz.setVisibility(View.VISIBLE);
                    textToSpeech.speak("Correct", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    btnQuizAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_2)));
                    textToSpeech.speak("Incorrect", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });
        txtQuizText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(txtQuizText.getText(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

    }
}