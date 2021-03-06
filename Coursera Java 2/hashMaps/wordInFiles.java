
/**
 * Write a description of wordInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class wordInFiles {
    private HashMap<String, ArrayList<String>> wordFileTracker;
    
    public wordInFiles(){
       
        wordFileTracker = new HashMap<String,ArrayList<String>>();
        
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);       
        for(String word : fr.words()){
            String flnm = f.getName();
            if(!wordFileTracker.containsKey(word)){
                ArrayList<String> fileNames=  new ArrayList<String>();
                fileNames.add(flnm);
                wordFileTracker.put(word,fileNames);
                }else{
                    ArrayList<String> wrdFiles = wordFileTracker.get(word);
                if(!wrdFiles.contains(flnm)){
                    wrdFiles.add(flnm);
                    wordFileTracker.put(word,wrdFiles);
                }
            }
            
        }
        
    }
    
    public void buildWordFileMap(){
        wordFileTracker.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int maxNum = 0;
        for(String k : wordFileTracker.keySet()){
            int numFiles = wordFileTracker.get(k).size();
            if(numFiles>maxNum){
                maxNum = numFiles;
            }
        }
        return maxNum;
    }
    
    public ArrayList<String> wordInNumFiles(int number){
        ArrayList<String> wrdInFiles = new ArrayList<String>();
        for(String k : wordFileTracker.keySet()){
            int numFiles = wordFileTracker.get(k).size();
            if(numFiles==number){
                wrdInFiles.add(k);
            }
        }
        return wrdInFiles;
    }
    
    public void printFilesIn(String word){
        if(wordFileTracker.containsKey(word)){
            ArrayList<String> files = new ArrayList<String>() ;
            files = wordFileTracker.get(word);
            for(int k=0;k<files.size();k++){
                System.out.println(files.get(k)+"\n");
            }
        }
    }
    
    public void printMap(){
        for(String k :  wordFileTracker.keySet()){
            ArrayList<String> flnms = wordFileTracker.get(k);
            //System.out.println(k+"\t"+flnms.size());
            for(int i=0;i<flnms.size();i++){
                if(i==0){
                    System.out.println(k+"\t"+flnms.get(i));
                }else{
                     System.out.println(" "+"\t"+flnms.get(i));
                }
            }
        }
    }

}
