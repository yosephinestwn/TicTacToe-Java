import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;
import java.util.Random;

public class Board extends JPanel implements ActionListener{
    //BOARD ITEMS
    //The game board, filled with 1 for the human player (player1 if the opponent is also human) and 2 for the opponent
    private final int[][] board = new int[3][3];
    private GameStatus gameStatus = null; //The winner of the game, at first it would be null
    private Icon player1;
    private Icon player2;
    private boolean finished = false; //Flag to determine if the game is still going or not
    private JButton button;
    private  JButton button2;
    private JLabel label;
    private  JButton button3;
    private  JButton button4;
    private Opponent opponent;
    private final JFrame frame;
    //A flag to determine if this is our (player1 if the opponent is also human) turn or not
    private boolean ourTurn;
    private TicTacListener mouse;

    private final int offset = 95;
    private boolean started; //A flag to determine if the game already started or not

    //COLORS
    private final Color turtle = new Color(152, 109, 142);
    private final Color offwhite = new Color(0xf7f7f7);
    private final Color darkgrey = new Color(239, 227,208);
    private final Color pink = new Color(130,92,121);

    public Board(){
        //Generate the new frame / window for the game
        frame = new JFrame("Tic Tac Toe");
        frame.getContentPane();
        Dimension size = new Dimension(420,300);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        newGame(); //Calling the "landing page"
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        started = false;
    }

    /**
     * Creating a "landing page" on the window, which ask the player if they want to play the game
     */
    private void newGame(){
        //Making the button for "New Game", the game will start if this button is clicked on
        button = new JButton("New Game"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("Serif", Font.BOLD, 20));
            }

