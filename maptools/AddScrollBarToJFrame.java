import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AddScrollBarToJFrame {
    public static void main(String[]args) {
        //Create a JPanel
        JPanel panel=new JPanel();

        //Create a scrollbar using JScrollPane and add panel into it's viewport
        //Set vertical and horizontal scrollbar always show
        JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Create a JFrame with title ( AddScrollBarToJFrame )
        JFrame frame=new JFrame("AddScrollBarToJFrame");

        //Add JScrollPane into JFrame
        frame.add(scrollBar);

        //Set close operation for JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set JFrame size
        frame.setSize(400,400);

        //Make JFrame visible. So we can see it.
        frame.setVisible(true);

        //So, if you want to add other component like JTextArea, just add them into JPanel.After that add
        //the JPanel into JScrollPane before add the JScrollPane into JFrame.
    }
}