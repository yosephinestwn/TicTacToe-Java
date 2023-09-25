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
    private final int[][] board = new int[3][3];
    private GameStatus gameStatus = null;
    private Icon player1;
    private Icon player2;
    private boolean finished = false;
    private JButton button;
    private  JButton button2;
    private JLabel label;
    private  JButton button3;
    private  JButton button4;
    private Opponent opponent;
    private final JFrame frame;
    private boolean ourTurn;
    private TicTacListener mouse;

    private final int offset = 95;

    //COLORS
    private final Color turtle = new Color(152, 109, 142);
    private final Color offwhite = new Color(0xf7f7f7);
    private final Color darkgrey = new Color(239, 227,208);
    private final Color pink = new Color(130,92,121);

    public Board(){
        frame = new JFrame("Tic Tac Toe");
        frame.getContentPane();
        Dimension size = new Dimension(420,300);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        newGame();
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void newGame(){
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
                        page.drawImage(newnewImg, 30+ offset*i,30+offset*j, null);
                    }
                    else if(board[i][j] == 2){
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
                        page.drawImage(newnewImg, 30+ offset*i,30+offset*j, null);
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
                    page.drawString("THE WINNER", 310, 60);
                    page.drawString("IS YOUR", 310, 120);
                    page.drawString("OPPONENT", 310, 180);
                }
            }
            else if(gameStatus == GameStatus.CIRCLE){
                if(player1 == Icon.CIRCLE){
                    page.drawString("YOU WIN", 310, 150);
                }
                else if(player2 == Icon.CIRCLE){
                    page.drawString("THE WINNER", 310, 60);
                    page.drawString("IS YOUR", 310, 120);
                    page.drawString("OPPONENT", 310, 180);
                }
            }
            else{
                page.drawString("IT IS A TIE", 310, 178);
            }
        }
        else{
            Font font2 = new Font("Serif", Font.ITALIC, 20);
            page.setFont(font2);
            page.drawString("", 325, 180);
            if(ourTurn){
                page.drawString("It's", 325, 60);
                page.drawString("your", 325, 120);
                page.drawString("turn", 325, 180);
            }
            else{
                page.drawString("It's your", 325, 60);
                page.drawString("opponent's", 325, 120);
                page.drawString("turn", 325, 180);

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
        resetGame();
    }

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
                super.setSize(150, 40);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void setLocation(int x, int y) {
                super.setLocation(70, 70);
            }
        };
        button2 = new JButton("Normal Player (Robot)"){
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
        button3 = new JButton("Random Player (Robot)"){
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
        button4 = new JButton("Perfect Player (Robot)"){
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
                super.setLocation(70, 250);
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
            new Perfect_Player();
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
    }

    public void paintComponent(Graphics page){
        super.paintComponent(page);
        drawBoard(page);
        drawUI(page);
        drawGame(page);
    }
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
    }

    private class TicTacListener implements MouseListener{

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
                    if(a > 0 && a < 99){
                        selX = 0;
                    }
                    else if(a > 100 && a < 199){
                        selX = 1;
                    }
                    else if(a > 200 & a < 299){
                        selX = 2;
                    }
                    else selX = -1;
                    if(b > 0 && b < 99){
                        selY = 0;
                    }
                    else if(b > 100 && b < 199){
                        selY = 1;
                    }
                    else if(b > 200 & b < 299){
                        selY = 2;
                    }
                    else selY =-1;
                    if(selX != -1 && selY != -1){
                        if(board[selY][selY] == 0){
                            board[selX][selY] = 1;
                            ourTurn = false;
                            whoWon();
                        }
                        else System.out.println("Invalid Click! This field is not empty");
                    }
                    else System.out.println("Invalid Click!");
                }
                else {
                    if (opponent == null){
                        a = e.getX();
                        b = e.getY();
                        if(a > 0 && a < 99){
                            selX = 0;
                        }
                        else if(a > 100 && a < 199){
                            selX = 1;
                        }
                        else if(a > 200 & a < 299){
                            selX = 2;
                        }
                        else selX = -1;
                        if(b > 0 && b < 99){
                            selY = 0;
                        }
                        else if(b > 100 && b < 199){
                            selY = 1;
                        }
                        else if(b > 200 & b < 299){
                            selY = 2;
                        }
                        else selY =-1;
                        if(selX != -1 && selY != -1){
                            if(board[selY][selY] == 0){
                                board[selX][selY] = 2;
                                whoWon();
                            }
                            else System.out.println("Invalid Click! This field is not empty");
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
                    else {
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
