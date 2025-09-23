package org.example;

public class Fibonacci {
    public static void printFibonacci(int n) {
        int a=0, b=1;
        System.out.println(a + " " +b);
        for (int i=2; i<n; i++) {
            int c = a + b;
            System.out.println(""+c);
            a=b;
            b=c;
        }
    }

    public static void main(String[] args) {
        printFibonacci(10);
    }
}
