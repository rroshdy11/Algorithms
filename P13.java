import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class P13 {
	
	public static void main(String[] args) {
		new Task().solve();
	}
}


class Task {
	Scanner in = new Scanner(new BufferedInputStream(System.in)) ;
	PrintWriter out = new PrintWriter(System.out);
	
	int[] p ;
	int[][] dp ;
	int[][] father ;
	
	void MatrixChainOrder(int l , int r){
		if(l == r){
			out.print("A" + l) ;
			return ;
		}
		int f = father[l][r] ;
		out.print("(");
		MatrixChainOrder(l, f);
		out.print(" x ");
		MatrixChainOrder(f+1, r);
		out.print(")");
	}
	
	void solve() {
		int cas = 1 ;
		while(true){
                        System.out.print("Enter Number of Matrices:");
			int n = in.nextInt() ;
		    if(n == 0){
		    	break ;
		    }
		    p = new int[n+1] ;
		    dp = new int[n+2][n+2] ;
		    father = new int[n+1][n+1] ;
		    for(int i = 1 ; i <= n ; i++){
		    	p[i-1] = in.nextInt() ;
		    	p[i] = in.nextInt() ;
		    }
		    
		    for(int i = 1 ; i <= n+1 ; i++){
		    	dp[i][i] = 0 ;
		    }
		    for(int d = 2 ; d <= n ; d++){
		    	for(int i = 1 ; i + d - 1 <= n ; i++){
		    		int j = i + d - 1 ;
		    		dp[i][j] = Integer.MAX_VALUE ;
		    		for(int k = i ; k <= j ; k++){
		    			int sum  = dp[i][k] + dp[k+1][j] + p[i-1] * p[k] * p[j] ;
		    		    if(sum < dp[i][j]){
		    		    	dp[i][j] = sum ; 
		    		    	father[i][j] = k ;
		    		    }
		    		}
		    	}
		    }
		    out.print("Case " + cas++ + ": ") ;
		    MatrixChainOrder(1, n);
		    out.println() ;
		    out.flush();
		}
		out.flush(); 
	}
}