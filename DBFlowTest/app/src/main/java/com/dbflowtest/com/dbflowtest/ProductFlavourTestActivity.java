package com.dbflowtest.com.dbflowtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by COMP on 02-03-2016.
 */
public class ProductFlavourTestActivity extends Activity {
    String LOG_TAG = getClass().getName();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        textView =(TextView)findViewById(R.id.main_title1);

        textView.setText("Which url ?= "+Hosts.BASE_URL);
        Log.e(LOG_TAG, "Which url ?= "+Hosts.BASE_URL);

    }
}
