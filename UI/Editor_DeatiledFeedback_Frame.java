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

import UI.Editor_Welcome_Page.editorListener;
import UI.User_Frame.ButAction;
import Utilities.Feedback;

public class Editor_DeatiledFeedback_Frame extends JFrame {
    Path_Finder_Frame controller;
    protected Color backGroundpink; //backgroud color
    protected JPanel contentPane=new JPanel();
    protected Feedback feed;
	
    public Editor_DeatiledFeedback_Frame(Path_Finder_Frame c,Feedback f){
        controller=c;
		feed=f;
        backGroundpink=Color.decode("#dd96b8"); //color adjusment
        this.setTitle("Path Finder");
		setSize(1200,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
        setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(backGroundpink);
		JLabel lblNewLabel = new JLabel("FeedBack");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(150, 40, 488, 73);
		contentPane.add(lblNewLabel);
		
        ImageIcon image=new ImageIcon("image (8).png");
        JLabel imageLab=new JLabel();
        imageLab.setIcon(image);
        imageLab.setBounds(930, 10, 40, 40);
        contentPane.add(imageLab);

        
        ImageIcon image4=new ImageIcon("image (7).png");
        JLabel imageLab4=new JLabel();
        imageLab4.setIcon(image4);
        imageLab4.setBounds(90, 50, 50, 50);
        contentPane.add(imageLab4);

		JLabel lblNewLabel_3 = new JLabel("Editor Account");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(981, 10, 195, 46);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Name X");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 172, 170, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Location: ");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(10, 329, 170, 27);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Sender: ");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_1_2.setBounds(10, 135, 170, 27);
		contentPane.add(lblNewLabel_1_2);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
		btnNewButton.setBounds(659, 587, 170, 46);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setOpaque(true);
		btnNewButton.setBorderPainted(false);
        btnNewButton.addActionListener(new editorFeedListener("delete"));
        btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(btnNewButton);

		
		JLabel lblNewLabel_2 = new JLabel(feed.getFeedBack());
		lblNewLabel_2.setBounds(335, 169, 786, 408);
		contentPane.add(lblNewLabel_2);

        JButton backBut=new JButton();
        backBut.addActionListener(new editorFeedListener("back1"));
        backBut.setBounds(20, 20, 50, 45);
        backBut.setFocusable(false);
        backBut.setBackground(backGroundpink);
        backBut.setIcon(new ImageIcon("image (11).png"));
        backBut.setBorder(BorderFactory.createEmptyBorder()); //setting borders of buttons 
        contentPane.add(backBut);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("E-Mail: ");
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_1_2_1.setBounds(10, 212, 170, 27);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Mail X");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(10, 249, 170, 27);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Map: "+feed.getMap());
		lblNewLabel_1_3_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(10, 382, 170, 27);
		contentPane.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Building: "+feed.getBuilding());
		lblNewLabel_1_3_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_3_2.setBounds(10, 419, 170, 27);
		contentPane.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Floor: "+feed.getFloor());
		lblNewLabel_1_3_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_3_3.setBounds(10, 459, 170, 27);
		contentPane.add(lblNewLabel_1_3_3);
		
		JLabel lblNewLabel_1_3_4 = new JLabel("Room: X");
		lblNewLabel_1_3_4.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_3_4.setBounds(10, 494, 170, 27);
		contentPane.add(lblNewLabel_1_3_4);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Feedback:");
		lblNewLabel_1_2_2.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_1_2_2.setBounds(335, 135, 170, 27);
		contentPane.add(lblNewLabel_1_2_2);
    }
    public class editorFeedListener implements ActionListener {
        String butType;
        public editorFeedListener(String s){
            butType=s;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(butType.equals("back1")){
                controller.changeFrame(8);
                //if user clicks to back button the app will be directed to editor seefeedback page
            }
            if(butType.equals("delete")){
                //Deleting the feedback from database should be added here
                controller.changeFrame(8);
            }
        }
    }
}
