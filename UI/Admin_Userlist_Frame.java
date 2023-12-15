package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import UI.User_Frame.ButAction;

public class Admin_Userlist_Frame extends JFrame {
    protected JButton backBut=new JButton();
    protected JPanel contentPane=new JPanel();
    protected Color backGroundpink; //backgroud color
    protected Path_Finder_Frame controller;
    
    Admin_Userlist_Frame(Path_Finder_Frame c){
        controller=c;
        backGroundpink=Color.decode("#dd96b8"); //color adjusment
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("image (18).png").getImage()); //seting icon of the app

        setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User List");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(130, 84, 268, 73);
		contentPane.add(lblNewLabel);
        contentPane.setBackground(backGroundpink);

        ImageIcon image=new ImageIcon("image (8).png");
        JLabel imageLab=new JLabel();
        imageLab.setIcon(image);
        imageLab.setBounds(930, 10, 40, 40);
        contentPane.add(imageLab);

        
        ImageIcon image4=new ImageIcon("image (7).png");
        JLabel imageLab4=new JLabel();
        imageLab4.setIcon(image4);
        imageLab4.setBounds(70, 90, 50, 50);
        contentPane.add(imageLab4);

        backBut.addActionListener(new ButAction("back1"));
        backBut.setBounds(20, 20, 50, 45);
        backBut.setFocusable(false);
        backBut.setBackground(backGroundpink);
        backBut.setIcon(new ImageIcon("image (11).png"));
        backBut.setBorder(BorderFactory.createEmptyBorder()); //setting borders of buttons 
        contentPane.add(backBut);
		
		JLabel lblNewLabel_1 = new JLabel("Editor1_Account");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(86, 184, 200, 62);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Editor");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(450, 184, 124, 62);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("REMOVE");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 25));
		btnNewButton.setBounds(733, 205, 179, 39);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.addActionListener(new ButAction("remove"));
        btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("REMOVE");
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 25));
		btnNewButton_1.setBounds(733, 292, 179, 39);
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setBackground(Color.BLACK);
        btnNewButton_1.addActionListener(new ButAction("remove"));
        btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Admin");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_2_1.setBounds(450, 271, 124, 62);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Admin_Account");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(86, 271, 200, 62);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton_2 = new JButton("REMOVE");
		btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 25));
		btnNewButton_2.setBounds(733, 372, 179, 39);
        btnNewButton_2.setForeground(Color.WHITE);
        btnNewButton_2.setBackground(Color.BLACK);
        btnNewButton_2.addActionListener(new ButAction("remove"));
        btnNewButton_2.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Editor");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_2_2.setBounds(450, 351, 124, 62);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Editor2_Account");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_1_2.setBounds(86, 351, 200, 62);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_3 = new JLabel("Admin Account");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(981, 10, 195, 46);
		contentPane.add(lblNewLabel_3);
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
            if(butType.equals("remove")){
                System.out.println("in progress");
                //user removing from data base should be added here
            }
        }
    }
}
