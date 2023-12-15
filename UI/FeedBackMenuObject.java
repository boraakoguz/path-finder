package UI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utilities.Feedback;

public class FeedBackMenuObject extends JPanel{
    public FeedBackMenuObject(Feedback feedback){
        JLabel mapName = new JLabel(feedback.getMap());
        JLabel buildingName = new JLabel(feedback.getBuilding());
        JLabel floorName = new JLabel(feedback.getFloor());
        JButton seeFeedback = new JButton("See Feedback");
        this.setLayout(new GridLayout(1, 4));
        this.add(mapName);
        this.add(buildingName);
        this.add(floorName);
        this.add(seeFeedback);
        this.setVisible(true);
    }
}
