package com.yagita.esh.activity.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.yagita.esh.R;
import com.yagita.esh.model.TestAnswer;

import java.util.ArrayList;

public class CheckScreen extends AppCompatActivity {
    TableLayout tableLayout;
    Button btnBackCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_screen);
        getWidget();
        addAction();
    }
    private void getWidget(){
        tableLayout = findViewById(R.id.tableLayout);
        btnBackCheck = findViewById(R.id.btnBackCheck);
        Intent intent = getIntent();
        if (intent != null) {
            ArrayList<TestAnswer> receivedList = (ArrayList<TestAnswer>) intent.getSerializableExtra("dataAnswer");
            if (receivedList != null) {
                // Xử lý danh sách đã nhận được
                for (int i = 0; i < receivedList.size(); i++) {
                    TestAnswer testAnswer = receivedList.get(i);
                    addRow(tableLayout, "Câu " + (testAnswer.getStt() + 1), testAnswer.getUserAnswer(), testAnswer.getAnswer());
                }
            }
        }
    }
    private void addAction(){
        btnBackCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void addRow(TableLayout tableLayout, String col1Text, String col2Text, String col3Text) {
        TableRow tableRow = new TableRow(this);

        // Cột 1
        TextView col1TextView = new TextView(this);
        col1TextView.setText(col1Text);
        col1TextView.setTextColor(Color.BLACK);
        col1TextView.setPadding(8, 8, 8, 8);
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        col1TextView.setLayoutParams(params1);
        tableRow.addView(col1TextView);

        // Cột 2
        TextView col2TextView = new TextView(this);
        col2TextView.setText(col2Text);
        col2TextView.setTextColor(Color.BLACK);
        if(col2Text.equals(col3Text)){
            col2TextView.setBackgroundResource(R.color.home_color_button_1);
        }else {
            col2TextView.setBackgroundResource(R.color.home_color_button_2);
        }
        col2TextView.setPadding(8, 8, 8, 8);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        col2TextView.setLayoutParams(params2);
        tableRow.addView(col2TextView);

        // Cột 3
        TextView col3TextView = new TextView(this);
        col3TextView.setText(col3Text);
        col3TextView.setTextColor(Color.BLACK);
        col3TextView.setBackgroundResource(R.color.home_color_button_1);
        col3TextView.setPadding(8, 8, 8, 8);
        TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        col3TextView.setLayoutParams(params3);
        tableRow.addView(col3TextView);
        tableLayout.addView(tableRow);
    }
}