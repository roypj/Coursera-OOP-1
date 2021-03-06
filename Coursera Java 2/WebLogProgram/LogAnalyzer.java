
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()){
             records.add(WebLogParser.parseEntry(line));
            }
     }
     
     public int countUniqueIps(){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if(!uniqueIps.contains(ipAddr)){
                 uniqueIps.add(ipAddr);
                }
            }
            return uniqueIps.size();
        }
        
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             if(le.getStatusCode()>num){
                 System.out.println(le);
                }
            }
        }
     
     public int UniqueIPVisitsOnDay(String someDay){
         ArrayList<String> uniqueIpsOnDay = new ArrayList<String>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString();
             //System.out.println(date);
             //System.out.println(date.substring(4,10));
             if(date.substring(4,10).equals(someDay)){
                 String ipAdr = le.getIpAddress();
                 if(!uniqueIpsOnDay.contains(ipAdr)){
                      uniqueIpsOnDay.add(ipAdr);
                    }
                }
             
            }
         return uniqueIpsOnDay.size();
        }  
        
        public int countUniueIpsInRange(int low, int high){
            ArrayList<String> uniqueIpsInRange = new ArrayList<String>();
            for(LogEntry le : records){
                int statCd = le.getStatusCode();
                if(statCd >=low && statCd <= high){
                    String ipAdr = le.getIpAddress();
                    if(!uniqueIpsInRange.contains(ipAdr)){
                        uniqueIpsInRange.add(ipAdr);
                    }
                }
                
            }
            return uniqueIpsInRange.size();
        }
        
     public HashMap<String,Integer> countVisitsPerIp(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for(LogEntry le :records){
             String ipAdr = le.getIpAddress();
             if(!counts.containsKey(ipAdr)){
                 counts.put(ipAdr,1);
                }
             else{
                 counts.put(ipAdr,counts.get(ipAdr)+1);
                }
            }
            return counts;
        }
    
    public int mostNumberVisitsByIp(HashMap<String,Integer> counts){
        int maxNum = 0;
        String maxVisitIp = null;
        for(String ip : counts.keySet()){
            int visits = counts.get(ip);
            if(visits>maxNum){
                maxNum = visits;
                maxVisitIp = ip;
            }
        }
        return maxNum;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
        ArrayList<String> ipAdrs = new ArrayList<String>();
        int maxNum = mostNumberVisitsByIp(counts);
        for(String ip : counts.keySet()){
            if(counts.get(ip)==maxNum){
                ipAdrs.add(ip);
            }
        }
        return ipAdrs;
    }
    
    public HashMap<String,ArrayList<String>> ipForDays(){
        HashMap<String,ArrayList<String>> iPsByDay = new HashMap<String,ArrayList<String>>();
        for(LogEntry le : records){
            ArrayList<String> ipAdrs = new ArrayList<String>();
            String ipAdr = le.getIpAddress();
            String mmmdd = le.getAccessTime().toString().substring(4,10);
            if(!iPsByDay.containsKey(mmmdd)){
                ipAdrs.add(ipAdr);
                iPsByDay.put(mmmdd,ipAdrs);                
            }else{
                ArrayList<String> temp = iPsByDay.get(mmmdd);
                temp.add(ipAdr);
                iPsByDay.put(mmmdd,temp);                
            }
        }
        return iPsByDay;
    }
    public String dayWithMostIpVisits(HashMap<String,ArrayList<String>> ipForDays ){
        int maxVisits = 0;
        String maxVisitDay = null;
        for(String day : ipForDays.keySet()){
            int numVisits = ipForDays.get(day).size();
            if(numVisits > maxVisits){
                maxVisits = numVisits;
                maxVisitDay = day;
            }
        }
        return maxVisitDay;
    }
    public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String,ArrayList<String>> ipForDays, String Day){
        ArrayList<String> maxVisitIps = new ArrayList<String>();
        ArrayList<String> dayVisits = ipForDays.get(Day);
        HashMap<String,Integer> ipDayCount = new HashMap<String,Integer>();
        for(String ip : dayVisits){
            if(!ipDayCount.containsKey(ip)){ 
                ipDayCount.put(ip,1);
            }else{
                ipDayCount.put(ip,ipDayCount.get(ip)+1);
            }
        }
        maxVisitIps = iPsMostVisits(ipDayCount);
        return maxVisitIps;
    }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
