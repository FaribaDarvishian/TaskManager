package com.example.taskmanager.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(tableName = "UserTable")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "userName")
    private String mUserName;

    @ColumnInfo(name = "passWord")
    private String mPassword;

    @ColumnInfo(name = "dateCreated")
    private Date mDateCreated;

    @ColumnInfo(name = "userType")
    private UserType mUserType;

    public User(String userName, String password, UserType userType) {
        mUserName = userName;
        mPassword = password;
        mUserType = userType;
        mDateCreated = new Date();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public Date getDateCreated() {
        return mDateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        mDateCreated = dateCreated;
    }

    public UserType getUserType() {
        return mUserType;
    }

    public void setUserType(UserType userType) {
        mUserType = userType;
    }

    public static class DateConverter {

        @TypeConverter
        public Long fromDate(Date value) {
            return value.getTime();
        }

        @TypeConverter
        public Date fromLong(Long value) {
            return new Date(value);
        }
    }
    public static class UserTypeConverter {

        @TypeConverter
        public String convertUserTypeToString(UserType value) {
            return value.toString();
        }

        @TypeConverter
        public UserType formString(String value) {
            switch (value) {
                case "ADMIN":
                    return UserType.ADMIN;
                case "USER":
                    return UserType.USER;
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mUserName='" + mUserName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mDateCreated=" + mDateCreated +
                ", mUserType=" + mUserType +
                '}';
    }
    public String getPhotoFileName() {
        return "IMG_PROFILE_" + getUserName() + ".jpg";
    }
}
