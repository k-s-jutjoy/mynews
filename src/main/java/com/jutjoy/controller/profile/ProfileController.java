package com.jutjoy.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.service.profile.ProfileCreateService;
import com.jutjoy.domain.service.profile.ProfileDeleteService;
import com.jutjoy.domain.service.profile.ProfileEditService;
import com.jutjoy.domain.service.profile.ProfileFindService;
import com.jutjoy.domain.service.profile.ProfileListService;

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


    // ★ ① プロフィール新規作成
    @GetMapping("/profile/create")
    public String create(@ModelAttribute("form") ProfileCreateForm form) {
        return "profile/create";
    }

    @PostMapping("/profile/create")
    public String create(@Validated @ModelAttribute("form") ProfileCreateForm form,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            return "profile/create";
        }

        profileCreateService.create(form);

        return "redirect:/profile/create/complete";
    }

    @GetMapping("/profile/create/complete")
    public String complete() {
        return "profile/complete";
    }


    // ★ ② プロフィール一覧
    @GetMapping("/profile/list")
    public String list(Model model) {

        model.addAttribute("profileList", profileListService.list());

        return "profile/list";
    }


    // ★ ③ プロフィール編集画面表示
    @GetMapping("/profile/edit")
    public String edit(@RequestParam("id") Integer id,
                       @ModelAttribute("form") ProfileEditForm form,
                       Model model) {

        var profile = profileFindService.findById(id);

        form.setId(profile.getId());
        form.setName(profile.getName());
        form.setGender(profile.getGender());
        form.setHobby(profile.getHobby());
        form.setIntroduction(profile.getIntroduction());

        return "profile/edit";
    }


    // ★ ④ プロフィール編集処理
    @PostMapping("/profile/edit")
    public String edit(@Validated @ModelAttribute("form") ProfileEditForm form,
                       BindingResult result) {

        if (result.hasErrors()) {
            return "profile/edit";
        }

        profileEditService.update(form);

        return "redirect:/profile/edit/complete";
    }

    @GetMapping("/profile/edit/complete")
    public String editComplete() {
        return "profile/edit_complete";
    }


    // ★ ⑤ プロフィール削除
    @PostMapping("/profile/delete")
    public String delete(@RequestParam("id") Integer id) {

        profileDeleteService.delete(id);

        return "redirect:/profile/list";
    }

}
