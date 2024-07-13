//package com.sz.bewater.practice.interview.redis;
//
//import cn.hutool.core.util.ObjectUtil;
//import org.redisson.Redisson;
//import org.redisson.api.RLock;
//import org.redisson.api.RMap;
//import org.redisson.api.RReadWriteLock;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyReadWriteLock {
//    private static Config config;
//    private static RedissonClient redisson;
//
//    private static RReadWriteLock readWriteLock;
//    private static String cacheKey;
//    private static String dbData;
//
//
//    static {
//        config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        redisson = Redisson.create(config);
//        readWriteLock = redisson.getReadWriteLock("MyReadWriteLock");
//        cacheKey = "cacheKey";
//        dbData = "10";
//    }
//
//    public  Object readData(){
//        RLock rLock = readWriteLock.readLock();
//        rLock.lock();
//        try{
//            //先从缓存读数据
//            //Redisson 通过对 Redis 的封装，使得我们可以在 Redis 上使用类似 Java Map 接口的功能，而不局限于 Redis 本身的数据结构。
//            RMap<Object, Object> map = redisson.getMap("myCache");
//            Object cacheResult = map.get(cacheKey);
//            if (ObjectUtil.isNotEmpty(cacheResult)){
//                return cacheResult;
//            }
//            //从db中读数据
//            return dbData;
//        }finally {
//            rLock.unlock();
//        }
//
//    }
//
//    public void writeData(String newValue){
//        RLock rLock = readWriteLock.writeLock();
//        rLock.lock();
//        try {
//            //操作数据库
//            dbData = newValue;
//            //操作缓存
//            RMap<Object, Object> map = redisson.getMap("myCache");
//            map.put(cacheKey,newValue);
//        } finally {
//            rLock.unlock();
//        }
//
//
//    }
//
//
//    public static void main(String[] args) {
//        MyReadWriteLock myReadWriteLock = new MyReadWriteLock();
//        myReadWriteLock.writeData("20");
//        System.out.println(myReadWriteLock.readData());
//    }
//
//
//
//}
