
/**
 * Write a description of dnaReader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class dnaReader {
    private HashMap<String, Integer> codonCounts;
    
    public dnaReader(){
        codonCounts = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start,String dna){
        codonCounts.clear();
        
            for(int k=start;k<dna.length()-3;k+=3){
                String codon = dna.substring(k,k+3);
                if(!codonCounts.containsKey(codon)){
                    codonCounts.put(codon.trim(),1);                    
                }else{
                    codonCounts.put(codon,codonCounts.get(codon)+1);
                }
            }
        System.out.println(codonCounts.size());
    }
    
    public String getMostCommonCodon(){
        String mostCommon =null;
        int maxCount =0;
        for(String k : codonCounts.keySet()){
            if(codonCounts.get(k)>maxCount){
                maxCount = codonCounts.get(k);
                mostCommon = k;
            }
        }
        return mostCommon;
    }
    public int getCount(String cdn){
        int count =0;
        if(codonCounts.containsKey(cdn)){
            count = codonCounts.get(cdn);
        }
        return count;
    }
    public void printCodons(int start, int end){
        for(String k :codonCounts.keySet()){
            int count = codonCounts.get(k);
            if(count>=start && count<=end){
                System.out.println(k+"\t"+count);
            }
        }
    }
    

}
