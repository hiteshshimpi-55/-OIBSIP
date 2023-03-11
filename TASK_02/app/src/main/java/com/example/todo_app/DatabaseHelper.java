package com.example.todo_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context,"task.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String createTable = "CREATE TABLE TASK_TABLE( ID INTEGER PRIMARY KEY AUTOINCREMENT, TASK_NAME TEXT, DONE BOOLEAN)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addOne(DataModel dataModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("TASK_NAME", dataModel.getName());
        cv.put("DONE", dataModel.isCheck());
        long insert = db.insert("TASK_TABLE", null, cv);
        if(insert == -1){
            return false;
        }
        return true;
    }

    public List<DataModel> getAlldata(){
        List<DataModel> list = new ArrayList<>();

        String query = "SELECT * FROM TASK_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                DataModel dataModel = new DataModel();
                dataModel.setName(cursor.getString(1));
                dataModel.setCheck(cursor.getInt(2) == 1);
                list.add(dataModel);
            }while (cursor.moveToNext());
        }else {
            //failure
        }

        cursor.close();
        db.close();
        return list;
    }

    public boolean deleteOne(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM TASK_TABLE WHERE TASK_NAME = '" + name + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }
}
