package com.example.usualtest.lock;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTest extends Thread {


    static class MyThread extends Thread{

        public void run(){

            System.out.println("MyThread: run()");

        }

        public void start(){

            System.out.println("MyThread: start()");

        }

    }

    static class MyRunnable implements Runnable{

        public void run(){

            System.out.println("MyRunnable: run()");

        }

        public void start(){

            System.out.println("MyRunnable: start()");

        }

    }

    public static void main(String args[]) throws InterruptedException {
        //MyThread重写了start方法，所以它的run方法不会被执行
        //MyRunnable不是线程，真正的线程是new Thread(MyRunnable)，所以会MyRunnable的run函数
       /* MyThread myThread  =  new MyThread();

        MyRunnable myRunnable = new MyRunnable();

        Thread thread  =  new Thread(myRunnable);

        myThread.start();

        thread.start();*/

        /*
        * Join（）作用：让主线程等待子线程运行结束后才能继续运行。
        这段代码里面的意思是这样的：

        程序在main线程中调用thread1线程的join方法，则main线程放弃cpu控制权，并返回thread1线程继续执行直到线程thread1执行完毕
        所以结果是thread1线程执行完后，才到主线程执行，相当于在main线程中同步thread1线程，thread1执行完了，main线程才有执行的机会

        作为一个有素养的技术人，这里必须要亲自看看join（）的源码了。
        *
        * */
      /* aa.start();
       aa.join();
       bb.start();
       bb.join();
       cc.start();
       cc.join();*/

       //原理：利用并发包里的Excutors的newSingleThreadExecutor产生一个单线程的线程池，而这个线程池的底层原理就是一个先进先出（FIFO）的队列。
        // 代码中executor.submit依次添加了123线程，按照FIFO的特性，执行顺序也就是123的执行结果，从而保证了执行顺序。
         ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(aa);
        executorService.submit(bb);
        executorService.submit(cc);

        executorService.shutdown();


        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add("wqw");
    }

     static Thread aa =  new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("--------------thred1");
            }
        });



    static Thread bb = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("--------------thred2");
            }
        });
    static Thread cc = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("--------------thred3");
        }
    });



}
