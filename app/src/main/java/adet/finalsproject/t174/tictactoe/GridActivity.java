package adet.finalsproject.t174.tictactoe;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GridActivity extends HasNavbarMenu {
    private static String GRID = "grid";
    private static String TURN = "turn";
    private static String BLOCKS = "blocks";
    private static String TURN_NUM = "turnNum";
    private static String LAST_BLOCK = "lastBlock";

    private String turn;
    private int turnNum;
    private Grid grid;
    private ArrayList<Integer> blocks;
    private Coordinates lastBlock;

    private Button box0;
    private Button box1;
    private Button box2;
    private Button box3;
    private Button box4;
    private Button box5;
    private Button box6;
    private Button box7;
    private Button box8;

    private TextView turnText;


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

        if (savedInstanceState != null) {
            turn = savedInstanceState.getString(TURN);
            grid = (Grid) savedInstanceState.getSerializable(GRID);
            System.out.print("blocks: ");
            blocks = (ArrayList<Integer>) savedInstanceState.getSerializable(BLOCKS);
            for(int block: blocks) {
                System.out.print(block + ", ");
            }
            turnNum = savedInstanceState.getInt(TURN_NUM);
            lastBlock = (Coordinates) savedInstanceState.getSerializable(LAST_BLOCK);
        } else {
            turn = randomTurn(new String[] {Grid.O, Grid.X});
            grid = new Grid();

            System.out.print("blocks: ");
            blocks = generateBlocks();
            for(int block: blocks) {
                System.out.print(block + ", ");
            }
            turnNum = 0;
            lastBlock = new Coordinates(-1, -1);
        }
        System.out.println();

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
         turnText.setText("Turn for player " + this.turn);
        placeBlock();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(GRID, grid);
        outState.putString(TURN, turn);
        outState.putSerializable(BLOCKS, blocks);
        outState.putInt(TURN_NUM, turnNum);
        outState.putSerializable(LAST_BLOCK, lastBlock);

        super.onSaveInstanceState(outState);
    }

    public void setTurn(String turn) {
        this.turn = turn;

        turnText.setText("Turn for player " + this.turn);
        placeBlock();
    }

    public void placeBlock() {
        ArrayList<Coordinates> emptyBlocks = findEmptyBlocks();
        Button[][] boxes = {{box0, box1, box2}, {box3, box4, box5}, {box6, box7, box8}};


        if(lastBlock.getRow() != -1 || lastBlock.getCol() != -1) {
            int row = lastBlock.getRow();
            int col = lastBlock.getCol();
            Button box = boxes[row][col];
            box.setText(Grid.EMPTY);
            box.setEnabled(true);
            grid.mark(row, col, Grid.EMPTY);
        }

        if(turnNum < 8) {
            Coordinates block = emptyBlocks.get(blocks.get(turnNum));
            int row = block.getRow();
            int col = block.getCol();

            Button box = boxes[row][col];
            box.setText(Grid.BLOCK);
            box.setEnabled(false);
            grid.mark(row, col, Grid.BLOCK);

            lastBlock = block;

            turnNum += 1;
        }
    }

    public ArrayList<Coordinates> findEmptyBlocks() {
        ArrayList<Coordinates> emptyBlocks = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(grid.getValue(i, j).equals(Grid.EMPTY) || grid.getValue(i, j).equals(Grid.BLOCK)) {
                    emptyBlocks.add(new Coordinates(i, j));
                }
            }
        }

        return emptyBlocks;
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
            check(v);
            setTurn(Grid.O);
        } else if(this.turn.equals(Grid.O) && buttonText.isEmpty()){
            button.setText(Grid.O);
            this.grid.mark(coordinates.getRow(), coordinates.getCol(), Grid.O);
            check(v);
            setTurn(Grid.X);
        } else {
            System.out.println("invalid: " + this.turn + " " + buttonText);
        }
    }

    public void check(View v) {
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

        boolean result = grid.check();

        boolean isDraw = grid.checkDraw();
        System.out.println("isDraw: " + isDraw);

        if(result) {
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
        box0.setText(Grid.EMPTY);
        box1.setText(Grid.EMPTY);
        box2.setText(Grid.EMPTY);
        box3.setText(Grid.EMPTY);
        box4.setText(Grid.EMPTY);
        box5.setText(Grid.EMPTY);
        box6.setText(Grid.EMPTY);
        box7.setText(Grid.EMPTY);
        box8.setText(Grid.EMPTY);

        turn = randomTurn(new String[] {Grid.O, Grid.X});
        grid = new Grid();

        blocks = generateBlocks();
        turnNum = 0;
        lastBlock = new Coordinates(-1, -1);
    }


    public void navigateToCongrats() {
        Intent intent = new Intent(GridActivity.this,CongratsActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void navigateToDraw() {
        Intent intent = new Intent(GridActivity.this,DrawActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public String randomTurn(String[] turns) {
        int rnd = new Random().nextInt(turns.length);
        return turns[rnd];
    }

    public ArrayList<Integer> generateBlocks() {
        ArrayList<Integer> blocks = new ArrayList<>();

        for(int i = 9; i > 1; i--) {
            int rnd = new Random().nextInt(i);
            System.out.println("Index: " + i);
            System.out.println("Random number: " + rnd);

            blocks.add(rnd);
        }

        return blocks;
    }
}