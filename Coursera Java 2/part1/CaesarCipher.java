
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.Character.*;
import java.lang.StringBuilder.*;
public class CaesarCipher {
    
    public String encrypt(String input, int key){
        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cipher = Alphabet.substring(key)+Alphabet.substring(0,key);
        StringBuilder encryptedString  = new StringBuilder(input);
        
        for(int charCtr =0;charCtr<encryptedString.length();charCtr++){
            char currChar = encryptedString.charAt(charCtr);
            int idx = Alphabet.indexOf(Character.toUpperCase(currChar)); 
            if(idx!=-1){
                if(Character.isLowerCase(currChar)){
                    encryptedString.setCharAt(charCtr,Character.toLowerCase(cipher.charAt(idx)));
                }
                else{
                    encryptedString.setCharAt(charCtr,cipher.charAt(idx));
                }         
            }
      
        }
        return encryptedString.toString();
    }
    public char getEncryptedChar(char ch,int key){
        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cipher = Alphabet.substring(key)+Alphabet.substring(0,key);
        int idxAlph = Alphabet.indexOf(Character.toUpperCase(ch));
        if(idxAlph!=-1){
            if(Character.isLowerCase(ch)){
                return Character.toLowerCase(cipher.charAt(idxAlph));
            }else{
                return cipher.charAt(idxAlph);
            }
        }else{
            return ch;
        }    
    }
    public String encryptTwoKeys(String message, int key1,int key2){
      StringBuilder encryptedMessage = new StringBuilder(message);
      for(int charIdx=0;charIdx<encryptedMessage.length();charIdx++){
          char currChar = encryptedMessage.charAt(charIdx);
          char newChar=Character.MIN_VALUE;
          if(charIdx%2==0){
              newChar = getEncryptedChar(currChar,key1);            
            }
            else{
              newChar = getEncryptedChar(currChar,key2);
            }
          encryptedMessage.setCharAt(charIdx,newChar);
        }
        return encryptedMessage.toString();
    }
    public void testEcrypt(){
    //System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!",23));    
    //System.out.println(encrypt("First Legion Attack East Flank!",23));
    //String ecr = encrypt("First Legion Attack East Flank!",23);
    //System.out.println("First Legion Attack East Flank!");
    //System.out.println(ecr);
    //System.out.println(encrypt(ecr,3));
    System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15));
//     FileResource fr = new FileResource();
//     String message = fr.asString();
//     int key =17;
//     String ecr = encrypt(message,key);
//     String dcr =encrypt(ecr,26-key);
//     System.out.println(ecr);
//     System.out.println(dcr);
//     System.out.println(key);
    }
    
    public void testMultiKey(){
    System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",8,21));
    }
}
    
