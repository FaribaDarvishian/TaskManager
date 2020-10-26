package com.example.taskmanager.repository;

import com.example.taskmanager.model.State;
import com.example.taskmanager.model.Task;

import java.util.List;
import java.util.UUID;

public interface IRepository<E> {
    List<E> getList(State taskState);
    List<E> getList();
    E get(UUID uuid);
    void setList(List<E> list);
    void update(E e);
    void delete(E e);
    void insert(E e);
    void insertList(List<E> list);
    int getPosition(UUID uuid);
    void addTask(State e);
    void clearTaskRepository();
    void deleteUserTask(String username);
    void delete(UUID taskId);
    boolean checkTaskExists(Task task);
    List<Task> getList(State taskState, String username);


}
