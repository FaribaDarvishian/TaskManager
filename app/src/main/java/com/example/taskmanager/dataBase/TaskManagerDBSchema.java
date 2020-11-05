package com.example.taskmanager.dataBase;

public class TaskManagerDBSchema {
    public static final String NAME = "TaskManagerDB.dp";
    public static final int VERSION = 1;

    public static final class UserTable {
        public static final String NAME = "userTable";

        public static final class USERCOLS {
            public static final String id = "idUser";
            public static final String USERNAME = "userName";
            public static final String PASSWORD = "password";
            public static final String USERTYPE = "userType";

        }
    }



    public static final class TaskTable {
        public static final String NAME = "TaskTable";

        public static final class TASKCOLS {
            public static final String ID = "id";
            public static final String UUID="uuid";
            //userid TODO
            public static final String TITLE = "title";
            public static final String DESCRIPTION ="description";
            public static final String TASKDATE = "taskDate";
            public static final String STATE = "state";
            public static final String USERNAME= "user";
        }
    }
}
