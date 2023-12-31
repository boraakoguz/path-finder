package UI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Building.Space;



//this class runs all actions that user type user can do. basically there are 3 panels; left,middle and right. things happens by changin these panels features. 
public class User_Frame extends JFrame{
    protected Path_Finder_Frame controller;
    protected boolean[] isMenuchange=new boolean[3];
    protected int[] lastPanels=new int[3];

    protected ContentPanel contentPanel=new ContentPanel();
    protected JPanel leftPanel=new JPanel();
    protected JPanel rightPanel=new JPanel();
    protected DirectionsPanel directionsPanel;

    protected JButton logBut=new JButton("Login");
    protected JButton feedBut=new JButton("Create Feedback");
    protected JButton watBut=new JButton();
    protected JButton backBut=new JButton();
    protected JButton menuBut=new JButton();
    protected JButton fOBut=new JButton("Find Objects"); //find object button
    protected JButton nextBut=new JButton("Next");
    protected JButton goBut=new JButton("Go");
    protected Color backGroundpink; //backgroud color
    protected JLabel firstLab=new JLabel( "Where Are You?");
    protected JLabel mapLab=new JLabel("Select Map");
    protected JComboBox<String> mapPickList = new JComboBox<String>();
    protected DefaultListModel<Space> startSearchListModel = new DefaultListModel<Space>();
    protected JList<Space> startSearchList = new JList<Space>(startSearchListModel);
    protected JTextField startSearchTextField;
    ImageIcon image;
    JLabel imageLab;

    protected DefaultListModel<Space> targetSearchListModel = new DefaultListModel<Space>();
    protected JList<Space> targetSearchList = new JList<Space>(targetSearchListModel);
    protected JTextField targetSearchTextField;

    protected Space startLocation;
    protected Space targetLocation;

    protected JLabel first2Lab=new JLabel( "Where do You want to go?");

    protected Controller backendController;
    protected JComboBox<Space> searchBar = new JComboBox<Space>();


