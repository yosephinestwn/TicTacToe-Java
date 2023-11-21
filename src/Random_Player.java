import java.util.Random;

public class Random_Player implements Opponent{
    /**
     * Generate a random movement for this type of opponent
     * Choose an empty field to be filled randomly
     * @param board the game board
     */
    public static void nextMovement(int[][] board) {
        Random rand = new Random();

        int x = rand.nextInt(3);
        int y = rand.nextInt(3);
        while(board[x][y] != 0){
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        }
        board[x][y] = 2;
    }
}
