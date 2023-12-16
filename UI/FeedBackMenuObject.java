package UI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utilities.Feedback;

public class FeedBackMenuObject extends JPanel{
    protected Color backGroundpink; //backgroud color
    public FeedBackMenuObject(Feedback feedback,ActionListener action ){
        backGroundpink=Color.decode("#dd96b8"); //color adjusment
        JLabel mapName = new JLabel(feedback.getMap());
        mapName.setBackground(backGroundpink);
        mapName.setOpaque(true);
        mapName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel buildingName = new JLabel(feedback.getBuilding());
        buildingName.setBackground(backGroundpink);
        buildingName.setOpaque(true);
        buildingName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel floorName = new JLabel(feedback.getFloor());
        floorName.setBackground(backGroundpink);
        floorName.setOpaque(true);
        floorName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JButton seeFeedback = new JButton("See Feedback");
        seeFeedback.setBackground(Color.BLACK);
        seeFeedback.setForeground(Color.WHITE);
        seeFeedback.setOpaque(true);
        seeFeedback.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        seeFeedback.addActionListener(action);

        this.setLayout(new GridLayout(1, 4));
        this.add(mapName);
        this.add(buildingName);
        this.add(floorName);
        this.add(seeFeedback);
        this.setVisible(true);
    }
}
