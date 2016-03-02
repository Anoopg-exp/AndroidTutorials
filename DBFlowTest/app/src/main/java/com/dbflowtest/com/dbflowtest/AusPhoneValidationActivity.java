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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by COMP on 20-01-2016.
 */
public class AusPhoneValidationActivity extends AppCompatActivity {

    EditText phoneNumberEditText;
    final String TAG = getClass().getName();
    int validLength;

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
        loadPhoneNumber();
    }

    private void initViews() {
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumber);
    }

    public void loadPhoneNumber() {
        phoneNumberEditText.addTextChangedListener(new TextWatcher() {

            boolean isEditing = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e(TAG, "is back cou :" + count);
                int len = s.length();
                int cursorPos = phoneNumberEditText.getSelectionStart();
                Log.e(TAG, "cursor pos :" + cursorPos);
                if ((cursorPos < len)) {
                    isEditing = true;
                    //String formatted = editFromPosition(cursorPos, len, count, s.toString(), this);
                    phoneNumberEditText.removeTextChangedListener(this);
                    String formatted = validatePhone(s.toString().replaceAll("\\s+", ""), cursorPos, count, isEditing);
                    if (formatted != null)
                        phoneNumberEditText.setText(formatted);
                    else
                        phoneNumberEditText.setText(s.toString());
                    if (phoneNumberEditText.getTag(R.id.phoneNumber) != null) {
                        int curNewPos = (int) phoneNumberEditText.getTag(R.id.phoneNumber);
                        if (phoneNumberEditText.getText().length() >= curNewPos)
                            phoneNumberEditText.setSelection(curNewPos);
                        phoneNumberEditText.setTag(R.id.phoneNumber, null);
                    } else {
                        if (phoneNumberEditText.getText().length() > cursorPos)
                            phoneNumberEditText.setSelection(cursorPos);
                    }
                    phoneNumberEditText.addTextChangedListener(this);

                } else {
                    isEditing = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!isEditing) {
                    phoneNumberEditText.removeTextChangedListener(this);
                    Log.e(TAG, "EDITABLE string :" + s.toString());
                    Log.e(TAG, "EDITABLE length :" + s.length());
                    int cursorPos = phoneNumberEditText.getSelectionStart();
                    String edited = validatePhone(s.toString().replaceAll("\\s+", ""), cursorPos, 0, isEditing);
                    s.clear();
                    s.append(edited);
                    if (phoneNumberEditText.getTag(R.id.phoneNumber) != null) {
                        int curNewPos = (int) phoneNumberEditText.getTag(R.id.phoneNumber);
                        if (phoneNumberEditText.getText().length() > curNewPos)
                            phoneNumberEditText.setSelection(curNewPos);
                        phoneNumberEditText.setTag(R.id.phoneNumber, null);
                    } else {
                        if (phoneNumberEditText.getText().length() > cursorPos)
                            phoneNumberEditText.setSelection(cursorPos);
                    }
                    phoneNumberEditText.addTextChangedListener(this);
                }
            }
        });
    }


    private String validatePhone(String input, int curPos, int count, boolean isEditing) {

        if (input.length() > 3) {
            Log.e(TAG, "trimmed val :" + input.substring(0, 2));
            Log.e(TAG, "Cursor pos :" + curPos);
            switch (input.substring(0, 2)) {
                case "04":
                    //setEditTextMaxLength(12);
                    //setEditTextLength(12);
                    if (isEditing)
                        return getEditedSubString(input, 4, 8, curPos, count);
                    else
                        return getSubString(input, 4, 8, curPos);
                case "01":
                case "02":
                case "03":
                case "08":
                    //setEditTextMaxLength(12);
                    //setEditTextLength(12);
                    if (isEditing)
                        return getEditedSubString(input, 2, 7, curPos, count);
                    else
                        return getSubString(input, 2, 7, curPos);
            }

            if (input.startsWith(("+61"))) {
                switch (input.substring(0, 4)) {
                    case "+611":
                    case "+612":
                    case "+613":
                    case "+618":
                        //  setEditTextMaxLength(15);
                        //setEditTextLength(15);
                        if (isEditing)
                            return getEditedSubString(input, 3, 5, 10, curPos, count);
                        else
                            return getSubString(input, 3, 5, 10, curPos);
                    case "+614":
                        //setEditTextMaxLength(15);
                        //setEditTextLength(15);
                        if (isEditing)
                            return getEditedSubString(input, 3, 7, 11, curPos, count);
                        else
                            return getSubString(input, 3, 7, 11, curPos);
                }

                if (isEditing)
                    return getString(input, count, curPos);
            }
        }

        return input;
    }

    private String editFromPosition(int curPos, int len, int count, String withSpace, TextWatcher watcher) {

        String formatted = null;
        String input = withSpace.toString().replaceAll("\\s+", "");

        if (input.length() > 3) {
            Log.e(TAG, "trimmed val :" + input.substring(0, 2));
            Log.e(TAG, "Cursor pos :" + curPos);
            switch (input.substring(0, 2)) {
                case "04":
                    //setEditTextMaxLength(12);
                    formatted = getEditedSubString(input, 4, 8, curPos, count);
                    break;
                case "01":
                case "02":
                case "03":
                case "08":
                    //setEditTextMaxLength(12);
                    formatted = getEditedSubString(input, 2, 7, curPos, count);
                    break;
            }

            if (input.startsWith(("+61"))) {
                switch (input.substring(0, 4)) {
                    case "+612":
                    case "+613":
                    case "+618":
                        //  setEditTextMaxLength(15);
                        formatted = getEditedSubString(input, 3, 5, 10, curPos, count);
                        break;
                    case "+614":
                        //setEditTextMaxLength(15);
                        formatted = getEditedSubString(input, 3, 7, 11, curPos, count);
                        break;
                }
            }
        }

        return formatted;
    }

    private String getString(String concatString, int count, int curPos) {

        if (curPos != 0)
            phoneNumberEditText.setTag(R.id.phoneNumber, curPos - 1);

        return concatString;
    }


    private String getSubString(String concatString, int start, int end, int curPos) {
        String trim = concatString.replaceAll("\\s+", "");
        StringBuilder builder = new StringBuilder(trim);

        if (builder.length() > start) {
            builder.insert(start, " ");
            curPos++;
        }

        if (builder.length() > end) {
            builder.insert(end, " ");
            //curPos++;
        }

        phoneNumberEditText.setTag(R.id.phoneNumber, curPos);
        return builder.toString();
    }


    private String getSubString(String concatString, int start, int middle, int end, int curPos) {
        String trim = concatString.replaceAll("\\s+", "");
        StringBuilder builder = new StringBuilder(trim);

        if (builder.length() > start) {
            builder.insert(start, " ");
            curPos++;
        }

        if (builder.length() > middle) {
            builder.insert(middle, " ");
            // curPos++;
        }

        if (builder.length() > end)
            builder.insert(end, " ");

        phoneNumberEditText.setTag(R.id.phoneNumber, curPos);
        return builder.toString();
    }

    private String getEditedSubString(String concatString, int start, int end, int curPos, int count) {
        String trim = concatString.replaceAll("\\s+", "");
        StringBuilder builder = new StringBuilder(trim);

        if (builder.length() > start) {
            builder.insert(start, " ");
            //curPos++;
        }

        if (builder.length() > end)
            builder.insert(end, " ");

        if (builder.length() > curPos) {
            String sub = String.valueOf(builder.toString().charAt(curPos - 1));
            if (sub.equals(" ") && count != 0) {
                curPos = curPos + 1;
                phoneNumberEditText.setTag(R.id.phoneNumber, curPos);
            }

        }

        return builder.toString();
    }

    private String getEditedSubString(String concatString, int start, int middle, int end, int curPos, int count) {
        String trim = concatString.replaceAll("\\s+", "");
        StringBuilder builder = new StringBuilder(trim);

        if (builder.length() > start) {
            builder.insert(start, " ");
        }

        if (builder.length() > middle) {
            builder.insert(middle, " ");
        }

        if (builder.length() > end)
            builder.insert(end, " ");


        if (builder.length() > curPos) {
            String sub = String.valueOf(builder.toString().charAt(curPos - 1));
            if (sub.equals(" ") && count != 0) {
                curPos = curPos + 1;
                phoneNumberEditText.setTag(R.id.phoneNumber, curPos);
            }

        }

        return builder.toString();
    }

    public void savePhoneNum(View view) {

        String num = phoneNumberEditText.getText().toString();
        String replace = num.replaceAll("\\s+", "");
        if (!validFormat(num, replace)) {
            //phoneNumberEditText.setText(replace);
            Toast.makeText(this, "Invalid Phone number", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Phone no succeed", Toast.LENGTH_SHORT).show();
        }


    }

    public void setEditTextMaxLength(int length) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        phoneNumberEditText.setFilters(FilterArray);
    }


    private void setEditTextLength(int validLength) {

        this.validLength = validLength;
    }

    private int getValidLength() {

        return this.validLength;
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


}
