package dictionary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите слово, которое хотели бы найти");
        String query = sc.nextLine();
//        findArticles(query);
        System.out.println(Dictionary.simpleFindArticle(query));
    }
}
