package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/thread-signaling.html

Problem:
1, if a thread calls notify() before the thread calls wait(), the waiting thread will miss signal, so waiting thread may
   waiting forever.

Solution:
1, to avoid losing signals, add a member variable isNotified in the MonitorObject, so a thread will only call wait()
   when isNotified=false ensure notify() will happen after wait()

output:
Notify_Thread_1 before notify; SharedSignal.wasSignalled=false
Notify_Thread_1 after notify; SharedSignal.wasSignalled=true
Waiting_Thread_1 got data; SharedSignal.wasSignalled=false
Notify_Thread_2 before notify; SharedSignal.wasSignalled=false
Notify_Thread_2 after notify; SharedSignal.wasSignalled=true
Waiting_Thread_2 got data; SharedSignal.wasSignalled=false

 */
public class App
{
    static class MonitorObject{}
    static class SharedSignal { // wait and notify
        MonitorObject monitorObject = new MonitorObject();
        public boolean wasSignalled = false;
        public void doWait(){
            synchronized (monitorObject){
                if(!wasSignalled) {  // NOTE: wait() when wasSignalled=false
                    try {
                        monitorObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                wasSignalled = false;  // clear signal and continue running.
            }
        }
        public void doNotify(){
            synchronized (monitorObject){
                wasSignalled = true;
                monitorObject.notify();
            }
        }
    }
    public static void main( String[] args )
    {
        final SharedSignal sharedSignal = new SharedSignal();
        String threadName = "Notify_Thread_1";
        new Thread(threadName){
            public void run(){
                try { Thread.sleep(500); }
                catch (InterruptedException e) { e.printStackTrace(); }
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " before notify; SharedSignal.wasSignalled=" + sharedSignal.wasSignalled);
                sharedSignal.doNotify();
                System.out.println(threadName + " after notify; SharedSignal.wasSignalled=" + sharedSignal.wasSignalled);
            }
        }.start();
        threadName = "Waiting_Thread_1";
        new Thread(threadName){
            public void run(){
                sharedSignal.doWait();
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " got data; SharedSignal.wasSignalled=" + sharedSignal.wasSignalled);
            }
        }.start();
        threadName = "Waiting_Thread_2";
        new Thread(threadName){
            public void run(){
                sharedSignal.doWait();
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " got data; SharedSignal.wasSignalled=" + sharedSignal.wasSignalled);
            }
        }.start();
        threadName = "Notify_Thread_2";
        new Thread(threadName){
            public void run(){
                try { Thread.sleep(600); }
                catch (InterruptedException e) { e.printStackTrace(); }
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " before notify; SharedSignal.wasSignalled=" + sharedSignal.wasSignalled);
                sharedSignal.doNotify();
                System.out.println(threadName + " after notify; SharedSignal.wasSignalled=" + sharedSignal.wasSignalled);
            }
        }.start();
    }
}
