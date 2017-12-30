
/**
 * Write a description of miniProject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class miniProject {
    
    public void totalBirths(CSVParser parser){
        int totalBirths = 0;
        int girlBirths = 0;
        int boyBirths = 0;
        
        for (CSVRecord rec : parser){
            int numBirths = Integer.parseInt(rec.get(2));
            totalBirths +=numBirths;
            String gender = rec.get(1);
            if(gender.equals("M")){
             boyBirths+=numBirths;
            }
            if(gender.equals("F")){
             girlBirths+=numBirths;
            }
        
        }
        System.out.println("Total Births : "+totalBirths);
        System.out.println("Girl Births : "+girlBirths);
        System.out.println("Boy Births : "+boyBirths);
    
    
    }
    
    public long getMaxGirlRank(CSVParser parser){
        long maxGirlRank=0;
        for(CSVRecord rec:parser){
            if(rec.get(1).equals("F")){
            long rank = rec.getRecordNumber();
            if(rank>maxGirlRank){
                maxGirlRank = rank;
            }
            
        }       
        
        }   
     return maxGirlRank;
    }
    
    
    public long getRank(int year,String Name,String Gender){
       FileResource fr = new FileResource("C:\\Users\\Roy\\Desktop\\Coursera Java\\babyNames\\us_babynames\\us_babynames_by_year\\yob"+Integer.toString(year)+".csv");
       //FileResource fr = new FileResource("C:\\Users\\Roy\\Desktop\\Coursera Java\\babyNames\\us_babynames\\us_babynames_test\\yob"+Integer.toString(year)+"short.csv");
        //FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
         long rank=0;
        //int found=0;
        long maxGalRnk =0;
        if(Gender.equals("M")){  
            CSVParser parserGal = fr.getCSVParser(false);
            maxGalRnk =getMaxGirlRank(parserGal);
            //System.out.println(maxGalRnk);
        }
        for(CSVRecord rec:parser){
            String name = rec.get(0);
            String gender = rec.get(1);
            
            if(Gender.equals(gender)&&Name.equals(name)){
                rank = rec.getRecordNumber(); 
                //found=1;
                break;
            }             
         
        }  
        if(rank==0){
            return -1;
        }else{
            return rank-maxGalRnk;
        }
    }
    
    public String getName(int year,int Rank,String Gender){
        FileResource fr = new FileResource("C:\\Users\\Roy\\Desktop\\Coursera Java\\babyNames\\us_babynames\\us_babynames_by_year\\yob"+Integer.toString(year)+".csv");
        //FileResource fr = new FileResource("C:\\Users\\Roy\\Desktop\\Coursera Java\\babyNames\\us_babynames\\us_babynames_test\\yob"+Integer.toString(year)+"short.csv");
        //FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        long maxGalRnk = 0;
        String name = null;
        //int found=0;
         if(Gender.equals("M")){  
            CSVParser parserGal = fr.getCSVParser(false);
            maxGalRnk =getMaxGirlRank(parserGal);
            //System.out.println(maxGalRnk);
        }
        
        for (CSVRecord rec : parser){
            String gender = rec.get(1);
            long rowNum = rec.getRecordNumber();
            if(Gender.equals(gender)&&(Rank==(rowNum-maxGalRnk))){
             name = rec.get(0);
             //found=1;
            }
        
        }
        if(name==null){
        return "No Name";
        }else{
            return name;
        }
    
    
    }
    
    public String WhatNameInYear(String Name,int Year,int newYear, String Gender){
        long maxGalRnk = 0;
        String newName = null;  
        int yrRank = (int)getRank(Year,Name,Gender);
        newName = getName(newYear,yrRank,Gender);
        if(newName==null){
         return Name+"("+Gender+") born in "+Year+" would have been nameless if born in "+ newYear+".";
        }else{
         return Name+"("+Gender+") born in "+Year+" would have been "+newName+"("+Gender+") if born in "+ newYear+".";      
        }
    
    }
    
    public int yearOfHighestRank(String Name, String Gender){
        DirectoryResource dr = new DirectoryResource();
        long highestRank =1000000000;
        int yrOfHighestRank=0;
        String flnm=null;
        int rank =0;
        for(File fl : dr.selectedFiles()){
            flnm = fl.getName();            
            //int year = Integer.parseInt(flnm.substring(flnm.indexOf("20"),flnm.indexOf("20")+4));
            int year = Integer.parseInt(flnm.substring(flnm.indexOf("b")+1,flnm.indexOf("b")+5));
             rank = (int)getRank(year,Name,Gender);
            //System.out.println("Filename "+flnm+" rank "+rank);
            if(highestRank==1000000000&&rank!=-1){
                highestRank = rank;
                yrOfHighestRank = year;
             }else{
                if(rank<highestRank&&rank!=-1){
                highestRank=rank;
                yrOfHighestRank = year;
                }
                
             }
        
        }
        if(yrOfHighestRank==0){
            return -1;
        }
        else{
        return yrOfHighestRank;
    }
    
    }
    
    public double getAverageRank(String Name, String Gender){
        double sum=0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File fl : dr.selectedFiles()){
            String flnm = fl.getName();
            int year = Integer.parseInt(flnm.substring(flnm.indexOf("b")+1,flnm.indexOf("b")+5));
            //int year = Integer.parseInt(flnm.substring(flnm.indexOf("20"),flnm.indexOf("20")+4));
            int rank= (int) getRank(year,Name,Gender);
            if(rank!=-1){
                sum+=rank;
                count+=1;
            }
        
        }
        if(sum>0){
            return sum/count;
        }else{
            return -1;
        }
    }
    
    public int getTotalBirthsRankedHigher(int Year,String Name,String Gender){
        FileResource fr = new FileResource("C:\\Users\\Roy\\Desktop\\Coursera Java\\babyNames\\us_babynames\\us_babynames_by_year\\yob"+Integer.toString(Year)+".csv");
        //FileResource fr = new FileResource("C:\\Users\\Roy\\Desktop\\Coursera Java\\babyNames\\us_babynames\\us_babynames_test\\yob"+Integer.toString(Year)+"short.csv");
        //FileResource fr = new FileResource();
        int birthsHigher = 0;
        int rankThreshold = (int)getRank(Year,Name,Gender);
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord rec : parser){
            String name = rec.get(0);
            String gender = rec.get(1);
            
            if(Gender.equals(gender)){
                int rank = (int) getRank(Year,name,Gender);
          
                if(rank<rankThreshold && rank!=-1){
                    birthsHigher+=Integer.parseInt(rec.get(2));
                }
                if(rank>=rankThreshold){
                    break;
                }
            }
        }
        return birthsHigher;
       
    }
    
    public void testTotalBirths(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser(false);
    totalBirths(parser);
    }
    
    public void testMaxGirlrank(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser(false);
    System.out.println(getMaxGirlRank(parser));    
    }
    
    public void testGetRank(){
        System.out.println("Num female names in 1900 "+getRank(1900,"Zuma","F"));
        System.out.println("Num male names in 1905 "+getRank(1905,"Woodson","M"));
        System.out.println("rank of emily in 1960 "+getRank(1960,"Emily","F"));  
         System.out.println("rank of frank in 1971 "+getRank(1971,"Frank","M")); 
        //System.out.println("rank of frank in 1990 "+getRank(1990,"Jessica","F")); 
          //System.out.println("rank of frank in 1990 "+getRank(1990,"Monica","F")); 

        //System.out.println(getRank(1880,"Wilma","F"));
    }
    
        public void testGetName(){
        System.out.println(getName(1980,350,"F"));    
        System.out.println(getName(1982,450,"M"));
        //System.out.println(getName(1880,7,"M"));
        //System.out.println(getName(1880,942,"F"));
        //System.out.println(getName(1880,-942,"F"));
    }
    public void testWhatnameInYr(){
        //System.out.println(WhatNameInYear("Mary",1880,2014,"F")); 
        //System.out.println(WhatNameInYear("John",1880,2014,"M")); 
        //System.out.println(WhatNameInYear("Mary",1880,2014,"M")); 
        System.out.println(WhatNameInYear("Susan",1972,2014,"F"));
        System.out.println(WhatNameInYear("Owen",1974,2014,"M"));
    }
    
    public void testyearOfHighestRank(){
    System.out.println(yearOfHighestRank("Genevieve","F"));
    System.out.println(yearOfHighestRank("Mich","M"));
    }
    
    public void testGetAvgRank(){
    //System.out.println(getAverageRank("Mason","M"));
    //System.out.println(getAverageRank("Jacob","M"));
    System.out.println(getAverageRank("Susan","F"));
    System.out.println(getAverageRank("Robert","M"));
    
    }
    
    public void testgetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(1990,"Emily","F"));
          System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
        //System.out.println(getTotalBirthsRankedHigher(2012,"Ethan","M"));
    //System.out.println(getTotalBirthsRankedHigher(2014,"Olivia","F"));    
    //System.out.println(getTotalBirthsRankedHigher(1880,"Mary","F"));
    //System.out.println(getTotalBirthsRankedHigher(1880,"Mary","M"));
    //System.out.println(getTotalBirthsRankedHigher(1880,"xsazs","M"));
    }
}
