package algorithm;

import java.util.Random;

public class Vector {
    private double _x;
    private double _y;
    private double _z;
    private double _g;
    private String _name;
    private int _clusterNumber;

    public Vector(double _x, double _y, double _z, double _g, String _name){
        this._x = _x;
        this._y = _y;
        this._z = _z;
        this._g = _g;
        this._name = _name;
    }

    public Vector(double _x, double _y, double _z, double _g){
        this._x = _x;
        this._y = _y;
        this._z = _z;
        this._g = _g;
    }

    public void setCluster(int n) {
        this._clusterNumber = n;
    }

    public double get_x() {
        return _x;
    }

    public void set_x(double _x) {
        this._x = _x;
    }

    public double get_y() {
        return _y;
    }

    public void set_y(double _y) {
        this._y = _y;
    }

    public double get_z() {
        return _z;
    }

    public void set_z(double _z) {
        this._z = _z;
    }

    public double get_g() {
        return _g;
    }

    public void set_g(double _g) {
        this._g = _g;
    }
    public String get_name() {
        return this._name;
    }
    public String toStringIris(){
        return "[x: " + this._x + ", y: " + this._y + ", z: " + this._z + ", g: " + this._g + ", species: " + this._name + "]";
    }

    public String toStringCentroid(){
        return "x: " + this._x + ", y: " + this._y + ", z: " + this._z + ", g: " + this._g;
    }

    public static double distance(Vector p, Vector centroid) {
        return Math.sqrt(Math.pow((centroid.get_x() - p.get_x()), 2)
                + Math.pow((centroid.get_y() - p.get_y()), 2)
                + Math.pow((centroid.get_z() - p.get_z()), 2)
                + Math.pow((centroid.get_g() - p.get_g()), 2));
    }

    public static Vector createRandomPoints(){
        Random r = new Random();
        double x = 4 + (9 - 4) * r.nextDouble();
        double y = 2 + (5 - 2) * r.nextDouble();
        double z = 1 + (8 - 1) * r.nextDouble();
        double g = 0 + (3 - 0) * r.nextDouble();

        return new Vector(x,y,z,g);
    }
}
