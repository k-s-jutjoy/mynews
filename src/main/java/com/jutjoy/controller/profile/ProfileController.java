package com.jutjoy.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort; // ★ ページング＋並び順制御用
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // ★ 完了メッセージ表示用

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.service.profile.*;

/*
 * Controller は画面制御のみを担当し、
 * 実際の業務処理はすべて Service に委譲する設計
 */
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

	// プロフィール新規作成（入力画面）
	/*
	 * ・GET は画面表示のみ
	 * ・Form を Model にバインドすることで 入力値の保持・エラーメッセージ表示を可能にしている
	 */
	@GetMapping("/profile/create")
	public String create(@ModelAttribute("form") ProfileCreateForm form) {
		return "profile/create";
	}

	// プロフィール新規作成（登録処理）
	@PostMapping("/profile/create")
	public String create(
			@Validated @ModelAttribute("form") ProfileCreateForm form,
			BindingResult result,
			RedirectAttributes attributes) { // ★ メッセージ受け渡し
	
		if (result.hasErrors()) {
			return "profile/create";
		}

		// 作成後 → detail へ
		Integer id = profileCreateService.create(form);

		// ★ 作成完了メッセージを FlashAttribute で渡す（リロードしても出ない）
		attributes.addFlashAttribute("message", "プロフィールを作成しました。");

		return "redirect:/profile/detail?id=" + id;
	}

	// プロフィール詳細
	/*
	 * ・作成後、編集後共通で遷移
	 * ・閲覧のみ（編集しない）
	 */
	@GetMapping("/profile/detail")
	public String detail(@RequestParam("id") Integer id, Model model) {
		Profile profile = profileFindService.findById(id);
		model.addAttribute("profile", profile);
		return "profile/detail";
	}

    // プロフィール一覧（ページング＋ID順）
	@GetMapping("/profile/list")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		int pageSize = 7;  // 1ページあたりの表示件数

		Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "id"));
		Page<Profile> profilePage = profileListService.list(pageable);

		model.addAttribute("profileList", profilePage.getContent());
		model.addAttribute("totalPages", profilePage.getTotalPages());
		model.addAttribute("currentPage", page);

		return "profile/list";
	}

	// プロフィール編集（入力画面）
	@GetMapping("/profile/edit")
	public String edit(@RequestParam("id") Integer id, @ModelAttribute("form") ProfileEditForm form, Model model) {
		Profile profile = profileFindService.findById(id);

		form.setId(profile.getId());
		form.setName(profile.getName());
		form.setGender(profile.getGender());
		form.setHobby(profile.getHobby());
		form.setIntroduction(profile.getIntroduction());

		model.addAttribute("profile", profile);

		return "profile/edit";
	}

	// プロフィール編集（更新処理）
	@PostMapping("/profile/edit")
	public String edit(
			@Validated @ModelAttribute("form") ProfileEditForm form,
			BindingResult result,
			RedirectAttributes attributes) { // ★ メッセージ受け渡し

		if (result.hasErrors()) {
			return "profile/edit";
		}

		profileEditService.update(form);

		// ★ 編集完了メッセージ
		attributes.addFlashAttribute("message", "プロフィールを更新しました。");

		// 編集後 → detail へ
		return "redirect:/profile/detail?id=" + form.getId();
	}

	// プロフィール削除
	@PostMapping("/profile/delete")
	public String delete(@RequestParam("id") Integer id) {
		profileDeleteService.delete(id);
		return "redirect:/profile/list";
	}
}
