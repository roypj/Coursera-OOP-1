
/**
 * Write a description of part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part3 {
    public boolean twoOccurrences(String a,String b){
        int fstIdx = b.indexOf(a);
        //if(fstIdx!=-1){
            int scndIdx = b.indexOf(a,fstIdx+a.length());
        //}
        if (scndIdx!=-1){
            return true;
        }else{
        return false;
        }
    
    }
    public String endsWith(String a , String b){
        int strtIdx = b.indexOf(a);
        if(strtIdx!=-1){
            return b.substring(strtIdx+a.length());
        }
         else{
             return b;
            }
        }
    
    
    
  public void test(){
    System.out.println(twoOccurrences("a","banana"));
    System.out.println(twoOccurrences("by", "A story by Abby Long"));
    System.out.println(twoOccurrences("atg", "atgbnmsdfv"));
    System.out.println(endsWith("atg", "atgbnmsdfv"));
     System.out.println(endsWith("an", "banana"));
       System.out.println(endsWith("zoo", "forest"));
    }

}
