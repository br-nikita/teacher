package com.example.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
public class MainActivity extends AppCompatActivity{

    DataBaseHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper = new DataBaseHelper(getApplicationContext());
        // создаем базу данных// если создана, то ничего не происходит
        DBHelper.create_db();

        getSupportActionBar().setTitle("Teacher");
        Button bt1;
        Button bt2;
        Button bt3;
        Button bt4;
        TextView tv1;

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        tv1 = findViewById(R.id.tv1);
        String s;
        s="Привет! Я твой личный помощник для обучения русскому языку. Ты можешь изучать теорию, решать " +
                "олимпиадные задания или готовиться к ВПР. Выбери, чем бы ты хотел заняться.\n" +
                "Более подробно обо всех функциях приложения можно прочитать в пункте меню \"О программе\"";
        tv1.setText(s);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("teacher.android.intent.action.select_class");
                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("teacher.android.intent.action.olympiad_select_class");
                startActivity(intent);
            }
        });


        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("teacher.android.intent.action.vpr_prepare");
                startActivity(intent);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vprexampledialog myDialogFragment = new vprexampledialog();
                myDialogFragment.show(getSupportFragmentManager(), "a");
            }
        });

    }
    //обработка нажатий диалогового окна
    public void okClicked() {
        Intent intent = new Intent("teacher.android.intent.action.vpr");//открытие activity select topic для выбора варианта
        startActivity(intent);
    }
    public void cancelClicked() {
        //Intent intent = new Intent("teacher.android.intent.action.vpr");
        //startActivity(intent);
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