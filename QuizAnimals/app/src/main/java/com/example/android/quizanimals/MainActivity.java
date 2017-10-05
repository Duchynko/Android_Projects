package com.example.android.quizanimals;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import static android.R.attr.fastScrollOverlayPosition;
import static android.R.attr.name;
import static android.R.attr.verticalScrollbarPosition;
import static android.R.id.message;
import static com.example.android.quizanimals.R.id.mainScrollView;

public class MainActivity extends AppCompatActivity {

    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // This method shows the results, after buton is clicked
    public void showResults (View view) {
        //Get user's name
        score = 0;
        EditText nameField = (EditText) findViewById(R.id.nameField);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();
        calculateScore();
        createResultReport(name, score);

        String message = createResultReport(name, score);
        displayMessage(message);
    }

    //This method calculate the score
    public int calculateScore () {

        //Question 1
        RadioButton Q1Answer = (RadioButton) findViewById(R.id.Q1Answer);
        boolean Q1AnswerCheck = Q1Answer.isChecked();
        if (Q1AnswerCheck)
            score++;

        //Question 2
        EditText Q2AnswerField = (EditText) findViewById(R.id.Q2AnswerField);
        if (Q2AnswerField.getText().toString().equals("Gorilla"))
        score++;

        //Question 3
        CheckBox Q3Answer1 = (CheckBox) findViewById(R.id.Q3Answer1);
        boolean Q3Answer1Check = Q3Answer1.isChecked();
        if (Q3Answer1Check)
            score++;

        CheckBox Q3Answer2 = (CheckBox) findViewById(R.id.Q3Answer2);
        boolean Q3Answer2Check = Q3Answer2.isChecked();
        if (Q3Answer2Check)
            score++;

        CheckBox Q3Answer3 = (CheckBox) findViewById(R.id.Q3Answer3);
        boolean Q3Answer3Check = Q3Answer3.isChecked();
        if (Q3Answer3Check)
            score++;

        //Question 4
        RadioButton Q4Answer = (RadioButton) findViewById(R.id.Q4Answer);
        boolean Q4AnswerCheck = Q4Answer.isChecked();
        if (Q4AnswerCheck)
            score++;

        //Question 5
        EditText Q5AnswerField = (EditText) findViewById(R.id.Q5AnswerField);
        if (Q5AnswerField.getText().toString().equals("Bat"))
        score++;

        //Question 6
        CheckBox Q6Answer1 = (CheckBox) findViewById(R.id.Q6Answer1);
        boolean Q6Answer1Check = Q6Answer1.isChecked();
        if (Q6Answer1Check)
            score++;

        CheckBox Q6Answer2 = (CheckBox) findViewById(R.id.Q6Answer2);
        boolean Q6Answer2Check = Q6Answer2.isChecked();
        if (Q6Answer2Check)
            score++;

        //Question 7
        RadioButton Q7Answer = (RadioButton) findViewById(R.id.Q7Answer);
        boolean Q7AnswerCheck = Q7Answer.isChecked();
        if (Q7AnswerCheck)
            score++;

        //Question 8
        EditText Q8AnswerField = (EditText) findViewById(R.id.Q8AnswerField);
        if (Q8AnswerField.getText().toString().equals("Bat"))
        score++;

        //Question 9
        CheckBox Q9Answer1 = (CheckBox) findViewById(R.id.Q9Answer1);
        boolean Q9Answer1Check = Q9Answer1.isChecked();
        if (Q9Answer1Check)
            score++;

        CheckBox Q9Answer2 = (CheckBox) findViewById(R.id.Q9Answer2);
        boolean Q9Answer2Check = Q9Answer2.isChecked();
        if (Q9Answer2Check)
            score++;

        return score;
    }

    //This method creates the result report
    public String createResultReport (String name, int score) {
        String resultReport = getString(R.string.congratulation);
        resultReport += "\n" + name + getString(R.string.report_your_score) + score;
        if (score < 4) {
            resultReport += "\n" + getString(R.string.low_score);
        }else if (score < 9) {
            resultReport += "\n" + getString(R.string.medium_score);
        }else {
            resultReport += "\n" + getString(R.string.high_score);
        }
        return resultReport;
    }

    public void displayMessage (String message){

        TextView scoreTextView = (TextView) findViewById(R.id.score_text_view);
        scoreTextView.setText(message);
    }

    public void restartQuiz(View view) {
        score=0;
        TextView updatedScore = (TextView) findViewById(R.id.score_text_view);
        updatedScore.setText("");

    }

}
