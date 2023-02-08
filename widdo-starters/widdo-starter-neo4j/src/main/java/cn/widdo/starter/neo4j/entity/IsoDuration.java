package cn.widdo.starter.neo4j.entity;

import java.io.Serializable;

/**
 * neo4j原生接口封装-时间.
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/08/15 15:09
 */
public class IsoDuration implements Serializable {
    /**
     * 月.
     */
    private long months;
    /**
     * 天.
     */
    private long days;
    /**
     * 秒.
     */
    private long seconds;
    /**
     * 毫秒.
     */
    private int nanoseconds;

    /**
     * getter.
     *
     * @return a long months
     */
    public long getMonths() {
        return months;
    }

    /**
     * setter.
     *
     * @param months    months
     */
    public void setMonths(long months) {
        this.months = months;
    }

    /**
     * getter.
     *
     * @return a long days
     */
    public long getDays() {
        return days;
    }

    /**
     * setter.
     *
     * @param days  days
     */
    public void setDays(long days) {
        this.days = days;
    }

    /**
     * getter.
     *
     * @return a long seconds
     */
    public long getSeconds() {
        return seconds;
    }

    /**
     * setter.
     *
     * @param seconds   seconds
     */
    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    /**
     * getter.
     *
     * @return a nanoseconds result
     */
    public int getNanoseconds() {
        return nanoseconds;
    }

    /**
     * setter.
     *
     * @param nanoseconds   nanoseconds
     */
    public void setNanoseconds(int nanoseconds) {
        this.nanoseconds = nanoseconds;
    }
}
