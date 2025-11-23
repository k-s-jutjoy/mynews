package com.jutjoy.controller.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ← これが正しい
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jutjoy.domain.entity.News;
import com.jutjoy.domain.form.news.NewsCreateForm;
import com.jutjoy.domain.service.news.NewsCreateService;
import com.jutjoy.domain.service.news.NewsListService;
import com.zaxxer.hikari.util.FastList;

@Controller
public class NewsController {
    @Autowired
    private NewsListService newsListService;
    @GetMapping("/news/list")
    public String list(@RequestParam(required = false) String title, Model model) {

        FastList<News> newsList = (FastList<News>) newsListService.list(title);
        model.addAttribute("newsList", newsList);

        return "news/list";
    }
    @Autowired
    private NewsCreateService newsCreateService;

    @GetMapping("/news/create/complete")
    public String complete() {
        return "news/complete";
    }

    @GetMapping("/news/create")
    public String create(@ModelAttribute("form") NewsCreateForm newsCreateForm) {
        return "news/create";
    }

    @PostMapping("/news/create")
    public String create(@Validated @ModelAttribute("form") NewsCreateForm newsCreateForm,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            return "news/create";
        }

        newsCreateService.create(newsCreateForm);
        return "redirect:/news/create/complete";
    }
}
