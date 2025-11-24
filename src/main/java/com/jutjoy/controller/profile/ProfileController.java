package com.jutjoy.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.service.profile.*;

@Controller
public class ProfileController {

    @Autowired
    private ProfileCreateService profileCreateService;
    @Autowired
    private ProfileListService profileListService;
    @Autowired
    private ProfileFindService profileFindService;
    @Autowired
    private ProfileEditService profileEditService;
    @Autowired
    private ProfileDeleteService profileDeleteService;

    // 新規作成
    @GetMapping("/profile/create")
    public String create(@ModelAttribute("form") ProfileCreateForm form) {
        return "profile/create";
    }

    @PostMapping("/profile/create")
    public String create(@Validated @ModelAttribute("form") ProfileCreateForm form,
                         BindingResult result) {
        if (result.hasErrors()) return "profile/create";
        profileCreateService.create(form);
        return "redirect:/profile/create/complete";
    }

    @GetMapping("/profile/create/complete")
    public String complete() { return "profile/complete"; }

    // 一覧
    @GetMapping("/profile/list")
    public String list(Model model) {
        model.addAttribute("profileList", profileListService.list());
        return "profile/list";
    }

    // 編集
    @GetMapping("/profile/edit")
    public String edit(@RequestParam("id") Integer id,
                       @ModelAttribute("form") ProfileEditForm form) {
        var profile = profileFindService.findById(id);
        form.setId(profile.getId());
        form.setName(profile.getName());
        form.setGender(profile.getGender());
        form.setHobby(profile.getHobby());
        form.setIntroduction(profile.getIntroduction());
        return "profile/edit";
    }

    @PostMapping("/profile/edit")
    public String edit(@Validated @ModelAttribute("form") ProfileEditForm form,
                       BindingResult result) {
        if (result.hasErrors()) return "profile/edit";
        profileEditService.update(form);
        return "redirect:/profile/edit/complete";
    }

    @GetMapping("/profile/edit/complete")
    public String editComplete() { return "profile/edit_complete"; }

    // 削除
    @PostMapping("/profile/delete")
    public String delete(@RequestParam("id") Integer id) {
        profileDeleteService.delete(id);
        return "redirect:/profile/list";
    }
}
