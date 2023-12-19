package UI;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AdminMapTools extends JFrame {
    Controller backendController;

    public AdminMapTools(Controller backendController){
        this.backendController = backendController;
        this.setSize(1200,700);
        //ToolsPanel tools = new ToolsPanel();
        MainPanel mainPanel = new MainPanel(backendController);
        LeftScreenPanel leftScreenPanel = mainPanel.getLeftScreenPanel();
        //LeftScreenPanel leftScreen = new LeftScreenPanel("Admin Map Tools Panel",backendController);
        
        ToolsPanel toolsPanel = mainPanel.getToolsPanel();
        //MainPanel mainPanel = tools.getMainPanel();
        SettingsBarPanel settingsBar = new SettingsBarPanel();
        
        

        JPanel settingsAndTools = new JPanel();
        settingsAndTools.setLayout(new BorderLayout());
        settingsAndTools.add(settingsBar, BorderLayout.NORTH);
        settingsAndTools.add(toolsPanel, BorderLayout.CENTER);

        JPanel allMapTools = new JPanel();
        allMapTools.setLayout(new BorderLayout());
        allMapTools.add(settingsAndTools, BorderLayout.NORTH);
        allMapTools.add(mainPanel, BorderLayout.CENTER);
        

        this.add(leftScreenPanel, BorderLayout.WEST);
        this.add(allMapTools , BorderLayout.CENTER);
        this.setVisible(false);

    }
}