
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key){
        //StringBuilder sb = new StringBuilder(myText);
        ArrayList<String> follows = new ArrayList<String>();
        /*for(int i = 0 ; i<myText.length();i++){
            int idx = myText.indexOf(key,i);
            if(idx!=-1){
                char fls = myText.charAt(idx+1);
                follows.add(Character.toString(fls));
                i = idx+1;
               }
               
           }*/
           int start = 0;
           while(true){
               int idx = myText.indexOf(key,start);
               if(idx==-1){
                   break;
               }else {
                   if(idx < myText.length()-1){
                       char fls = myText.charAt(idx+key.length());
                       follows.add(Character.toString(fls));
                    }
                   start = idx+1;
               }
           }
        return follows;
       }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String Key = myText.substring(index,index+1);
        sb.append(Key);
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(Key);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            Key=next;
        }
        
        return sb.toString();
    }

}
