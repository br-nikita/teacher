package com.example.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class select_topic extends AppCompatActivity implements View.OnClickListener{

    Button[] bt = new Button[20];
    String action;
    Drawable green;
    Drawable yellow;
    DataBaseHelper DBHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    Cursor userCursor2;
    int check=0;
    int selected_class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_topic);
        Context context=getApplicationContext();
        green= AppCompatResources.getDrawable(context,R.drawable.button_green);
        yellow=AppCompatResources.getDrawable(context,R.drawable.button_yellow);
        bt[0]=findViewById(R.id.bt1);
        bt[1]=findViewById(R.id.bt2);
        bt[2]=findViewById(R.id.bt3);
        bt[3]=findViewById(R.id.bt4);
        bt[4]=findViewById(R.id.bt5);
        bt[5]=findViewById(R.id.bt6);
        bt[6]=findViewById(R.id.bt7);
        bt[7]=findViewById(R.id.bt8);
        bt[8]=findViewById(R.id.bt9);
        bt[9]=findViewById(R.id.bt10);
        bt[10]=findViewById(R.id.bt11);
        bt[11]=findViewById(R.id.bt12);
        bt[12]=findViewById(R.id.bt13);
        bt[13]=findViewById(R.id.bt14);
        bt[14]=findViewById(R.id.bt15);
        bt[15]=findViewById(R.id.bt16);
        bt[16]=findViewById(R.id.bt17);
        bt[17]=findViewById(R.id.bt18);
        bt[18]=findViewById(R.id.bt19);
        bt[19]=findViewById(R.id.bt20);
        bt[0].setOnClickListener(this);
        bt[1].setOnClickListener(this);
        bt[2].setOnClickListener(this);
        bt[3].setOnClickListener(this);
        bt[4].setOnClickListener(this);
        bt[5].setOnClickListener(this);
        bt[6].setOnClickListener(this);
        bt[7].setOnClickListener(this);
        bt[8].setOnClickListener(this);
        bt[9].setOnClickListener(this);
        bt[10].setOnClickListener(this);
        bt[11].setOnClickListener(this);
        bt[12].setOnClickListener(this);
        bt[13].setOnClickListener(this);
        bt[14].setOnClickListener(this);
        bt[15].setOnClickListener(this);
        bt[16].setOnClickListener(this);
        bt[17].setOnClickListener(this);
        bt[18].setOnClickListener(this);
        bt[19].setOnClickListener(this);
        Intent intent = getIntent();
        action = intent.getAction();
        if(action.equals("teacher.android.intent.action.olympiad_select_variant")) {
            Bundle arg = getIntent().getExtras();
            selected_class = (int) arg.get("selected_class");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        DBHelper = new DataBaseHelper(this);
        db = DBHelper.open();
        Intent intent = getIntent();
        action = intent.getAction();
        //Отображение тем/////////////////////////////////////////////////////
        //При выборе 1 класса
        if (action.equals("teacher.android.intent.action.class1")) {
            bt[0].setText("1. Язык и речь");
            bt[1].setText("2. Текст, предложение, диалог");
            bt[2].setText("3. Слово");
            bt[3].setText("4. Слово и слог");
            bt[4].setText("5. Перенос слов");
            bt[5].setText("6. Ударение");
            bt[6].setText("7. Звуки и буквы");
            bt[7].setText("8. Русский алфавит");
            bt[8].setText("9. Гласные звуки");
            bt[9].setText("10. Согласные звуки");
            bt[10].setText("11. Буквы Й и И");
            bt[11].setText("12. Твёрдые и мягкие согласные звуки");
            bt[12].setText("13. Мягкий знак (ь)");
            bt[13].setText("14. Глухие и звонкие согласные звуки");
            bt[14].setText("15. Шипящие согласные звуки");
            bt[15].setText("16. Буквосочетания ЧК, ЧН, ЧТ");
            bt[16].setText("17. Буквосочетания ЖИ-ШИ, ЧА-ЩА, ЧУ-ЩУ");
            bt[17].setText("18. Заглавная буква в словах");
            for(int i=18;i<20;i++) {
                bt[i].setVisibility(View.GONE);
            }
            selected_class = 1;
            check=1;
        }
        //При выборе 2 класса
        if (action.equals("teacher.android.intent.action.class2")) {
            bt[0].setText("1. Виды речи");
            bt[1].setText("2. Диалог и монолог");
            bt[2].setText("3. Текст");
            bt[3].setText("4. Предложение");
            bt[4].setText("5. Члены предложения");
            bt[5].setText("6. Слово и его значение");
            bt[6].setText("7. Однокоренные слова");
            bt[7].setText("8. Звуки и буквы");
            bt[8].setText("9. Русский алфавит");
            bt[9].setText("10. Гласные звуки");
            bt[10].setText("11. Согласные звуки");
            bt[11].setText("12. Мягкий знак (ь)");
            bt[12].setText("13. Правописание буквосочетаний с шипящими звуками");
            bt[13].setText("14. Звонкие и глухие согласные звуки");
            bt[14].setText("15. Разделительный мягкий знак");
            bt[15].setText("16. Части речи");
            bt[16].setText("17. Глагол");
            bt[17].setText("18. Имя прилагательное");
            bt[18].setText("19. Местоимение");
            bt[19].setText("20. Предлоги");
            selected_class = 2;
            check=1;
        }
        //При выборе 3 класса
        if (action.equals("teacher.android.intent.action.class3")) {
            bt[0].setText("1. Имя существительное");
            bt[1].setText("2. Имя прилагательное");
            bt[2].setText("3. Местоимение");
            bt[3].setText("4. Глагол");
            bt[4].setText("5. ");
            bt[5].setText("6. ");
            bt[6].setText("7. ");
            bt[7].setText("8. ");
            bt[8].setText("9. ");
            bt[9].setText("10. ");
            bt[10].setText("11. ");
            bt[11].setText("12. ");
            bt[12].setText("13. ");
            bt[13].setText("14. ");
            bt[14].setText("15. ");
            bt[15].setText("16. ");
            bt[16].setText("17. ");
            bt[17].setText("18. ");
            for(int i=4;i<20;i++) {
                bt[i].setVisibility(View.GONE);
            }
            selected_class = 3;
            check=1;
        }
        //При выборе 4 класса
        if (action.equals("teacher.android.intent.action.class4")) {
            bt[0].setText("1. Текст");
            bt[1].setText("2. Предложение");
            bt[2].setText("3. Словосочетание");
            bt[3].setText("4. Однородные члены предложения");
            bt[4].setText("5. Знаки препинания");
            bt[5].setText("6. Простые и сложные предложения");
            bt[6].setText("7. Лексическое значение слова");
            bt[7].setText("8. Состав слова");
            bt[8].setText("9. Части речи");
            bt[9].setText("10. Изменение по падежам имён существительных");
            bt[10].setText("11. Три склонения имён существительных");
            bt[11].setText("12. Правописание безударных падежных окончаний");
            bt[12].setText("13. ");
            bt[13].setText("14. ");
            bt[14].setText("15. ");
            bt[15].setText("16. ");
            bt[16].setText("17. ");
            bt[17].setText("18. ");
            for(int i=12;i<20;i++) {
                bt[i].setVisibility(View.GONE);
            }
            selected_class = 4;
            check=1;
        }
        //при выборе впр
        if (action.equals("teacher.android.intent.action.vpr")) {
            bt[0].setText("1 Вариант");
            bt[1].setText("2 Вариант");
            bt[2].setText("3 Вариант");
            bt[3].setText("4 Вариант");
            bt[4].setText("5 Вариант");
            bt[5].setText("6 Вариант");
            bt[6].setText("7 Вариант");
            for(int i=5;i<20;i++) {
                bt[i].setVisibility(View.GONE);
            }
            check=2;
        }
        //при выборе подготовка к впр
        if (action.equals("teacher.android.intent.action.vpr_prepare")) {
            bt[0].setVisibility(View.GONE);
            bt[1].setText("№2. Алфавит");
            bt[2].setText("№3. Глухой-звонкий согласный");
            bt[3].setText("№4. Твёрдый-мягкий согласный");
            bt[4].setText("№5. Разделение слов на слоги");
            bt[5].setText("№6. Перенос слов");
            bt[6].setText("№7. Составление предложений");
            for(int i=7;i<20;i++) {
                bt[i].setVisibility(View.GONE);
            }
            check=3;
        }
        //при выборе олимпиады 1 класс
        if (action.equals("teacher.android.intent.action.olympiad_select_variant") && selected_class==1){
            bt[0].setText("1 Вариант");
            for(int i=1;i<20;i++) {
                bt[i].setVisibility(View.GONE);
            }
            check=4;
        }
        //при выборе олимпиады остальные классы
        if (action.equals("teacher.android.intent.action.olympiad_select_variant") && selected_class>1){
            bt[0].setText("1 Вариант");
            bt[1].setText("2 Вариант");
            bt[2].setText("3 Вариант");
            bt[3].setText("4 Вариант");
            bt[4].setText("5 Вариант");
            for(int i=3;i<20;i++) {
                bt[i].setVisibility(View.GONE);
            }
            check=4;
        }
        //////////////////////////////////////////////////////////////////////////////////////
        //покраска кнопок с темами в зависимости от прогресса
        if(check==1){
            //если тест сдан, то progress=1,кнопка красится в зелёный
            //если тест не сдан, то кнопка красится в жёлтый и показывается прогресс изучения в процентах
            userCursor = db.rawQuery("select progress_test from theory_tests where class=?", new String[]{Integer.toString(selected_class)});
            userCursor2 = db.rawQuery("select progress from theory where class=?", new String[]{Integer.toString(selected_class)});
            if (userCursor.getCount() > 0) {
                userCursor.moveToFirst();
                userCursor2.moveToFirst();
                int i = 0;
                while (!userCursor.isAfterLast()) {
                    if (userCursor.getInt(0) == 2) {
                        //bt[i].setBackgroundColor(Color.GREEN);
                        bt[i].setBackground(green);
                        bt[i].setTextColor(Color.WHITE);
                    }
                    else if (userCursor.getInt(0) == 1) {
                        bt[i].setBackground(yellow);
                        bt[i].setTextColor(Color.BLACK);
                        if (userCursor2.getInt(0) >0) {
                            int p;//% прогресса
                            String s;
                            p=userCursor2.getInt(0);
                            if(p>0) {
                                s = bt[i].getText().toString();
                                s = s + "\nПрогресс изучения: " + p + " %";
                                bt[i].setText(s);
                            }
                        }
                    }
                    i += 1;
                    userCursor2.moveToNext();
                    userCursor.moveToNext();
                }
            }
            userCursor.close();
            userCursor2.close();
            db.close();
        }
    }

    @Override
    public void onClick(View v) {
        //переход по темам
        Intent intent;
        //Выбор темы
        int selected_topic=0;
        if (v.getId()==R.id.bt1) {
            selected_topic=1;
        }
        else if(v.getId()==R.id.bt2) {
            selected_topic=2;
        }
        else if(v.getId()==R.id.bt3) {
            selected_topic=3;
        }
        else if(v.getId()==R.id.bt4) {
            selected_topic=4;
        }
        else if(v.getId()==R.id.bt5) {
            selected_topic=5;
        }
        else if(v.getId()==R.id.bt6) {
            selected_topic=6;
        }
        else if(v.getId()==R.id.bt7) {
            selected_topic=7;
        }
        if(check==1)
            intent = new Intent(this, selected_topic.class);
        else if(check==2)
            intent = new Intent(this, vpr.class);
        else if (check==3)
            intent = new Intent(this, vpr_prepare.class);
        else if (check==4)
            intent = new Intent(this, olympiad.class);
        else
            intent = new Intent(this, MainActivity.class);
        intent.putExtra("selected_class", selected_class);
        intent.putExtra("selected_topic", selected_topic);//тема или вариант впр
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