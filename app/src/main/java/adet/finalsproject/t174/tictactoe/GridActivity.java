package adet.finalsproject.t174.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class GridActivity extends HasNavbarMenu {

    final String EMPTY = "";
    final String X = "X";
    final String O = "O";
    String turn = O;

    Button box0;
    Button box1;
    Button box2;
    Button box3;
    Button box4;
    Button box5;
    Button box6;
    Button box7;
    Button box8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

         box0 = findViewById(R.id.box0);
         box1 = findViewById(R.id.box1);
         box2 = findViewById(R.id.box2);
         box3 = findViewById(R.id.box3);
         box4 = findViewById(R.id.box4);
         box5 = findViewById(R.id.box5);
         box6 = findViewById(R.id.box6);
         box7 = findViewById(R.id.box7);
         box8 = findViewById(R.id.box8);
    }

    public void setTurn(String turn) {
        this.turn = turn;

        TextView turnText = findViewById(R.id.turnText);
        turnText.setText("Turn for player " + this.turn);
    }

    public void mark(View v) {
        Button button = (Button) v;
        String buttonText = (String) button.getText();
        System.out.println("buttonText: " + buttonText);

        System.out.println("turn: " + this.turn);

        if(this.turn.equals(X) && buttonText.isEmpty()){
            button.setText(X);
            check(v);
            setTurn(O);
        } else if(this.turn.equals(O) && buttonText.isEmpty()){
            button.setText(O);
            check(v);
            setTurn(X);
        } else {
            System.out.println("invalid: " + this.turn + " " + buttonText);
        }
    }

    public boolean checkLine(String[] line) {
        String lastBox = EMPTY;
        for(String box: line) {
            if(box.isEmpty() || (!lastBox.isEmpty() && !lastBox.equals(box))) {
//                System.out.println("box: " + box);
//                System.out.println("lastBox: " + lastBox);
                return false;
            }

            lastBox = box;
        }

        return true;
    }

    public boolean checkRows(String[][] grid) {
        for(String[] row: grid) {
            if(checkLine(row)) {
                return true;
            }
        }

        return false;
    }


    public boolean checkCols(String[][] grid) {
        for(int i = 0; i < 3; i++) {
            ArrayList<String> col = new ArrayList<String>();
            for(int j = 0; j < 3; j++) {
                col.add(grid[j][i]);
            }
            String[] colArr = new String[ col.size() ];
            col.toArray(colArr);


            System.out.print("columns: ");
            for(String box: colArr) {
                if(box.isEmpty()) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[" + box + "]");
                }
            }
            System.out.println();

            if(checkLine(colArr)) {
                return true;
            }
        }

        return false;
    }

    public boolean checkDiagonals(String[][] grid) {
        String[][] diagonals = {
                {grid[0][0], grid[1][1], grid[2][2]},
                {grid[0][2], grid[1][1], grid[2][0]}
        };

        for(String[] diagonal: diagonals) {
            if(checkLine(diagonal)) {
                return true;
            }
        }

        return false;
    }

    public void check(View v) {
        String box0Text = (String)box0.getText();
        String box1Text = (String)box1.getText();
        String box2Text = (String)box2.getText();
        String box3Text = (String)box3.getText();
        String box4Text = (String)box4.getText();
        String box5Text = (String)box5.getText();
        String box6Text = (String)box6.getText();
        String box7Text = (String)box7.getText();
        String box8Text = (String)box8.getText();

        String[][] grid = {{box0Text,box1Text,box2Text},{box3Text,box4Text,box5Text},{box6Text,box7Text,box8Text}};

        for(String[] row: grid) {
            for(String box: row) {
                if(box.isEmpty()) {
                    System.out.print(" [ ] ");
                } else {
                    System.out.print(" [" + box + "] ");
                }
            }
            System.out.println();
        }

        boolean rowResult = checkRows(grid);
        System.out.println("rowResult: " + rowResult);

        boolean colResult = checkCols(grid);
        System.out.println("colResult: " + colResult);

        boolean diagonalResult = checkDiagonals(grid);
        System.out.println("diagonalResult: " + diagonalResult);

        boolean isDraw = checkDraw(grid);
        System.out.println("isDraw: " + isDraw);

        if(rowResult || colResult || diagonalResult) {
            System.out.println("Player " + this.turn + " Wins!");

            Toast.makeText(this, "Player " + this.turn + " Wins!", Toast.LENGTH_SHORT).show();
            resetGrid();
            navigateToCongrats();
        } else if(isDraw) {
            System.out.println("Draw!");

            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            resetGrid();
            navigateToDraw();
        }
    }

    public void resetGrid() {
        Button box0 = findViewById(R.id.box0);
        Button box1 = findViewById(R.id.box1);
        Button box2 = findViewById(R.id.box2);
        Button box3 = findViewById(R.id.box3);
        Button box4 = findViewById(R.id.box4);
        Button box5 = findViewById(R.id.box5);
        Button box6 = findViewById(R.id.box6);
        Button box7 = findViewById(R.id.box7);
        Button box8 = findViewById(R.id.box8);

        box0.setText(EMPTY);
        box1.setText(EMPTY);
        box2.setText(EMPTY);
        box3.setText(EMPTY);
        box4.setText(EMPTY);
        box5.setText(EMPTY);
        box6.setText(EMPTY);
        box7.setText(EMPTY);
        box8.setText(EMPTY);
    }

    public boolean checkDraw(String[][] grid) {
        for(String[] row: grid) {
            for(String box: row) {
                if(box.isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void navigateToCongrats() {
        Intent intent = new Intent(GridActivity.this,CongratsActivity.class);
        startActivity(intent);
    }

    public void navigateToDraw() {
        Intent intent = new Intent(GridActivity.this,DrawActivity.class);
        startActivity(intent);
    }
}