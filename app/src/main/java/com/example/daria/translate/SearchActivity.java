package com.example.daria.translate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.daria.translate.Tasks.TranslatorTask;


public class SearchActivity extends Activity {

    EditText query;
    Button search;
    public static final String QUERY = "query";
    Context context;
    InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        search = (Button) findViewById(R.id.search_button);
        query = (EditText) findViewById(R.id.word_field);


        search.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = query.getText().toString();
                Intent i = new Intent(context, ResultActivity.class);
                i.putExtra(QUERY, word);
                startActivity(i);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
    }


}
