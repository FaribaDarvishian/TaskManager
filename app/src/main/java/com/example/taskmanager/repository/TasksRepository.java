//package com.example.taskmanager.repository;
//
//
//
//import com.example.taskmanager.model.State;
//import com.example.taskmanager.model.Task;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//
//public class TasksRepository implements Serializable {
//    private static TasksRepository sTasksRepository;
//    private List<Task> mTasks;
//    public static int mNumberOfTasks;
//
//    public static TasksRepository getInstance() {
//        if (sTasksRepository == null)
//            sTasksRepository = new TasksRepository();
//        return sTasksRepository;
//    }
//
//    private TasksRepository() {
//
//        mTasks = new ArrayList<>();
//        for (int i = 0; i < mNumberOfTasks; i++) {
//            Task task = new Task();
//            mTasks.add(task);
//        }
//    }
//
//    public List<Task> getList() {
//        return mTasks;
//    }
//
//    public List<Task> getList(State taskState) {
//        List<Task> taskList = new ArrayList<>();
//        for (Task task : mTasks) {
//            if (task.getTaskState() == taskState)
//                taskList.add(task);
//        }
//        return taskList;
//    }
//
//    public List<Task> getList(State taskState, String username) {
//        List<Task> taskList = new ArrayList<>();
//        for (Task task : mTasks) {
//            if (task.getTaskState() == taskState && task.getUsername().equals(username))
//                taskList.add(task);
//        }
//        return taskList;
//    }
//
//
//    public void addTask(Task task) {
//
//        mTasks.add(task);
//    }
//
//    public int getIndexOfTask(Task task) {
//        return mTasks.indexOf(task);
//    }
//
//    public void clearTaskRepository() {
//        sTasksRepository = new TasksRepository();
//
//    }
//
//    public void deleteUserTask(String username){
//        for (Task task:mTasks) {
//            if (task.getUsername().equals(username))
//                mTasks.remove(task);
//        }
//    }
//    public void delete(UUID taskId){
//        for (Task task:mTasks) {
//            if (task.getId().equals(taskId))
//                mTasks.remove(task);
//        }
//    }
//    public Task get(UUID uuid) {
//        for (Task task : mTasks) {
//            if (task.getId().equals(uuid))
//                return task;
//        }
//        return null;
//    }
//
//    public void update(Task task) {
//        Task updateTask = get(task.getId());
//        updateTask.setTaskTitle(task.getTaskTitle());
//        updateTask.setTaskDescription(task.getTaskDescription());
//        updateTask.setTaskState(task.getTaskState());
//        updateTask.setTaskDate(task.getTaskDate());
//
//    }
//    public boolean checkTaskExists(Task searchTask){
//        for (Task task : mTasks) {
//            if (task.equals(searchTask))
//                return  false;
//        }
//        return  true;
//    }
//}
