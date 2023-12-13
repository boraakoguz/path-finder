import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestGUIert {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        
        frame.setSize(400,400);
        
        
        LeftScreenPanel panel = new LeftScreenPanel("edf");

        frame.add(panel);
        frame.setVisible(true);
        System.out.println("dscs");
    }
}
