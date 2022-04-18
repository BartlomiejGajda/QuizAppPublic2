package com.example.quizapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    public QuestionBasic questionBasic = new QuestionBasic("jaja", new String[]{"dupa", "jaja", "jaja", "JAJA"},"JAJA");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendQuestionArray(View view){
        Intent intent = new Intent(this, QuestionBasicActivity.class);
        Gson gson = new Gson();
        String myJson = gson.toJson(questionBasic);
        intent.putExtra("myjson", myJson);
        startActivity(intent);
    }

}