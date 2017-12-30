
/**
 * Write a description of EfficientmarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientmarkovModel extends AbstractMarkovModel{
 private int keyLen;
 private HashMap<String,ArrayList<String>> followsMap;
    
    public EfficientmarkovModel(int n) {
        myRandom = new Random();
        keyLen = n;
     }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        followsMap = new HashMap<String,ArrayList<String>>();
        buildMap();
        printHashMapInfo();
    }
    
    public void buildMap(){
           /*for(int i = 0; i<myText.length();i++){
            ArrayList<String> follows = new ArrayList<String>();
            String key = null;
            if(i<myText.length()-keyLen){
                key = myText.substring(i,i+keyLen);
            }
            else if (i==myText.length()-keyLen){
                key = myText.substring(i);
                followsMap.put(key,new ArrayList<String>());
            }
            if(!followsMap.containsKey(key)&& i <myText.length()-keyLen){
                follows.add(myText.substring(i+keyLen,i+keyLen+1));
                followsMap.put(key,follows);
            }else if (followsMap.containsKey(key)&& i<myText.length()-keyLen){
                follows = followsMap.get(key);
                follows.add(myText.substring(i+keyLen,i+keyLen+1));
                followsMap.put(key,follows);
            }
        }*/
         ArrayList<String> follows = new ArrayList<String>();
         String key = myText.substring(myText.length()-keyLen);
         followsMap.put(key,follows);
        for(int i =0;i<myText.length()-keyLen;i++){
            follows = new ArrayList<String>();
            key = myText.substring(i,i+keyLen);
            if(!followsMap.containsKey(key)){
                follows.add(myText.substring(i+keyLen,i+keyLen+1));
                followsMap.put(key,follows);
            }else{
                follows = followsMap.get(key);
                follows.add(myText.substring(i+keyLen,i+keyLen+1));
                followsMap.put(key,follows);
            }
        }
        
      }
    
    public ArrayList<String> getFollows(String key){
        //System.out.println("inside map get follows");
        return followsMap.get(key);
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
            //System.out.println(Key);
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
    
    public void printHashMapInfo(){
        /*for(String key : followsMap.keySet()){
            System.out.println(key+" --"+followsMap.get(key));
        }*/
        System.out.println ("Hash Map Size "+ followsMap.size());
        System.out.println("Num keys in hashmap "+followsMap.keySet().size());
        String maxKey = null;
        int MaxSize = 0;
        for(String key : followsMap.keySet()){
            if(followsMap.get(key).size()>MaxSize){
                MaxSize = followsMap.get(key).size();
                maxKey = key;
            }            
        }
        System.out.println("The size of the largest value in hashmap "+followsMap.get(maxKey).size()+" and key is "+maxKey);
        
    }
    
    public String toString(){
        return "Markov model of order "+keyLen;
    }
}
