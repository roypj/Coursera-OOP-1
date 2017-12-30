
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipherTwo {
    private String alphabet;
    private String cipher1;
    private String cipher2;
    private int mainkey1;
    private int mainkey2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        mainkey1 = key1;
        mainkey2 = key2;
        cipher1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        cipher2 = alphabet.substring(key2)+alphabet.substring(0,key2);
       
    }
    public String encryptDualKey(String message){
        StringBuilder sb = new StringBuilder(message);
        for(int k=0;k<sb.length();k++){
            char currChar = sb.charAt(k);
            //System.out.println(k+" "+k%2+" "+currChar);
            int idx =0;
            if(k%2==0){
                 idx = alphabet.indexOf(Character.toUpperCase(currChar));
                 // System.out.println(k+" "+k%2+" "+currChar+"idx :"+idx);
                 if(idx!=-1){
                     if(Character.isLowerCase(currChar)){                  
                         sb.setCharAt(k,Character.toLowerCase(cipher1.charAt(idx)));
                        }else{
                            sb.setCharAt(k,cipher1.charAt(idx));
                        }
                    }
            }else{
                idx = alphabet.indexOf(Character.toUpperCase(currChar));
                 //System.out.println(k+" "+k%2+" "+currChar+"idx :"+idx);
                if(idx!=-1){
                    if(Character.isLowerCase(currChar)){                  
                        sb.setCharAt(k,Character.toLowerCase(cipher2.charAt(idx)));
                    }else{
                        sb.setCharAt(k,cipher2.charAt(idx));
                    }
                }
        }
            
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }
    public String decryptDualKey(String encrypted){
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(26-mainkey1,26-mainkey2);
        String decrypted = ccTwo.encryptDualKey(encrypted);
        return decrypted;       
    }    
    
}
