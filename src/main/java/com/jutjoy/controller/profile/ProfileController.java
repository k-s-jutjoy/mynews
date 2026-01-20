package com.jutjoy.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;   // ★ 追加
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jutjoy.domain.entity.profile.Profile;
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

    // GET: 新規作成画面表示 → view: profile/create.html
    // プロフィール新規作成
    @GetMapping("/profile/create")
    public String create(@ModelAttribute("form") ProfileCreateForm form) {
        return "profile/create";
    }

    // POST: 新規登録 → Service: create → redirect: /profile/create/complete
    @PostMapping("/profile/create")
    public String create(@Validated @ModelAttribute("form") ProfileCreateForm form,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "profile/create"; // 入力エラー時 viewへ戻る
        }
        profileCreateService.create(form);
        return "redirect:/profile/create/complete";
    }

    // GET: 新規作成完了 → view: profile/complete.html
    @GetMapping("/profile/create/complete")
    public String complete() {
        return "profile/complete";
    }

    // GET: 一覧表示 → Service: list → view: profile/list.html
    // ★ プロフィール一覧（登録日時の新しい順でページング）
    @GetMapping("/profile/list")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {

        int pageSize = 7; // 1ページあたりの件数

        // ★ PageRequest に Sort を追加（registeredDate の新しい順）
        Pageable pageable = PageRequest.of(
                page - 1,
                pageSize,
                Sort.by(Sort.Direction.ASC, "id")  // ← ここが追加
        );

        Page<Profile> profilePage = profileListService.list(pageable);

        model.addAttribute("profileList", profilePage.getContent());
        model.addAttribute("totalPages", profilePage.getTotalPages());
        model.addAttribute("currentPage", page);

        return "profile/list";
    }

    // GET: 編集画面表示 → Service: find → view: profile/edit.html
    @GetMapping("/profile/edit")
    public String edit(@RequestParam("id") Integer id,
                       @ModelAttribute("form") ProfileEditForm form,
                       Model model) {
        Profile profile = profileFindService.findById(id);

        form.setId(profile.getId());
        form.setName(profile.getName());
        form.setGender(profile.getGender());
        form.setHobby(profile.getHobby());
        form.setIntroduction(profile.getIntroduction());
        model.addAttribute("profile", profile);

        return "profile/edit";
    }

    // POST: 更新処理 → Service: update → redirect: /profile/edit/complete
    @PostMapping("/profile/edit")
    public String edit(@Validated @ModelAttribute("form") ProfileEditForm form,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "profile/edit"; // 入力エラー時
        }
        profileEditService.update(form);
        return "redirect:/profile/edit/complete";
    }

    // GET: 更新完了 → view: profile/edit_complete.html
    @GetMapping("/profile/edit/complete")
    public String editComplete() {
        return "profile/edit_complete";
    }

    // POST: 削除処理 → Service: delete → redirect: /profile/list
    @PostMapping("/profile/delete")
    public String delete(@RequestParam("id") Integer id) {
        profileDeleteService.delete(id);
        return "redirect:/profile/list";
    }
}
