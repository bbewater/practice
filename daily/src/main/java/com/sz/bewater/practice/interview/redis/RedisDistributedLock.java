package com.sz.bewater.practice.interview.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedisDistributedLock {
// 分布式锁的出现是由于现代的系统架构逐渐从单体架构转变为分布式架构。
// 本地锁（如 synchronized、ReentrantLock）只能保证在相同 JVM 下的互斥性，不能跨 JVM 保证互斥性，因此需要分布式锁。
// Redisson 实现的分布式锁，底层是通过 Redis 的 setnx 命令（set if not exists，如果不存在则添加）和 Lua 脚本实现的。
// Lua 脚本用于保证 Redis 中多个指令的原子性执行，确保设置锁和过期时间是一个不可分割的操作。
// 指令格式：set lock myLock nx ex 30：添加互斥锁并设置过期时间。nx 代表互斥，ex 代表锁的过期时间。
// 通过该指令，设置锁的同时设置过期时间（为了避免死锁），这个指令的原子性确保了设置锁和设置过期时间是一个不可分割的操作。
// 然而，过期时间不好控制：设置太短，程序可能在执行完之前就释放了锁；设置太长，会影响其他线程加锁。

// Redisson 提供了更强大的分布式锁机制，利用 Watchdog（看门狗）机制，使得程序员不再需要关注锁的过期时间，而是由 Watchdog 来控制。
// Watchdog 默认将锁的过期时间设置为 30 秒，并且每隔 10 秒检查一次。如果程序还没执行完，则会自动给锁续期 30 秒。
// 程序员只需要在程序执行完毕后手动释放锁即可。

// Watchdog 的底层实现是 Redisson 启动了一个调度任务，每隔 10 秒检查一次锁的持有状态。如果超过 10 秒锁还未被释放，则续期 30 秒。

// Redisson 提供的分布式锁是支持重入的。底层使用一个 hash 数据结构存储，key 为锁的名称，value 为线程 id 和重入次数。重入过程类似于 ReentrantLock 提供的重入锁。

// Redisson 提供的分布式锁不能保证主从一致性。Redisson 提供的红锁（RedLock）可以保证一致性，但实现较为复杂，需要每个 Redis 实例同时持有锁，性能较低。
// Redis 主从架构遵循 AP 协议，目的是高可用优先。如果需要强一致性（CP），建议使用 Zookeeper 提供的分布式锁。


//    redisson可以理解成增强redis功能的一个强大的java框架


    private static RLock lock;

    static{
        //类的静态代码块是在类被加载到jvm的时候执行的(类加载中的初始化阶段) 且只执行一次
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create();
        lock = redissonClient.getLock("MyLock");
    }


    public static void main(String[] args) {
        try {
//            lock.tryLock(10,10,TimeUnit.SECONDS)  第一个参数为获取锁超时等待时间  第二个参数为锁的过期时间 假如设置了过期时间则watchdog不再起作用  不设置过期时间 或者设置为-1则watchdog会每隔10s帮我们锁续期
            boolean getLock = lock.tryLock(10, TimeUnit.SECONDS);
            if (getLock){
                System.out.println("获取了分布式锁啦");
                //业务逻辑
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }


    }



}
