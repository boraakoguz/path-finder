package UI;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AdminMapTools extends JFrame {
    Controller backendController;
    public Path_Finder_Frame contFrame;

    public AdminMapTools(Controller backendController,Path_Finder_Frame m){
        this.backendController = backendController;
        contFrame=m;
        this.setSize(1200,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
        MainPanel mainPanel = new MainPanel(backendController,contFrame);
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