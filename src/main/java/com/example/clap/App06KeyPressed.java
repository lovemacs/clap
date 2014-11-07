package com.example.clap;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created with IntelliJ IDEA.
 * User: HanLiangYuan
 * Date: 14-10-28
 * Time: 下午2:33
 * To change this template use File | Settings | File Templates.
 */
public class App06KeyPressed extends JPanel implements KeyListener {

    public static void main(String[] args) {
        App06KeyPressed app06KeyPressed = new App06KeyPressed();
        JFrame jFrame = new JFrame();
        jFrame.add(app06KeyPressed);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
