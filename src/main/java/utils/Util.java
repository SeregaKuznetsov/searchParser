package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

}
