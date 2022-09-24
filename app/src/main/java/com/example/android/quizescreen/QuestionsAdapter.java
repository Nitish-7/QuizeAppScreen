package com.example.android.quizescreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {
    Context context;
    Litsener litsener;

    public QuestionsAdapter(Context context, Litsener litsener) {
        this.context = context;
        this.litsener = litsener;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.question_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.optionA.getBackground().setTint(context.getResources().getColor(R.color.themeWhite));
        holder.optionB.getBackground().setTint(context.getResources().getColor(R.color.themeWhite));
        holder.optionC.getBackground().setTint(context.getResources().getColor(R.color.themeWhite));

        holder.optionA.setClickable(true);
        holder.optionB.setClickable(true);
        holder.optionC.setClickable(true);

        holder.optionA.setOnClickListener(new View.OnClickListener() {
            int marks = 0;

            @Override
            public void onClick(View view) {
                if (holder.tvOptionA.getText().toString().equals(Questions.correctAnswers[position])) {
                    holder.optionA.getBackground().setTint(context.getResources().getColor(R.color.green));
                    marks = 1;
                } else {
                    holder.optionA.getBackground().setTint(context.getResources().getColor(R.color.red));
                    if (holder.tvOptionB.getText().toString().equals(Questions.correctAnswers[position])) {
                        holder.optionB.getBackground().setTint(context.getResources().getColor(R.color.green));
                    } else {
                        holder.optionC.getBackground().setTint(context.getResources().getColor(R.color.green));
                    }
                }
                holder.optionB.setClickable(false);
                holder.optionC.setClickable(false);
                litsener.onChoose(marks);
            }
        });

        holder.optionB.setOnClickListener(new View.OnClickListener() {
            int marks = 0;

            @Override
            public void onClick(View view) {
                if (holder.tvOptionB.getText().toString().equals(Questions.correctAnswers[position])) {
                    holder.optionB.getBackground().setTint(context.getResources().getColor(R.color.green));
                    marks = 1;
                } else {
                    holder.optionB.getBackground().setTint(context.getResources().getColor(R.color.red));
                    if (holder.tvOptionA.getText().toString().equals(Questions.correctAnswers[position])) {
                        holder.optionA.getBackground().setTint(context.getResources().getColor(R.color.green));
                    } else {
                        holder.optionC.getBackground().setTint(context.getResources().getColor(R.color.green));
                    }
                }
                holder.optionA.setClickable(false);
                holder.optionC.setClickable(false);
                litsener.onChoose(marks);
            }
        });

        holder.optionC.setOnClickListener(new View.OnClickListener() {
            int marks = 0;

            @Override
            public void onClick(View view) {

                if (holder.tvOptionC.getText().toString().equals(Questions.correctAnswers[position])) {
                    holder.optionC.getBackground().setTint(context.getResources().getColor(R.color.green));
                    marks = 1;
                } else {
                    holder.optionC.getBackground().setTint(context.getResources().getColor(R.color.red));
                    if (holder.tvOptionB.getText().toString().equals(Questions.correctAnswers[position])) {
                        holder.optionB.getBackground().setTint(context.getResources().getColor(R.color.green));
                    } else {
                        holder.optionA.getBackground().setTint(context.getResources().getColor(R.color.green));
                    }
                }
                holder.optionB.setClickable(false);
                holder.optionA.setClickable(false);
                litsener.onChoose(marks);
            }
        });


        holder.tvCurrentQuestion.setText(Questions.question[position]);
        holder.tvOptionA.setText(Questions.choices[position][0]);
        holder.tvOptionB.setText(Questions.choices[position][1]);
        holder.tvOptionC.setText(Questions.choices[position][2]);
        holder.ivQuestionImage.setImageResource(Questions.questionImages[position]);

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder {
        TextView tvCurrentQuestion;
        View optionA, optionB, optionC;
        ImageView ivQuestionImage;
        TextView tvOptionA, tvOptionB, tvOptionC;

        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivQuestionImage = itemView.findViewById(R.id.iv_question_image);
            tvCurrentQuestion = itemView.findViewById(R.id.tv_current_question);
            optionA = itemView.findViewById(R.id.option_a);
            optionB = itemView.findViewById(R.id.option_b);
            optionC = itemView.findViewById(R.id.option_c);
            tvOptionA = itemView.findViewById(R.id.tv_option_a);
            tvOptionB = itemView.findViewById(R.id.tv_option_b);
            tvOptionC = itemView.findViewById(R.id.tv_option_c);
        }
    }
}

interface Litsener {
    void onChoose(int marks);
}
