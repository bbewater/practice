package com.sz.bewater.practice.interview.redis;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class BloomFilter {
    //布隆过滤器用来解决缓存穿透问题
//    缓存穿透: 访问一个既不存在于缓存也不存在于db的数据 可能是恶意攻击(访问)  导致每次请求都打到了db 在高并发情况下可能是造成db宕机
//    布隆过滤器是在请求到缓存之前加了一道过滤器
//    工作原理:redisson实现的过滤器是用到了位图(bitmap)  整体上是一个大的数组 每个位置存的不是0就是1 初始化时都是0
//            当新元素想要存入缓存前 通过多个hash函数得到多个hash值 拿到hash值在对数组取模得到位置 并将数组上对应位置改为1
//            在布隆过滤器中查找元素是否存在的流程 跟存入的流程一样 找到相应位置 每个位置上都为1则元素存在  假如有一个位置不为1则不存在
//    误判:布隆过滤器是存在一定的误判的:不同的元素经过多个hash函数运算 再对数组取模得到的位置可能是一样的
//          导致这个元素其实不存在 但是在布隆过滤器查询结果是存在的
//    如何解决误判: 增加hash函数个数  位图增大  但是会面临着空间资源的损耗  我们可以设置我们允许接受的误判率 交由布隆过滤器帮我配置hash函数个数及位图大小
//                毕竟在我们能接受的误判率情况下 也不至于造成数据库压力的过分增大


//    通常情况下，布隆过滤器的 add 方法是用来添加数据的键（key）的。在使用布隆过滤器时，我们向其中添加的是要进行存在性检查的数据的键，
//    而不是数据的实际值。布隆过滤器内部会使用预先定义的多个哈希函数对这些键进行哈希计算，并将结果映射到位图中的对应位置。






    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create();
        //利用redisson客户端创建布隆过滤器
        RBloomFilter<Object> sampleBloomFilter = redissonClient.getBloomFilter("sampleBloomFilter");
        //设置布隆过滤器期望存入的大小 和最大容忍误判率 这里设置的是1%  布隆过滤器会根据这两个参数依靠算法得到hash算法的数目 和数组长度
        sampleBloomFilter.tryInit(10000L,0.01);
        sampleBloomFilter.add("item1");
        sampleBloomFilter.add("item2");


        System.out.println("contains item1 :"+sampleBloomFilter.contains("item1"));
        System.out.println("contains item2 :"+sampleBloomFilter.contains("item2"));


    }


}
