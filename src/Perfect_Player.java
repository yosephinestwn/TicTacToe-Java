import java.util.Random;

public class Perfect_Player implements Opponent{

    public static void nextMovement(int[][] board) {
        boolean found = false;
        for(int i = 0; i < 3; i++){
            if((board[i][0] == 2 && board[i][1] == 2) && board[i][2] ==0){
                found = true;
                board[i][2] = 2;
                break;
            }
            if((board[i][1] == 2 && board[i][2] == 2) && board[i][0] ==0){
                found = true;
                board[i][0] = 2;
                break;
            }
            if((board[i][0] == 2 && board[i][2] == 2) && board[i][1] ==0){
                found = true;
                board[i][1] = 2;
                break;
            }
        }
        if(!found){
            for(int i = 0; i < 3; i++){
                if((board[0][i] == 2 && board[1][i] == 2) && board[2][i] ==0){
                    found = true;
                    board[2][i] = 2;
                    break;
                }
                if((board[1][i] == 2 && board[2][i] == 2) && board[0][i] ==0){
                    found = true;
                    board[0][i] = 2;
                    break;
                }
                if((board[0][i] == 2 && board[2][i] == 2) && board[1][i] ==0){
                    found = true;
                    board[1][i] = 2;
                    break;
                }
            }
        }
        if(!found){
            if((board[0][0] == 2 && board[1][1] == 2) && board[2][2] ==0){
                found = true;
                board[2][2] = 2;
            }
            if((board[1][1] == 2 && board[2][2] == 2) && board[0][0] ==0){
                found = true;
                board[0][0] = 2;
            }
            if((board[0][0] == 2 && board[2][2] == 2) && board[1][1] ==0){
                found = true;
                board[1][1] = 2;
            }
        }
        if(!found){
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
}
