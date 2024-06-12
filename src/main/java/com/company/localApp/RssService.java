package com.company.localApp;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssService {

    private static final String RSS_FEED_URL = "https://www.theonion.com/rss";

    public List<Article> fetchRssFeed() {
        List<Article> articles = new ArrayList<>();
        try {
            URL feedSource = new URL(RSS_FEED_URL);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));

            for (SyndEntry entry : feed.getEntries()) {
                String title = entry.getTitle();
                String link = entry.getLink();
                String descriptionHtml = entry.getDescription().getValue();

                // Use Jsoup to parse and clean the HTML content
                Document doc = Jsoup.parse(descriptionHtml);
                Element paragraph = doc.select("p").first();
                String summary = paragraph != null ? paragraph.text() : "";

                String imageUrl = null;
                Element imageElement = doc.select("img").first();
                if (imageElement != null) {
                    imageUrl = imageElement.attr("src");
                }

                articles.add(new Article(title, link, imageUrl, summary));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }
}
