
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
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
    
    public void printAllGenes(String dna){
        int ocur =0;
        while (true){
        String Gene = findGene(dna);
        if(!Gene.isEmpty()){
        System.out.println(Gene);
        dna = dna.substring(dna.indexOf(Gene)+Gene.length());
        //System.out.println("new dna"+dna);
        ocur+=1;
        }
        if(Gene.isEmpty()){
        break;
        }
           
        }
        System.out.println("The number of Genes found is : "+ocur);
    
     
    
    }

    public void testStopCodon(){
    System.out.println(findStopCodon("ATGGGSAAATAA",0,"TAA"));
    System.out.println(findStopCodon("ATGGGAAATAA",0,"TAA"));
    }
    
    public void testFindGene(){
    System.out.println(findGene("xxxATGXXXXXXTAA"));
    System.out.println(findGene("xxxATGYYYTAG"));
    System.out.println(findGene("xxxATGZZZTGA"));
    System.out.println(findGene("xxxATGZZGA"));
    System.out.println(findGene("xxxTGZZGA"));  
    System.out.println(findGene("xxxATGSSSTGAZZZTAGAAAAAATGA"));
    }
    
    public void testPrintallGenes(){
    printAllGenes("xxxATGXXXXXXTAAxxxATGYYYYYYTAGxxxATGZZZZZZTGAxxxATZZZZZZTAAxxxATZZZZZZTAxxxATGZZZTAA");
    printAllGenes("ATGTAAGATGCCCTAGT");
    }
}
