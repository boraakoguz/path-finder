package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainPanel extends JPanel{
    final public static int DRAW_ACTION = 0;
    final public static int COLOR_ACTION = 1;
    final public static int ERASER_ACTION = 2;
    final public static int MOVE_ACTION = 3;
    final public static int CURSOR_ACTION = 4;
    final public static int ZOOM_IN_ACTION = 5;
    final public static int ZOOM_OUT_ACTION = 6;
    final public static int PATH_ACTION = 7;
    final public static int WALL_ACTION = 8;
    public int currenAction = -1;
    public Color currentColor = Color.WHITE;


    //drawPanel setSize() ---> PANEL_DEFAULT_SIZE + (currentZoom*ADDITIONAL_PANEL_SIZE)
    //mainPanel setPreferedSize() ---> PANEL_DEFAULT_SIZE + (currentZoom*ADDITIONAL_PANEL_SIZE) + 200
    final public int PANEL_DEFAULT_SIZE = 200;
    final public int MAX_ZOOM = 5;
    final public int MIN_ZOOM = 0;
    public int currentZoom = 1;
    public int differenceSize = 100;
    public int drawPanelSize = (PANEL_DEFAULT_SIZE*(currentZoom+2));
    public int mainPanelSize = drawPanelSize + differenceSize*2;

    //current zoom + 2. Remove panel defult size
    
    JPanel westPanel = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel mainPanel = new JPanel(null);
    DrawPanel drawPanel;
    LeftScreenPanel leftScreenPanel;
    JScrollPane scrollPane;
    Controller backendController;

    public MainPanel(Controller backendController, LeftScreenPanel leftScreenPanel) {
        this.backendController = backendController;
        this.drawPanel = new DrawPanel(this,leftScreenPanel,backendController);
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

        //drawDFSD(drawPanel.getGraphics());
        //System.out.println(drawPanel.getGraphics());

        drawPanel.setCursor(new Cursor(13));
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

    public void setPanelSize() {
        drawPanelSize = (PANEL_DEFAULT_SIZE*(currentZoom+2));
        mainPanelSize = drawPanelSize + differenceSize*2;
        drawPanel.setSize(drawPanelSize, drawPanelSize);
        mainPanel.setPreferredSize(new Dimension(mainPanelSize, mainPanelSize));
        mainPanel.setSize(mainPanelSize, mainPanelSize);
    }

    public void drawSpace(Graphics g) {
        
    }

    public void drawDFSD(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(100, 100, 32, 32);
    }

}
