import java.util.Random;

public class Normal_Player implements Opponent{

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
