package com.example.taskmanager.dataBase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.taskmanager.model.State;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.model.UserType;

import java.util.Date;
import java.util.UUID;

public class TaskCursorWrapper extends CursorWrapper {
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Task getTask(){

        String stringUUID = getString(getColumnIndex(TaskManagerDBSchema.TaskTable.TASKCOLS.UUID));
        String title = getString(getColumnIndex(TaskManagerDBSchema.TaskTable.TASKCOLS.TITLE));
        Date taskDate = new Date(getLong(getColumnIndex(TaskManagerDBSchema.TaskTable.TASKCOLS.TASKDATE)));
        String description = getString(getColumnIndex(TaskManagerDBSchema.TaskTable.TASKCOLS.DESCRIPTION));
        String username = getString(getColumnIndex(TaskManagerDBSchema.TaskTable.TASKCOLS.USERNAME));
        State taskState =State.valueOf(getString(getColumnIndex(TaskManagerDBSchema.TaskTable.TASKCOLS.STATE)));

        return new Task(UUID.fromString(stringUUID),title,description,taskDate,username, taskState);

    }
    public User getUser(){

        String username = getString(getColumnIndex(TaskManagerDBSchema.UserTable.USERCOLS.USERNAME));
        String password = getString(getColumnIndex(TaskManagerDBSchema.UserTable.USERCOLS.PASSWORD));
        UserType userType = UserType.valueOf(getString(getColumnIndex(TaskManagerDBSchema.UserTable.USERCOLS.USERTYPE)));

        return new User( username,  password,  userType);

    }
}
