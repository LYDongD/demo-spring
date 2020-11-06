package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * 模拟多线程可见性问题
 * 1 两个线程同时修改变量10000次，查看变量最后的结果
 *
 * 竞态模型：read-and-write -> 发生了更新覆盖
 * 内存模型：1 多核cpu缓存可见性问题
 *          2 单核cpu线程切换导致寄存器与缓存不一致问题
 * @author Liam(003046)
 * @date 2020/6/11 下午3:53
 */
@Slf4j
public class RaceProblem {

    private int count = 0;

    public void add10K(){
        for (int i = 0; i < 10 * 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) throws Exception{
        RaceProblem visualProblem = new RaceProblem();
        Thread threadCpu1 = new Thread(visualProblem::add10K);
        Thread threadCpu2 = new Thread(visualProblem::add10K);
        threadCpu1.start();
        threadCpu2.start();
        threadCpu1.join();
        threadCpu2.join();
        log.info("count: {}", visualProblem.count);
    }
}
