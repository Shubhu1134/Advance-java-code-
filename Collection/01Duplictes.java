import java.util.*;

class RemoveDupliates{
    public static void main(String args[]){
        List<Integer>numbers = Arrays.asList(1,2,3,2,4,4,5);

        Set<Integer>unique = new HashSet<>(numbers);

        System.out.println("WITHOUT DUPLICATES "+unique);
    }
}

// import java.util.*;

//  class RemoveDuplicates {
//     public static void main(String[] args) {
//         List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);

//         Set<Integer> unique = new HashSet<>(numbers);
//         System.out.println("Without duplicates: " + unique);
//     }
// }
