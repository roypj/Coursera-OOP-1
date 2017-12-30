
/**
 * Write a description of CountCommonWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CountCommonWords {
    
    //method to get common words
    //common words in common.txt
    //read common.txt and sore common words as a string.
    //method should read common words file and return an array of strings with common words
    
    public String[] getCommon(){
        FileResource fr = new FileResource("CommonWordsData\\common.txt");
        String[] commonWords = new String[20];
        int idx =0;
        for(String wrd : fr.words()){
            commonWords[idx]=wrd;
            idx+=1;
        }
    return commonWords;
    }
    //count occurrence of common words in a file
    //get contents of file, common words and return an int array with count of common words.
    
    public int[] getCommonWordCount(FileResource fr,String[] common,int[] counts){
        for(String wrd : fr.words()){
            String word = wrd.toLowerCase();
            for(int k=0;k<common.length;k++){
                if(common[k].equals(word)){
                    counts[k]+=1;
                    break;
                }            
            }
        }
        return counts;
    }

    public void testGetCommon(){
        String[] cmnWrds = getCommon();
        for(int k=0;k<cmnWrds.length;k++){
            System.out.println(cmnWrds[k]);
        }
    }
    
    public void testGetwrdCnt(){
        String[] files ={"caesar.txt","errors.txt","hamlet.txt","likeit.txt","macbeth.txt","romeo.txt"};
        for(int i=0;i<files.length;i++){
        FileResource fr = new FileResource("CommonWordsData\\"+files[i]);
        String[] common = getCommon();
        int[] counts = new int[common.length];
        counts = getCommonWordCount(fr,common,counts);
        System.out.println("Processed "+files[i]);
        for(int k=0;k<counts.length;k++){
            System.out.println(common[k]+"\t"+counts[k]);
        }
    }
    }
}
