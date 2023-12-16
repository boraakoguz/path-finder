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

public class Editor_Welcome_Page extends JFrame{
    protected Controller backendController;
    protected JPanel contentPane=new JPanel();
    protected Color backGroundpink; //backgroud color
    protected Path_Finder_Frame controller;
    protected JLabel lblNewLabel_1;
    protected String editorName;
    public Editor_Welcome_Page(Path_Finder_Frame c,Controller bc){
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
		JLabel lblNewLabel = new JLabel("Editor Account");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(60, 10, 353, 82);
		contentPane.add(lblNewLabel);

        ImageIcon image=new ImageIcon("image (8).png");
        JLabel imageLab=new JLabel();
        imageLab.setIcon(image);
        imageLab.setBounds(10, 30, 40, 40);
        contentPane.add(imageLab);

        ImageIcon image2=new ImageIcon("image (18).png");
        JLabel imageLab2=new JLabel();
        imageLab2.setIcon(image2);
        imageLab2.setBounds(20, 340, 50, 50);
        contentPane.add(imageLab2);

        ImageIcon image3=new ImageIcon("image (2).png");
        JLabel imageLab3=new JLabel();
        imageLab3.setIcon(image3);
        imageLab3.setBounds(20, 430, 50, 50);
        contentPane.add(imageLab3);
		
		JButton btnNewButton = new JButton("Map Tools");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 25));
		btnNewButton.setBounds(101, 342, 239, 47);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setOpaque(true);
		btnNewButton.setBorderPainted(false);
        btnNewButton.addActionListener(new editorListener("map"));
        btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(btnNewButton);
		
		JButton btnAddUser = new JButton("See Feedbacks");
		btnAddUser.setFont(new Font("Arial", Font.PLAIN, 25));
		btnAddUser.setBounds(101, 429, 239, 47);
        btnAddUser.setForeground(Color.WHITE);
        btnAddUser.setBackground(Color.BLACK);
        btnAddUser.setOpaque(true);
		btnAddUser.setBorderPainted(false);
        btnAddUser.addActionListener(new editorListener("feed"));
        btnAddUser.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(btnAddUser);
		
		lblNewLabel_1 = new JLabel("Welcome Editor " + this.editorName);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(440, 136, 353, 47);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("What Do You Want To Do ?");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(10, 261, 330, 52);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton_1.setBounds(1037, 584, 112, 39);
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setBackground(Color.BLACK);
        btnNewButton_1.setOpaque(true);
		btnNewButton_1.setBorderPainted(false);
        btnNewButton_1.addActionListener(new editorListener("out"));
        btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(btnNewButton_1);
    }
    public void setEditorName(String name){
        this.editorName = name;
        lblNewLabel_1.setText("Welcome Editor " + this.editorName);
    }
    public class editorListener implements ActionListener {
        String butType;
        public editorListener(String s){
            butType=s;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //admins will directed to each(the page names are same with buttype names. so, ı do not write which page will be directed in each if)
            if(butType.equals("map")){
                System.out.println("in progress");
                controller.changeFrame(9);
            }
            else if(butType.equals("feed")){
                controller.changeFrame(8);
            }
            else if(butType.equals("out")){
                controller.changeFrame(0);
            }
        }    
    }

}
