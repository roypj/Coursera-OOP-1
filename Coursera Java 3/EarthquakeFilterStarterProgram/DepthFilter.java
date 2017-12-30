
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    private double minDepth;
    private double maxDepth;
    private String Name;
    
    public DepthFilter(double min, double max,String nm){
        minDepth = min;
        maxDepth = max;
        Name = nm;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth()>=minDepth && qe.getDepth()<=maxDepth;
    }
    
    public String getName(){
        return Name;
    }

}
