package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import Building.Space;
import UI.User_Frame.fListener;
import UI.User_Frame.startListActionListener;
import UI.User_Frame.startSearch;

public class CFeedback_Frame extends JFrame {
    protected JLabel mapLab=new JLabel("Select Map");
    protected JComboBox<String> mapPickList = new JComboBox<String>();
    protected DefaultListModel<Space> startSearchListModel = new DefaultListModel<Space>();
    protected JList<Space> startSearchList = new JList<Space>(startSearchListModel);
    protected JTextField startSearchTextField;

    protected DefaultListModel<Space> targetSearchListModel = new DefaultListModel<Space>();
    protected JList<Space> targetSearchList = new JList<Space>(targetSearchListModel);
    protected JTextField targetSearchTextField;

    protected Space startLocation;
    protected Space targetLocation;
    protected JComboBox<Space> searchBar = new JComboBox<Space>();

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
		lblNewLabel.setBounds(75, 60, 374, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Please Enter Your Name");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(31, 129, 220, 21);
		contentPane.add(lblNewLabel_1);

		ImageIcon image=new ImageIcon("image (9).png");
        JLabel imageLab=new JLabel();
        imageLab.setIcon(image);
        imageLab.setBounds(20, 70, 40, 40);
        contentPane.add(imageLab);

		JButton backBut=new JButton();
		backBut.addActionListener(new feedListener("back1"));
        backBut.setBounds(20, 20, 50, 45);
        backBut.setFocusable(false);
        backBut.setBackground(backGroundpink);
        backBut.setIcon(new ImageIcon("image (11).png"));
        backBut.setBorder(BorderFactory.createEmptyBorder()); //setting borders of buttons 
        contentPane.add(backBut);

		textField = new JTextField();
		textField.setBounds(26, 160, 220, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Location");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(26, 358, 184, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Please Enter Your Mail");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(31, 207, 220, 21);
		contentPane.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(26, 238, 220, 37);
		contentPane.add(textField_1);
		
		
        
        
        mapLab.setBounds(20, 420, 100, 15);
        mapPickList.setBounds(20,440,250,30);
        mapPickList.addActionListener(new comboBoxActionListener());
        for (String map : backendController.getAvailableMaps()) {
            mapPickList.addItem(map);
        }

        JLabel searchBarLabel = new JLabel("Search where you are");
        searchBarLabel.setBounds(20,480,250,20);
        startSearchTextField = new JTextField();
        startSearchTextField.addKeyListener(new startSearch());
        startSearchTextField.addFocusListener(new fListener("start"));
        startSearchList.setBounds(20, 530, 250, 60);
        startSearchList.setVisible(false);
        startSearchList.addMouseListener(new startListActionListener());
        startSearchTextField.setBounds(20, 500, 250, 30);
        
        contentPane.add(searchBar);
        contentPane.add(mapLab);
        contentPane.add(startSearchTextField);
        contentPane.add(startSearchList);
        contentPane.add(mapPickList);
        contentPane.add(searchBarLabel);
		
		JLabel lblNewLabel_1_4 = new JLabel("Write Your Problem Here");
		lblNewLabel_1_4.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(436, 115, 220, 21);
		contentPane.add(lblNewLabel_1_4);
		
		textField_6 = new JTextArea();
		textField_6.setBounds(446, 169, 636, 411);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(727, 601, 138, 29);
		btnNewButton.addActionListener(new feedListener("submit"));
		btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(btnNewButton);
    }

	public class feedListener implements ActionListener {
		String butType;
		public feedListener(String s){
			butType=s;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(butType.equals("back1")){
				controller.changeFrame(0);
			}
			else if(butType.equals("submit")){
				backendController.addFeedBack(textField_1.getText(),textField.getText(),textField_6.getText(), textField_2.getText(), textField_3.getText(),textField_4.getText(),textField_5.getText());
				//after feedback submited app goes back to main page
				controller.changeFrame(0);
			}
			
		}
	}

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
