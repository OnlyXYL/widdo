package cn.widdo.study.thread.task;

import java.util.concurrent.Callable;

/**
 * TaskCallable.
 *
 * @author XYL
 * @date 2024/02/26 19:02
 * @since 305.2.2.0
 */
public class TaskCallable implements Callable<Boolean> {

    /**
     * name.
     */
    private final String name;

    /**
     * time.
     */
    private final int time;

    /**
     * constructor method of TaskCallable.
     *
     * @param name name
     * @param time time
     */
    public TaskCallable(final String name, final int time) {
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
