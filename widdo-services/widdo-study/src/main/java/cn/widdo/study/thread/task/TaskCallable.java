package cn.widdo.study.thread.task;

import java.util.concurrent.Callable;

/**
 * TaskCallable
 *
 * @author XYL
 * @date 2024/02/26 19:02
 * @since 305.2.2.0
 */
public class TaskCallable implements Callable<Boolean> {

    private final String name;

    private final int time;

    public TaskCallable(String name, int time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public Boolean call() throws Exception {
        for (int i = 0; i < time; i++) {
            System.out.println("task" + name + " " + (i + 1) + " round");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(name + " is interrupted when calculating, will stop...");
                return false;
            }
        }
        return true;
    }

}
