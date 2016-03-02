package com.dbflowtest.com.dbflowtest.models;

import com.dbflowtest.com.dbflowtest.db.ColonyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP on 14-01-2016.
 */
@ModelContainer
@Table(database = ColonyDatabase.class)
public class Queen extends BaseModel {

    @PrimaryKey(autoincrement = true)
    public long queen_id;

    @Column
    public String queen_name;

    @Column
    @ForeignKey(saveForeignKeyModel = false)
    public Colony colony;

    // For retrieval
   /* List<Ant> ants;
    @OneToMany(methods = {OneToMany.Method.SAVE, OneToMany.Method.DELETE}, variableName = "ants")
    public List<Ant> getMyAnts() {
        if (ants == null || ants.isEmpty()) {

           // ant.queenForeignKeyContainer
            *//*ants = SQLite.select()
                    .from(Ant.class)
                    .where(Ant_Table.queenForeignKeyContainer_id.eq(Id))
                    .queryList();*//*
        }
        return ants;
    }
*/

}
