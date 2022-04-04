/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class P11 {
 
             
    public static void backtrack(int []f,int []integers)
    {
        
        int i=f.length-1;
        ArrayList used=new ArrayList();
        while(i>0)
        {
            int used_indx=-1;
            for(int c=f.length-1;c>=0;c--)
            {
                
                if(f[i]==f[c])
                {
                    used_indx=c;
               
                }
            }
            if(used_indx!=-1)
            {
              if(integers[used_indx]!=0){
              used.add(integers[used_indx]);
              }
              i=used_indx;
              f[i]=f[i]-integers[i];
              
              
                
            }
            else
            {
                used.add(integers[i]);
                f[i]=f[i]-integers[i];
            }
        }
        System.err.println("");
        System.out.println("subset with maximum sum{"+used+"}");
    }
    public static int maximum(int[] integers,int size)
    {
        
        if(integers.length==0){
        return 0;
        }
        int[] f=new int [size+1];
        for(int i=0;i<size+1;i++)
        {
            if(i==0)
            {
                f[i]=0;
            }
            else if(i==1)
            {
               f[i]=integers[i];
            }
            else
            {
                f[i]=max(f[i-2]+integers[i],f[i-1]);
            }
        }
        int maximum_sum=f[size];
        backtrack(f, integers);
        if(maximum_sum<=0)
        {
            return 0;
        }
        else{
            return maximum_sum;
        }
        
        
        
    }
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("Please Enter the Array Size :");
        int size=input.nextInt();
        int []integers=new int[size+1];
        integers[0]=0;
         System.out.println("Please Enter the Array elements :");
        for(int i=1;i<size+1;i++)
        {
            integers[i]=input.nextInt();
        }
        int max_sum=maximum(integers, size);
        System.out.println("the maximum sum is:"+max_sum);
        
    }
    
}
