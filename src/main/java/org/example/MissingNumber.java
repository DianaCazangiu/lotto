package org.example;

public class MissingNumber {
    public static int findMissing(int[] arr,int n) {
        int expectedSum = n * (n+1)/2;
        int actualSum = 0;
        for (int num:arr) {
            actualSum +=num;

        }
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,7,8,9};
        System.out.println("Numar lipsa: " + findMissing(arr,9));
    }
}
