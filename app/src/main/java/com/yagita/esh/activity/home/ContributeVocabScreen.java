package com.yagita.esh.activity.home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yagita.esh.R;
import com.yagita.esh.activity.MainActivity;
import com.yagita.esh.fragment.HomeFragment;

public class ContributeVocabScreen extends AppCompatActivity {
    ImageView btnBackContributeProfile, imgRank;
    Button btnConfirmContribute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute_vocab_screen);
        getWidget();
        addAction();

    }

    private void getWidget() {
        btnBackContributeProfile = findViewById(R.id.btnBackContributeProfile);
        btnConfirmContribute = findViewById(R.id.btnConfirmContribute);
        imgRank = findViewById(R.id.imgRank);
    }

    private void addAction() {
        btnBackContributeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imgRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContributeVocabScreen.this, ContributeRankScreen.class));
            }
        });
        btnConfirmContribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContributeVocabScreen.this);
//                builder.setTitle("Lời cảm ơn");
                builder.setMessage("Cảm ơn đóng góp của bạn. Thông tin đang được xét duyệt, vui lòng đợi");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        startActivity(new Intent(ContributeVocabScreen.this, MainActivity.class));
                    }
                });
                builder.setNegativeButton("Đóng góp khác", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });

    }

}