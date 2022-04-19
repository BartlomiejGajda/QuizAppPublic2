package com.example.quizapp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionBasic {
    private String question;
    //private String answer_a, answer_b, answer_c, answer_d;
    private String[] answers;
    private String answer_correct;
    private int counter;

    public QuestionBasic(String question, String[] answers, String answer_correct) {
        this.question = question;
        this.answers = answers;
        this.answer_correct = answer_correct;
        this.counter = answers.length;

    }

    public void shuffle(){
        List<String> list = Arrays.asList(answers);

        Collections.shuffle(list);
        list.toArray(answers);
    }

    public boolean checkAnswer(String answer_chosen){
        return answer_chosen.equals(answer_correct);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getAnswer_correct() {
        return answer_correct;
    }

    public void setAnswer_correct(String answer_correct) {
        this.answer_correct = answer_correct;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
