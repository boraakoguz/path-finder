package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//this class runs all actions that user type user can do. basically there are 3 panels; left,middle and right. things happens by changin these panels features. 
public class User_Frame extends JFrame {
    //creating variables
    protected Path_Finder_Frame controller;
    protected boolean[] isMenuchange=new boolean[3];
    protected int[] lastPanels=new int[3];

    protected JPanel contentPanel=new JPanel();
    protected JPanel leftPanel=new JPanel();
    protected JPanel rightPanel=new JPanel();
    protected JPanel middlePanel=new JPanel();

    protected JButton logBut=new JButton("Login");
    protected JButton feedBut=new JButton("Create Feedback");
    protected JButton vendBut=new JButton();
    protected JButton watBut=new JButton();
    protected JButton backBut=new JButton();
    protected JButton menuBut=new JButton();
    protected JButton fOBut=new JButton("Find Objects"); //find object button
    protected JButton nextBut=new JButton("Next");
    protected JButton goBut=new JButton("Go");
    protected Color backGroundpink; //backgroud color
    protected JLabel firstLab=new JLabel( "Where Are You?");
    protected JLabel mapLab=new JLabel("Select Map");
    protected String[] maps={"C1","C2"};
    protected JComboBox mapCombo;
    protected JLabel buildLab=new JLabel("Select Building");
    protected String[] builds={"C1","C2"};
    protected JComboBox buildCombo;
    protected JLabel floLab=new JLabel("Selecet Floor");
    protected String[] flos={"C1","C2"};
    protected JComboBox floCombo;
    protected JLabel roomLab=new JLabel("Select Room");
    protected String[] rooms={"C1","C2"};
    protected JComboBox roomCombo;

    protected JLabel first2Lab=new JLabel( "Where do You want to go?");
    protected JLabel build2Lab=new JLabel("Select Building");
    protected String[] builds2={"C1","C2"};
    protected JComboBox build2Combo;
    protected JLabel flo2Lab=new JLabel("Selecet Floor");
    protected String[] flos2={"C1","C2"};
    protected JComboBox flo2Combo;
    protected JLabel room2Lab=new JLabel("Select Room");
    protected String[] rooms2={"C1","C2"};
    protected JComboBox room2Combo;



    public User_Frame(Path_Finder_Frame c){
        controller=c;
        backGroundpink=Color.decode("#dd96b8"); //color adjusment
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("image (18).png").getImage()); //seting icon of the app
        setContentPane(contentPanel);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(null);
        setLeftPanel(backGroundpink);
        setMiddlePanel(Color.WHITE);
        setRightPanel(Color.WHITE);
        contentPanel.add(leftPanel);
        contentPanel.add(middlePanel);
        contentPanel.add(rightPanel);
        left1();
        

