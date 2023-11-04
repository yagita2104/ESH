package com.yagita.esh.db;

import android.content.Context;
import android.content.res.Resources;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.Scanner;

public class JsonReader {

    public static JSONObject loadJSONFromRaw(Context context, int resourceId) {
        String json = null;
        try {
            Resources resources = context.getResources();
            InputStream inputStream = resources.openRawResource(resourceId);

            Scanner scanner = new Scanner(inputStream, "UTF-8");
            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }

            inputStream.close();
            json = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray loadJSONArrayFromRaw(Context context, int resourceId) {
        String json = null;
        try {
            Resources resources = context.getResources();
            InputStream inputStream = resources.openRawResource(resourceId);

            Scanner scanner = new Scanner(inputStream, "UTF-8");
            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }

            inputStream.close();
            json = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            return new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}