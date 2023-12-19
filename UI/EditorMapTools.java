package UI;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class EditorMapTools extends JFrame {
    Controller backendController;

    public EditorMapTools(Controller backendController){
        this.backendController = backendController;
        this.setSize(1200,700);
        MainPanelEditor mainPanel = new MainPanelEditor(backendController);
        LeftScreenPanelEditor leftScreenPanel = mainPanel.getLeftScreenPanel();
        
        ToolsPanelEditor toolsPanel = mainPanel.getToolsPanel();
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