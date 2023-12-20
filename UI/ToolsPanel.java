package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolsPanel extends JPanel implements ActionListener{
    MainPanel mainPanel;

    final private int ICON_WIDTH = 40;
    final private int ICON_HEIGHT = 40;

    JButton blueBack = new JButton(new ImageIcon("blue-back-icon.png"));
    JButton draw = new JButton(new ImageIcon("draw-icon.png"));
    JButton color = new JButton(new ImageIcon("color-icon.png"));
    JButton eraser = new JButton(new ImageIcon("eraser-icon.png"));
    JButton move = new JButton(new ImageIcon("move-icon.png"));
    JButton reset = new JButton(new ImageIcon("reset-icon.png"));
    JButton cursor = new JButton(new ImageIcon("cursor-icon.png"));
    JButton zoomIn = new JButton(new ImageIcon("zoomIn-icon.png"));
    JButton zoomOut = new JButton(new ImageIcon("zoomOut-icon.png"));
    JButton path = new JButton(new ImageIcon("path-icon.png"));
    JButton wall = new JButton(new ImageIcon("wall-icon.png"));
    JButton object = new JButton(new ImageIcon("object-icon.png"));
    JButton addFloor = new JButton(new ImageIcon("add-floor-icon.png"));
    JButton delete = new JButton(new ImageIcon("delete-icon.png"));
    JButton addMap = new JButton(new ImageIcon("add-map-icon.png"));
    JButton currentButton;

    
    ImageIcon blueBackIcon = new ImageIcon(((ImageIcon) blueBack.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon drawIcon = new ImageIcon(((ImageIcon) draw.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon colorIcon = new ImageIcon(((ImageIcon) color.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon eraserIcon = new ImageIcon(((ImageIcon) eraser.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon moveIcon = new ImageIcon(((ImageIcon) move.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon resetIcon = new ImageIcon(((ImageIcon) reset.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon cursorIcon = new ImageIcon(((ImageIcon) cursor.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon zoomInIcon = new ImageIcon(((ImageIcon) zoomIn.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon zoomOutIcon = new ImageIcon(((ImageIcon) zoomOut.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon pathIcon = new ImageIcon(((ImageIcon) path.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon wallIcon = new ImageIcon(((ImageIcon) wall.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon objectIcon = new ImageIcon(((ImageIcon) object.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon addFlorIcon = new ImageIcon(((ImageIcon) addFloor.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon deleteIcon = new ImageIcon(((ImageIcon) delete.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon addMapIcon = new ImageIcon(((ImageIcon) addMap.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    
    public ToolsPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        blueBack.setIcon(blueBackIcon);
        draw.setIcon(drawIcon);
        color.setIcon(colorIcon);
        eraser.setIcon(eraserIcon);
        move.setIcon(moveIcon);
        reset.setIcon(resetIcon);
        cursor.setIcon(cursorIcon);
        zoomIn.setIcon(zoomInIcon);
        zoomOut.setIcon(zoomOutIcon);
        path.setIcon(pathIcon);
        wall.setIcon(wallIcon);
        object.setIcon(objectIcon);
        addFloor.setIcon(addFlorIcon);
        delete.setIcon(deleteIcon);
        addMap.setIcon(addMapIcon);

        blueBack.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        draw.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        color.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        eraser.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        move.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        reset.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        cursor.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        zoomIn.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        zoomOut.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        path.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        wall.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        object.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        addFloor.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        delete.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        addMap.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));

        JPanel tempPanel = new JPanel();
        tempPanel.add(blueBack);
        tempPanel.add(draw);
        tempPanel.add(color);
        tempPanel.add(eraser);
        tempPanel.add(move);
        tempPanel.add(reset);
        tempPanel.add(cursor);
        tempPanel.add(zoomIn);
        tempPanel.add(zoomOut);
        tempPanel.add(path);
        tempPanel.add(wall);
        tempPanel.add(object);
        tempPanel.add(addFloor);
        tempPanel.add(delete);
        tempPanel.add(addMap);

        blueBack.addActionListener(this);
        draw.addActionListener(this);
        color.addActionListener(this);
        eraser.addActionListener(this);
        move.addActionListener(this);
        reset.addActionListener(this);
        cursor.addActionListener(this);
        zoomIn.addActionListener(this);
        zoomOut.addActionListener(this);
        path.addActionListener(this);
        wall.addActionListener(this);
        object.addActionListener(this);
        addFloor.addActionListener(this);
        delete.addActionListener(this);
        addMap.addActionListener(this);


        setLayout(new BorderLayout());
        add(tempPanel, BorderLayout.WEST);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void disableEditing() {
        blueBack.setEnabled(true);
        draw.setEnabled(false);
        color.setEnabled(false);
        eraser.setEnabled(false);
        move.setEnabled(true);
        reset.setEnabled(true);
        cursor.setEnabled(false);
        zoomIn.setEnabled(true);
        zoomOut.setEnabled(true);
        path.setEnabled(false);
        wall.setEnabled(false);
        object.setEnabled(false);
        addFloor.setEnabled(false);
        delete.setEnabled(false);
        addMap.setEnabled(false);
        arrangeCurrentButton();
    }

    public void buildingEditing() {
        blueBack.setEnabled(true);
        draw.setEnabled(false);
        color.setEnabled(false);
        eraser.setEnabled(false);
        move.setEnabled(true);
        reset.setEnabled(true);
        cursor.setEnabled(false);
        zoomIn.setEnabled(true);
        zoomOut.setEnabled(true);
        path.setEnabled(false);
        wall.setEnabled(false);
        object.setEnabled(false);
        addFloor.setEnabled(true);
        delete.setEnabled(true);
        addMap.setEnabled(false);
        arrangeCurrentButton();
    }

    public void floorEditing() {
        blueBack.setEnabled(true);
        draw.setEnabled(true);
        color.setEnabled(true);
        eraser.setEnabled(true);
        move.setEnabled(true);
        reset.setEnabled(true);
        cursor.setEnabled(true);
        zoomIn.setEnabled(true);
        zoomOut.setEnabled(true);
        path.setEnabled(true);
        wall.setEnabled(true);
        object.setEnabled(true);
        addFloor.setEnabled(false);
        delete.setEnabled(true);
        addMap.setEnabled(false);
        arrangeCurrentButton();
    }

    public void mapEditing() {
        blueBack.setEnabled(true);
        draw.setEnabled(true);
        color.setEnabled(true);
        eraser.setEnabled(true);
        move.setEnabled(true);
        reset.setEnabled(true);
        cursor.setEnabled(true);
        zoomIn.setEnabled(true);
        zoomOut.setEnabled(true);
        path.setEnabled(true);
        wall.setEnabled(true);
        object.setEnabled(false);
        addFloor.setEnabled(false);
        delete.setEnabled(true);
        addMap.setEnabled(true);
        arrangeCurrentButton();
    }

    public void arrangeCurrentButton() {
        if(currentButton != null) {
            if(currentButton.isEnabled() == false) {
                mainPanel.noAction();
                currentButton.setBackground(getBackground());
            }
        }        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(currentButton != null) {
            currentButton.setBackground(getBackground());
        }

        if(e.getSource() == blueBack) {
            System.out.println("Blue Back");
            currentButton = blueBack;
            mainPanel.blueBack();
        }
        else if(e.getSource() == draw) {
            System.out.println("Draw");
            currentButton = draw;
            currentButton.setBackground(Color.GREEN);
            mainPanel.draw();
        }
        else if(e.getSource() == color) {
            System.out.println("Color");
            mainPanel.color();
        }
        else if(e.getSource() == eraser) {
            System.out.println("Eraser");
            currentButton = eraser;
            currentButton.setBackground(Color.GREEN);
            mainPanel.eraser();
        }
        else if(e.getSource() == move) {
            System.out.println("Move");
            currentButton = move;
            currentButton.setBackground(Color.GREEN);
            mainPanel.move();
        }
        else if(e.getSource() == reset) {
            System.out.println("Reset");
            currentButton = reset;
            mainPanel.reset();
        }
        else if(e.getSource() == cursor) {
            System.out.println("Cursor");
            currentButton = cursor;
            currentButton.setBackground(Color.GREEN);
            mainPanel.cursor();
        }
        else if(e.getSource() == zoomIn) {
            System.out.println("Zoom In");
            mainPanel.zoomIn();
        }
        else if(e.getSource() == zoomOut) {
            System.out.println("Zoom Out");
            mainPanel.zoomOut();
        }
        else if(e.getSource() == path) {
            System.out.println("Path");
            currentButton = path;
            currentButton.setBackground(Color.GREEN);
            mainPanel.path();
        }
        else if(e.getSource() == wall) {
            System.out.println("Wall");
            currentButton = wall;
            currentButton.setBackground(Color.GREEN);
            mainPanel.wall();
        }
        else if(e.getSource() == object) {
            System.out.println("Object");
            currentButton = object;
            currentButton.setBackground(Color.GREEN);
            mainPanel.object();
        }
        else if(e.getSource() == addFloor) {
            System.out.println("Add Floor");
            currentButton = addFloor;
            mainPanel.addFloor();
        }
        else if(e.getSource() == delete) {
            System.out.println("Delete");
            currentButton = delete;
            mainPanel.delete();
        }
        else if(e.getSource() == addMap) {
            System.out.println("Add Map");
            currentButton = addMap;
            mainPanel.addMap();
        }
    }   
}