        //buts();
        //comboBoxs();
    }

    //these set methods sets panels to default versions 
    protected void setLeftPanel(Color c){
        leftPanel.removeAll();
        leftPanel.setBackground(c);
        leftPanel.setBounds(0, 0, 300, 700);
        leftPanel.setLayout(null);
        backBut.addActionListener(new ButAction("back1"));
        backBut.setBounds(20, 20, 50, 45);
        backBut.setFocusable(false);
        backBut.setBackground(c);
        backBut.setIcon(new ImageIcon("image (11).png"));
        backBut.setBorder(BorderFactory.createEmptyBorder()); //setting borders of buttons 
        leftPanel.add(backBut);
        repaint();
    }
    protected void setMiddlePanel(Color c){
        middlePanel.removeAll();
        middlePanel.setBackground(c);
        middlePanel.setBounds(300, 0, 700, 700);
        middlePanel.setLayout(null);
        repaint();
    }
    protected void setRightPanel(Color c){
        rightPanel.removeAll();
        rightPanel.setBackground(c);
        rightPanel.setBounds(1000, 0, 200, 700);
        rightPanel.setLayout(null);
        menuBut.addActionListener(new ButAction("menu1"));
        menuBut.setBounds(140, 20, 35, 35);
        menuBut.setFocusable(false);
        menuBut.setBackground(c);
        menuBut.setIcon(new ImageIcon("image (10).png"));
        menuBut.setBorder(BorderFactory.createEmptyBorder());
        rightPanel.add(menuBut);
        repaint();
    }
    
    //this method opens the menu
    protected void right1(){
        setRightPanel(backGroundpink);

        logBut.addActionListener(new ButAction("log"));
        logBut.setBounds(0, 60, 100, 40);
        logBut.setFocusable(false);
        logBut.setBackground(backGroundpink);
        logBut.setIcon(new ImageIcon("image (22).png"));
        logBut.setBorder(BorderFactory.createEmptyBorder());

        feedBut.addActionListener(new ButAction("feed"));
        feedBut.setBounds(10, 120, 150, 40);
        feedBut.setFocusable(false);
        feedBut.setBackground(backGroundpink);
        feedBut.setIcon(new ImageIcon("image (9).png"));
        feedBut.setBorder(BorderFactory.createEmptyBorder());

        rightPanel.add(logBut);
        rightPanel.add(feedBut);
    }
    //this method creates "where are you panel"
    protected void left1(){
        //button adjusments
        nextBut.addActionListener(new ButAction("next1"));
        nextBut.setBounds(20, 550, 100, 60);
        nextBut.setForeground(Color.WHITE);
        nextBut.setFocusable(false);
        nextBut.setBackground(Color.BLACK);
        nextBut.setBorder(BorderFactory.createEmptyBorder());

        fOBut.addActionListener(new ButAction("findo1"));
        fOBut.setBounds(150, 550, 100, 60);
        fOBut.setForeground(Color.WHITE);
        fOBut.setFocusable(false);
        fOBut.setBackground(Color.BLACK);
        fOBut.setBorder(BorderFactory.createEmptyBorder());

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
        leftPanel.add(firstLab);
        leftPanel.add(mapLab);
        leftPanel.add(roomLab);
        leftPanel.add(floLab);
        leftPanel.add(buildLab);
        leftPanel.add(floCombo);
        leftPanel.add(mapCombo);
        leftPanel.add(roomCombo);
        leftPanel.add(buildCombo);

        // Adding buttons to Frame  
        leftPanel.add(nextBut);
        leftPanel.add(fOBut);
    }
    //this method crates "where do you want to go panel"
    protected void left2(){
        //button adjusments
        goBut.addActionListener(new ButAction("go"));
        goBut.setBounds(20, 550, 100, 60);
        goBut.setForeground(Color.WHITE);
        goBut.setFocusable(false);
        goBut.setBackground(Color.BLACK);
        goBut.setBorder(BorderFactory.createEmptyBorder());

        //combobox adjusments
        first2Lab.setFont(new Font("Arial", Font.BOLD, 20));
        first2Lab.setBounds(20, 90, 250, 60); //labeling combobox

        build2Combo=new JComboBox(builds);
        build2Combo.addActionListener(new comboClickListener(build2Combo));
        build2Combo.setEditable(false);
        build2Lab.setBounds(20, 260, 100, 15);
        build2Combo.setBounds(20, 280, 250, 50);

        flo2Combo=new JComboBox(flos);
        flo2Combo.addActionListener(new comboClickListener(flo2Combo));
        flo2Combo.setEditable(false);
        flo2Lab.setBounds(20, 350, 100, 15);
        flo2Combo.setBounds(20, 370, 250, 50);

        room2Combo=new JComboBox(rooms);
        room2Combo.addActionListener(new comboClickListener(room2Combo));
        room2Combo.setEditable(false);
        room2Lab.setBounds(20, 440, 100, 15);
        room2Combo.setBounds(20, 460, 250, 50);

        // Adding comboboxes to Frame
        leftPanel.add(first2Lab);
        leftPanel.add(room2Lab);
        leftPanel.add(flo2Lab);
        leftPanel.add(build2Lab);
        leftPanel.add(flo2Combo);
        leftPanel.add(room2Combo);
        leftPanel.add(build2Combo);

        // Adding buttons to Frame  
        leftPanel.add(goBut);
    }
    //this method will show the directions this method is where backend should be added
    protected void middle1(){
        setMiddlePanel(Color.WHITE);
        JLabel lab=new JLabel("Directions....");
        lab.setBounds(250, 250, 100, 100);
        middlePanel.add(lab);
        repaint();
    }
    //actionlisteners of buttons
    public class ButAction implements ActionListener {
        String butType;
        public ButAction(String s){
            butType=s;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(butType.equals("findo1")){
                isMenuchange[0]=true;
                setLeftPanel(backGroundpink);
                
                watBut.addActionListener(new ButAction("wat"));
                watBut.setBounds(40, 200, 200, 200);
                watBut.setFocusable(false);
                watBut.setBackground(backGroundpink);
                watBut.setIcon(new ImageIcon("image (23).png"));
                watBut.setBorder(BorderFactory.createEmptyBorder());

                vendBut.addActionListener(new ButAction("vend"));
                vendBut.setBounds(60, 450, 200, 200);
                vendBut.setFocusable(false);
                vendBut.setBackground(backGroundpink);
                vendBut.setIcon(new ImageIcon("image (16).png"));
                vendBut.setBorder(BorderFactory.createEmptyBorder());

                leftPanel.add(vendBut);
                leftPanel.add(watBut);
            }
            else if(butType.equals("back1")){
                if(isMenuchange[0]){
                    setLeftPanel(backGroundpink);
                    left1();
                    isMenuchange[0]=false;
                }
                if(isMenuchange[1]){
                    setMiddlePanel(Color.WHITE);
                    isMenuchange[1]=false;
                }
                if(isMenuchange[2]){
                    setRightPanel(Color.WHITE);
                    isMenuchange[2]=false;
                }
            }
            else if(butType.equals("menu1")){
                    right1();
                    isMenuchange[2]=true;
            }
            else if(butType.equals("next1")){
                isMenuchange[0]=true;
                setLeftPanel(backGroundpink);
                left2();
            }

            else if((butType.equals("wat"))||(butType.equals("vend"))){
                isMenuchange[1]=true;
                middle1();
                // the path directions of objects should be added here 
            }

            else if(butType.equals("go")){
                isMenuchange[1]=true;
                middle1();
                // the path directions of space to space should be added here 
            }

            else if(butType.equals("log")){
                controller.changeFrame(1);
            }
            else if(butType.equals("feed")){
                controller.changeFrame(2);
            }
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