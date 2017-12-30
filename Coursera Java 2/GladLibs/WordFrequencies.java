
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String wrd : fr.words()){
            String word = wrd.toLowerCase();
            int idx = myWords.indexOf(word);
            if(idx==-1){
                myWords.add(word);
                myFreqs.add(1);
            }else{
                int count = myFreqs.get(idx);
                myFreqs.set(idx, count+1);
            }
            
        }
//         for(int i=0;i<myFreqs.size();i++){
//             System.out.println(myWords.get(i)+"\t"+myFreqs.get(i));
//         }
        System.out.println("Num unique words : " +myWords.size());
        int maxIdx = findIndexOfMax(myFreqs);
        System.out.println("The word that occurs most is : "+myWords.get(maxIdx)+" and count is : "+myFreqs.get(maxIdx));
        
    }
    public int findIndexOfMax(ArrayList<Integer> myFreqs){
        int maxIdx =0;
        int max =0;
        for(int i=0;i<myFreqs.size();i++){
            if(myFreqs.get(i)>max){
                max = myFreqs.get(i);
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void testUnique(){
        findUnique();        
    }

}
