package cn.widdo.study.thread.demo;

/**
 * ThreadUnsafeExample.
 *
 * @author XYL
 * @date 2023/05/18 16:55
 * @since 305.2.0.1
 */
public class ThreadUnsafeExample {

    /**
     * cnt.
     */
    private int cnt = 0;

    /**
     * method named add.
     */
    public void add() {
        cnt++;
    }

    /**
     * method named get,and return cnt.
     *
     * @return return cnt
     */
    public int get() {
        return cnt;
    }
}
