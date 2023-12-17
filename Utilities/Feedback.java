package Utilities;
/**
 * @author Mehmet Emre Şahin
 * The feedback class for the Path Finder app.
 */
public class Feedback {

    //String variable that includes feedback.
    private String mail;
    private String name;
    private String content;
    private String map;
    private String building; 
    private String floor;
    private String room;
    //The status of the feedback: 'false' for unhandled feedbacks, 'true' for done ones.
    private boolean status;
    public Feedback(String mail, String name, String content, String map, String building, String floor, String room, boolean status){
        this.mail = mail;
        this.name = name;
        this.content = content;
        this.map = map;
        this.building = building;
        this.floor = floor;
        this.room = room;
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
    public String getName(){ return this.name;}
    public String getMail(){ return this.mail;}
    public String getFeedBack() { return this.content; }
    public String getMap() { return this.map; }
    public String getBuilding() { return this.building; }
    public String getFloor() { return this.floor; }
    public String getRoom(){ return this.room; }

    public String toString(){
        return this.content + " Status: " + this.status;
    }
}
