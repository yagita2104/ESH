package com.yagita.esh.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yagita.esh.R;
import com.yagita.esh.activity.storage.VocabKnown;
import com.yagita.esh.activity.storage.VocabUnknown;

public class StorageFragment extends Fragment {
    Button btnVocabKnow, btnVocabUnknown;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_storage, container, false);
        btnVocabKnow = view.findViewById(R.id.btnVocabKnow);
        btnVocabUnknown = view.findViewById(R.id.btnVocabUnknow);
        btnVocabKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), VocabKnown.class));
            }
        });
        btnVocabUnknown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), VocabUnknown.class));
            }
        });

        return view;
    }
}