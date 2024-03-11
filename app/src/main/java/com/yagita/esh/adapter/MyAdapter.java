package com.yagita.esh.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.yagita.esh.activity.home.VocabFragment;
import com.yagita.esh.model.Vocabulary;

import java.util.List;

public class MyAdapter extends FragmentStateAdapter {
    private final List<Vocabulary> vocabs;

    public MyAdapter(@NonNull FragmentActivity fragmentActivity, List<Vocabulary> vocabs) {
        super(fragmentActivity);
        this.vocabs = vocabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return VocabFragment.newInstance(vocabs.get(position));
    }

    @Override
    public int getItemCount() {
        return vocabs.size();
    }
    public Vocabulary getVocabularyAtPosition(int position) {
        if (position >= 0 && position < vocabs.size()) {
            return vocabs.get(position);
        }
        return null;
    }
    public void removeVocabularyAtPosition(int position) {
        if (position >= 0 && position < vocabs.size()) {
            vocabs.remove(position);
            notifyDataSetChanged(); // Cập nhật lại Adapter sau khi xoá
        }
    }


}
