
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location currLoc;
    private double maxDist;
    private String Name;
    
    public DistanceFilter(Location loc, double dist,String nm){
        currLoc = loc;
        maxDist = dist;
        Name = nm;
    }
    
    public boolean satisfies(QuakeEntry qe){
        double distTo = currLoc.distanceTo(qe.getLocation());
        return distTo < maxDist;
    }
    
    public String getName(){
        return Name;
    }

}
