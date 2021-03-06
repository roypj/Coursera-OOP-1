
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    
    public int compare(QuakeEntry q1 , QuakeEntry q2){
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();
        String lw1 = title1.substring(title1.lastIndexOf(" "));
        String lw2 = title2.substring(title2.lastIndexOf(" "));
        if(lw1.compareTo(lw2)<0){
            return -1;
        }
        if(lw1.compareTo(lw2)>0){
            return 1;
        }
        if(lw1.compareTo(lw2)==0){
            if(q1.getMagnitude()<q2.getMagnitude()){
                return -1;
            }
            if(q1.getMagnitude()>q2.getMagnitude()){
                return 1;
            }            
           return 0;
         }
        return 0;
    }

}
