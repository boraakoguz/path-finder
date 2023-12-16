import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class TestGUIert {
    public TestGUIert(){
        JFrame frame = new JFrame();
        
        frame.setSize(700,400);
        
        JButton button1 = new JButton("Select");
        LeftScreenPanel leftScreen = new LeftScreenPanel("Admin Map Tools Panel", button1);
        MainPanel mainPanel = new MainPanel();
        SettingsBarPanel settingsBar = new SettingsBarPanel();
        ToolsPanel tools = new ToolsPanel(mainPanel);
        

        JPanel settingsAndTools = new JPanel();
        settingsAndTools.setLayout(new BorderLayout());
        settingsAndTools.add(settingsBar, BorderLayout.NORTH);
        settingsAndTools.add(tools, BorderLayout.CENTER);

        JPanel allMapTools = new JPanel();
        allMapTools.setLayout(new BorderLayout());
        allMapTools.add(settingsAndTools, BorderLayout.NORTH);
        allMapTools.add(mainPanel, BorderLayout.CENTER);
        

        frame.add(leftScreen, BorderLayout.WEST);
        frame.add(allMapTools , BorderLayout.CENTER);

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        TestGUIert test = new TestGUIert();
    }

    
}