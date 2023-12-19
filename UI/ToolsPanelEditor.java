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

public class ToolsPanelEditor extends JPanel implements ActionListener{
    MainPanelEditor mainPanel;

    final private int ICON_WIDTH = 40;
    final private int ICON_HEIGHT = 40;

    JButton blueBack = new JButton(new ImageIcon("blue-back-icon.png"));
    JButton eraser = new JButton(new ImageIcon("eraser-icon.png"));
    JButton move = new JButton(new ImageIcon("move-icon.png"));
    JButton reset = new JButton(new ImageIcon("reset-icon.png"));
    JButton cursor = new JButton(new ImageIcon("cursor-icon.png"));
    JButton zoomIn = new JButton(new ImageIcon("zoomIn-icon.png"));
    JButton zoomOut = new JButton(new ImageIcon("zoomOut-icon.png"));
    JButton object = new JButton(new ImageIcon("object-icon.png"));

    JButton currerntGreen;
    
    ImageIcon blueBackIcon = new ImageIcon(((ImageIcon) blueBack.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon eraserIcon = new ImageIcon(((ImageIcon) eraser.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon moveIcon = new ImageIcon(((ImageIcon) move.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon resetIcon = new ImageIcon(((ImageIcon) reset.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon cursorIcon = new ImageIcon(((ImageIcon) cursor.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon zoomInIcon = new ImageIcon(((ImageIcon) zoomIn.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    ImageIcon zoomOutIcon = new ImageIcon(((ImageIcon) zoomOut.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));

    ImageIcon objectIcon = new ImageIcon(((ImageIcon) object.getIcon()).getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH));
    
    public ToolsPanelEditor(MainPanelEditor mainPanel) {
        this.mainPanel = mainPanel;
        blueBack.setIcon(blueBackIcon);
        eraser.setIcon(eraserIcon);
        move.setIcon(moveIcon);
        reset.setIcon(resetIcon);
        cursor.setIcon(cursorIcon);
        zoomIn.setIcon(zoomInIcon);
        zoomOut.setIcon(zoomOutIcon);
        object.setIcon(objectIcon);


        blueBack.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        eraser.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        move.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        reset.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        cursor.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        zoomIn.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        zoomOut.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        object.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));


        JPanel tempPanel = new JPanel();
        tempPanel.add(blueBack);
        tempPanel.add(eraser);
        tempPanel.add(move);
        tempPanel.add(reset);
        tempPanel.add(cursor);
        tempPanel.add(zoomIn);
        tempPanel.add(zoomOut);
        tempPanel.add(object);


        blueBack.addActionListener(this);
        eraser.addActionListener(this);
        move.addActionListener(this);
        reset.addActionListener(this);
        cursor.addActionListener(this);
        zoomIn.addActionListener(this);
        zoomOut.addActionListener(this);
        object.addActionListener(this);



        setLayout(new BorderLayout());
        add(tempPanel, BorderLayout.WEST);
    }

    public MainPanelEditor getMainPanel() {
        return mainPanel;
    }

    public void disableEditing() {
        eraser.setEnabled(false);
        cursor.setEnabled(false);

    }

    public void enableEditing() {
        eraser.setEnabled(true);
        cursor.setEnabled(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(currerntGreen != null) {
            currerntGreen.setBackground(getBackground());
            currerntGreen = null;
        }
        if(e.getSource() == blueBack) {
            System.out.println("Blue Back");
            currerntGreen = blueBack;
            mainPanel.blueBack();
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
        else if(e.getSource() == reset) {
            System.out.println("Reset");
            currerntGreen = reset;
            mainPanel.reset();
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
        else if(e.getSource() == object) {
            System.out.println("Object");
            object.setBackground(Color.GREEN);
            currerntGreen = object;
            mainPanel.object();
        }
    }   
}
