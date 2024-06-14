package cn.widdo.study.thread.task;

/**
 * TaskRunnable
 *
 * @author XYL
 * @date 2024/02/26 18:58
 * @since 305.2.2.0
 */
public class TaskRunnable implements Runnable {

    public String name;

    private final int time;

    public TaskRunnable(String name, int time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public void run() {

        for (int i = 0; i < time; i++) {
            System.out.println("task" + name + " " + (i + 1) + " round");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(name + " is interrupted when calculating, will stop...");
                return;
            }
        }
    }
}
