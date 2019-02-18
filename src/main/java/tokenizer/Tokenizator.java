package tokenizer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Tokenizator {

    public static void main(String[] args) {

        for (int i = 1; i <= 80; i ++) {
            StringBuilder stringBuilder = new StringBuilder();
            String article = readArticle("src/main/resources/articles/" + i + ".txt");
            String[] words = article.split(" ");
            for (String word : words) {
                word = replaceBadChars(word);
                word = Stemmer.stem(word);
                stringBuilder.append(word.trim());
                stringBuilder.append("  ");
            }
            writeArticle(stringBuilder.toString(), i);
        }
    }

    private static String replaceBadChars(String word) {
        word = word.replace(".", "")
            .replace(")", "")
            .replace(":", "")
            .replace("(", "")
            .replace(";", "")
            .replace("=", "")
            .replace("?", "")
            .replace(",", "")
            .replace("!", "")
            .replace("[", "")
            .replace("]", "")
            .replace("%", "")
            .replace("%", "")
            .replace("→", "")
            .replace("«", "")
            .replace("…", "")
            .replace("...", "");
        if (!(word.equals("–")) && !word.equals("—")) {
            return word;
        }
        return "";
    }

    private static String readArticle(String filePath) {
        String content = null;
        try {
            content = Files.lines(Paths.get(filePath)).reduce("", String::concat);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeArticle(String text, int i) {
        try(FileWriter articleWriter = new FileWriter("src/main/resources/tokenizedArticles/"+ i +".txt"))
        {
            articleWriter.write(text);
            articleWriter.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
