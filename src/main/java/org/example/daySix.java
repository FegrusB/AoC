package org.example;

import com.sun.source.tree.IfTree;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class daySix {

    public static void main(String[] args) {

        Scanner myScanner = GetScanner.get("Day-6");
        String text = myScanner.nextLine();

        StringBuilder out = new StringBuilder();
        boolean firstPacket = false;
        boolean firstMessage = false;
        for(int i = 3; i < text.length();i++){

            HashSet<Character> set = new HashSet<>();

            set.add(text.charAt(i-3));
            set.add(text.charAt(i-2));
            set.add(text.charAt(i-1));
            set.add(text.charAt(i));

            if (set.size() == 4 && !firstPacket){
                System.out.println(i+1);
                firstPacket = true;
            }
            if(i >= 13) {
                set.add(text.charAt(i - 4));
                set.add(text.charAt(i - 5));
                set.add(text.charAt(i - 6));
                set.add(text.charAt(i - 7));
                set.add(text.charAt(i - 8));
                set.add(text.charAt(i - 9));
                set.add(text.charAt(i - 10));
                set.add(text.charAt(i - 11));
                set.add(text.charAt(i - 12));
                set.add(text.charAt(i - 13));
            }
            if (set.size() == 14 && !firstMessage){
                System.out.println(i+1);
                firstMessage = true;
            }
        }

    }

}
