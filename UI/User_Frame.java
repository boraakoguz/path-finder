package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Building.Space;



//this class runs all actions that user type user can do. basically there are 3 panels; left,middle and right. things happens by changin these panels features. 
public class User_Frame extends JFrame{
    //creating variables
    protected Controller searcher;
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
    protected String[] maps={"","",""};
    protected JList mapList=new JList<>(maps);
    protected JTextField mapText;
    protected JLabel buildLab=new JLabel("Select Building");
    protected String[] builds={"","",""};
    protected JList buildList=new JList<>(builds);
    protected JTextField buildCombo;
    protected JLabel floLab=new JLabel("Selecet Floor");
    protected String[] flos={"","",""};
    protected JList floList=new JList<>(flos);
    protected JTextField floCombo;
    protected JLabel roomLab=new JLabel("Select Room");
    protected String[] rooms={"","",""};
    protected JList roomList=new JList<>(rooms);
    protected JTextField roomCombo;

    protected JLabel first2Lab=new JLabel( "Where do You want to go?");
    protected JLabel build2Lab=new JLabel("Select Building");
    protected String[] builds2={"","",""};
    protected JList build2List=new JList<>(builds2);
    protected JTextField build2Combo;
    protected JLabel flo2Lab=new JLabel("Selecet Floor");
    protected String[] flos2={"","",""};
    protected JList flo2List=new JList<>(flos2);
    protected JTextField flo2Combo;
    protected JLabel room2Lab=new JLabel("Select Room");
    protected String[] rooms2={"","",""};
    protected JList room2List=new JList<>(rooms2);
    protected JTextField room2Combo;



