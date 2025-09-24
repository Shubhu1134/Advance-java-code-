import java.util.*;

class Test{
    public static void main(String args []){
        List<String> names = new ArrayList<>(Arrays.asList("a", "b", "c","d"));

        Collections.reverse(names);

        System.out.println(" Reversed names :"+ names );
    }
}