package Utilities;
/**
 * @author Mehmet Emre Şahin
 * The feedback class for the Path Finder app.
 */
public class Feedback {

    //String variable that includes feedback.
    private String content;
    private String map;
    private String building; 
    private String floor;
    //The status of the feedback: 'false' for unhandled feedbacks, 'true' for done ones.
    private boolean status;
    public Feedback(String content, String map, String building, String floor, boolean status){
        this.content = content;
        this.map = map;
        this.building = building;
        this.floor = floor;
        this.status = status;
    }
    //Getter and setter for feedback status.

    /**
     * Getter for feedback. Returns if the feedback is handled.
     * @return status of feedback.
     */
    public boolean getStatus() { return status; }

    /**
     * Setter for feedback status.
     * @param status of feedback.
     */
    public void setStatus( boolean status ) { this.status = status; }

    /**
     * Getter for the feedback message.
     * @param feedback message.
     */
    public String getFeedBack() { return this.content; }
    public String getMap() { return this.map; }
    public String getBuilding() { return this.building; }
    public String getFloor() { return this.floor; }

    public String toString(){
        return this.content + " Status: " + this.status;
    }
}
