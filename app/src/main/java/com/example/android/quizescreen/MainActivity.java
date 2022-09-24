package com.example.android.quizescreen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Litsener {
    TextView tvQuestionNo;
    TextView btnNextQues;
    ProgressBar questionCompletedProgress;
    RecyclerView recyclerView;
    QuestionsAdapter adapter;
    CustomLinearLayoutManager linearLayoutManager;

    int totalQuestion = Questions.question.length;
    int currentQuestionIndex = 0;
    int isChoosen = 0;
    int score=0;

    class CustomLinearLayoutManager extends LinearLayoutManager {

        boolean canScroll = false;

        public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        void setScroll(boolean canScroll) {
            this.canScroll = canScroll;
        }

        @Override
        public boolean canScrollHorizontally() {
            return canScroll;
        }

        @Override
        public void onScrollStateChanged(int state) {
            super.onScrollStateChanged(state);
            if (state == RecyclerView.SCROLL_STATE_IDLE) {
                this.canScroll = false;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.vp_questions);
        adapter = new QuestionsAdapter(this, this);
        linearLayoutManager = new CustomLinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
        btnNextQues = findViewById(R.id.btn_next);
        questionCompletedProgress = findViewById(R.id.pb_question_completed);
        tvQuestionNo = findViewById(R.id.tv_question_no);

        btnNextQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNewQuestion();
            }
        });

    }


    void loadNewQuestion() {
        if (isChoosen == 0) {
            return;
        }
        if (currentQuestionIndex == totalQuestion-2) {
            btnNextQues.setText("SUBMIT");
        }

        if (currentQuestionIndex == totalQuestion-1) {
            recyclerView.setVisibility(View.GONE);
            btnNextQues.setVisibility(View.GONE);
            findViewById(R.id.completed_quize_view).setVisibility(View.VISIBLE);
            TextView tv_score=findViewById(R.id.tv_score);
            tv_score.setText(((score*100)/4)+"% Score");
        }


        linearLayoutManager.setScroll(true);
        isChoosen = 0;
        tvQuestionNo.setText((currentQuestionIndex + 1) + "/" + 4);
        questionCompletedProgress.setProgress((currentQuestionIndex + 1) * 25);
        recyclerView.smoothScrollToPosition(currentQuestionIndex + 1);
        currentQuestionIndex++;
    }

    @Override
    public void onChoose(int marks) {
        isChoosen = 1;
        score+=marks;
    }
}