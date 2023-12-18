package UI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Building.Space;

public class DirectionsPanel extends JPanel {

    JButton nextStep = new JButton("Next");
    JButton previousStep = new JButton("Previous");
    Controller backendController;
    ArrayList<Space> directions;
    int stepIndex = 0;

    public DirectionsPanel(Controller backendController){
        this.backendController = backendController;
        this.nextStep.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(directions != null){
                    stepIndex++;
                    if(stepIndex >= directions.size()){
                        stepIndex = directions.size()-1;
                    }
                }       
            }  
        });
        this.previousStep.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(directions != null){
                    stepIndex--;
                    if(stepIndex<0){
                        stepIndex = 0;
                    }
                }
            }
            
        });

        this.setLayout(new BorderLayout());
        this.add(previousStep, BorderLayout.SOUTH);
        this.add(nextStep, BorderLayout.SOUTH);
        this.setSize(700,700);
        this.setVisible(false);
    }
    public void setDirections(Space start, Space target){
        this.directions = this.backendController.getDirections(start, target);
        this.stepIndex = 0;
        repaint();
    }

    public void setVisibility(Boolean vis){
        this.setVisible(vis);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("paint");
        super.paintComponent(g);
        Space  test = this.directions.get(0);
        g.setColor(test.getColor());
        g.fillRect(test.getX(),test.getY(), test.getWidth(), test.getHeight());

    }
}
