
import java.util.Scanner;
public class P3 {
	static int mergeSort(int arr[], int array_size) 
	{ 
		int temp[] = new int[array_size]; 
		return tmergeSort(arr, temp, 0, array_size - 1); 
	} 

	static int tmergeSort(int arr[], int temp[], int left, int right) 
	{ 
		int mid, inv_count = 0; 
		if (right > left) { 
			mid = (right + left) / 2; 

			inv_count = tmergeSort(arr, temp, left, mid); 
			inv_count += tmergeSort(arr, temp, mid + 1, right); 

			inv_count += merge(arr, temp, left, mid + 1, right); 
		} 
		return inv_count; 
	} 

	static int merge(int arr[], int temp[], int left, int mid, int right) 
	{ 
		int i, j, k; 
		int inv_count = 0; 

		i = left; 
		j = mid; 
		k = left; 
		while ((i <= mid - 1) && (j <= right)) { 
			if (arr[i] <= arr[j]) { 
				temp[k++] = arr[i++]; 
			} 
			else { 
				temp[k++] = arr[j++]; 
				inv_count = inv_count + (mid - i); 
			} 
		} 

		while (i <= mid - 1) 
			temp[k++] = arr[i++]; 

		while (j <= right) 
			temp[k++] = arr[j++]; 

		for (i = left; i <= right; i++) 
			arr[i] = temp[i]; 

		return inv_count; 
	} 

	public static void main(String[] args) 
	{     int n;
            Scanner sc=new Scanner(System.in);  
            System.out.print("Enter the number of elements you want to store: ");  
            n=sc.nextInt();  
            int[] arr;  
            arr = new int[100];
            System.out.println("Enter the elements of the array: ");  
            for(int i=0; i<n; i++)
            {
             arr[i]=sc.nextInt();  
            }
		System.out.println("Inversions_count = " + mergeSort(arr, n)); 
	} 
} 
