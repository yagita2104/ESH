package com.yagita.esh.db;

import android.content.Context;
import android.database.Cursor;

import com.yagita.esh.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class VocabDAO {
    Context context;
    DBESH database;
    List<Vocabulary> vocabularyList = new ArrayList<>();

    public VocabDAO(Context context) {
        this.context = context;
        database = new DBESH(context);
    }

    public List<Vocabulary> getListVocab() {
        String sql = "select * from tblVocabulary";
        vocabularyList.clear();
        Cursor cs = database.getData(sql);
        while (cs.moveToNext()) {
            String id = cs.getString(0);
            String english = cs.getString(1);
            String sub_english = cs.getString(2);
            String example = cs.getString(3);
            String english_vocabulary_test1 = cs.getString(4);
            String english_vocabulary_test2 = cs.getString(5);
            String english_vocabulary_test3 = cs.getString(6);
            List<String> eng = new ArrayList<>();
            eng.add(english_vocabulary_test1);
            eng.add(english_vocabulary_test2);
            eng.add(english_vocabulary_test3);
            String fill_blank = cs.getString(7);
            String mistake1 = cs.getString(8);
            String mistake2 = cs.getString(9);
            String mistake3 = cs.getString(10);
            List<String> mistake = new ArrayList<>();
            mistake.add(mistake1);
            mistake.add(mistake2);
            mistake.add(mistake3);
            int status = cs.getInt(11);
            Vocabulary a = new Vocabulary(id, english, sub_english, example, eng, fill_blank, mistake, status);
            vocabularyList.add(a);
        }
        return vocabularyList;
    }
    public void setStatus(Vocabulary vocabulary){
        String sql = "UPDATE tblVocabulary SET status = "+vocabulary.getStatus()+" WHERE id = '"+vocabulary.getID()+"'";
        database.queryData(sql);
    }

    public List<Vocabulary> getListVocabUnknown() {
        String sql = "select * from tblVocabulary WHERE status = 0";
        vocabularyList.clear();
        Cursor cs = database.getData(sql);
        while (cs.moveToNext()) {
            String id = cs.getString(0);
            String english = cs.getString(1);
            String sub_english = cs.getString(2);
            String example = cs.getString(3);
            String english_vocabulary_test1 = cs.getString(4);
            String english_vocabulary_test2 = cs.getString(5);
            String english_vocabulary_test3 = cs.getString(6);
            List<String> eng = new ArrayList<>();
            eng.add(english_vocabulary_test1);
            eng.add(english_vocabulary_test2);
            eng.add(english_vocabulary_test3);
            String fill_blank = cs.getString(7);
            String mistake1 = cs.getString(8);
            String mistake2 = cs.getString(9);
            String mistake3 = cs.getString(10);
            List<String> mistake = new ArrayList<>();
            mistake.add(mistake1);
            mistake.add(mistake2);
            mistake.add(mistake3);
            int status = cs.getInt(11);
            Vocabulary a = new Vocabulary(id, english, sub_english, example, eng, fill_blank, mistake, status);
            vocabularyList.add(a);
        }
        return vocabularyList;
    }
    public List<Vocabulary> getListVocabKnown() {
        String sql = "select * from tblVocabulary WHERE status = 1";
        vocabularyList.clear();
        Cursor cs = database.getData(sql);
        while (cs.moveToNext()) {
            String id = cs.getString(0);
            String english = cs.getString(1);
            String sub_english = cs.getString(2);
            String example = cs.getString(3);
            String english_vocabulary_test1 = cs.getString(4);
            String english_vocabulary_test2 = cs.getString(5);
            String english_vocabulary_test3 = cs.getString(6);
            List<String> eng = new ArrayList<>();
            eng.add(english_vocabulary_test1);
            eng.add(english_vocabulary_test2);
            eng.add(english_vocabulary_test3);
            String fill_blank = cs.getString(7);
            String mistake1 = cs.getString(8);
            String mistake2 = cs.getString(9);
            String mistake3 = cs.getString(10);
            List<String> mistake = new ArrayList<>();
            mistake.add(mistake1);
            mistake.add(mistake2);
            mistake.add(mistake3);
            int status = cs.getInt(11);
            Vocabulary a = new Vocabulary(id, english, sub_english, example, eng, fill_blank, mistake, status);
            vocabularyList.add(a);
        }
        return vocabularyList;
    }
}
