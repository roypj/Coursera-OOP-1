
/**
 * Write a description of part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class part4 {
    public void readUrls(){
    
        URLResource URLs = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");;
       for( String word : URLs.words()){
        
        
        String test =word;
        int yotubIdx = test.toUpperCase().indexOf("youtube".toUpperCase());
        //System.out.println(yotubIdx);
        if(yotubIdx!=-1){
        int startIdx = test.toUpperCase().lastIndexOf("\"",yotubIdx);
        //System.out.println(startIdx);
        int endIdx = test.toUpperCase().indexOf("\"",yotubIdx+"youtube".length());
        //System.out.println(endIdx);
        System.out.println(test.substring(startIdx+1,endIdx));
    }
    }
    }
    
    
//"http://www.youtube.com/watch?v=oPRfDC8kTqM"

}