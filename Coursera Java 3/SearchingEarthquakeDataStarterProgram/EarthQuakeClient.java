import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude()>magMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            Location currLoc = qe.getLocation();
            
            if(currLoc.distanceTo(from)/1000 < distMax){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            double depth = qe.getDepth();
            if(depth>minDepth && depth<maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where , String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            String info = qe.getInfo(); 
            if(where.toLowerCase().equals("start")){
                if(info.startsWith(phrase)){
                    answer.add(qe);
                }                
            }else if(where.toLowerCase().equals("end")){
                if(info.endsWith(phrase)){
                    answer.add(qe);
                }
            }else if(where.toLowerCase().equals("any")){
                int idx = info.indexOf(phrase);
                if(idx!=-1){
                    answer.add(qe);
                }
            }           
        }      
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double mag = 5.0;
        ArrayList<QuakeEntry> byMag = filterByMagnitude(list,mag);
        System.out.println("The number of quakes above magnitude "+mag+" are "+byMag.size());
        dumpCSV(byMag);
       

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> byDist = filterByDistanceFrom(list,1000,city);
        System.out.println("The number of quakes within a given distance of the city is " + byDist.size());
        dumpCSV(byDist);
        for(QuakeEntry qe : byDist){
            System.out.println(qe.getInfo()+" ---"+qe.getLocation().distanceTo(city));
        }
       }
       
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source =  "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> byDepth = filterByDepth(list,-4000,-2000);
        for(QuakeEntry qe : byDepth){
            System.out.println(qe);
        }
        System.out.println("The number of quakes by depth criteria(depth) : "+byDepth.size());
    }
    
    public void quakesbyPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source ="data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        //ArrayList<QuakeEntry> byPhrase = filterByPhrase(list,"end","Alaska");
        ArrayList<QuakeEntry> byPhrase = filterByPhrase(list,"any","Can");
        //ArrayList<QuakeEntry> byPhrase = filterByPhrase(list,"start","Quarry Blast");
        //dumpCSV(byPhrase);
        System.out.println("The number of quakes with that criteria(phrase) :"+byPhrase.size());
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
           System.out.println(qe);
        }
    }
    
}
