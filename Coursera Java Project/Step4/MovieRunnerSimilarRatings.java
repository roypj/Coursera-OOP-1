
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    
     public void printAverageRatings(){
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("ratedmovies_short.csv");
        RaterDatabase rdb = new RaterDatabase();
        rdb.initialize("ratings_short.csv");
        System.out.println("The number of movies " +mdb.size());
        System.out.println("The number of Raters " +rdb.size());
        int minRaters = 1;
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> avgRating = fr.getAverageRatings(minRaters);
        System.out.println("The number of movies rated by min of "+minRaters+" raters : "+avgRating.size());
        Collections.sort(avgRating);
        for(int i = 0 ; i <avgRating.size();i++){
            Rating currRating = avgRating.get(i);
            String title = currRating.getItem();
            System.out.println(currRating.getValue()+ " " +mdb.getTitle(title));
        }
       
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("ratedmovies_short.csv");
        RaterDatabase rdb = new RaterDatabase();
        rdb.initialize("ratings_short.csv");
        System.out.println("The number of movies " +mdb.size());
        System.out.println("The number of Raters " +rdb.size());
        int minRaters = 1;
        int year = 1990;
        String genre = "Drama";
        AllFilters af = new AllFilters();
       Filter  yr = new YearAfterFilter(year);
       Filter  genF = new GenreFilter(genre);
       af.addFilter(yr);
       af.addFilter(genF);
       FourthRatings fr = new FourthRatings();
        ArrayList<Rating> avgRating = fr.getAverageRatingsByFilter(minRaters,af);
        System.out.println("The number of movies rated by min of "+minRaters+" raters : "+avgRating.size());
        Collections.sort(avgRating);
        for(int i = 0 ; i <avgRating.size();i++){
            Rating currRating = avgRating.get(i);
            String title = currRating.getItem();
            System.out.println(currRating.getValue()+ " "+mdb.getTitle(title)+" "+mdb.getDirector(title)+" "+mdb.getGenres(title)+" "+mdb.getYear(title));
        }
    }
    
    public void printSimilarRatings(){
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("ratedmoviesfull.csv");
        RaterDatabase rdb = new RaterDatabase();
        rdb.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> similarRatings = fr.getSimilarRatings("71", 20, 5);
        System.out.println("The size of the similar ratings "+similarRatings.size());
        int maxIter = java.lang.Math.min(5,similarRatings.size());
        for(int i = 0; i<maxIter;i++){
            System.out.println(MovieDatabase.getTitle(similarRatings.get(i).getItem())+"---"+similarRatings.get(i).getValue());
        }
        System.out.println("---------------------------------------------------------------------");
           
    }
    
    public void printSimilarRatingsByGenre(){
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("ratedmoviesfull.csv");
        RaterDatabase rdb = new RaterDatabase();
        rdb.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        String genre = "Mystery";
        Filter  genF = new GenreFilter(genre);
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("964", 20, 5,genF);
        System.out.println("The size of the similar ratings "+similarRatings.size());
        int maxIter = java.lang.Math.min(5,similarRatings.size());
        for(int i = 0; i<maxIter;i++){
            System.out.println(MovieDatabase.getTitle(similarRatings.get(i).getItem())+"---"+similarRatings.get(i).getValue());
        }
        System.out.println("---------------------------------------------------------------------");
    }
    
    public void printSimilarRatingsByDirector(){
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("ratedmoviesfull.csv");
        RaterDatabase rdb = new RaterDatabase();
        rdb.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        String director = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        Filter  dirF = new DirectorsFilter(director);
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("120", 10, 2,dirF);
        System.out.println("The size of the similar ratings "+similarRatings.size());
        int maxIter = java.lang.Math.min(5,similarRatings.size());
        for(int i = 0; i<maxIter;i++){
            System.out.println(MovieDatabase.getTitle(similarRatings.get(i).getItem())+"---"+similarRatings.get(i).getValue());
        }
        System.out.println("---------------------------------------------------------------------");
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("ratedmoviesfull.csv");
        RaterDatabase rdb = new RaterDatabase();
        rdb.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        int minMin = 80;
        int maxMin = 160;
        String genre = "Drama";
        AllFilters af = new AllFilters();
       Filter  min = new MinutesFilter(minMin,maxMin);
       Filter  genF = new GenreFilter(genre);
       af.addFilter(min);
       af.addFilter(genF);
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("168",10,3,af);
        System.out.println("The size of the similar ratings "+similarRatings.size());
        int maxIter = java.lang.Math.min(5,similarRatings.size());
        for(int i = 0; i<maxIter;i++){
            System.out.println(MovieDatabase.getTitle(similarRatings.get(i).getItem())+"---"+similarRatings.get(i).getValue());
        }
        System.out.println("---------------------------------------------------------------------");
    }
    public void printSimilarRatingsByYearAfterAndMinutes(){
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("ratedmoviesfull.csv");
        RaterDatabase rdb = new RaterDatabase();
        rdb.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        int minMin = 70;
        int maxMin = 200;
        int year = 1975;
       AllFilters af = new AllFilters();
       Filter  min = new MinutesFilter(minMin,maxMin);
       Filter  yr = new YearAfterFilter(year);
       af.addFilter(min);
       af.addFilter(yr);
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("314",10,5,af);
        System.out.println("The size of the similar ratings "+similarRatings.size());
        int maxIter = java.lang.Math.min(5,similarRatings.size());
        for(int i = 0; i<maxIter;i++){
            System.out.println(MovieDatabase.getTitle(similarRatings.get(i).getItem())+"---"+similarRatings.get(i).getValue());
        }
        System.out.println("---------------------------------------------------------------------");
    }
    
    

}
