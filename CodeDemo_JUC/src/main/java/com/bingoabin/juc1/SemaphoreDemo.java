package com.bingoabin.juc1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 在信号量上我们定义两种操作：
 * acquire（获取）当一个线程调用acquire操作时，他要么通过成功获取信号量（信号量减1），要么一直等待下去，直到有线程释放信号量，或超时。
 * release（释放）实际上会将信号量加1，然后唤醒等待的线程。
 *
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(()->{
                //占有资源
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到了车位...");

                    try { TimeUnit.SECONDS.sleep(temp); } catch (InterruptedException e) {e.printStackTrace(); }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName()+"===释放了车位...");
                    semaphore.release();

                }
            }, String.valueOf(i)).start();
        }
    }
}
