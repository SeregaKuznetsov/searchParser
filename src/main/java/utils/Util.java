package utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class Util {

    public static String readArticle(String filePath) {
        String content = null;
        try {
            content = Files.lines(Paths.get(filePath)).reduce("", String::concat);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getDocsCount(String path) {
        try (Stream<Path> files = Files.list(Paths.get(path))) {
            return files.count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void writeArticle(String text, String name) {
        try(FileWriter articleWriter = new FileWriter("src/main/resources/tfidf/"+ name))
        {
            articleWriter.write(text);
            articleWriter.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void parseForLink(String vector) {
        try (BufferedReader r = Files.newBufferedReader(Paths.get("src/main/resources/articles/index.txt"))) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < vector.length(); i++) {
                if (vector.charAt(i) == '1') {
                    System.out.println(r.readLine());
                } else {
                    r.readLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parseForLink(Map<String, Double> rankedMap) {
        for (Map.Entry<String, Double> stringDoubleEntry : rankedMap.entrySet()) {
            String link = getLinkForThatDocNumber(stringDoubleEntry.getKey());
            System.out.println(link + " " + stringDoubleEntry.getValue());
        }

    }

    public static String getLinkForThatDocNumber(String key) {
        try (BufferedReader r = Files.newBufferedReader(Paths.get("src/main/resources/articles/index.txt"))) {
            for (int i = 0; i < Integer.valueOf(key); i++) {
                r.readLine();
            }
            return r.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
