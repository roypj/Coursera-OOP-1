
/**
 * Write a description of part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part2 {
    
    public int howMany(String a, String b){
    int occur = 0;
        while(true){
        int idx = b.indexOf(a);
            if(idx==-1){
                    break;
                }
            else{
            b = b.substring(idx+a.length());
            occur +=1;
            
            }
        
        }
        return occur;
    
    }
    
    public void testhowMany(){
    System.out.println(howMany("AA","ATAAAA"));
    System.out.println(howMany("GAA", "GAA"));
    System.out.println(howMany("GAA", "ABC"));
    System.out.println(howMany("", "ABC"));
    
    }

}
