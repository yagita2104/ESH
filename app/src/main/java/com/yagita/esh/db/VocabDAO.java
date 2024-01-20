package com.yagita.esh.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.yagita.esh.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class VocabDAO {
    Context context;
    DBESH database;
    public VocabDAO(Context context) {
        this.context = context;
        database = new DBESH(context);
    }
    public String getTableName(){
        return database.getTableName();
    }
    public List<Vocabulary> getListVocab() {
        List<Vocabulary> vocabularyList = new ArrayList<>();
        String sql = "select * from "+ getTableName();
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
        String sql = "UPDATE "+getTableName()+" SET status = "+vocabulary.getStatus()+" WHERE id = '"+vocabulary.getID()+"'";
        database.queryData(sql);
    }

    public List<Vocabulary> getListVocabUnknown() {
        String sql = "select * from "+getTableName()+" WHERE status = 0";
        List<Vocabulary> vocabularyList = new ArrayList<>();
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
        String sql = "select * from "+ getTableName() +" WHERE status = 1";
        List<Vocabulary> vocabularyList = new ArrayList<>();
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
    public List<Vocabulary> getVocabWithId(String id) {
        List<Vocabulary> vocabularyList = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName() + " WHERE id LIKE '" + id + "%'";
        Cursor cs = database.getData(sql);
        while (cs.moveToNext()) {
            String idd = cs.getString(0);
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
            Vocabulary a = new Vocabulary(idd, english, sub_english, example, eng, fill_blank, mistake, status);
            vocabularyList.add(a);
        }
        return vocabularyList;
    }
}
