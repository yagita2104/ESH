package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yagita.esh.R;
import com.yagita.esh.activity.home.exercise.QuizScreen;

public class ExerciseScreen extends AppCompatActivity {
    ImageView btnBackExercise;
    Button btnQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_screen);
        getWidget();
        addAction();
    }
    private void getWidget() {
        btnBackExercise = findViewById(R.id.btnBackExercise);
        btnQuiz = findViewById(R.id.btnQuiz);
    }
    private void addAction() {
        btnBackExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExerciseScreen.this, QuizScreen.class));
            }
        });
    }


}