package com.example.usualtest.lock;

/**
 * 本例中各个线程是共享100张票的资源的
 *
 */
public class RunnableCreate {
    public static void main(String[] args) {
        MyRunnable m =new MyRunnable();
        Thread t1=new Thread(m,"窗口1");
        Thread t2=new Thread(m,"窗口2");
        Thread t3=new Thread(m,"窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class MyRunnable implements Runnable{
    private int ticket=1;
    public void run(){
        while(ticket<=100){  //几个线程共同卖100张票
            String threadName = Thread.currentThread().getName();
            System.out.println("【" + threadName + "】售出第【" + ticket++ + "】张票");
        }

    }
}
