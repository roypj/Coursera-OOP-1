
/**
 * Write a description of debugging1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class debugging1 {
    
    public void findAbc(String input) {
        //System.out.println(input.length());
    int index = input.indexOf("abc");//0
    //System.out.println("index - "+index);
    //System.out.println("index - 1 :"+index+1);
    //System.out.println("index -4 : "+index+4);
    while (true) {
        if (index == -1||index>=input.length()-3) {
            break;
        }
        System.out.println("index is - "+index);                           
        String found = input.substring(index+1, index+4);
        System.out.println(found);
       
        System.out.println(input.substring(index+4));
        index = input.indexOf("abc", index+3);
        //System.out.println("--------------------------------");
       System.out.println("new index - "+index);
    }
}
   public void test() {
    //no code yet
    //       0123
    //findAbc("abcabca");
    //         0123456
    findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
}

}
