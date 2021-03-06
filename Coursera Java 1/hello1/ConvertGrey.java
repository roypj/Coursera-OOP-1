
/**
 * Write a description of ConvertGrey here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
public class ConvertGrey {
    public ImageResource makeGrey(ImageResource flnm){
        ImageResource img = new ImageResource(flnm);
        for(Pixel px:img.pixels()){
            //int x = px.getX();
            //int y = px.getY();
            int avgVal = (px.getRed()+px.getBlue()+px.getGreen())/3;
            px.setRed(avgVal);
            px.setGreen(avgVal);
            px.setBlue(avgVal);        
        }
        return img;
    }
    public void testgrey(){
    DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource img = new ImageResource(f);
            ImageResource opImg = makeGrey(img);
            //opImg.draw();    
            String oldnm = img.getFileName();
            String newnm = "Grey - "+oldnm;
            opImg.setFileName(newnm);
            opImg.save();
        }
    }

}



