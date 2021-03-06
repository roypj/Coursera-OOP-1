
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String FileName){
        FileResource movieData = new FileResource("data/"+FileName);
        CSVParser parser = movieData.getCSVParser();
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        
        for(CSVRecord record : parser){
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster = record.get("poster");
            int minutes = Integer.parseInt(record.get("minutes"));
            Movie mov = new Movie(id,title,year,genres,director,country, poster,minutes);
            movieList.add(mov);
        }
        
        return movieList;
    }
    
    private int isRaterPresent(ArrayList<Rater> raterList,String id){
        if(raterList.size()==0){
            return -1;
        }else{
            for(int i = 0;i<raterList.size();i++){
                if(raterList.get(i).getID().equals(id)){
                    return i;
                }                
            }
        }
        return -1;
    }
    
    public ArrayList<Rater> loadRaters(String FileName){
        FileResource raterData = new FileResource("data/"+FileName);
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        CSVParser parser = raterData.getCSVParser();
        for(CSVRecord rec : parser){
            String id = rec.get("rater_id");
            //System.out.println(id);
            String item = rec.get("movie_id").toString();
            //System.out.println(item);
            double rating = Double.parseDouble(rec.get("rating"));
            int idx = isRaterPresent(raterList,id);
            if(idx==-1){
                Rater currRater = new EfficientRater(id);
                currRater.addRating(item,rating);
                raterList.add(currRater);
            }else{  
                 Rater foundRater = raterList.get(idx);
                 foundRater.addRating(item,rating);
                 raterList.set(idx,foundRater);                  
            }
        }
        return raterList;
    }
    
    public ArrayList<Movie> findMoviesOfGenre(String Genre,ArrayList<Movie> movieList){
        ArrayList<Movie> genreMovies = new ArrayList<Movie>();
        for(Movie mov : movieList){
            String genre = mov.getGenres().toLowerCase();
            if(genre.indexOf(Genre.toLowerCase())!=-1){
                genreMovies.add(mov);
            }
        }
        return genreMovies;
    }
    
    public ArrayList<Movie> findMoviesOfDuration(int duration, ArrayList<Movie> movieList){
        ArrayList<Movie> durationMovies = new ArrayList<Movie>();
        for(Movie mov : movieList){
            int dur = mov.getMinutes();
            if(dur>duration){
                durationMovies.add(mov);
            }
        }
        return durationMovies;
    }
    
    public HashMap<String,Integer> DirectorsMax(ArrayList<Movie> movieList){
        HashMap<String,Integer> directorsCnt = new HashMap<String,Integer>();
        HashMap<String,Integer> directorsMax = new HashMap<String,Integer>();
        int maxCount = 0;
        for(Movie mov : movieList){
            String[] directors = mov.getDirector().split(",");
            for(int i = 0;i<directors.length;i++){
                if(!directorsCnt.containsKey(directors[i].trim())){
                    directorsCnt.put(directors[i].trim(),1);
                }
                else{
                    int count = directorsCnt.get(directors[i].trim());
                    directorsCnt.put(directors[i].trim(),count+1);
                }
            }            
        }
        for(String dir : directorsCnt.keySet()){
            int count = directorsCnt.get(dir);
            if(count>=maxCount){
                maxCount = count;
            }
        }
        System.out.println("The max num movies by a director is "+maxCount);
        for(String dir : directorsCnt.keySet()){
            int count = directorsCnt.get(dir);
            if(count==maxCount){
               directorsMax.put(dir, count);
            }
        }
        
        return directorsMax;
    }
    
    public double numRatingsByRater(ArrayList<Rater> raterList, String id){
        int idx = isRaterPresent(raterList,id);
        if(idx!=-1){
         Rater currRater = raterList.get(idx);
         return currRater.numRatings();
        }
        return 0;
    }
    
    public HashMap<String,Integer> RatersMax(ArrayList<Rater> raterList){
        HashMap<String, Integer> raterMap = new HashMap<String,Integer>();
        HashMap<String, Integer> raterMax = new HashMap<String,Integer>();
        for(int i = 0 ; i<raterList.size();i++){
            Rater currRater = raterList.get(i);
            raterMap.put(currRater.getID(),currRater.numRatings());
        }
        int maxCount = 0;
        for(String key : raterMap.keySet()){
            int currCount = raterMap.get(key);
            if(currCount > maxCount){
                maxCount = currCount;
            }
        }
        System.out.println("The maximum number of ratings by a user is "+maxCount);
        for(String key : raterMap.keySet()){
            int count = raterMap.get(key);
            if(count == maxCount){
                raterMax.put(key,count);
            }
        }
        return raterMax;
    } 
    
    public int numRatingsForMovie(ArrayList<Rater> raterList,String movie){
        int count = 0;
        for(int i = 0 ; i <raterList.size(); i++){
            Rater currRater = raterList.get(i);
            if(currRater.hasRating(movie)){
                count+=1;
            }
        }
        return count;
    }
    
    public ArrayList<String> numMoviesRated(ArrayList<Rater> raterList){
        ArrayList<String> movieList = new ArrayList<String>();
        for(int i = 0; i < raterList.size(); i++){
            Rater currRater = raterList.get(i);
            ArrayList<String> currList = currRater.getItemsRated();
            for(int k = 0 ; k<currList.size(); k++){
                String currMov = currList.get(k);
                if(!movieList.contains(currMov)){
                    movieList.add(currMov);
                }
            }
        }
        return movieList;
    }
    
    public void testLoadMovies(){
       ArrayList<Movie> testList = loadMovies("ratedmovies_short.csv");
        //ArrayList<Movie> testList = loadMovies("ratedmoviesfull.csv");
        for(Movie mov : testList){
            System.out.println(mov);
        }
        /*System.out.println(testList.size());
        ArrayList<Movie> genreList = findMoviesOfGenre("Comedy",testList);
        System.out.println("The movies of the genre are"+genreList.size());
        ArrayList<Movie> durationMovies = findMoviesOfDuration(150,testList);
         System.out.println("The longer than 150 mins"+durationMovies.size());
        HashMap<String,Integer> directorsMax = DirectorsMax(testList);
        System.out.println(directorsMax.size());*/
       /* for(Movie mov : durationMovies){
            System.out.println(mov);
        }*/
       /*for(String key : directorsMax.keySet()){
            System.out.println(key+" "+directorsMax.get(key));
            System.out.println("The num directors with max movie count is "+directorsMax.size());
        }*/
    }
    
    public void testLoadRaters(){
        ArrayList<Rater> testList = loadRaters("ratings_short.csv");
        //ArrayList<Rater> testList = loadRaters("ratings.csv");
        System.out.println("number of raters "+testList.size());
        for(int k = 0 ; k<testList.size();k++){
            Rater currRater = testList.get(k);
            System.out.println("ID = "+currRater.getID()+" num ratings "+currRater.numRatings());
            ArrayList<String> currMovies = currRater.getItemsRated();
            for(int i = 0;i<currMovies.size();i++){
                System.out.println("Movie "+currMovies.get(i)+" "+currRater.getRating(currMovies.get(i)));
            }
        }
        String id = "193";
        System.out.println("The number of ratings by rater "+id+" is "+numRatingsByRater(testList,id));
        HashMap<String,Integer> raterMax = RatersMax(testList);
        for(String key : raterMax.keySet()){
            System.out.println("Rater "+key+" num ratings "+ raterMax.get(key));
        }
        String movie = "1798709";
        System.out.println("The movie "+movie+" was rated by "+numRatingsForMovie(testList,movie)+" raters.");
        System.out.println("The total number of diff movies rated is "+numMoviesRated(testList));
    }

}
