package com.example.usualtest.lock;

/**
 * 各个线程之间执行是相互独立，互不干扰的 继承Thread类的线程不能共享资源
 */
public class ThreadCreate {
    public static void main(String[] args) {
        MyThread m1 = new MyThread("窗口1");
        MyThread m2 = new MyThread("窗口2");
        MyThread m3 = new MyThread("窗口3");
        m1.start();
        m2.start();
        m3.start();
    }
}

class MyThread extends Thread {
    private int ticket = 1;
    MyThread(String name) {
        super(name); // 调用父类带参数的构造方法
    }
    public void run() {
        while (ticket <= 100) { // 每个线程都拥有100张票，各自卖各自得票
            String threadName = Thread.currentThread().getName();
            System.out.println("【" + threadName + "】售出第【" + ticket++ + "】张票");
        }
    }
}
