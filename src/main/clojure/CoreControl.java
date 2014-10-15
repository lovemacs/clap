import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

class Grid extends JComponent {
    public void paint(Graphics g) {
        g.drawRect (10, 10, 800, 500);    

        for (int i = 10; i <= 800; i+= 10)
            g.drawLine (i, 10, i, 510);

        for (int i = 10; i <= 500; i+= 10)
            g.drawLine (10, i, 810, i);
    }
}

public class CoreControl {

    public static void main(String[] a) {
        JFrame window = new JFrame();
        window.setSize(840,560);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new Grid());
        window.setVisible(true);
    }

}