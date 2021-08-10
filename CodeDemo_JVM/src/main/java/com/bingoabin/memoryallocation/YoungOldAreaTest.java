package com.bingoabin.memoryallocation;

/**
 * 测试：大对象直接进入到老年代
 * -Xmx60m -Xms60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseParNewGC
 * -Xmx60m -Xms60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * 新生代20M
 * 老年代40M
 *
 * -Xmx30m -Xms30m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseParNewGC
 * -Xmx30m -Xms30m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * 新生代10M
 * 老年代20M
 *
 * -XX:PretenureSizeThreshold
 *
 * @author kkb-james
 */
public class YoungOldAreaTest {
    public static void main(String[] args) {
        // 15M没有  16M存储  20M新生代
        // 7M没有   8M存储   10M新生代
        // parnew  80%新生代大小  -- 老年代


        // 14M没有  15M存储  20M新生代
        // 6M没有   7M存储   10M新生代
        // PS      70%新生代大小  ---> 老年代

        // -XX:PretenureSizeThreshold
        byte[] buffer = new byte[1024*1024*6];
        System.out.println("打印");
    }
}
