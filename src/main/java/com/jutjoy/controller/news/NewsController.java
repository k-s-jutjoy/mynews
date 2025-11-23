package com.jutjoy.controller.news;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jutjoy.domain.entity.News;
import com.jutjoy.domain.form.news.NewsCreateForm;
import com.jutjoy.domain.service.news.NewsCreateService;
import com.jutjoy.domain.service.news.NewsListService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsListService newsListService = new NewsListService();
    private final NewsCreateService newsCreateService = new NewsCreateService();
    /**
     * ニュース一覧
     */
    @GetMapping("/news/list")
    public String list(@RequestParam(required = false) String title,
                       Model model) {

        List<News> newsList = newsListService.list(title);
        model.addAttribute("newsList", newsList);

        return "news/list";
    }

    @GetMapping("/news/create")
    public String create(@ModelAttribute("form") NewsCreateForm form) {
        return "news/create";
    }


    @PostMapping("/news/create")
    public String create(@Validated @ModelAttribute("form") NewsCreateForm form,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            return "news/create";
        }

        newsCreateService.create(form);

        return "redirect:/news/create/complete";
    }


    @GetMapping("/news/create/complete")
    public String complete() {
        return "news/complete";
    }
}
