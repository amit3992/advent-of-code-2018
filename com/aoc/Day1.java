package com.aoc;

import java.util.*;

import java.io.*;

public class Day1 {

    long currentF;
    long change;
    long resultF;
    Set<Long> set;
    List<Long> list;

    Day1() {
        currentF = 0;
        change = 0;
        resultF = 0;
        set = new HashSet<>();
        list = new ArrayList<>();
    }

    public long getResultF() {
        return resultF;
    }

    public void adjustChange(long change) {
        System.out.println("Current => " + currentF);
        System.out.println("Change => " + change);
        resultF = currentF + change;
        System.out.println("Result => " + resultF);

        if(!set.contains(resultF)) {
            set.add(resultF);
        } else {
            System.out.println("FIRST DUPLICATE: " + resultF);
            return;
        }
        currentF = resultF;
    }

    public long countResult() {
        for(Long change : list) {
//            System.out.println("Current => " + currentF);
//            System.out.println("Change => " + change);
            resultF = currentF + change;
//            System.out.println("Result => " + resultF);
            currentF = resultF;
        }

        return resultF;
    }

    public long firstFrequency() {
        boolean found = false;

        do {
            for(Long change : list) {
//                System.out.println("Current => " + currentF);
//                System.out.println("Change => " + change);
                resultF = currentF + change;
                if(!set.contains(resultF)) {
                    set.add(resultF);
                } else {
//                    System.out.println("VOILAAAA");
                    return resultF;
                }

//                System.out.println("Result => " + resultF);
                currentF = resultF;
            }
        } while (!found);

        return Long.MAX_VALUE;
    }

    public List<Long> buildList(String url) {

        File file = new File(url);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch(FileNotFoundException f) {

        }

        String st;
        try {
            while ((st = br.readLine()) != null) {
                long val = Long.parseLong(st);
                list.add(val);
            }

        } catch(IOException ex) {
            System.out.println("Exception => " + ex.getMessage());
        }

        return list;
    }

    public static void main(String[] args) {
        Day1 d1 = new Day1();
        d1.buildList("input/Day1.txt");
        System.out.println("First => " + d1.countResult());
        System.out.println("Second => " + d1.firstFrequency());
    }
}
