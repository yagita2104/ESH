package com.yagita.esh.activity.home.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
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

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizScreen extends AppCompatActivity {
    ImageView btnBackQuiz;
    TextView txtQuizText;
    Button btnQuizAnswer0, btnQuizAnswer1, btnQuizAnswer2, btnQuizAnswer3, btnNextQuiz;
    int random_number = 0;
    List<JsonVocab> jsonVocabList;
    int index = 0;
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
        txtQuizText.setText(jsonVocab.getE());
        ArrayList<Integer> listAnswer = selectNumberRandom();
        List<String> answer = jsonVocab.getTA();
        answer.add(jsonVocab.getSE());
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
    private void addAction() {
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

                }else{
                    Toast.makeText(QuizScreen.this, "Không còn từ vựng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnQuizAnswer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnQuizAnswer0.getText().toString();
                if(jsonVocabList.get(index).getSE().equals(answer)){
                    Toast.makeText(QuizScreen.this, "True", Toast.LENGTH_SHORT).show();
                    btnQuizAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_1)));
                    btnNextQuiz.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(QuizScreen.this, "false", Toast.LENGTH_SHORT).show();
                    btnQuizAnswer0.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_2)));
                }
            }
        });
        btnQuizAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnQuizAnswer1.getText().toString();
                if(jsonVocabList.get(index).getSE().equals(answer)){
                    Toast.makeText(QuizScreen.this, "True", Toast.LENGTH_SHORT).show();
                    btnQuizAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_1)));
                    btnNextQuiz.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(QuizScreen.this, "false", Toast.LENGTH_SHORT).show();
                    btnQuizAnswer1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_2)));
                }
            }
        });
        btnQuizAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnQuizAnswer2.getText().toString();
                if(jsonVocabList.get(index).getSE().equals(answer)){
                    Toast.makeText(QuizScreen.this, "True", Toast.LENGTH_SHORT).show();
                    btnQuizAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_1)));
                    btnNextQuiz.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(QuizScreen.this, "false", Toast.LENGTH_SHORT).show();
                    btnQuizAnswer2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_2)));
                }
            }
        });
        btnQuizAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = btnQuizAnswer3.getText().toString();
                if(jsonVocabList.get(index).getSE().equals(answer)){
                    Toast.makeText(QuizScreen.this, "True", Toast.LENGTH_SHORT).show();
                    btnQuizAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_1)));
                    btnNextQuiz.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(QuizScreen.this, "false", Toast.LENGTH_SHORT).show();
                    btnQuizAnswer3.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(QuizScreen.this, R.color.home_color_button_2)));
                }
            }
        });
    }
}