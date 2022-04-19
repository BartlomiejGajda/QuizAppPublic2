package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


import com.google.gson.Gson;

public class QuestionBasicActivity extends AppCompatActivity {
    private QuestionBasic question;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_basic);
        Gson gson = new Gson();
        question = gson.fromJson(getIntent().getStringExtra("myjson"), QuestionBasic.class);
        question.shuffle();
        TextView textView = findViewById(R.id.textViewQuestion);
        /*Button[] buttons = new Button[question.getCounter()];
        for (int i = 0; i < question.getCounter(); i++) {
            Button button = new Button(this);
            button.setId(i+1);
            button.setText("maupa");
            buttons[i] = button; }

        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < question.getCounter(); i++) {
            ll.addView(buttons[i]);
        }*/
        //ConstraintLayout ll = (ConstraintLayout) findViewById(R.id.question_basic_Layout);
        for (int i = 6; i > question.getCounter();i--){
            int a = R.id.a6;
            Button button = findViewById(a-i+1);
            button.setVisibility(View.GONE);
        }

        //ll.removeView(button1);
        //ViewGroup layout = (ViewGroup) button1.getParent();
        //if(null!=layout) //for safety only  as you are doing onClick
        //    layout.removeView(button1);
        textView.setText(question.getQuestion());




    }
    @SuppressLint("SetTextI18n")
    public void sendAnswer(View view) throws InterruptedException {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        if(check(buttonText)) {
            b.setText("ccccchuwno");
        }
    }

    private boolean check(String answer){
        return question.checkAnswer(answer);
    }
}