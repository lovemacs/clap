package painting;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: HanLiangYuan
 * Date: 14-10-15
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
public class Step01SwingPaint {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(250,250);
        f.setVisible(true);
    }
}
