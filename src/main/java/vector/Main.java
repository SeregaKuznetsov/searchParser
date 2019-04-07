package vector;

import tfidf.TF_IDF;
import tokenizer.Stemmer;
import utils.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    /*

        1. построить TF-IDF вектор запроса
        2. Взять вектор документа
        3. Посчитать скалярное произведение векторов, через пары
        4. Посчитать длины векторов
        5. Посчитать косинус для каждого документа и вектора запроса
        6. Отсортировать полученные косинусы в порядке убывания
        7. Вывести полученный список

     */

    public static void main(String[] args) {
        Map<String, Double> rankedPages = new HashMap<>();
        Scanner sc = new Scanner(System.in);
//        System.out.println("Введите запрос");
//        String query = sc.nextLine();

        String query = "Сегодня праздник у программистов";
//        String query = "Тестирование новой фичи";
        String stemmedQuery = Stemmer.stemQuery(query);
        double tfValue = TF_IDF.getTfValue(stemmedQuery);
//        System.out.println("Tf value - " + tfValue);
//        String tfVector = TF_IDF.getTfVector(stemmedQuery);
//        System.out.println("tf vector - " + tfVector);
        String idfVector = TF_IDF.getIdfVector(stemmedQuery);
//        System.out.println("idf vector - " + idfVector);
        String tf_idf_vector = TF_IDF.getTfIdfVector(stemmedQuery, tfValue);
//        System.out.println("tf-idf vector of query - " + tf_idf_vector);

        for (int i = 0; i < 100; i++) {
            String vectorOfTheFirstDoc = VectorComparator.getTfIdfDocVector(i);
//            System.out.println("tf-idf vector of doc " + i + " - " + vectorOfTheFirstDoc);

            double vectorLength = VectorComparator.getVectorLength(vectorOfTheFirstDoc);
//            System.out.println("doc vector length - " + vectorLength);

            double secondVectorLength = VectorComparator.getVectorLength(tf_idf_vector);
//            System.out.println("query vector length - " + vectorLength);

            double vectorLengthMulti = vectorLength * secondVectorLength;

            double scalarProduct = VectorComparator.getScalarProduct(vectorOfTheFirstDoc, tf_idf_vector);
//            System.out.println("scalar products of two vectors - " + scalarProduct);

            double cosAngle = VectorComparator.getCosign(scalarProduct, vectorLengthMulti);
//            System.out.println("Косинус угла между векторами - " + cosAngle);

            rankedPages.put(Util.getLinkForThatDocNumber(String.valueOf(i)), cosAngle);
        }


         rankedPages.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

    }
}
