package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;


import Building.Building;
import Building.Floor;
import Building.Map;
import Building.Room;
import Building.Space;

public class DrawPanel extends JPanel implements MouseInputListener, ComponentListener{
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
        addComponentListener(this);
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

    //TODO Combine edit and delete methods there are several same codes.
    public void editSpace(int x, int y) {
        int editX = resizeDefaultVaules(x);
        int editY = resizeDefaultVaules(y);
        for(Space space : this.activeSpace.getContents()){
            if(
                editX > space.getX() &&
                editY > space.getY() &&
                editX < space.getX() + space.getWidth() &&
                editY < space.getY() + space.getHeight()
            ) {
                JButton selectSpaceButton = new JButton("Select Space");
                JButton locationButton = new JButton("Change Location and Size");
                JButton colorButton = new JButton(" Change Color");
                JButton nameButton = new JButton("Change Name");
                JButton enteranceButton = new JButton("Change Enterance");

                selectSpaceButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        backendController.setCurrentDrawContext(space);
                        repaint();
                    }
                    
                });
                locationButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JTextField field1 = new JTextField();
                        JTextField field2 = new JTextField();
                        JTextField field3 = new JTextField();
                        JTextField field4 = new JTextField();
                        String text1 = "Information about the space '" + space.getName() + "'";
                        String text2 =  "X-Cor: " + space.getX() + "\n" +
                                        "Y-Cor: " + space.getY() + "\n" +
                                        "Width: " + space.getWidth() + "\n" +
                                        "Height: " + space.getHeight() + "\n\n";
                        Object [] fields = {
                        text1, text2,
                        "New X-Cor", field1,
                        "New Y-Cor", field2,
                        "New Width", field3,
                        "New Height", field4,
                        };
                        //TODO: add All conditions, entering letter, not entering, entering to big number, entering zero or negative etc
                        int chooice = JOptionPane.showConfirmDialog(mainPanel,fields,"Edit Space",JOptionPane.OK_CANCEL_OPTION);
                        if(
                            chooice == JOptionPane.OK_OPTION &&
                            !field1.getText().equals("") &&
                            !field2.getText().equals("") &&
                            !field3.getText().equals("") &&
                            !field4.getText().equals("")                 
                            ) 
                            {
                                try {
                                    int newX = Integer.parseInt(field1.getText());
                                    int newY = Integer.parseInt(field2.getText());
                                    int newWidth = Integer.parseInt(field3.getText());
                                    int newHeight = Integer.parseInt(field4.getText());

                                    if(newX > 0 && newY > 0 && newWidth > 0 && newHeight > 0) {
                                        space.setX(newX);
                                        space.setY(newY);
                                        space.setWidth(newWidth);
                                        space.setHeight(newHeight);
                                    }
                                } catch (Exception e2) {
                                    System.out.println("String");   
                                }
                        }
                        else {
                            System.out.println("outside");
                        }
                        System.out.println(field1.getText());
                        repaint();
                    }
                });

                colorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color newColor = JColorChooser.showDialog(mainPanel, JColorChooser.SELECTION_MODEL_PROPERTY, space.getColor());                                        
                        if(newColor != null) {
                            space.setColor(newColor);
                            repaint();
                        }           
                    }
                });

                nameButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String newName = JOptionPane.showInputDialog(mainPanel, "Enter New Name", "Change Name", JOptionPane.PLAIN_MESSAGE);
                        if(newName != null && !newName.equals("")) {
                            space.setName(newName);
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
                                maxCor = space.getY() + space.getHeight();
                                minCor = space.getY();
                                corOtherComp = space.getX() + space.getWidth();
                                isOnX = false;
                                directionChooice = 1;
                            }                            
                        });
                        westButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                newSide = "WEST";
                                maxCor = space.getY() + space.getHeight();
                                minCor = space.getY();
                                corOtherComp = space.getX();
                                isOnX = false;
                                directionChooice = 2;
                            }                            
                        });
                        northButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                newSide = "NORTH";
                                maxCor = space.getX() + space.getWidth();
                                minCor = space.getX();
                                corOtherComp = space.getY();
                                isOnX = true;
                                directionChooice = 3;
                            }                            
                        });
                        southButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                newSide = "SOUTH";
                                maxCor = space.getX() + space.getWidth();
                                minCor = space.getX();
                                corOtherComp =  space.getY()+ space.getHeight();
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
                                        space.setEntranceX(newValue);
                                        space.setEntranceY(corOtherComp);
                                    }
                                    else {
                                        space.setX(corOtherComp);
                                        space.setY(newValue);
                                    }
                                    repaint();
                                }

                            } catch (Exception e2) {
                                // TODO: handle exception
                            }
                            
                        }
                        
                        directionChooice = 0;
                        System.out.println("dasdsc");
                    }
                    
                });
                Object [] buttons = {
                    selectSpaceButton ,locationButton, colorButton, nameButton, enteranceButton
                };
                JOptionPane.showConfirmDialog(mainPanel,buttons,"Edit Space",JOptionPane.CANCEL_OPTION);
                backendController.save();
                break;                
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        //g.fillRect(20, 40, 10, 30);
        if(activeSpace == null) {
            toolsPanel.disableEditing();
            g.setFont(new Font("Courier New", 1, resizeNormalValues(4)));
            g.drawString("To use the map tools, you should select a map from the left panel!!!",
                    resizeNormalValues(20),
                    resizeNormalValues(20));
        }
        else if(activeSpace instanceof Building) {
            toolsPanel.disableEditing();
            g.setFont(new Font("Courier New", 1, resizeNormalValues(4)));
            g.drawString("You can't see the building directly, you have to choose a floor!!!",
                    resizeNormalValues(20),
                    resizeNormalValues(20));
        }
        //if(activeSpace != null) {
        else {  
            toolsPanel.enableEditing();  
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
        if(this.activeSpace instanceof Map){
            for (Space building : this.activeSpace.getContents()) {
                g.setColor(building.getColor());
                g.drawRect(
                    resizeNormalValues(building.getX()),
                    resizeNormalValues(building.getY()),
                    resizeNormalValues(building.getWidth()),
                    resizeNormalValues(building.getHeight()));
                g.setColor(Color.GREEN);
                g.fillRect(
                    resizeNormalValues(building.getEntranceX())-5,
                    resizeNormalValues(building.getEntranceY())-5,
                    10,
                    10);
                g.setColor(Color.BLACK);
                g.drawString(building.getName(),
                    resizeNormalValues(building.getX()),
                    resizeNormalValues(building.getY()+5));
            }
        }
        /*
        else if(this.activeSpace instanceof Building){
            if(this.activeSpace.getContents().size() >= 1){
                Space floor = activeSpace.getContents().get(0);
                g.setColor(floor.getColor());
                g.drawRect(
                    resizeNormalValues(floor.getX()),
                    resizeNormalValues(floor.getY()),
                    resizeNormalValues(floor.getWidth()),
                    resizeNormalValues(floor.getHeight()));
                g.setColor(Color.GREEN);
                g.fillRect(
                    resizeNormalValues(floor.getEntranceX())-5,
                    resizeNormalValues(floor.getEntranceY())-5,
                    10,
                    10);
                g.setColor(Color.BLACK);
                g.drawString(floor.getName(),
                    resizeNormalValues(floor.getX()),
                    resizeNormalValues(floor.getY()+5));
            } 
        }
         */
        else if(this.activeSpace instanceof Floor){
            for (Space room : this.activeSpace.getContents()) {
                g.setColor(room.getColor());
                g.drawRect(
                    resizeNormalValues(room.getX()),
                    resizeNormalValues(room.getY()),
                    resizeNormalValues(room.getWidth()),
                    resizeNormalValues(room.getHeight()));
                g.setColor(Color.GREEN);
                g.fillRect(
                    resizeNormalValues(room.getEntranceX())-5,
                    resizeNormalValues(room.getEntranceY())-5,
                    10,
                    10);

                g.setColor(Color.BLACK);
                g.drawString(room.getName(),
                    resizeNormalValues(room.getX()),
                    resizeNormalValues(room.getY()+5));
            }
            Floor currentFloor = (Floor) activeSpace;
            System.out.println(currentFloor.getDownStairX());
            System.out.println(currentFloor.getDownStairY());
            System.out.println(currentFloor.getUpStairX());
            System.out.println(currentFloor.getUpStairY ());
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
        }
        else if(this.activeSpace instanceof Room){
            System.out.println("r");
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

        else if(mainPanel.getCurrentAction() == MainPanel.CURSOR_ACTION) {
            editSpace(e.getX(), e.getY());
        }
        //System.out.println(resizeDefaultVaules(e.getX()) + ", " + resizeDefaultVaules(e.getY()));
        
        else if(mainPanel.getCurrentAction() == MainPanel.MOVE_ACTION) {
            moveFirstX = e.getX();
            moveFirstY = e.getY();
            previousX = getX();
            previousY = getY();
            System.out.println("X: " + getX());
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
            //setLocation(e.getX()-moveFirstX, e.getY()-moveFirstY);
            previousX = getX();
            previousY = getY();
            System.out.println(e.getX());
            System.out.println(moveFirstX);
        }
        
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    

    //TODO: These methods can be deleted. Maybe?
    @Override
    public void componentResized(ComponentEvent e) {
        //paintComponent(getGraphics());
    }

    @Override
    public void componentMoved(ComponentEvent e) {}


    @Override
    public void componentShown(ComponentEvent e) {}


    @Override
    public void componentHidden(ComponentEvent e) {}

    
}
