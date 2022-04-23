package com.example.quizapp;

import androidx.annotation.NonNull;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity {
    private FirebaseFirestore db;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public String myJson;

    public QuestionBasic question1 = new QuestionBasic("Ilość województw w Polsce wynosi:", Arrays.asList("15", "16", "13", "18"),"16");
    public QuestionBasic question2 = new QuestionBasic("Tak?", Arrays.asList("tak","nie","moze"),"tak");
    public QuestionBasic question3 = new QuestionBasic("Nie?", Arrays.asList("tak","nie","moze"),"nie");
    private Object QuestionBasic;
    public QuestionBasic[] quiz1;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    //ListView quizList = findViewById(R.id.list);
    public String tempQuizName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference docRef = db.collection("quizzes");

        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        adapter.add(document.getId());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        setListAdapter(adapter);
        /*Gson gson = new Gson();
        Map<String, com.example.quizapp.QuestionBasic> city = new HashMap<>();
        city.put("question1", question1);
        city.put("question2", question2);
        city.put("question3", question3);
        //city.put((com.example.quizapp.QuestionBasic) QuestionBasic, question2);


        db.collection("quizzes").document("Quiz1")
                .set(city);
                /*.addOnSuccessListener(new OnSuccessListener<Void>() {
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
    }


    public void loadQuizList(View view){
        /*Intent intent = new Intent(this, QuestionBasicActivity.class);
        int counter = 0;
        int score = 0;
        intent.putExtra("myjson", myJson);
        intent.putExtra("counter", counter);
        startActivity(intent);*/

    }

}