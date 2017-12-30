
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
     private double minMag;
     private double maxMag;
     private String Name;
    public MagnitudeFilter(double min, double max,String nm){
         minMag = min;
         maxMag = max;
         Name = nm;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude()<=maxMag && qe.getMagnitude()>=minMag;
    }
    
    public String getName(){
        return Name;
    }

}
