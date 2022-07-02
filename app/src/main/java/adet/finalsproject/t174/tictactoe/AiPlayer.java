package adet.finalsproject.t174.tictactoe;

public class AiPlayer {
    static public Coordinates bestMove(Grid grid, String marker) {
        int bestScore = (int) Double.NEGATIVE_INFINITY;
        Coordinates move = new Coordinates(-1,-1); //invalid coordinates, signifies null

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(grid.getValue(i, j).equals(Grid.EMPTY)){
                    grid.mark(i, j, marker);
                    int score = minimax(grid, 0, false, marker, 0);
                    grid.mark(i, j, Grid.EMPTY);


                    System.out.println("AI new score: " + score);
                    System.out.println("AI current bestScore: " + bestScore);
                    if(score > bestScore) {
                        bestScore = score;
                        System.out.println("AI best score: " + bestScore);
                        move = new Coordinates(i, j);
                    }
                }
            }
        }

        return move;
    }

    static public int minimax(Grid grid, int depth, boolean isMaximizing, String marker, int lastScore) {
        System.out.println("minimax(depth: "+depth+", isMaximizing: "+ isMaximizing +", lastScore: " + lastScore + ")");
        String result = grid.findResult();
//        System.out.println("minimax result: " + result);
        if(!result.equals(Grid.NO_WINNER)) {
            if (result.equals(Grid.O) || result.equals(Grid.X)) {
                if (result.equals(marker)) {
//                System.out.println("minimax win, result:" + result);
//                System.out.println("minimax win");
                    lastScore += 1;
                } else {
//                System.out.println("minimax loss");
                    lastScore -= 1;
                }
            }

            return lastScore;
        }


        if(isMaximizing){
            int bestScore = (int) Double.NEGATIVE_INFINITY;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid.getValue(i, j).equals(Grid.EMPTY)) {
                        grid.mark(i, j, marker);

                        int score = minimax(grid, depth + 1, false, marker, lastScore);
                        grid.mark(i, j, Grid.EMPTY);

                        if(score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = (int) Double.POSITIVE_INFINITY;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid.getValue(i, j).equals(Grid.EMPTY)) {
                        if(marker.equals(Grid.O)) {
                            grid.mark(i, j, Grid.X);
                        } else {
                            grid.mark(i, j, Grid.O);
                        }
                         int score = minimax(grid, depth + 1, true, marker, lastScore);
                        grid.mark(i, j, Grid.EMPTY);

                        if(score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }
}