    public User_Frame(Path_Finder_Frame c,Controller bc){
        backendController=bc;
        controller=c;
        backGroundpink=Color.decode("#dd96b8"); //color adjusment
        this.directionsPanel = new DirectionsPanel(backendController);
        this.directionsPanel.setBounds(300,0,700,700);
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,735);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("source/image (18).png").getImage()); //seting icon of the app
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        menuBut.addActionListener(new ButAction("menu1"));
        menuBut.setBounds(1140, 20, 35, 35);
        menuBut.setFocusable(false);

        menuBut.setIcon(new ImageIcon("source/image (10).png"));
        menuBut.setBorder(BorderFactory.createEmptyBorder());
        contentPanel.add(menuBut);

        setLeftPanel(backGroundpink);
        
        contentPanel.add(leftPanel);
        contentPanel.add(rightPanel);
        contentPanel.add(directionsPanel);
        left1();
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
        backBut.setIcon(new ImageIcon("source/image (11).png"));
        backBut.setBorder(BorderFactory.createEmptyBorder()); //setting borders of buttons 
        leftPanel.add(backBut);
        repaint();
    }
    
    
    //this method opens the menu
    protected void right1(){
        

        rightPanel.setBackground(backGroundpink);
        rightPanel.setBounds(1000, 0, 200, 700);
        rightPanel.setLayout(null);
        

        logBut.addActionListener(new ButAction("log"));
        logBut.setBounds(0, 60, 100, 40);
        logBut.setFocusable(false);
        logBut.setBackground(backGroundpink);
        logBut.setIcon(new ImageIcon("source/image (22).png"));
        logBut.setBorder(BorderFactory.createEmptyBorder());

        feedBut.addActionListener(new ButAction("feed"));
        feedBut.setBounds(10, 120, 150, 40);
        feedBut.setFocusable(false);
        feedBut.setBackground(backGroundpink);
        feedBut.setIcon(new ImageIcon("source/image (9).png"));
        feedBut.setBorder(BorderFactory.createEmptyBorder());

        rightPanel.add(logBut);
        rightPanel.add(feedBut);
        contentPanel.add(rightPanel);

        
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
        
        
        mapLab.setBounds(20, 120, 100, 15);
        mapPickList.setBounds(20,140,250,30);
        mapPickList.addActionListener(new comboBoxActionListener());
        for (String map : backendController.getAvailableMaps()) {
            mapPickList.addItem(map);
        }

        JLabel searchBarLabel = new JLabel("Search where you are");
        searchBarLabel.setBounds(20,180,250,20);
        startSearchTextField = new JTextField();
        startSearchTextField.addKeyListener(new startSearch());
        startSearchTextField.addFocusListener(new fListener("start"));
        startSearchList.setBounds(20, 230, 250, 60);
        startSearchList.setVisible(false);
        startSearchList.addMouseListener(new startListActionListener());
        startSearchTextField.setBounds(20, 200, 250, 30);
        
        
        // Adding comboboxes to Frame
        leftPanel.add(firstLab);
        leftPanel.add(searchBar);
        leftPanel.add(mapLab);
        leftPanel.add(startSearchTextField);
        leftPanel.add(startSearchList);
        leftPanel.add(mapPickList);
        leftPanel.add(searchBarLabel);
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
        first2Lab.setFont(new Font("Arial", Font.BOLD, 19));
        first2Lab.setBounds(20, 90, 250, 60); //labeling combobox
        
        targetSearchTextField=new JTextField();
        targetSearchTextField.addKeyListener(new targetSearch());
        targetSearchTextField.addFocusListener(new fListener("target"));
        targetSearchList.setBounds(20, 230, 250, 60);
        targetSearchList.setVisible(false);
        targetSearchList.addMouseListener(new targetListActionListener());
        targetSearchTextField.setBounds(20, 200, 250, 30);
        
        leftPanel.add(targetSearchTextField);
        leftPanel.add(targetSearchList);

        // Adding comboboxes to Frame
        leftPanel.add(first2Lab);
        // Adding buttons to Frame  
        leftPanel.add(goBut);
    }

    public class ContentPanel extends JPanel{
        private BufferedImage image;
        public ContentPanel()
        {
            setOpaque(true);
            try
            {
                image = ImageIO.read(new File("source/image (30).png"));
            }
            catch(IOException ioe)
            {
                System.out.println("Unable to fetch image.");
                ioe.printStackTrace();
            }
        }

        @Override
        public Dimension getPreferredSize()
        {
            return (new Dimension(image.getWidth(), image.getHeight()));
        }
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
    
    //Search bar methods 
    // search method should be added here
    
    //actionlisteners of buttons
    public class ButAction implements ActionListener {
        String butType;
        public ButAction(String s){
            butType=s;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(butType.equals("findo1")){
                if(startLocation==null){
                    JOptionPane.showMessageDialog(null, "Please Enter Your Location Properly");
                }
                else{
                    isMenuchange[0]=true;
                    setLeftPanel(backGroundpink);

                    watBut.addActionListener(new ButAction("wat"));
                    watBut.setBounds(30, 200, 100, 100);
                    watBut.setFocusable(false);
                    watBut.setBackground(backGroundpink);
                    watBut.setIcon(new ImageIcon("source/image (23).png"));
                    watBut.setBorder(BorderFactory.createEmptyBorder());

                    leftPanel.add(watBut);
                }
            }
            else if(butType.equals("back1")){
                if(isMenuchange[0]){
                    startLocation=null;
                    targetLocation=null;
                    isMenuchange[0]=false;
                    setLeftPanel(backGroundpink);
                    left1();
                }
                if(isMenuchange[1]){
                    directionsPanel.setVisibility(false);
                    isMenuchange[1]=false;
                }
                if(isMenuchange[2]){
                    rightPanel.removeAll();
                    contentPanel.remove(rightPanel);
                    isMenuchange[2]=false;
                }
            }
            else if(butType.equals("menu1")){
                right1();
                isMenuchange[2]=true;
            }
            else if(butType.equals("next1")){
                if(startLocation==null){
                    JOptionPane.showMessageDialog(null, "Please Enter Your Location Properly");
                }
                else{
                    isMenuchange[0]=true;
                    setLeftPanel(backGroundpink);
                    left2();
                }
            }
            else if(butType.equals("wat")){
                directionsPanel.nearestObject(startLocation, 1);
                directionsPanel.setVisibility(true);
                isMenuchange[1]=true;
            }
            else if(butType.equals("vend")){
                directionsPanel.nearestObject(startLocation, 0);
                directionsPanel.setVisibility(true);
            }
            else if(butType.equals("go")){
                if(targetLocation==null){
                    JOptionPane.showMessageDialog(null, "Please Enter Your Destination Properly");
                }
                else{
                    isMenuchange[1]=true;
                    directionsPanel.setDirections(startLocation, targetLocation);
                    directionsPanel.setVisibility(true);

                }              
            }

            else if(butType.equals("log")){
                controller.changeFrame(1);
            }
            else if(butType.equals("feed")){
                controller.changeFrame(2);
            }
            repaint();
        }
    
        
    }

    //Searchbars' Listeners 
       
    protected class startSearch implements KeyListener{
  
        @Override
        public void keyTyped(KeyEvent e) {
            ArrayList<Space> searchResults= backendController.search(startSearchTextField.getText()+ e.getKeyChar());
            startSearchListModel.clear();
            for (Space space : searchResults) {
                startSearchListModel.addElement(space);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
    }

    protected class targetSearch implements KeyListener{
  
        @Override
        public void keyTyped(KeyEvent e) {
            ArrayList<Space> searchResults= backendController.search(targetSearchTextField.getText()+ e.getKeyChar());
            targetSearchListModel.clear();
            for (Space space : searchResults) {
                targetSearchListModel.addElement(space);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {            
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
        if(comboType.equals("start")){
            startSearchList.setVisible(true);
        }
        else if(comboType.equals("target")){
            startSearchList.setVisible(false);
            targetSearchList.setVisible(true);
        }
        else{
            startSearchList.setVisible(false);
        }
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        startSearchList.setVisible(false);
    }
    
   }
   protected class comboBoxActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        String pick = (String)mapPickList.getSelectedItem();
        backendController.setCurrentMap(pick);
    }
    
   }
   protected class startListActionListener implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        Space pick = startSearchList.getSelectedValue();
        startSearchTextField.setText(pick.getName());
        startLocation = pick;
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
   }
   protected class targetListActionListener implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        Space pick = targetSearchList.getSelectedValue();
        targetSearchTextField.setText(pick.getName());
        targetLocation = pick;
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
   }
    
}