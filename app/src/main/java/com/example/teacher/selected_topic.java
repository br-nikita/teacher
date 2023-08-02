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
import android.widget.ScrollView;
import android.widget.TextView;

public class selected_topic extends AppCompatActivity implements View.OnClickListener{
    Button bt1;
    Button bt2;
    ScrollView sv;
    DataBaseHelper DBHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    Cursor userCursor2;
    int selected_class;
    int selected_topic;
    int progress;//процент изучения темы
    TextView [] text=new TextView[15];
    int b;
    Double procent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_topic);
        sv=findViewById(R.id.sv);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        Bundle arg = getIntent().getExtras();
        selected_class=(int)arg.get("selected_class");
        selected_topic=(int)arg.get("selected_topic");
        DBHelper= new DataBaseHelper(this);
        db=DBHelper.open();
        String text_db ;//переменные для получения значений из cursor
        String color ;//переменные для получения значений из cursor
        text[0]=findViewById(R.id.textView);
        text[1] = findViewById(R.id.textView2);
        text[2] = findViewById(R.id.textView3);
        text[3] = findViewById(R.id.textView4);
        text[4] = findViewById(R.id.textView5);
        text[5] = findViewById(R.id.textView6);
        text[6] = findViewById(R.id.textView7);
        text[7] = findViewById(R.id.textView8);
        text[8] = findViewById(R.id.textView9);
        text[9] = findViewById(R.id.textView10);
        userCursor=db.rawQuery("select progress_test from theory_tests,theory where theory.class=? and theory.topic=? and theory_tests.id_theory=theory.id_theory",
                new String[]{Integer.toString(selected_class),Integer.toString(selected_topic)});
        userCursor.moveToFirst();
        if(userCursor.getInt(0)==0) {
            db.execSQL("update theory_tests set progress_test=1 where class=? and topic =?",
                    new String[]{Integer.toString(selected_class), Integer.toString(selected_topic)});
        }
        //Запрос данных по выбранному классу и теме
        userCursor = db.rawQuery("select color, text from theory, theory_parts where theory.class=? and theory.topic=? and theory_parts.id_theory=theory.id_theory",
                new String[]{Integer.toString(selected_class),Integer.toString(selected_topic)});
        //заполнение окна текстом выбранной темы
        if(userCursor.getCount()>0) {
            userCursor.moveToFirst();
            int i = 0;
            while (!userCursor.isAfterLast()) {
                color = userCursor.getString(0);
                text_db = userCursor.getString(1);
                if (text[i].getText() == "") {
                    text[i].setText(text_db);
                }
                if (color.equals("b"))
                    text[i].setTextColor(Color.BLACK);
                if (color.equals("r"))
                    text[i].setTextColor(Color.RED);
                if (color.equals("g"))
                    text[i].setTextColor(Color.parseColor("#009706"));
                i += 1;
                userCursor.moveToNext();
            }
        }
            userCursor.close();
            db.close();
        //скрытие лишних textView
        for(int j=0;j<10;j++) {
            if (text[j].getText() == "") {
                text[j].setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void onResume() {//прогресс изучения темы (для окраски кнопок)
        super.onResume();
        DBHelper= new DataBaseHelper(this);
        db=DBHelper.open();
        sv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                double max;
                max=(sv.getChildAt(0).getHeight()-sv.getHeight())/100.0;
                max=i1/max;
                progress=(int) max;
                getSupportActionBar().setTitle(Integer.toString(progress)+" %");
            }
        });

        sv.post(new Runnable(){
            @Override
            public void run() {
                userCursor=db.rawQuery("select progress from theory where class=? and topic =?",
                        new String[] {Integer.toString(selected_class),Integer.toString(selected_topic)});
                userCursor.moveToFirst();
                procent=(sv.getChildAt(0).getHeight()-sv.getHeight())/100.0;
                procent=procent*(userCursor.getInt(0));
                b=procent.intValue();
                sv.scrollTo(0,b);
            }
        });
        //db.close();
    }
    public void onPause() {//прогресс изучения темы (для окраски кнопок)
        super.onPause();
        DBHelper= new DataBaseHelper(this);
        db=DBHelper.open();
        db.execSQL("update theory set progress=? where class=? and topic =?",
                new String[] {Integer.toString(progress),Integer.toString(selected_class),Integer.toString(selected_topic)});
        db.close();
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.bt1) {
            Back();
        }
        if (v.getId()==R.id.bt2) {
            Test();
        }
    }
    public void Test() {
        Intent intent = new Intent(this, test_theory.class);
        intent.putExtra("selected_class", selected_class);
        intent.putExtra("selected_topic", selected_topic);
        startActivity(intent);
    }

    private void Back() {
        Intent intent = new Intent(this, select_topic.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
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