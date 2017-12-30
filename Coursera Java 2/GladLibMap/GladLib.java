import edu.duke.*;
import java.util.*;
import java.io.*;

public class GladLib {
    private HashMap<String,ArrayList<String>> contentMap;
//     private ArrayList<String> adjectiveList;
//     private ArrayList<String> nounList;
//     private ArrayList<String> colorList;
//     private ArrayList<String> countryList;
//     private ArrayList<String> nameList;
//     private ArrayList<String> animalList;
//     private ArrayList<String> timeList;
//     private ArrayList<String> verbList;
//     private ArrayList<String> fruitList;
     private ArrayList<String> subList;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static DirectoryResource dataSourceDirectory;
    
    public GladLib(){
      DirectoryResource dataSourceDirectory = new DirectoryResource();
        contentMap = new HashMap<String,ArrayList<String>>();
        myRandom = new Random();
        subList = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
    }
    
//     public GladLib(String source){
//         initializeFromSource(source);
//         myRandom = new Random();
//         subList = new ArrayList<String>();
//     }
    
    private void initializeFromSource(DirectoryResource dr) {
        for(File f : dr.selectedFiles()){
            String flnm = f.getName();
            String absPath = f.getAbsolutePath();
            //System.out.println(abspath.replace("\\","\\\"));
            String key = flnm.substring(0,flnm.indexOf("."));
            System.out.println("key "+key);
            ArrayList<String> contents = new ArrayList<String>();
            contents = readIt(absPath);
            System.out.println("contents "+contents.size());
            contentMap.put(key,contents);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (contentMap.containsKey(label)) {
            return randomFrom(contentMap.get(label));
        }else{
            return "**UNKNOWN**";
        }
    }
    
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));        
        //int idx = subList.indexOf(sub);
        if(!subList.contains(sub)){
            subList.add(sub);
            return prefix+sub+suffix;
        }else{
            while(subList.contains(sub)){
                sub = getSubstitute(w.substring(first+1,last)); 
             }
            subList.add(sub);
            return prefix+sub+suffix;
        }
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            //FileResource resource = new FileResource("C:\\Users\\Roy\\Desktop\\Coursera Java 2\\GladLibMap\\data\\content\\"+source);
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        subList.clear();
        String story = fromTemplate("data/templates/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("The total number oof words replaced were : "+subList.size());
    }
    


}
