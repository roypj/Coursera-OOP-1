
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
        //this("ratedmovies_short.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    

    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String movie, int minimalRaters){
        double rating = 0;
        int numRaters = 0;
        for (int k = 0 ; k<myRaters.size() ; k++){
            Rater currRater = myRaters.get(k);
            double currRating = currRater.getRating(movie);
            if(currRating!=-1){
                rating+=currRating;
                numRaters+=1;
            }
        }
        if(numRaters>=minimalRaters){
            return rating/numRaters;
        }
        return 0.0;
    }
    
    public ArrayList<Rating>  getAverageRatings(int minimalRaters){
        ArrayList<String> movieList = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for(int i = 0 ; i<movieList.size();i++){
            String movie = movieList.get(i);
            double avgRating = getAverageByID(movie,minimalRaters);
            if(avgRating >0){
                Rating currRating = new Rating(movie, avgRating);
                avgRatings.add(currRating);
            }
        }
        return avgRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<String> movieList = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for(int i = 0 ; i<movieList.size();i++){
            String movie = movieList.get(i);
            double avgRating = getAverageByID(movie,minimalRaters);
            if(avgRating >0){
                Rating currRating = new Rating(movie, avgRating);
                avgRatings.add(currRating);
            }
        }
        return avgRatings;
    }
}
