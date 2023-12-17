package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class ToolsPanel extends JPanel implements ActionListener{
    MainPanel mainPanel;
    final private int ICON_WIDTH = 40;
    final private int ICON_HEIGHT = 40;

    JButton draw = new JButton(new ImageIcon("draw-icon.png"));
    JButton color = new JButton(new ImageIcon("color-icon.png"));
    JButton eraser = new JButton(new ImageIcon("eraser-icon.png"));
    JButton move = new JButton(new ImageIcon("move-icon.png"));
    JButton cursor = new JButton(new ImageIcon("cursor-icon.png"));
    JButton zoomIn = new JButton(new ImageIcon("zoomIn-icon.png"));
    JButton zoomOut = new JButton(new ImageIcon("zoomOut-icon.png"));
    JButton path = new JButton(new ImageIcon("path-icon.png"));
    JButton wall = new JButton(new ImageIcon("wall-icon.png"));

    JButton currerntGreen;
    
    ImageIcon drawIcon = new ImageIcon(((ImageIcon) draw.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon colorIcon = new ImageIcon(((ImageIcon) color.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon eraserIcon = new ImageIcon(((ImageIcon) eraser.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon moveIcon = new ImageIcon(((ImageIcon) move.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon cursorIcon = new ImageIcon(((ImageIcon) cursor.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon zoomInIcon = new ImageIcon(((ImageIcon) zoomIn.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon zoomOutIcon = new ImageIcon(((ImageIcon) zoomOut.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon pathIcon = new ImageIcon(((ImageIcon) path.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon wallIcon = new ImageIcon(((ImageIcon) wall.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    
    public ToolsPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        draw.setIcon(drawIcon);
        color.setIcon(colorIcon);
        eraser.setIcon(eraserIcon);
        move.setIcon(moveIcon);
        cursor.setIcon(cursorIcon);
        zoomIn.setIcon(zoomInIcon);
        zoomOut.setIcon(zoomOutIcon);
        path.setIcon(pathIcon);
        wall.setIcon(wallIcon);

        draw.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        color.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        eraser.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        move.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        cursor.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        zoomIn.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        zoomOut.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        path.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        wall.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        
        JPanel tempPanel = new JPanel();
        tempPanel.add(draw);
        tempPanel.add(color);
        tempPanel.add(eraser);
        tempPanel.add(move);
        tempPanel.add(cursor);
        tempPanel.add(zoomIn);
        tempPanel.add(zoomOut);
        tempPanel.add(path);
        tempPanel.add(wall);

        draw.addActionListener(this);
        color.addActionListener(this);
        eraser.addActionListener(this);
        move.addActionListener(this);
        cursor.addActionListener(this);
        zoomIn.addActionListener(this);
        zoomOut.addActionListener(this);
        path.addActionListener(this);
        wall.addActionListener(this);


        

        


        setLayout(new BorderLayout());
        add(tempPanel, BorderLayout.WEST);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(currerntGreen != null) {
            currerntGreen.setBackground(getBackground());
            currerntGreen = null;
        }
        if(e.getSource() == draw) {
            System.out.println("Draw");
            draw.setBackground(Color.GREEN);
            currerntGreen = draw;
            mainPanel.draw();
        }
        else if(e.getSource() == color) {
            System.out.println("Color");
            mainPanel.color();
        }
        else if(e.getSource() == eraser) {
            System.out.println("Eraser");
            eraser.setBackground(Color.GREEN);
            currerntGreen = eraser;
            mainPanel.eraser();
        }
        else if(e.getSource() == move) {
            System.out.println("Move");
            move.setBackground(Color.GREEN);
            currerntGreen = move;
            mainPanel.move();
        }
        else if(e.getSource() == cursor) {
            System.out.println("Cursor");
            cursor.setBackground(Color.GREEN);
            currerntGreen = cursor;
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
            path.setBackground(Color.GREEN);
            currerntGreen = path;
            mainPanel.path();
        }

        else if(e.getSource() == wall) {
            System.out.println("Wall");
            wall.setBackground(Color.GREEN);
            currerntGreen = wall;
            mainPanel.wall();
        }
    }   
}
