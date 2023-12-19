package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.MouseInputListener;

import Building.Map;

public class MainPanel extends JPanel implements MouseInputListener{
    DrawPanel drawPanel;
    LeftScreenPanel leftScreenPanel;
    ToolsPanel toolsPanel;
    Controller backendController;

    final public static int BLUE_BACK_ACTION = 0;
    final public static int DRAW_ACTION = 1;
    final public static int COLOR_ACTION = 2;
    final public static int ERASER_ACTION = 3;
    final public static int MOVE_ACTION = 4;
    final public static int RESET_ACTION = 5;
    final public static int CURSOR_ACTION = 6;
    final public static int ZOOM_IN_ACTION = 7;
    final public static int ZOOM_OUT_ACTION = 8;
    final public static int PATH_ACTION = 9;
    final public static int WALL_ACTION = 10;
    final public static int OBEJECT_ACTION = 11;
    final public static int ADD_FLOOR_ACTION = 12;
    final public static int DELETE_ACTION = 13;
    public int currenAction = -1;
    public Color currentColor = Color.BLACK;

    final public int MAP_PANEL_DEFAULT_SIZE = 200;
    final public int MAX_ZOOM = 5;
    final public int MIN_ZOOM = 0;
    public int currentZoom = 1;
    public int differenceSize = 100;
    
    public int drawPanelSize = (MAP_PANEL_DEFAULT_SIZE*(currentZoom+2));
    public int mainPanelSize = drawPanelSize + differenceSize*2;
    
    JPanel westPanel = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel mainPanel = new JPanel(null);
    
    JScrollPane scrollPane;

    //int moveFirstX;
    //int moveFirstY;
    

    //, ToolsPanel toolsPanel
    public MainPanel(Controller backendController) {
        this.backendController = backendController;
        //this.toolsPanel = toolsPanel;
        //this.leftScreenPanel = new LeftScreenPanel(TOOL_TIP_TEXT_KEY, backendController);
        this.leftScreenPanel = new LeftScreenPanel("Admin Map Tools", backendController);
        this.toolsPanel = new ToolsPanel(this);
        this.drawPanel = new DrawPanel(this,leftScreenPanel, this.toolsPanel, backendController);
        setLayout(new BorderLayout());
        add(westPanel, BorderLayout.WEST);
        add(northPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        westPanel.setBackground(Color.BLUE);
        northPanel.setBackground(Color.ORANGE);
        mainPanel.setBackground(Color.CYAN);

        mainPanel.setPreferredSize(new Dimension(mainPanelSize, mainPanelSize));
        mainPanel.setSize(mainPanelSize, mainPanelSize);
        drawPanel.setSize(drawPanelSize, drawPanelSize);
        drawPanel.setLocation(differenceSize, differenceSize);
        this.backendController.setDrawPanel(drawPanel);
        mainPanel.add(drawPanel);
        JScrollPane scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);;
        add(scrollPane);
        //addMouseListener(this);
        //addMouseMotionListener(this);
        //mainPanel.addMouseListener(this);
        //mainPanel.addMouseMotionListener(this);
        //drawPanel.addMouseMotionListener(this);
        
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
            System.out.println("Draw Cursor");
        }
        else if(cursorType == COLOR_ACTION) {
            drawPanel.setCursor(new Cursor(0));
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
        else if(cursorType == ZOOM_IN_ACTION) {
            drawPanel.setCursor(new Cursor(0));
        }
        else if(cursorType == ZOOM_OUT_ACTION) {
            drawPanel.setCursor(new Cursor(0));
        }
        else if(cursorType == PATH_ACTION) {
            drawPanel.setCursor(Cursors.pathCursor());
        }
        else if(cursorType == WALL_ACTION) {
            drawPanel.setCursor(Cursors.wallCursor());
        }
    }



    //TODO: add method for preparing all methods, there are 2 same lines at each methods. Separate them.
    public void blueBack() {
        currenAction = BLUE_BACK_ACTION;
        arrangeCursor(COLOR_ACTION);
        backendController.setCurrentDrawContext(backendController.getCurrentDrawContext().getParent());
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
        //TODO: ARRANGE CURSOR
        arrangeCursor(MOVE_ACTION);
        drawPanel.setLocation(differenceSize, differenceSize);
        System.out.println("sadsfdsfds");
    }
    public void cursor() {
        currenAction = CURSOR_ACTION;
        arrangeCursor(CURSOR_ACTION);
    }

    public void zoomIn() {
        currenAction = ZOOM_IN_ACTION;
        arrangeCursor(ZOOM_IN_ACTION);
        if(currentZoom != MAX_ZOOM) {
            currentZoom++;
            setPanelSize();            
        }
        System.out.println(currentZoom);
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
        currenAction = OBEJECT_ACTION;
        arrangeCursor(DRAW_ACTION);
    }
    
    public void addFloor() {
        currenAction = ADD_FLOOR_ACTION;
        arrangeCursor(DRAW_ACTION);
    }
    
    public void delete() {
        System.out.println("Inside main panel delete");
        currenAction = DELETE_ACTION;
        arrangeCursor(DRAW_ACTION);
        //System.out.println(backendController.getCurrentDrawContext());
        backendController.deleteSpace(backendController.getCurrentDrawContext());
        drawPanel.repaint();

    }

    public void setPanelSize() {
        drawPanelSize = (MAP_PANEL_DEFAULT_SIZE*(currentZoom+2));

        //TODO: This might be unneccesray
        mainPanelSize = drawPanelSize + differenceSize*2;
        drawPanel.setSize(drawPanelSize, drawPanelSize);
        System.out.println("TESTTESTTESTTESTTESTTESTTESTTESTTESTTEST");
        mainPanel.setPreferredSize(new Dimension(mainPanelSize, mainPanelSize));
        mainPanel.setSize(mainPanelSize, mainPanelSize);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        /*
        System.out.println("DRAGGED");
        if(currenAction == MOVE_ACTION) {
            if(
                e.getX() > drawPanel.getX() && e.getX() < drawPanel.getX() + drawPanel.getWidth() &&
                e.getY() > drawPanel.getY() && e.getY() < drawPanel.getY() + drawPanel.getHeight()
            ) {
                drawPanel.setLocation(e.getX()-moveFirstX, e.getY()-moveFirstY);
                System.out.println(e.getX());
                System.out.println(moveFirstX);
            }
        }
         */
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*
        System.out.println("DSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        if(currenAction == MOVE_ACTION) {
            moveFirstX = e.getX() - drawPanel.getX();
            moveFirstY = e.getY() - drawPanel.getY();
        }
         */
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
