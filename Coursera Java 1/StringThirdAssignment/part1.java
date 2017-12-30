
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
public class part1 {
    
    public int findStopCodon(String dna, int startIdx, String stopCodon){
        int currIdx = dna.indexOf(stopCodon,startIdx+3);
        while(currIdx!=-1){
        if((currIdx-startIdx)%3==0){
            return currIdx;
        }
        else{
            currIdx = dna.indexOf(stopCodon,currIdx+1);
        }
        }
        return dna.length();
    
    }
    
    public String findGene(String dna){
    int startIdx = dna.indexOf("ATG");
    //System.out.println(dna);
    //System.out.println(startIdx);
    if (startIdx==-1){
    return "";
    }
    int atgIdx = findStopCodon(dna,startIdx,"TAA");
    //System.out.println(atgIdx);
    int tagIdx = findStopCodon(dna,startIdx,"TAG");
    //System.out.println(tagIdx);
    int tgaIdx = findStopCodon(dna,startIdx,"TGA");
    //System.out.println(tagIdx);
    
    int minIdx = Math.min(Math.max(0,atgIdx),Math.min(Math.max(0,tagIdx),Math.max(0,tgaIdx)));
     //System.out.println(minIdx);
    if (minIdx==0||minIdx==dna.length()){
    return "";
    }else{
    
    return dna.substring(startIdx,minIdx+3);
    }
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
       while (true){
        String Gene = findGene(dna);
        if(!Gene.isEmpty()){
        //System.out.println(Gene);
        geneList.add(Gene);
        dna = dna.substring(dna.indexOf(Gene)+Gene.length());
        //System.out.println("new dna"+dna);
        }
        if(Gene.isEmpty()){
        break;
        }
           
        }
        return geneList;    
    
    }
    
    public int countOfChar(String a, String b){
        int aCount = 0;
        int aStrt = b.indexOf(a);
        while(true){
            if(aStrt==-1){
            break;
            }
            else{
            aCount+=1;
            aStrt = b.indexOf(a,aStrt+a.length());
            }
        
        }
        return aCount;
    }
    
    public float cgRatio(String dna){
        float cCount = countOfChar("C",dna);
        float gCount = countOfChar("G",dna);
  
     return (float) ((cCount+gCount)/dna.length());   
    }
    
    public void processGenes(StorageResource geneList){
        int lenCount=0;
        int cgCount=0;
        int threshLen=60;
        double threshCG=0.35;
        int maxLen=0;
        for (String s : geneList.data()){
            
            int len = s.length();
            float cg = cgRatio(s);
            if(len>maxLen){
                maxLen=len;
            }
            if(len>threshLen){
            lenCount+=1;
            System.out.println(s);
        }
             if(cg>threshCG){
            cgCount+=1;
            //System.out.println(s);
        }
        
        
        }   
        
        System.out.println("The number of strings with > 60 length : "+lenCount);
        System.out.println("The number genes with > 0.35 CG : "+cgCount);
        System.out.println("The max length of a gene in this sample : "+maxLen);
       
    
    }

    public void testGetAllGenes(){
    String dna = "xxxATGXXXXXXTAAxxxATGYYYYYYTAGxxxATGZZZZZZTGAxxxATZZZZZZTAAxxxATZZZZZZTAxxxATGZZZTAA";
    StorageResource lst = getAllGenes(dna);
    for (String s:lst.data()){
    System.out.println(s);
    }   
   
    }
    public void testCount()
    {
    System.out.println(countOfChar("C","CC"));
    System.out.println(countOfChar("C","VVVVV"));
    System.out.println(countOfChar("C","AAXXCCDDSSCC"));
    }
    
    public void testCG(){
    System.out.println(cgRatio("ATGCCATAG"));
    }
    
    public void testProcessGene(){
         StorageResource geneList = new StorageResource();
//         geneList.add("ATGCCGGTAG");
//         geneList.add("ATGCCGGGTAG");
//         geneList.add("ATGCCGGGTAG");
//         geneList.add("ATGCTAG");
//         geneList.add("ATGXXXXXXXXXXXXCTAG");
        FileResource fa = new FileResource();
        String dna = fa.asString();
        //System.out.println(countOfChar("CTG",dna));
        //System.out.println(dna);
        geneList = getAllGenes(dna);
        System.out.println("number of genes in file "+geneList.size());
       // for (String s : geneList.data()){System.out.println(s);}
        
       processGenes(geneList);
    
    }
 
}