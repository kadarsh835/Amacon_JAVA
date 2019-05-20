package com.SBI;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Main {

    private static long cpuTime(Thread thr) {
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        if (mxBean.isThreadCpuTimeSupported()) {
            try {
                return mxBean.getThreadCpuTime(thr.getId());
            } catch (UnsupportedOperationException e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("Not supported");
        }
        return 0;
    }

    public static void main (String[] args){
        Bank bankObj = new Bank();
        System.out.println("Balance before making transactions : "+bankObj.getTotalBalance());

        myThread thread1 = new myThread(bankObj, 0, 2500000);
        myThread thread2 = new myThread(bankObj, 2500000, 5000000);
        myThread thread3 = new myThread(bankObj, 5000000, 7500000);
        myThread thread4 = new myThread(bankObj, 7500000, 10000000);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        System.out.println("Balance after making transactions : "+bankObj.getTotalBalance());
        System.out.println("Thread 1 : "+(cpuTime(thread1)/1000000));
        System.out.println("Thread 2 : "+(cpuTime(thread2)/1000000));
        System.out.println("Thread 3 : "+(cpuTime(thread3)/1000000));
        System.out.println("Thread 4 : "+(cpuTime(thread4)/1000000));
    }
}
