package com.yw.springbootdemo.queue;

import com.google.common.collect.EvictingQueue;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yangwei
 * @date 2020-08-02 9:43
 */
public class QueueTest {

    public static void main(String[] args) {
        EvictingQueue<Integer> queue = EvictingQueue.create(3);
        for (int i = 0; i < 6; i++) {
            queue.add(i);
            System.out.println("添加元素：" + i);
            System.out.println("当前队列中元素集合：" + StringUtils.join(queue.iterator(), ',') + "\n");
        }

        EvictingDeque<Integer> deque = EvictingDeque.create(3);
        for (int i = 0; i < 6; i++) {
            deque.add(i);
            System.out.println("deque添加元素：" + i);
            System.out.println("deque当前队列中元素集合：" + StringUtils.join(deque.iterator(), ',') + "\n");
            System.out.println("deque队尾元素元素集合：" + deque.peekLast());
        }

//        Deque<Integer> deque = new ArrayDeque<>(3);
//        for (int i = 0; i < 6; i++) {
//            deque.add(i);
//            System.out.println("deque添加元素：" + i);
//            System.out.println("deque当前队列中元素集合：" + StringUtils.join(deque.iterator(), ',') + "\n");
//        }

    }
}
