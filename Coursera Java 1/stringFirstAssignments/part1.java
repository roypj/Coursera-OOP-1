
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part1 {
    public String findSimpleGene(String dna){
        int startidx = dna.indexOf("ATG");
        if (startidx==-1){return "";}
        int endidx = dna.indexOf("TAA",startidx+3);
        if(endidx==-1){return "";}
        String dnastr = dna.substring(startidx,endidx+3);
        return dnastr;  
    
    }
    public void testDna(){
    String result = findSimpleGene("AATGCGTAATATGGT");
    System.out.println("dna is AATGCGTAATATGGT");
    System.out.println("the dna is"+result);
    String result1 = findSimpleGene("ACGTAATATTGT");
    System.out.println(result1);
    String result2 = findSimpleGene("AATGCGTATGGT");
    System.out.println(result2);
    String result3 = findSimpleGene("AATGCGATGTAATATGGT");
    System.out.println(result3);
    }

}
