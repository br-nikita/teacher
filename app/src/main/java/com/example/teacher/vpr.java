package com.example.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class vpr extends AppCompatActivity implements View.OnClickListener {
    DataBaseHelper DBHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    int selected_variant;
    int number=2;
    int ball2=0,ball3=0,ball4=0,ball5=0,ball6=0,ball7=0;
    String answer2="",answer3="",answer4="",answer5="",answer6="",answer7="";
    String right_answer2="",right_answer3="",right_answer4="",right_answer5="",right_answer6="",right_answer7="";
    Button bt1;
    Button bt2;
    Button bt3;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    EditText[] edit = new EditText[5];
    CheckBox[] cb = new CheckBox[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vpr);
        getSupportActionBar().setTitle("ВПР");
        DBHelper = new DataBaseHelper(getApplicationContext());
        db = DBHelper.open();
        Bundle arg = getIntent().getExtras();
        selected_variant=(int)arg.get("selected_topic");//выбранный вариант
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3 = findViewById(R.id.bt3);
        bt3.setOnClickListener(this);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        cb[0] = findViewById(R.id.cb1);
        cb[1] = findViewById(R.id.cb2);
        cb[2] = findViewById(R.id.cb3);
        cb[3] = findViewById(R.id.cb4);
        cb[4] = findViewById(R.id.cb5);
        edit[0] = findViewById(R.id.edit1);
        edit[1] = findViewById(R.id.edit2);
        edit[2] = findViewById(R.id.edit3);
        edit[3] = findViewById(R.id.edit4);
        edit[4] = findViewById(R.id.edit5);
        task_number();
    }


    //заполнение вопросов
    private void task_number(){

        if (number==2){
            userCursor = db.rawQuery("select question, answers from vpr, vpr_answers where variant =? and type=2 " +
                            "and vpr.id_vpr=vpr_answers.id_vpr", new String[]{Integer.toString(selected_variant)});
            ///////////////////////////////смена отображаемых элементов интерфейса
            for(int i=0;i<5;i++) {
                edit[i].setVisibility(View.VISIBLE);
                cb[i].setVisibility(View.GONE);
            }
            //////////////////////////////
            if (userCursor.getCount() > 0) {
                userCursor.moveToFirst();
                String s;
                s=userCursor.getString(0);
                tv1.setText(s);
                s=userCursor.getString(1);
                tv2.setText(s);
            }
        }
        else if (number==3){
            userCursor = db.rawQuery("select question, answers from vpr, vpr_answers where variant =? and type=3 " +
                    "and vpr.id_vpr=vpr_answers.id_vpr", new String[]{Integer.toString(selected_variant)});
            for(int i=0;i<5;i++) {
                edit[i].setVisibility(View.GONE);
                edit[i].clearFocus();
                cb[i].setVisibility(View.VISIBLE);
            }
            if (userCursor.getCount() > 0) {
                userCursor.moveToFirst();
                String s;
                s=userCursor.getString(0);
                tv1.setText(s);
                s=userCursor.getString(1);
                tv2.setText(s);
                String[] words = s.split(", ");
                cb[0].setText(words[0]);
                cb[1].setText(words[1]);
                cb[2].setText(words[2]);
                cb[3].setText(words[3]);
                cb[4].setText(words[4]);
            }
        }
        else if (number==4){
            userCursor = db.rawQuery("select question, answers from vpr, vpr_answers where variant =? and type=4 " +
                    "and vpr.id_vpr=vpr_answers.id_vpr", new String[]{Integer.toString(selected_variant)});
            for(int i=0;i<5;i++) {
                edit[i].setVisibility(View.GONE);
                cb[i].setVisibility(View.VISIBLE);
            }
            if (userCursor.getCount() > 0) {
                userCursor.moveToFirst();
                String s;
                s=userCursor.getString(0);
                tv1.setText(s);
                s=userCursor.getString(1);
                tv2.setText(s);
                String[] words = s.split(", ");
                cb[0].setText(words[0]);
                cb[1].setText(words[1]);
                cb[2].setText(words[2]);
                cb[3].setText(words[3]);
                cb[4].setText(words[4]);
            }
        }
        else if (number==5){
            for(int i=0;i<4;i++) {
                edit[i].setVisibility(View.VISIBLE);
                edit[i].clearFocus();
            }
            for(int i=0;i<5;i++)
                cb[i].setVisibility(View.GONE);

            userCursor = db.rawQuery("select question, answers from vpr, vpr_answers where variant =? and type=5 " +
                    "and vpr.id_vpr=vpr_answers.id_vpr", new String[]{Integer.toString(selected_variant)});
            if (userCursor.getCount() > 0) {
                userCursor.moveToFirst();
                String s;
                s=userCursor.getString(0);
                tv1.setText(s);
                s=userCursor.getString(1);
                tv2.setText(s);
                String[] words = s.split(", ");
                edit[0].setText(words[0]);
                edit[1].setText(words[1]);
                edit[2].setText(words[2]);
                edit[3].setText(words[3]);
            }
        }
        else if (number==6){
            for(int i=0;i<3;i++) {
                edit[i].setVisibility(View.VISIBLE);
                edit[i].clearFocus();
            }
            for(int i=0;i<5;i++)
                cb[i].setVisibility(View.GONE);
            edit[3].clearFocus();
            edit[4].clearFocus();
            edit[3].setVisibility(View.GONE);
            edit[4].setVisibility(View.GONE);
            userCursor = db.rawQuery("select question, answers from vpr, vpr_answers where variant =? and type=6 " +
                    "and vpr.id_vpr=vpr_answers.id_vpr", new String[]{Integer.toString(selected_variant)});
            if (userCursor.getCount() > 0) {
                userCursor.moveToFirst();
                String s;
                s=userCursor.getString(0);
                tv1.setText(s);
                s=userCursor.getString(1);
                tv2.setText(s);
            }

        }
        else if (number==7) {
            edit[0].setVisibility(View.VISIBLE);
            for (int i = 1; i < 5; i++)
                edit[i].setVisibility(View.GONE);
            for (int i = 0; i < 5; i++) {
                cb[i].setVisibility(View.GONE);
                edit[i].clearFocus();
                cb[i].clearFocus();
            }
            bt1.setVisibility(View.GONE);
            bt2.setVisibility(View.VISIBLE);
            userCursor = db.rawQuery("select question, answers from vpr, vpr_answers where variant =? and type=7 " +
                    "and vpr.id_vpr=vpr_answers.id_vpr", new String[]{Integer.toString(selected_variant)});
            if (userCursor.getCount() > 0) {
                userCursor.moveToFirst();
                String s;
                s = userCursor.getString(0);
                tv1.setText(s);
                s = userCursor.getString(1);
                tv2.setText(s);
            }
        }
    }
    //проверка ответов
    private void checkAnswer(int a){
        DBHelper = new DataBaseHelper(getApplicationContext());
        db = DBHelper.open();
        if(a==2){
            //поиск правильных ответов на задание и сверка с ответами пользователя
            userCursor = db.rawQuery("select right_answer_1, right_answer_2, right_answer_3, right_answer_4, right_answer_5 from vpr, vpr_answers where vpr.variant =? and vpr.type=2 and vpr.id_vpr=vpr_answers.id_vpr",
                          new String[]{Integer.toString(selected_variant)});
            String s;
            userCursor.moveToFirst();
            int count=0;
            for(int i=0;i<5;i++) {
                s = userCursor.getString(i);
                if (edit[i].getText().toString().equals(s)) {
                    count+=1;
                }
                answer2+=edit[i].getText().toString()+", ";
                right_answer2+=s+", ";
            }
            answer2=answer2.substring(0,answer2.length()-2);
            right_answer2=right_answer2.substring(0,right_answer2.length()-2);
            //подсчёт баллов в соответствии с количеством правильных ответов
            if(count==5)
                ball2=2;
            if(count==3)
                ball2=1;
            for(int i=0;i<5;i++)
                edit[i].setText("");
        }
        if(a==3){
            int count_checked=0;//проверка хитрых учеников (выбор более двух ответов).
            userCursor = db.rawQuery("select right_answer_1, right_answer_2 from vpr, vpr_answers " +
                            "where vpr.variant =? and vpr.type=3 and vpr.id_vpr=vpr_answers.id_vpr",
                    new String[]{Integer.toString(selected_variant)});
            String s1,s2;
            userCursor.moveToFirst();
            s1=userCursor.getString(0);
            s2=userCursor.getString(1);
            for(int i=0;i<5;i++)
                if (cb[i].isChecked()) {
                    count_checked+=1;
                    answer3 += cb[i].getText().toString() + ", ";
                    if (cb[i].getText().toString().equals(s1) || cb[i].getText().toString().equals(s2))
                        ball3 += 1;
                }
            if(answer3.length()>2)
                answer3=answer3.substring(0,answer3.length()-2);
            right_answer3=s1+", "+s2;
            for(int i=0;i<5;i++)
                cb[i].setChecked(false);
            if(count_checked==3)
                ball3=ball3-1;
            if(count_checked==4)
                ball3=ball3-2;
            if(count_checked==5)
                ball3=0;
            if(ball3<0)
                ball3=0;
        }
        if(a==4){
            int count_checked=0;
            userCursor = db.rawQuery("select right_answer_1, right_answer_2 from vpr, vpr_answers " +
                            "where vpr.variant =? and vpr.type=4 and vpr.id_vpr=vpr_answers.id_vpr",
                    new String[]{Integer.toString(selected_variant)});
            String s1,s2;
            userCursor.moveToFirst();
            s1=userCursor.getString(0);
            s2=userCursor.getString(1);
            for(int i=0;i<5;i++)
                if (cb[i].isChecked()) {
                    count_checked+=1;
                    answer4 += cb[i].getText().toString() + ", ";
                    if (cb[i].getText().toString().equals(s1) || cb[i].getText().toString().equals(s2))
                        ball4 += 1;
                }
            if(answer4.length()>2)
                answer4=answer4.substring(0,answer4.length()-2);
            right_answer4=s1+", "+s2;
            for(int i=0;i<5;i++)
                cb[i].setChecked(false);
            if(count_checked==3)
                ball4=ball4-1;
            if(count_checked==4)
                ball4=ball4-2;
            if(count_checked==5)
                ball4=0;
            if(ball3<0)
                ball4=0;
        }
        if(a==5){
            userCursor = db.rawQuery("select right_answer_1, right_answer_2, right_answer_3, right_answer_4 " +
                            "from vpr, vpr_answers where vpr.variant =? and vpr.type=5 and vpr.id_vpr=vpr_answers.id_vpr",
                    new String[]{Integer.toString(selected_variant)});
            String s;
            userCursor.moveToFirst();
            int count=0;
            for(int i=0;i<4;i++) {
                s = userCursor.getString(i);
                if (edit[i].getText().toString().equals(s)) {
                    count+=1;
                }
                answer5+=edit[i].getText().toString()+", ";
                right_answer5+=s+", ";
            }
            answer5=answer5.substring(0,answer5.length()-2);
            right_answer5=right_answer5.substring(0,right_answer5.length()-2);
            if(count==4)
                ball5=3;
            if(count==3)
                ball5=2;
            if(count==2)
                ball5=1;
            for(int i=0;i<4;i++)
                edit[i].setText("");
        }
        if(a==6){
            userCursor = db.rawQuery("select right_answer_1, right_answer_2, right_answer_3 " +
                            "from vpr, vpr_answers where vpr.variant =? and vpr.type=6 and vpr.id_vpr=vpr_answers.id_vpr",
                    new String[]{Integer.toString(selected_variant)});
            String s;
            userCursor.moveToFirst();
            int count=0;
            String edit_answer_1, edit_answer_2, edit_answer_3;
            edit_answer_1=edit[0].getText().toString();
            edit_answer_2=edit[1].getText().toString();
            edit_answer_3=edit[2].getText().toString();
            for(int i=0;i<3;i++){
                s = userCursor.getString(i);
                if (edit_answer_1.equals(s) || edit_answer_2.equals(s) || edit_answer_3.equals(s)) {
                    count += 1;
                }
                answer6=edit_answer_1+", "+edit_answer_2+", "+edit_answer_3+", ";
                right_answer6+=s+", ";
            }
            if(answer6.length()>2)
                answer6=answer6.substring(0,answer6.length()-2);
            right_answer6=right_answer6.substring(0,right_answer6.length()-2);
            if(count==3)
                ball6=3;
            if(count==2)
                ball6=2;
            if(count==1)
                ball6=1;
            for(int i=0;i<3;i++)
                edit[i].setText("");
        }
        if(a==7){
            userCursor = db.rawQuery("select right_answer_1, right_answer_2, right_answer_3, right_answer_4 from vpr, vpr_answers where vpr.variant =? " +
                            "and vpr.type=7 and vpr.id_vpr=vpr_answers.id_vpr",
                    new String[]{Integer.toString(selected_variant)});
            String s, s1, s2, s3;
            userCursor.moveToFirst();
            s = userCursor.getString(0);
            s1 = userCursor.getString(1);
            s2 = userCursor.getString(2);
            s3 = userCursor.getString(3);
            if (edit[0].getText().toString().equals(s) || edit[0].getText().toString().equals(s1) ||
                    edit[0].getText().toString().equals(s2) || edit[0].getText().toString().equals(s3)) {
                ball7=1;
            }
            answer7+=edit[0].getText().toString();
            right_answer7+=s;
            if(s1.length()>10)
                right_answer7+=" ИЛИ "+s1;
            if(s2.length()>10)
                right_answer7+=" ИЛИ "+s2;
            if(s3.length()>10)
                right_answer7+=" ИЛИ "+s3;
            edit[0].setText("");
            edit[0].setVisibility(View.GONE);
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

    @Override
    public void onClick(View view) {//переключение заданий
        if(view.getId()==R.id.bt1){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(bt1.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if(number<7) {
                checkAnswer(number);//проверка ответов на задания
                number += 1;
                task_number();//смена задания
            }
        }
        if(view.getId()==R.id.bt2){//подсчёт баллов, вывод правильных ответов и советы по повторению материала
            checkAnswer(number);//проверка ответов на 7 задание
            int ball;
            ball=ball2+ball3+ball4+ball5+ball6+ball7;
            String s;
            s="Набрано "+ ball;
            if(ball==1)
                s+=" балл";
            else if(ball>1 && ball<5)
                s+=" балла";
            else
                s+=" баллов";
            s+=" из 13.";
            tv1.setText(s);
            s="Твой ответ на 2 задание: "+answer2+".\n" +
                    "Правильный ответ на 2 задание: "+right_answer2+".\n" +
                    "Твой ответ на 3 задание: "+answer3+".\n" +
                    "Правильный ответ на 3 задание: "+right_answer3+".\n"+
                    "Твой ответ на 4 задание: "+answer4+".\n" +
                    "Правильный ответ на 4 задание: "+right_answer4+".\n"+
                    "Твой ответ на 5 задание: "+answer5+".\n" +
                    "Правильный ответ на 5 задание: "+right_answer5+".\n"+
                    "Твой ответ на 6 задание: "+answer6+".\n" +
                    "Правильный ответ на 6 задание: "+right_answer6+".\n"+
                    "Твой ответ на 7 задание: "+answer7+"\n" +
                    "Правильный ответ на 7 задание: "+right_answer7;
            tv2.setText(s);
            tv3.setVisibility(View.VISIBLE);
            s="Совет от программы!";
            s=s+"\n Задание №2 (набрано "+ball2+" б. из 2).";
            if(ball2<2)//////////////////советы по повторению теории
                s=s+"\nПовтори алфавит русского языка. Будь внимательнее при расстановке слов. " +
                        "Не забудь выполнить проверку после расстановки всех слов!";
            s=s+"\n Задание №3 (набрано "+ball3+" б. из 2).";
            if(ball3<2)
                s=s+"\nПовтори правило: \"Как отличить звонкие согласные звуки от глухих\"!";
            s=s+"\n Задание №4 (набрано "+ball4+" б. из 2).";
            if(ball4<2)
                s=s+"\nПовтори правило: \"Как отличить мягкие согласные звуки от твёрдых\"!";
            s=s+"\n Задание №5 (набрано "+ball5+" б. из 3).";
            if(ball5<2)
                s=s+"\nПовтори правило: \"Разделение слов на слоги\"!";
            s=s+"\n Задание №6 (набрано "+ball6+" б. из 3).";
            if(ball6<2)
                s=s+"\nПовтори правило: \"Перенос слов\"!";
            s=s+"\n Задание №7 (набрано "+ball7+" б. из 1).";
            if(ball7<1)
                s=s+"Не забывай начинать предложения с заглавной буквы. В конце предложения ставь точку.";
            if(ball==13)
                s="Набран максимальный балл.\nМолодец!!!";
            /////////////////////////////////////////////////////
            tv3.setText(s);
            bt2.setVisibility(View.GONE);
            bt3.setVisibility(View.VISIBLE);
            userCursor.close();
            db.close();
            DBHelper.close();
        }
        if(view.getId()==R.id.bt3) {//выход из активити (появляется после нажатия на кнопку bt2(отвечает за отображение результатов))
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }
}