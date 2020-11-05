package com.example.taskmanager.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.taskmanager.dataBase.TaskCursorWrapper;
import com.example.taskmanager.dataBase.TaskManagerDBHelper;
import com.example.taskmanager.dataBase.TaskManagerDBSchema;
import com.example.taskmanager.model.State;
import com.example.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskDBRepository implements IRepository<Task> {
    private static TaskDBRepository sTaskDBRepository;
    public static Context mContext;
    private SQLiteDatabase mDatabase;

    public static TaskDBRepository getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (sTaskDBRepository == null)
            sTaskDBRepository = new TaskDBRepository();
        return sTaskDBRepository;
    }

    private TaskDBRepository() {
        TaskManagerDBHelper taskDBHelper = new TaskManagerDBHelper(mContext);
        mDatabase = taskDBHelper.getWritableDatabase();
    }

    @Override
    public List<Task> getList(State taskState) {

        List<Task> tasks = new ArrayList<>();
        String selection = TaskManagerDBSchema.TaskTable.TASKCOLS.STATE + "=?";
        String[] selectionArgs = new String[]{taskState.toString()};
        TaskCursorWrapper cursorWrapper = queryTasks(selection, selectionArgs);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                tasks.add(cursorWrapper.getTask());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return tasks;
    }
    @Override
    public List<Task> getList(State taskState, String username) {

        List<Task> tasks = new ArrayList<>();
        String selection = TaskManagerDBSchema.TaskTable.TASKCOLS.STATE + TaskManagerDBSchema.TaskTable.TASKCOLS.USERNAME + " = ?";
        String[] selectionArgs = new String[]{taskState.toString(), username};
        TaskCursorWrapper cursorWrapper = queryTasks(selection, selectionArgs);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                tasks.add(cursorWrapper.getTask());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return tasks;


    }

    @Override
    public List<Task> getList() {
        List<Task> tasks = new ArrayList<>();
        TaskCursorWrapper cursorWrapper = queryTasks(null, null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                tasks.add(cursorWrapper.getTask());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return tasks;
    }

    private TaskCursorWrapper queryTasks(String selection, String[] selectionArgs) {
        Cursor cursor = mDatabase.query(TaskManagerDBSchema.TaskTable.NAME,
               null , selection, selectionArgs, null, null, null);
        TaskCursorWrapper taskCursorWrapper = new TaskCursorWrapper(cursor);
        return taskCursorWrapper;
    }

    @Override
    public Task get(UUID uuid) {
        String selection = TaskManagerDBSchema.TaskTable.TASKCOLS.UUID + "=?";
        String[] selectionArgs = new String[]{uuid.toString()};
        TaskCursorWrapper taskCursorWrapper = queryTasks(selection, selectionArgs);
        try {
            taskCursorWrapper.moveToFirst();
            return taskCursorWrapper.getTask();
        } finally {
            taskCursorWrapper.close();
        }
    }

    @Override
    public void setList(List<Task> list) {

    }

    @Override
    public void update(Task task) {
        ContentValues values = getTaskContentValue(task);
        String where = TaskManagerDBSchema.TaskTable.TASKCOLS.UUID + "=?";
        String[] whereArgs = new String[]{task.getId().toString()};
        mDatabase.update(TaskManagerDBSchema.TaskTable.NAME, values, where, whereArgs);

    }

    private ContentValues getTaskContentValue(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskManagerDBSchema.TaskTable.TASKCOLS.UUID, task.getId().toString());
        values.put(TaskManagerDBSchema.TaskTable.TASKCOLS.TITLE, task.getTaskTitle());
        values.put(TaskManagerDBSchema.TaskTable.TASKCOLS.DESCRIPTION, task.getTaskDescription());
        values.put(TaskManagerDBSchema.TaskTable.TASKCOLS.USERNAME, task.getTaskDescription());
        values.put(TaskManagerDBSchema.TaskTable.TASKCOLS.TASKDATE, task.getTaskDate().getTime());
//        if(task.getTaskState().toString()!=null)
//            values.put(TaskDBSchema.TaskTable.COLS.STATE, task.getTaskState().toString());
        return values;
    }

    @Override
    public void delete(Task task) {
        String where = TaskManagerDBSchema.TaskTable.TASKCOLS.UUID + "=?";
        String[] whereArgs = new String[]{task.getId().toString()};
        mDatabase.delete(TaskManagerDBSchema.TaskTable.NAME, where, whereArgs);
    }

    @Override
    public void insert(Task task) {
        ContentValues values = getTaskContentValue(task);
        mDatabase.insert(TaskManagerDBSchema.TaskTable.NAME, null, values);
    }

    @Override
    public void clearTaskRepository() {
        mDatabase = null;
    }

    @Override
    public void deleteUserTask(String username) {
        String where = TaskManagerDBSchema.TaskTable.TASKCOLS.USERNAME + "=?";
        String[] whereArgs = new String[]{username};
        mDatabase.delete(TaskManagerDBSchema.TaskTable.NAME, where, whereArgs);
    }

    @Override
    public void delete(UUID taskId) {

        String where = TaskManagerDBSchema.TaskTable.TASKCOLS.UUID + "=?";
        String[] whereArgs = new String[]{taskId.toString()};
        mDatabase.delete(TaskManagerDBSchema.TaskTable.NAME, where, whereArgs);
    }

    @Override
    public void insertList(List<Task> list) {

    }

    @Override
    public int getPosition(UUID uuid) {
        List<Task> tasks = getList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(uuid))
                return i;
        }
        return -1;
    }

    @Override
    public void addTask(State taskState) {
        Task task = new Task();
        task.setTaskTitle("Task : " + (sTaskDBRepository.getList().size() + 1));
        task.setTaskDescription("demoTask");
        task.setTaskDate(new Date());
        task.setUser("Todo");
        task.setTaskState(taskState);
        ContentValues values = getTaskContentValue(task);
        mDatabase.insert(TaskManagerDBSchema.TaskTable.NAME, null, values);

    }
    @Override
    public boolean checkTaskExists(Task task) {

        return CheckIsDataAlreadyInDBorNot(TaskManagerDBSchema.NAME, TaskManagerDBSchema.TaskTable.TASKCOLS.USERNAME, task.getUsername());
    }

    public boolean CheckIsDataAlreadyInDBorNot(String TableName,
                                               String dbfield, String fieldValue) {
        TaskManagerDBHelper taskBaseHelper = new TaskManagerDBHelper(mContext);
        SQLiteDatabase sqldb = taskBaseHelper.getWritableDatabase();
        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
