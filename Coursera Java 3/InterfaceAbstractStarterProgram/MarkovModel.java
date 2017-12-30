
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
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
    
    public String toString(){
        return "Markov model of order "+keyLen;
    }
}