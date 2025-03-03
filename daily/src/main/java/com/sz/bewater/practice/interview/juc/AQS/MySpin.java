package com.sz.bewater.practice.interview.juc.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project: practice
 * Description: 模拟线程自旋获取锁
 *
 * @Author: zhoudun
 * @Date: 2025/3/3
 */
public class MySpin {
    public static Lock lock = new ReentrantLock();
    public static Thread t1;
    public static Thread t2;

    public static void main(String[] args) {
        t1 = new Thread(()->{
            try{
                lock.lock();
                System.out.println("t1 get lock");
                TimeUnit.SECONDS.sleep(10);
            }catch (Exception e){

            }finally {
                lock.unlock();
                LockSupport.unpark(t2);
                System.out.println("t1 release lock and unpark t2");
            }
        });

        t2 = new Thread(()->{
            try{
                TimeUnit.MILLISECONDS.sleep(100);
                int count = 0;
                for(;;){ //模拟t2自旋5次尝试获取锁 获取不到则阻塞自己 等待t1唤醒自己 再次尝试获取锁
                    if(lock.tryLock()){
                        System.out.println("t2 get lock");
                        break;
                    }
                    System.out.println("t2 have no lock");
                    count++;
                    if (count > 4){
                        LockSupport.park();
                    }

                }
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();

    }
}
