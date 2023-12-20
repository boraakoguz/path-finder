package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.util.Currency;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;
import javax.swing.text.StyledEditorKit.FontSizeAction;

import Building.Building;
import Building.Floor;
import Building.Map;
import Building.MapObject;
import Building.Room;
import Building.Space;

public class DrawPanel extends JPanel implements MouseInputListener{
    MainPanel mainPanel;
    LeftScreenPanel leftScreenPanel;
    ToolsPanel toolsPanel;

    Controller backendController;
    Space activeSpace;
    
    public int testX;
    public int testY;
    public int testWidth;
    public int testHeight;

    int originalX;
    int originalY;
    int originalWidth;
    int originalHeight;

    int moveFirstX;
    int moveFirstY;

    int previousX;
    int previousY;

    boolean drawingIsSuccesful;

    Space editedSpace;

    int floorNumber = 0;

    public int resizeDefaultVaules(int value) {
        return value/(mainPanel.getCurrentZoom()+2);
    }

    public int resizeNormalValues(int value) {
        return value*(mainPanel.getCurrentZoom()+2);
    }

    public DrawPanel(MainPanel mainPanel, LeftScreenPanel leftScreenPanel, ToolsPanel toolsPanel, Controller controller){

        this.backendController = controller;
        this.mainPanel = mainPanel;
        this.leftScreenPanel = leftScreenPanel;
        this.toolsPanel = toolsPanel;

        setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        
    }
    public void setActiveSpace(Space space){
        this.activeSpace = space;
        repaint();
    }

    //TODO: Delete these params
    public void addSpace(int x, int y, int width, int height) {
        paintDrawTempOriginal(getGraphics());
        
        String name = JOptionPane.showInputDialog(mainPanel, 
                                        "X-Cor: " + originalX +
                                        "\nY-Cor: " + originalY +
                                        "\nWidth: " + originalWidth +
                                        "\nHeight: " + originalHeight +
                                        "\nColor: " + String.format("#%06x", mainPanel.getCurrentColor().getRGB() & 0x00FFFFFF)                            
                                        ,"Add Space", 1);

        //TODO: Can be checked if there is a place with the same name.
        //TODO: If there is alternative place name can be entered instead of previous one ,or it can be asked
        //TODO: to user to enter another value
        if(name == null || name.equals("")) {
            repaint();
        }
        else {
            Space newSpace;
            if(this.activeSpace instanceof Map){
                newSpace = new Building(name);
            }
            else if(this.activeSpace instanceof Floor){
                newSpace = new Room(name);
            }
            else{
                newSpace = new Map(name);
            }
            newSpace.setX(originalX);
            newSpace.setY(originalY);
            newSpace.setWidth(originalWidth);
            newSpace.setHeight(originalHeight);
            newSpace.setColor(mainPanel.getCurrentColor());
            newSpace.setEntranceX(originalX+originalWidth/2);
            newSpace.setEntranceY(originalY+originalHeight);
            
            
            if(this.activeSpace instanceof Map){
                ((Building)newSpace).addFloor(0);
            }

            backendController.addSpace(this.activeSpace, newSpace);
            backendController.save();
            if(this.activeSpace instanceof Map){
                leftScreenPanel.fillBuildingBox(this.activeSpace);
            }
            else if(this.activeSpace instanceof Building){
                leftScreenPanel.fillFloorBox(this.activeSpace);
            }
            else if(this.activeSpace instanceof Floor){
                leftScreenPanel.fillRoomBox(this.activeSpace);
            }
            else{
                leftScreenPanel.fillMapBoxes();
            } 
            repaint();
        }
    }
    public void addObject(int x, int y, int width, int height) {
        paintDrawTempOriginal(getGraphics());
        
        String name = JOptionPane.showInputDialog(mainPanel, 
                                        "X-Cor: " + originalX +
                                        "\nY-Cor: " + originalY +
                                        "\nWidth: " + originalWidth +
                                        "\nHeight: " + originalHeight +
                                        "\nColor: " + String.format("#%06x", mainPanel.getCurrentColor().getRGB() & 0x00FFFFFF)                            
                                        ,"Add Space", 1);

        if(name == null || name.equals("")) {
            repaint();
        }
        else { 
            if(!(this.activeSpace instanceof Floor)){
                return;
            }
            MapObject newSpace = new MapObject(name, 1, true);
            newSpace.setX(originalX);
            newSpace.setY(originalY);
            newSpace.setWidth(originalWidth);
            newSpace.setHeight(originalHeight);
            newSpace.setColor(mainPanel.getCurrentColor());
            newSpace.setEntranceX(originalX+originalWidth/2);
            newSpace.setEntranceY(originalY+originalHeight);
            backendController.addMapObjectToFloor((Floor)this.activeSpace, newSpace);
            backendController.save();
            if(this.activeSpace instanceof Map){
                leftScreenPanel.fillBuildingBox(this.activeSpace);
            }
            else if(this.activeSpace instanceof Building){
                leftScreenPanel.fillFloorBox(this.activeSpace);
            }
            else if(this.activeSpace instanceof Floor){
                leftScreenPanel.fillRoomBox(this.activeSpace);
            }
            else{
                leftScreenPanel.fillMapBoxes();
            } 
            repaint();
        }
    }

