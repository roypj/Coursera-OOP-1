
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int maxDepthIdx = from;
        for(int i=from+1;i<quakes.size();i++){
            if(quakes.get(i).getDepth()>quakes.get(maxDepthIdx).getDepth()){
                maxDepthIdx = i;
            }
            
        }
        return maxDepthIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
           if(!checkInSortedOrder(in)){
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }else{
            System.out.println("The number of passes is : "+i);
            break;
        }
        }
        
    }
    
    public void sortBylargestDepth(ArrayList<QuakeEntry> quakes){
        for(int k = 0; k<70;k++){
            int maxIdx = getLargestDepth(quakes,k);
            QuakeEntry temp = quakes.get(maxIdx);
            quakes.set(maxIdx,quakes.get(k));
            quakes.set(k,temp);
           }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakes,int numSorted){
        for(int i = 1 ; i<quakes.size();i++){
            if(quakes.get(i).getMagnitude() < quakes.get(i-1).getMagnitude()){
                QuakeEntry temp = quakes.get(i);
                quakes.set(i,quakes.get(i-1));
                quakes.set(i-1,temp);
            }            
        }
        System.out.println("The pass completed is : "+numSorted);
        //for(QuakeEntry qe : quakes){
           // System.out.println(qe);
        //}
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        //for(QuakeEntry qe : in){
            //System.out.println(qe);
       // }
        for(int i=0;i<in.size()-1;i++){
            if(!checkInSortedOrder(in)){
                onePassBubbleSort(in,i);
            }else{
                System.out.println("The number of passes is : "+i);
                break;
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> in){
        boolean sorted = true;
        for(int i=0;i<in.size()-1;i++){
            if(in.get(i).getMagnitude() > in.get(i+1).getMagnitude()){
              sorted = false;
              break;
            }                        
        }
        return sorted;        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthQuakeDataWeekDec6sample1.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortBylargestDepth(list);
        sortByMagnitudeWithBubbleSort(list);
        for (QuakeEntry qe: list) { 
           // System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}