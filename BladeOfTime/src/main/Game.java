package main;

import javax.swing.*;

public class Game {
    private static Game uniqueInstance = null;

    private Game()
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Blade of Time");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame(); //state
        gamePanel.setMap();


        gamePanel.startGameThread();
    }
    public static Game getInstance()
    {
        if(uniqueInstance == null)
        {
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }

}
