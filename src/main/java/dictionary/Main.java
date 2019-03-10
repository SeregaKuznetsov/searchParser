package dictionary;

import tokenizer.Stemmer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите слова, которые хотели бы найти");
        String query = sc.nextLine();
        Dictionary.findArticles(Stemmer.stem(query));
//        System.out.println(Dictionary.simpleFindArticle(query));
    }
}
