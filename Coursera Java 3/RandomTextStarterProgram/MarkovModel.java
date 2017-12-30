
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int keyLen;
    
    public MarkovModel(int n) {
        myRandom = new Random();
        keyLen = n;
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
           int start = 0;
           while(true){
               int idx = myText.indexOf(key,start);
               if(idx==-1){
                   break;
               }else {
                   if(idx < myText.length()-key.length()){
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
        int index = myRandom.nextInt(myText.length()-keyLen);
        String Key = myText.substring(index,index+keyLen);
        sb.append(Key);
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(Key);
            if(follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            Key=Key.substring(1)+next;
        }
        
        return sb.toString();
    }

}
