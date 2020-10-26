package com.example.taskmanager.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.NoCopySpan;

import androidx.annotation.Nullable;

import static com.example.taskmanager.dataBase.TaskDBSchema.*;
import static com.example.taskmanager.dataBase.TaskDBSchema.NAME;

public class TaskDBHelper extends SQLiteOpenHelper {
    public TaskDBHelper(@Nullable Context context) {
       // super();
       super(context, NAME, null, TaskDBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TaskTable.NAME + "(" +
                TaskDBSchema.TaskTable.COLS.ID + " integer primary key autoincrement," +
                TaskDBSchema.TaskTable.COLS.UUID + " text," +
                TaskTable.COLS.TITLE + " text," +
                TaskTable.COLS.TaskDate + " long," +
                TaskTable.COLS.USERNAME + " text," +
                TaskTable.COLS.DESCRIPTION + " text," +
                TaskTable.COLS.STATE + " text" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO
    }


}
