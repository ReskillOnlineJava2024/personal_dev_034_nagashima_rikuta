package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	UserRepository userRepository;

	//ログイン画面
	@GetMapping({ "/login", "/logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		session.invalidate();
		
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}
		return "login";
	}
	 //登録フォーム
	@GetMapping("/users/new")
	public String create() {
		return "userRegistration";
	}

	//ログインボタンクリック
	@PostMapping({ "/", "/login" })
	public String login(
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			Model model) {
		
		session.invalidate();

		List<UserEntity> userList = userRepository.findByUserNameAndPassword(userName, password);
		
		
		if (userList == null || userList.size() == 0) {
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}
		if (userName == null || userName.length() == 0) {
			model.addAttribute("message", "名前を入力してください");
			return "login";
		}
		if (password == null || password.length() == 0) {
			model.addAttribute("message", "パスワードを入力してください");
			return "login";
		}
		

		// セッション管理されたアカウント情報に名前をセット
		account.setUserName(userName);
		
		return "redirect:/recipes";
	}
	
	//ユーザー登録クリック
	@PostMapping("/users/add")
	public String store(
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			Model model) {

		UserEntity user = new UserEntity(userName,password);
		
		List<UserEntity> userList = userRepository.findByUserName(userName);
		
//		if (userName.equals(account.getUserName())) {
//			model.addAttribute("message", "その名前は使用済みです");
//			return "userRegistration";
//		}
		if (userList.size() > 0) {
			model.addAttribute("message", "その名前は使用済みです");
			return "userRegistration";
		}
		
		
		if (userName == null || userName.length() == 0) {
			model.addAttribute("message", "名前を入力してください");
			return "userRegistration";
		}
		if (password == null || password.length() == 0) {
			model.addAttribute("message", "パスワードを入力してください");
			return "userRegistration";
		}
		if (userName.length() >= 20 || userName.length() <= 3) {
			model.addAttribute("message", "名前は4文字以上20文字以下で入力してください");
			return "userRegistration";
		}
		if (password.length() >= 20 || password.length() <= 3) {
			model.addAttribute("message", "パスワードは4文字以上20文字以下で入力してください");
			return "userRegistration";
		}
		if (!password.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9]*$"))
		{
			model.addAttribute("message","パスワードはアルファベットのみで入力してください");
			return "userRegistration";
		}
	
//		if (userName.equals(user.getName())) {
//			model.addAttribute("message", "その名前は使用済みです");
//			return "userRegistration";
//			
//		}else {
				userRepository.save(user);

				return "redirect:/login";
//				}
	}
}
