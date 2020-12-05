package flappy;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;


public class FlappyPanel extends JPanel implements KeyListener, ActionListener {

    final int WIDTH = 525, HEIGHT = 550;
    final int WALLXVELOCITY = 5;
    final int WALLWIDTH = 50;
    int flappyHeight = HEIGHT / 4;
    int flappyV = 0, flappyA = 8, flappyI = 1; // impose/paskridimas/fizika
    int score = 0;
    int[] wallX = {WIDTH, WIDTH + WIDTH / 2};
    int[] gap = {(int)(Math.random() * (HEIGHT-150)), (int)(Math.random() * (HEIGHT-100))};
    boolean gameOver = false;
    Timer time = new Timer(40, this);

    public FlappyPanel()
    {

        setSize(WIDTH, HEIGHT);
        setFocusable(true);
        addKeyListener(this);

        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (!gameOver)
        {
            g.setColor(Color.YELLOW);
            g.drawString("SCORE: " + score, WIDTH / 3, 10);
            g.drawString("REPLAY: R", WIDTH / 2, 10);
            g.drawString("START: S ", WIDTH / 5, 10);


            drawWall(g);
            logic();
            drawFlappy(g);
        } else
            {
            g.setColor(Color.YELLOW);
            g.drawString("SCORE: " + score, WIDTH / 3, 10);
            g.drawString("REPLAY: R", WIDTH / 2, 10);
            g.drawString("START: S ", WIDTH / 5, 10);
        }
    }

    private void drawFlappy(Graphics g)
    {
        g.setColor(Color.WHITE);

        g.fillRect(75, flappyHeight + flappyV, 25, 25);
    }

    private void drawWall(Graphics g)
    {
        for (int i = 0; i < 2; i++)
        {

            g.setColor(Color.RED);
            g.fillRect(wallX[i], 0, WALLWIDTH, HEIGHT);

            g.setColor(Color.BLACK);
            g.fillRect(wallX[i], gap[i], WALLWIDTH, 100);
        }
    }

    private void logic()
    {
        for(int i = 0; i < 2; i ++)
        {

            if (wallX[i] <= 100 && wallX[i] + WALLWIDTH >= 100 || wallX[i] <= 75 && wallX[i] + WALLWIDTH >= 75)
            {
                if ((flappyHeight + flappyV) >= 0 && (flappyHeight + flappyV) <= gap[i]
                        || (flappyHeight + flappyV + 25) >= gap[i] + 100 && (flappyHeight + flappyV + 25) <= HEIGHT)
                {
                    gameOver = true;
                }
            }

            if(flappyHeight + flappyV <= 0 || flappyV + flappyHeight + 25 >= HEIGHT)
            {
                gameOver = true;
            }

            //75 flappy
            if (75 > wallX[i] + WALLWIDTH)
            {
                score++;
            }

            if (wallX[i] + WALLWIDTH <= 0)
            {
                wallX[i] = WIDTH;
                gap[i] = (int)(Math.random() * (HEIGHT-150));
            }

        }

    }

    public void actionPerformed(ActionEvent e)
    {
        //kiekvienas frame
        //flappy judėjimas
        flappyA += flappyI;
        flappyV += flappyA;
        wallX[0] -= WALLXVELOCITY;
        wallX[1] -= WALLXVELOCITY;

        repaint();
    }

    public void keyTyped(KeyEvent e)
    {


    }
    public void keyPressed(KeyEvent e)
    {

        int code = e.getKeyCode();


        if(code == e.VK_SPACE)
        {
            flappyA = -10;
        }
        //kad nereiktu iskart space
        if (code == e.VK_S)
        {
            time.start();
        }
        //restart mirus
        if(code == e.VK_R)
        {
            time.stop();
            flappyHeight = HEIGHT / 4;
            flappyV = 0;
            flappyA = 8;
            score = 0;
            wallX[0] = WIDTH;
            wallX[1] = WIDTH + WIDTH / 2; //tarpas tarp sienu
            gap[0] = (int)(Math.random() * (HEIGHT-150));
            gap[1] = (int)(Math.random() * (HEIGHT-100));
            gameOver = false;

            repaint();
        }
    }
    public void keyReleased(KeyEvent e) {

    }
}
