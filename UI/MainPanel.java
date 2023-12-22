package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Building.Building;
import Building.Floor;
import Building.Map;
import Building.Space;

public class MainPanel extends JPanel {
    DrawPanel drawPanel;
    LeftScreenPanel leftScreenPanel;
    ToolsPanel toolsPanel;
    Controller backendController;

    final public static int NO_ACTION = -1;
    final public static int BLUE_BACK_ACTION = 0;
    final public static int DRAW_ACTION = 1;
    final public static int COLOR_ACTION = 2;
    final public static int ERASER_ACTION = 3;
    final public static int MOVE_ACTION = 4;
    final public static int RESET_ACTION = 5;
    final public static int CURSOR_ACTION = 6;
    final public static int EDIT_ACTION = 7;
    final public static int ZOOM_IN_ACTION = 8;
    final public static int ZOOM_OUT_ACTION = 9;
    final public static int PATH_ACTION = 10;
    final public static int WALL_ACTION = 11;
    final public static int OBJECT_ACTION = 12;
    final public static int ADD_FLOOR_ACTION = 13;
    final public static int DELETE_ACTION = 14;
    final public static int ADD_MAP_ACTION = 15;
    public int currenAction = -1;
    public Color currentColor = Color.BLACK;

    final public int MAP_PANEL_DEFAULT_SIZE = 200;
    final public int MAX_ZOOM = 20;
    final public int MIN_ZOOM = 0;
    public int currentZoom = 1;
    public int differenceSize = 100;
    
    public int drawPanelSize = (MAP_PANEL_DEFAULT_SIZE*(currentZoom+2));
    public int mainPanelSize = drawPanelSize + differenceSize*2;
    public Path_Finder_Frame contFrame;
    //JPanel westPanel = new JPanel();
    //JPanel northPanel = new JPanel();
    JPanel mainPanel = new JPanel(null);
    
    JScrollPane scrollPane;

