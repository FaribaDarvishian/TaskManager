package com.example.taskmanager.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.taskmanager.dataBase.TaskManagerDBSchema.*;
import static com.example.taskmanager.dataBase.TaskManagerDBSchema.NAME;

public class TaskManagerDBHelper extends SQLiteOpenHelper {
    public TaskManagerDBHelper(@Nullable Context context) {
       // super();
       super(context, NAME, null, TaskManagerDBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder userQuery = new StringBuilder();
        userQuery.append("CREATE TABLE " + TaskManagerDBSchema.UserTable.NAME + " (");
        userQuery.append(TaskManagerDBSchema.UserTable.USERCOLS.id + " " +
                "INTEGER PRIMARY KEY AUTOINCREMENT,");
        userQuery.append(UserTable.USERCOLS.USERNAME + " TEXT,");
        userQuery.append(UserTable.USERCOLS.PASSWORD+ " TEXT");
        userQuery.append(UserTable.USERCOLS.USERTYPE+ " TEXT");
        userQuery.append(");");

        StringBuilder tableQuery = new StringBuilder();

        tableQuery.append("CREATE TABLE " + TaskManagerDBSchema.TaskTable.NAME + " (");
        tableQuery.append(TaskTable.TASKCOLS.ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT,");
       // tableQuery.append(TaskManagerDBSchema.TaskTable.TASKCOLS.idUser +
            //    " INTEGER FOREIGN KEY FROM userTable,");
        tableQuery.append(TaskTable.TASKCOLS.UUID+ " TEXT NOT NULL,");
        tableQuery.append(TaskTable.TASKCOLS.TITLE+ " TEXT,");
        tableQuery.append(TaskTable.TASKCOLS.DESCRIPTION + " TEXT,");
        tableQuery.append(TaskTable.TASKCOLS.TASKDATE + " TEXT,");
        tableQuery.append(TaskTable.TASKCOLS.STATE+ " TEXT");
        tableQuery.append(");");


        db.execSQL(userQuery.toString());
        db.execSQL(tableQuery.toString());



//        db.execSQL("CREATE TABLE " + TaskTable.NAME + "(" +
//                TaskTable.TASKCOLS.ID + " integer primary key autoincrement," +
//                TaskTable.TASKCOLS.UUID + " text," +
//                TaskTable.TASKCOLS.TITLE + " text," +
//                TaskTable.TASKCOLS.TaskDate + " long," +
//                TaskTable.TASKCOLS.USERNAME + " text," +
//                TaskTable.TASKCOLS.DESCRIPTION + " text," +
//                TaskTable.TASKCOLS.STATE + " text" +
//                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO
    }


}
