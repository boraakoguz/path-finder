package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utilities.User;

public class Admin_Userlist_Frame extends JFrame {
    protected Controller backendController;
    protected JButton backBut=new JButton();
    protected JPanel contentPane=new JPanel();
    protected Color backGroundpink; //backgroud color
    protected Path_Finder_Frame controller;
    protected ArrayList<User> userList;
    protected JPanel userlistPanel=new JPanel();
    
    Admin_Userlist_Frame(Path_Finder_Frame c,Controller bc){
        backendController=bc;
        controller=c;
        backGroundpink=Color.decode("#dd96b8"); //color adjusment
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("source/image (18).png").getImage()); //seting icon of the app

        setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User List");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(130, 84, 268, 73);
		contentPane.add(lblNewLabel);
        contentPane.setBackground(backGroundpink);

        ImageIcon image=new ImageIcon("source/image (8).png");
        JLabel imageLab=new JLabel();
        imageLab.setIcon(image);
        imageLab.setBounds(930, 10, 40, 40);
        contentPane.add(imageLab);

        
        ImageIcon image4=new ImageIcon("source/image (7).png");
        JLabel imageLab4=new JLabel();
        imageLab4.setIcon(image4);
        imageLab4.setBounds(70, 90, 50, 50);
        contentPane.add(imageLab4);

        backBut.addActionListener(new ButAction("back1"));
        backBut.setBounds(20, 20, 50, 45);
        backBut.setFocusable(false);
        backBut.setBackground(backGroundpink);
        backBut.setIcon(new ImageIcon("source/image (11).png"));
        backBut.setBorder(BorderFactory.createEmptyBorder()); //setting borders of buttons 
        contentPane.add(backBut);
		
        
		userlistPanel.setBounds( 60, 170, 900,400);
        refreshUserList();
		
        
		
		JLabel lblNewLabel_3 = new JLabel("Admin Account");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(981, 10, 195, 46);
		contentPane.add(lblNewLabel_3);
        contentPane.add(userlistPanel);
    }
    public void refreshUserList(){
        userList = backendController.getUserList();
        userlistPanel.setVisible(false);
        userlistPanel.removeAll();
        userlistPanel.setLayout(new GridLayout(userList.size(),1));
		for (User user : userList) {
			UserMenuObject menuObj = new UserMenuObject(user,backendController,this);
			userlistPanel.add(menuObj);
		}
        userlistPanel.setVisible(true);
    }
    public class ButAction implements ActionListener {
        String butType;
        public ButAction(String s){
            butType=s;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(butType.equals("back1")){
                controller.changeFrame(3);
                //if user clicks to back button the app will be directed to admin welcome page
            }
        }
    }
}
