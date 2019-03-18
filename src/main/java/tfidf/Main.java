package tfidf;

import utils.Util;

import java.io.File;

public class Main {

    /*
        Для каждого файла в словаре вычислить IDF = log 10 от числа документов / на колличество единиц в файле
        Для каждого файла в словаре в котором для каждой позиции, где индекс = 1 вычислить TF
        Вычислить и записать в каждую позицию вектора TF * IDF

        TF (term frequency — частота слова) — отношение числа вхождений некоторого слова к общему числу слов документа.
        Таким образом, оценивается важность слова в пределах отдельного документа.

    */

    public static void main(String[] args) {
        long docsAmount = Util.getDocsCount("src/main/resources/dictionary");
        File dir = new File("src/main/resources/dictionary"); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        for (File file : arrFiles) {
            String vector = Util.readArticle(file.getPath());
            double idf = TF_IDF.getIdf(vector);
            String tf_vector = TF_IDF.getTfVector(vector, file.getName());
            String tf_idf_vector = TF_IDF.getTfIdfVector(idf, tf_vector);
            System.out.println("idf of file " + file.getPath() + " is " + idf);
            System.out.println("tf vector " + tf_vector);
            System.out.println("tf-idf vector - " + tf_idf_vector);
            Util.writeArticle(tf_idf_vector, file.getName());
        }
    }

}
