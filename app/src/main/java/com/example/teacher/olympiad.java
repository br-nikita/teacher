package com.example.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class olympiad extends AppCompatActivity {
    int selected_class;
    int selected_variant;
    DataBaseHelper DBHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    Cursor userCursor2;
    TextView tv1;
    Button bt1;
    Button bt2;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    RadioButton rb5;
    RadioGroup rg;
    ArrayList<Line> lines = new ArrayList<>();
    RecyclerView resultList;
    String []answers = new String[30];
    Spinner sp;
    private ArrayList<String> arr_spinner;
    private ArrayAdapter<String> adapter_spinner;
    int n_task;//номер задания для отображения

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.olympiad);
        getSupportActionBar().setTitle("Олимпиадные задания");
        Bundle arg = getIntent().getExtras();
        selected_class=(int)arg.get("selected_class");
        selected_variant=(int)arg.get("selected_topic");
        DBHelper=new DataBaseHelper(this);
        db=DBHelper.open();
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        tv1 = findViewById(R.id.tv1);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        rg = findViewById(R.id.rg);
        sp = findViewById(R.id.spinner);
        arr_spinner=new ArrayList<String>();
        adapter_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arr_spinner);
        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter_spinner);
        adapter_spinner.notifyDataSetChanged();
        lines.add(new Line ("Вариант","Номер задания","Ваш ответ","Правильный ответ"));
        resultList = findViewById(R.id.resultList);
        LineAdapter lineAdapter = new LineAdapter(this, lines);
        resultList.setAdapter(lineAdapter);
        userCursor = db.rawQuery("select variant,question_number,question,answers,right_answer from olympiad where variant =? and class=? order by id",
                new String[]{Integer.toString(selected_variant),Integer.toString(selected_class)});
        if(userCursor.getCount()>0) {
            userCursor.moveToFirst();
            while (!userCursor.isAfterLast()) {
                int task;
                task = userCursor.getInt(1);
                arr_spinner.add("№" + task);
                userCursor.moveToNext();
            }
        }
        adapter_spinner.notifyDataSetChanged();
        for(int i=0;i<30;i++)
            answers[i]="Ответа нет";
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                check_answer();
                n_task=position+1;
                if(n_task==12)
                    bt2.setVisibility(View.VISIBLE);
                show_task(n_task);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_answer();
                if(n_task<12)
                    n_task+=1;
                if(n_task==12) {
                    bt2.setVisibility(View.VISIBLE);
                }
                show_task(n_task);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                bt1.setVisibility(View.GONE);
                rg.setVisibility(View.GONE);
                tv1.setVisibility(View.GONE);
                sp.setVisibility(View.GONE);
                check_answer();
                userCursor.moveToFirst();
                int i=1;
                while (!userCursor.isAfterLast()) {
                    int v,z;//вариант, № задания
                    String s;
                    v=userCursor.getInt(0);
                    z=userCursor.getInt(1);
                    s=userCursor.getString(4);
                    lines.add(new Line (Integer.toString(v),Integer.toString(z),answers[i],s));
                    i++;
                    userCursor.moveToNext();
                }
               // lineAdapter.notifyDataSetChanged();
                resultList.setVisibility(View.VISIBLE);
            }
        });

    }
    private void show_task(int num_task){
        sp.setSelection(n_task-1);
        userCursor2 = db.rawQuery("select * from olympiad where variant =? and class=? and question_number=?",
                new String[]{Integer.toString(selected_variant), Integer.toString(selected_class), Integer.toString(num_task)});
        if (userCursor2.getCount() > 0) {
            userCursor2.moveToFirst();
            String s;
            int n;
            n=userCursor2.getInt(3);
            s=n+". "+userCursor2.getString(4);
            tv1.setText(s);
            s=userCursor2.getString(5);
            String[] words = s.split("; ");
            rb1.setText(words[0]);
            rb2.setText(words[1]);
            rb3.setText(words[2]);
            rb4.setText(words[3]);
            rb5.setText(words[4]);
        }
    }
    private void check_answer(){
        if(rb1.isChecked()) {answers[n_task]=rb1.getText().toString();}
        if(rb2.isChecked()) {answers[n_task]=rb2.getText().toString();}
        if(rb3.isChecked()) {answers[n_task]=rb3.getText().toString();}
        if(rb4.isChecked()) {answers[n_task]=rb4.getText().toString();}
        if(rb5.isChecked()) {answers[n_task]=rb5.getText().toString();}
        rg.clearCheck();
    }

    //меню
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