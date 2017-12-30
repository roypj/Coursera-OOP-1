
/**
 * Write a description of TestCCtwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCCtwo {
    public String halfOfString(String message, int start){
        StringBuilder halfString = new StringBuilder();
        StringBuilder sb = new StringBuilder(message);
        for(int k=start;k<sb.length();k+=2){
           
                halfString.append(sb.charAt(k));
            
        }
        return halfString.toString();
    }
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
        String half1 = halfOfString(encrypted,0);
        String half2 = halfOfString(encrypted,1);
        int[] counts1 = countLetters(half1);
        int[] counts2 = countLetters(half2);
        int maxDex1 = maxIdx(counts1);
        int maxDex2 = maxIdx(counts2);
        int dkey1 = maxDex1-4;
        if(maxDex1<4){
            dkey1 = 26-(4-maxDex1);
        }
        int dkey2 = maxDex2-4;
        if(maxDex2<4){
            dkey2 = 26-(4-maxDex2);
        }
        System.out.println(dkey1+","+dkey2);
        CaesarCipherTwo cc2 = new CaesarCipherTwo(dkey1,dkey2);
        String decrypted = cc2.decryptDualKey(encrypted);
        return decrypted;
    }
    public void testCaesarTwo(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "hieeeee how areeeee you doingeeee";        
        CaesarCipherTwo cc2 = new CaesarCipherTwo(17,4);
       // System.out.println(cc2.mainkey1+" "+cc2.mainkey2);
        String ecrypted = cc2.encryptDualKey(message);
        //String decrypted = cc2.decryptDualKey(ecrypted);
        String decrypted = breakCaesarCipher(ecrypted);
        System.out.println("ORIGINAL : "+message);
        System.out.println("ENCRYPTED : "+ecrypted);
        System.out.println("DECRYPTED : "+decrypted);    
    }
}
