package com.example.quizapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    private FirebaseFirestore db;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    //public QuestionBasic question1 = new QuestionBasic("Ilość województw w Polsce wynosi:", Arrays.asList("15", "16", "13", "18"),"16");
    //public QuestionBasic question2 = new QuestionBasic("Tak?", Arrays.asList("tak","nie","moze"),"tak");
    //public QuestionBasic question3 = new QuestionBasic("Nie?", Arrays.asList("tak","nie","moze"),"nie");

    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList<QuestionBasic> listQuestions=new ArrayList<QuestionBasic>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);



        CollectionReference docRef = db.collection("topics");

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for(QueryDocumentSnapshot document : task.getResult()) {
                    adapter.add(document.getId());
                    adapter.notifyDataSetChanged();
                    }
                }
            });
        setListAdapter(adapter);

       /* Gson gson = new Gson();
        Map<String, com.example.quizapp.QuestionBasic> city = new HashMap<>();
        city.put("question1", question1);
        city.put("question2", question2);
        city.put("question3", question3);
        //city.put((com.example.quizapp.QuestionBasic) QuestionBasic, question2);


        db.collection("topics").document("Test1").collection("questions")
                .add(question1);
        db.collection("topics").document("Test1").collection("questions")
                .add(question2);
        db.collection("topics").document("Test1").collection("questions")
                .add(question3);
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

    public static List<QuestionBasic> quizList = new ArrayList<>();
    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        String values=((TextView)v).getText().toString();
        CollectionReference questionsRef = db.collection("topics").document(values).collection("questions");
        Intent intent = new Intent(this, QuestionActivity.class);
        questionsRef.get().addOnCompleteListener(task -> {
            for(QueryDocumentSnapshot document : task.getResult()) {
                QuestionBasic questionBasic = document.toObject(QuestionBasic.class);
                listQuestions.add(questionBasic);
            }
            Gson gson = new Gson();
            QuestionBasic[] quizArray = listQuestions.toArray(new QuestionBasic[0]);
            String myJson = gson.toJson(quizArray);
            intent.putExtra("myjson", myJson);
            intent.putExtra("counter", 0);
            startActivity(intent);
        });
    }
}