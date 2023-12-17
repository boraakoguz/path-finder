package UI;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class LeftScreenPanel extends JPanel {
    private JLabel labelTitle;
    private JLabel labelMap = new JLabel("Select Map");
    private JLabel labelBuilding = new JLabel("Select Building");
    private JLabel labelFloor = new JLabel("Select Floor");
    private JLabel labelRoom = new JLabel("Select Room");
    private JComboBox mapBox = new JComboBox<>();
    private JComboBox buildingBox = new JComboBox<>(); 
    private JComboBox floorBox = new JComboBox<>();
    private JComboBox roomBox = new JComboBox<>();
    private JButton button1;
    private JButton button2;
    GridBagConstraints c = new GridBagConstraints();
    

    public LeftScreenPanel(String title) {
        labelTitle = new JLabel(title);
        labelTitle.setFont(new Font("Serif", Font.ITALIC, 25));
        setBackground(Color.PINK);

        setLayout(new GridBagLayout());
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;        
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 2;
        
        c.insets = new Insets(0, 10, 50, 10);
        c.gridx = 0;
        c.gridy = 0;
        add(labelTitle, c);

        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 0;
        c.gridy = 1;
        add(labelMap, c);

        c.insets = new Insets(0, 10, 5, 10);
        c.gridx = 0;
        c.gridy = 2;
        add(mapBox, c);
        
        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 0;
        c.gridy = 3;
        add(labelBuilding, c);

        c.insets = new Insets(0, 10, 5, 10);
        c.gridx = 0;
        c.gridy = 4;
        add(buildingBox, c);

        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 0;
        c.gridy = 5;
        add(labelFloor, c);

        c.insets = new Insets(0, 10, 5, 10);
        c.gridx = 0;
        c.gridy = 6;
        add(floorBox, c);

        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 0;
        c.gridy = 7;
        add(labelRoom, c);

        c.insets = new Insets(0, 10, 50, 10);
        c.gridx = 0;
        c.gridy = 8;
        add(roomBox, c);

        c.gridwidth = 1;
    }
    
    public LeftScreenPanel(String type, JButton button1) {
        this(type);
        this.button1 = button1;
        c.gridx = 0;
        c.gridy = 9;
        add(button1, c);

    }

    public LeftScreenPanel(String type, JButton button1, JButton button2) {
        this(type, button1);
        this.button2 = button2;
        c.gridx = 1;
        c.gridy = 9;
        add(button2, c);
    }


    public void fillMapBoxes() {

    }

    public void fillBuildingBox() {

    }

    public void fillFloorBox() {
        
    }

    public void fillRoomBox() {
        
    }


}
