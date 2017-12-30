
/***
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class part1 {

    public void countryInfo(CSVParser parser,String Country){
        int found =0;
     for (CSVRecord record:parser){
         
         String cntry = record.get("Country");
         //System.out.println(cntry);
         
        if(cntry.contains(Country)){
            found=1;
            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");
            System.out.println(Country+": "+exports+" :"+value);
            //break;
        }
        }
        if(found==0){
        System.out.println("NOT FOUND");        
        }
    
    
    }
    public void listTwoExportItems(CSVParser parser,String exportItem1,String exportItem2){
        for(CSVRecord rd:parser){
         String expItms = rd.get("Exports");
         if(expItms.contains(exportItem1)&&expItms.contains(exportItem2)){
             System.out.println(rd.get("Country"));            
            }
        }
    
    
    }
    
    public void numberOfExporters(CSVParser parser, String exportItem){
        int expCnt =0;
        for(CSVRecord rd:parser){
            String exports = rd.get("Exports");
            if(exports.contains(exportItem)){expCnt+=1;}
        }
        System.out.println("Number of exporters : "+expCnt);
    }
    public void bigExporters(CSVParser parser,String dollars){
        for(CSVRecord rd:parser){
            if(rd.get("Value (dollars)").length()>dollars.length()){
                System.out.println(rd.get("Country")+" "+rd.get("Value (dollars)"));
            }
        
        }
    
    
    }
    public void testcsv(){
    FileResource file = new FileResource();
    CSVParser parser = file.getCSVParser();
   // for(CSVRecord rd:parser){System.out.println(rd);}
   // }
   //countryInfo(parser,"Nauru");
    //countryInfo(parser,"Germany");
    //countryInfo(parser,"dindigul");
   //listTwoExportItems(parser,"cotton","flowers");
    //numberOfExporters(parser,"cocoa");
    bigExporters(parser,"$999,999,999,999");
 
}
}
