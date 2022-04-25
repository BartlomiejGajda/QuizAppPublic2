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


public class AfterQuestionFragment extends Fragment {

    //OnDataPass dataPasser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String score = requireArguments().getString("score");
        String counter = requireArguments().getString("counter");
        TextView scoreCounterTextView = (TextView) getView().findViewById(R.id.scoreCounterTextView);
        try{
            scoreCounterTextView.setText("Wynik: "+ score +"/"+ counter);}
        catch (Exception ignored){}
        Button returnButton = getView().findViewById(R.id.returnButton);

    }
    /*public interface OnDataPass {
        public void onDataPass(int score);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }
    public void passData(int data) {
        dataPasser.onDataPass(data);
    }*/
}
