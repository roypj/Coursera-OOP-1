import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
       StringBuilder sb = new StringBuilder(message);
       StringBuilder slice = new StringBuilder();
       for(int k=whichSlice;k<sb.length();k+=totalSlices){
           slice.append(sb.charAt(k));  
           //System.out.println("k :"+k);           
        }
        //System.out.println(sb.length()-totalSlices);
       return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int k=0;k<klength;k++){
            String slice = sliceString(encrypted,k,klength);
            key[k] = cc.getKey(slice);       
        }
        return key;
    }

    public void breakVigenere () {
       FileResource fr = new FileResource();
       String encrypted = fr.asString();
       //FileResource dict = new FileResource();
       //HashSet<String> dicts = readDictionary(dict);
       //String key = "flute";
//        int[] dkey = tryKeyLength(encrypted, 4,'e');
//        for(int i=0;i<dkey.length;i++){
//            System.out.println(dkey[i]);
//         }
//        VigenereCipher vc = new VigenereCipher(dkey);
        //String dcrypt = breakForLanguage(encrypted,dicts);
       //System.out.println(dcrypt.substring(0,50));
       HashMap<String, HashSet<String>> langDict  = makeLangDict();
       breakForAllLangs(encrypted,langDict);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for(String line : fr.lines()){
            dict.add(line.toLowerCase());
        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dict){
        int validWordCount =0;
        String[] words = message.split("\\W+");
        for(String wrd : words){
            if(dict.contains(wrd.toLowerCase())){
                validWordCount+=1;
            }
        }
        return validWordCount;
    }
    
    public HashMap<String,Integer> breakForLanguage(String encrypted, HashSet<String> dict){
        int validKeyLen = 0;
        int maxWrdCnt = 0;
        HashMap<String, Integer> decrypDtls = new HashMap<String, Integer>();
        char mstCmn = mostCommonCharIn(dict); 
         for(int keyLen=1;keyLen<=100;keyLen++){
            int[] dkey = tryKeyLength(encrypted,keyLen,mstCmn);
            VigenereCipher vc = new VigenereCipher(dkey);
            String dcrypt = vc.decrypt(encrypted);
            int validWordCount = countWords(dcrypt,dict);
            if(validWordCount > maxWrdCnt){
                maxWrdCnt = validWordCount;
                validKeyLen = keyLen;
            }
        }
        
        decrypDtls.put("WrdCnt",maxWrdCnt);
        decrypDtls.put("KeyLen",validKeyLen);
        
//             int[] dkeyFinal = tryKeyLength(encrypted,validKeyLen,'e');
//             VigenereCipher vc = new VigenereCipher(dkeyFinal);
//             String dcrypt = vc.decrypt(encrypted);
//             System.out.println("The max word count is: "+maxWrdCnt);
//             System.out.println("The key Length is : "+dkeyFinal.length);
//             System.out.println("The final Key is");
//             for(int i=0;i<dkeyFinal.length;i++){
//                 System.out.println(dkeyFinal[i]);
//             }
//             System.out.println("_________________");
//             VigenereCipher vcf = new VigenereCipher(dkeyFinal);
//             String dcryptf = vcf.decrypt(encrypted);
            return decrypDtls;
    }
    
    public char mostCommonCharIn(HashSet<String> dict){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts  = new int[26];
        int maxIdx = 0;
        int maxCnt = 0;
        for(String s : dict){
            StringBuilder sb = new StringBuilder(s);
            for(int k=0;k<sb.length();k++){
                int idx = alphabet.indexOf(Character.toLowerCase(sb.charAt(k)));
                if(idx!=-1){
                    counts[idx]+=1;
                }
            }
        }
        for(int i =0;i<counts.length;i++){
             if(counts[i]> maxCnt){
                maxCnt = counts[i];
                maxIdx = i;
            }
        }
        return alphabet.charAt(maxIdx);
    }
    
    public HashMap<String, HashSet<String>> makeLangDict(){
        HashMap<String, HashSet<String>> langDict = new  HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File fl : dr.selectedFiles()){
            String language = fl.getName();
            FileResource fr = new FileResource(fl);
            HashSet<String> dict = readDictionary(fr);
            langDict.put(language,dict);
        }
        return langDict;
    }
    
 
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        HashMap<String, HashMap<String,Integer>> langDtls = new HashMap<String,HashMap<String,Integer>>();
        int maxWrdCnt = 0;
        int keyLenFinal = 0;
        String language = null;
        for(String lang : languages.keySet()){
            HashSet<String> dict = languages.get(lang);
            HashMap<String,Integer> thisLangDtl = breakForLanguage(encrypted, dict);
            langDtls.put(lang,thisLangDtl);
        }
        //find language with most valid words and get key length
        for(String lang : langDtls.keySet()){
            HashMap<String,Integer> wrdCnt  = langDtls.get(lang);
            int vldWrdCnt = wrdCnt.get("WrdCnt");
            if(vldWrdCnt> maxWrdCnt){
                maxWrdCnt = vldWrdCnt;
                language = lang;
                keyLenFinal = wrdCnt.get("KeyLen");
            }            
        }
        char mstCmn = mostCommonCharIn(languages.get(language));
        int[] dkeyFinal = tryKeyLength(encrypted,keyLenFinal,mstCmn);
        VigenereCipher vc = new VigenereCipher(dkeyFinal);
        String dcrypt = vc.decrypt(encrypted);
        System.out.println("The detected language :"+language);
        System.out.println("The most common character  is : "+mstCmn);
        System.out.println("The length of the key :"+keyLenFinal);
        System.out.println("The lmax valid word count :"+maxWrdCnt);
        System.out.println("The decrypted string---> "+dcrypt.substring(0,100));
        //for(int i=0;i<dkeyFinal.length;i++){
                 //System.out.println(dkeyFinal[i]);
             //}        
    }
//     public void testSliceStr(){
//         System.out.println(sliceString("abcdefghijklm", 0, 3));
//         System.out.println(sliceString("abcdefghijklm", 1, 3));
//           System.out.println(sliceString("abcdefghijklm", 2, 3));
//         System.out.println(sliceString("abcdefghijklm", 0,4));
//          System.out.println(sliceString("abcdefghijklm", 1, 4));
//         System.out.println(sliceString("abcdefghijklm", 2, 4));
//           System.out.println(sliceString("abcdefghijklm", 3,4));
//         System.out.println(sliceString("abcdefghijklm", 0, 5));
//         System.out.println(sliceString("abcdefghijklm", 1, 5));
//         System.out.println(sliceString("abcdefghijklm", 2, 5));
//         System.out.println(sliceString("abcdefghijklm", 3, 5));
//         System.out.println(sliceString("abcdefghijklm", 4, 5));
//     }

//     public void testTryKey(){
//     FileResource fr = new FileResource();
//     String encrypted = fr.asString();
//     String key = "flute";
//     int[] dkey = tryKeyLength(encrypted, key.length(),'e');
//     for(int i=0;i<dkey.length;i++){
//     System.out.println(dkey[i]);
//     }
//  }
    
//         public void testCmnChar(){
//             FileResource fr = new FileResource();
//             HashSet<String> dict = readDictionary(fr);
//             char mstCmn = mostCommonCharIn(dict);
//             System.out.println("The most common Character is : "+mstCmn);            
//         }

//    public void testMakeLangDict(){
//         HashMap<String, HashSet<String>> dict = makeLangDict();
//         for(String key : dict.keySet()){
//             System.out.println(key);
//         }
//     }



}
