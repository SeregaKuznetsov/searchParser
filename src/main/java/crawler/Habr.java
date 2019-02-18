package crawler;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;

public class Habr {

    public static void main(String[] args) {

        Document doc;
        int exceptionCounter = 0;
        int counter = 1;
        int articleCount = 80;
        String url;

        for (int i = 100000; counter < articleCount + 1 && i < 360000;  i++) {
            try {
                url = "https://habrahabr.ru/post/" + i +"/";
                doc = Jsoup.connect(url).get();
                System.out.println("Number of article: " + i);
                writeArticle(doc, counter);
                writeIndex(counter, url);
                counter++;

            } catch (HttpStatusException ex) {
//                System.out.println("Exception count: " + ++exceptionCounter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeIndex(int counter, String url) {
        try {
            FileWriter indexWriter = new FileWriter("src/main/resources/articles/index.txt", true);
            indexWriter.write(counter + ".txt:" + url + "\n");
            indexWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getText(Document jDoc) {
        return jDoc.select("div.post__text.post__text-html.js-mediator-article").text();
    }

    private static void writeArticle(Document jdoc, int i) {
        try(FileWriter articleWriter = new FileWriter("src/main/resources/articles/"+ i +".txt"))
        {
            articleWriter.write(getText(jdoc));
            articleWriter.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
