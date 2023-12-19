package UI;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class EditorMapTools extends JFrame {
    Controller backendController;
    public Path_Finder_Frame contFrame;

    public EditorMapTools(Controller backendController,Path_Finder_Frame m){
        this.backendController = backendController;
        contFrame=m;
        this.setSize(1200,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
        MainPanelEditor mainPanel = new MainPanelEditor(backendController,contFrame);
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