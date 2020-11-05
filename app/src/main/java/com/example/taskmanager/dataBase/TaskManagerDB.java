package com.example.taskmanager.dataBase;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;

@Database(entities ={Task.class, User.class},version =1,exportSchema = false)
@TypeConverters({Task.UUIDConverter.class, Task.StateConverter.class, User.DateConverter.class,
        User.UserTypeConverter.class})

public abstract class TaskManagerDB extends RoomDatabase {

    public abstract com.example.taskmanager.dataBase.TaskDataBaseDAO taskDataBaseDAO();
    public abstract com.example.taskmanager.dataBase.UserDataBaseDAO userDateBaseDAO();
}
