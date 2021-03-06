
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
     private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> followsMap;
    
     public EfficientMarkovWord(int order) {
         myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        followsMap = new HashMap<WordGram, ArrayList<String>>();
        buildMap();
        printHashMapInfo();
    }
    
    private void buildMap(){
        //HashMap<Integer, ArrayList<String>> followsMap = new HashMap<Integer,ArrayList<String>>();
        //get last word gram and add it to hashmap
        WordGram wg = new WordGram(myText,myText.length - myOrder,myOrder);
        followsMap.put(wg,new ArrayList<String>());
        for(int index = 0 ; index < myText.length - myOrder; index++){
            ArrayList<String> follows = new ArrayList<String>();
            wg = new WordGram(myText,index,myOrder);
            if(!followsMap.containsKey(wg)){
               follows.add(myText[indexOf(myText,wg,index)+wg.length()]);
               followsMap.put(wg,follows);
            }else{
                follows = followsMap.get(wg);
                follows.add(myText[indexOf(myText,wg,index)+wg.length()]);
                followsMap.put(wg,follows);
            }
            
        }
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
    
    public void printHashMapInfo(){
        /*for(WordGram key : followsMap.keySet()){
            System.out.println(key+" --"+followsMap.get(key));
        }*/
        System.out.println ("Hash Map Size "+ followsMap.size());
        System.out.println("Num keys in hashmap "+followsMap.keySet().size());
        WordGram maxKey = null;
        int MaxSize = 0;
        for(WordGram key : followsMap.keySet()){
            if(followsMap.get(key).size()>MaxSize){
                MaxSize = followsMap.get(key).size();
                maxKey = key;
            }            
        }
        System.out.println("The size of the largest value in hashmap "+followsMap.get(maxKey).size()+" and key is "+maxKey);
        
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
       return followsMap.get(kGram);        
    }
    
    public String toString(){
        return "Markov word of order "+myOrder;
    }


}
