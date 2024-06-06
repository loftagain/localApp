package com.company.localApp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @GetMapping
    public String getNews(Model model) {
        List<Article> articles = new ArrayList<>();
        try {
            // Connect to the website
            Document doc = Jsoup.connect("https://www.theonion.com").get();

            // Select elements that represent news articles
            Elements newsElements = doc.select("article");

            for (Element newsElement : newsElements) {
                // Extract the title and link
                String title = newsElement.select("h2").text();
                String link = newsElement.select("a").attr("href");

                // Extract a preview/summary of the article
                String preview = newsElement.select("p").text(); // Assuming the preview is within a <p> tag

                // Extract the image URL
                String imageUrl = newsElement.select("img").attr("src");
                if (!isImageValid(imageUrl)) {
                    imageUrl = null;
                }

                // Add to articles list
                articles.add(new Article(title, link, imageUrl, preview));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("articles", articles);
        return "news";
    }

    private boolean isImageValid(String imageUrl) {
        try {
            if (imageUrl == null || imageUrl.isEmpty()) {
                return false;
            }
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            return false;
        }
    }
}

class Article {
    private String title;
    private String link;
    private String imageUrl;
    private String preview;

    public Article(String title, String link, String imageUrl, String preview) {
        this.title = title;
        this.link = link;
        this.imageUrl = imageUrl;
        this.preview = preview;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPreview() {
        return preview;
    }
}