    public MainPanel(Controller backendController,Path_Finder_Frame m) {
        this.backendController = backendController;
        contFrame=m;
        this.leftScreenPanel = new LeftScreenPanel("Admin Map Tools", backendController,contFrame);
        this.toolsPanel = new ToolsPanel(this);
        this.drawPanel = new DrawPanel(this,leftScreenPanel, this.toolsPanel, backendController);
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBackground(Color.CYAN);

        mainPanel.setPreferredSize(new Dimension(mainPanelSize, mainPanelSize));
        mainPanel.setSize(mainPanelSize, mainPanelSize);
        drawPanel.setSize(drawPanelSize, drawPanelSize);
        drawPanel.setLocation(differenceSize, differenceSize);
        this.backendController.setDrawPanel(drawPanel);
        mainPanel.add(drawPanel);
        JScrollPane scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);;
        add(scrollPane);
    }

    public ToolsPanel getToolsPanel() {
        return toolsPanel;
    }

    public LeftScreenPanel getLeftScreenPanel() {
        return leftScreenPanel;
    }

    public int getCurrentZoom() {
        return currentZoom;
    }

    public int getCurrentAction() {
        return currenAction;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void arrangeCursor(int cursorType) {
        if(cursorType == DRAW_ACTION) {
            drawPanel.setCursor(new Cursor(1));
        }
        else if(cursorType == ERASER_ACTION) {
            drawPanel.setCursor((Cursors.eraserCursor()));
        }
        else if(cursorType == MOVE_ACTION) {
            drawPanel.setCursor(new Cursor(13));
        }
        else if(cursorType == CURSOR_ACTION) {
            drawPanel.setCursor(new Cursor(12));
        }
        else if(cursorType == PATH_ACTION) {
            drawPanel.setCursor(Cursors.pathCursor());
        }
        else if(cursorType == WALL_ACTION) {
            drawPanel.setCursor(Cursors.wallCursor());
        }
        else if(cursorType == OBJECT_ACTION) {
            drawPanel.setCursor(Cursors.objectCursor());
        }
        else {
            drawPanel.setCursor(new Cursor(0));
        }
    }

    public void blueBack() {
        currenAction = BLUE_BACK_ACTION;
        arrangeCursor(BLUE_BACK_ACTION);
        backendController.setCurrentDrawContext(backendController.getCurrentDrawContext().getParent());
    }

    public void noAction() {
        currenAction = NO_ACTION;
        arrangeCursor(NO_ACTION);
    }

    public void draw() {
        currenAction = DRAW_ACTION;
        arrangeCursor(DRAW_ACTION);
    }
    public void color() {
        currenAction = COLOR_ACTION;
        arrangeCursor(COLOR_ACTION);
        Color newColor = JColorChooser.showDialog(this, JColorChooser.SELECTION_MODEL_PROPERTY, currentColor);
        if(newColor != null) {
            currentColor = newColor;
            backendController.getCurrentDrawContext().setColor(newColor);
            if(backendController.getCurrentDrawContext() instanceof Map) {
                mainPanel.setBackground(newColor);
            }
            else {
                drawPanel.repaint();
            }
        }
    }
    public void eraser() {
        currenAction = ERASER_ACTION;
        arrangeCursor(ERASER_ACTION);
    }
    public void move() {
        currenAction = MOVE_ACTION;
        arrangeCursor(MOVE_ACTION);
    }

    public void reset() {
        currenAction = RESET_ACTION;
        arrangeCursor(RESET_ACTION);
        drawPanel.setLocation(differenceSize, differenceSize);
    }
    public void cursor() {
        currenAction = CURSOR_ACTION;
        arrangeCursor(CURSOR_ACTION);
    }

    public void edit() {
        currenAction = EDIT_ACTION;
        arrangeCursor(EDIT_ACTION);
    }

    public void zoomIn() {
        currenAction = ZOOM_IN_ACTION;
        arrangeCursor(ZOOM_IN_ACTION);
        if(currentZoom != MAX_ZOOM) {
            currentZoom++;
            setPanelSize();            
        }
    }

    public void zoomOut() {
        currenAction = ZOOM_OUT_ACTION;
        arrangeCursor(ZOOM_OUT_ACTION);
        if(currentZoom != MIN_ZOOM) {
            currentZoom--;
            setPanelSize();            
        }
    }

    public void path() {
        currenAction = PATH_ACTION;
        arrangeCursor(PATH_ACTION);
    }

    public void wall() {
        currenAction = WALL_ACTION;
        arrangeCursor(WALL_ACTION);
    }
    
    public void object() {
        currenAction = OBJECT_ACTION;
        arrangeCursor(OBJECT_ACTION);
    }
    
    public void addFloor() {
        currenAction = ADD_FLOOR_ACTION;
        arrangeCursor(ADD_FLOOR_ACTION);
        Building currentBuilding = (Building)backendController.getCurrentDrawContext();
        JButton up = new JButton("UP");
        JButton down = new JButton("DOWN");
        Object [] buttons = {"Where do you want to add a floor?", up, down};
        
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentBuilding.addFloor(Building.FLOOR_UP);
                
            }            
        });

        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentBuilding.addFloor(Building.FLOOR_DOWN);
            }            
        });
        
        JOptionPane.showConfirmDialog(this,buttons,"Add Floor",JOptionPane.INFORMATION_MESSAGE);
                
    }
    
    public void delete() {
        System.out.println("Inside main panel delete");
        currenAction = DELETE_ACTION;
        arrangeCursor(DELETE_ACTION);
        
        //TODO: ADD SELF DESCTRUCTION METHOD
        Space currentSpace = backendController.getCurrentDrawContext(); 
        Space parentSpace = currentSpace.getParent();
       
        if(parentSpace != null) {
            if(currentSpace instanceof Floor) {
                Building currentBuilding = (Building) parentSpace;
                Floor currentFloor = (Floor) currentSpace;
                String floorNumber = currentFloor.getFloorNumber() +"";
                if(currentBuilding.floorCanBeDeleted(currentFloor)) {
                    if(currentFloor.getFloorNumber() != Building.FLOOR_GROUND) {
                        int chooice = JOptionPane.showConfirmDialog (this, "Are you sure deleting " + backendController.getCurrentDrawContext() + 
                        " ?\nEverything inside will also be deleted" ,"Warning", JOptionPane.YES_NO_OPTION);
                        if(chooice == JOptionPane.YES_OPTION) {
                            currentBuilding.deleteFloor(currentFloor);
                            drawPanel.setActiveSpace(parentSpace);
                            drawPanel.repaint();
                            JOptionPane.showMessageDialog(mainPanel, "Floor successfully deleted",
                            "Invalid", JOptionPane.DEFAULT_OPTION);  
                        }                        
                    }
                    else{
                        if(currentBuilding.getNumberOfFloors() == 1) {
                            int chooice = JOptionPane.showConfirmDialog (this, "Are you sure deleting " + backendController.getCurrentDrawContext() + 
                            " ?\nEverything inside will also be deleted" ,"Warning", JOptionPane.YES_NO_OPTION);
                            if(chooice == JOptionPane.YES_OPTION) {
                                currentBuilding.deleteFloor(currentFloor);
                                //TODO: How does it work? How does it arrange current space correctly????
                                drawPanel.repaint();
                                JOptionPane.showMessageDialog(mainPanel, "Floor successfully deleted",
                                "Invalid", JOptionPane.DEFAULT_OPTION);    
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(mainPanel, "You can't delete the ground floor without deleting all the other floors.",
                            "Invalid", JOptionPane.DEFAULT_OPTION);
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "You can only delete the top or bottom Floor!" +
                    "\nYour current floor number is: " + floorNumber + "\nTop floor: " + (currentBuilding.getMaxFloor()-1) + 
                    "\nBottem floor: " + (currentBuilding.getMinFloor()+1),
                    "Invalid", JOptionPane.DEFAULT_OPTION);
                }   
            }
            else {
                int chooice = JOptionPane.showConfirmDialog (this, "Are you sure deleting " + backendController.getCurrentDrawContext() + 
                    " ?\nEverything inside will also be deleted" ,"Warning", JOptionPane.YES_NO_OPTION);
                if(chooice == JOptionPane.YES_OPTION) {
                    backendController.deleteSpace(backendController.getCurrentDrawContext());
                    backendController.setCurrentDrawContext(parentSpace);
                }
            }
        }
        else if(currentSpace instanceof Map) {
        }
    }
    public void addMap() {
        currenAction = ADD_MAP_ACTION;
        arrangeCursor(ADD_MAP_ACTION);
    }

    public void setPanelSize() {
        drawPanelSize = (MAP_PANEL_DEFAULT_SIZE*(currentZoom+2));

        //TODO: This might be unneccesray
        mainPanelSize = drawPanelSize + differenceSize*2;
        drawPanel.setSize(drawPanelSize, drawPanelSize);
        mainPanel.setPreferredSize(new Dimension(mainPanelSize, mainPanelSize));
        mainPanel.setSize(mainPanelSize, mainPanelSize);
    }

}
