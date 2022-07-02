package adet.finalsproject.t174.tictactoe;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AiGridActivity extends HasNavbarMenu {
    Grid grid;
    String turn;
    String playerMarker;

    Button box0;
    Button box1;
    Button box2;
    Button box3;
    Button box4;
    Button box5;
    Button box6;
    Button box7;
    Button box8;

    TextView turnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_grid);

        turn = randomTurn(new String[] {Grid.O, Grid.X});
        grid = new Grid();

        box0 = findViewById(R.id.ai_box0);
        box1 = findViewById(R.id.ai_box1);
        box2 = findViewById(R.id.ai_box2);
        box3 = findViewById(R.id.ai_box3);
        box4 = findViewById(R.id.ai_box4);
        box5 = findViewById(R.id.ai_box5);
        box6 = findViewById(R.id.ai_box6);
        box7 = findViewById(R.id.ai_box7);
        box8 = findViewById(R.id.ai_box8);

        box0.setTag(new Coordinates(0,  0));
        box1.setTag(new Coordinates(0,  1));
        box2.setTag(new Coordinates(0,  2));
        box3.setTag(new Coordinates(1,  0));
        box4.setTag(new Coordinates(1,  1));
        box5.setTag(new Coordinates(1,  2));
        box6.setTag(new Coordinates(2,  0));
        box7.setTag(new Coordinates(2,  1));
        box8.setTag(new Coordinates(2,  2));

        box0.setText(grid.getValue(0,0));
        box1.setText(grid.getValue(0,1));
        box2.setText(grid.getValue(0,2));
        box3.setText(grid.getValue(1,0));
        box4.setText(grid.getValue(1,1));
        box5.setText(grid.getValue(1,2));
        box6.setText(grid.getValue(2,0));
        box7.setText(grid.getValue(2,1));
        box8.setText(grid.getValue(2,2));

        turnText = findViewById(R.id.turnText);
        turnText.setText("Your are " + this.turn);

    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item) {
        return super.onOptionsItemSelectedConfirmed(item);
    }

    public void setTurn(String turn) {
        this.turn = turn;

        TextView turnText = findViewById(R.id.turnText);
        turnText.setText("Your are " + this.turn);
    }

    public void mark(View v) {
        Button button = (Button) v;
        String buttonText = (String) button.getText();
        Coordinates coordinates = (Coordinates) button.getTag();
        System.out.println("buttonText: " + buttonText);

        System.out.println("turn: " + this.turn);

        if(this.turn.equals(Grid.X) && buttonText.isEmpty()){
            button.setText(Grid.X);
            this.grid.mark(coordinates.getRow(), coordinates.getCol(), Grid.X);
            if(check(v)) {
                return;
            }
            setTurn(Grid.O);
        } else if(this.turn.equals(Grid.O) && buttonText.isEmpty()){
            button.setText(Grid.O);
            this.grid.mark(coordinates.getRow(), coordinates.getCol(), Grid.O);
            if(check(v)) {
                return;
            }
            setTurn(Grid.X);
        } else {
            System.out.println("invalid: " + this.turn + " " + buttonText);
            return;
        }

//        AI's turn
        aiMove(v);
    }

    public void aiMove(View v) {
        Coordinates aiMove = AiPlayer.bestMove(grid, turn);
        if(aiMove.getRow() == -1 || aiMove.getCol() == -1) {
            System.out.println("Error: Ai cannot find a move");
            return;
        }
        Button buttonGrid[][] = {{box0, box1, box2}, {box3, box4, box5}, {box6, box7, box8}};

        Button aiButton = buttonGrid[aiMove.getRow()][aiMove.getCol()];
        String aiButtonText = (String) aiButton.getText();
        System.out.println("AiMove Row: "+ aiMove.getRow());
        System.out.println("AiMove Col: "+ aiMove.getCol());


        if(this.turn.equals(Grid.X) && aiButtonText.isEmpty()){
            aiButton.setText(Grid.X);
            this.grid.mark(aiMove.getRow(), aiMove.getCol(), Grid.X);
            if(check(v)) {
                return;
            }
            setTurn(Grid.O);
        } else if(this.turn.equals(Grid.O) && aiButtonText.isEmpty()){
            aiButton.setText(Grid.O);
            this.grid.mark(aiMove.getRow(), aiMove.getCol(), Grid.O);
            if(check(v)) {
                return;
            }
            setTurn(Grid.X);
        } else {
            System.out.println("Error: turn - " + this.turn );
        }
    }

    public boolean check(View v) {
//        for(String[] row: grid) {
//            for(String box: row) {
//                if(box.isEmpty()) {
//                    System.out.print(" [ ] ");
//                } else {
//                    System.out.print(" [" + box + "] ");
//                }
//            }
//            System.out.println();
//        }

        String result = grid.findResult();

        boolean isDraw = grid.checkDraw();
        System.out.println("isDraw: " + isDraw);

        if(!result.equals(Grid.NO_WINNER)&&!result.equals(Grid.TIE)) {
            String winner = grid.findResult();
            if(!winner.equals(turn)) {
                System.out.println("Player Wins!");

                Toast.makeText(this, "Player Wins!", Toast.LENGTH_SHORT).show();
                System.out.println("Player wins: (winner:" + winner + ", marker: " + playerMarker + ")");
                System.out.println("Grid:");
                System.out.println(grid);
                resetGrid();
                navigateToCongrats();
            } else {
                System.out.println("AI Wins!");

                Toast.makeText(this, "AI Wins!", Toast.LENGTH_SHORT).show();
                resetGrid();
                navigateToYouLose();
            }
            return true;
        } else if(result.equals(Grid.TIE)) {
            System.out.println("Draw!");

            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            resetGrid();
            navigateToDraw();
            return true;
        }

        return false;
    }

    public void resetGrid() {
        Button box0 = findViewById(R.id.ai_box0);
        Button box1 = findViewById(R.id.ai_box1);
        Button box2 = findViewById(R.id.ai_box2);
        Button box3 = findViewById(R.id.ai_box3);
        Button box4 = findViewById(R.id.ai_box4);
        Button box5 = findViewById(R.id.ai_box5);
        Button box6 = findViewById(R.id.ai_box6);
        Button box7 = findViewById(R.id.ai_box7);
        Button box8 = findViewById(R.id.ai_box8);

        box0.setText(Grid.EMPTY);
        box1.setText(Grid.EMPTY);
        box2.setText(Grid.EMPTY);
        box3.setText(Grid.EMPTY);
        box4.setText(Grid.EMPTY);
        box5.setText(Grid.EMPTY);
        box6.setText(Grid.EMPTY);
        box7.setText(Grid.EMPTY);
        box8.setText(Grid.EMPTY);

        grid.resetGrid();
    }


    public void navigateToCongrats() {
        Intent intent = new Intent(AiGridActivity.this,CongratsActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void navigateToDraw() {
        Intent intent = new Intent(AiGridActivity.this,DrawActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void navigateToYouLose() {
        Intent intent = new Intent(AiGridActivity.this,YouLoseActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public String randomTurn(String[] turns) {
        int rnd = new Random().nextInt(turns.length);
        return turns[rnd];
    }
}