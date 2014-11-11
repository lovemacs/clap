package image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: HanLiangYuan
 * Date: 14-10-15
 * Time: 上午10:31
 * http://docs.oracle.com/javase/tutorial/uiswing/painting/index.html.
 */
public class Step04RefiningTheDesign {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? " +
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }


}

class MyPanel extends JPanel {


    RedSquare redSquare = new RedSquare();

    public MyPanel() {

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(), e.getY());
            }
        });

    }

    private void moveSquare(int x, int y) {
        // Current square state, stored as final variables
        // to avoid repeat invocations of the same methods.
        final int OFFSET = 1;
        final int squareX = redSquare.getSquareX();
        final int squareY = redSquare.getSquareY();
        final int squareW = redSquare.getSquareW();
        final int squareH = redSquare.getSquareH();

        if ((squareX != x) || (squareY != y)) {

            // The square is moving, repaint background
            // over the old square location.
            repaint(squareX, squareY, squareW + OFFSET, squareH + OFFSET);

            // Update coordinates.
            redSquare.setSquareX(x);
            redSquare.setSquareY(y);

            // Repaint the square at the new location.
            repaint(x, y, squareW + OFFSET, squareH + OFFSET);

        }
    }


    public Dimension getPreferredSize() {
        return new Dimension(250, 200);
    }

    protected void paintComponent(Graphics g) {

        // Let UI Delegate paint first, which
        // includes background filling since
        // this component is opaque.
        super.paintComponent(g);

        g.drawString("This is my custom Panel!", 10, 20);
        g.setColor(Color.RED);

        redSquare.paintComponent(g);
    }
}


class RedSquare {

    private Image image;

    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;

    RedSquare() {
        try {
            image = ImageIO.read(new File("examples/clojure-icon.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int getSquareX() {
        return squareX;
    }

    public void setSquareX(int squareX) {
        this.squareX = squareX;
    }

    public int getSquareY() {
        return squareY;
    }

    public void setSquareY(int squareY) {
        this.squareY = squareY;
    }

    public int getSquareW() {
        return squareW;
    }

    public void setSquareW(int squareW) {
        this.squareW = squareW;
    }

    public int getSquareH() {
        return squareH;
    }

    public void setSquareH(int squareH) {
        this.squareH = squareH;
    }



    protected void paintComponent(Graphics g) {
//        g.setColor(Color.RED);
//        g.fillRect(squareX, squareY, squareW, squareH);
//        g.setColor(Color.BLACK);
//        g.drawRect(squareX, squareY, squareW, squareH);
        g.drawImage(image,squareX, squareY, squareW, squareH, null);
    }
}