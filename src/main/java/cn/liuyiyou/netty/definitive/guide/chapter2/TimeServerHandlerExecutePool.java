package cn.liuyiyou.netty.definitive.guide.chapter2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 代码清单2.2：伪异步I/O 的 TimeServerHandlerExecutePool
 * @author: liuyiyou.cn
 * @date: 2019/1/29
 * @version: V1.0
 */
public class TimeServerHandlerExecutePool {

    private ExecutorService executorService;

    public TimeServerHandlerExecutePool(int maxPoolSize,int queueSize) {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120L,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void  excute(Runnable task){
        executorService.execute(task);
    }

}
