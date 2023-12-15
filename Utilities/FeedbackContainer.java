package Utilities;

import java.util.ArrayList;

public class FeedbackContainer {
    private ArrayList<Feedback> feedBackList;
    public FeedbackContainer( ArrayList<Feedback> feedBackList){
        this.feedBackList = feedBackList;
    }
    public ArrayList<Feedback> getFeedBackList(){
        return this.feedBackList;
    }
    public void addFeedBack(Feedback fb){
        this.feedBackList.add(fb);
    }
}
