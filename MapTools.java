import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

public class MapTools implements MouseInputListener {
    Controller controller = new Controller();
    JFrame settingsFrame;
    JFrame drawFrame;
    public MapTools() {
        settingsFrame = new JFrame();
        drawFrame = new JFrame();
        settingsFrame.setVisible(true);
        settingsFrame.setSize(100, 300);

        drawFrame.setVisible(true);
        drawFrame.setLocationRelativeTo(null);
        drawFrame.setSize(300,400);
        drawFrame.addMouseListener(null);
        
    }


    /**
     * Add spaces
     * @param  
     */
    public void addSpaces() {

    }

    /**
     * Deletes spaces
     * @param  
     */
    public void deleteSpaces() {

    }

    /**
     * Moves spaces
     * @param  
     */
    public void moveSpaces() {

    }

    /**
     * Adds objects
     * @param  
     */
    public void addObject() {

    }

    /**
     * Deletes spaces
     * @param  
     */
    public void deleteObject() {

    }

    /**
     * Moves objects
     * @param  
     */
    public void moveObject() {

    }

    /**
     * Draws path
     */
    public void drawPath() {

    }

    /**
     * Deletes path
     */
    public void deletePath() {
        
    }



    public static void main(String[] args) {
        MapTools tools = new MapTools();
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Tesdfsd");
    }
}
