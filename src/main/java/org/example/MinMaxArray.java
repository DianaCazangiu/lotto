package org.example;

public class MinMaxArray {
    public static void main(String[] args) {
        int[] arr={10,56,2,7,29,31,4,89,126,70};
        int min = arr[0], max = arr[0];

        for (int num:arr) {
            if (num<min) {
                min=num;
            }
            if (num>max) {
                max=num;
            }
        }
        System.out.println("Min: " +min);
        System.out.println("Max: "+max);
    }


}
