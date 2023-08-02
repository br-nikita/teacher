package com.example.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class vpr_prepare extends AppCompatActivity implements View.OnClickListener {
    Button bt1;
    TextView text;
    int selected_topic;
    DataBaseHelper DBHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vpr_prepare);
        DBHelper = new DataBaseHelper(getApplicationContext());
        db = DBHelper.open();
        getSupportActionBar().setTitle("Подготовка к ВПР");
        Bundle arg = getIntent().getExtras();
        selected_topic=(int)arg.get("selected_topic");
        text=findViewById(R.id.textView);
        text.setTextColor(Color.BLACK);
        bt1 = findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
        if(selected_topic==2){
            userCursor = db.rawQuery("select text from vpr_theory where type=2",null);
            userCursor.moveToFirst();
            String s = userCursor.getString(0);
            text.setText(s);
        }
        if(selected_topic==3){
            userCursor = db.rawQuery("select text from vpr_theory where type=3",null);
            userCursor.moveToFirst();
            String s = userCursor.getString(0);
            text.setText(s);
        }
        if(selected_topic==4){
            userCursor = db.rawQuery("select text from vpr_theory where type=4",null);
            userCursor.moveToFirst();
            String s = userCursor.getString(0);
            text.setText(s);
        }
        if(selected_topic==5){
            userCursor = db.rawQuery("select text from vpr_theory where type=5",null);
            userCursor.moveToFirst();
            String s = userCursor.getString(0);
            text.setText(s);
        }
        if(selected_topic==6){
            userCursor = db.rawQuery("select text from vpr_theory where type=6",null);
            userCursor.moveToFirst();
            String s = userCursor.getString(0);
            text.setText(s);
        }
        if(selected_topic==7){
            userCursor = db.rawQuery("select text from vpr_theory where type=7",null);
            userCursor.moveToFirst();
            String s = userCursor.getString(0);
            text.setText(s);
        }

    }
    private void Back() {
        Intent intent = new Intent("teacher.android.intent.action.vpr_prepare");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.bt1) {
            Back();
        }
    }
    //Меню
    private void goto_main() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
    private void open_faq() {
        Intent intent = new Intent(this, about.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.goto_main) {
            goto_main();
            return true;
        }
        if(id==R.id.open_faq) {
            open_faq();
            return true;
        }
        return true;
    }
}