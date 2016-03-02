package com.dbflowtest.com.dbflowtest.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by COMP on 17-02-2016.
 */
@Database(name = PatientDataBase.name, version = PatientDataBase.version)
public class PatientDataBase {
    public static final String name = "PatientDb";
    public static final int version = 1;
}
