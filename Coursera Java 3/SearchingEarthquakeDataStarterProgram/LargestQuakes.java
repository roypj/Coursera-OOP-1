
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes {
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int maxIdx = 0;
        double maxMag = 0;
        for(int i = 0;i < quakeData.size();i++){
            double currMag = quakeData.get(i).getMagnitude();
            if(currMag > maxMag){
                maxIdx = i;
                maxMag = currMag;
            }            
        }
        return maxIdx;
    } 
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        int maxMagIdx = 0;
        for( int i = 0; i<howMany;i++){
            maxMagIdx = indexOfLargest(copy);
            ret.add(copy.get(maxMagIdx));
            copy.remove(maxMagIdx);
        }
        return ret;
    }
    
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("The number of quakes read are : "+list.size());
        /*for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        int maxIdx = indexOfLargest(list);
        System.out.println("The max Index is :"+maxIdx);
        QuakeEntry maxMag = list.get(maxIdx);
        System.out.println(maxMag);*/
        ArrayList<QuakeEntry> ret = getLargest(list,50);
        for(int i = 0;i<ret.size();i++){
            System.out.println(ret.get(i));
        }
        
    }

}
