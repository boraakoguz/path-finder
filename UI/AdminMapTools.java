package UI;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class AdminMapTools extends JFrame {
    Controller backendController;

    public AdminMapTools(Controller backendController){
        this.backendController = backendController;
        this.setSize(1200,700);
        MainPanel mainPanel = new MainPanel(backendController);
        LeftScreenPanel leftScreenPanel = mainPanel.getLeftScreenPanel();

        
        ToolsPanel toolsPanel = mainPanel.getToolsPanel();
        JPanel settingsAndTools = new JPanel();
        settingsAndTools.setLayout(new BorderLayout());
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