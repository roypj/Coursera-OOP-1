
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, String target, int start){
            for(int i = start;i<words.length;i++){
                if(words[i].equals(target)){
                    return i;
                }
            }
            return -1;
       }
    public void testindexOf(){
        String st = "this is just a test yes this is a simple test";
        String[] words = st.split("\\s+");
        /*System.out.println(indexOf(words,"this",0));
        System.out.println(indexOf(words,"this",3));
        System.out.println(indexOf(words,"frog",0));
        System.out.println(indexOf(words,"frog",5));
         System.out.println(indexOf(words,"simple",2));
        System.out.println(indexOf(words,"test",5));*/
        System.out.println(getFollows("is"));
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        for(int i = start ; i<myText.length-1;i++){
            int idx = indexOf(myText,key,start);
            if(idx==-1){
                break;
            }
            else{
                follows.add(myText[idx+1]);
                start = idx+1;
            }
            
        }
        return follows;
    }

}
