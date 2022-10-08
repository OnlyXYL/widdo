package cn.widdo.graph.entity.neo4j;

import java.io.Serializable;

/**
 * neo4j原生接口封装-空间位置
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/15 15:09
 */
public class Point3D implements Serializable {
    double srid;
    double x;
    double y;
    double z;

    public Point3D() {
    }

    public Point3D(int srid, double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.srid = srid;
    }

    public double getSrid() {
        return srid;
    }

    public void setSrid(double srid) {
        this.srid = srid;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

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
    public String toString() {
        return "Point{srid=" + this.srid + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + '}';
    }
}
