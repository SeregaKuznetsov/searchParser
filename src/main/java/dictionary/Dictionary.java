package dictionary;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Dictionary {

    private static Set<String> dictionary = new HashSet<>();

    public static void createDictinary(String[] args) {

        System.out.println(getDocsCount("src/main/resources/dictionary"));
        Arrays.stream(new File("src/main/resources/dictionary").listFiles()).forEach(File::delete);

        int docsCounter;

        for (docsCounter = 1; docsCounter <= getDocsCount("src/main/resources/tokenizedArticles"); docsCounter ++) {
            String article = readArticle("src/main/resources/tokenizedArticles/" + docsCounter + ".txt");
            String[] words = article.split(" ");
            dictionary.addAll(Arrays.asList(words));
            saveCurrentDictionary(docsCounter - 1);
            clearCurrentDictionary();
        }

        System.out.println(getDocsCount("src/main/resources/dictionary"));


    }

    public static String simpleFindArticle(String query) {
        StringBuilder sb = new StringBuilder();
        File f = new File("src/main/resources/dictionary/" + query + ".txt");
        if(f.exists() && !f.isDirectory()) {
            String doc = readArticle(f.getPath());
            for (int i = 0; i < doc.length(); i++) {
                if (doc.charAt(i) == '1') {
                    sb.append(i+1).append("\n");
                }
            }
            return sb.toString();
        }
        return "К сожалению, ничего не удалось найти по запросу " + query;
    }

//    private static void findArticles(String query) {
//        String[] words = query.split(" ");
//        Set<String> mathesSet = new HashSet<>();
//
//        for (int i = 0; i < words.length; i++) {
//            mathesSet.addAll(findArticlesByWord(words[i]));
//        }
//
//        if (!mathesSet.isEmpty()) {
//
//        } else {
//            return "К сожалению, ничего не удалось найти по запросу " + query;
//        }
//
//
//    }

//    private static Set<String> findArticlesByWord(String word) {
//        Set<String> results = new HashSet<>();
//        File f = new File("src/main/resources/dictionary/" + word + ".txt");
//        if(f.exists() && !f.isDirectory()) {
//            String doc = readArticle(f.getPath());
//            for (int i = 0; i < doc.length(); i++) {
//                if (doc.charAt(i) == '1') {
//                    results.add(String.valueOf(i));
//                }
//            }
//            return results;
//        }
//        return null;
//    }

    private static void saveCurrentDictionary(int docsCounter) {
        File f = null;
        for (String s : dictionary) {
            f = new File("src/main/resources/dictionary/" + s + ".txt");
            checkExisting(f.getPath());
            writeInIndexPos(f.getPath(), docsCounter);
        }
    }

    private static void clearCurrentDictionary() {
        dictionary.clear();
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

    private static void showDictionary() {
        for (String word : dictionary) {
            System.out.println(word);
        }
    }

    private static long getDocsCount(String path) {
        try (Stream<Path> files = Files.list(Paths.get(path))) {
            return files.count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void checkExisting(String path) {
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {

        } else {
            try {
                f.createNewFile();
                StringBuilder stringBuilder = new StringBuilder((int) getDocsCount("src/main/resources/tokenizedArticles"));
                for (int i = 0; i < stringBuilder.capacity(); i++) {
                  stringBuilder.append('0');
                }
                try (FileWriter fileWriter = new FileWriter(new File(path))) {
                    fileWriter.write(stringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    try (BufferedReader r = Files.newBufferedReader(path, encoding)) {
        r.lines().forEach(System.out::println);
    }
     */

    private static void writeInIndexPos(String path, int  i) {
        String line = "0";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(path)));
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder(line);
        stringBuilder.setCharAt(i, '1');

        try (FileWriter fileWriter = new FileWriter(new File(path))) {
            fileWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
