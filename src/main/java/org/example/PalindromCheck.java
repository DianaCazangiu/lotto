package org.example;

public class PalindromCheck {
    public static boolean isPalindrome(String word) {
       word = word.toLowerCase().replaceAll("\\s+","");
       int i = 0, j = word.length()-1;
       while (i<j) {
           if (word.charAt(i)!= word.charAt(j)) {
               return false;
           }
           i++;
           j--;
       }
       return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("Ana"));
    }
}
