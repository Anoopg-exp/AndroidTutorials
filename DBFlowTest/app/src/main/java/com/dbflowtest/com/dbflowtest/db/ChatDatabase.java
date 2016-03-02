package com.dbflowtest.com.dbflowtest.db;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by COMP on 21-02-2016.
 */
@Database(name = ChatDatabase.name, version = PatientDataBase.version)
public class ChatDatabase {
    public static final String name = "ChatDB";
    public static final int version = 1;
}
