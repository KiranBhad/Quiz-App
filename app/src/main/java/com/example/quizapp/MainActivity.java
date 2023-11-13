package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView question,total_questions;
    Button optionA,optionB,optionC,optionD;
    TextView countDown;
    CountDownTimer timer;
    Button submit;
    int score = 0;
    int totalQuestion = QuestionAnswer.Question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    int currQuestion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.question);
        total_questions = findViewById(R.id.total_questions);
        submit = findViewById(R.id.SubmitBtn);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        countDown = findViewById(R.id.contDown);

        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);
        submit.setOnClickListener(this);
        total_questions.setText("Total Questions :"+totalQuestion);
        loadNewQuestion();
    }
    @Override
    public void onClick(View view) {
        optionA.setBackgroundColor(Color.WHITE);
        optionA.setTextColor(Color.RED);
        optionB.setBackgroundColor(Color.WHITE);
        optionB .setTextColor(Color.RED);
        optionC.setBackgroundColor(Color.WHITE);
        optionC.setTextColor(Color.RED);
        optionD.setBackgroundColor(Color.WHITE);
        optionD.setTextColor(Color.RED);


        Button clickedButton = (Button) view;

        if(clickedButton.getId() == R.id.SubmitBtn)
        {

            if(selectedAnswer.equals(QuestionAnswer.CorrectAnswers[currentQuestionIndex])){
                score +=2;
            }

            currentQuestionIndex++;
            currQuestion++;
            loadNewQuestion();

        }
        else{
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
            clickedButton.setTextColor(Color.WHITE);


        }

    }

    private void loadNewQuestion() {
        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }

        question.setText(+currQuestion+"."+QuestionAnswer.Question[currentQuestionIndex]);
        optionA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        optionB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        optionC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        optionD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);


    }

    private void finishQuiz() {
        String passStatus = "";
        if(score > totalQuestion*0.50){
            passStatus = "Passed";
        }
        else{
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this).setTitle(passStatus)
                .setMessage("Score is "+score+" out of 10")
                .setPositiveButton("Restart",((dialogInterface, i) -> restartQuiz()))
                .setNegativeButton("Quit app",((dialogInterface,i)-> quitApp()))
                .show();
    }

    private void quitApp() {
        finish();
    }

    private void restartQuiz() {
            score = 0;
            currentQuestionIndex = 0;
            currQuestion = 1;
             loadNewQuestion();
            
    }




}