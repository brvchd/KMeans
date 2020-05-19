package algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class DataReader {
    public static List<Vector> load(String fileName) {
        String line;
        List<Vector> vectors = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while ((line = bufferedReader.readLine()) != null) {
                String[] iris = line.split(",");
                vectors.add(new Vector(Double.parseDouble(iris[0]), Double.parseDouble(iris[1]), Double.parseDouble(iris[2]), Double.parseDouble(iris[3]), iris[4]));
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vectors;
    }
}
