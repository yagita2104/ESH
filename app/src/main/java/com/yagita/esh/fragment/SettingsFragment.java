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
import android.widget.Toast;

import com.yagita.esh.R;
import com.yagita.esh.activity.home.StatisticScreen;
import com.yagita.esh.activity.settings.FeedbackScreen;
import com.yagita.esh.activity.settings.NotificationScreen;
import com.yagita.esh.activity.settings.SpecializedScreen;

public class SettingsFragment extends Fragment {
    Button btnLanguage, btnSpecialized, btnNotification, btnFeedback, btnMoreSettings;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        getWidget(view);
        addAction();
        return view;
    }
    private void getWidget(View view) {
        btnLanguage = view.findViewById(R.id.btnLanguage);
        btnSpecialized = view.findViewById(R.id.btnSpecialized);
        btnNotification = view.findViewById(R.id.btnNotification);
        btnFeedback = view.findViewById(R.id.btnFeedback);
        btnMoreSettings = view.findViewById(R.id.btnMoreSettings);
    }
    private void addAction() {
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Ngôn ngữ");
                builder.setItems(new CharSequence[]{"Tiếng anh", "Tiếng việt"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Toast.makeText(view.getContext(), "Đã chọn tiếng anh", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(view.getContext(), "Đã chọn tiếng việt", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        btnSpecialized.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), SpecializedScreen.class));
            }
        });
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), NotificationScreen.class));
            }
        });
        btnMoreSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Cài đặt khác");
                builder.setItems(new CharSequence[]{"Về chúng tôi", "Phiên bản"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Toast.makeText(view.getContext(), "Về chúng tôi: .....", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(view.getContext(), "Phiên bản 0.0.0", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), FeedbackScreen.class));
            }
        });
    }
}