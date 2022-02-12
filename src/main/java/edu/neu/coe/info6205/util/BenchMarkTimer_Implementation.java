package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BenchMarkTimer_Implementation {
    public static void main(String[] args) {


        int arrayOrder;

        for(arrayOrder = 1; arrayOrder<=4; arrayOrder++){
            System.out.println();
            if(arrayOrder ==1) {
                System.out.println("INSERTION SORT FOR ORDERED ARRAY");
            }
            else if(arrayOrder ==2) {
                System.out.println("INSERTION SORT FOR REVERSELY ORDERED ARRAY");
            }
            else if(arrayOrder ==3) {
                System.out.println("INSERTION SORT FOR PARTIALLY ORDERED ARRAY");
            }
            else {
                System.out.println("INSERTION SORT FOR RANDOMLY ORDERED ARRAY");
            }

                getArray(arrayOrder, 50000);


        }

    }
    public static void getArray(int arrayOrder,int n) {

        InsertionSort<Integer> sorter = new InsertionSort<Integer>();
        Supplier<Integer[]> orderedSupplier = () -> orderedArray(n);
        Supplier<Integer[]> reverseSupplier= () -> reversedArray(n);
        Supplier<Integer[]> partialSupplier = () -> partialOrderArray(n);
        Supplier<Integer[]> randomSupplier = () -> randomArray(n);
        String arrayName=null;
        Consumer<Integer[]> con = (t)->{sorter.sort(t, 0,t.length);
        };
        Supplier<Integer[]> sup =orderedSupplier;
        if(arrayOrder==1)
        {
            sup=orderedSupplier;
            arrayName=" Ordered Array";
        }
        else if(arrayOrder==2)
        {
            sup=reverseSupplier;
            arrayName= " Reversely Ordered Array";
        }
        else if(arrayOrder==3)
        {
            sup=partialSupplier;
            arrayName="Partially Ordered Array";
        }
        else if(arrayOrder==4)
        {
            sup=randomSupplier;
            arrayName="Randomly Ordered Array";
        }
        Benchmark_Timer<Integer[]> bt =new Benchmark_Timer<Integer[]>("Benchmarking testing for Insertion Sort ",con);
        double meantime =bt.runFromSupplier(sup,100);
        System.out.println("Insertion sort for "+ arrayName + " of size " + n + " takes a meantime of  " + meantime);

    }
    public static Integer[] reversedArray(int n)
    {
        Integer[] in = new Integer[n];
        int count =1;
        for(int i=0;i<n;i++)
        {
            in[i]=n+2-count;
            count++;
        }
        return in;
    }
    public static Integer[] orderedArray(int n)
    {
        Integer[] in = new Integer[n];
        for(int i=0;i<n;i++)
        {
            in[i]=i+1;
        }
        return in;

    }
    public static Integer[] randomArray(int n)
    {
        Random r= new Random();
        Integer[] in = new Integer[n];
        for(int i=0;i<n;i++)
        {
            in[i]=r.nextInt(n);
        }
        return in;
    }
    public static Integer[] partialOrderArray(int n) {
        Random r = new Random();
        Integer[] in = new Integer[n];
        for (int i = 0; i < n / 4; i++) {
            in[i] = i + 1;
        }
        for (int i = n / 4; i < n * 3 / 4; i++) {
            in[i] = r.nextInt(n);
        }
        for (int i = n * 3 / 4; i < n; i++) {
            in[i] = i + 1;
        }
        return in;

    }
}
