package com.yagita.esh.activity.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yagita.esh.R;
import com.yagita.esh.model.Vocabulary;

import java.io.Serializable;
import java.util.Locale;

public class VocabFragment extends Fragment {
    private static final String ARG_VOCAB_DATA = "argWord";

    public Vocabulary word;
    TextToSpeech textToSpeech;
    public VocabFragment() {
        // Required empty public constructor
    }

    public static VocabFragment newInstance(Vocabulary vocab) {
        VocabFragment fragment = new VocabFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_VOCAB_DATA, (Serializable) vocab);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            word = (Vocabulary) getArguments().getSerializable(ARG_VOCAB_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vocab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txtWord, txtWordTranslate, txtSentences;
        ImageView imgVocabSpeech;

        txtWord = view.findViewById(R.id.txtWord);
        txtWordTranslate = view.findViewById(R.id.txtWordTranslate);
        txtSentences = view.findViewById(R.id.txtSentences);
        imgVocabSpeech = view.findViewById(R.id.imgVocabSpeech);

        txtWord.setText(word.getEnglish());
        txtWordTranslate.setText(word.getSub_English());
        txtSentences.setText(word.getExample());
        textToSpeech = new TextToSpeech(view.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    // Thiết lập ngôn ngữ cho Text-to-Speech (VD: English)
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        imgVocabSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dừng text-to-speech trước khi nói từ mới
                stopSpeaking();
                textToSpeech.speak(txtWord.getText(), TextToSpeech.QUEUE_FLUSH, null, null);

            }
        });
    }
    public void stopSpeaking() {
        if (textToSpeech != null && textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Dừng text-to-speech trước khi rời khỏi màn hình
        stopSpeaking();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopSpeaking();
    }
}