import javax.swing.JPanel;

import java.awt.Color;
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
    private JButton button1;
    private JButton button2;
    private JComboBox mapBox;
    private JComboBox buildingBox;
    private JComboBox floorBox;
    private JComboBox roomBox;

    public LeftScreenPanel(String title) {
        labelTitle = new JLabel(title);
        setBackground(Color.PINK);
        add(labelTitle);
        add(labelMap);
        add(labelBuilding);
        add(labelFloor);
        add(labelRoom);


    }
    
    public LeftScreenPanel(String type, JButton button1) {
        this(type);
        this.button1 = button1;
    }

    
    public LeftScreenPanel(String type, JButton button1, JButton button2) {
        this(type, button1);
        this.button2 = button2;
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
