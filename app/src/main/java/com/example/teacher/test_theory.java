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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class test_theory extends AppCompatActivity implements View.OnClickListener{

    DataBaseHelper DBHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    Cursor userCursor2;
    Button bt1;
    Button bt2;
    int selected_class;
    int selected_topic;
    TextView[] text = new TextView[10];
    EditText[] edit = new EditText[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_theory);
        getSupportActionBar().setTitle("Проверка знаний");
        DBHelper = new DataBaseHelper(getApplicationContext());
        db = DBHelper.open();
        Bundle arg = getIntent().getExtras();
        selected_class=(int)arg.get("selected_class");
        selected_topic=(int)arg.get("selected_topic");
        bt1 = findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
        bt2 = findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
        text[0] = findViewById(R.id.line1);
        text[1] = findViewById(R.id.line2);
        text[2] = findViewById(R.id.line3);
        text[3] = findViewById(R.id.line4);
        text[4] = findViewById(R.id.line5);
        text[5] = findViewById(R.id.line6);
        edit[0] = findViewById(R.id.edit1);
        edit[1] = findViewById(R.id.edit2);
        edit[2] = findViewById(R.id.edit3);
        edit[3] = findViewById(R.id.edit4);
        edit[4] = findViewById(R.id.edit5);
        edit[5] = findViewById(R.id.edit6);

        userCursor = db.rawQuery("select question,answer,status from tests_content,theory_tests where theory_tests.class=? and theory_tests.topic =? and theory_tests.id_test=tests_content.id_test order by number_question",
                new String[]{Integer.toString(selected_class),Integer.toString(selected_topic)});
        //заполнение вопросов
        if (userCursor.getCount() > 0) {
            userCursor.moveToFirst();
            int i = 0;
            String question,answer;
            int status;
            while (!userCursor.isAfterLast()) {
                question = userCursor.getString(0);
                answer = userCursor.getString(1);
                status = userCursor.getInt(2);
                if (text[i].getText() == "") {
                    text[i].setText(question);
                }
                if (status == 1) {
                    text[i].setTextColor(Color.parseColor("#009706"));
                    edit[i].setTextColor(Color.parseColor("#009706"));
                    edit[i].setText(answer);
                    edit[i].setFocusable(false);
                }
                i += 1;
                userCursor.moveToNext();
            }
        }
        for(int i=0;i<6;i++)
        {
            if(text[i].getText() == ""){
                text[i].setVisibility(View.GONE);
                edit[i].setVisibility(View.GONE);
            }
        }

    }

    //Выполняется при нажатии клавиши "Проверить ответы".
    //проверяются данные ответы, если все правильные, то высвечивается кнопка "Закончить тестирование"
    public void End() {
        userCursor2=db.rawQuery("select id_test from theory_tests where theory_tests.class=? and theory_tests.topic =?",
                new String[]{Integer.toString(selected_class),Integer.toString(selected_topic)});
        userCursor2.moveToFirst();
        int c=userCursor2.getInt(0);
        userCursor.moveToFirst();
        if (userCursor.getCount() > 0) {
            userCursor.moveToFirst();
            int i = 0;
            int counter_right_answer=userCursor.getCount();
            String answer;
            while (!userCursor.isAfterLast()) {
                answer = userCursor.getString(1);
                if (edit[i].getText().toString().equals(answer)) {
                    counter_right_answer-=1;
                    text[i].setTextColor(Color.parseColor("#009706"));
                    edit[i].setTextColor(Color.parseColor("#009706"));
                    db.execSQL("update tests_content set status=1 where number_question=? and id_test=?",
                            new String[] {Integer.toString(i+1), Integer.toString(c)});
                    edit[i].setFocusable(false);
                }
                else {
                    text[i].setTextColor(Color.RED);
                    edit[i].setTextColor(Color.RED);
                }
                i += 1;
                userCursor.moveToNext();
            }
            if(counter_right_answer==0) {//прогресс изучения темы (для окраски кнопок)
                db.execSQL("update theory_tests set progress_test=2 where class=? and topic =?",
                        new String[] {Integer.toString(selected_class),Integer.toString(selected_topic)});
                Toast.makeText(getApplicationContext(), "Тест сдан!", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getApplicationContext(), "Тест не сдан! Неправильные ответы выделены красным! Внимательно прочитай тему и попробуй пройти тест ещё раз.", Toast.LENGTH_LONG).show();
            bt2.setVisibility(View.VISIBLE);
        }

        //db.close();
        //userCursor.close();
    }

    private void Back() {
        Intent intent = new Intent(this, select_topic.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    //реакция на нажатие кнопок
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt1) {
            End();
        }
        if (v.getId() == R.id.bt2) {
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
