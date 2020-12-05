package flappy;

import javax.swing.JFrame;


public class FlappyFrame extends JFrame {

    public  FlappyFrame() {

        add(new FlappyPanel());

        setSize(525, 550);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new FlappyFrame();
    }

}
