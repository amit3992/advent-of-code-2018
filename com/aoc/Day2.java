package com.aoc;

import java.io.*;
import java.util.*;

//abcdef
//        bababc
//        abbcde
//        abcccd
//        aabcdd
//        abcdee
//        ababab

public class Day2 {

    List<String> list;
    long nTwos;
    long nThrees;
    long result;

    Day2() {
        list = new ArrayList<>();
        nTwos = 0;
        nThrees = 0;
        result = 0;
    }

    public List<String> buildList(String url) {

        File file = new File(url);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch(FileNotFoundException f) {

        }

        String st;
        try {
            while ((st = br.readLine()) != null) {
                list.add(st);
            }

        } catch(IOException ex) {
            System.out.println("Exception => " + ex.getMessage());
        }

        return list;
    }

    public void process() {
        if(this.list == null || this.list.size() == 0) {
            return;
        }

        for(String s : list) {
            processWord(s);
        }
    }

    private boolean isOneDifference(String a, String b) {

        if(a == b) {
            return false;
        }

        int l = a.length();
        int count = 0;

        for(int i = 0; i < l; i++) {
            if (a.charAt(i) != b.charAt(i))
                count++;
            if (count > 1)
                return false;
        }

        return true;
    }

    public String commonLetters() {
        for(String a : list) {
            for(String b : list) {
                if(isOneDifference(a, b)) {
                    System.out.println("A: " + a);
                    System.out.println("B: " + b);
                    return getLetters(a, b);
                }
            }
        }

        return "X";
    }

    private String getLetters(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int l = a.length();

        for(int i = 0; i < l; i++) {
            if(a.charAt(i) == b.charAt(i)) {
                sb.append(a.charAt(i));
            }
        }

        return sb.toString();
    }



    public long getResult() {
        process();
        return nTwos * nThrees;
    }

    private void processWord(String word) {
        HashMap<Character, Integer> charMap = new HashMap<>();

        boolean found2 = false;
        boolean found3 = false;

        /* Build word map */
        for(char c : word.toCharArray()) {
            if(charMap.containsKey(c)) {
                int count = charMap.get(c);
                count += 1;
                charMap.put(c, count);
            } else {
                charMap.put(c, 1);
            }
        }

        /* Count twos and 3s */
        for(char c : charMap.keySet()) {
            if(charMap.get(c) == 2 && !found2) {
                nTwos += 1;
                found2 = true;
            }

            if(charMap.get(c) == 3 && !found3) {
                nThrees += 1;
                found3 = true;
            }
        }

    }

    public static void main(String [] args) {
        Day2 d2 = new Day2();
        d2.buildList("input/Day2.txt");
        System.out.println("First => " + d2.getResult());
        System.out.println("=============================================================");
        System.out.println("Second => " + d2.commonLetters());

    }
}
