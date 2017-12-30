
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
        System.out.println("from abstract class"+myText.length());
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key){
        //StringBuilder sb = new StringBuilder(myText);
        ArrayList<String> follows = new ArrayList<String>();
           int start = 0;
           while(true){
               //System.out.println(key+" "+start+" "+myText.length());               
               int idx = myText.indexOf(key,start);
               //System.out.println(idx);
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

}
