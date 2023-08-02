package com.example.teacher;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LineAdapter  extends RecyclerView.Adapter<LineAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Line> lines;

    LineAdapter(Context context, List<Line> lines) {
        this.lines = lines;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public LineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LineAdapter.ViewHolder holder, int position) {
        Line line = lines.get(position);
        holder.variantView.setText(line.getVariant());
        holder.numberView.setText(line.getNumber());
        holder.answerView.setText(line.getAnswer());
        holder.right_answerView.setText(line.getRight_answer());
        if(line.getVariant().equals("Вариант"))
        {
            holder.variantView.setTextColor(Color.BLACK);
            holder.numberView.setTextColor(Color.BLACK);
            holder.answerView.setTextColor(Color.BLACK);
            holder.right_answerView.setTextColor(Color.BLACK);
        }
        else if(line.getAnswer().equals(line.getRight_answer())) {
            holder.variantView.setTextColor(Color.parseColor("#009706"));
            holder.numberView.setTextColor(Color.parseColor("#009706"));
            holder.answerView.setTextColor(Color.parseColor("#009706"));
            holder.right_answerView.setTextColor(Color.parseColor("#009706"));
        }
        else {
            holder.variantView.setTextColor(Color.RED);
            holder.numberView.setTextColor(Color.RED);
            holder.answerView.setTextColor(Color.RED);
            holder.right_answerView.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView variantView, numberView, answerView, right_answerView;
        ViewHolder(View view){
            super(view);
            variantView = view.findViewById(R.id.variant);
            numberView = view.findViewById(R.id.number);
            answerView = view.findViewById(R.id.answer);
            right_answerView = view.findViewById(R.id.right_answer);
        }
    }
}