    public User_Frame(Path_Finder_Frame c){
        controller=c;
        searcher=new Controller();
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
        nextBut.setBounds(20, 600, 100, 60);
        nextBut.setForeground(Color.WHITE);
        nextBut.setFocusable(false);
        nextBut.setBackground(Color.BLACK);
        nextBut.setBorder(BorderFactory.createEmptyBorder());

        fOBut.addActionListener(new ButAction("findo1"));
        fOBut.setBounds(150, 600, 100, 60);
        fOBut.setForeground(Color.WHITE);
        fOBut.setFocusable(false);
        fOBut.setBackground(Color.BLACK);
        fOBut.setBorder(BorderFactory.createEmptyBorder());

        //combobox adjusments
        firstLab.setFont(new Font("Arial", Font.BOLD, 28));
        firstLab.setBounds(20, 60, 250, 60); //labeling combobox

        mapText=new JTextField();
        mapText.addKeyListener(new comboClickListener("map"));
        mapText.addFocusListener(new fListener("map"));
        mapList.setBounds(20, 170, 250, 60);
        mapList.setVisible(false);
        mapLab.setBounds(20, 120, 100, 15);
        mapText.setBounds(20, 140, 250, 30);

        buildCombo=new JTextField();
        buildCombo.addKeyListener(new comboClickListener("build"));
        buildCombo.addFocusListener(new fListener("build"));
        buildList.setBounds(20, 290, 250, 60);
        buildList.setVisible(false);
        buildLab.setBounds(20, 240, 100, 15);
        buildCombo.setBounds(20, 260, 250, 30);

        floCombo=new JTextField();
        floCombo.addKeyListener(new comboClickListener("flo"));
        floCombo.addFocusListener(new fListener("flo"));
        floList.setBounds(20, 410, 250, 60);
        floList.setVisible(false);
        floLab.setBounds(20, 360, 100, 15);
        floCombo.setBounds(20, 380, 250, 30);

        roomCombo=new JTextField();
        roomCombo.addKeyListener(new comboClickListener("room"));
        roomCombo.addFocusListener(new fListener("room"));
        roomList.setBounds(20, 530, 250, 60);
        roomList.setVisible(false);
        roomLab.setBounds(20, 480, 100, 15);
        roomCombo.setBounds(20, 500, 250, 30);

        // Adding comboboxes to Frame
        leftPanel.add(firstLab);
        leftPanel.add(mapLab);
        leftPanel.add(roomLab);
        leftPanel.add(floLab);
        leftPanel.add(buildLab);
        leftPanel.add(floCombo);
        leftPanel.add(mapText);
        leftPanel.add(roomCombo);
        leftPanel.add(buildCombo);
        leftPanel.add(mapList);
        leftPanel.add(buildList);
        leftPanel.add(floList);
        leftPanel.add(roomList);
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

        build2Combo=new JTextField();
        build2Combo.addKeyListener(new comboClickListener("build2"));
        build2Combo.addFocusListener(new fListener("build2"));
        build2List.setBounds(20, 190, 250, 60);
        build2List.setVisible(false);
        build2Lab.setBounds(20, 140, 100, 15);
        build2Combo.setBounds(20, 160, 250, 30);

        flo2Combo=new JTextField();
        flo2Combo.addKeyListener(new comboClickListener("flo2"));
        flo2Combo.addFocusListener(new fListener("flo2"));
        flo2List.setBounds(20, 310, 250, 60);
        flo2List.setVisible(false);
        flo2Lab.setBounds(20, 260, 100, 15);
        flo2Combo.setBounds(20, 280, 250, 30);

        room2Combo=new JTextField();
        room2Combo.addKeyListener(new comboClickListener("room2"));
        room2Combo.addFocusListener(new fListener("room2"));
        room2List.setBounds(20, 430, 250, 60);
        room2List.setVisible(false);
        room2Lab.setBounds(20, 380, 100, 15);
        room2Combo.setBounds(20, 400, 250, 30);

        // Adding comboboxes to Frame
        leftPanel.add(first2Lab);
        leftPanel.add(room2Lab);
        leftPanel.add(flo2Lab);
        leftPanel.add(build2Lab);
        leftPanel.add(flo2Combo);
        leftPanel.add(room2Combo);
        leftPanel.add(build2Combo);
        leftPanel.add(build2List);
        leftPanel.add(flo2List);
        leftPanel.add(room2List);

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
    //Search bar methots 
    // search method should be added here
    protected void getText(String s){
        ArrayList<Space> searchList=new ArrayList<>();
        if(s.equals("map")){
            maps[0]=searchList.get(0).getName();
            if(searchList.get(1)==null){
                maps[1]=null;
            }
            else{
                maps[1]=searchList.get(1).getName();
            }
            if(searchList.get(2)==null){
                maps[2]=null;
            }
            else{
                maps[2]=searchList.get(2).getName();
            }
        }
        else if(s.equals("build")){
            builds[0]=searchList.get(0).getName();
            if(searchList.get(1)==null){
                builds[1]=null;
            }
            else{
                builds[1]=searchList.get(1).getName();
            }
            if(searchList.get(2)==null){
                builds[2]=null;
            }
            else{
                builds[2]=searchList.get(2).getName();
            }
        }
        else if(s.equals("flo")){
            flos[0]=searchList.get(0).getName();
            if(searchList.get(1)==null){
                flos[1]=null;
            }
            else{
                flos[1]=searchList.get(1).getName();
            }
            if(searchList.get(2)==null){
                flos[2]=null;
            }
            else{
                flos[2]=searchList.get(2).getName();
            }
        }
        else if(s.equals("room")){
            rooms[0]=searchList.get(0).getName();
            if(searchList.get(1)==null){
                rooms[1]=null;
            }
            else{
                rooms[1]=searchList.get(1).getName();
            }
            if(searchList.get(2)==null){
                rooms[2]=null;
            }
            else{
                rooms[2]=searchList.get(2).getName();
            }
        }
        else if(s.equals("build2")){
            builds2[0]=searchList.get(0).getName();
            if(searchList.get(1)==null){
                builds2[1]=null;
            }
            else{
                builds2[1]=searchList.get(1).getName();
            }
            if(searchList.get(2)==null){
                builds2[2]=null;
            }
            else{
                builds2[2]=searchList.get(2).getName();
            }
        }
        else if(s.equals("flo2")){
            flos2[0]=searchList.get(0).getName();
            if(searchList.get(1)==null){
                flos2[1]=null;
            }
            else{
                flos2[1]=searchList.get(1).getName();
            }
            if(searchList.get(2)==null){
                flos2[2]=null;
            }
            else{
                flos2[2]=searchList.get(2).getName();
            }
        }
        else if(s.equals("room2")){
            rooms2[0]=searchList.get(0).getName();
            if(searchList.get(1)==null){
                rooms2[1]=null;
            }
            else{
                rooms2[1]=searchList.get(1).getName();
            }
            if(searchList.get(2)==null){
                rooms2[2]=null;
            }
            else{
                rooms2[2]=searchList.get(2).getName();
            }
        }

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

    //Searchbars' Listeners 
       
    protected class comboClickListener implements KeyListener{
        String comboType;
        
        public comboClickListener(String s){
            comboType=s;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if(comboType.equals("map")){
            getText("map");
            repaint();
        }
        else if(comboType.equals("build")){
            getText("build");
            repaint();
        }
        else if(comboType.equals("flo")){
            getText("flo");
            repaint();
        }
        else if(comboType.equals("room")){
            getText("room");
            repaint();
        }
        else if(comboType.equals("build2")){
            getText("build2");
            repaint();
        }
        else if(comboType.equals("flo2")){
            getText("flo2");
            repaint();
        }
        else if(comboType.equals("room2")){
            getText("room2");
            repaint();
        }
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("hello");
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
    }

   protected class fListener implements FocusListener{
    String comboType;
    fListener(String s){
        comboType=s;
    }
    @Override
    public void focusGained(FocusEvent e) {
        if(comboType.equals("map")){
            mapList.setVisible(true);
            buildList.setVisible(false);
            roomList.setVisible(false);
            floList.setVisible(false);
        }
        else if(comboType.equals("build")){
            mapList.setVisible(false);
            buildList.setVisible(true);
            roomList.setVisible(false);
            floList.setVisible(false);
        }
        else if(comboType.equals("flo")){
            mapList.setVisible(false);
            buildList.setVisible(false);
            roomList.setVisible(false);
            floList.setVisible(true);
        }
        else if(comboType.equals("room")){
            mapList.setVisible(false);
            buildList.setVisible(false);
            roomList.setVisible(true);
            floList.setVisible(false);
        }
        else if(comboType.equals("build2")){
            build2List.setVisible(true);
            room2List.setVisible(false);
            flo2List.setVisible(false);
        }
        else if(comboType.equals("flo2")){
            build2List.setVisible(false);
            room2List.setVisible(false);
            flo2List.setVisible(true);
        }
        else if(comboType.equals("room2")){
            build2List.setVisible(false);
            room2List.setVisible(true);
            flo2List.setVisible(false);
        }
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        mapList.setVisible(false);
        buildList.setVisible(false);
        roomList.setVisible(false);
        floList.setVisible(false);
        build2List.setVisible(false);
        room2List.setVisible(false);
        flo2List.setVisible(false);
    }
    
   }

    
}