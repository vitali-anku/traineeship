package travel.avg.task1.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBMethods {
    public static ArrayList<String> outputMap(Context context){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ArrayList<String> list = new ArrayList<>();

        try{
            String query = "Select date from " + DBHelper.tableName1;
            Cursor cursor = sqLiteDatabase.rawQuery(query,null);

            String asd = "";
            if(cursor.moveToFirst()){
                int int1 = cursor.getColumnIndex("date");
                do {
                    String date = cursor.getString(int1);

                    if(!date.equals(asd) || asd.equals("")){
                        list.add(date);
                    }
                    asd = date;

                }while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (Exception e){

        }
        return list;
    }
    public static void saveMap(Context context, Map<String, Map<String, Integer>> inputMap) {

        DBHelper dbHelper = new DBHelper(context);

        for (Object nameKey1 : inputMap.keySet()) {
            for (Object nameKey2 : inputMap.get(nameKey1).keySet()) {
                String dat = nameKey1.toString();
                String nam = nameKey2.toString();
                Integer count = inputMap.get(nameKey1).get(nameKey2);
                dbHelper.addList(dat, nam, count);
            }
        }
    }
    public static Map<String, Integer> WordCount(Context context){
        Map<String, Integer> wordcount = new HashMap<>();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        List<String> str = new ArrayList<>();

        try{
            String query = "Select name from " + DBHelper.tableName1;
            Cursor cursor = sqLiteDatabase.rawQuery(query,null);

            if(cursor.moveToFirst()){
                int int1 = cursor.getColumnIndex("name");
                do {
                    String name = cursor.getString(int1);
                    str.add(name);
                }while (cursor.moveToNext());
            }

            cursor.close();

            for (String word : str) {
                Integer oldCount = wordcount.get(word);
                if ( oldCount == null ) {
                    oldCount = 0;
                }
                wordcount.put(word, oldCount + 1);
            }
        }
        catch (Exception e){

        }

        return wordcount;
    }
    public static  Map<String, Integer> outputMap1(Context context, String date){
        Map<String, Integer> firstMap = new LinkedHashMap<>();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        try{
            String query = "Select date, name, count from List";
            Cursor cursor = sqLiteDatabase.rawQuery(query,null);

            if(cursor.moveToFirst()){
                int int3 = cursor.getColumnIndex("date");
                int int1 = cursor.getColumnIndex("name");
                int int2 = cursor.getColumnIndex("count");
                do {
                    String nam = cursor.getString(int1);
                    Integer count = cursor.getInt(int2);
                    String outdate = cursor.getString(int3);
                    if(date.equals(outdate)){
                        firstMap.put(nam, count);
                    }
                }while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (Exception e){}

        return firstMap;
    }
}