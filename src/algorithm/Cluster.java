package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private int _clusterNumber;
    private Vector _centroid;
    private List<Vector> _vectorList;

    public Cluster(int _clusterNumber) {
        this._clusterNumber = _clusterNumber;
        this._vectorList = new ArrayList<>();
        this._centroid = Vector.createRandomPoints();
    }

    public List<Vector> getPoints() {
        return _vectorList;
    }

    public void addPoint(Vector vector) {
        _vectorList.add(vector);
    }

    public Vector get_centroid() {
        return _centroid;
    }

    public void clear() {
        _vectorList.clear();
    }

    public void showCluster() {
        double sum = 0;
        System.out.println("Cluster number: " + _clusterNumber);
        System.out.println("Centroid coordinates: " + _centroid.toStringCentroid());
        System.out.println("Points: ");
        for(Vector p : _vectorList) {
            System.out.println(p.toStringIris());
            sum += Vector.distance(p, get_centroid());
        }
        System.out.println("Sum of distances: " + sum);
    }
}
