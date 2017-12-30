
/**
 * Write a description of ConvertGrey here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
public class BatchInversion {
    public ImageResource invertImage(ImageResource flnm){
        ImageResource img = new ImageResource(flnm);
        for(Pixel px:img.pixels()){
              
            px.setRed(255-px.getRed());
            px.setGreen(255 - px.getGreen());
            px.setBlue(255-px.getBlue());        
        }
        return img;
    }
    public void selectAndInvert(){
    DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource img = new ImageResource(f);
            ImageResource opImg = invertImage(img);
            //opImg.draw();    
            String oldnm = img.getFileName();
            String newnm = "Inverted - "+oldnm;
            System.out.println(newnm);
            opImg.setFileName(newnm);
            opImg.save();
        }
    }

}




