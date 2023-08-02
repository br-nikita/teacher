package com.example.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class about extends AppCompatActivity {
TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getSupportActionBar().setTitle("О программе");
        tv1=findViewById(R.id.tv1);
        tv1.setText("Изучение теории\nДанный раздел предназначен для изучения правил по русскому языку. Для доступа к теории" +
                " нужно выбрать класс и тему. После изучения какой-либо темы можно проверить полученные знания. В конце страницы с темой есть кнопка " +
                "\"Проверка знаний\", при нажатии на неё откроется окно с тестом по теме.\n" +
                "Решение олимпиадных заданий\nДанный раздел предназначен для решения олимпиадных заданий, с помощью него можно" +
                " подготовиться к конкурсу \"Русский медвежонок\". Для доступа к заданиям необходимо выбрать класс и вариант.\n" +
                "Подготовка к ВПР\nДанный раздел предназначен для изучения теоретических материалов, необходимых при решении заданий по ВПР.\n" +
                "Решение билетов ВПР\nЭтот раздел используется для подготовки к ВПР. Для доступа к заданиям необходимо выбрать вариант. " +
                "В каждом варианте содержится 6 заданий, по результатам решения программа предлагает рекомендации по повторению теоретических" +
                " материалов.");
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

    public boolean onCreateOptionsMenu(Menu menu) {

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