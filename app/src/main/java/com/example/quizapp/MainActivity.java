package com.example.quizapp;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    //private FirebaseFirestore db;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public String myJson;


    /*public Answer a1 = new Answer("16",true);
    public Answer a2 = new Answer("14",false);
    public Answer a3 = new Answer("15",false);
    public Answer a4 = new Answer("17",false);
    public Answer b1 = new Answer("tak",true);
    public Answer b2 = new Answer("nie",false);
    public Answer b3 = new Answer("może",false);
    public QuestionBasic question1 = new QuestionBasic("Ilość województw w Polsce wynosi:", new Answer[]{a1,a2,a3,a4});
    public QuestionBasic question2 = new QuestionBasic("Tak?", new Answer[]{b1,b2,b3});*/
    public QuestionBasic[] question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("quizzes").document("testquiz");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        myJson = document.getString("question");
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void sendQuestionArray(View view){
        Intent intent = new Intent(this, QuestionBasicActivity.class);
        //Gson gson = new Gson();
        //String myJson = gson.toJson(question);
        //Map<String, Object> city = new HashMap<>();
        //city.put("question", myJson);
            

        /*db.collection("quizzes").document("testquiz")
                .set(city)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });*/
        int counter = 0;
        int score = 0;
        intent.putExtra("myjson", myJson);
        intent.putExtra("counter", counter);
        startActivity(intent);
    }

}