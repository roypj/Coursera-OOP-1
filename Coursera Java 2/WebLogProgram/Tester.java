
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        //String filename = "C:/Users/Roy/Desktop/Coursera Java 2/webLog/short-test_log.txt";
        String filename = "C:\\Users\\Roy\\Desktop\\Coursera Java 2\\WebLogProgram\\weblog2_log";
        la.readFile(filename);
        //la.printAll();
        System.out.println("The number of Unique Ips: "+la.countUniqueIps());
        //la.printAllHigherThanNum(400);
       System.out.println("ip visits on a given day "+ la.UniqueIPVisitsOnDay("Sep 27"));
       //System.out.println("Sep 30 "+ la.UniqueIPVisitsOnDay("Sep 30"));
       System.out.println("200-299 "+ la.countUniueIpsInRange(200,299));
       //System.out.println("300-399 "+ la.countUniueIpsInRange(300, 399));
       HashMap<String,Integer> counts = la.countVisitsPerIp();
       //System.out.println("hashmap of counts :"+counts);
       int maxV = la.mostNumberVisitsByIp(counts);
       System.out.println("max visits by any Ip maxV :"+maxV);
      System.out.println("ips Most visited:"+la.iPsMostVisits(counts));
       //System.out.println(la.ipForDays());
       HashMap<String,ArrayList<String>> ipForDays = la.ipForDays();
       System.out.println("Max visit day :" + la.dayWithMostIpVisits(ipForDays));
       System.out.println("ips with max visits for a given day :-"+la.iPsWithMostVisitsOnDay (ipForDays,"Sep 29"));
    }
}
