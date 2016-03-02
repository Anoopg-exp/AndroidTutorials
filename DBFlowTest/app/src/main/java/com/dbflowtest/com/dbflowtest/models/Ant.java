package com.dbflowtest.com.dbflowtest.models;

import com.dbflowtest.com.dbflowtest.db.ColonyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

/**
 * Created by COMP on 14-01-2016.
 */

@ModelContainer
@Table(database = ColonyDatabase.class)
public class Ant extends BaseModel {


    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String type;

    @Column
    public boolean isMale;

    @Column
    public String name;

    @ForeignKey(saveForeignKeyModel = false)
    public ForeignKeyContainer<Queen> queenForeignKeyContainer ;

    public void associateQueen(Queen queen){
        queenForeignKeyContainer = FlowManager.getContainerAdapter(Queen.class).toForeignKeyContainer(queen);
    }


}
