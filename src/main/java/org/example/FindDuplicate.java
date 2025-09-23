package org.example;

import java.util.HashSet;

public class FindDuplicate {
    public static void main(String[] args) {
        int[] arr = {1,3,5,2,5,7,9,2};
       HashSet<Integer> set = new HashSet<>();
       for (int num:arr) {
           if (!set.add(num)) {
               System.out.println("Duplicat gasit:  " + num);
           }

         }
        }
    }

