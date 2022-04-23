package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


import com.google.gson.Gson;

public class QuestionBasicActivity extends AppCompatActivity {
    private QuestionBasic[] questions;
    private QuestionBasic question; //current question
    private Gson gson;
    public int counter;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_basic);
        gson = new Gson();
        questions = gson.fromJson(getIntent().getStringExtra("myjson"), QuestionBasic[].class);
        counter = getIntent().getIntExtra("counter",0);
        question = questions[counter];
        //question.shuffle(); //mieszamy kolejność odpowiedzi

        TextView textView = findViewById(R.id.textViewQuestion);

        int a1 = 0;

        Button button1 = findViewById(R.id.a1);
        Button button2 = findViewById(R.id.a2);
        Button button3 = findViewById(R.id.a3);
        Button button4 = findViewById(R.id.a4);
        Button button5 = findViewById(R.id.a5);
        Button button6 = findViewById(R.id.a6);

        for (int i = 6; i > question.getAnswers().size();i--){
            int a = R.id.a1;
            Button button = findViewById(a+i-1);
            button.setVisibility(View.GONE);
        }
        try{
        button1.setText(question.getAnswers().get(0));
        button2.setText(question.getAnswers().get(1));
        button3.setText(question.getAnswers().get(2));
        button4.setText(question.getAnswers().get(3));
        button5.setText(question.getAnswers().get(4));
        button6.setText(question.getAnswers().get(5));}
        catch (Exception ignored){}

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
        textView.setText(question.getQuestion());
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

    @SuppressLint("SetTextI18n")
    public void sendAnswer1(View view) throws InterruptedException {
        Button b = (Button) view;
        Intent intent = new Intent(this, QuestionBasicActivity.class);
        String myJson = gson.toJson(questions);
        intent.putExtra("myjson", myJson);
        intent.putExtra("counter", counter+1);
        startActivity(intent);

        if (question.getAnswers().get(0).equals(question.getAnswer_correct())) {
            b.setText("Correct");
            b.setBackgroundColor(0xFF00ff99);
        }
    }
    @SuppressLint("SetTextI18n")
    public void sendAnswer2(View view) throws InterruptedException {
        Button b = (Button)view;
        if(question.getAnswers().get(1).equals(question.getAnswer_correct())) {
            b.setText("Correct");
            b.setBackgroundColor(0xFF00ff99);
        }
    }
    @SuppressLint("SetTextI18n")
    public void sendAnswer3(View view) throws InterruptedException {
        Button b = (Button)view;
        if(question.getAnswers().get(2).equals(question.getAnswer_correct())) {
            b.setText("Correct");
            b.setBackgroundColor(0xFF00ff99);
        }
    }
    @SuppressLint("SetTextI18n")
    public void sendAnswer4(View view) throws InterruptedException {
        Button b = (Button)view;
        if(question.getAnswers().get(3).equals(question.getAnswer_correct())) {
            b.setText("Correct");
            b.setBackgroundColor(0xFF00ff99);
        }
    }
    @SuppressLint("SetTextI18n")
    public void sendAnswer5(View view) throws InterruptedException {
        Button b = (Button)view;
        if(question.getAnswers().get(4).equals(question.getAnswer_correct())) {
            b.setText("Correct");
            b.setBackgroundColor(0xFF00ff99);
        }
    }
    @SuppressLint("SetTextI18n")
    public void sendAnswer6(View view) throws InterruptedException {
        Button b = (Button)view;
        if(question.getAnswers().get(5).equals(question.getAnswer_correct())) {
            b.setText("Correct");
            b.setBackgroundColor(0xFF00ff99);
        }
    }
}