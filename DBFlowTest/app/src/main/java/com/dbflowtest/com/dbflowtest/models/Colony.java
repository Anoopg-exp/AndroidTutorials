package com.dbflowtest.com.dbflowtest.models;

import com.dbflowtest.com.dbflowtest.db.ColonyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by COMP on 14-01-2016.
 */

@ModelContainer
@Table(database = ColonyDatabase.class)
public class Colony extends BaseModel {
    @PrimaryKey(autoincrement = true)
    public long id;
    @Column
    public  String name;
}
