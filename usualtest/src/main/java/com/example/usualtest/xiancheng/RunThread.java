package com.example.usualtest.xiancheng;

public class RunThread extends Thread{

    volatile private boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println("进入到run方法中了");
        while (isRunning == true) {
            System.out.println("running is true");
        }
        System.out.println("线程执行完成了");
    }


    public static void main(String[] args) {
        try {
            RunThread thread = new RunThread();
            thread.start();
            Thread.sleep(1000);
            thread.setRunning(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*在main线程中，thread.setRunning(false);将启动的线程RunThread中的共享变量设置为false，从而想让RunThread.java的
    while循环结束。如果使用JVM -server参数执行该程序时，RunThread线程并不会终止，从而出现了死循环。

       （2）原因分析

    现在有两个线程，一个是main线程，另一个是RunThread。它们都试图修改isRunning变量。按照JVM内存模型，
    main线程将isRunning读取到本地线程内存空间，修改后，再刷新回主内存。

    而在JVM设置成 -server模式运行程序时，线程会一直在私有堆栈中读取isRunning变量。因此，
    RunThread线程无法读到main线程改变的isRunning变量。从而出现了死循环，导致RunThread无法终止。
*/
}


