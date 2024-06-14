package cn.widdo.study.thread.service.impl;

import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.study.thread.service.ThreadService;
import cn.widdo.study.thread.task.TaskCallable;
import cn.widdo.study.thread.task.TaskRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.*;

/**
 * ThreadServiceImpl
 *
 * @author XYL
 * @date 2024/02/26 18:55
 * @since 305.2.2.0
 */
@Service
public class ThreadServiceImpl implements ThreadService {

    private static final Logger log = LoggerFactory.getLogger(ThreadServiceImpl.class);

    @Override
    public WiddoResult runnable(Map<String, Object> params) {

        final TaskRunnable task1 = new TaskRunnable("one", 4);
        final TaskRunnable task2 = new TaskRunnable("two", 2);

        final Thread t1 = new Thread(task1);
        final Thread t2 = new Thread(task2);

        t1.start();

        try {
            //在主线程中等待t1执行2秒
            t1.join(2000);
        } catch (InterruptedException e) {
            System.out.println(" t1 interrupted when waiting join");
            log.error(InterruptedException.class.getName(), e);
        }

        //t1执行两秒后打断
        t1.interrupt();
        t2.start();

        try {
            t2.join(1000);
        } catch (InterruptedException e) {
            System.out.println(" t2 interrupted when waiting join");
            log.error(InterruptedException.class.getName(), e);
        }

        return WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS);
    }

    @Override
    public WiddoResult callable(Map<String, Object> params) {
        final ExecutorService pool = Executors.newCachedThreadPool();

        final TaskCallable task1 = new TaskCallable("one", 5);
        final Future<Boolean> f1 = pool.submit(task1);


        try {
            if (f1.get(2, TimeUnit.SECONDS)) {
                System.out.println("one complete successfully");
            }
        } catch (InterruptedException e) {
            System.out.println("future 在睡着时被打断");
            pool.shutdown();
        } catch (ExecutionException e) {
            System.out.println("future 在尝试取得任务结果时出错");
            pool.shutdown();
        } catch (TimeoutException e) {
            System.out.println("future 时间超时");
            pool.shutdown();
        } finally {
            pool.shutdown();
        }

        return WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS);
    }
}
