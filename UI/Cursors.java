package UI;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Cursors {
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image eraser = toolkit.getImage("source/eraser-icon.png");
    private static Image path = toolkit.getImage("source/path-icon.png");
    private static Image wall = toolkit.getImage("source/wall-icon.png");
    private static Image object = toolkit.getImage("source/object-icon.png");
    
    public static Cursor eraserCursor() {
        return toolkit.createCustomCursor(eraser , new Point(0, 47), "img");
    }
    public static Cursor pathCursor() {
        return toolkit.createCustomCursor(path , new Point(0, 47), "img");
    }
    public static Cursor wallCursor() {
        return toolkit.createCustomCursor(wall , new Point(0, 47), "img");
    }
    public static Cursor objectCursor() {
        return toolkit.createCustomCursor(object , new Point(0, 47), "img");
    }
}
