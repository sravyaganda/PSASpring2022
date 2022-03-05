package edu.neu.coe.info6205.union_find;
import java.util.*;

public class UF_HWQUPC_Implementation {

    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();

        int avg=count(n);
        System.out.println("Average pairs for n "+ n + " is "+ avg );

    }

     public static int count(int n)
     {
         Queue<Integer> q=new LinkedList<Integer>();
         for(int i=1;i<=10;i++) {
             q.add(createPairs(n));
         }
         int avg=0;
         while(!q.isEmpty())
         {
             avg+=q.poll();
         }
         return avg/10;
     }

       public static int createPairs(int N)
       {
           UF_HWQUPC uf=new UF_HWQUPC(N,true);
           Random rand=new Random();
           int m=0;
           while(uf.components()!=1)
           {
               int r1=rand.nextInt(N);
               int r2=rand.nextInt(N);
               uf.connect(r1,r2);
               m++;
           }
           return m;
       }
    }

