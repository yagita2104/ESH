package com.yagita.esh.activity.home.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yagita.esh.R;
import com.yagita.esh.json.JsonReader;
import com.yagita.esh.json.JsonVocab;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FillBlankScreen extends AppCompatActivity {
    ImageView btnBackFillBlank;
    TextView txtFillblank, txtCountDown;
    Button btnFillAnswer0, btnFillAnswer1, btnFillAnswer2, btnFillAnswer3, btnNextFill;
    List<JsonVocab> jsonVocabList;
    int index = 0;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_blank_screen);
        getWidget();
        getData();
        addAction();
        setItem(jsonVocabList.get(0));
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
        btnBackFillBlank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnNextFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((index + 1) < jsonVocabList.size()){
                    index++;
                    setItem(jsonVocabList.get(index));
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
                if(jsonVocabList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                }
                else {
                    btnFillAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                }
            }
        });
        btnFillAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnFillAnswer1.getText().toString();
                if(jsonVocabList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                }
                else {
                    btnFillAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                }
            }
        });
        btnFillAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnFillAnswer2.getText().toString();
                if(jsonVocabList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                }
                else {
                    btnFillAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                }
            }
        });
        btnFillAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnFillAnswer3.getText().toString();
                if(jsonVocabList.get(index).getEnglish().equals(answer)){
                    btnFillAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_1)));
                    btnNextFill.setVisibility(View.VISIBLE);
                }
                else {
                    btnFillAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(FillBlankScreen.this, R.color.home_color_button_2)));
                }
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
    public void setItem(JsonVocab jsonVocab){
        txtFillblank.setText(jsonVocab.getFill_Blank());
        ArrayList<Integer> listAnswer = selectNumberRandom();
        List<String> answer = jsonVocab.getMistake();
        answer.add(jsonVocab.getEnglish());
        System.out.println(listAnswer);
        btnFillAnswer0.setText(answer.get(listAnswer.get(0)));
        btnFillAnswer1.setText(answer.get(listAnswer.get(1)));
        btnFillAnswer2.setText(answer.get(listAnswer.get(2)));
        btnFillAnswer3.setText(answer.get(listAnswer.get(3)));
    }
}