package com.dbflowtest.com.dbflowtest.models;

import android.util.Log;

import com.dbflowtest.com.dbflowtest.db.ChatDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

/**
 * Created by COMP on 21-02-2016.
 */
@ModelContainer
@Table(database = ChatDatabase.class)
public class NewChat extends BaseModel {

    final String LOG_TAG = getClass().getName();

    public static final String COLUMN_ID   = "id";
    public static final String COLUMN_USER= "User_id";

    @PrimaryKey(autoincrement = true)
    @Column(name = COLUMN_ID)
    public long id;

    @Column
    public String message;

    @Column
    public boolean isMale;

    @Column
    public boolean isOffline;


    @Column
    @ForeignKey(saveForeignKeyModel = false)
    public ForeignKeyContainer<User> user;

   /* @Column
    @ForeignKey(references = {
            @ForeignKeyReference(columnName = COLUMN_USER, columnType = Long.class, foreignKeyColumnName = User.COLUMN_ID)}, saveForeignKeyModel = false)
    public ForeignKeyContainer<User> userForeignKeyContainer;*/


    public void setShopCategory(User user) {
        this.user = new ForeignKeyContainer<>(User.class);
       // Map<String, Object> keys = new LinkedHashMap<>();
        Log.d(LOG_TAG, "User id is :" + user.getUserId());
       // keys.put(User.COLUMN_ID, user.getUserId());
        //this.themeForeignContainer.setData(keys);
        this.user.setModel(user);
        this.user.put(User.COLUMN_ID,user.getUserId());
//        this.user.save();
    }

    public void associateUser(User user) {
        this.user = FlowManager.getContainerAdapter(User.class).toForeignKeyContainer(user);
    }


    public long getChatId() {
        return id;
    }

    public void setChatId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }

    public boolean isOffline() {
        return isOffline;
    }

    public void setIsOffline(boolean isOffline) {
        this.isOffline = isOffline;
    }
}
