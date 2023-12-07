package com.yagita.esh.activity.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yagita.esh.R;
import com.yagita.esh.activity.home.exercise.QuizScreen;
import com.yagita.esh.db.VocabDAO;
import com.yagita.esh.model.TestAnswer;
import com.yagita.esh.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TestScreen extends AppCompatActivity {

    ImageView btnBackTest;
    TextView txtTest, txtCountDown, txtProcess;
    Button btnTestAnswer0, btnTestAnswer1, btnTestAnswer2, btnTestAnswer3, btnNextTest;
    int random_number = 0;
    List<Vocabulary> vocabularyList;
    int index = 0;
    private CountDownTimer countDownTimer;
    private MediaPlayer mediaPlayer;
    TextToSpeech textToSpeech;
    VocabDAO vocabDAO = new VocabDAO(this);
    int index_process = 1;
    List<Vocabulary> listVocabKnown = new ArrayList<>();
    int correct_answer = 0;
    public ArrayList<TestAnswer> listTestAnswer = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_screen);
        getWidget();
        vocabularyList = vocabDAO.getListVocab();
        listVocabKnown = vocabDAO.getListVocabKnown();
        setProcess();
        addAction();
        setItem(vocabularyList.get(0));
    }

    private void getWidget() {
        btnBackTest = findViewById(R.id.btnBackTest);
        txtTest = findViewById(R.id.txtTest);
        txtProcess = findViewById(R.id.txtProcess);

        btnNextTest = findViewById(R.id.btnNextTest);
        btnTestAnswer0 = findViewById(R.id.btnTestAnswer0);
        btnTestAnswer1 = findViewById(R.id.btnTestAnswer1);
        btnTestAnswer2 = findViewById(R.id.btnTestAnswer2);
        btnTestAnswer3 = findViewById(R.id.btnTestAnswer3);

        txtCountDown = findViewById(R.id.txtCountDownTest);
    }
    private void setProcess(){
        String process = index_process + "/" + listVocabKnown.size();
        txtProcess.setText(process);
        index_process++;
    }
    public void setItem(Vocabulary vocabulary){
        txtTest.setText(vocabulary.getEnglish());
        ArrayList<Integer> listAnswer = selectNumberRandom();
        List<String> answer = vocabulary.getEnglish_Vocabulary_Test();
        answer.add(vocabulary.getSub_English());
        System.out.println(listAnswer);
        btnTestAnswer0.setText(answer.get(listAnswer.get(0)));
        btnTestAnswer1.setText(answer.get(listAnswer.get(1)));
        btnTestAnswer2.setText(answer.get(listAnswer.get(2)));
        btnTestAnswer3.setText(answer.get(listAnswer.get(3)));
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
        btnBackTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnNextTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextTest();
            }
        });
        btnTestAnswer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                if(index == vocabularyList.size() - 1){
                    endTest();
                }
                String answer = btnTestAnswer0.getText().toString();
                if(vocabularyList.get(index).getSub_English().equals(answer)){
                    listTestAnswer.add(new TestAnswer(index, answer, vocabularyList.get(index).getSub_English()));
                    btnTestAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.home_color_button_1)));
                    btnNextTest.setVisibility(View.VISIBLE);
                    playSound(true);
                    correct_answer++;
                }
                else {
                    listTestAnswer.add(new TestAnswer(index, answer, vocabularyList.get(index).getSub_English()));
                    btnTestAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.home_color_button_2)));
                    playSound(false);
                    nextTest();
                }
            }
        });
        btnTestAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                if(index == vocabularyList.size() - 1){
                    endTest();
                }
                String answer = btnTestAnswer1.getText().toString();
                if(vocabularyList.get(index).getSub_English().equals(answer)){
                    listTestAnswer.add(new TestAnswer(index, answer, vocabularyList.get(index).getSub_English()));
                    btnTestAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.home_color_button_1)));
                    btnNextTest.setVisibility(View.VISIBLE);
                    playSound(true);
                    correct_answer++;
                }
                else {
                    listTestAnswer.add(new TestAnswer(index, answer, vocabularyList.get(index).getSub_English()));
                    btnTestAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.home_color_button_2)));
                    playSound(false);
                    nextTest();
                }
            }
        });
        btnTestAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                if(index == vocabularyList.size() - 1){
                    endTest();
                }
                String answer = btnTestAnswer2.getText().toString();
                if(vocabularyList.get(index).getSub_English().equals(answer)){
                    listTestAnswer.add(new TestAnswer(index, answer, vocabularyList.get(index).getSub_English()));
                    btnTestAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.home_color_button_1)));
                    btnNextTest.setVisibility(View.VISIBLE);
                    playSound(true);
                    correct_answer++;
                }
                else {
                    listTestAnswer.add(new TestAnswer(index, answer, vocabularyList.get(index).getSub_English()));
                    btnTestAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.home_color_button_2)));
                    playSound(false);
                    nextTest();
                }
            }
        });
        btnTestAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                if(index == vocabularyList.size() - 1){
                    endTest();
                }
                String answer = btnTestAnswer3.getText().toString();
                if(vocabularyList.get(index).getSub_English().equals(answer)){
                    listTestAnswer.add(new TestAnswer(index, answer, vocabularyList.get(index).getSub_English()));
                    btnTestAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.home_color_button_1)));
                    btnNextTest.setVisibility(View.VISIBLE);
                    playSound(true);
                    correct_answer++;
                }
                else {
                    listTestAnswer.add(new TestAnswer(index, answer, vocabularyList.get(index).getSub_English()));
                    btnTestAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.home_color_button_2)));
                    playSound(false);
                    nextTest();
                }
            }
        });
        txtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(txtTest.getText(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

    }
    private void showCustomDialog() {
        Dialog customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.test_dialog_custom);

        TextView txtTestFinish = customDialog.findViewById(R.id.txtTestFinish);
        Button btnTestCheck = customDialog.findViewById(R.id.btnTestCheck);
        Button btnTestEnd = customDialog.findViewById(R.id.btnTestEnd);

        String content = "Bạn đã hoàn thành đúng " + correct_answer + "/" + listVocabKnown.size() +" câu.";
        txtTestFinish.setText(content);
        btnTestCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestScreen.this, CheckScreen.class);
                intent.putExtra("dataAnswer", listTestAnswer);
                startActivity(intent);
            }
        });
        btnTestEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
                onBackPressed();
            }
        });
        customDialog.show();
    }
    private void endTest(){
        btnNextTest.setText("End");
        btnNextTest.setVisibility(View.VISIBLE);
        index_process = 0;
        btnNextTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });
    }
    private void nextTest(){
        if((index + 1) < vocabularyList.size()){
            index++;
            setItem(vocabularyList.get(index));
            btnTestAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.bottom_nav)));
            btnTestAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.bottom_nav)));
            btnTestAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.bottom_nav)));
            btnTestAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TestScreen.this, R.color.bottom_nav)));
            btnNextTest.setVisibility(View.INVISIBLE);
            setProcess();
        }else {
            endTest();
        }
    }
//    private void addTestAnswer(Vocabulary vocab, Button btn){
//        listTestAnswer.add(new TestAnswer(index, btn.getText().toString(), vocab.getSub_English()));
//    }
}