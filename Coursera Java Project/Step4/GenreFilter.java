
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter {
    private String myGenre;
    
    public GenreFilter(String genre){
        myGenre = genre.toLowerCase();
    }
    
    @Override
	public boolean satisfies(String id) {
	    String currGenre = MovieDatabase.getGenres(id).toLowerCase();
		if(currGenre.indexOf(myGenre)!=-1){
		    return true;
		}
		return false;
	}

}
