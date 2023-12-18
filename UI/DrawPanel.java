package UI;
import java.awt.Color;
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
    public MainPanel mainPanel;

    Controller backendController;
    Space activeSpace;
    LeftScreenPanel leftScreenPanel;
    public int testX;
    public int testY;
    public int testWidth;
    public int testHeight;

    public int resizeDefaultVaules(int value) {
        return value/(mainPanel.getCurrentZoom()+2);
    }

    public int resizeNormalValues(int value) {
        return value*(mainPanel.getCurrentZoom()+2);
    }

    public DrawPanel(MainPanel mainPanel, LeftScreenPanel leftScreenPanel, Controller controller){
        this.backendController = controller;
        this.mainPanel = mainPanel;
        this.leftScreenPanel = leftScreenPanel;
        setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);
    }
    public void setActiveSpace(Space space){
        this.activeSpace = space;
        repaint();
    }
    public void addSpace(int x, int y, int width, int height) {
        paintDrawTemp(getGraphics());
        int originalX = resizeDefaultVaules(x);
        int originalY = resizeDefaultVaules(y);
        int originalWidth = resizeDefaultVaules(width);
        int originalHeight = resizeDefaultVaules(height);

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
            else if(this.activeSpace instanceof Building){
                newSpace = new Floor(name);
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
            newSpace.setName(name);

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
                JButton locationButton = new JButton("Change Location and Size");
                JButton colorButton = new JButton(" Change Color");
                JButton nameButton = new JButton("Change Name");
                JButton enteranceButton = new JButton("Change Enterance");

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
                    locationButton, colorButton, nameButton, enteranceButton
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
        if(this.activeSpace instanceof Map){
            for (Space building : this.activeSpace.getContents()) {
                g.setColor(building.getColor());
                g.fillRect(
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
                    resizeNormalValues(building.getY()));
            }
        }
        else if(this.activeSpace instanceof Building){
            if(this.activeSpace.getContents().size() >= 1){
                Space floor = activeSpace.getContents().get(0);
                g.setColor(floor.getColor());
                g.fillRect(
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
                    resizeNormalValues(floor.getY()));
            } 
        }
        else if(this.activeSpace instanceof Floor){
            for (Space room : this.activeSpace.getContents()) {
                g.setColor(room.getColor());
                g.fillRect(
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
                    resizeNormalValues(room.getY()));
            }
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
        System.out.println("Pressed");
        if(mainPanel.getCurrentAction() == MainPanel.DRAW_ACTION) {
            testX = e.getX();
            testY = e.getY();
        }

        else if(mainPanel.getCurrentAction() == MainPanel.ERASER_ACTION) {
            deleteSpace(e.getX(), e.getY());
        }

        else if(mainPanel.getCurrentAction() == MainPanel.CURSOR_ACTION) {
            editSpace(e.getX(), e.getY());
        }
        System.out.println(resizeDefaultVaules(e.getX()) + ", " + resizeDefaultVaules(e.getY()));
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if(mainPanel.getCurrentAction() == MainPanel.DRAW_ACTION) {
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
        if(mainPanel.getCurrentAction() == MainPanel.PATH_ACTION) {

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
