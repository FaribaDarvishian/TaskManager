package com.example.taskmanager.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


    @Entity(tableName = "taskTable")
    public class Task implements Serializable {

        @PrimaryKey(autoGenerate = true)
        private Long id;

        @ColumnInfo(name = "uuid")
        private UUID mTaskId;

        @ColumnInfo(name = "userId")
        private Long mUserId;

        @ColumnInfo(name = "title")
        private String mTaskTitle;

        @ColumnInfo(name = "description")
        private String mTaskDescription;

        @ColumnInfo(name = "state")
        private State mTaskState;

        @ColumnInfo(name = "date")
        private Date mTaskDate;

        public Task() {
        }

        public Task(User user) {
            mUserId = user.getId();
            mTaskId = UUID.randomUUID();
            mTaskDate = new Date();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public UUID getTaskId() {
            return mTaskId;
        }

        public void setTaskId(UUID taskId) {
            mTaskId = taskId;
        }

        public Long getUserId() {
            return mUserId;
        }

        public void setUserId(Long userId) {
            mUserId = userId;
        }

        public String getTaskTitle() {
            return mTaskTitle;
        }

        public void setTaskTitle(String taskTitle) {
            mTaskTitle = taskTitle;
        }

        public String getTaskDescription() {
            return mTaskDescription;
        }

        public void setTaskDescription(String taskDescription) {
            mTaskDescription = taskDescription;
        }

        public State getTaskState() {
            return mTaskState;
        }

        public void setTaskState(State taskState) {
            mTaskState = taskState;
        }

        public Date getTaskDate() {
            return mTaskDate;
        }

        public void setTaskDate(Date date) {
            mTaskDate = date;
        }

        public static class UUIDConverter {

            @TypeConverter
            public String fromUUID(UUID value) {
                return value.toString();
            }

            @TypeConverter
            public UUID fromString(String value) {
                return UUID.fromString(value);
            }
        }

        public static class StateConverter {

            @TypeConverter
            public String fromState(State value) {
                return value.toString();
            }

            @TypeConverter
            public State formString(String value) {
                switch (value) {
                    case "TODO":
                        return State.TODO;
                    case "DOING":
                        return State.DOING;
                    case "DONE":
                        return State.DONE;
                }
                return null;
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "id=" + id +
                    ", mTaskId=" + mTaskId +
                    ", mUserId=" + mUserId +
                    ", mTaskTitle='" + mTaskTitle + '\'' +
                    ", mTaskDescription='" + mTaskDescription + '\'' +
                    ", mTaskState=" + mTaskState +
                    ", mTaskDate=" + mTaskDate +
                    '}';

        }
    }
