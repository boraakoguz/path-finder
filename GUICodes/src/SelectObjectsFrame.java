import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectObjectsFrame extends JFrame{
    //creating variables
    Color backGroundpink;
    SelecetObjectPanel soPanel=new SelecetObjectPanel(); //the panel with colared backgroud
    JButton backBut=new JButton();
    JButton menuBut=new JButton();
    JButton watBut=new JButton();
    JButton vendBut=new JButton();

    public SelectObjectsFrame(){
        backGroundpink=backGroundpink.decode("#dd96b8");
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH); //makinf the frame full-screen
        this.setIconImage(new ImageIcon("image (18).png").getImage());
        buts();
        this.add(soPanel);
    }
    
    // Adding buttons to Frame
    protected void buts(){
        //button adjustments
        backBut.addActionListener(new ButAction());
        backBut.setBounds(20, 20, 50, 45);
        backBut.setFocusable(false);
        backBut.setBackground(backGroundpink); 
        backBut.setIcon(new ImageIcon("image (11).png")); //adding icons to buttons
        backBut.setBorder(BorderFactory.createEmptyBorder()); //setting borders of buttons 
        
        menuBut.addActionListener(new ButAction());
        menuBut.setBounds(1490, 10, 35, 35);
        menuBut.setFocusable(false);
        menuBut.setBackground(Color.WHITE);
        menuBut.setIcon(new ImageIcon("image (10).png"));
        menuBut.setBorder(BorderFactory.createEmptyBorder());

        watBut.addActionListener(new ButAction());
        watBut.setBounds(40, 200, 200, 200);
        watBut.setFocusable(false);
        watBut.setBackground(backGroundpink);
        watBut.setIcon(new ImageIcon("image (23).png"));
        watBut.setBorder(BorderFactory.createEmptyBorder());

        vendBut.addActionListener(new ButAction());
        vendBut.setBounds(60, 450, 200, 200);
        vendBut.setFocusable(false);
        vendBut.setBackground(backGroundpink);
        vendBut.setIcon(new ImageIcon("image (16).png"));
        vendBut.setBorder(BorderFactory.createEmptyBorder());

        // Adding buttons to Frame
        this.add(vendBut);
        this.add(watBut);
        this.add(menuBut);
        this.add(backBut);
    }

    // for the panel of frames, inner classes are used in order to be able to see all interface in a single file
    public class SelecetObjectPanel extends JPanel {
        public SelecetObjectPanel(){
            
        }
        public void paint(Graphics g){
            //painting backgroung
            super.paint(g);
            g.setColor(backGroundpink);
            g.fillRect(0, 0, getWidth()/4, getHeight());
            g.setColor(Color.WHITE);
            g.fillRect(getWidth()/4, 0, 3*getWidth()/4, getHeight());
        }
    }

    // actionlisteners of buttons
    // actions are not done yet
    public class ButAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("test");
        }
    }
    
}
