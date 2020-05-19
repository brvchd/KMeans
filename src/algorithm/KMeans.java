package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KMeans {

    private static int _k;
    private List<Vector> _vectors;
    private List<Cluster> _clusters;
    //private static double setosaCount = 0;
    //private static double virginicaCount = 0;
    //private static double versicolorCount = 0;

    public KMeans() {
        _vectors = DataReader.load("iris_training.txt");
        _clusters = new ArrayList<>();
        /*for (Vector vector : vectors){
            if(vector.getName().equals("Iris-setosa")){
                setosaCount++;
            } else if (vector.getName().equals("Iris-virginica")){
                virginicaCount++;
            } else if (vector.getName().equals("Iris-versicolor")){
                versicolorCount++;
            }
        }*/
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Provide number of k: ");
        _k = scanner.nextInt();
        KMeans km = new KMeans();
        km.clustersInitialize();
        km.calculate();
        //System.out.println("setosa " + setosaCount + " virginica: " + virginicaCount + " versicolor: " + versicolorCount);
    }

    public void clustersInitialize() {
        for (int i = 1; i <= _k; i++) {
            Cluster cluster = new Cluster(i);
            _clusters.add(cluster);
        }
        System.out.println("Initial state");
        showClusters();
    }

    private void showClusters() {
        for (int i = 0; i < _clusters.size(); i++) {
            double setosaClusterCount = 0;
            double virginicaClusterCount = 0;
            double versicolorClusterCount = 0;
            double totalElements = 0;
            Cluster c = _clusters.get(i);
            for(Vector vector : _clusters.get(i).getPoints()){
                totalElements++;
                if(vector.get_name().equals("Iris-setosa")){
                    setosaClusterCount++;
                } else if (vector.get_name().equals("Iris-virginica")){
                    virginicaClusterCount++;
                } else if (vector.get_name().equals("Iris-versicolor")){
                    versicolorClusterCount++;
                }
            }

            double setosaRatio = setosaClusterCount/totalElements;
            double virginicaRatio = virginicaClusterCount/totalElements;
            double versicolorRatio = versicolorClusterCount/totalElements;
            double entropy = calculateEntropy(setosaRatio,virginicaRatio,versicolorRatio);

            //System.out.println(setosaRatio + " " + virginicaRatio+ " " + versicolorRatio);
            //double entropy = (-1)*(setosaRatio*(log2(setosaRatio)) + virginicaRatio*(log2(virginicaRatio)) + versicolorRatio*(log2(versicolorRatio)));
            //System.out.println("--->there are " + setosaClusterCount + " setosas and virginicas: " + virginicaClusterCount + " and versicolors: " + versicolorClusterCount);

            c.showCluster();
            System.out.println("Entropy: " + entropy);
            System.out.println("=============================================================================================");
        }
    }

    public double calculateEntropy(double sRatio, double viRatio, double veRatio){
        double entropy = 0;
        if (sRatio != 0 && sRatio != 1){
            entropy += log2(sRatio) * sRatio;
        }
        if(viRatio != 0 && viRatio != 1){
            entropy += log2(viRatio) * viRatio;
        }
        if (veRatio != 0 && veRatio != 1){
            entropy += log2(veRatio) * veRatio;
        }
        return entropy*(-1);
    }

    private double log2(double n) {
        return Math.log(n) / Math.log(2);
    }

    public void calculate() {
        boolean finish = false;
        int iteration = 0;

        while(!finish) {
            clearClusters();
            List<Vector> lastCentroids = getCentroids();
            assignCluster();
            calculateCentroids();
            iteration++;
            List<Vector> currentCentroids = getCentroids();
            double distance = 0;
            for(int i = 0; i < lastCentroids.size(); i++) {
                distance += Vector.distance(lastCentroids.get(i),currentCentroids.get(i));
            }
            System.out.println("############################################################################################");
            System.out.println("Iteration: " + iteration);
            showClusters();
            if(distance == 0) {
                finish = true;
            }

        }
    }

    private void clearClusters() {
        for(Cluster cluster : _clusters) {
            cluster.clear();
        }
    }

    private List<Vector> getCentroids() {
        List<Vector> centroids = new ArrayList<>();
        for(Cluster cluster : _clusters) {
            Vector aux = cluster.get_centroid();
            Vector vector = new Vector(aux.get_x(),aux.get_y(),aux.get_y(),aux.get_g());
            centroids.add(vector);
        }
        return centroids;
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min;
        int cluster = 0;
        double distance;

        for(Vector vector : _vectors) {
            min = max;
            for(int i = 0; i < _clusters.size(); i++) {
                Cluster c = _clusters.get(i);
                distance = Vector.distance(vector, c.get_centroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            vector.setCluster(cluster);
            _clusters.get(cluster).addPoint(vector);
        }
    }

    private void calculateCentroids() {

        for(Cluster cluster : _clusters) {
            double sumX = 0;
            double sumY = 0;
            double sumZ = 0;
            double sumG = 0;
            List<Vector> vectors = cluster.getPoints();
            int pointsCount = vectors.size();

            for(Vector vector : vectors) {
                sumX += vector.get_x();
                sumY += vector.get_y();
                sumZ += vector.get_z();
                sumG += vector.get_g();
            }

            Vector centroid = cluster.get_centroid();
            if(pointsCount > 0) {
                double newX = sumX / pointsCount;
                double newY = sumY / pointsCount;
                double newZ = sumZ / pointsCount;
                double newG = sumG / pointsCount;
                centroid.set_x(newX);
                centroid.set_y(newY);
                centroid.set_z(newZ);
                centroid.set_g(newG);
            }
        }
    }
}
