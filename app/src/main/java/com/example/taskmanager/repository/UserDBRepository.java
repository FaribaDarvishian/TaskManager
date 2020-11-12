//package com.example.taskmanager.repository;
//
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.example.taskmanager.dataBase.TaskManagerDBHelper;
//import com.example.taskmanager.model.User;
//
//import java.util.List;
//
//public class UserDBRepository implements URepository {
//    private static UserDBRepository sUserRepository;
//    public static Context mContext;
//    private SQLiteDatabase mDatabase;
//
//    public static UserDBRepository getInstance() {
//        if (sUserRepository == null)
//            sUserRepository = new UserDBRepository();
//        return sUserRepository;
//    }
//
//    private UserDBRepository() {
//        TaskManagerDBHelper userDBHelper = new TaskManagerDBHelper(mContext);
//        mDatabase = userDBHelper.getWritableDatabase();
//    }
//
//    @Override
//    public List getList() {
//        return null;
//    }
//
//
//    @Override
//    public void setList(List list) {
//
//    }
//
//    @Override
//    public void update(Object o) {
//
//    }
//
//    @Override
//    public void delete(Object o) {
//
//    }
//
//    @Override
//    public void insert(Object o) {
//
//    }
//
//    @Override
//    public void insertList(List list) {
//
//    }
//
//    @Override
//    public void addUser(User user) {
//
//    }
//
//}
