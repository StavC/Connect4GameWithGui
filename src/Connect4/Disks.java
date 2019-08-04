package Connect4;

import javax.swing.*;
import java.awt.*;

public class Disks extends JPanel {

    private Color diskColor;



    public Disks(){
        this.diskColor=Color.white;
        repaint();
    }


    public Color getDiskColor() {
        return diskColor;
    }

    public void setDiskColor(Color diskColor) {
        this.diskColor = diskColor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {// painting the disks with their border
        super.paintComponent(g);


        g.setColor(this.diskColor);
        g.fillOval(10,10,this.getWidth()-20,this.getHeight()-20);
        g.setColor(Color.black);
        g.drawRect(0,0,this.getWidth(),this.getHeight());



    }
}
