package com.feifei.thread.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义用户线程工厂类
 * @author xuxiangfei
 * @date 2019/9/18
 */
public class UserThreadFactory implements ThreadFactory {

    /**
     * 线程名称前缀
     */
    private final String namePrefix;

    private final AtomicInteger nextId = new AtomicInteger(1);

    /**
     * 有参构造 初始化组名
     * @param whatFeatureOfGroup 组名
     */
    public UserThreadFactory(String whatFeatureOfGroup) {
        this.namePrefix = "UserThreadFactory-" + whatFeatureOfGroup + "-Work-";
    }

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable r) {
        String threadName = namePrefix + nextId.getAndIncrement();
        return new Thread(r, threadName);
    }
}
