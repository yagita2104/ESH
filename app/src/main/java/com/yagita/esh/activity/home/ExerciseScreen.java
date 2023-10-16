package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yagita.esh.R;

public class ExerciseScreen extends AppCompatActivity {
    ImageView btnBackExercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_screen);
        getWidget();
        addAction();
    }
    private void getWidget() {
        btnBackExercise = findViewById(R.id.btnBackExercise);

    }
    private void addAction() {
        btnBackExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


}