
/**
 * Write a description of autoBreakCeasarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class autoBreakCeasarCipher {
    
    public int maxCountAlphabet(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[]counts = new int[26];
        
        for(int k=0;k<message.length();k++){
            int idx = alphabet.indexOf(Character.toLowerCase(message.charAt(k)));
            if(idx!=-1){
                counts[idx]+=1;
            }           
        
        }
        for(int k=0;k<counts.length;k++){
        System.out.println(alphabet.charAt(k)+"\t"+counts[k]);
        }
        int maxIdx =0;
        int max=0;
        for(int i=0;i<counts.length;i++){
            if(counts[i]>max){
                maxIdx=i;
                max=counts[i];
            }
        }
    return maxIdx;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int maxDex = maxCountAlphabet(encrypted);
        int dkey = maxDex -4;
        if(maxDex<4){
            dkey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted,26-dkey);
    }
    
    
    
    public void testDecrypt(){
        String message = " hieeeee howeeee areeee youeeee";
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(message,17);
        String decrypted = decrypt(encrypted);
        //String decrypted1 = cc.encrypt(encrypted,9);
        System.out.println("Orignal message "+message);
        System.out.println("encrypted message "+encrypted);
        System.out.println("decrypted message "+decrypted);
        //System.out.println("decrypted message "+decrypted1);
    
    }
    
    public void testmaxCntAlph(){
    int maxIdx = maxCountAlphabet("Hi, do you want a lollipop today?I own many good flavors, but banana is outstanding");
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    System.out.println(alphabet.substring(maxIdx,maxIdx+1));
    }
    
    
}
