package com.yagita.esh.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yagita.esh.R;
import com.yagita.esh.model.Vocabulary;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

public class DBESH extends SQLiteOpenHelper {
    Context context;
    List<Vocabulary> vocabularyList;
    public DBESH(Context context) {
        super(context, "esh.sqlite", null, 1);
        this.context = context;
    }
    //truy vấn không trả kết quả
    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    //truy vấn trả kết quả
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    public void getData(){
        JSONArray jsonArray = JsonReader.loadJSONArrayFromRaw(context, R.raw.data);
        String data = "";
        if(jsonArray != null){
            data = jsonArray.toString();
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Vocabulary>>() {}.getType();
        vocabularyList = gson.fromJson(data, listType);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS tblVocabulary (" +
                "id TEXT PRIMARY KEY, " +
                "english TEXT, " +
                "sub_english TEXT, " +
                "example TEXT, " +
                "english_vocabulary_test1 TEXT, " +
                "english_vocabulary_test2 TEXT, " +
                "english_vocabulary_test3 TEXT, " +
                "fill_blank TEXT, " +
                "mistake1 TEXT, " +
                "mistake2 TEXT, " +
                "mistake3 TEXT, " +
                "status INTEGER)";
        sqLiteDatabase.execSQL(createTableQuery);
        getData();
        for (Vocabulary item : vocabularyList) {
            ContentValues values = new ContentValues();
            values.put("id", item.getID());
            values.put("english", item.getEnglish());
            values.put("sub_english", item.getSub_English());
            values.put("example", item.getExample());
            values.put("english_vocabulary_test1", item.getEnglish_Vocabulary_Test().get(0));
            values.put("english_vocabulary_test2", item.getEnglish_Vocabulary_Test().get(1));
            values.put("english_vocabulary_test3", item.getEnglish_Vocabulary_Test().get(2));
            values.put("fill_blank", item.getFill_Blank());
            values.put("mistake1", item.getMistake().get(0));
            values.put("mistake2", item.getMistake().get(1));
            values.put("mistake3", item.getMistake().get(2));
            values.put("status", item.getStatus());
            sqLiteDatabase.insert("tblVocabulary", null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
