package com.dbflowtest.com.dbflowtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.dbflowtest.com.dbflowtest.Supporter.DbUtils;
import com.dbflowtest.com.dbflowtest.adapters.AntRecyclerAdapter;
import com.dbflowtest.com.dbflowtest.models.Ant;
import com.dbflowtest.com.dbflowtest.models.Colony;
import com.dbflowtest.com.dbflowtest.models.NewChat;
import com.dbflowtest.com.dbflowtest.models.Queen;
import com.dbflowtest.com.dbflowtest.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP on 15-02-2016.
 */
public class DbFlowTestActivity extends AppCompatActivity {

    final String LOG_TAG = getClass().getName();
    private TextView mainTxt;
    private List<Ant> antList = new ArrayList<>();
    private AntRecyclerAdapter adapter;
    private RecyclerView patientRecycleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        mainTxt = (TextView) findViewById(R.id.mainTxt);
        patientRecycleView = (RecyclerView) findViewById(R.id.patientRecycleView);
        patientRecycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AntRecyclerAdapter(antList);
        patientRecycleView.setAdapter(adapter);
        initManagers();
        //  initChatTable();

    }

    /**
     * Using foreign key in db flow
     */
    private void initChatTable() {

        User user = new User();
        user.userName = "atg";
        user.userStatus = "active";
        user.save();

        NewChat newChat = new NewChat();
        newChat.message = "hi";
        newChat.isMale = true;
        newChat.isOffline = false;
        newChat.associateUser(user);
        newChat.save();

    }

    /**
     * Used for inserting and retrieval purpose
     */
    private void initManagers() {

        Colony c = new Colony();
        c.name = "New Colony";
        c.id = 1;
        c.save();

        Queen q = new Queen();
        q.queen_name = "New queen0";
        q.queen_id = 1;
        q.colony = c;
        q.save();

        Queen q1 = new Queen();
        q1.queen_name = "New queen1";
        q1.queen_id = 2;
        q1.colony = c;
        q1.save();

        for (int i = 1; i < 10; i++) {
            Ant ant = new Ant();
            ant.id = i;

            if (i % 2 != 0) {
                ant.isMale = true;
                ant.type = "mat";
                ant.name = "rahul" + i;
                ant.associateQueen(q);
            } else {
                ant.isMale = false;
                ant.type = "worker";
                ant.name = "daisy" + i;
                ant.associateQueen(q1);
            }
            ant.save();
        }

        //  Queen queen = new Queen();
        List<Ant> antsList = DbUtils.getAntsFromTable();

        for (Ant ant : antsList) {
            Log.i(LOG_TAG, "ant Id:" + ant.id);
            Log.i(LOG_TAG, "ant is Male:" + ant.isMale);
            Log.i(LOG_TAG, "Queen Id is :" + DbUtils.getWhichQueen((long) ant.queenForeignKeyContainer.getValue("queen_id")).queen_id);
            Log.i(LOG_TAG, "Queen name is :" + DbUtils.getWhichQueen((long) ant.queenForeignKeyContainer.getValue("queen_id")).queen_name);

        }

        Log.i(LOG_TAG, "---------------------------");

        List<Ant> workerList = DbUtils.getWorkersFromTable();
        for (Ant ant : workerList) {
            Log.i(LOG_TAG, "ant Id:" + ant.id);
            Log.i(LOG_TAG, "Ant type is :" + ant.type);
        }


        DbUtils.updateAntList();
        final List<Ant> descendingList = DbUtils.getDescendingList();
        refreshView(descendingList);
        List<Ant> groupByList = DbUtils.getAntGroupByList();
        List<Ant> havingList = DbUtils.getAntHavingList();
        List<Ant> limitList = DbUtils.getAntWithLimitAndOffset();
        List<Ant> joinedList = DbUtils.outerJoinAntList();
        refreshView(joinedList);

       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DbFlowTestActivity.this, "After deleting " ,Toast.LENGTH_LONG ).show();
                DbUtils.deleteSomeAnts();
                refreshView(DbUtils.getDescendingList());
            }
        }, 5000);*/


    }

    private void refreshView(List<Ant> antList) {
        this.antList.clear();
        this.antList.addAll(antList);
        adapter.notifyDataSetChanged();
    }
}
