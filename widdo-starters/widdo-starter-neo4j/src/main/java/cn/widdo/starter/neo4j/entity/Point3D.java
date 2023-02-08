package cn.widdo.starter.neo4j.entity;

import java.io.Serializable;

/**
 * neo4j原生接口封装-空间位置.
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/08/15 15:09
 */
public class Point3D implements Serializable {

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
     * z.
     */
    private double z;

    /**
     * construct has no params.
     */
    public Point3D() {
    }

    /**
     * constructor has three params.
     *
     * @param srid  srid
     * @param x x
     * @param y y
     * @param z z
     */
    public Point3D(final int srid, final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
     * @return a result type of double
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
     * get z.
     *
     * @return a result type of double
     */
    public double getZ() {
        return z;
    }

    /**
     * set z.
     * @param z z
     */
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Point3D that = (Point3D) o;
            return this.srid == that.srid && Double.compare(that.x, this.x) == 0 && Double.compare(that.y, this.y) == 0 && Double.compare(that.z, this.z) == 0;
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
        return String.format("Point{srid=%s, x=%s, y=%s, z=%s}", this.srid, this.x, this.y, this.z);
    }
}
