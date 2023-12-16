package UI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utilities.User;

public class UserMenuObject extends JPanel {
    protected Color backGroundpink; //backgroud color
    public UserMenuObject(User user,ActionListener action){
        backGroundpink=Color.decode("#dd96b8"); //color adjusment
        JLabel userName = new JLabel(user.getuserName());
        userName.setBackground(backGroundpink);
        userName.setOpaque(true);
        userName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel userLevel = new JLabel(String.valueOf(user.getuserLevel()));
        userLevel.setBackground(backGroundpink);
        userLevel.setOpaque(true);
        userLevel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JButton removeUser = new JButton("Remove");
        removeUser.setBackground(Color.BLACK);
        removeUser.setForeground(Color.WHITE);
        removeUser.setOpaque(true);
        removeUser.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        removeUser.addActionListener(action);
        this.setLayout(new GridLayout(1, 3));
        this.add(userName);
        this.add(userLevel);
        this.add(removeUser);
        this.setVisible(true);
    }
}
