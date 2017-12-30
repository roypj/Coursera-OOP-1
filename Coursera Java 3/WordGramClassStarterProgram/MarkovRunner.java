
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        MarkovWord mw = new MarkovWord(5);
        runModel(mw,st,100,844);
    } 
    
    public void testHashmap() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
       // String st = "this is a test yes this is really a test";
       // String st = "this is a test yes this is really a test yes a test this is wow";
        st = st.replace('\n', ' '); 
       EfficientMarkovWord mw = new EfficientMarkovWord(2);
        //mw.setTraining(st);
        //mw.setRandom(643);
        runModel(mw,st,100,65);
    } 
    
    public void compareMethods(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        long mwstart = System.nanoTime();
        MarkovWord mw = new MarkovWord(2);
        runModel(mw,st,100,42);
        long mwEnd = System.nanoTime()-mwstart;
        long effst = System.nanoTime();
        EfficientMarkovWord efmw = new EfficientMarkovWord(2);
         runModel(efmw,st,100,42);
         long effEn = System.nanoTime()-effst;
         System.out.println ("not efficient time = "+ mwEnd);
          System.out.println (" efficient time = "+ effEn);
          System.out.println(mwEnd/effEn);
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
