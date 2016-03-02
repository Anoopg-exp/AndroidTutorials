package com.dbflowtest.com.dbflowtest.models;

import com.dbflowtest.com.dbflowtest.db.ChatDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by COMP on 21-02-2016.
 */
@ModelContainer
@Table(database = ChatDatabase.class)
public class User extends BaseModel{

    public static final String COLUMN_ID = "id";

    @PrimaryKey(autoincrement = true)
    @Column(name = COLUMN_ID)
    public long  id;

    @Column
    public  String userName;

    @Column
    public  String userStatus;


    public long getUserId() {
        return id;
    }

    public void setUserId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
