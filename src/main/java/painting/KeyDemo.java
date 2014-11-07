package painting;

import com.sun.imageio.plugins.jpeg.JPEGImageMetadataFormatResources;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created with IntelliJ IDEA.
 * User: HanLiangYuan
 * Date: 14-10-31
 * Time: 下午4:06
 * To change this template use File | Settings | File Templates.
 */
public class KeyDemo  {
    public static void main(String[] args) {
       JFrame jFrame = new JFrame();
        jFrame.setSize(300, 400);
       // jFrame.pack();
        jFrame.setVisible(true);
       jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       jFrame.addKeyListener(new KeyListener() {
           public void keyTyped(KeyEvent e) {
               System.out.println("e = " + e);
           }

           public void keyPressed(KeyEvent e) {
               System.out.println("e = " + e);
           }

           public void keyReleased(KeyEvent e) {
               System.out.println("e = " + e);
           }
       });



    }
/*
    private String getCorpName(Object corpName) throws RuntimeException {

        try {
            String strCorpName = (String)corpName;
            String strCorpName4Tomcat =  new String(strCorpName.getBytes("ISO-8859-1"), "utf-8");
            String strCorpName4Weblogic = java.net.URLDecoder.decode(strCorpName,"UTF-8");
            if(strCorpName.equals(strCorpName4Tomcat)) {
                return strCorpName4Tomcat;
            } else {
                return strCorpName4Weblogic;
            }
        } catch (Exception e) {
            return "";
             e.trace
            throw new RuntimeException("Compiled Code");
        }
    }*/


}
