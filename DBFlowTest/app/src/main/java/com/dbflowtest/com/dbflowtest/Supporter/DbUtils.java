package com.dbflowtest.com.dbflowtest.Supporter;

import com.dbflowtest.com.dbflowtest.models.Ant;
import com.dbflowtest.com.dbflowtest.models.Ant_Table;
import com.dbflowtest.com.dbflowtest.models.Patients;
import com.dbflowtest.com.dbflowtest.models.Queen;
import com.dbflowtest.com.dbflowtest.models.Queen_Table;
import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP on 17-02-2016.
 */
public class DbUtils {


    /**
     * @param patientList
     */
    public static void addPatients(ArrayList<Patients> patientList) {


        for (Patients patientsObj : patientList) {

            Patients patient = new Patients();
            patient.setPatientId(patientsObj.getPatientId());
            patient.setFirstName(patientsObj.getFirstName());
            patient.setLastName(patientsObj.getLastName());
            patient.setUR(patientsObj.getUR());
            patient.setBed(patientsObj.getBed());
            patient.setAdCat(patientsObj.getAdCat());
            patient.setAdDate(patientsObj.getAdDate());
            patient.setAddress1(patientsObj.getAddress1());
            patient.setAddress2(patientsObj.getAddress2());
            patient.setAdmissionDate(patientsObj.getAdmissionDate());
            patient.setAdmissionStatus(patientsObj.getAdmissionStatus());
            patient.setDoB(patientsObj.getDoB());
            patient.setEpisodes(patientsObj.getEpisodes());
            patient.setUnit(patientsObj.getUnit());
            patient.setVisitNo(patientsObj.getVisitNo());
            patient.setSpeciality(patientsObj.getSpeciality());
            patient.setDischargeDate(patientsObj.getDischargeDate());
            patient.setMessage(patientsObj.getMessage());
            patient.setMedicare(patientsObj.getMedicare());
            patient.setInsurancePolicyNo(patientsObj.getInsurancePolicyNo());
            patient.setInitials(patientsObj.getInitials());
            patient.setInsurer(patientsObj.getInsurer());
            patient.setWard(patientsObj.getWard());
            patient.setRefDocProviderNo(patientsObj.getRefDocProviderNo());
            patient.setRefDoc(patientsObj.getRefDoc());
            patient.setGender(patientsObj.getGender());
            patient.setRefDate(patientsObj.getRefDate());
            patient.save();
        }
    }


    public static List<Patients> getAllPatients() {
        List<Patients> patientList;

        patientList = SQLite.select().from(Patients.class).queryList();

        return patientList;
    }

    /**
     * Used to fetch ants from table
     *
     * @return
     */
    public static List<Ant> getAntsFromTable() {
        List<Ant> ants;
        ants = SQLite.select().from(Ant.class).where(Ant_Table.queenForeignKeyContainer_queen_id.eq(1)).queryList();
        return ants;
    }


    /**
     * Used to fetch Worker ants details from table
     *
     * @return
     */
    public static List<Ant> getWorkersFromTable() {
        List<Ant> ants;
        ants = SQLite.select().from(Ant.class).where(Ant_Table.type.eq("worker")).queryList();
        return ants;
    }


    /**
     * Descending list
     * For Asc set boolean to true
     *
     * @return
     */
    public static List<Ant> getDescendingList() {
        List<Ant> ants;
        ants = SQLite.select().from(Ant.class).where().orderBy(Ant_Table.id, false).queryList();
        return ants;
    }


    /**
     * Used to fetch which queen related to ants
     *
     * @param id
     * @return
     */
    public static Queen getWhichQueen(long id) {
        Queen queenName = SQLite.select().from(Queen.class).where(Queen_Table.queen_id.eq(id)).querySingle();
        return queenName;
    }

    /**
     * This will list ants by name and id .
     * If name is same and only name is provided in the groupBy statement
     * then if duplicate on the table it will list last value from the iteration.
     *
     * @return
     */
    public static List<Ant> getAntGroupByList() {
        List<Ant> antList = SQLite.select().from(Ant.class).groupBy(Ant_Table.name, Ant_Table.id).queryList();
        return antList;
    }

    /**
     * This will provide ants which are having id greater than 5
     * @return
     */
    public static List<Ant> getAntHavingList() {
        List<Ant> antList = SQLite.select().from(Ant.class).where().orderBy(Ant_Table.id, true).groupBy(Ant_Table.name, Ant_Table.id)
                .having(Ant_Table.id.greaterThanOrEq(5)).queryList();
        return antList;
    }


    /**
     * Limit and offset will provide list with corresponding ant list.
     * limit = 3 indicates limit of the list while offset = 5 indicates ant list from 5 th position.
     *
     * @return
     */
    public static List<Ant> getAntWithLimitAndOffset() {
        List<Ant> antList = SQLite.select().from(Ant.class).limit(3).offset(5).queryList();
        return antList;
    }


    /**
     * This list will rename all mat to mater
     */
    public static  void updateAntList(){
        Where<Ant> update =SQLite.update(Ant.class).set(Ant_Table.type.eq("mater")).where(Ant_Table.type.is("mat")
            ).and(Ant_Table.isMale.is(true));
        update.queryClose();

    }


    /**
     * Delete ants based on certain conditions.
     */
    public static void deleteSomeAnts(){

        SQLite.delete(Ant.class).where(Ant_Table.type.eq("mater")).and(Ant_Table.isMale.is(true)).query();

    }

    /**
     * List out only special ants who are matching with queen id.
     * Join consist of
     * Left ,Right , Inner and Full please go through this  http://www.w3schools.com/sql/sql_join.asp
     * @return
     */
    public static List<Ant> outerJoinAntList(){
        List<Ant> antList =   SQLite.select(Ant_Table.id,Ant_Table.name,Ant_Table.isMale,Ant_Table.type).from(Ant.class).leftOuterJoin(Queen.class)
                .on(Ant_Table.id.eq(Queen_Table.queen_id)).queryList();
        return antList;
    }

    /**
     * List out the full components from two table . there will be x*y rows as output
     * @return
     */
    public static List<Ant> crossJoinAntList(){
        List<Ant> antList =   SQLite.select(Ant_Table.id,Ant_Table.name,Ant_Table.isMale,Ant_Table.type).from(Ant.class)
                .crossJoin(Queen.class).natural().queryList();
        return antList;
    }


}
