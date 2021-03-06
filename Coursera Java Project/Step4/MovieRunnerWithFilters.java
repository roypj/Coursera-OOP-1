
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
       public void printAverageRatings(){
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("The number of raters "+tr.getRaterSize());
        MovieDatabase mdb = new MovieDatabase();
        //mdb.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies " +mdb.size());
        int minRaters = 35;
        ArrayList<Rating> avgRating = tr.getAverageRatings(minRaters);
        System.out.println("The number of movies rated by min of "+minRaters+" raters : "+avgRating.size());
        Collections.sort(avgRating);
        for(int i = 0 ; i <5;i++){
            Rating currRating = avgRating.get(i);
            String title = currRating.getItem();
            System.out.println(currRating.getValue()+ " " +mdb.getTitle(title));
        }
       
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("The number of raters "+tr.getRaterSize());
        MovieDatabase mdb = new MovieDatabase();
        //mdb.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies " +mdb.size());
        int minRaters = 20;
        int year = 2000;
        Filter  yr = new YearAfterFilter(year);
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minRaters,yr);
        System.out.println("The number of movies rated by min of "+minRaters+" raters : "+avgRating.size());
        Collections.sort(avgRating);
        for(int i = 0 ; i <5;i++){
            Rating currRating = avgRating.get(i);
            String title = currRating.getItem();
            System.out.println(currRating.getValue()+ " "+mdb.getYear(title)+" " +mdb.getTitle(title));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("The number of raters "+tr.getRaterSize());
        MovieDatabase mdb = new MovieDatabase();
        //mdb.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies " +mdb.size());
        int minRaters = 20;
        String genre = "Comedy";
        Filter  genF = new GenreFilter(genre);
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minRaters,genF);
        System.out.println("The number of movies rated by min of "+minRaters+" raters : "+avgRating.size());
        Collections.sort(avgRating);
        for(int i = 0 ; i <5;i++){
            Rating currRating = avgRating.get(i);
            String title = currRating.getItem();
            System.out.println(currRating.getValue()+ " "+mdb.getTitle(title)+" "+mdb.getGenres(title));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("The number of raters "+tr.getRaterSize());
        MovieDatabase mdb = new MovieDatabase();
        //mdb.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies " +mdb.size());
        int minRaters = 5;
        int minMin =105;
        int maxMin = 135;
        Filter  minF = new MinutesFilter(minMin,maxMin);
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minRaters,minF);
        System.out.println("The number of movies rated by min of "+minRaters+" raters : "+avgRating.size());
        Collections.sort(avgRating);
        for(int i = 0 ; i <5;i++){
            Rating currRating = avgRating.get(i);
            String title = currRating.getItem();
            System.out.println(currRating.getValue()+ " "+mdb.getTitle(title)+" "+mdb.getMinutes(title));
        }
    }
    
    public void printAverageRatingsByDirector(){
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("The number of raters "+tr.getRaterSize());
        MovieDatabase mdb = new MovieDatabase();
        //mdb.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies " +mdb.size());
        int minRaters = 4;
         
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        Filter  dirF = new DirectorsFilter(directors);
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minRaters,dirF);
        System.out.println("The number of movies rated by min of "+minRaters+" raters : "+avgRating.size());
        Collections.sort(avgRating);
        for(int i = 0 ; i <5;i++){
            Rating currRating = avgRating.get(i);
            String title = currRating.getItem();
            System.out.println(currRating.getValue()+ " "+mdb.getTitle(title)+" "+mdb.getDirector(title));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("The number of raters "+tr.getRaterSize());
        MovieDatabase mdb = new MovieDatabase();
        //mdb.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies " +mdb.size());
        int minRaters = 8;
        int year = 1990;
        String genre = "Drama";
        AllFilters af = new AllFilters();
       Filter  yr = new YearAfterFilter(year);
       Filter  genF = new GenreFilter(genre);
       af.addFilter(yr);
       af.addFilter(genF);
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minRaters,af);
        System.out.println("The number of movies rated by min of "+minRaters+" raters : "+avgRating.size());
        Collections.sort(avgRating);
        for(int i = 0 ; i <5;i++){
            Rating currRating = avgRating.get(i);
            String title = currRating.getItem();
            System.out.println(currRating.getValue()+ " "+mdb.getTitle(title)+" "+mdb.getDirector(title)+" "+mdb.getGenres(title)+" "+mdb.getYear(title));
        }
    }
    
    public void printAverageRatingsByDirectorAndMinutes(){
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("The number of raters "+tr.getRaterSize());
        MovieDatabase mdb = new MovieDatabase();
        //mdb.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies " +mdb.size());
        int minRaters = 3;         
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        Filter  dirF = new DirectorsFilter(directors);
        int minMin =90;
        int maxMin = 180;
        Filter  minF = new MinutesFilter(minMin,maxMin);
        AllFilters af = new AllFilters();
        af.addFilter(dirF);
        af.addFilter(minF);
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minRaters,af);
        System.out.println("The number of movies rated by min of "+minRaters+" raters : "+avgRating.size());
        Collections.sort(avgRating);
        for(int i = 0 ; i <5;i++){
            Rating currRating = avgRating.get(i);
            String title = currRating.getItem();
            System.out.println(currRating.getValue()+ " "+mdb.getTitle(title)+" "+mdb.getDirector(title)+" "+mdb.getGenres(title)+" "+mdb.getYear(title)+" "+mdb.getMinutes(title));
        }
    }
    
    public void testQTen(){
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("The number of raters "+tr.getRaterSize());
        MovieDatabase mdb = new MovieDatabase();
        //mdb.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies " +mdb.size());
        int minRaters = 3;         
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        Filter  dirF = new DirectorsFilter(directors);
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minRaters,dirF);
        /*int minMin =90;
        int maxMin = 180;
        Filter  minF = new MinutesFilter(minMin,maxMin);
        AllFilters af = new AllFilters();
        af.addFilter(dirF);
        af.addFilter(minF);
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minRaters,af);*/
        System.out.println("The number of movies rated by min of "+minRaters+" raters : "+avgRating.size());
        Collections.sort(avgRating);
        for(int i = 0 ; i <avgRating.size();i++){
            Rating currRating = avgRating.get(i);
            String title = currRating.getItem();
            System.out.println(currRating.getValue()+ " "+mdb.getTitle(title)+" "+mdb.getDirector(title)+" "+mdb.getMinutes(title));
        }
    }
    
}
