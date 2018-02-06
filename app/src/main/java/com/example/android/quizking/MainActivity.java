package com.example.android.quizking;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }





    /**
     * We don't want the user to be able to cheat and answer a question twice in a row.
     * We create these boolean variables to check if a question has been answered.
     * We can use these later if we want to add a reset button.
     */

    boolean answeredOne;
    boolean answeredTwo;
    boolean answeredThree;
    boolean answeredFour;
    boolean answeredFive;
    boolean finishedQuiz;

    int queenValue = 0;
    int quizPoints = 0;




    public void questionOne(View view) {


        // If it has been answered, you can't answer again.
        if (answeredOne) {
            return;
        }

        // If it hasn't been answered, we make sure the user can't answer again.
        answeredOne = true;

        RadioButton carlsenButton = (RadioButton) findViewById(R.id.radio_carlsen);
        RadioButton kasparovButton = (RadioButton) findViewById(R.id.radio_kasparov);
        RadioButton caruanaButton = (RadioButton) findViewById(R.id.radio_caruana);

        if (carlsenButton.isChecked()) {
            displayCorrectOne(true);
        } else {
            displayCorrectOne(false);
        }
    }

    /**
     * Displays if the answer was correct or not.
     * (for the first question)
     */



    private void displayCorrectOne(boolean correctAnswer) {

        if (correctAnswer) {
            TextView correctText = (TextView) findViewById(R.id.q_one_react);
            correctText.setVisibility(View.VISIBLE);
            quizPoints += 1;
        } else {
            TextView incorrectText = (TextView) findViewById(R.id.q_one_react);
            incorrectText.setTextColor(Color.parseColor("#FF0000"));
            incorrectText.setText(getString(R.string.wrong_one));
            incorrectText.setVisibility(View.VISIBLE);
        }

    }


    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (queenValue == 25) {
            // Show an error message as a toast
            Toast.makeText(this, "This is too much.", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        queenValue += 1;
        displayQueenValue(queenValue);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (queenValue == 0) {
            // Show an error message as a toast
            Toast.makeText(this, "This is too low.", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        queenValue -= 1;
        displayQueenValue(queenValue);
    }


    /**
     * This method displays the queen's value on the screen.
     */
    private void displayQueenValue(int queenz) {
        TextView quantityTextView = (TextView) findViewById(R.id.queen_text_view);
        quantityTextView.setText("" + queenz);
    }

    /**
     * This method analyzes the queen's value in question two
     * then displays a correct or incorrect message
     */


    public void submitQueen(View view) {

        if (answeredTwo) {
            return;
        }


        if (queenValue == 9) {
            TextView correctText = (TextView) findViewById(R.id.q_two_react);
            correctText.setVisibility(View.VISIBLE);
            quizPoints += 1;
        } else {
            TextView incorrectText = (TextView) findViewById(R.id.q_two_react);
            incorrectText.setTextColor(Color.parseColor("#FF0000"));
            incorrectText.setText(getString(R.string.wrong_two));
            incorrectText.setVisibility(View.VISIBLE);
        }

    }


    /**
     * When the user pushes the puzzle button, we verify all the checkboxes before
     * figuring out if the answer is right.
     */

    public void submitPuzzle(View view) {

        /**
         * First make sure it's the first time the user is submitting an answer to this puzzle
         */

        if (answeredThree) {
            return;
        }

        answeredThree = true;

        // Figure out if each checkbox is correct.

        CheckBox pawnCheckbox = (CheckBox) findViewById(R.id.pawn_checkbox);
        boolean clickedPawn = pawnCheckbox.isChecked();

        CheckBox bishopCheckbox = (CheckBox) findViewById(R.id.bishop_checkbox);
        boolean clickedBishop = bishopCheckbox.isChecked();

        CheckBox rookCheckbox = (CheckBox) findViewById(R.id.rook_checkbox);
        boolean clickedRook = rookCheckbox.isChecked();

        CheckBox queenCheckbox = (CheckBox) findViewById(R.id.queen_checkbox);
        boolean clickedQueen = queenCheckbox.isChecked();

        // There is only one right answer. Only the queen and the rook can capture

        boolean isCorrect = false;

        if (clickedQueen) {
            if (clickedRook) {
                isCorrect = true;

                //Since there is only one right answer, there is only one right combination.
                //Both the queen and the rook checkboxes must be checked.
                //But if another checkbox is also checked, then the answer is wrong!

                if (clickedPawn) {
                    isCorrect = false;
                }

                if (clickedBishop) {
                    isCorrect = false;
                }

            }
        }

        if (isCorrect) {
            TextView correctText = (TextView) findViewById(R.id.q_three_react);
            correctText.setVisibility(View.VISIBLE);
            quizPoints += 1;
        } else {
            TextView incorrectText = (TextView) findViewById(R.id.q_three_react);
            incorrectText.setTextColor(Color.parseColor("#FF0000"));
            incorrectText.setText(getString(R.string.wrong_three));
            incorrectText.setVisibility(View.VISIBLE);
        }


    }


    public void questionFour(View view) {


        // If it has been answered, you can't answer again.
        if (answeredFour) {
            return;
        }

        // If it hasn't been answered, we make sure the user can't answer again.
        answeredFour = true;

        RadioButton fourYes = (RadioButton) findViewById(R.id.four_radio_yes);
        RadioButton fourNo = (RadioButton) findViewById(R.id.four_radio_no);

        if (fourYes.isChecked()) {
            displayCorrectFour(true);
        } else {
            if (fourNo.isChecked()) {
                displayCorrectFour(false);
            }

        }
    }


    private void displayCorrectFour(boolean correctAnswer) {

        if (correctAnswer) {
            TextView incorrectText = (TextView) findViewById(R.id.q_four_react);
            incorrectText.setTextColor(Color.parseColor("#FF0000"));
            incorrectText.setText(getString(R.string.wrong_four));
            incorrectText.setVisibility(View.VISIBLE);
        } else {

            TextView correctText = (TextView) findViewById(R.id.q_four_react);
            correctText.setVisibility(View.VISIBLE);
            quizPoints += 1;
        }
    }


    public void submitFive(View view) {

        if (answeredFive) {
            return;
        }
        answeredFive = true;

        EditText answerFive = (EditText) findViewById(R.id.answer_five_field);
        Editable answerEditable = answerFive.getText();
        String answer = answerEditable.toString();
        answer = answer.toLowerCase();

        if (answer.equals("norway")) {
            TextView correctText = (TextView) findViewById(R.id.q_five_react);
            correctText.setVisibility(View.VISIBLE);
            quizPoints += 1;
        } else {

            TextView incorrectText = (TextView) findViewById(R.id.q_five_react);
            incorrectText.setTextColor(Color.parseColor("#FF0000"));
            incorrectText.setText(getString(R.string.wrong_five));
            incorrectText.setVisibility(View.VISIBLE);
        }

    }


    public void finishQuiz(View view) {

        if (finishedQuiz) {
            return;
        }
        finishedQuiz = true;

        Toast.makeText(this, "Easy peazy, lemon squeazy!", Toast.LENGTH_SHORT).show();
        //First let's get the user's name
        EditText playerName = (EditText) findViewById(R.id.player_name_field);
        Editable playerNameEditable = playerName.getText();
        String nameOfPlayer = playerNameEditable.toString();

        //Now we get the first part of the goodbye message

        TextView goodbyeText = (TextView) findViewById(R.id.goodbye_message);
        String laterAlligator = (String) goodbyeText.getText();
        laterAlligator = laterAlligator + " " + nameOfPlayer;
        goodbyeText.setText(laterAlligator);

        //We display that
        goodbyeText.setVisibility(View.VISIBLE);

        //And now we get the second part and display that along with the added score.


        TextView goodbyeTextTwo = (TextView) findViewById(R.id.goodbye_message_two);
        String laterCrocodile = (String) goodbyeTextTwo.getText();
        String stringPoints = String.valueOf(quizPoints);
        laterCrocodile += " " + stringPoints + " / 5";
        goodbyeTextTwo.setText(laterCrocodile);

        goodbyeTextTwo.setVisibility(View.VISIBLE);

    }



}
