package vector;

import utils.Util;

import java.io.File;

public class VectorComparator {
    public static String getTfIdfDocVector(int i) {
        StringBuilder vector = new StringBuilder();
        File dir = new File("src/main/resources/tfidf");
        File[] arrFiles = dir.listFiles();
        for (File arrFile : arrFiles) {
            String fileVector = Util.readArticle(arrFile.getPath());
            String[] values = fileVector.split(" ");
            vector.append(values[i]).append(" ");
        }
        return vector.toString();
    }

    public static double getScalarProduct(String vectorOfTheFirstDoc, String tf_idf_vector) {
        String[] docVector = vectorOfTheFirstDoc.split(" ");
//        System.out.println("Length of docVector - " + docVector.length);
        String[] queryVector = tf_idf_vector.split(" ");
//        System.out.println("Length of query vector - " + queryVector.length);
        double value = 0;
        for (int i = 0; i < docVector.length; i++) {
            double multi = Double.valueOf(docVector[i]) * Double.valueOf(queryVector[i]);
            value+=multi;
        }
        return value;
    }

    public static double getVectorLength(String vectorOfTheFirstDoc) {
        String[] docVector = vectorOfTheFirstDoc.split(" ");
        double sum = 0;
        for (String s : docVector) {
            sum+=Math.pow(Double.valueOf(s), 2);
        }
        return Math.sqrt(sum);
    }

    public static double getCosign(double scalarProduct, double vectorLengthMulti) {
        if (scalarProduct == 0.0 && vectorLengthMulti == 0.0) {
            return  0.0;
        } else
            return scalarProduct/vectorLengthMulti;
    }
}
