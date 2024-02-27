package com.yagita.esh.activity.home.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.media.MediaPlayer;
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
    TextView txtFillblank, txtCountDown, txtFillInfor;
    Button btnFillAnswer0, btnFillAnswer1, btnFillAnswer2, btnFillAnswer3, btnNextFill;
    List<Vocabulary> vocabularyList;
    int index = 0;
    private CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;
    TextToSpeech textToSpeech;
    VocabDAO vocabDAO;
    int count = 1;
    int sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_blank_screen);
        vocabDAO = new VocabDAO(this);
        getWidget();
        vocabularyList = vocabDAO.getListVocab();
        sum = vocabularyList.size();
        addAction();
        setItem(vocabularyList.get(0));
        String text = count + "/"+ sum;
        txtFillInfor.setText(text);
    }
    private void getWidget(){
        btnBackFillBlank = findViewById(R.id.btnBackFillblank);
        txtFillblank = findViewById(R.id.txtFillblank);
        btnFillAnswer0 = findViewById(R.id.btnFillAnswer0);
        btnFillAnswer1 = findViewById(R.id.btnFillAnswer1);
        btnFillAnswer2 = findViewById(R.id.btnFillAnswer2);
        btnFillAnswer3 = findViewById(R.id.btnFillAnswer3);
        txtFillInfor = findViewById(R.id.txtFillInfor);

        btnNextFill = findViewById(R.id.btnNextFill);
        txtCountDown = findViewById(R.id.txtCountDownFillBlank);
    }
    private void playSound(boolean bool) {
        // Tạo một đối tượng MediaPlayer và phát âm thanh từ tệp raw/correct_answer.mp3

        if(bool){
            mediaPlayer = MediaPlayer.create(this, R.raw.correct_sound);
        }else {
            mediaPlayer = MediaPlayer.create(this, R.raw.incorrect_sound);
        }


        // Bắt đầu phát âm thanh
        mediaPlayer.start();

        // Đặt sự kiện để giải phóng MediaPlayer khi phát xong âm thanh
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        });
    }

    private void releaseMediaPlayer() {
        // Giải phóng tài nguyên của MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
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
                    count++;
                    String text = count + "/" + sum;
                    txtFillInfor.setText(text);
                }else {
                    btnNextFill.setText("Bạn đã hoàn thành bài tập hôm nay");
                    startCountdownTimer();
                }
            }
        });
        btnFillAnswer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                String answer = btnFillAnswer0.getText().toString();
                if(vocabularyList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                    playSound(true);
                }
                else {
                    btnFillAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                    playSound(false);
                }
            }
        });
        btnFillAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                String answer = btnFillAnswer1.getText().toString();
                if(vocabularyList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                    playSound(true);
                }
                else {
                    btnFillAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                    playSound(false);
                }
            }
        });
        btnFillAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                String answer = btnFillAnswer2.getText().toString();
                if(vocabularyList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                    playSound(true);
                }
                else {
                    btnFillAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                    playSound(false);
                }
            }
        });
        btnFillAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                String answer = btnFillAnswer3.getText().toString();
                if(vocabularyList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                    playSound(true);
                }
                else {
                    btnFillAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                    playSound(false);
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
    private ArrayList<Integer> selectNumberRandom(){
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
    private void setItem(Vocabulary vocabulary){
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