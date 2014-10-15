package com.example.clap;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: HanLiangYuan
 * Date: 14-10-15
 * Time: 上午9:16
 * To change this template use File | Settings | File Templates.
 */
public class App03FillRect extends JPanel{
    @Override
    public void paint(Graphics g) {
       g.fillRect(0, 0, 20, 20);

       g.drawString("hello,world",0,30);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App03FillRect");
        frame.add(new App03FillRect());

        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
