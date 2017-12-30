
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int MinMin;
    private int MaxMin;
    
    public MinutesFilter(int minMin, int maxMin){
        MinMin = minMin;
        MaxMin = maxMin;
    }
    @Override
    public boolean satisfies(String id) {
	    int currDur = MovieDatabase.getMinutes(id);
		return (currDur >=MinMin) && (currDur <=MaxMin);
	}
    

}