import java.util.Random;

public class Random_Player implements Opponent{

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
