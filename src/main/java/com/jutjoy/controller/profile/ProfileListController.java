package com.jutjoy.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jutjoy.domain.service.profile.ProfileListService;

@Controller
public class ProfileListController {

    @Autowired
    private ProfileListService profileListService;

    @GetMapping("/profile/list")
    public String list(Model model) {
        model.addAttribute("profiles", profileListService.findAll());
        return "profile/list";
    }
}
