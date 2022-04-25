package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;


import com.google.gson.Gson;

public class QuestionBasicActivity extends AppCompatActivity implements QuestionFragment.OnDataPass {
    private QuestionBasic[] questions;
    private QuestionBasic question; //current question
    private Gson gson;
    public int counter = 0;
    public int score = 0;
    Bundle savedInstanceState;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        questions = gson.fromJson(getIntent().getStringExtra("myjson"), QuestionBasic[].class);
        //counter = questions.length;
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            String myJson = gson.toJson(questions[counter]);
            bundle.putString("question",myJson);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentQuestionView, QuestionFragment.class, bundle)
                    .commit();
        }

        setContentView(R.layout.activity_question_basic);

        counter = getIntent().getIntExtra("counter",counter);
        question = questions[counter];
        question.shuffle(); //mieszamy kolejność odpowiedzi

        /*Button[] buttons = new Button[question.getCounter()];
        for (int i = 0; i < question.getCounter(); i++) {
            Button button = new Button(this);
            button.setId(i+1);
            buttons[i] = button; }

        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < question.getCounter(); i++) {
            ll.addView(buttons[i]);
        }*/
        //ConstraintLayout ll = (ConstraintLayout) findViewById(R.id.question_basic_Layout);


        //ll.removeView(button1);
        //ViewGroup layout = (ViewGroup) button1.getParent();
        //if(null!=layout) //for safety only  as you are doing onClick
        //    layout.removeView(button1);
    }

    public void setButton(Button b, Boolean isCorrect){
        if(isCorrect){
            b.setBackgroundColor(Color.GREEN);
            b.setTextColor(Color.BLACK);
        }else{
            b.setBackgroundColor(Color.RED);
            b.setTextColor(Color.WHITE);
        }
    }

    @Override
    public void onDataPass(int score) {
        this.score+=score;
        this.counter+=1;
        gson = new Gson();
        questions = gson.fromJson(getIntent().getStringExtra("myjson"), QuestionBasic[].class);
        if(counter<=questions.length) {
            String myJson = gson.toJson(questions[counter]);
            Bundle bundle = new Bundle();
            bundle.putString("question", myJson);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentQuestionView, QuestionFragment.class, bundle)
                    .commit();
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt("score", this.score);
            bundle.putInt("counter",this.counter);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentQuestionView, AfterQuestionFragment.class, bundle)
                    .commit();
        }
    }
}