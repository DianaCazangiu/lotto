package org.example;

public class ReverseWord {
    public static void main (String[] args) {
        String sentence ="Java is cool";
        String[] words = sentence.split("");
        StringBuilder result = new StringBuilder();
        for (String word:words) {
            result.append(new StringBuilder(word).reverse().append(" "));
        }
        System.out.println(result.toString().trim());
    }
}
