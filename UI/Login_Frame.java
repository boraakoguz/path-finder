package UI;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login_Frame extends JFrame{
    Path_Finder_Frame controller;
    protected Color backGroundpink; //backgroud color
    protected JPanel contentPane=new JPanel();
	protected JTextField textField;
	protected JTextField textField_1;

    public Login_Frame(Path_Finder_Frame c){
        controller=c;
        backGroundpink=Color.decode("#dd96b8"); //color adjusment
        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,700);
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
		btnNewButton.setBackground(new Color(0, 0, 255));
		btnNewButton.setBounds(311, 530, 160, 50);
		panel_1.add(btnNewButton);
    }

}
