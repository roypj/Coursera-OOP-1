
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.Character.*;
import java.lang.StringBuilder.*;
public class part1 {
    public boolean isVowel(char ch){
        String vowel ="aeiou";
        int idx = vowel.indexOf(Character.toLowerCase(ch));
        if(idx==-1){
            return false;
        }else{
            return true;
        }    
    }
    
 public String replaceVowels(String phrase,char ch){
     StringBuilder input = new StringBuilder(phrase);
     for(int i=0;i<phrase.length();i++){
         if(isVowel(input.charAt(i))){
            input.setCharAt(i,ch);
            }
        }
     
return input.toString();
}

public String emphasize(String phrase, char ch){
    StringBuilder input = new StringBuilder(phrase);
    char lowerCh = Character.toLowerCase(ch);
    for(int k=0;k<input.length();k++){
        if((Character.toLowerCase(input.charAt(k))==lowerCh)&&(k+1)%2!=0){
            input.setCharAt(k,'*');        }
        if((Character.toLowerCase(input.charAt(k))==lowerCh)&&(k+1)%2==0){
            input.setCharAt(k,'+');
        }   
    
    }
    return input.toString();
}

 public void testisVowel(){
System.out.println(isVowel('f'));
System.out.println(isVowel('A'));
System.out.println(isVowel('a'));
System.out.println(isVowel('E'));
System.out.println(isVowel('e'));
System.out.println(isVowel('D'));
System.out.println(isVowel('d'));
}   
public void testReplaceVowel(){
System.out.println(replaceVowels("Hello World",'*'));
System.out.println(replaceVowels("BINGO",'&'));
System.out.println(replaceVowels("AEIOU",'c'));
}

public void testEmphasize(){
    System.out.println(emphasize("dna ctgaaactga",'a'));
    System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
}
}


