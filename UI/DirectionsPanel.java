package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Building.Building;
import Building.Floor;
import Building.Space;

public class DirectionsPanel extends JPanel {
    final double ZOOMIN = 3;
    final int XCORRECTION = 10;
    final int YCORRECTION = 10;
    final int  COLLISION_DETECTION_SIZE = 20;
    JButton nextStep = new JButton("Next");
    JButton previousStep = new JButton("Previous");
    Controller backendController;
    ArrayList<Space> enterDirections;
    ArrayList<Space> exitDirections;
    ArrayList<Space> directions;
    ArrayList<String> directionDescriptions;
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
        fillStringDirections();
        repaint();
    }
    public void fillStringDirections(){
        directionDescriptions = new ArrayList<String>();
        if(directionDescriptions != null){
            directionDescriptions.clear();
        }
        for(int i = 0; i<this.directions.size();i++){
            String description = "";
            if(i<changeDirectionTypeIndex){
                description = description + "Exit ";
            }
            else{
                description = description + "Enter ";
            }
            description = description + directions.get(i);
            directionDescriptions.add(description);
        }
    }

    public void setVisibility(Boolean vis){
        this.setVisible(vis);
        repaint();
    }
    public int zoomIn(double d){
        System.out.println("input:" + d + "output" + (int) (d*ZOOMIN));
        return (int) (d*ZOOMIN);
    }
    public int getMean(int data, int offset){
        return (data+data+offset)/2;
    }
    /* 
    public ArrayList<Polygon> drawLineBetweenEntrances(int startX, int startY, int targetX, int targetY){
        if(startX - targetX < 0){ // to the left

        }
        return null;
    }
    */
    public void drawLineBetweenEntrances(int startX, int startY, int targetX, int targetY, Graphics g){
        int dx = targetX - startX;
        int dy = targetY - startY;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - 10, xn = xm, ym = 10, yn = -10, x;
        double sin = dy / D, cos = dx / D;
    
        x = xm*cos - ym*sin + startX;
        ym = xm*sin + ym*cos + startY;
        xm = x;
    
        x = xn*cos - yn*sin + startX;
        yn = xn*sin + yn*cos + startY;
        xn = x;
    
        int[] xpoints = {targetX, (int) xm, (int) xn};
        int[] ypoints = {targetY, (int) ym, (int) yn};
        
        g.drawLine(startX, startY, targetX, targetY);
        g.fillPolygon(xpoints, ypoints, 3);
    }
    public boolean checkVertical(int x,int y,boolean isDown){
         int tempY = y;
        if(isDown){
            tempY = y;
            while(tempY>0){
                tempY = tempY - COLLISION_DETECTION_SIZE;
                if(checkCollision(x, y)){
                    return false;
                }
            }
        }
        else{
            while(tempY<700){
                tempY = tempY + COLLISION_DETECTION_SIZE;
                if(checkCollision(x, y)){
                    return false;
                }
            }
        }   
        return true;  
    }
    public boolean checkHorizontal(int x,int y,boolean isRight){
         int tempX = x;
        if(isRight){
            tempX = y;
            while(tempX>0){
                tempX = tempX - COLLISION_DETECTION_SIZE;
                if(checkCollision(x, y)){
                    return false;
                }
            }
        }
        else{
            while(tempX<700){
                tempX = tempX + COLLISION_DETECTION_SIZE;
                if(checkCollision(x, y)){
                    return false;
                }
            }
        }   
        return true;  
    }
    
    public boolean checkCollision(int x, int y){
        for(Space space : currentStep.getContents()){
            if(
                x > space.getX() &&
                y > space.getY() &&
                x < space.getX() + space.getWidth() &&
                y < space.getY() + space.getHeight())
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<Point> entrances = new ArrayList<Point>();
        
        g.setColor(currentStep.getColor());
        g.drawRect(zoomIn(currentStep.getX())+XCORRECTION,zoomIn(currentStep.getY())+YCORRECTION, zoomIn(currentStep.getWidth()), zoomIn(currentStep.getHeight())); 
        g.drawString(currentStep.getName(), getMean(zoomIn(currentStep.getX())+XCORRECTION, currentStep.getWidth()), getMean(zoomIn(currentStep.getY())+YCORRECTION, currentStep.getY()));
        if(!(currentStep instanceof Building)){
            if(currentStep instanceof Floor){
                Point downPoint = new Point(((Floor)currentStep).getDownStairX(),((Floor)currentStep).getDownStairY());
                Point upPoint = new Point(((Floor)currentStep).getUpStairX(),((Floor)currentStep).getUpStairY());
                entrances.add(downPoint);
                entrances.add(upPoint);
                g.setColor(Color.RED);
                g.fillOval(
                    zoomIn(downPoint.getX())+XCORRECTION,
                    zoomIn(downPoint.getY())+YCORRECTION,
                    10,
                    10);
                g.setColor(Color.YELLOW);
                g.fillOval(
                    zoomIn(upPoint.getX())+XCORRECTION,
                    zoomIn(upPoint.getY())+YCORRECTION,
                    10,
                    10);
            }
            for (Space space : currentStep.getContents()){
                if(directions.contains(space)){
                    entrances.add(new Point(space.getEntranceX(),space.getEntranceY()));
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
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Step: " + (stepIndex+1)  + "/" + maxIndex, 10, 30);  
        g.drawString(directionDescriptions.get(stepIndex), 350, 600);
        if(entrances.size()>= 2){
            drawLineBetweenEntrances(
                zoomIn(entrances.get(0).getX())+XCORRECTION,
                zoomIn(entrances.get(0).getY())+YCORRECTION,
                zoomIn(entrances.get(1).getX())+XCORRECTION,
                zoomIn(entrances.get(1).getY())+YCORRECTION,
                g);
        }  
    }
}
