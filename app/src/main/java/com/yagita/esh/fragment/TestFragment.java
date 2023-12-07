package com.yagita.esh.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yagita.esh.R;
import com.yagita.esh.activity.test.TestScreen;
import com.yagita.esh.db.VocabDAO;
import com.yagita.esh.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;


public class TestFragment extends Fragment {
    Button btnStartTest;
    List<Vocabulary> list = new ArrayList<>();
    VocabDAO vocabDAO;
    TextView txtTest;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        btnStartTest = view.findViewById(R.id.btnStartTest);
        txtTest = view.findViewById(R.id.txtTest);

        vocabDAO = new VocabDAO(view.getContext());
        updateContent();
        btnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.size() > 0){
                    startActivity(new Intent(getActivity(), TestScreen.class));
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Lưu ý!!!");
                    builder.setMessage("Hiện tại bạn chưa có từ vựng nào đã học cả. Hãy trở lại khi đã học các từ vựng.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }
            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Cập nhật lại nội dung khi fragment được hiển thị lại
        updateContent();
    }

    private void updateContent() {
        list = vocabDAO.getListVocabKnown();
        String content = "Đã học " + list.size() + " từ mới. Bắt đầu kiểm tra?";
        txtTest.setText(content);
    }
}