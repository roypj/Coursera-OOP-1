
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> mtchAll;
    private StringBuilder Names;
    
    public MatchAllFilter(){
        mtchAll = new ArrayList<Filter>();
        Names = new StringBuilder();
    }
    
    public void addFilter(Filter f){
        mtchAll.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : mtchAll){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName(){
       for(Filter f : mtchAll){
           Names.append(f.getName()+" ");
        } 
        return Names.toString();
    }

}
