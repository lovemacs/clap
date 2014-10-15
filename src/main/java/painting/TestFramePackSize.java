package painting;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class TestFramePackSize extends JFrame {
    
    public TestFramePackSize(){
        
//        this.setSize(800, 600);
        
        /*
         * 总结：
         * 1、单独使用setSize()时，是按照设置的大小显示的；此时不能使用pack()，否则按照pack()自动适配
         * 2、单独使用pack()时，是按照组件的大小自动适配的
         * 3、单独使用setPreferredSize()时，设置的大小无效，必须在后面添加pack()配合显示
         * 
         * pack() 调整此窗口的大小，以适合其子组件的首选大小和布局
         * 就算JFrame用setSize来设定框体大小，用了pack（）一样会改变成刚好能包含全部组件的框体。
         * 
         * 当使用setPreferredSize()时需要配合pack()显示，否则setPreferredSize()的设置效果不生效
         * 
         * 
         */
        
        this.setPreferredSize(new Dimension(800, 600));
//        this.pack();
        
        
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
    public static void main(String args[]){
        new TestFramePackSize();
        
    }
    
}