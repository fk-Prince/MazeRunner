package Game;


import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setTitle("Maze Runner");
        Game game = new Game();
        frame.add(game);
        frame.setIconImage(new ImageIcon(Game.class.getResource("/Image/ApplicationIcon/maze.png")).getImage());
        game.startGame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}