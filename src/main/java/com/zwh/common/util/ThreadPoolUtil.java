package com.zwh.common.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2014/8/20.
 */
public class ThreadPoolUtil {

    private static final long keepAliveTime = 24;
    private ThreadPoolExecutor executor;

    public ThreadPoolUtil(int corePoolSize, int maximumPoolSize) {
        BlockingQueue queue = new LinkedBlockingQueue();
        this.executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.HOURS, queue);
    }

    public void execute(Runnable runnable) {
        if (this.executor != null) {
            this.executor.execute(runnable);
        }
    }

    public void shutdown() {
        if (this.executor != null) {
            this.executor.shutdown();
        }
    }
}
