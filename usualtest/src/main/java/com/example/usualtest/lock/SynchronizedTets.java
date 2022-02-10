package com.example.usualtest.lock;

public class SynchronizedTets  {

   /* public static void main(String[] args) {
        System.out.println("使用关键字synchronized");
        SyncThread syncThread = new SyncThread();
       *//* Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread, "SyncThread2");*//*

       // thread1和thread2同时在执行 这是因为synchronized只锁定对象，每个对象只有一个锁（lock）与之相关联，
        // 而上面的代码等同于下面这段代码：
        Thread thread1 = new Thread(new SyncThread(), "SyncThread1");
        Thread thread2 = new Thread(new SyncThread(), "SyncThread2");
        thread1.start();
        thread2.start();
    }*/
    /* 二、当一个线程访问对象的一个synchronized(this)同步代码块时，另一
    个线程仍然可以访问该对象中的非synchronized(this)同步代码块。
　　多个线程访问synchronized和非synchronized代码块
    * */
    public static void main(String[] args) {
        System.out.println("使用关键字synchronized");
        Mthreads mt=new Mthreads();
        Thread thread1 = new Thread(mt, "mt1");
        Thread thread2 = new Thread(mt, "mt2");
        thread1.start();
        thread2.start();
    }
}
class Mthreads implements Runnable {
    private int count;

    public Mthreads() {
        count = 0;
    }

    public void countAdd() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " count:" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
    public void printCount() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " count:" + count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        if (threadName.equals("mt1")) {
            countAdd();
        } else if (threadName.equals("mt2")) {
            printCount();
        }
    }
}
