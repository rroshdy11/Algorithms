import java.util.Arrays;
import java.util.Scanner;

public class P2 {
      static  P2 q=new P2();
    public static long  m_w , m_z;
    public static long kthSmallest(long[] arr, int k)
    {
        Arrays.sort(arr);
        return arr[k - 1];
    }
   
    public static int get_random()
    {
    	q.m_z=36969 * (q.m_z & 65535) + (q.m_z >> 16);
    	q.m_w=18000 * (q.m_w & 65535) + (q.m_w >> 16);
    	long res=((m_z << 16)+ m_w);
    	return (int) (res % 1000000000);
    }

    public static void main(String[] args)
    {
    		System.out.println("Enter N,K,m_w,m_z: ");
    		Scanner input = new Scanner(System.in);
				int N, k;
				N=input.nextInt();
				k=input.nextInt();
				q.m_w=input.nextLong();
				q.m_z=input.nextLong();
                                long []arr=new long[N];
					
				for(int i=0;i<N;i++)
				{
				  arr[i]=get_random();
                                  
				}
					
				System.out.println("K'th smallest element is " + kthSmallest(arr, k));
				
    }
}

