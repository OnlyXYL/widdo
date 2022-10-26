package cn.widdo.starter.neo4j.entity;

import java.io.Serializable;

/**
 * neo4j原生接口封装-时间
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/08/15 15:09
 */
public class IsoDuration implements Serializable {
    private long months;
    private long days;
    private long seconds;
    private int nanoseconds;

    public long getMonths() {
        return months;
    }

    public void setMonths(long months) {
        this.months = months;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public int getNanoseconds() {
        return nanoseconds;
    }

    public void setNanoseconds(int nanoseconds) {
        this.nanoseconds = nanoseconds;
    }
}
