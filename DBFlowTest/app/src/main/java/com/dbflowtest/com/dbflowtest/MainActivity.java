package com.dbflowtest.com.dbflowtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dbflowtest.com.dbflowtest.Supporter.DbUtils;
import com.dbflowtest.com.dbflowtest.models.Ant;
import com.dbflowtest.com.dbflowtest.models.Colony;
import com.dbflowtest.com.dbflowtest.models.Queen;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView title1;
    TextView title2;
    TextView title3;
    EditText phoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        initViews();
        // initManagers();
        loadPhoneNumber();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        title1 = (TextView) findViewById(R.id.main_title1);
        title2 = (TextView) findViewById(R.id.main_title2);
        title3 = (TextView) findViewById(R.id.main_title3);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumber);
    }


    private void loadPhoneNumber() {

        phoneNumberEditText.addTextChangedListener(new TextWatcher() {

            boolean validNumber;
            int preLength = 0;
            String lastWord;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("tag", "before txt changed called chara :" + s);
                Log.i("tag", "before txt changed called start:" + start);
                Log.i("tag", "before txt changed called count:" + count);
                Log.i("tag", "before txt changed called after:" + after);
                if (after == 0) {
                    lastWord = String.valueOf(s.toString().charAt(start));
                    Log.i("tag", "last :" + lastWord);
                    preLength = start;
                }

                int len = s.length();
                int cursorPos = phoneNumberEditText.getSelectionStart();

                //Removing last word if empty space in back press...
                if (lastWord != null) {
                    if (lastWord.equals(" ") && ((cursorPos == len))) {
                        phoneNumberEditText.removeTextChangedListener(this);
                        String edit = phoneNumberEditText.getText().toString();
                        phoneNumberEditText.setText(edit.substring(0, preLength - 1));
                        phoneNumberEditText.setSelection(preLength - 1);
                        lastWord = null;
                        phoneNumberEditText.addTextChangedListener(this);
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("tag", "on txt changed called chara:" + s);
                Log.i("tag", "on txt changed called start:" + start);
                Log.i("tag", "on txt changed called before:" + before);
                Log.i("tag", "on txt changed called count:" + count);

                int len = s.length();
                int cursorPos = phoneNumberEditText.getSelectionStart();

                if ((cursorPos < len) && (count != 0))
                    editFromPosition(cursorPos, len, count, s.toString(), this);

                if (s.toString().startsWith("04")) {
                    validNumber = true;
                    setEditTextMaxLength(12);
                    if ((len == 4) || (len == 8)) {
                        appendSpace(count, this);
                    }
                } else if (s.toString().startsWith("01") || s.toString().startsWith("02") ||
                        s.toString().startsWith("03") || s.toString().startsWith("08")) {
                    validNumber = true;
                    setEditTextMaxLength(12);
                    if ((len == 2) || (len == 7)) {
                        appendSpace(count, this);
                    }
                } else if (s.toString().startsWith("+61")) {
                    setEditTextMaxLength(15);
                    if (len == 3)
                        appendSpace(count, this);

                    if (s.toString().startsWith("+61 3") || s.toString().startsWith("+61 1")
                            || s.toString().startsWith("+61 2") || s.toString().startsWith("+61 8")) {
                        validNumber = true;
                        if ((len == 5) || (len == 10)) {
                            appendSpace(count, this);
                        }
                    } else if (s.toString().startsWith("+61 4")) {
                        validNumber = true;
                        if ((len == 7) || (len == 11)) {
                            appendSpace(count, this);
                        }
                    }

                } else {
                    setEditTextMaxLength(15);
                    validNumber = false;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
               /* if (!validNumber)
                    phoneNumberEditText.setError("Invalid number format");*/
            }
        });

    }

    private void appendSpace(int count, TextWatcher watcher) {

        if (count != 0) {
            phoneNumberEditText.removeTextChangedListener(watcher);
            phoneNumberEditText.append(" ");
            phoneNumberEditText.addTextChangedListener(watcher);
        }
    }

    private void appendSpace(String val, int cursorPos, int pos, int secondPos, TextWatcher watcher) {
        phoneNumberEditText.removeTextChangedListener(watcher);
        StringBuilder s = new StringBuilder(val);

        if (s.length() > pos) {
            s.insert(pos, " ");
            if (s.toString().startsWith("+61")) {
                if ((cursorPos == pos + 2))
                    cursorPos++;
            } else if (s.toString().startsWith("0")) {
                if (cursorPos == pos + 1)
                    cursorPos++;
            }
        }

        if (val.length() > secondPos) {
            s.insert(secondPos, " ");
            if (s.toString().startsWith("+61")) {
                if (cursorPos == secondPos + 2)
                    cursorPos++;
            } else if (s.toString().startsWith("0")) {
                if (cursorPos == secondPos + 1)
                    cursorPos++;
            }
        }

        if (s.toString().startsWith("+61")) {
            s.insert(3, " ");
            if ((s.toString().startsWith("+61 3") || s.toString().startsWith("+61 4") || s.toString().startsWith("+61 1")
                    || s.toString().startsWith("+61 2") || s.toString().startsWith("+61 8"))) {
                if (cursorPos == 4)
                    cursorPos++;
            }
            /*if (cursorPos == 4)
                cursorPos++;*/
            //cursorPos++;
        }

        phoneNumberEditText.setText(s.toString());
        phoneNumberEditText.setSelection(cursorPos);
        phoneNumberEditText.addTextChangedListener(watcher);
    }

    private void removeAllSpace(String s, TextWatcher watcher, int cursorPos) {
        String withoutSpace = s.replaceAll("\\s+", "");
        phoneNumberEditText.removeTextChangedListener(watcher);
        phoneNumberEditText.setText(withoutSpace);
        phoneNumberEditText.setSelection(cursorPos);
        phoneNumberEditText.addTextChangedListener(watcher);
    }

    private void editFromPosition(int cursorPos, int len, int count, String s, TextWatcher watcher) {
        Log.i("tag", "on txt changed called editing from middle ");
        Log.i("tag", "on txt changed called cursor pos:" + cursorPos);
        String withoutSpace = null;
        Log.i("tag", " replacing space :" + s.replaceAll("\\s+", ""));
        withoutSpace = s.replaceAll("\\s+", "");

        if (s.toString().startsWith("04")) {
            setEditTextMaxLength(12);
            // if ((cursorPos == 4) || (cursorPos == 8)) {
            //if (!validFormat(s.toString(), "04") && (len == 12))
            appendSpace(withoutSpace, cursorPos, 4, 8, watcher);
            //  }
        } else if (s.toString().startsWith("01") || s.toString().startsWith("02") ||
                s.toString().startsWith("03") || s.toString().startsWith("08")) {
            setEditTextMaxLength(12);
            // if ((cursorPos == 2) || (cursorPos == 7)) {
            //if (!validFormat(s.toString(), "03") && (len == 12))
            appendSpace(withoutSpace, cursorPos, 2, 7, watcher);
            //}
        } else if (s.toString().startsWith("+61")) {
            setEditTextMaxLength(15);

            if (s.toString().startsWith(""))
                if (len == 3)
                    appendSpace(count, watcher);

            if ((s.toString().startsWith("+61 3")) || s.toString().startsWith("+613") ||
                    (s.toString().startsWith("+61 1")) || s.toString().startsWith("+611") ||
                    (s.toString().startsWith("+61 2")) || s.toString().startsWith("+612") ||
                    (s.toString().startsWith("+61 8")) || s.toString().startsWith("+618")) {
                /*if (!validFormat(s.toString(), "+61 3") || !validFormat(s.toString(), "+613") ||
                        (!validFormat(s.toString(), "+61 2") || !validFormat(s.toString(), "+612")) ||
                        (!validFormat(s.toString(), "+61 1") || !validFormat(s.toString(), "+611")) ||
                        !validFormat(s.toString(), "+61 8") || !validFormat(s.toString(), "+618"))*/
                //if (len == 15)
                appendSpace(withoutSpace, cursorPos, 4, 9, watcher);
            } else if (s.toString().startsWith("+61 4") || s.toString().startsWith("+614")) {
                //if (!validFormat(s.toString(), "+61 4") || !validFormat(s.toString(), "614")) {
                //if(len ==15)
                appendSpace(withoutSpace, cursorPos, 6, 10, watcher);
                //}
            } else {
                removeAllSpace(s.toString(), watcher, cursorPos);
            }

        } else {
            //setEditTextMaxLength(15);
            removeAllSpace(s.toString(), watcher, cursorPos);
        }
    }

    public void setEditTextMaxLength(int length) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        phoneNumberEditText.setFilters(FilterArray);
    }

    private boolean validFormat(String number, String start) {

        if (start.startsWith("04")) {
            if (number.matches("\\d{4}[\\s]\\d{3}[\\s]\\d{3}")) {
                return true;
            }
        } else if (start.startsWith("03") || start.startsWith("01") || start.startsWith("02")
                || start.startsWith("08")) {
            if (number.matches("\\d{2}[\\s]\\d{4}[\\s]\\d{4}")) {
                return true;
            }
        } else if (start.startsWith("+61 3") || start.startsWith("+613") || start.startsWith("+61 2") || start.startsWith("+612")
                || start.startsWith("+61 1") || start.startsWith("+611") || start.startsWith("+61 8") || start.startsWith("+618")) {
            if (number.matches("\\+\\d{2}[\\s]\\d[\\s]\\d{4}[\\s]\\d{4}")) {
                return true;
            }
        } else if (start.startsWith("+61 4") || start.startsWith("+614")) {
            if (number.matches("\\+\\d{2}[\\s]\\d{3}[\\s]\\d{3}[\\s]\\d{3}")) {
                return true;
            }
        }

        return false;
    }

    private boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;

    }

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
            Ant a = new Ant();
            a.id = i;

            if (i % 2 != 0) {
                a.isMale = true;
                a.type = "mat";
                a.associateQueen(q);
            } else {
                a.isMale = false;
                a.type = "worker";
                a.associateQueen(q1);
            }
            a.save();
        }


        Queen obj = new Queen();
        List<Ant> antsList = DbUtils.getAntsFromTable();

        for (Ant ant : antsList) {
            Log.i("MainActivity", "ant id:" + ant.id);
            // ant.queenForeignKeyContainer.
            Log.i("MainActivity", "ant is male:" + ant.isMale);

            // Log.i("MainActivity", "Queen name is :" + ant.queenForeignKeyContainer.getValue("name"));
            Log.i("MainActivity", "Queen id is :" + DbUtils.getWhichQueen((long) ant.queenForeignKeyContainer.getValue("id")).queen_id);
            Log.i("MainActivity", "Queen name is :" + DbUtils.getWhichQueen((long) ant.queenForeignKeyContainer.getValue("id")).queen_name);
            //  Log.i("MainActivity","Queen is :" + ant.queenForeignKeyContainer.getValue(Ant_Table.queenForeignKeyContainer_id.getContainerKey()));

        }
    }

}
