package cn.widdo.starter.neo4j.entity;

import java.io.Serializable;

/**
 * neo4j原生接口封装-地理位置.
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/08/15 15:09
 */
public class Point2D implements Serializable {

    /**
     * srid.
     */
    private double srid;

    /**
     * x.
     */
    private double x;
    /**
     * y.
     */
    private double y;

    /**
     * construct has no params.
     */
    public Point2D() {
    }

    /**
     * Point2D.
     *
     * @param srid  srid
     * @param x x
     * @param y y
     * @author XYL
     * @date 2022/11/28 14:39:36
     **/
    public Point2D(final int srid, final double x, final double y) {
        this.x = x;
        this.y = y;
        this.srid = srid;
    }

    /**
     * get srid.
     *
     * @return a result type of double
     */
    public double getSrid() {
        return srid;
    }

    /**
     * set srid.
     *
     * @param srid  srid
     */
    public void setSrid(double srid) {
        this.srid = srid;
    }

    /**
     * get x.
     *
     * @return a result type of double
     */
    public double getX() {
        return x;
    }

    /**
     * set x.
     *
     * @param x x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * get y.
     *
     * @return a result typ of double
     */
    public double getY() {
        return y;
    }

    /**
     * set y.
     *
     * @param y y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * get Z.
     *
     * @return a result type of double
     */
    public double getZ() {
        return Double.NaN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Point2D that = (Point2D) o;
            return this.srid == that.srid && Double.compare(that.x, this.x) == 0 && Double.compare(that.y, this.y) == 0;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Point{srid=" + this.srid + ", x=" + this.x + ", y=" + this.y + '}';
    }
}
