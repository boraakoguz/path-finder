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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Utilities.Login;


public class Login_Frame extends JFrame{
	protected Controller backendController;
    Path_Finder_Frame controller;
    protected Color backGroundpink; //backgroud color
    protected JPanel contentPane=new JPanel();
	protected JTextField textField;
	protected JTextField textField_1;

    public Login_Frame(Path_Finder_Frame c,Controller bc){
		backendController=bc;
        controller=c;
        backGroundpink=Color.decode("#dd96b8"); //color adjusment
        this.setTitle("Path Finder");
		setSize(1200,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(backGroundpink);
		panel.setBounds(0, 0, 399, 653);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(400, 0, 776, 653);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
        ImageIcon image=new ImageIcon("image (17).png");
        JLabel imageLab=new JLabel();
        imageLab.setIcon(image);
        imageLab.setBounds(80, 220, 200, 200);
        panel.add(imageLab);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(327, 75, 243, 58);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(227, 262, 300, 58);
		panel_1.add(textField);
		textField.setColumns(10);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(227, 424, 300, 58);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("Please Enter Your Email");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(227, 222, 300, 30);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Please Enter Your Password");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(227, 393, 300, 21);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton.setOpaque(true);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(210, 530, 140, 50);
		btnNewButton.addActionListener(new textListener("login"));
		btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		panel_1.add(btnNewButton);

		JButton cancelbut = new JButton("Cancel");
		cancelbut.setFont(new Font("Arial", Font.PLAIN, 16));
		cancelbut.setOpaque(true);
		cancelbut.setBorderPainted(false);
		cancelbut.setBackground(Color.BLACK);
		cancelbut.setForeground(Color.WHITE);
		cancelbut.setBounds(400, 530, 140, 50);
		cancelbut.addActionListener(new textListener("cancel"));
		cancelbut.setBorder(BorderFactory.createEmptyBorder());
		panel_1.add(cancelbut);
    }
	protected boolean dummyAdmin(String mail,String password){
		if((mail.equals("admin"))&&(password.equals("admin"))){
			return true;
		}
		else{
			return false;
		}
	}

	protected boolean dummyEditor(String mail,String password){
		if((mail.equals("editor"))&&(password.equals("editor"))){
			return true;
		}
		else{
			return false;
		}
	}
	public class textListener implements ActionListener {
		String mail;
		String password;
		String butType;
		public textListener(String s){
			butType=s;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//if user enters something and clicks to login app will automatically directed to admin or editor welcome page
			//if user clicks cancel button then the app wiil goes back to main page
			if(butType.equals("login")){
				mail=textField.getText();
				password=textField_1.getText();
				if((mail.equals(""))||(password.equals(""))){
					JOptionPane.showMessageDialog(null, "Enter Both Username and Password");
				}
				else{
					int result = backendController.login(mail, password);
					switch (result) {
						case 0:
							JOptionPane.showMessageDialog(null, "Wrong Username");
							break;
						case 1:
							JOptionPane.showMessageDialog(null, "Wrong Password");
							break;
						case 2:
							controller.changeFrame(4);
							break;
						case 3:
							controller.changeFrame(3);
							break;
						default:
							JOptionPane.showMessageDialog(null, "An Error occured please try later");
							break;
					}
				}
				
			}
			else if(butType.equals("cancel")){
				controller.changeFrame(0);
			}
		}
	
		
	}
}
