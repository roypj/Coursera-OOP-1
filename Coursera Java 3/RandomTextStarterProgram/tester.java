
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class tester {
    public void testGetFollows(){
        String st = "this is a test yes this is a test.";
        FileResource fr = new FileResource();
        st = fr.asString();
        st = st.replace('\n',' ');
        /*System.out.println("Length of text "+st.length());*/
        MarkovOne markov = new MarkovOne();
        markov.setRandom(42);
        markov.setTraining(st);
       /* String text = markov.getRandomText(200);
        System.out.println(text);*/
        System.out.println(markov.getFollows("o").size());
       System.out.println(markov.getFollows("he").size());
       //System.out.println(markov.getFollows("th").size());
         /*System.out.println(markov.getFollows("e")+" "+markov.getFollows("e").size());
        System.out.println(markov.getFollows("es")+" "+markov.getFollows("es").size());*/
    
    }

}
