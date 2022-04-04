import java.util.ArrayList;

public class P16 {
    public static void printDuplicates(int[] arr){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            if(!result.contains(arr[i])){
                for(int j=0; j<arr.length; j++){
                    if(arr[i] == arr[j]){
                        result.add(arr[j]);
                    }
                }
            }
        }
        System.out.println(result);
    }
    public static void main(String[] args) {
        int[] arr = {5, 4, 5, 5, 3, 1, 2, 2, 4};
        printDuplicates(arr);
    }
}
