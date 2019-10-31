package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int round;
    private int player1=0;
    private int player2=0;

    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonId = "button" + i + j;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(resId);

                buttons[i][j].setOnClickListener(this);
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("0");
        }

        round++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (round == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private void player1Wins() {
        player1++;
        Toast.makeText(MainActivity.this, "PLAYER 1 wins", Toast.LENGTH_LONG).show();
        reset();
        winPoints();
    }

    private void player2Wins() {
        player2++;
        Toast.makeText(MainActivity.this, "PLAYER 2 wins", Toast.LENGTH_LONG).show();
        reset();
        winPoints();
    }

    private void draw() {
        Toast.makeText(MainActivity.this, "Its a fucking draw", Toast.LENGTH_LONG).show();
        reset();
        winPoints();
    }

    private void reset() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        round=0;
        player1Turn=true;
    }

    private void winPoints()
    {
        textView1.setText("Player 1:"+player1 );
        textView2.setText("Player 2:"+player2 );
    }

    public boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

            if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
                return true;
            }

            if (field[2][0].equals(field[1][1]) && field[2][0].equals(field[0][2]) && !field[2][0].equals("")) {
                return true;
            }
            return false;
        }

    public void doSomething(View view) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        round=0;
        player1Turn=true;

    }
}
