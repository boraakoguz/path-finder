package Building;

import Utilities.FeedbackContainer;

public class Map extends Space{
    FeedbackContainer feedbackContainer;
    public Map(String name){
        super(name);
    }
    public void addFeedBackContainer(FeedbackContainer container){
        this.feedbackContainer = container;
    }
    public FeedbackContainer getFeedbackContainer(){
        return this.feedbackContainer;
    }
    
    public String getCustomString() {
        return name + " Map";
    }
}
