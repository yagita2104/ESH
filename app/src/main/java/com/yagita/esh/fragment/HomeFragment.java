package com.yagita.esh.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.cast.framework.media.ImagePicker;
import com.yagita.esh.R;
import com.yagita.esh.activity.home.ContributeProfileScreen;
import com.yagita.esh.activity.home.ExerciseScreen;
import com.yagita.esh.activity.home.StatisticScreen;
import com.yagita.esh.activity.home.VocabScreen;

public class HomeFragment extends Fragment {
    Button btnVocabulary, btnExercise, btnStatistics, btnContribute, btnEditImg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getWidget(view);
        addAction();
        return view;
    }
    public void getWidget(View view){
        btnVocabulary = view.findViewById(R.id.btnVocabulary);
        btnExercise = view.findViewById(R.id.btnExercise);
        btnStatistics = view.findViewById(R.id.btnStatistics);
        btnContribute = view.findViewById(R.id.btnContribute);
        btnEditImg = view.findViewById(R.id.btnEditImg);
    }
    public void addAction(){
        btnVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VocabScreen.class));
            }
        });
        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ExerciseScreen.class));
            }
        });
        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), StatisticScreen.class));
            }
        });
        btnContribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ContributeProfileScreen.class));
            }
        });
        btnEditImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });
    }
}