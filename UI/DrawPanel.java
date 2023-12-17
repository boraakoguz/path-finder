package UI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
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
    ArrayList<Color> testColorList = new ArrayList<Color>();
    ArrayList<String> testSpacesName = new ArrayList<String>();

    ArrayList<Integer> testEnterenceXList = new ArrayList<Integer>();
    ArrayList<Integer> testEnterenceYList = new ArrayList<Integer>();

    public int totalSpaces = 0;
    public int testX;
    public int testY;
    public int testWidth;
    public int testHeight;

    

    public DrawPanel() {

    }

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
        int originalX = resizeDefaultVaules(x);
        int originalY = resizeDefaultVaules(y);
        int originalWidth = resizeDefaultVaules(width);
        int originalHeight = resizeDefaultVaules(height);
        int area = width*height;
        //TODO: ADD AREA
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
            testXList.add(originalX);
            testYList.add(originalY);
            testWidthList.add(originalWidth);
            testHeightList.add(originalHeight);
            testColorList.add(mainPanel.getCurrentColor());
            testEnterenceXList.add(originalX + originalWidth/2);
            testEnterenceYList.add(originalY + originalHeight);
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
                testColorList.remove(i);
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
                int currentSpaceIndex = i;
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
                        String text1 = "Information about the space '" + testSpacesName.get(currentSpaceIndex) + "'";
                        String text2 =  "X-Cor: " + testXList.get(currentSpaceIndex) + "\n" +
                                        "Y-Cor: " + testYList.get(currentSpaceIndex) + "\n" +
                                        "Width: " + testWidthList.get(currentSpaceIndex) + "\n" +
                                        "Height: " + testHeightList.get(currentSpaceIndex) + "\n\n";
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
                                        testXList.set(currentSpaceIndex, newX);
                                        testYList.set(currentSpaceIndex, newY);
                                        testWidthList.set(currentSpaceIndex, newWidth);
                                        testHeightList.set(currentSpaceIndex, newHeight);
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
                        Color newColor = JColorChooser.showDialog(mainPanel, JColorChooser.SELECTION_MODEL_PROPERTY, testColorList.get(currentSpaceIndex));                                        
                        if(newColor != null) {
                            testColorList.set(currentSpaceIndex, newColor);
                            paint(getGraphics());
                        }           
                    }
                });

                nameButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String newName = JOptionPane.showInputDialog(mainPanel, "Enter New Name", "Change Name", JOptionPane.PLAIN_MESSAGE);
                        if(newName != null && !newName.equals("")) {
                            testSpacesName.set(currentSpaceIndex, newName);
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
                                maxCor = testYList.get(currentSpaceIndex) + testHeightList.get(currentSpaceIndex);
                                minCor = testYList.get(currentSpaceIndex);
                                corOtherComp = testXList.get(currentSpaceIndex) + testWidthList.get(currentSpaceIndex);
                                isOnX = false;
                                directionChooice = 1;
                            }                            
                        });
                        westButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                newSide = "WEST";
                                maxCor = testYList.get(currentSpaceIndex) + testHeightList.get(currentSpaceIndex);
                                minCor = testYList.get(currentSpaceIndex);
                                corOtherComp = testXList.get(currentSpaceIndex);
                                isOnX = false;
                                directionChooice = 2;
                            }                            
                        });
                        northButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                newSide = "NORTH";
                                maxCor = testXList.get(currentSpaceIndex) + testWidthList.get(currentSpaceIndex);
                                minCor = testXList.get(currentSpaceIndex);
                                corOtherComp = testYList.get(currentSpaceIndex);
                                isOnX = true;
                                directionChooice = 3;
                            }                            
                        });
                        southButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                newSide = "SOUTH";
                                maxCor = testXList.get(currentSpaceIndex) + testWidthList.get(currentSpaceIndex);
                                minCor = testXList.get(currentSpaceIndex);
                                corOtherComp = testYList.get(currentSpaceIndex) + testHeightList.get(currentSpaceIndex);
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
                                        testEnterenceXList.set(currentSpaceIndex, newValue);
                                        testEnterenceYList.set(currentSpaceIndex, corOtherComp);
                                    }
                                    else {
                                        testEnterenceXList.set(currentSpaceIndex, corOtherComp);
                                        testEnterenceYList.set(currentSpaceIndex, newValue);
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
            g.setColor(testColorList.get(i));
            g.fillRect(
                resizeNormalValues(testXList.get(i)), 
                resizeNormalValues(testYList.get(i)), 
                resizeNormalValues(testWidthList.get(i)), 
                resizeNormalValues(testHeightList.get(i))
                );
            g.setColor(Color.GREEN);
            g.fillRect(
                resizeNormalValues(testEnterenceXList.get(i))-5,
                resizeNormalValues(testEnterenceYList.get(i))-5,
                10,
                10
                );
            g.setColor(Color.BLACK);
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
