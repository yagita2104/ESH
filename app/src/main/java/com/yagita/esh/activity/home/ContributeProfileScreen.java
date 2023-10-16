package com.yagita.esh.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yagita.esh.R;

public class ContributeProfileScreen extends AppCompatActivity {
    Button btnContinueVocab;
    ImageView btnBackContribute, imgRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute_profile_screen);
        getWidget();
        addAction();

    }

    private void getWidget() {
        btnContinueVocab = findViewById(R.id.btnContinueVocab);
        btnBackContribute = findViewById(R.id.btnBackContribute);
        imgRank = findViewById(R.id.imgRank);
    }

    private void addAction() {
        btnContinueVocab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContributeProfileScreen.this, ContributeVocabScreen.class));
            }
        });
        btnBackContribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imgRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContributeProfileScreen.this, ContributeRankScreen.class));
            }
        });

    }
}