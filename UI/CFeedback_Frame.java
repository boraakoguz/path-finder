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
import javax.swing.JTextArea;
import javax.swing.JTextField; 
 
public class CFeedback_Frame extends JFrame { 
	protected Controller backendController; 
    Path_Finder_Frame controller; 
    protected Color backGroundpink; //backgroud color 
    protected JPanel contentPane=new JPanel(); 
    protected JTextField textField; 
	protected JTextField textField_1; 
	protected JTextField textField_2; 
	protected JTextField textField_3; 
	protected JTextField textField_4; 
	protected JTextField textField_5; 
	protected JTextArea textField_6; 
 
    public CFeedback_Frame(Path_Finder_Frame c,Controller bc){ 
		backendController=bc; 
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
 
		JLabel lblNewLabel = new JLabel("Create Feedback"); 
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30)); 
		lblNewLabel.setBounds(75, 50, 374, 59); 
		contentPane.add(lblNewLabel); 
		 
		JLabel lblNewLabel_1 = new JLabel("Please Enter Your Name"); 
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18)); 
		lblNewLabel_1.setBounds(31, 109, 220, 21); 
		contentPane.add(lblNewLabel_1); 
 
		ImageIcon image=new ImageIcon("image (9).png"); 
        JLabel imageLab=new JLabel(); 
        imageLab.setIcon(image); 
        imageLab.setBounds(20, 60, 40, 40); 
        contentPane.add(imageLab); 
 
		textField = new JTextField(); 
		textField.setBounds(26, 140, 220, 37); 
		contentPane.add(textField); 
		textField.setColumns(10); 
		 
		JLabel lblNewLabel_2 = new JLabel("Location"); 
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 30)); 
		lblNewLabel_2.setBounds(26, 288, 184, 30); 
		contentPane.add(lblNewLabel_2); 
		 
		JLabel lblNewLabel_1_1 = new JLabel("Please Enter Your Mail"); 
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 18)); 
		lblNewLabel_1_1.setBounds(31, 187, 220, 21); 
		contentPane.add(lblNewLabel_1_1); 
		 
		textField_1 = new JTextField(); 
		textField_1.setColumns(10); 
		textField_1.setBounds(26, 218, 220, 37); 
		contentPane.add(textField_1); 

        JButton backBut=new JButton();
        backBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeFrame(0);
            }
            
        });
        backBut.setBounds(20, 10, 50, 45);
        backBut.setFocusable(false);
        backBut.setBackground(backGroundpink);
        backBut.setIcon(new ImageIcon("image (11).png"));
        backBut.setBorder(BorderFactory.createEmptyBorder()); //setting borders of buttons 
        contentPane.add(backBut);
		 
		JLabel lblNewLabel_1_2 = new JLabel("Select Map"); 
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 18)); 
		lblNewLabel_1_2.setBounds(31, 328, 220, 21); 
		contentPane.add(lblNewLabel_1_2); 
		 
		textField_2 = new JTextField(); 
		textField_2.setColumns(10); 
		textField_2.setBounds(26, 359, 220, 37); 
		contentPane.add(textField_2); 
		 
		JLabel lblNewLabel_1_1_1 = new JLabel("Select Building"); 
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18)); 
		lblNewLabel_1_1_1.setBounds(31, 406, 220, 21); 
		contentPane.add(lblNewLabel_1_1_1); 
		 
		textField_3 = new JTextField(); 
		textField_3.setColumns(10); 
		textField_3.setBounds(26, 437, 220, 37); 
		contentPane.add(textField_3); 
		 
		JLabel lblNewLabel_1_3 = new JLabel("Select Floor"); 
		lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 18)); 
		lblNewLabel_1_3.setBounds(36, 484, 220, 21); 
		contentPane.add(lblNewLabel_1_3); 
		 
		textField_4 = new JTextField(); 
		textField_4.setColumns(10); 
		textField_4.setBounds(31, 515, 220, 37); 
		contentPane.add(textField_4); 
		 
		JLabel lblNewLabel_1_1_2 = new JLabel("Select Room"); 
		lblNewLabel_1_1_2.setFont(new Font("Arial", Font.PLAIN, 18)); 
		lblNewLabel_1_1_2.setBounds(36, 562, 220, 21); 
		contentPane.add(lblNewLabel_1_1_2); 
		 
		textField_5 = new JTextField(); 
		textField_5.setColumns(10); 
		textField_5.setBounds(31, 593, 220, 37); 
		contentPane.add(textField_5); 
		 
		JLabel lblNewLabel_1_4 = new JLabel("Write Your Problem Here"); 
		lblNewLabel_1_4.setFont(new Font("Arial", Font.PLAIN, 18)); 
		lblNewLabel_1_4.setBounds(436, 115, 220, 21); 
		contentPane.add(lblNewLabel_1_4); 
		 
		textField_6 = new JTextArea(); 
		textField_6.setBounds(446, 149, 636, 411); 
		contentPane.add(textField_6); 
		textField_6.setColumns(10); 
		 
		JButton btnNewButton = new JButton("Submit"); 
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18)); 
        btnNewButton.setForeground(Color.WHITE); 
        btnNewButton.setBackground(Color.BLACK); 
		btnNewButton.setBounds(727, 601, 138, 29); 
		btnNewButton.addActionListener(new feedListener()); 
		btnNewButton.setBorder(BorderFactory.createEmptyBorder()); 
		contentPane.add(btnNewButton); 
    } 
 
	public class feedListener implements ActionListener { 
 
		@Override 
		public void actionPerformed(ActionEvent e) { 
			backendController.addFeedBack(textField_1.getText(),textField.getText(),textField_6.getText(), textField_2.getText(), textField_3.getText(),textField_4.getText(),textField_5.getText()); 
			//after feedback submited app goes back to main page 
			controller.changeFrame(0); 
		} 
    }
}