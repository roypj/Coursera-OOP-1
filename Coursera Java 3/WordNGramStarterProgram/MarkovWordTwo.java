
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, String target1,String target2, int start){
            for(int i = start;i<words.length-2;i++){
                if(words[i].equals(target1)&&words[i+1].equals(target2)){
                    return i;
                }
            }
            return -1;
       }
    public void testindexOf(){
        /*String st = "this is just a test yes this is a simple test";
        String[] words = st.split("\\s+");
        System.out.println(indexOf(words,"this","is",0));
        System.out.println(indexOf(words,"this","is",3));
        System.out.println(indexOf(words,"just","a",0));
        System.out.println(indexOf(words,"frog",5));
         System.out.println(indexOf(words,"simple",2));
        System.out.println(indexOf(words,"test",5));
        System.out.println(getFollows("this","is"));
        System.out.println(getFollows("just","a"));*/
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1,key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1,String key2) {
        //String st = "this is just a test yes this is a simple test";
        //String[] myText = st.split("\\s+");
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        for(int i = start ; i<myText.length-1;i++){
            int idx = indexOf(myText,key1,key2,start);
            if(idx==-1){
                break;
            }
            else{
                follows.add(myText[idx+2]);
                start = idx+2;
            }
            
        }
        return follows;
    }
}
