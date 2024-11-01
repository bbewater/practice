package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class MyBlockingQueueApi {
// put 和 take 是阻塞操作，会等待直到操作成功。
//offer 和 poll 是非阻塞操作，如果条件不满足会立即返回，可以选择指定超时时间。

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);



        // 使用 put 方法添加元素（阻塞操作）
        queue.put("Element 1");
        System.out.println("Added: Element 1");
        queue.put("Element 2");
        System.out.println("Added: Element 2");

        // 使用 offer 方法添加元素（非阻塞操作）
        boolean success = queue.offer("Element 3");
        System.out.println("Offer Element 3: " + success);

        // 使用 offer 方法添加元素，并指定超时时间（2秒）
        success = queue.offer("Element 3", 2, TimeUnit.SECONDS);
        System.out.println("Offer Element 3 with timeout: " + success);

        // 使用 take 方法移除元素（阻塞操作）
        String element = queue.take();
        System.out.println("Took: " + element);

        // 使用 poll 方法移除元素（非阻塞操作）
        //peek操作是查看队列中的下一个元素 和poll的区别是在于要不要取出这个元素 peek仅仅是查看 而poll是取出
        element = queue.poll();
        System.out.println("Polled: " + element);

        // 使用 offer 方法添加元素（非阻塞操作）
        success = queue.offer("Element 3");
        System.out.println("Offer Element 3: " + success);

        // 使用 poll 方法移除元素，并指定超时时间（2秒）
        element = queue.poll(2, TimeUnit.SECONDS);
        System.out.println("Polled with timeout: " + element);


    }


}
