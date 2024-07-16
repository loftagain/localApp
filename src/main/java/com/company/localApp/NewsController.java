package com.company.localApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private RssService rssService;

    @GetMapping
    public String getNews(Model model) {
        List<Article> articles = rssService.fetchRssFeed();
        model.addAttribute("articles", articles);
        model.addAttribute("pageTitle", "news");
        model.addAttribute("styles", "news");
        model.addAttribute("pageContent", "news");
        return "news";
    }
}
