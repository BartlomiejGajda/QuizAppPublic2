package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Objects;


public class QuestionFragment extends Fragment {
    private QuestionBasic question;
    OnDataPass dataPasser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String myJson = requireArguments().getString("question");
        Gson gson = new Gson();
        question = gson.fromJson(myJson, QuestionBasic.class);
        question.shuffle();

        TextView questionTextView = (TextView) getView().findViewById(R.id.questionTextView);
        Button button1 = getView().findViewById(R.id.answer1);
        Button button2 = getView().findViewById(R.id.answer2);
        Button button3 = getView().findViewById(R.id.answer3);
        Button button4 = getView().findViewById(R.id.answer4);
        Button button5 = getView().findViewById(R.id.answer5);
        Button button6 = getView().findViewById(R.id.answer6);
        Button[] buttons = new Button[]{button1,button2,button3,button4,button5,button6};
        for (Button button: buttons
        ) {button.setVisibility(View.VISIBLE);}
        for (int i = 6; i > question.getAnswers().size();i--){
            int a = R.id.answer1;
            Button button = getView().findViewById(a+i-1);
            button.setVisibility(View.GONE);
        }

        try{
            questionTextView.setText(question.getQuestion());}
        catch (Exception ignored){}

        try{
            button1.setText(question.getAnswers().get(0));
            button2.setText(question.getAnswers().get(1));
            button3.setText(question.getAnswers().get(2));
            button4.setText(question.getAnswers().get(3));
            button5.setText(question.getAnswers().get(4));
            button6.setText(question.getAnswers().get(5));}
        catch (Exception ignored){}
        for (Button button: buttons
             ) {
            button.setOnClickListener(new View.OnClickListener()
            {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v)
                {
                    if (button.getText().equals(question.getAnswer_correct())) {
                    button.setText("Correct");
                    button.setBackgroundColor(0xFF00ff99);
                    dataPasser.onDataPass(1);
                } else {
                        button.setText("Incorrect");
                        button.setBackgroundColor(Color.RED);
                        dataPasser.onDataPass(0);
                    }
                }
            });
        }

    }
    public interface OnDataPass {
        public void onDataPass(int score);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }
    public void passData(int data) {
        dataPasser.onDataPass(data);
    }
}
