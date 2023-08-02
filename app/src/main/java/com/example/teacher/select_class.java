package com.example.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class select_class extends AppCompatActivity implements View.OnClickListener {

    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;
    TextView tv1;
    String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_class);

        getSupportActionBar().setTitle("Выбор класса");
        tv1 = findViewById(R.id.tv1);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        Intent intent = getIntent();
        action = intent.getAction();
        //олимпиада
        if(action.equals("teacher.android.intent.action.olympiad_select_class")){
            bt1.setText("1 Класс");
            bt2.setText("2-3 Класс");
            bt3.setText("4-5 Класс");
            bt4.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int selected_class=0;
        if(v.getId() == R.id.bt1 && action.equals("teacher.android.intent.action.olympiad_select_class")){
            selected_class=1;//1 класс
            Intent intent = new Intent("teacher.android.intent.action.olympiad_select_variant");
            intent.putExtra("selected_class", selected_class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.bt1) {
            Intent intent = new Intent("teacher.android.intent.action.class1");
            startActivity(intent);
        }

        if(v.getId() == R.id.bt2 && action.equals("teacher.android.intent.action.olympiad_select_class")){
            selected_class=2;//2-3 класс
            Intent intent = new Intent("teacher.android.intent.action.olympiad_select_variant");
            intent.putExtra("selected_class", selected_class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.bt2) {
            Intent intent = new Intent("teacher.android.intent.action.class2");
            startActivity(intent);
        }

        if(v.getId() == R.id.bt3 && action.equals("teacher.android.intent.action.olympiad_select_class")){
            selected_class=3;//4-5 класс
            Intent intent = new Intent("teacher.android.intent.action.olympiad_select_variant");
            intent.putExtra("selected_class", selected_class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.bt3) {
            Intent intent = new Intent("teacher.android.intent.action.class3");
            startActivity(intent);
        }

        if (v.getId() == R.id.bt4) {
            Intent intent = new Intent("teacher.android.intent.action.class4");
            startActivity(intent);
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