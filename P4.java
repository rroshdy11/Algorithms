

import java.util.Scanner;

public class P4 {
private static int[] arr = new int[5010];

public static int minOperations(int l, int r)
{
	if (l > r)
	{
		return 0;
	}

	else if (l == r && arr[l] != 0)
	{
		return 1;
	}

	else if (l == r && arr[l] == 0)
	{
		return 0;
	}

	int ans = (r - l) + 1;

	int mn_indx =Integer.MIN_VALUE;

	int mx = Integer.MAX_VALUE;

	for (int i = l;i <= r;i++)
	{
		if (mx > arr[i])
		{
		mx = arr[i];
		mn_indx = i;
		}
	}
	int mn = arr[mn_indx];

	for (int i = l;i <= r;i++)
	{
		arr[i] = arr[i] - mn;
	}

	int l1 = mn_indx - 1;
	int r1 = mn_indx + 1;

	ans = Math.min(ans,minOperations(l, l1) + minOperations(r1, r) + mn);

	return ans;
}

public static void main(String[] args)

{
      int n;
            Scanner sc=new Scanner(System.in);  
            System.out.print("Enter the number of elements you want to store: ");  
            n=sc.nextInt();  
  
            arr = new int[5010];
            System.out.println("Enter the elements of the array: ");  
            for(int i=0; i<n; i++)
            {
             arr[i]=sc.nextInt();  
            }
	
	System.out.println(minOperations(0, n));

}

    
}