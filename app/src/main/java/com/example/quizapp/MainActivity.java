package com.example.quizapp;

import androidx.annotation.NonNull;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {
    private FirebaseFirestore db;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    //public QuestionBasic question1 = new QuestionBasic("Ilość województw w Polsce wynosi:", Arrays.asList("15", "16", "13", "18"),"16");
    //public QuestionBasic question2 = new QuestionBasic("Tak?", Arrays.asList("tak","nie","moze"),"tak");
    //public QuestionBasic question3 = new QuestionBasic("Nie?", Arrays.asList("tak","nie","moze"),"nie");
    public QuestionBasic[] quiz1;
    //public QuestionBasic questionBasic;

    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<QuestionBasic> adapter2;
    ArrayList<QuestionBasic> listQuestions=new ArrayList<QuestionBasic>();
    //ListView quizList = findViewById(R.id.list);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);



        CollectionReference docRef = db.collection("topics");

        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        adapter.add(document.getId());
                        adapter.notifyDataSetChanged();
                        /*CollectionReference questions = document.getReference().collection("questions");
                        questions.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                for(QueryDocumentSnapshot document2 : task.getResult()) {
                                    //document2.toObject(QuestionBasic.class);
                                }
                            }
                        });*/
                        }
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
        //FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onListItemClick(l, v, pos, id);
        String values=((TextView)v).getText().toString();

        CollectionReference questionsRef = db.collection("topics").document("Test1").collection("questions");
        Intent intent = new Intent(this, QuestionBasicActivity.class);
        questionsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot document : task.getResult()) {
                    ((TextView) v).setText(document.getId());
                    QuestionBasic questionBasic = document.toObject(QuestionBasic.class);
                    //String question = (String) document3.get("question");
                    //String answer_correct = (String) document3.get("answer_correct");
                    //String[] answers = (String[]) document3.get("answers");
                    //quizList.add(new QuestionBasic(question, Arrays.asList(answers),answer_correct));
                    listQuestions.add(questionBasic);
                }

                //intent.putExtra("myquestionlist", (Parcelable) db.document(values));
                Gson gson = new Gson();
                //DocumentReference quiz = db.collection("top").document(values);
                QuestionBasic[] quizArray = listQuestions.toArray(new QuestionBasic[0]);
                String myJson = gson.toJson(quizArray);
                intent.putExtra("myjson", myJson);
                intent.putExtra("counter", 0);
                startActivity(intent);
                //Toast.makeText(this, quizList.get(0).getAnswer_correct().toString(), Toast.LENGTH_LONG);
            }
        });
        /*Intent intent = new Intent(this, QuestionBasicActivity.class);
        //intent.putExtra("myquestionlist", (Parcelable) db.document(values));
        Gson gson = new Gson();
        //DocumentReference quiz = db.collection("top").document(values);
        QuestionBasic[] quizArray = listQuestions.toArray(new QuestionBasic[0]);
        String myJson = gson.toJson(quizArray);
        intent.putExtra("myjson", myJson);
        intent.putExtra("counter", 0);
        //Toast.makeText(this, quizList.get(0).getAnswer_correct().toString(), Toast.LENGTH_LONG);
        //startActivity(intent);*/
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