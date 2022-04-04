public class P1 {
	static int swaps=0;
	int sort(int arr[])
    {
        int n = arr.length;
        
        for (int i = 1; i < n; ++i) 
        {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) 
            {
                arr[j + 1] = arr[j];
                j = j - 1;
                swaps++;
            }
            arr[j + 1] = key;
            
        }
        return swaps;
    }
	public static void main(String[] args) {
		int arr[] = { 2, 1, 3, 2, 2 };
		P1 is=new P1();
		is.sort(arr);
		int swaap=is.sort(arr);
		int n=arr.length;
		for(int i=0;i<n;i++)
		{
			System.out.print(arr[i]+ " ");			
		}
		System.out.println();
		System.out.println("Number of swaps "+ swaap);
	}

}
