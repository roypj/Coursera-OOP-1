
/**
 * Write a description of DirectoryBrowser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
public class DirectoryBrowser {
    public void browseDirectory(){
    DirectoryResource dr = new DirectoryResource();
        for (File f :dr.selectedFiles()){
            System.out.println(f);
        }
    }   

}
