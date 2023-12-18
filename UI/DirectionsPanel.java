package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Building.Space;

public class DirectionsPanel extends JPanel {
    final int ZOOMIN = 4;
    final int XCORRECTION = 20;
    final int YCORRECTION = 100;
    JButton nextStep = new JButton("Next");
    JButton previousStep = new JButton("Previous");
    Controller backendController;
    ArrayList<Space> enterDirections;
    ArrayList<Space> exitDirections;
    ArrayList<Space> directions;
    int stepIndex = 0;
    int maxIndex = 0;
    int changeDirectionTypeIndex;
    boolean exitDirectionType;
    Space currentStep;

    public DirectionsPanel(Controller backendController){
        this.backendController = backendController;
        this.setBackground(Color.WHITE);
        this.nextStep.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(directions != null){
                    stepIndex++;
                    if(stepIndex >= maxIndex){
                        stepIndex = maxIndex-1;
                    }
                    else if(stepIndex >= changeDirectionTypeIndex){
                        exitDirectionType = false;
                    }
                    else if(stepIndex < changeDirectionTypeIndex){
                        exitDirectionType = true;
                    }
                    currentStep = directions.get(stepIndex);
                }  
                repaint();     
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
                    else if(stepIndex >= changeDirectionTypeIndex){
                        exitDirectionType = false;
                    }
                    else if(stepIndex < changeDirectionTypeIndex){
                        exitDirectionType = true;
                    }
                    currentStep = directions.get(stepIndex);
                }
                repaint();
            }
            
        });
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.add(previousStep);
        buttonsPanel.add(nextStep);
        this.setLayout(new BorderLayout());
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.setSize(700,700);
        this.setVisible(false);
    }
    public void setDirections(Space start, Space target){
        this.enterDirections = this.backendController.getEnterDirections(start, target);
        this.exitDirections = this.backendController.getExitDirections(start, target);
        this.directions = new ArrayList<Space>();
        this.directions.addAll(exitDirections);
        this.directions.addAll(enterDirections);
        this.stepIndex = 0;
        this.changeDirectionTypeIndex = exitDirections.size();
        this.maxIndex = this.directions.size();
        this.currentStep = this.directions.get(0);
        this.exitDirectionType = true;
        repaint();
    }

    public void setVisibility(Boolean vis){
        this.setVisible(vis);
        repaint();
    }
    public int zoomIn(int data){
        return data*ZOOMIN;
    }
    public int getMean(int data, int offset){
        return (data+data+offset)/2;
    }
    public Polygon drawLineBetweenEntrances(int startX, int startY, int targetX, int targetY){
        ArrayList<Integer> avoidedXPoints = new ArrayList<Integer>();
        ArrayList<Integer> avoidedYPoints = new ArrayList<Integer>();
        ArrayList<Integer> avoidedHeights = new ArrayList<Integer>();
        ArrayList<Integer> avoidedWidths = new ArrayList<Integer>();
        for (Space space : currentStep.getContents()) {
            
        }
        return null;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Space space : currentStep.getContents()){
            if(directions.contains(space)){
                if(exitDirectionType){
                    g.setColor(Color.RED);
                }
                else{
                    g.setColor(Color.GREEN);
                }
                g.fillOval(zoomIn(space.getEntranceX())+XCORRECTION, zoomIn(space.getEntranceY())+YCORRECTION, 10, 10);
            }
            
            g.setColor(space.getColor());
            g.drawRect(zoomIn(space.getX())+XCORRECTION,zoomIn(space.getY())+YCORRECTION, zoomIn(space.getWidth()), zoomIn(space.getHeight())); 
            g.drawString(space.getName(), getMean(zoomIn(space.getX())+XCORRECTION, space.getWidth()), getMean(zoomIn(space.getY())+YCORRECTION, space.getY()));
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Step: " + (stepIndex+1)  + "/" + maxIndex, 10, 30);  
        g.drawString("Go to " + currentStep, 350, 650);  
    }
}
