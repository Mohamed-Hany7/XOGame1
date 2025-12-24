package com.example.xogame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button[] buttons = new Button[9];
    Button btnReset;
    TextView txtTurn;

    int turn = 1;
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTurn = findViewById(R.id.txtTurn);
        btnReset = findViewById(R.id.btnReset);

        for (int i = 0; i < 9; i++) {
            int id = getResources().getIdentifier("b" + (i + 1), "id", getPackageName());
            buttons[i] = findViewById(id);
            buttons[i].setOnClickListener(this);
        }

        btnReset.setOnClickListener(v -> resetGame());
    }

    @Override
    public void onClick(View view) {
        if (gameOver) return;

        Button btn = (Button) view;
        if (!btn.getText().toString().equals("")) return;

        if (turn == 1) {
            btn.setText("X");
            txtTurn.setText("Turn: O");
            turn = 2;
        } else {
            btn.setText("O");
            txtTurn.setText("Turn: X");
            turn = 1;
        }

        checkWinner();
    }

    void checkWinner() {
        String[] b = new String[9];
        for (int i = 0; i < 9; i++) {
            b[i] = buttons[i].getText().toString();
        }

        int[][] win = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] w : win) {
            if (b[w[0]].equals("X") && b[w[1]].equals("X") && b[w[2]].equals("X")) {
                Toast.makeText(this, "X Wins!", Toast.LENGTH_SHORT).show();
                gameOver = true;
                return;
            }
            if (b[w[0]].equals("O") && b[w[1]].equals("O") && b[w[2]].equals("O")) {
                Toast.makeText(this, "O Wins!", Toast.LENGTH_SHORT).show();
                gameOver = true;
                return;
            }
        }
    }

    void resetGame() {
        for (Button b : buttons) {
            b.setText("");
        }
        turn = 1;
        gameOver = false;
        txtTurn.setText("Turn: X");
    }
}