import java.util.Random;

public class Normal_Player implements Opponent{
    /**
     * Generate the next movement for this type of opponent
     * For every each movement of this opponent is either a random player movement or perfect player movement
     * @param board The game board
     */
    public static void nextMovement(int[][] board) {
        Random rand = new Random();

        int random = rand.nextInt(2);
        if(random ==0){
            Random_Player.nextMovement(board);
        }
        else{
            Perfect_Player.nextMovement(board);
        }
    }
}
