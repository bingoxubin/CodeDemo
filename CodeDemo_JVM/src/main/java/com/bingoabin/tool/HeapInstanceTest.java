package com.bingoabin.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
-Xmx600m -Xms600m -XX:+PrintGCDetails
*/
public class HeapInstanceTest {


    byte[] buffer = new byte[new Random().nextInt(1024*100)];

    public static void main(String[] args) {
        List<HeapInstanceTest> list = new ArrayList<HeapInstanceTest>();
        while (true){

            list.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
