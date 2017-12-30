
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
     public MarkovWord(int order) {
         myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, WordGram target, int start){
            for(int i = start;i<words.length-target.length();i++){
                WordGram wg = new WordGram(words,i,target.length());
                if(wg.equals(target)){
                    return i;
                }
            }
            return -1;
       }
    public void testindexOf(){
        String st = "this is just a test yes this is a simple test";
        String[] words = st.split("\\s+");
        int size = 2;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			System.out.println(wg);
			System.out.println(getFollows(wg));
		}
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            //ArrayList<String> follows = new ArrayList<String>();
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        for(int i = start ; i<myText.length-kGram.length();i++){
            int idx = indexOf(myText,kGram,start);
            if(idx==-1){
                break;
            }
            else{
                follows.add(myText[idx+kGram.length()]);
                start = idx+1;
            }
            
        }
        return follows;
    }
    
    public String toString(){
        return "Markov word of order "+myOrder;
    }


}
