package com.yuanq.rabbit;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanqiang on 2017/11/20.
 */
public class Polygon {

    public static void test (){
        List<Map<String, Double>> polygonPoints = new ArrayList<>();
        double [] doubles = new double[] {116.315649,40.060041,116.325279,40.059599,116.326213,40.05728,116.324129,40.055761,116.323087,40.053579,116.317087,40.054159,116.315649,40.056009};

        for (int i = 0; i < doubles.length; i++) {
            Map<String ,Double> map = new HashMap<>();

            map.put("x" , doubles[i]);
            map.put("y" , doubles[++i]);

            polygonPoints.add(map);
        }
//        System.out.println(polygonPoints);


//        double x = 116.322584;
//        double y = 40.058246;

        double x = 116.329555;
        double y = 40.060013 ;
        System.out.println(        Polygon.isPointInPolygon(x ,y ,polygonPoints));
    }

    /**
     * 验证坐标是否在区域内
     * @param x
     * @param y
     * @param polygonPoints
     * @return
     */
    public static boolean isPointInPolygon(double x, double y, List<Map<String, Double>> polygonPoints) {
        Point2D.Double geoPoint = buildPoint(x, y);
        List<Point2D.Double> geoPolygon = buildPolygon(polygonPoints);
        return Polygon.isPointInPolygon(geoPoint, geoPolygon);
    }

    /**
     * 检查一个坐标是否在多边形内
     * @param point 检查的点坐标
     * @param polygon 参照的多边形
     * @return
     */
    public static boolean isPointInPolygon(Point2D.Double point, List<Point2D.Double> polygon) {
        GeneralPath p = new GeneralPath();

        Point2D.Double first = polygon.get(0);
        p.moveTo(first.x, first.y);
        polygon.remove(0);

        polygon.forEach(d -> p.lineTo(d.x, d.y));

        p.lineTo(first.x, first.y);

        p.closePath();

        return p.contains(point);
    }

    /**
     * 构建一个坐标点
     * @param x 纬度 31.000...
     * @param y 经度 121.000...
     * @return
     */
    public static Point2D.Double buildPoint(double x, double y) {
        return new Point2D.Double(x, y);
    }

    /**
     * 构建一个多边形
     * @param polygonPoints
     * @return
     */
    public static List<Point2D.Double> buildPolygon(List<Map<String, Double>> polygonPoints) {
        List<Point2D.Double> geoPolygon = new ArrayList<>();

        polygonPoints.forEach(map -> geoPolygon.add(buildPoint(map.get("x"), map.get("y"))));

        return geoPolygon;
    }


}
