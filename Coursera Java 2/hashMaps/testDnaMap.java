
/**
 * Write a description of testDnaMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class testDnaMap {
        public void testCodonCounts(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dnaReader reader = new dnaReader();
        reader.buildCodonMap(0,dna);
        //reader.buildCodonMap(1,dna);
        //reader.buildCodonMap(2,dna);
        reader.printCodons(7,7);
        String mstCmn = reader.getMostCommonCodon();
        //System.out.println("The most common codon is : "+mstCmn);
        int cmnCnt = reader.getCount(mstCmn);
       // System.out.println("The number of occurrences of most common :"+cmnCnt);
        
    }
    

}
