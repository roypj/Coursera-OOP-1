
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
        //this("ratedmovies_short.csv", "ratings_short.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        FirstRatings fr = new FirstRatings();
        ArrayList<String> uniqueList = fr.numMoviesRated(myRaters);
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for(int i = 0 ; i<uniqueList.size();i++){
            String movie = uniqueList.get(i);
            double avgRating = getAverageByID(movie,minimalRaters);
            if(avgRating >0){
                Rating currRating = new Rating(movie, avgRating);
                avgRatings.add(currRating);
            }
        }
        return avgRatings;
    }
    
    public String getTitle(String id){
        for( int i = 0 ; i <myMovies.size();i++){
            Movie currMovie = myMovies.get(i);
            if(currMovie.getID().equals((id))){
                return currMovie.getTitle();
            }
        }
        return "Title for movie "+ id+" not found.";
    }
    
    public String getID(String title){
        for(int i = 0 ; i<myMovies.size();i++){
            Movie currMovie = myMovies.get(i);
            if(currMovie.getTitle().equals(title)){
                return currMovie.getID();
            }
        }
        return "ID for the movie "+title+" not found.";
    }

}
