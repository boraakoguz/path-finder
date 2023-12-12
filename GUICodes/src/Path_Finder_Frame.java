import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Path_Finder_Frame extends JFrame {
    //creating variables
    MainPanel mPanel=new MainPanel(); //the panel with colered backgroud 
    JButton backBut=new JButton();
    JButton menuBut=new JButton();
    JButton fOBut=new JButton("Find Objects"); //find object button
    JButton nextBut=new JButton("Next");
    Color backGroundpink; //backgroud color
    JLabel firstLab=new JLabel( "Where Are You?");
    JLabel mapLab=new JLabel("Select Map");
    String[] maps={"C1","C2"};
    JComboBox mapCombo;
    JLabel buildLab=new JLabel("Select Building");
    String[] builds={"C1","C2"};
    JComboBox buildCombo;
    JLabel floLab=new JLabel("Selecet Floor");
    String[] flos={"C1","C2"};
    JComboBox floCombo;
    JLabel roomLab=new JLabel("Select Room");
    String[] rooms={"C1","C2"};
    JComboBox roomCombo;

    public Path_Finder_Frame(){
        backGroundpink=backGroundpink.decode("#dd96b8"); //color adjusment
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);//maximasing the frame
        this.setIconImage(new ImageIcon("image (18).png").getImage()); //seting icon of the app
        buts();
        comboBoxs();
        this.add(mPanel);
    }
    // creating buttons to Frame
    protected void buts(){
        //button adjusments
        backBut.addActionListener(new ButAction());
        backBut.setBounds(20, 20, 50, 45);
        backBut.setFocusable(false);
        backBut.setBackground(backGroundpink);
        backBut.setIcon(new ImageIcon("image (11).png"));
        backBut.setBorder(BorderFactory.createEmptyBorder()); //setting borders of buttons 
    
        menuBut.addActionListener(new ButAction());
        menuBut.setBounds(1490, 10, 35, 35);
        menuBut.setFocusable(false);
        menuBut.setBackground(Color.WHITE);
        menuBut.setIcon(new ImageIcon("image (10).png"));
        menuBut.setBorder(BorderFactory.createEmptyBorder());

        nextBut.addActionListener(new ButAction());
        nextBut.setBounds(20, 550, 150, 75);
        nextBut.setForeground(Color.WHITE);
        nextBut.setFocusable(false);
        nextBut.setBackground(Color.BLACK);
        nextBut.setBorder(BorderFactory.createEmptyBorder());

        fOBut.addActionListener(new ButAction());
        fOBut.setBounds(200, 550, 150, 75);
        fOBut.setForeground(Color.WHITE);
        fOBut.setFocusable(false);
        fOBut.setBackground(Color.BLACK);
        fOBut.setBorder(BorderFactory.createEmptyBorder());

        // Adding buttons to Frame 
        this.add(nextBut);
        this.add(fOBut);
        this.add(menuBut);
        this.add(backBut);
    }
    // creating comboboxes
    protected void comboBoxs(){
        //combobox adjusments
        firstLab.setFont(new Font("Arial", Font.BOLD, 28));
        firstLab.setBounds(20, 90, 250, 60); //labeling combobox
        mapCombo=new JComboBox(maps);
        mapCombo.addActionListener(new comboClickListener(mapCombo));
        mapCombo.setEditable(false);
        mapLab.setBounds(20, 170, 100, 15);
        mapCombo.setBounds(20, 190, 250, 50);

        buildCombo=new JComboBox(builds);
        buildCombo.addActionListener(new comboClickListener(buildCombo));
        buildCombo.setEditable(false);
        buildLab.setBounds(20, 260, 100, 15);
        buildCombo.setBounds(20, 280, 250, 50);

        floCombo=new JComboBox(flos);
        floCombo.addActionListener(new comboClickListener(floCombo));
        floCombo.setEditable(false);
        floLab.setBounds(20, 350, 100, 15);
        floCombo.setBounds(20, 370, 250, 50);

        roomCombo=new JComboBox(rooms);
        roomCombo.addActionListener(new comboClickListener(roomCombo));
        roomCombo.setEditable(false);
        roomLab.setBounds(20, 440, 100, 15);
        roomCombo.setBounds(20, 460, 250, 50);

        // Adding comboboxes to Frame
        this.add(firstLab);
        this.add(mapLab);
        this.add(roomLab);
        this.add(floLab);
        this.add(buildLab);
        this.add(floCombo);
        this.add(mapCombo);
        this.add(roomCombo);
        this.add(buildCombo);
    }

    // for the Panel of frame, inner classes are used in order to be able to see all interface in a single file
    public class MainPanel extends JPanel {
        public MainPanel(){
            
        }
        public void paint(Graphics g){
            //painting backgroud
            super.paint(g);
            g.setColor(backGroundpink);
            g.fillRect(0, 0, getWidth()/4, getHeight());
            g.setColor(Color.WHITE);
            g.fillRect(getWidth()/4, 0, 3*getWidth()/4, getHeight());
        }
    }

    //actionlisteners of buttons
    //the actions are not done yet
    public class ButAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("test1");
        }
    
        
    }
    protected class comboClickListener implements ActionListener{
        JComboBox c;
        int i=0;
        public comboClickListener(JComboBox c){
            this.c=c;
            this.i=i;
        }
        
        public void actionPerformed(ActionEvent e){
            System.out.println("test2");   
        }
    }
    
}
