package UI;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

public class DrawPanel extends JPanel implements MouseInputListener, ComponentListener{
    public MainPanel mainPanel;
    ArrayList<Integer> testXList = new ArrayList<Integer>();
    ArrayList<Integer> testYList = new ArrayList<Integer>();
    ArrayList<Integer> testWidthList = new ArrayList<Integer>();
    ArrayList<Integer> testHeightList = new ArrayList<Integer>();
    ArrayList<String> testSpacesName = new ArrayList<String>();

    ArrayList<Integer> enterenceXList = new ArrayList<Integer>();
    ArrayList<Integer> enterenceYList = new ArrayList<Integer>();

    public int totalSpaces = 0;
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

    public DrawPanel(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);
    }
    public void addSpace(int x, int y, int width, int height) {
        paintDrawTemp(getGraphics());
        int area = width*height;
        String name = JOptionPane.showInputDialog(mainPanel, 
        "Space height is: " + getHeight() 
        , "Add Space", 1);

        //TODO: Can be checked if there is a place with the same name.
        //TODO: If there is alternative place name can be entered instead of previous one ,or it can be asked
        //TODO: to user to enter another value
        if(name == null || name.equals("")) {
            repaint();
        }
        else {
            testXList.add(resizeDefaultVaules(testX));
            testYList.add(resizeDefaultVaules(testY));
            testWidthList.add(resizeDefaultVaules(testWidth));
            testHeightList.add(resizeDefaultVaules(testHeight));
            testSpacesName.add(name);
            totalSpaces++;
            repaint();
        }
        System.out.println(testXList.size());
    }

    public void deleteSpace(int x, int y) {
        int deleteX = resizeDefaultVaules(x);
        int deleteY = resizeDefaultVaules(y);
        for(int i = 0; i < totalSpaces; i++){
            if(
                deleteX > testXList.get(i) &&
                deleteY > testYList.get(i) &&
                deleteX < testXList.get(i) + testWidthList.get(i) &&
                deleteY < testYList.get(i) + testHeightList.get(i)
            ) {
                testXList.remove(i);
                testYList.remove(i);
                testWidthList.remove(i);
                testHeightList.remove(i);
                testSpacesName.remove(i);
                totalSpaces--;
                repaint();
                break;
            }
        }
    }

    //TODO Combine edit and delete methods there are several same codes.
    public void editSpace(int x, int y) {
        int editX = resizeDefaultVaules(x);
        int editY = resizeDefaultVaules(y);
        for(int i = 0; i < totalSpaces; i++){
            if(
                editX > testXList.get(i) &&
                editY > testYList.get(i) &&
                editX < testXList.get(i) + testWidthList.get(i) &&
                editY < testYList.get(i) + testHeightList.get(i)
            ) {
                JTextField field1 = new JTextField();
                JTextField field2 = new JTextField();
                JTextField field3 = new JTextField();
                JTextField field4 = new JTextField();
                String text1 = "Information about the current building";
                String text2 =  "X-Cor: " + testXList.get(i) + "\n" +
                                "Y-Cor: " + testYList.get(i) + "\n" +
                                "Width: " + testWidthList.get(i) + "\n" +
                                "Height: " + testHeightList.get(i) + "\n\n";
                Object [] fields = {
                text1, text2,
                "New X-Cor", field1,
                "New Y-Cor", field2,
                "New Width", field3,
                "New Height", field4
                };
                //TODO: add All conditions, entering letter, not entering, entering to big number, entering zero or negative etc
                JOptionPane.showConfirmDialog(mainPanel,fields,"Edit Space",JOptionPane.OK_CANCEL_OPTION);
                //if(text1.is)
                repaint();
                break;
            }
        }
    }

    public void editSpace() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < totalSpaces; i++) {
            g.drawRect(
                resizeNormalValues(testXList.get(i)), 
                resizeNormalValues(testYList.get(i)), 
                resizeNormalValues(testWidthList.get(i)), 
                resizeNormalValues(testHeightList.get(i))
                );
            g.drawString(
                testSpacesName.get(i),
                resizeNormalValues(testXList.get(i)),
                resizeNormalValues(testYList.get(i))
                );
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
            addSpace(testX, testY, testHeight, testWidth);
            
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
