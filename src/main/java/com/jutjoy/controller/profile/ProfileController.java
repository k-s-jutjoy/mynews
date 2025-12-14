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
	/*
	 * ・@Validated により Form のバリデーションを実行 
	 * ・エラー時は登録処理を行わず、入力画面へ戻す 
	 * ・正常時のみ Serviceに登録処理を委譲 
	 * ・PRGパターンを採用し、二重送信を防止
	 */
	@PostMapping("/profile/create")
	public String create(@Validated @ModelAttribute("form") ProfileCreateForm form, BindingResult result) {
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
    // 新規作成 完了画面
    /*
     * ・処理を持たない純粋な完了画面
     */

    // プロフィール一覧（ページング＋ID順）
    /*
     * ・一覧件数が増えても対応できるようページングを採用
     * ・並び順は ID 昇順（登録順）とし、
     *   画面表示の順序を明確にしている
     * ・Controller では条件指定のみ行い、
     *   実際の取得処理は Service に委譲
     */
	@GetMapping("/profile/list")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {

		int pageSize = 7;  // 1ページあたりの表示件数

        // PageRequest.of は 0 始まりのため page - 1
        // Sort を指定することで DB 取得時点で並び替えを行う
  		Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "id") 
		);

		Page<Profile> profilePage = profileListService.list(pageable);
        /*
         * View には以下の情報を渡す
         * ・profileList : 現在のページのデータ
         * ・totalPages  : 総ページ数（ページネーション用）
         * ・currentPage : 現在のページ番号
         */
		model.addAttribute("profileList", profilePage.getContent());
		model.addAttribute("totalPages", profilePage.getTotalPages());
		model.addAttribute("currentPage", page);

		return "profile/list";
	}

    // プロフィール編集（入力画面）
    /*
     * ・編集対象は id で特定
     * ・DB から取得した Entity の値を Form に詰め替えて表示
     * ・Entity を直接 View にバインドしないことで安全性を確保
     */
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
    /*
     * ・新規作成と同様に Form バリデーションを実施
     * ・エラー時は編集画面へ戻す
     * ・正常時のみ更新処理を Service に委譲
     */
	@PostMapping("/profile/edit")
	public String edit(@Validated @ModelAttribute("form") ProfileEditForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "profile/edit";
		}
		profileEditService.update(form);
		return "redirect:/profile/edit/complete";
	}
    // 編集 完了画面

	@GetMapping("/profile/edit/complete")
	public String editComplete() {
		return "profile/edit_complete";
	}

    // プロフィール削除
    /*
     * ・削除処理は GET ではなく POST のみに限定
     * ・URL 直打ちによる誤削除を防止
     * ・削除後は一覧画面へリダイレクト
     */
	@PostMapping("/profile/delete")
	public String delete(@RequestParam("id") Integer id) {
		profileDeleteService.delete(id);
		return "redirect:/profile/list";
	}
}
