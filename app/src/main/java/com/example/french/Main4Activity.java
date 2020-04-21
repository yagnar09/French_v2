package com.example.french;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main4Activity extends AppCompatActivity {
    private ImageView questionImage;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    String quizData[][] = {

            {"white", "blanc", "vert", "bleu", "jaune"},
            {"red", "rouge", "noir", "orange", "it's a pen"},
            {"green", "vert", "blanc", "bleu", "jaune"},
            {"blue", "bleu", "rouge", "noir", "orange"},
            {"yellow", "jaune", "vert", "blanc", "violet"},
            {"black", "noir", "bleu", "rouge", "rose"},
            {"orange", "orange", "jaune", "vert", "blanc"},
            {"purple", "violet", "noir", "bleu", "rouge"},
            {"pink", "rose", "orange", "jaune", "vert"},
            {"brown", "marron", "rouge", "violet", "bleu"},


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        questionImage = findViewById(R.id.imageView);
        answer1 = findViewById(R.id.Answer1);
        answer2 = findViewById(R.id.Answer2);
        answer3 = findViewById(R.id.Answer3);
        answer4 = findViewById(R.id.Answer4);

        for (int i = 0; i < quizData.length; i++) {

            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);


            quizArray.add(tmpArray);
        }

        showNextQuiz();
        View.OnClickListener check=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(view);
            }
        };

        answer1.setOnClickListener(check);
        answer2.setOnClickListener(check);
        answer3.setOnClickListener(check);
        answer4.setOnClickListener(check);
    }


    public void showNextQuiz() {




        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());


        ArrayList<String> quiz = quizArray.get(randomNum);


        questionImage.setImageResource(
                getResources().getIdentifier(quiz.get(0), "drawable", getPackageName()));
        rightAnswer = quiz.get(1);


        quiz.remove(0);
        Collections.shuffle(quiz);


        answer1.setText(quiz.get(0));
        answer2.setText(quiz.get(1));
        answer3.setText(quiz.get(2));
        answer4.setText(quiz.get(3));


        quizArray.remove(randomNum);

    }
    public void checkAnswer(View view) {

        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {

            alertTitle = "correct";
            rightAnswerCount++;

        } else {

            alertTitle = "incorrect";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("la bonne réponse : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizArray.size() < 1) {

                    showResult();

                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void showResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("resultat");
        builder.setMessage(rightAnswerCount + " / 9");
        builder.setPositiveButton("réessayer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate();
            }
        });
        builder.setNegativeButton("sortir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }
}
