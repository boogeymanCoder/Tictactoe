package adet.finalsproject.t174.tictactoe;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Grid {
    final static String EMPTY = "";
    final static String X = "X";
    final static String O = "O";
    final static String TIE = "TIE";
    final static String NO_WINNER = "NO_WINNER";

    private String[][] grid = {{EMPTY, EMPTY, EMPTY}, {EMPTY, EMPTY, EMPTY}, {EMPTY, EMPTY, EMPTY}};


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



    public boolean checkRows() {
        for(String[] row: this.grid) {
            if(checkLine(row)) {
                return true;
            }
        }

        return false;
    }

    public boolean checkCols() {
        for(int i = 0; i < 3; i++) {
            ArrayList<String> col = new ArrayList<String>();
            for(int j = 0; j < 3; j++) {
                col.add(grid[j][i]);
            }
            String[] colArr = new String[ col.size() ];
            col.toArray(colArr);


//            System.out.print("columns: ");
//            for(String box: colArr) {
//                if(box.isEmpty()) {
//                    System.out.print("[ ]");
//                } else {
//                    System.out.print("[" + box + "]");
//                }
//            }
//            System.out.println();

            if(checkLine(colArr)) {
                return true;
            }
        }

        return false;
    }

    public boolean checkDiagonals() {
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

    public void resetGrid() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                grid[i][j] = EMPTY;
            }
//            System.out.println();
        }
    }

    public boolean checkDraw() {
        for(String[] row: grid) {
            for(String box: row) {
                if(box.isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean check() {
//        for(String[] row: this.grid) {
//            for(String box: row) {
//                if(box.isEmpty()) {
//                    System.out.print(" [ ] ");
//                } else {
//                    System.out.print(" [" + box + "] ");
//                }
//            }
//            System.out.println();
//        }

        boolean rowResult = checkRows();
//        System.out.println("rowResult: " + rowResult);

        boolean colResult = checkCols();
//        System.out.println("colResult: " + colResult);

        boolean diagonalResult = checkDiagonals();
//        System.out.println("diagonalResult: " + diagonalResult);

        boolean isDraw = checkDraw();
//        System.out.println("isDraw: " + isDraw);

        if(rowResult || colResult || diagonalResult) {
            return true;
        } else {
            return false;
        }
    }

//  returns NO_WINNER, X, O, or TIE
    public String findResult() {
        String rowResult = findRowsResult();
//        System.out.println("rowResult: " + rowResult);
        if(!rowResult.equals( NO_WINNER)) {
            return rowResult;
        }

        String colResult = findColsResult();
//        System.out.println("colResult: " + colResult);
        if(!colResult.equals(NO_WINNER)) {
            return colResult;
        }

        String diagonalResult = findDiagonalsResult();
//        System.out.println("diagonalResult: " + diagonalResult);
        if(!diagonalResult.equals(NO_WINNER)) {
            return diagonalResult;
        }

        boolean isDraw = checkDraw();
//        System.out.println("isDraw: " + isDraw);
        if(isDraw) {
            return TIE;
        }

        return NO_WINNER;
    }

    public String findLineResult(String[] line) {
        String lastBox = EMPTY;
        for(String box: line) {
            if(box.isEmpty() || (!lastBox.isEmpty() && !lastBox.equals(box))) {
//                System.out.println("box: " + box);
//                System.out.println("lastBox: " + lastBox);
                return NO_WINNER;
            }

            lastBox = box;
        }

        if(lastBox.equals( EMPTY)) {
            return NO_WINNER;
        }

        return lastBox;
    }

    public String findRowsResult() {
        String winner = NO_WINNER;

        for(String[] row: this.grid) {
            winner = findLineResult(row);
            if(!winner.equals(NO_WINNER)) {
                return winner;
            }
        }

        return winner;
    }

    public String findColsResult() {

        String winner = NO_WINNER;
        for(int i = 0; i < 3; i++) {
            ArrayList<String> col = new ArrayList<String>();
            for(int j = 0; j < 3; j++) {
                col.add(grid[j][i]);
            }
            String[] colArr = new String[ col.size() ];
            col.toArray(colArr);


//            System.out.print("columns: ");
//            for(String box: colArr) {
//                if(box.isEmpty()) {
//                    System.out.print("[ ]");
//                } else {
//                    System.out.print("[" + box + "]");
//                }
//            }
//            System.out.println();


            winner = findLineResult(colArr);

            if(!winner.equals(NO_WINNER)) {
                return winner;
            }
        }

        return winner;
    }

    public String findDiagonalsResult() {
        String winner = NO_WINNER;

        String[][] diagonals = {
                {grid[0][0], grid[1][1], grid[2][2]},
                {grid[0][2], grid[1][1], grid[2][0]}
        };

        for(String[] diagonal: diagonals) {

            winner = findLineResult(diagonal);

            if(!winner.equals(NO_WINNER)) {
                return winner;
            }
        }

        return winner;
    }


    public void mark(int row, int col, String val) {
        this.grid[row][col] = val;
    }

    public String getValue(int row, int col) {
        return this.grid[row][col];
    }

}
