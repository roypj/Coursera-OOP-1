
/**
 * Write a description of CaesarCipherOO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipherOO {
    private String alphabet ;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipherOO(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        mainKey = key;
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
        }
    public String encrypt(String message){
        StringBuilder sb = new StringBuilder(message);
        for(int k=0;k<sb.length();k++){
            char currChar = sb.charAt(k);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if(idx!=-1){
                if(Character.isLowerCase(currChar)){
                    sb.setCharAt(k,Character.toLowerCase(shiftedAlphabet.charAt(idx)));
                }else{
                    sb.setCharAt(k,shiftedAlphabet.charAt(idx));
                }
            }
        }
        return sb.toString();
    }
    public String decrypt(String encrypted){
        CaesarCipherOO CCoo = new CaesarCipherOO(26-mainKey);
        String decrypted = CCoo.encrypt(encrypted);
        return decrypted;
    }
   
    

}
