package UI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class TestGUIert {
    public TestGUIert(){
        JFrame frame = new JFrame();
        
        frame.setSize(400,400);
        
        JButton button1 = new JButton("Next");
        JButton button2 = new JButton("Find Objects");
        //LeftScreenPanel panel = new LeftScreenPanel("Where do you want to go?");
        //LeftScreenPanel panel = new LeftScreenPanel("Where do you want to go?", button1);
        LeftScreenPanel panel = new LeftScreenPanel("Where do you want to go?", button1, button2);
        
        frame.add(panel);
        frame.setVisible(true);
        System.out.println("working!");
    }

    public static void main(String[] args) {
        new TestGUIert();
    }
}