    public void deleteSpace(int x, int y) {
        int deleteX = resizeDefaultVaules(x);
        int deleteY = resizeDefaultVaules(y);
        for(Space space : this.activeSpace.getContents()){
            if(
                deleteX > space.getX() &&
                deleteY > space.getY() &&
                deleteX < space.getX() + space.getWidth() &&
                deleteY < space.getY() + space.getHeight()
            ) {
                backendController.deleteSpace(space);
                repaint();
                break;
            }
        }
    }
    public void selectSpace(int x, int y) {
        int editX = resizeDefaultVaules(x);
        int editY = resizeDefaultVaules(y);
        for(Space space : this.activeSpace.getContents()){
            if(
                editX > space.getX() &&
                editY > space.getY() &&
                editX < space.getX() + space.getWidth() &&
                editY < space.getY() + space.getHeight()                
            ) {
                backendController.setCurrentDrawContext(space);
                repaint();
                break;
            }
        } 
    }
    
    //TODO Combine edit and delete methods there are several same codes.
    public void editSpace(int x, int y) {
        int editX = resizeDefaultVaules(x);
        int editY = resizeDefaultVaules(y);
        System.out.println(editX +", " + editY);
        System.out.println(activeSpace);
        boolean isClickedOn = false;
        for(Space space : this.activeSpace.getContents()){
            if(
                editX > space.getX() &&
                editY > space.getY() &&
                editX < space.getX() + space.getWidth() &&
                editY < space.getY() + space.getHeight()                
            ) { 
                isClickedOn = true;
                editedSpace = space;
                break;
            }
        }
        if(isClickedOn == false) {
            editedSpace = activeSpace;
            System.out.println("İnside also?");
        }

        JButton locationButton = new JButton("Change Location");
        JButton colorButton = new JButton(" Change Color");
        JButton nameButton = new JButton("Change Name");
        JButton enteranceButton = new JButton("Change Enterance");
        JButton upStairsEnterenceButton = new JButton("Change Up Stairs' Enterence");
        JButton downStairsEnterenceButton = new JButton("Change Down Stairs' Enterence");

        locationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField field1 = new JTextField();
                JTextField field2 = new JTextField();

                int maxX = editedSpace.getParent().getX() + editedSpace.getParent().getWidth() - editedSpace.getWidth();
                int minX = editedSpace.getParent().getX();
                int maxY = editedSpace.getParent().getY() + editedSpace.getParent().getHeight() - editedSpace.getHeight();
                int minY = editedSpace.getParent().getY();

                String newXRange = "New X-Cor Range (" + minX + " - " + maxX + ")";
                String newYRange = "New Y-Cor Range(" + minY + " - " + maxY + ")";

                String text1 = "Information about the space '" + editedSpace.getName() + "'";
                String text2 =  "X-Cor: " + editedSpace.getX() + "\n" +
                                "Y-Cor: " + editedSpace.getY() + "\n\n";
                                
                Object [] fields = {
                text1, text2,
                newXRange, field1,
                newYRange, field2                
                };

                //TODO: add All conditions, entering letter, not entering, entering to big number, entering zero or negative etc
                int chooice = JOptionPane.showConfirmDialog(mainPanel,fields,"Change Location",JOptionPane.OK_CANCEL_OPTION);
                if(
                    chooice == JOptionPane.OK_OPTION &&
                    !field1.getText().equals("") &&
                    !field2.getText().equals("")      
                    ) 
                    {
                        try {
                            int newX = Integer.parseInt(field1.getText());
                            int newY = Integer.parseInt(field2.getText());

                            if(newX <= maxX && newX >= minX && newY <= maxY && newY >= minY) {
                                editedSpace.setEntranceX(editedSpace.getEntranceX() + newX - editedSpace.getX());
                                editedSpace.setEntranceY(editedSpace.getEntranceY() + newY - editedSpace.getY());
                                editedSpace.setX(newX);
                                editedSpace.setY(newY);
                                repaint();
                                JOptionPane.showMessageDialog(mainPanel, "Location of the " + editedSpace + " changed successfully",
                                "Invalid", JOptionPane.DEFAULT_OPTION);
                            }
                            else {
                                JOptionPane.showMessageDialog(mainPanel, "You should enter the values within the range", "Invalid", JOptionPane.DEFAULT_OPTION);
                            }
                        } catch (Exception e2) {
                            JOptionPane.showMessageDialog(mainPanel, "You should enter number!");
                        }
                }
                else if(chooice == JOptionPane.OK_OPTION){
                    JOptionPane.showMessageDialog(mainPanel, "You should enter number!");
                }                
            }
        });

        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(mainPanel, JColorChooser.SELECTION_MODEL_PROPERTY, editedSpace.getColor());                                        
                if(newColor != null) {
                    editedSpace.setColor(newColor);
                    repaint();
                    JOptionPane.showMessageDialog(mainPanel, "Color changed successfully!");             
                }           
            }
        });

        nameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = JOptionPane.showInputDialog(mainPanel, "Enter New Name", "Change Name", JOptionPane.PLAIN_MESSAGE);
                if(newName != null && !newName.equals("")) {
                    editedSpace.setName(newName);
                    repaint();
                }
            }
        });

        enteranceButton.addActionListener(new ActionListener() {
            String newSide;
            boolean isOnX;
            int maxCor;
            int minCor;
            int corOtherComp;
            int directionChooice = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton eastButton = new JButton("EAST");
                JButton westButton = new JButton(" WEST");
                JButton northButton = new JButton("NORTH");
                JButton southButton = new JButton("SOUTH");
                
                eastButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newSide = "EAST";
                        maxCor = editedSpace.getY() + editedSpace.getHeight();
                        minCor = editedSpace.getY();
                        corOtherComp = editedSpace.getX() + editedSpace.getWidth();
                        isOnX = false;
                        directionChooice = 1;
                    }                            
                });
                westButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newSide = "WEST";
                        maxCor = editedSpace.getY() + editedSpace.getHeight();
                        minCor = editedSpace.getY();
                        corOtherComp = editedSpace.getX();
                        isOnX = false;
                        directionChooice = 2;
                    }                            
                });
                northButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newSide = "NORTH";
                        maxCor = editedSpace.getX() + editedSpace.getWidth();
                        minCor = editedSpace.getX();
                        corOtherComp = editedSpace.getY();
                        isOnX = true;
                        directionChooice = 3;
                    }                            
                });
                southButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newSide = "SOUTH";
                        maxCor = editedSpace.getX() + editedSpace.getWidth();
                        minCor = editedSpace.getX();
                        corOtherComp =  editedSpace.getY()+ editedSpace.getHeight();
                        isOnX = true;
                        directionChooice = 4;
                    }                            
                });
                Object [] directionButtons = {
                    eastButton, westButton, northButton, southButton
                };
                int chooice = JOptionPane.showConfirmDialog(mainPanel,directionButtons,"Choose Side",JOptionPane.CANCEL_OPTION);
                if(chooice == JOptionPane.OK_OPTION && directionChooice != 0) {
                    String text = "";
                    if(isOnX) {
                        text = "Enter Enterance X-Cor.";
                    }
                    else {
                        text = "Enter Enterance Y-Cor";
                    }
                    text += "\nThe value must be between " + minCor + " and " + maxCor;
                    String newName = JOptionPane.showInputDialog(mainPanel, text, "Change Enterence Location", JOptionPane.PLAIN_MESSAGE);
                    
                    try {
                        int newValue = Integer.parseInt(newName);
                        if(newValue > minCor && newValue < maxCor) {
                            if(isOnX) {
                                editedSpace.setEntranceX(newValue);
                                editedSpace.setEntranceY(corOtherComp);
                            }
                            else {
                                editedSpace.setX(corOtherComp);
                                editedSpace.setY(newValue);
                            }
                            repaint();
                        }

                    } catch (Exception e2) {}
                    
                }      
                directionChooice = 0;
            }       
        });

        upStairsEnterenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Floor currentFloor = (Floor)editedSpace;
                JTextField field1 = new JTextField();
                JTextField field2 = new JTextField();

                String text1 = "Information about the space '" + currentFloor.getCustomString() + "'";
                String text2 =  "Up Stairs X: " + currentFloor.getUpStairX() + "\n" +
                                "Up Stairs Y: " + currentFloor.getUpStairY() + "\n\n";

                String newXRange = "New Up Stairs X-Range (" + currentFloor.getX() + " - " + (currentFloor.getX() + currentFloor.getWidth()) + ")";
                String newYRange = "New Up Stairs Y-Range(" + currentFloor.getY() + " - " + (currentFloor.getY() + currentFloor.getHeight()) + ")";
                
                Object [] fields = {
                text1, text2,
                newXRange, field1,
                newYRange, field2,
                };

                int chooice = JOptionPane.showConfirmDialog(mainPanel,fields,"Change Up Stairs' Enterance",JOptionPane.OK_CANCEL_OPTION);
                if(chooice == JOptionPane.OK_OPTION){                    
                    try {   
                            int newUpStairsX = Integer.parseInt(field1.getText());
                            int newUpStairsY = Integer.parseInt(field2.getText());
                            if(
                                newUpStairsX >= currentFloor.getX() && 
                                newUpStairsX <= currentFloor.getX() + currentFloor.getWidth() &&
                                newUpStairsY >= currentFloor.getY() &&
                                newUpStairsY <= currentFloor.getY() + currentFloor.getHeight()
                                ) 
                                {
                                    currentFloor.setUpStairsX(newUpStairsX);
                                    currentFloor.setUpStairsY(newUpStairsY);
                                    System.out.println("Drawn");
                                    repaint();
                                    JOptionPane.showMessageDialog(mainPanel, "Color changed successfully!");
                            }
                            else {
                                JOptionPane.showMessageDialog(mainPanel, "You should enter values, in range!");
                            }
                        } catch (Exception e2) {
                            JOptionPane.showMessageDialog(mainPanel, "You should enter number");
                    }                    
                }   
            }    
        });

        downStairsEnterenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Floor currentFloor = (Floor)editedSpace;
                JTextField field1 = new JTextField();
                JTextField field2 = new JTextField();

                String text1 = "Information about the space '" + currentFloor.getCustomString() + "'";
                String text2 =  "Down Stairs X: " + currentFloor.getDownStairX() + "\n" +
                                "Down Stairs Y: " + currentFloor.getDownStairY() + "\n\n";

                String newXRange = "New Down Stairs X-Range (" + currentFloor.getX() + " - " + (currentFloor.getX() + currentFloor.getWidth()) + ")";
                String newYRange = "New Down Stairs Y-Range(" + currentFloor.getY() + " - " + (currentFloor.getY() + currentFloor.getHeight()) + ")";
                
                Object [] fields = {
                text1, text2,
                newXRange, field1,
                newYRange, field2,
                };

                int chooice = JOptionPane.showConfirmDialog(mainPanel,fields,"Change Down Stairs' Enterance",JOptionPane.OK_CANCEL_OPTION);
                if(chooice == JOptionPane.OK_OPTION){                    
                    try {   
                        int newDownStairsX = Integer.parseInt(field1.getText());
                        int newDownStairsY = Integer.parseInt(field2.getText());
                        if(
                            newDownStairsX >= currentFloor.getX() && 
                            newDownStairsX <= currentFloor.getX() + currentFloor.getWidth() &&
                            newDownStairsY >= currentFloor.getY() &&
                            newDownStairsY <= currentFloor.getY() + currentFloor.getHeight()
                            ) 
                            {
                                currentFloor.setDownStairsX(newDownStairsX);
                                currentFloor.setDownStairsY(newDownStairsY);
                                System.out.println("Drawn");
                                repaint();
                                JOptionPane.showMessageDialog(mainPanel, "Color changed successfully!");
                            }
                            else {
                                JOptionPane.showMessageDialog(mainPanel, "You should enter values, in range!");
                            }
                        } catch (Exception e2) {
                            JOptionPane.showMessageDialog(mainPanel, "You should enter number");
                    }                   
                }   
            }    
        });
        

        Object [] buttons = {
            locationButton, colorButton, nameButton, enteranceButton
        };
        String editMessage = "Edit: ";

        if(editedSpace instanceof Room) {
            editMessage = "Edit Room: ";
        }
        else if(editedSpace instanceof Floor) {
            editMessage = "Edit Floor: ";
            buttons = new Object[]{
                upStairsEnterenceButton, downStairsEnterenceButton
            };
        }
        else if(editedSpace instanceof Building) {
            editMessage = "Edit Building: ";

        }
        else if(editedSpace instanceof Map) {
            editMessage = "Edit Map: ";
            buttons = new Object[]{
                nameButton
            };
        }
        JOptionPane optionPane = new JOptionPane(editMessage + editedSpace, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

        JDialog dialog;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(buttons.length,1));
        for (int i = 0; i < buttons.length; i++)
        {
            panel.add((JButton)buttons[i]);
        }
        optionPane.add(panel);
        dialog = optionPane.createDialog(mainPanel, "Edit");
        dialog.setVisible(true);
        
        backendController.save();  
        }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(activeSpace == null) {
            g.setColor(Color.DARK_GRAY);
            toolsPanel.disableEditing();
            g.setFont(new Font("Courier New", 1, resizeNormalValues(4)));
            g.drawString("To use the map tools, you should select a map from the left panel.",
                    resizeNormalValues(20),
                    resizeNormalValues(20));
                
        }
        else if(activeSpace instanceof Room) {
            g.setColor(Color.DARK_GRAY);
            toolsPanel.disableEditing();
            g.setFont(new Font("Courier New", 1, resizeNormalValues(4)));
            g.drawString("Inside of the room",
                    resizeNormalValues(20),
                    resizeNormalValues(20));
        }
        else if(activeSpace instanceof Building) {
            toolsPanel.buildingEditing();
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Courier New", 1, resizeNormalValues(3)));
            for(int i = 0; i < 200; i +=100) {
                for(int j = 3; j < 200; j+=4) {
                    g.drawString("You can't see the building. You have to choose a floor.",
                    resizeNormalValues(activeSpace.getX() + i),
                    resizeNormalValues(activeSpace.getY() + j));
                }                            
            }
        }
        else if(activeSpace instanceof Map) {
            toolsPanel.mapEditing();
            }

        else if(activeSpace instanceof Floor) {
            toolsPanel.floorEditing();
        }

        if(activeSpace instanceof Building || activeSpace instanceof Floor) {
            g.setColor(activeSpace.getColor());
            g.fillRect(
            0,
            0,
            getWidth(),
            resizeNormalValues(activeSpace.getY()));
            g.fillRect(
                0,
                0,
                resizeNormalValues(activeSpace.getX()),
                getHeight());
            g.fillRect(
                resizeNormalValues(activeSpace.getX() + activeSpace.getWidth()),
                0,
                resizeNormalValues(getWidth() - activeSpace.getX() - activeSpace.getWidth()),
                getHeight());
            g.fillRect(
                0,
                resizeNormalValues(activeSpace.getY() + activeSpace.getHeight()),
                getWidth(),
                resizeNormalValues(getHeight() - activeSpace.getY() - activeSpace.getHeight()));
        }
        
        if(activeSpace instanceof Map || activeSpace instanceof Floor) {
            for (Space space : this.activeSpace.getContents()) {
            if(space instanceof MapObject){
                g.drawImage(resize(((MapObject) space).getIcon(),resizeNormalValues(space.getWidth()),resizeNormalValues(space.getHeight())), resizeNormalValues(space.getX()), resizeNormalValues(space.getY()), null);
            }
            else if(space instanceof Building || space instanceof Room) {
                g.setColor(space.getColor());
                g.drawRect(
                    resizeNormalValues(space.getX()),
                    resizeNormalValues(space.getY()),
                    resizeNormalValues(space.getWidth()),
                    resizeNormalValues(space.getHeight()));
                g.setColor(Color.GREEN);
                g.fillRect(
                    resizeNormalValues(space.getEntranceX())-5,
                    resizeNormalValues(space.getEntranceY())-5,
                    10,
                    10);
                g.setColor(Color.BLACK);
                g.drawString(space.getName(),
                    resizeNormalValues(space.getX()),
                    resizeNormalValues(space.getY()+5));
            }       
            }
            if(activeSpace instanceof Floor){
                Floor currentFloor = (Floor)activeSpace;
                g.setColor(Color.RED);
                    g.fillRect(
                    resizeNormalValues(currentFloor.getDownStairX())-5,
                    resizeNormalValues(currentFloor.getDownStairY())-5,
                    10,
                    10);
                g.setColor(Color.YELLOW);
                        g.fillRect(
                        resizeNormalValues(currentFloor.getUpStairX())-5,
                        resizeNormalValues(currentFloor.getUpStairY())-5,
                        10,
                        10);
                
                if(currentFloor.getFloorNumber() == Floor.GROUND_FLOOR) {
                    g.setColor(Color.GREEN);
                        g.fillRect(
                        resizeNormalValues(currentFloor.getParent().getEntranceX())-5,
                        resizeNormalValues(currentFloor.getParent().getEntranceY())-5,
                        10,
                        10);
                }
            }        
        }
    } 

    
    public void paintPath(Graphics g, int x, int y){
        g.fillRect(x, y, 3, 3);
    }

    public void paintDrawTemp(Graphics g) {
        g.drawRect(testX, testY, testWidth, testHeight);
    }

    public void paintDrawTempOriginal(Graphics g) {
        g.drawRect(resizeNormalValues(originalX), resizeNormalValues(originalY), resizeNormalValues(originalWidth), resizeNormalValues(originalHeight));
    }

    public void paintEnterence(Graphics g, int x, int y) {
        g.fillRect(x-3, y-3, 6, 6);
    }
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return dimg;
    } 

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Clicked");
        paintComponent(getGraphics());
    }


    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Pressed");
        if(mainPanel.getCurrentAction() == MainPanel.DRAW_ACTION) {
            testX = e.getX();
            testY = e.getY();

            if(
                resizeDefaultVaules(testX) < activeSpace.getX() ||
                resizeDefaultVaules(testY) < activeSpace.getY() ||
                resizeDefaultVaules(testX) > activeSpace.getX() + activeSpace.getWidth() ||
                resizeDefaultVaules(testY) > activeSpace.getY() + activeSpace.getHeight()
            ) {
                drawingIsSuccesful = false;
                System.out.println(resizeDefaultVaules(testX) + " > " + activeSpace.getX());
                System.out.println(resizeDefaultVaules(testY) + " > " + activeSpace.getY());
                System.out.println(resizeDefaultVaules(testX) + " < " + activeSpace.getX() + activeSpace.getWidth());
                System.out.println(resizeDefaultVaules(testY) + " < " + activeSpace.getY() + activeSpace.getHeight());
                System.out.println("Drawing is not succesful");
            }
            else {
                drawingIsSuccesful = true;
            }
        }

        else if(mainPanel.getCurrentAction() == MainPanel.ERASER_ACTION) {
            deleteSpace(e.getX(), e.getY());
        }

        else if(mainPanel.getCurrentAction() == MainPanel.EDIT_ACTION) {
            editSpace(e.getX(), e.getY());
        }
        else if(mainPanel.getCurrentAction() == MainPanel.CURSOR_ACTION) {
            selectSpace(e.getX(), e.getY());
        }

        
        else if(mainPanel.getCurrentAction() == MainPanel.MOVE_ACTION) {
            moveFirstX = e.getX();
            moveFirstY = e.getY();
            previousX = getX();
            previousY = getY();
            System.out.println("X: " + getX());
        }
        else if(mainPanel.getCurrentAction() == MainPanel.OBJECT_ACTION) {
            testX = e.getX();
            testY = e.getY();

            if(
                resizeDefaultVaules(testX) < activeSpace.getX() ||
                resizeDefaultVaules(testY) < activeSpace.getY() ||
                resizeDefaultVaules(testX) > activeSpace.getX() + activeSpace.getWidth() ||
                resizeDefaultVaules(testY) > activeSpace.getY() + activeSpace.getHeight()
            ) {
                drawingIsSuccesful = false;
                System.out.println(resizeDefaultVaules(testX) + " > " + activeSpace.getX());
                System.out.println(resizeDefaultVaules(testY) + " > " + activeSpace.getY());
                System.out.println(resizeDefaultVaules(testX) + " < " + activeSpace.getX() + activeSpace.getWidth());
                System.out.println(resizeDefaultVaules(testY) + " < " + activeSpace.getY() + activeSpace.getHeight());
                System.out.println("Drawing is not succesful");
            }
            else {
                drawingIsSuccesful = true;
            }
        }
        
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if(mainPanel.getCurrentAction() == MainPanel.DRAW_ACTION && drawingIsSuccesful) {
            int currentX = e.getX();
            int currentY = e.getY();
            if(currentY > testY) {
                testHeight = e.getY() - testY;
            }
            else {
                testHeight = testY - e.getY();
                testY = e.getY();
            }
            if(currentX > testX) {
                testWidth = e.getX() - testX;
            }
            else {
                testWidth = testX - e.getX();
                testX = e.getX();
            }
            originalX = resizeDefaultVaules(testX);
            originalY = resizeDefaultVaules(testY);
            originalWidth = resizeDefaultVaules(testWidth);
            originalHeight = resizeDefaultVaules(testHeight);
            
            
            if(originalX < activeSpace.getX()) {
                originalWidth = originalWidth + originalX - activeSpace.getX();
                originalX = activeSpace.getX();
                //System.out.println("DSADSFSDFDS");
                System.out.println("İnside 1");
            }
            
            else if(originalX + originalWidth > activeSpace.getX() + activeSpace.getWidth()) {
                //System.out.println("LOK AT IT");
                //System.out.println(activeSpace.getX());
                //System.out.println(activeSpace.getWidth());
                //System.out.println(testX);
                originalWidth = activeSpace.getX() + activeSpace.getWidth() - originalX;
                System.out.println("İnside 2");
            }
            if(originalY < activeSpace.getY()) {
                originalHeight = originalHeight + originalY - activeSpace.getY();
                originalY = activeSpace.getY();
                System.out.println("İnside 3");
            }
            else if(originalY + originalHeight > activeSpace.getY() + activeSpace.getHeight()) {
                originalHeight = activeSpace.getY() + activeSpace.getHeight() - originalY;
                System.out.println("İnside 4");
            }
            
            
            addSpace(testX, testY, testWidth, testHeight);
            
        }
        else if(mainPanel.getCurrentAction() == MainPanel.OBJECT_ACTION && drawingIsSuccesful) {
            int currentX = e.getX();
            int currentY = e.getY();
            if(currentY > testY) {
                testHeight = e.getY() - testY;
            }
            else {
                testHeight = testY - e.getY();
                testY = e.getY();
            }
            if(currentX > testX) {
                testWidth = e.getX() - testX;
            }
            else {
                testWidth = testX - e.getX();
                testX = e.getX();
            }
            originalX = resizeDefaultVaules(testX);
            originalY = resizeDefaultVaules(testY);
            originalWidth = resizeDefaultVaules(testWidth);
            originalHeight = resizeDefaultVaules(testHeight);
            
            
            if(originalX < activeSpace.getX()) {
                originalWidth = originalWidth + originalX - activeSpace.getX();
                originalX = activeSpace.getX();
            }
            
            else if(originalX + originalWidth > activeSpace.getX() + activeSpace.getWidth()) {
                originalWidth = activeSpace.getX() + activeSpace.getWidth() - originalX;
            }
            if(originalY < activeSpace.getY()) {
                originalHeight = originalHeight + originalY - activeSpace.getY();
                originalY = activeSpace.getY();
            }
            else if(originalY + originalHeight > activeSpace.getY() + activeSpace.getHeight()) {
                originalHeight = activeSpace.getY() + activeSpace.getHeight() - originalY;
            }
            
            
            addObject(testX, testY, testWidth, testHeight);
            
        }
        
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        
    }


    @Override
    public void mouseExited(MouseEvent e) {
        
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if(mainPanel.getCurrentAction() == MainPanel.MOVE_ACTION) {
            setLocation(e.getX()-moveFirstX + previousX, e.getY()-moveFirstY + previousY);
            previousX = getX();
            previousY = getY();
            System.out.println(e.getX());
            System.out.println(moveFirstX);
        }        
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    

    
}
