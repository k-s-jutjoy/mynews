package com.jutjoy.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.service.profile.ProfileFindService;

@Controller
public class ProfileDetailController {

    @Autowired
    private ProfileFindService profileFindService;

    @GetMapping("/profile/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Profile profile = profileFindService.findById(id);
        model.addAttribute("profile", profile);
        return "profile/detail";
    }
}
