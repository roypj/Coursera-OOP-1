
/**
 * Write a description of TestCaesarCipherOO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipherOO {
     public int[] countLetters(String message){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counts = new int[26];
        StringBuilder sb = new StringBuilder(message);
        for(int k=0;k<sb.length();k++){
            char currChar = sb.charAt(k);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if(idx!=-1){
                counts[idx]+=1;
            }
        }
        return counts;
    }
    public int maxIdx(int[] counts){
        int max =0;
        int maxIdx =0;
        for(int k = 0;k<counts.length;k++){
            if(counts[k]>max){
                max = counts[k];
                maxIdx = k;
            }
        }
        return maxIdx;
    }
    public String breakCaesarCipher(String encrypted){
        int[] alphabetCounts = countLetters(encrypted);
        int maxDex = maxIdx(alphabetCounts);
        int dkey = maxDex-4;
        if(maxDex<4){
            dkey = 26-(4-maxDex);
        }
        CaesarCipherOO cc = new CaesarCipherOO(26-dkey);
        String decrypted = cc.encrypt(encrypted);
        return decrypted;
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherOO cc = new CaesarCipherOO(18);
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        //String decrypted = breakCaesarCipher(encrypted);
        System.out.println("ENCRYPTED : "+encrypted);
        System.out.println("DECRYPTED : "+decrypted);
        
    }
}
