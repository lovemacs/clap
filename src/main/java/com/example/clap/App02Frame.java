package com.example.clap;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: HanLiangYuan
 * Date: 14-10-14
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
public class App02Frame {

  public static void main(String[] args) {
    JFrame frame = new JFrame("app02");
    JPanel panel = new JPanel();
    JTextArea textArea = new JTextArea();

    panel.setLayout(new GridLayout());
    textArea.setText("test");  
    //当TextArea里的内容过长时生成滚动条  
    panel.add(new JScrollPane(textArea));  
    frame.add(panel);

    frame.setSize(200,200) ;
    frame.setVisible(true);
  }
}
