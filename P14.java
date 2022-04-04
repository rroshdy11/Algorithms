import java.util.HashSet;

public class P14 {
    public static void get_pairs(int[] arr, int sum){
        HashSet<Integer> hash = new HashSet<>();
        boolean found = false;
        for (int curr : arr) {
            int remainder = sum - curr;
            if(hash.contains(remainder)){
                System.out.println(curr + " " + remainder);
                found = true;
            }
            hash.add(curr);
        }
        if(!found){
            System.out.println("No pairs were found");
        }
    }
    public static void main(String[] args) {
        int[] arr = {8, 7, 2, 5, 3, 1};
        int sum = 10;
        get_pairs(arr,sum);
    }
}
