package com.example.taskmanager.repository;

import com.example.taskmanager.model.User;

import java.util.List;


public interface URepository<E> {
    List<E> getList();
    void setList(List<E> list);
    void update(E e);
    void delete(E e);
    void insert(E e);
    void insertList(List<E> list);
    void addUser(User user);



}
