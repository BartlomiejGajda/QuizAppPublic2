package com.example.quizapp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionBasic {
    private String question;
    //private String answer_a, answer_b, answer_c, answer_d;
    private List<String> answers;
    //private Answer[] answers;
    private String answer_correct;

    public QuestionBasic(String question, List<String> answers, String answer_correct) {
        this.question = question;
        this.answers = answers;
        this.answer_correct = answer_correct;

    }

    public void shuffle(){
        Collections.shuffle(answers);
    }

    /*public boolean checkAnswer(String answer_chosen){
        return answer_chosen.equals(answer_correct);
    }*/

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }


    public String getAnswer_correct() {
        return answer_correct;
    }

    public void setAnswer_correct(String answer_correct) {
        this.answer_correct = answer_correct;
    }
}
