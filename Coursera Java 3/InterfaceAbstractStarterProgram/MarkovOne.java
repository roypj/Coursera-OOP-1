
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovOne extends AbstractMarkovModel {
    
    
    public MarkovOne() {
        myRandom = new Random();
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
    
    public String toString(){
        return "Markov model of Order 1";
    }

}