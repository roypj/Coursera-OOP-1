
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Double> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Double>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,rating);
    }

    public boolean hasRating(String item) {       
            if (myRatings.containsKey(item)){
                return true;
            }       
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {        
            if (myRatings.containsKey(item)){
                return myRatings.get(item);
            }        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String key : myRatings.keySet()){
            list.add(key);
        }        
        return list;
    }

}
