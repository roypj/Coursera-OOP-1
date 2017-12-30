
/**
 * Write a description of DirectorFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String[] myDirectors;
    
    public DirectorsFilter(String Directors){
        myDirectors = Directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id){
        String currDir = MovieDatabase.getDirector(id).toLowerCase();
        for(int i = 0 ; i <myDirectors.length;i++){
            if(currDir.indexOf(myDirectors[i].toLowerCase().trim())!=-1){
                return true;
            }
        }
        return false;
    }

}
