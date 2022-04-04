import java.util.HashSet;

public class P15 {
    public static void minRepeatingIndex(int[] arr){
        HashSet<Integer> hash = new HashSet<>();
        int index = 0;
        boolean found = false;
        for (int i=arr.length-1; i>=0; i--) {
            if(hash.contains(arr[i])){
                index = i;
                found = true;
            }else{
                hash.add(arr[i]);
            }
        }
        if(found)
            System.out.println("The minimum index of the repeating element is " + index);
        else
            System.out.println("Invalid Input");
    }
    public static void main(String[] args) {
        int[] arr = {5, 1, 10, 1, 3, 6, 4};
        minRepeatingIndex(arr);
    }
}
