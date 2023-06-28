package cn.widdo.study.thread.demo;

import jodd.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * ThreadUnsafeMain
 * <p>
 * 1000个线程，同时对cnt进行自增操作，操作结束之后它的值有可能小于1000.
 *
 * @author XYL
 * @date 2023/05/18 16:56
 * @since 305.2.0.1
 */
public class ThreadUnsafeMain {

    protected ThreadUnsafeMain() {
        throw new UnsupportedOperationException(ThreadUnsafeMain.class.getName() + " cant`t be instance.");
    }

    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;
        final ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").get();

        //获取系统处理器个数，作为线程池数量
        final int threads = Runtime.getRuntime().availableProcessors();
        final ExecutorService executorService = new ThreadPoolExecutor(threadSize, threadSize,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                example.add();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }
}
