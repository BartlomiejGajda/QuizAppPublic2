package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;


import com.google.gson.Gson;

public class QuestionActivity extends AppCompatActivity implements QuestionFragment.OnDataPass {
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
    }


    @Override
    public void onDataPass(int score) {
        this.score+=score;
        counter+=1;
        //gson = new Gson();
        //questions = gson.fromJson(getIntent().getStringExtra("myjson"), QuestionBasic[].class);
        if(counter!=questions.length) {
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
            bundle.putInt("counter",counter);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentQuestionView, AfterQuestionFragment.class, bundle)
                    .commit();
        }
    }
}