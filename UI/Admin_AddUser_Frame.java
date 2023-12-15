package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin_AddUser_Frame extends JFrame{
    
    protected JButton backBut=new JButton();
    protected JPanel contentPane=new JPanel();
    protected Color backGroundpink; //backgroud color
    protected Path_Finder_Frame controller;
    protected JTextField textField;
	protected JTextField textField_1;

    Admin_AddUser_Frame(Path_Finder_Frame c){
        controller=c;
        backGroundpink=Color.decode("#dd96b8"); //color adjusment
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("image (18).png").getImage()); //seting icon of the app

        setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(backGroundpink);
		JLabel lblNewLabel = new JLabel("Add User");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(173, 96, 268, 73);
		contentPane.add(lblNewLabel);

		
		JLabel lblNewLabel_3 = new JLabel("Admin Account");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(981, 10, 195, 46);
		contentPane.add(lblNewLabel_3);

        ImageIcon image=new ImageIcon("image (8).png");
        JLabel imageLab=new JLabel();
        imageLab.setIcon(image);
        imageLab.setBounds(930, 10, 40, 40);
        contentPane.add(imageLab);

        ImageIcon image3=new ImageIcon("image (24).png");
        JLabel imageLab3=new JLabel();
        imageLab3.setIcon(image3);
        imageLab3.setBounds(120, 105, 50, 50);
        contentPane.add(imageLab3);
		
		textField = new JTextField();
		textField.setBounds(130, 314, 279, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Mail Adress");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(130, 277, 170, 27);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(130, 407, 279, 46);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter Passoword");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(130, 370, 170, 27);
		contentPane.add(lblNewLabel_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(130, 216, 279, 46);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1_2 = new JLabel("Choose User Type");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(130, 179, 170, 27);
		contentPane.add(lblNewLabel_1_2);
		
		JButton btnNewButton = new JButton("Add User");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
		btnNewButton.setBounds(110, 510, 150, 46);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.addActionListener(new addListener("add"));
        btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(btnNewButton);

        JButton btnNewButton1 = new JButton("Cancel");
		btnNewButton1.setFont(new Font("Arial", Font.PLAIN, 18));
		btnNewButton1.setBounds(280, 510, 150, 46);
        btnNewButton1.setForeground(Color.WHITE);
        btnNewButton1.setBackground(new Color(0, 0, 0));
        btnNewButton1.addActionListener(new addListener("cancel"));
        btnNewButton1.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(btnNewButton1);
    }

    public class addListener implements ActionListener {
        String butType;
        addListener(String s){
            butType=s;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(butType.equals("cancel")){
                controller.changeFrame(3);
                //if user enters cansel the app will be direted to admin welcome page
            }
            if(butType.equals("add")){
                controller.changeFrame(3);
                //users should be added to database in here
            }
        }
    }
}
