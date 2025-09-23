package org.example;

public class VowelsConsonants {
    public static void main(String[] args) {
        String text = "Selenium Java Automation";
        text = text.toLowerCase();
        int vowels = 0, consonants = 0;
        for (char c:text.toCharArray()) {
            if ("aeiou".indexOf(c)!=-1) {
                vowels++;
            }
            else if (Character.isLetter(c)){
                consonants++;

            }
        }
        System.out.println("Vowels: " +vowels);
        System.out.println("Consonants: " +consonants);
    }
}
