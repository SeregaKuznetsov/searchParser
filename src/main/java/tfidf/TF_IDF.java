package tfidf;

import tokenizer.Stemmer;
import utils.Util;

import java.io.File;
import java.sql.SQLOutput;
import java.text.DecimalFormat;

import static utils.Util.readArticle;

public class TF_IDF {
    public static String getTf(String path,String term) {
        double matchCount = 0;
        double wordsCount = 0;
        term = term.substring(0,term.length() - 4);
        String article = readArticle(path);
        String[] words = article.split(" ");
        for (String word : words) {
           if (word.equals("")) {
                continue;
           }
           else {
               if (word.equals(term)) {
                   matchCount++;
               }
               wordsCount++;
           }
        }

//        System.out.println("matchCount - " + matchCount);
//        System.out.println("wordss count - " + wordsCount);
//        System.out.println(" matchcount / words count - " + matchCount/wordsCount);
        return new DecimalFormat("#0.00000").format(matchCount/wordsCount);
    }

    public static double getIdf(String vector) {
        int matchCounter = 0;
        for (int i = 0; i < vector.length(); i++) {
            if (vector.charAt(i) == '1') {
                matchCounter++;
            }
        }

//        System.out.println("Vector - " + vector);
//        System.out.println("Match counter - " + matchCounter);
//        System.out.println("Vector length - " + vector.length());
//        System.out.println("Log 10 value - " + Math.log10(vector.length()/matchCounter));
        return Math.log10(vector.length()/matchCounter);
    }


    public static String getTfVector(String vector, String term) {
        String tf_vector = "";
        for (int i = 0; i < vector.length(); i++) {
            if (vector.charAt(i) == '1') {
                String tf = getTf("src/main/resources/tokenizedArticles/" + (i+1)  + ".txt", term);
                tf_vector+=tf + " ";
            } else {
                tf_vector+="0 ";
            }
        }
        return tf_vector;
    }

    public static String getTfIdfVector(double idf, String tf_vector) {
        String tf_idf_vector = "";
        String [] values = tf_vector.split(" ");
        for (String value : values) {
            if (value.equals("0")) {
                tf_idf_vector += "0 ";
                continue;
            } else {
                double tfidf = Double.valueOf(value.replace(",",".")) * idf;
                tf_idf_vector += tfidf + " ";
            }
        }
        return tf_idf_vector;
    }
}
