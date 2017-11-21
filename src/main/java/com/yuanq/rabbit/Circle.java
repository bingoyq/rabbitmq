package com.yuanq.rabbit;

import java.awt.geom.Point2D;
import java.util.zip.CRC32;

/**
 * Created by yuanqiang on 2017/11/20.
 */
public class Circle {

    static double EARTHRADIUS = 6370996.81;

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        //116.2993, lat: 40.06051
        Point2D.Double circlePoint = new Point2D.Double(116.2993 ,40.06051);

        //116.299156, lat: 40.060731 内
//        Point2D.Double point = new Point2D.Double(116.299156 ,40.060731);

        double r = 287.9052616752258;

//        116.306738, lat: 40.063961
        Point2D.Double point = new Point2D.Double(116.306738 ,40.063961);

        isPointInCircle(point ,circlePoint ,r);
    }

    /**
     * 验证坐标是否在圆内
     * @param point
     * @param circlePoint
     * @param r
     * @return
     */
    public static boolean isPointInCircle(Point2D.Double point , Point2D.Double circlePoint , double r) {
        //半径是R  如O(x,y)点圆心，任意一点P（x1,y1）
        //x-x1）*(x-x1)+(y-y1)*(y-y1)>R*R

        double px = getLoop(point.getX() , -180 , 180);
        double py = getRange(point.getY() ,-74 , 74);
        double cx = getLoop(circlePoint.getX() , -180 , 180);
        double cy = getRange(circlePoint.getY() ,-74 , 74);

        px = degreeToRad(px);
        py = degreeToRad(py);
        cx = degreeToRad(cx);
        cy = degreeToRad(cy);


        double result = EARTHRADIUS * Math.acos((Math.sin(py) * Math.sin(cy) +
                Math.cos(py) * Math.cos(cy) * Math.cos(cx - px)));

        System.out.println(result <= r);

        return false;
    }

    /**
     * 将v值限定在a,b之间，经度使用
     */
    private static double getLoop (double v, int a,int b) {
        while( v > b){
            v -= b - a;
        }
        while(v < a){
            v += b - a;
        }
        return v;
    }

    /**
     * 将v值限定在a,b之间，纬度使用
     */
    private static double getRange(double v ,int a, int b) {
        if(a != -1){
            v = Math.max(v, a);
        }
        if(b != -1){
            v = Math.min(v, b);
        }
        return v;
    }

    /**
     * 将度转化为弧度
     * @param {degree} Number 度
     * @returns {Number} 弧度
     */
    private static double degreeToRad(double v) {
        return Math.PI * v/180;
    }




}
