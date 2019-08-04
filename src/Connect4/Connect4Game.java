package Connect4;

import javax.swing.*;
import java.awt.*;

public class Connect4Game extends JFrame {

    public static void main(String[] args){

        Connect4Frame connect4Frame=new Connect4Frame();
        connect4Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connect4Frame.setSize(600,600);
        connect4Frame.setVisible(true);

    }

}
