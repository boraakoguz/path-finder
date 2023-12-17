package UI;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LeftScreenPanel extends JPanel implements ActionListener {
    private JLabel labelTitle;
    private JLabel labelMap = new JLabel("Select Map");
    private JLabel labelBuilding = new JLabel("Select Building");
    private JLabel labelFloor = new JLabel("Select Floor");
    private JLabel labelRoom = new JLabel("Select Room");
    private JComboBox mapBox = new JComboBox<>();
    private JComboBox buildingBox = new JComboBox<>(); 
    private JComboBox floorBox = new JComboBox<>();
    private JComboBox roomBox = new JComboBox<>();

    private JButton backButton = new JButton(new ImageIcon(((ImageIcon) new JButton(new ImageIcon("image (20).png")).getIcon()).getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH)));

    private JButton button1;
    private JButton button2;
    GridBagConstraints c = new GridBagConstraints();
    

    public LeftScreenPanel(String title) {
        labelTitle = new JLabel(title);
        labelTitle.setFont(new Font("Serif", Font.ITALIC, 25));
        setBackground(Color.PINK);
        
        backButton.setBackground(Color.PINK);
        //setLayout(null);
        backButton.setLocation(10, 10);
        add(backButton);
        backButton.addActionListener(this);

        setLayout(new GridBagLayout());
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;        
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 2;
        
        c.insets = new Insets(0, 10, 50, 10);
        c.gridx = 0;
        c.gridy = 1;
        add(labelTitle, c);

        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 0;
        c.gridy = 2;
        add(labelMap, c);

        c.insets = new Insets(0, 10, 5, 10);
        c.gridx = 0;
        c.gridy = 3;
        add(mapBox, c);
        
        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 0;
        c.gridy = 4;
        add(labelBuilding, c);

        c.insets = new Insets(0, 10, 5, 10);
        c.gridx = 0;
        c.gridy = 5;
        add(buildingBox, c);

        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 0;
        c.gridy = 6;
        add(labelFloor, c);

        c.insets = new Insets(0, 10, 5, 10);
        c.gridx = 0;
        c.gridy = 7;
        add(floorBox, c);

        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 0;
        c.gridy = 8;
        add(labelRoom, c);

        c.insets = new Insets(0, 10, 50, 10);
        c.gridx = 0;
        c.gridy = 9;
        add(roomBox, c);

        c.gridwidth = 1;

        
    }
    
    public LeftScreenPanel(String type, JButton button1) {
        this(type);
        this.button1 = button1;
        c.gridx = 0;
        c.gridy = 10;
        add(button1, c);

    }

    public LeftScreenPanel(String type, JButton button1, JButton button2) {
        this(type, button1);
        this.button2 = button2;
        c.gridx = 1;
        c.gridy = 10;
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


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        
        LeftScreenPanel panel = new LeftScreenPanel("sdsdsfds");
        frame.add(panel);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            System.out.println("Back");
        }
        //TODO: ADD OTHER BUTONS
    }

}
