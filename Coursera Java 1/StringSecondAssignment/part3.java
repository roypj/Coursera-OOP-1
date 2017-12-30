
/**
 * Write a description of part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part3 {

    public void test(){
    String dna = "CTGCCTGCATGATCGTA";
int pos = dna.indexOf("TG");
System.out.println("initial pos "+pos);
int count = 0;
while (pos >= 0) {
  count = count + 1;
  pos = dna.indexOf("TG",pos);
  System.out.println("next pos "+pos);
}
System.out.println(count);
    }
}
