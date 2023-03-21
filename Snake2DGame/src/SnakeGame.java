import javax.swing.*;

public class SnakeGame extends JFrame {

    Board board;

    SnakeGame()
    {
        board = new Board();
        add(board);
        pack();
        setResizable(false);
        setVisible(true);
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        //Initialize Snake Game
        SnakeGame snakeGame = new SnakeGame();
    }
}