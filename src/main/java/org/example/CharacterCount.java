package org.example;

import java.util.HashMap;

public class CharacterCount {

    public static void CountChars(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c:text.toCharArray()) {
            map.put(c,map.getOrDefault(c,0)+1);
        }
        System.out.println(map);
    }

    public static void main(String[] args) {
        CountChars("automation");
    }
}
