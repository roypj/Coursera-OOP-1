
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String position;
    private String phrase;
    private String Name;
    
    public PhraseFilter(String pos, String txt,String nm){
        position = pos;
        phrase = txt;
        Name = nm;
    }
    
    public boolean satisfies(QuakeEntry qe){
        if(position.toLowerCase().equals("start")){
            return qe.getInfo().startsWith(phrase);
        }else if(position.toLowerCase().equals("end")){
            return qe.getInfo().endsWith(phrase);
        }else if(position.toLowerCase().equals("any")){
            int idx = qe.getInfo().indexOf(phrase);
            if(idx!=-1){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    
    public String getName(){
        return Name;
    }

}
