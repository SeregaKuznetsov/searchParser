package linker;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLLinkExtractor {

    private Pattern patternTag, patternLink;
    private Matcher matcherTag, matcherLink;

        private static final String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
    private static final String HTML_A_HREF_TAG_PATTERN =
            "http://";


    public HTMLLinkExtractor() {
        patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
        patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
    }

    /**
     * Validate html with regular expression
     *
     * @param html html content for validation
     * @return Vector links and link text
     */
    public ArrayList<String> grabHTMLLinks(final String html) {

        ArrayList<String> result = new ArrayList<>();

        matcherTag = patternTag.matcher(html);

        while (matcherTag.find()) {

            String href = matcherTag.group(1); // href
            String linkText = matcherTag.group(2); // link text

            matcherLink = patternLink.matcher(href);

            while (matcherLink.find()) {

                String link = matcherLink.group(1); // link

                result.add(link);

            }

        }

        return result;

    }
}