            @Override
            public void setSize(int width, int height) {
                super.setSize(150, 60);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(70, 160);
            }
        };
        button.addActionListener(e->ourIcon());
        label = new JLabel("Start a new Game?"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("SansSerif", Font.BOLD, 20));
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(60, 100);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setOpaque(boolean isOpaque) {
                super.setOpaque(true);
            }
        };
        add(button);
        add(label);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Drawing the board for the game
     * @param page
     */
    private void drawBoard(Graphics page){
        setBackground(turtle);
        page.setColor(darkgrey);
        //PAINT
        int lineWidth = 5;
        int lineLength = 270;
        int x = 15;
        int y = 100;
        page.fillRoundRect(x, y, lineLength, lineWidth, 5, 30);
        page.fillRoundRect(x, y +offset, lineLength, lineWidth, 5, 30);
        page.fillRoundRect(y, x, lineWidth, lineLength, 30, 5);
        page.fillRoundRect(y +offset, x, lineWidth, lineLength, 30, 5);
    }

    /**
     * This method will check the array if there's a change and will actualize the appearance on the game page
     * @param page the page / window appearance that will be actualized
     */
    private void drawGame(Graphics page){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == 1){
                    if(player1 == Icon.CROSS){
                        ImageIcon X = new ImageIcon("C:/Users/agata/Downloads/orangex-removebg-preview.png");
                        Image xImg = X.getImage();
                        Image newXImg = xImg.getScaledInstance(80,80, Image.SCALE_SMOOTH);
                        ImageIcon newXIcon = new ImageIcon(newXImg);
                        Image newnewImg = newXIcon.getImage();
                        page.drawImage(newnewImg, 15+offset*i,15+offset*j, null);
                    }
                    else {
                        page.setColor(offwhite);
                        page.fillOval(30+offset*i, 30+offset*j,50,50);
                        page.setColor(turtle);
                        page.fillOval(40+offset*i, 40+offset*j,30,30);
                    }
                }
                else if(board[i][j] == 2){
                    if(player2 == Icon.CROSS){
                        ImageIcon X = new ImageIcon("C:/Users/agata/Downloads/orangex-removebg-preview.png");
                        Image xImg = X.getImage();
                        Image newXImg = xImg.getScaledInstance(80,80, Image.SCALE_SMOOTH);
                        ImageIcon newXIcon = new ImageIcon(newXImg);
                        Image newnewImg = newXIcon.getImage();
                        page.drawImage(newnewImg, 15+offset*i,15+offset*j, null);
                    }
                    else{
                        page.setColor(offwhite);
                        page.fillOval(30+offset*i, 30+offset*j,50,50);
                        page.setColor(turtle);
                        page.fillOval(40+offset*i, 40+offset*j,30,30);
                    }
                }
            }
        }
        repaint();
    }

    /**
     * This method actualize the appearance of the page, asking the player if they want to play again
     * If they choose yes, the page will go back to "Start Game" page
     * If they choose no, then the window will be closed automatically
     */
    private void playAgain(){
        frame.setTitle("Want to Play Again?");
        label = new JLabel("Want to play again?"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("SansSerif", Font.BOLD, 20));
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(60, 70);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setOpaque(boolean isOpaque) {
                super.setOpaque(true);
            }
        };
        add(label);
        button = new JButton("YES"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("Serif", Font.BOLD, 20));
            }

            @Override
            public void setSize(int width, int height) {
                super.setSize(150, 40);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(70, 130);
            }
        };
        button2 = new JButton("NO"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("Serif", Font.BOLD, 20));
            }

            @Override
            public void setSize(int width, int height) {
                super.setSize(150, 40);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(70, 190);
            }
        };
        button.addActionListener(this);
        button2.addActionListener(e-> System.exit(0));
        add(button);
        add(button2);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        repaint();
    }

    /**
     * Drawing the right hand part of the page
     * The right hand part of the page will show whose turn is it now when the game is still going
     * And show the winner of the game if the game is already finished
     * @param page the page that will be actualized
     */
    private void drawUI(Graphics page){
        page.setColor(pink);
        page.fillRect(300,0,120,300);
        Font font = new Font("Helvetica", Font.PLAIN, 20);
        page.setFont(font);

        page.setColor(offwhite);
        Font font1 = new Font("Serif", Font.ITALIC+Font.BOLD, 20);
        page.setFont(font1);

        //DRAW WHO'S TURN
        if(finished){
            if(gameStatus == GameStatus.CROSS){
                if(player1 == Icon.CROSS){
                    page.drawString("YOU WIN", 310, 150);
                }
                else if(player2 == Icon.CROSS){
                    page.drawString("THE", 310, 30);
                    page.drawString("WINNER", 310, 90);
                    page.drawString("IS YOUR", 310, 150);
                    page.drawString("OPPONENT", 310, 210);
                }
            }
            else if(gameStatus == GameStatus.CIRCLE){
                if(player1 == Icon.CIRCLE){
                    page.drawString("YOU WIN", 310, 150);
                }
                else if(player2 == Icon.CIRCLE){
                    page.drawString("THE", 310, 30);
                    page.drawString("WINNER", 310, 90);
                    page.drawString("IS YOUR", 310, 150);
                    page.drawString("OPPONENT", 310, 210);
                }
            }
            else{
                page.drawString("IT", 310, 30);
                page.drawString("IS", 310, 90);
                page.drawString("A", 310, 150);
                page.drawString("TIE", 310, 210);
            }
        }
        else{
            if(started){
                if(ourTurn){
                    Font font2 = new Font("Serif", Font.ITALIC, 20);
                    page.setFont(font2);
                    page.drawString("", 325, 180);
                    page.drawString("IT'S", 325, 60);
                    page.drawString("YOUR", 325, 120);
                    page.drawString("TURN", 325, 180);
                }
                else{
                    Font font2 = new Font("Serif", Font.ITALIC, 18);
                    page.setFont(font2);
                    page.drawString("", 325, 180);
                    page.drawString("IT'S", 310, 30);
                    page.drawString("YOUR", 310, 90);
                    page.drawString("OPPONENT'S", 310, 150);
                    page.drawString("TURN", 310, 210);

                }
            }
            else{
                Font font2 = new Font("Serif", Font.ITALIC, 20);
                page.setFont(font2);
                page.drawString("THE", 310, 30);
                page.drawString("GAME", 310, 90);
                page.drawString("IS", 310, 150);
                page.drawString("STARTING", 310, 210);
            }
        }
        //

        Image cookie = Toolkit.getDefaultToolkit().getImage("C:/Users/agata/Downloads/pngtree-game-control-glyph-icon-vector-png-image_1904105-removebg-preview.png");
        page.drawImage(cookie, 345, 235, 30, 30, this);
        Font c = new Font("Courier", Font.BOLD, 13);
        page.setFont(c);
        page.drawString("Tic Tac Toe", 325, 280);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resetGame(); //Reseting the game if the "Yes" option on the "Play again" page is clicked on
    }

    /**
     * Redraw the page, the player will be able to choose their icon on this page
     * They can choose either "X" or "O" and the icon is reserved for them
     * Their opponent will get the icon which is left
     */
    private void ourIcon(){
        remove(button);
        remove(label);
        frame.setTitle("Choose Your Icon");
        label = new JLabel("Choose Your Icon!"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("SansSerif", Font.BOLD, 20));
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(60, 70);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setOpaque(boolean isOpaque) {
                super.setOpaque(true);
            }
        };
        add(label);
        button = new JButton("Icon Cross"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("Serif", Font.BOLD, 20));
            }

            @Override
            public void setSize(int width, int height) {
                super.setSize(150, 40);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(70, 130);
            }
        };
        button2 = new JButton("Icon Circle"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("Serif", Font.BOLD, 20));
            }

            @Override
            public void setSize(int width, int height) {
                super.setSize(150, 40);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(70, 190);
            }
        };
        button.addActionListener(e -> {
            player1 = Icon.CROSS;
            player2 = Icon.CIRCLE;
            chooseOpponent();
        });
        button2.addActionListener(e -> {
            player2 = Icon.CROSS;
            player1 = Icon.CIRCLE;
            chooseOpponent();
        });
        add(button);
        add(button2);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        repaint();
    }

    /**
     * Redraw the page: the player is able to choose their opponent mode
     */
    private void chooseOpponent(){
        remove(button);
        remove(button2);
        remove(label);
        frame.setTitle("Choose Your Opponent");
        label = new JLabel("Choose Your Opponent!"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("SansSerif", Font.BOLD, 20));
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(40, 20);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setOpaque(boolean isOpaque) {
                super.setOpaque(true);
            }
        };
        button = new JButton("Human Player"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("Serif", Font.BOLD, 20));
            }

            @Override
            public void setSize(int width, int height) {
                super.setSize(210, 40);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(40, 70);
            }
        };
        button2 = new JButton("Normal Player (Robot)"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("Serif", Font.BOLD, 20));
            }

            @Override
            public void setSize(int width, int height) {
                super.setSize(250, 40);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(20, 130);
            }
        };
        button3 = new JButton("Random Player (Robot)"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("Serif", Font.BOLD, 20));
            }

            @Override
            public void setSize(int width, int height) {
                super.setSize(250, 40);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(20, 190);
            }
        };
        button4 = new JButton("Perfect Player (Robot)"){
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("Serif", Font.BOLD, 20));
            }

            @Override
            public void setSize(int width, int height) {
                super.setSize(250, 40);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(20, 250);
            }
        };

        button.addActionListener(e -> {
            opponent = null;
            startGame();
        });
        button2.addActionListener(e -> {
            opponent = new Normal_Player();
            startGame();
        });
        button3.addActionListener(e -> {
            opponent = new Random_Player();
            startGame();
        });
        button4.addActionListener(e-> {
            opponent = new Perfect_Player();
            startGame();
        });
        add(label);
        add(button);
        add(button2);
        add(button3);
        add(button4);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        repaint();
    }

    /**
     * Checking if there is already a winner of the game
     */
    private void whoWon(){
        GameStatus result = GameStatus.TIE;
        boolean full = true;
        for(int i = 0; i < 3; i++){
            if(board[i][0] !=0 &&
                    board[i][1] != 0 &&
                    board[i][2] != 0 &&
                    board[i][0] == board[i][1] &&
                    board[i][0] == board[i][2] &&
                    board[i][1] == board[i][2]){
                if(board[i][0]==1){
                    if(player1 == Icon.CIRCLE){
                        result= GameStatus.CIRCLE;
                    }
                    else{
                        result= GameStatus.CROSS;
                    }
                }
                else if(board[i][0]==2){
                    if(player2 == Icon.CIRCLE){
                        result= GameStatus.CIRCLE;
                    }
                    else{
                        result= GameStatus.CROSS;
                    }
                }
                break;
            }
        }
        if(result == GameStatus.TIE){
            for(int i = 0; i < 3; i++){
                if(board[0][i] != 0 &&
                        board[1][i] != 0 &&
                        board[2][i] != 0 &&
                        board[0][i] == board[1][i] &&
                        board[0][i] == board[2][i] &&
                        board[1][i] == board[2][i]){
                    if(board[0][i]==1){
                        if(player1 == Icon.CIRCLE){
                            result= GameStatus.CIRCLE;
                        }
                        else{
                            result= GameStatus.CROSS;
                        }
                    }
                    else if(board[0][i]==2){
                        if(player2 == Icon.CIRCLE){
                            result= GameStatus.CIRCLE;
                        }
                        else{
                            result= GameStatus.CROSS;
                        }
                    }
                    break;
                }
            }
        }
        if(result == GameStatus.TIE){
            if(board[0][0] != 0 && board[1][1] != 0 && board[2][2] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]){
                if(board[0][0]==1){
                    if(player1 == Icon.CIRCLE){
                        result= GameStatus.CIRCLE;
                    }
                    else{
                        result= GameStatus.CROSS;
                    }
                }
                else if(board[0][0]==2){
                    if(player2 == Icon.CIRCLE){
                        result= GameStatus.CIRCLE;
                    }
                    else{
                        result= GameStatus.CROSS;
                    }
                }
            }
            else if(board[0][2] != 0 && board[1][1] != 0 && board[2][0] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]){
                if(board[0][2]==1){
                    if(player1 == Icon.CIRCLE){
                        result= GameStatus.CIRCLE;
                    }
                    else{
                        result= GameStatus.CROSS;
                    }
                }
                else if(board[0][2]==2){
                    if(player2 == Icon.CIRCLE){
                        result= GameStatus.CIRCLE;
                    }
                    else{
                        result= GameStatus.CROSS;
                    }
                }
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == 0){
                    full = false;
                    break;
                }
            }
        }
        if((full && result == GameStatus.TIE) || result == GameStatus.CIRCLE || result == GameStatus.CROSS){
            finished = true;
        }
        gameStatus = result;
        if(finished){
            playAgain();
        }
    }

    /**
     * Reset the game
     */
    private void resetGame(){
        remove(button);
        remove(button2);
        remove(label);
        removeMouseListener(mouse);
        finished = false;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j <3; j++){
                board[i][j] = 0;
            }
        }
        newGame();
        started = false;
    }

    /**
     * Repaint the page everytime a change is made
     * @param page the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics page){
        super.paintComponent(page);
        drawBoard(page);
        drawUI(page);
        drawGame(page);
    }

    /**
     * Start the game, who has the first turn is random
     */
    private void startGame(){
        remove(label);
        remove(button);
        remove(button2);
        remove(button3);
        remove(button4);
        Random rand = new Random();
        int random = rand.nextInt(2);
        ourTurn = random == 0;
        mouse = new TicTacListener();
        addMouseListener(mouse);
        started = true;
    }

    private class TicTacListener implements MouseListener{ //The mouse listener for the game
        /**
         * For the human player, every time they click on a field, that field will be filled with their icon
         * If they click on a filled field or somewhere outside teh world, their input will be deemed as an invalid input and there will be a warning on the console
         * For the computer/robot player, the mouse click will generate their move
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            int selX ;
            int selY ;
            if(!finished){
                int a;
                int b;
                if(ourTurn){
                    a = e.getX();
                    b = e.getY();
                    if(a > 3 && a < 97){
                        selX = 0;
                    }
                    else if(a > 103 && a < 197){
                        selX = 1;
                    }
                    else if(a > 203 & a < 297){
                        selX = 2;
                    }
                    else selX = -1;
                    if(b > 3 && b < 97){
                        selY = 0;
                    }
                    else if(b > 103 && b < 197){
                        selY = 1;
                    }
                    else if(b > 203 & b < 297){
                        selY = 2;
                    }
                    else selY =-1;
                    if(selX != -1 && selY != -1){
                        if(board[selX][selY] == 0){
                            board[selX][selY] = 1;
                            ourTurn = false;
                            whoWon();
                        }
                        else{
                            if (board[selX][selY] == 1) {
                                System.out.println("This field is occupied by you");
                            }
                            else System.out.println("This field is occupied by your opponent");
                        }
                    }
                    else System.out.println("Invalid Click!");
                }
                else {
                    if (opponent == null){
                        a = e.getX();
                        b = e.getY();
                        if(a > 3 && a < 97){
                            selX = 0;
                        }
                        else if(a > 103 && a < 197){
                            selX = 1;
                        }
                        else if(a > 203 & a < 297){
                            selX = 2;
                        }
                        else selX = -1;
                        if(b > 3 && b < 97){
                            selY = 0;
                        }
                        else if(b > 103 && b < 197){
                            selY = 1;
                        }
                        else if(b > 203 & b < 297){
                            selY = 2;
                        }
                        else selY =-1;
                        if(selX != -1 && selY != -1){
                            if(board[selX][selY] == 0){
                                board[selX][selY] = 2;
                                whoWon();
                            }
                            else{
                                if (board[selX][selY] == 1) {
                                    System.out.println("This field is occupied by you");
                                }
                                else System.out.println("This field is occupied by your opponent");
                            }
                        }
                        else System.out.println("Invalid Click!");
                    }
                    else if(opponent instanceof Normal_Player){
                        Normal_Player.nextMovement(board);
                        whoWon();
                    }
                    else if (opponent instanceof Perfect_Player){
                        Perfect_Player.nextMovement(board);
                        whoWon();
                    }
                    else if (opponent instanceof Random_Player){
                        Random_Player.nextMovement(board);
                        whoWon();
                    }
                    ourTurn = true;
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
