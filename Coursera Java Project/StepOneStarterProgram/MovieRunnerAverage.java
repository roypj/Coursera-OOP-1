
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings();
        System.out.println("The number of movies : "+sr.getMovieSize());
        System.out.println("The number of raters : "+sr.getRaterSize());
        ArrayList<Rating> avgRating = sr.getAverageRatings(12);
        System.out.println(avgRating.size());
        Collections.sort(avgRating);
        for(int i = 0 ; i <5;i++){
            Rating currRating = avgRating.get(i);
            String title = currRating.getItem();
            System.out.println(currRating.getValue()+ "  "+sr.getTitle(title));
        }
       
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings();
        String title = "Vacation";
        String ID = sr.getID(title);
        ArrayList<Rating> avgRating = sr.getAverageRatings(0);
        for(int i = 0 ; i <avgRating.size();i++){
            Rating currRating = avgRating.get(i);
            if(currRating.getItem().equals(ID)){
                System.out.println( title + " "+currRating.getValue());
            }
        }      
    
    }

}
