import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SettingsBarPanel extends JPanel{
    JMenuBar menuBar = new JMenuBar();
    JMenu addMenu = new JMenu("Add");
    JMenu deleteMenu = new JMenu("Delete");
    JMenu editMenu = new JMenu("Edit");
    JMenu settingsMenu = new JMenu("Settings");
    JMenuItem infoMenu = new JMenuItem("Info");

    JMenu addSpace = new JMenu("Add Space");
    JMenu deleteSpace = new JMenu("Delete Space");
    JMenu editSpace = new JMenu("Edit Space");

    JMenu addObject = new JMenu("Add Object");
    JMenu deleteObject = new JMenu("Delete Object");
    JMenu editObject = new JMenu("Edit Object");
    JMenuItem tesItem1 = new JMenuItem("tesItem1");
    JMenuItem tesItem2 = new JMenuItem("tesItem2");

    

    public SettingsBarPanel() {
        setLayout(new BorderLayout());
        
        addMenu.add(addSpace);
        addMenu.add(addObject);
        menuBar.add(addMenu);

        deleteMenu.add(deleteSpace);
        deleteMenu.add(deleteObject);
        menuBar.add(deleteMenu);

        editMenu.add(editSpace);
        editMenu.add(editObject);
        menuBar.add(editMenu);

        menuBar.add(settingsMenu);
        menuBar.add(infoMenu);

        infoMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("esdfsfs");
            }
            
        });

        
        add(menuBar, BorderLayout.NORTH);
        //add(menuBar);
    }
}
