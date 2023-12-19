package UI;
import javax.swing.JPanel;

import Building.Map;
import Building.Space;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class LeftScreenPanelEditor extends JPanel{
    private Controller backendController;

    private JLabel labelTitle;
    private JLabel labelMap = new JLabel("Select Map");
    private JLabel labelBuilding = new JLabel("Select Building");
    private JLabel labelFloor = new JLabel("Select Floor");
    private JLabel labelRoom = new JLabel("Select Room");
    private JComboBox<Space> mapBox = new JComboBox<Space>();
    private JComboBox<Space> buildingBox = new JComboBox<Space>(); 
    private JComboBox<Space> floorBox = new JComboBox<Space>();
    private JComboBox<Space> roomBox = new JComboBox<Space>();
    private JButton backButton = new JButton("Back");
    GridBagConstraints c = new GridBagConstraints();
    

    public LeftScreenPanelEditor(String title, Controller controller) {
        this.backendController = controller;
        labelTitle = new JLabel(title);
        labelTitle.setFont(new Font("Serif", Font.ITALIC, 25));
        setBackground(Color.decode("#dd96b8"));

        backButton.setBackground(Color.PINK);
        backButton.setLocation(10, 10);
        add(backButton);

        fillMapBoxes();
        mapBox.addItemListener(new mapBoxListener());
        buildingBox.addItemListener(new buildBoxListener());
        floorBox.addItemListener(new floorBoxListener());
        roomBox.addItemListener(new roomBoxListener());

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
    public void emptyAllBoxes(){
        mapBox.removeAllItems();
        buildingBox.removeAllItems();
        floorBox.removeAllItems();
        roomBox.removeAllItems();
    }
    public void fillMapBoxes() {
        mapBox.removeAllItems();
        ArrayList<Map> maps = backendController.getAvailableMapObjects();
        mapBox.addItem(null);
        for (Map map : maps) {
            mapBox.addItem(map);
        }
    }

    public void fillBuildingBox(Space map) {
        buildingBox.removeAllItems();
        buildingBox.addItem(null);
        if(map != null){
            for (Space building : map.getContents()) {
                buildingBox.addItem(building);
            }
        }  
    }

    public void fillFloorBox(Space building) {
        floorBox.removeAllItems();
        floorBox.addItem(null);
        if(building != null){
            for (Space floorSpace : building.getContents()) {
                floorBox.addItem(floorSpace);
            }
        }
    }

    public void fillRoomBox(Space floor) {
        roomBox.removeAllItems();
        roomBox.addItem(null);
        if(floor != null){
            for (Space room : floor.getContents()) {
                roomBox.addItem(room);
            }
        }
        
    }

    class mapBoxListener implements ItemListener{
         @Override
        public void itemStateChanged(ItemEvent e) {
            if(mapBox.getSelectedItem() != null){
                Space map = (Space) mapBox.getSelectedItem();
                fillBuildingBox(map);
                backendController.setCurrentDrawContextEditor((Space)mapBox.getSelectedItem());
            }
            else{
                fillBuildingBox(null);
                backendController.setCurrentDrawContextEditor(null);
            }     
        }
    }
    class buildBoxListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(buildingBox.getSelectedItem() != null){
                Space building = (Space) buildingBox.getSelectedItem();
                fillFloorBox(building); 
                backendController.setCurrentDrawContextEditor((Space)buildingBox.getSelectedItem());
            }
            else{
                fillFloorBox(null);
                backendController.setCurrentDrawContextEditor((Space)mapBox.getSelectedItem());
            }
        }
    }
    class floorBoxListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(floorBox.getSelectedItem() != null){
                Space floor = (Space) floorBox.getSelectedItem();
                fillRoomBox(floor);
                backendController.setCurrentDrawContextEditor((Space)floorBox.getSelectedItem());
            }
            else{
                fillRoomBox(null);
                backendController.setCurrentDrawContextEditor((Space)buildingBox.getSelectedItem());
            }        
        }
    }
    class roomBoxListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(roomBox.getSelectedItem() != null){
                backendController.setCurrentDrawContextEditor((Space)roomBox.getSelectedItem());
            }
            else{
                backendController.setCurrentDrawContextEditor((Space)floorBox.getSelectedItem());
            }
        }
    }
}
